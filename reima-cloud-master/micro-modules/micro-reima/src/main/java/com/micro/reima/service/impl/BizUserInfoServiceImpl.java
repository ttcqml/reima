package com.micro.reima.service.impl;

import com.alibaba.fastjson.JSON;
import com.micro.common.core.enums.AccScene;
import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.EmailUtils;
import com.micro.common.core.utils.image.BarcodeUtil;
import com.micro.common.security.service.TokenService;
import com.micro.reima.domain.*;
import com.micro.reima.mapper.*;
import com.micro.reima.model.admin.BizUserInfoVo;
import com.micro.reima.model.admin.ChildDto;
import com.micro.reima.model.admin.UserCouponInfo;
import com.micro.reima.model.admin.UserInfo;
import com.micro.reima.model.bo.BizUserChildInfo;
import com.micro.reima.model.bo.BizUserInfoBo;
import com.micro.reima.model.bo.InviteMobileBo;
import com.micro.reima.model.bo.RefreshUserIno;
import com.micro.reima.model.pos.LoginVo;
import com.micro.reima.model.pos.MemberInfoVo;
import com.micro.reima.model.vo.ShuYunMemInfoVo;
import com.micro.reima.model.vo.UserInfoVo;
import com.micro.reima.service.*;
import com.micro.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.xml.bind.DatatypeConverter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
@Slf4j
public class BizUserInfoServiceImpl implements IBizUserInfoService 
{

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IBizShopInfoService bizShopInfoService;

    @Autowired
    private BizUserInfoMapper bizUserInfoMapper;
    @Autowired
    private BizCouponSettingMapper bizCouponSettingMapper;
    @Autowired
    private BizUserCouponMapper bizUserCouponMapper;
    @Autowired
    private BizOldUserMapper bizOldUserMapper;
    @Autowired
    private BizUserAccRecordMapper bizUserAccRecordMapper;
    @Autowired
    private BizUserAccMapper bizUserAccMapper;
    @Autowired
    private BizRegionMapper bizRegionMapper;

    @Autowired
    private IBizUserChildService bizUserChildService;

    @Autowired
    private IBizUserAccService bizUserAccService;

    @Autowired
    private IBizUserCouponService bizUserCouponService;

    @Override
    public SysUserInfo selectSysUserById(Long userId) {
        return bizUserInfoMapper.selectSysUserByUserId(userId);
    }

    @Override
    public String selectMobileByMemberCode(String memberCode) {
        return bizUserInfoMapper.selectMobileByMemberCode(memberCode);
    }

    @Override
    public String selectMemberCodeByMobile(String mobile) {
        return bizUserInfoMapper.selectMemberCodeByMobile(mobile);
    }

    @Override
    public BizUserInfo selectBizUserInfoByUserId(Long userId) {
        return bizUserInfoMapper.selectBizUserInfoByUserId(userId);
    }

    @Override
    public UserInfoVo selectUserInfoVoByUserId(Long userId) {
        UserInfoVo vo = bizUserInfoMapper.selectUserInfoVoByUserId(userId);
        if(null == vo){
            throw new ServiceException("数据不存在");
        }
        if(StringUtils.isEmpty(vo.getPhonenumber())){
            return vo;
        }
        // 获取数云积分余额
        ShuYunMemInfoVo shuYunMemInfoVo = bizShopInfoService.getMemForShuYun(vo.getPhonenumber());
        if(null!=shuYunMemInfoVo){
            vo.setBalance(null!=shuYunMemInfoVo.getPoint()?new BigDecimal(shuYunMemInfoVo.getPoint()):BigDecimal.ZERO);
            bizUserAccMapper.updateUserPoints(userId,vo.getBalance());
        }
        if(null == vo.getBalance()){
            vo.setBalance(BigDecimal.ZERO);
        }
        // 判断是否有新券
        int isNew = bizUserCouponMapper.checkIsNew(userId);
        vo.setIsNew(isNew>0?1:0);
        return vo;
    }

    @Override
    @Transactional
    public void saveBizUserInfo(BizUserInfoBo bo,Long userId) {
        BizUserInfo bizUserInfo = bizUserInfoMapper.selectBizUserInfoByUserId(userId);
        if(null == bizUserInfo){
            throw new ServiceException("用户信息不存在");
        }
        bizUserInfo.setProvinceId(bo.getProvinceId());
        bizUserInfo.setCityId(bo.getCityId());
        bizUserInfoMapper.updateSysUserByUserId(userId,bo.getSex(),bo.getUsername(),null);
        bizUserChildService.saveBizUserChildInfo(bo.getBizUserChildInfoBoList(),userId);
        if(!CollectionUtils.isEmpty(bo.getBizUserChildInfoBoList())){
            BizUserChildInfo childInfo1 = bo.getBizUserChildInfoBoList().get(0);
            boolean fullInfo = null!=bo.getProvinceId()&&null!=bo.getCityId()&&!StringUtils.isAnyEmpty(bo.getUsername(),bo.getSex(),bo.getPhonenumber(),childInfo1.getName(),childInfo1.getSex(),childInfo1.getBirthday());
            String val = bizUserInfoMapper.getValByParamCode("edit.user.info");
            if(fullInfo && null != val && !"0" .equals(val)){
                bizUserAccService.addUserAccBalance(userId,new BigDecimal(val), AccScene.完善信息.name(),false);
            }
            for (BizUserChildInfo childInfo:bo.getBizUserChildInfoBoList()){
                if(null==bizUserInfo.getBirthday()&&StringUtils.isNotEmpty(childInfo.getBirthday())){
                    bizUserInfo.setBirthday(DateUtils.parseDate(childInfo.getBirthday()));
                }
                if(null==childInfo.getId()&&StringUtils.isNotEmpty(childInfo.getBirthday())){
                    boolean thisMonth = DateUtils.parseDateToStr("MM",DateUtils.parseDate(childInfo.getBirthday())).equals(DateUtils.parseDateToStr("MM",DateUtils.getNowDate()));
                    if(thisMonth){
                        sendCoupon(userId,"birthday","send.user.birth.coupon");
                    }
                }
            }
            List<BizUserChild> childs = bizUserChildService.selectBizUserChildsByUserId(userId);
            bizUserInfo.setChildNum(CollectionUtils.isEmpty(childs)?0L:Long.valueOf(childs.size()));
        }
        bizUserInfo.setUpdateTime(DateUtils.getNowDate());
        bizUserInfoMapper.updateBizUserInfo(bizUserInfo);
    }

    @Override
    public String barCode(Long userId) {
        SysUserInfo sysUserInfo = bizUserInfoMapper.selectSysUserByUserId(userId);
        if(null == sysUserInfo || StringUtils.isEmpty(sysUserInfo.getPhonenumber())){
            throw new ServiceException("未绑定手机号");
        }
        return DatatypeConverter.printBase64Binary(BarcodeUtil.generate(sysUserInfo.getPhonenumber()));
    }

    @Override
    public MemberInfoVo getMemberinfobymobile(String mobile) {
        MemberInfoVo vo = bizUserInfoMapper.getMemberinfobymobile(mobile);
        if(null == vo){
            return new MemberInfoVo();
        }
        vo.setMember_status_code("0".equals(vo.getMember_status_code())?"validate":"invalidate");
        vo.setGender("0".equals(vo.getGender())?"1":vo.getGender());
        try {
            if(null!=vo.getBirthday()){
                String[] temp = DateUtils.parseDateToStr("yyyy-MM-dd",vo.getBirthday()).split("-");
                vo.setBirthday_year(Integer.valueOf(temp[0]));
                vo.setBirthday_month(Integer.valueOf(temp[1]));
                vo.setBirthday_day(Integer.valueOf(temp[2]));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public BizUserInfoVo selectBizUserInfoById(Long id)
    {
        return bizUserInfoMapper.selectBizUserInfoById(id);
    }

    /**
     * 查询用户列表
     * 
     * @param bizUserInfo 用户
     * @return 用户
     */
    @Override
    public List<BizUserInfoVo> selectBizUserInfoList(
        BizUserInfo bizUserInfo)
    {
        return bizUserInfoMapper.selectBizUserInfoList(bizUserInfo);
    }

    @Override
    public List<BizOldUser> selectBizOldUserList(BizOldUser bizOldUser) {
        return bizOldUserMapper.selectBizOldUserList(bizOldUser);
    }

    @Override
    public List<BizOldUser> exportBizOldUserList() {
        return bizOldUserMapper.exportBizOldUserList();
    }

    /**
     * 新增用户
     * 
     * @param bizUserInfo 用户
     * @return 结果
     */
    @Override
    public int insertBizUserInfo(BizUserInfo bizUserInfo)
    {
        bizUserInfo.setCreateTime(DateUtils.getNowDate());
        return bizUserInfoMapper.insertBizUserInfo(bizUserInfo);
    }

    /**
     * 修改用户
     * 
     * @param bizUserInfo 用户
     * @return 结果
     */
    @Override
    public int updateBizUserInfo(BizUserInfo bizUserInfo)
    {
        bizUserInfo.setUpdateTime(DateUtils.getNowDate());
        return bizUserInfoMapper.updateBizUserInfo(bizUserInfo);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteBizUserInfoByIds(Long[] ids)
    {
        return bizUserInfoMapper.deleteBizUserInfoByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteBizUserInfoById(Long id)
    {
        return bizUserInfoMapper.deleteBizUserInfoById(id);
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    protected void refreshOldUser(){
        List<RefreshUserIno> infos = bizUserInfoMapper.selectRefreshUserInfo();
        if(CollectionUtils.isEmpty(infos)){
            return;
        }
        log.info("refreshOldUser:{}",infos.size());
        for (RefreshUserIno info:infos){
            BizUserInfo bizUserInfo = bizUserInfoMapper.selectBizUserInfoByUserId(info.getUserId());
            if(null == bizUserInfo){
                continue;
            }
            try {
                if(StringUtils.isNotEmpty(info.getProvince())&&StringUtils.isNotEmpty(info.getCity())){
                    Long province = bizRegionMapper.selectByProName(info.getProvince());
                    if(null!=province){
                        Long city = bizRegionMapper.selectByCityName(province,info.getCity());
                        if(null == city){
                            city = bizRegionMapper.selectDefaultCityByPid(province);
                        }
                        bizUserInfo.setProvinceId(province);
                        bizUserInfo.setCityId(city);
                        bizUserInfo.setUpdateTime(DateUtils.getNowDate());
                        bizUserInfoMapper.updateBizUserInfo(bizUserInfo);
                    }
                }
            }catch (Exception e){
                log.error("province:{}",e);
            }
        }
    }

    @Scheduled(cron = "0/1 * * * * ?")
    protected void checkOldUser(){
        List<UserInfo> userInfos = bizUserInfoMapper.selectUserInfos();
        if(CollectionUtils.isEmpty(userInfos)){
            return;
        }
        for (UserInfo userInfo:userInfos){
            try {
                BizOldUser oldUser = bizOldUserMapper.selectBizOldUserByMobile(userInfo.getPhonenumber());
                if(null == oldUser){
                    bizUserInfoMapper.updateSysUserByUserId(userInfo.getUserId(),null,null,"new member");
                }else{
                    String sex = StringUtils.isEmpty(oldUser.getSex())?"1":oldUser.getSex();
                    bizUserInfoMapper.updateSysUserByUserId(userInfo.getUserId(),sex,oldUser.getNickName(),"old member");
                    BizUserInfo bizUserInfo = bizUserInfoMapper.selectBizUserInfoByUserId(userInfo.getUserId());
                    String birthDay = StringUtils.isNotEmpty(oldUser.getBirthday())||"0000-00-00".equals(oldUser.getBirthday())?oldUser.getBirthday():"";
                    // 省份 城市
                    try {
                        if(StringUtils.isNotEmpty(oldUser.getProvince())&&StringUtils.isNotEmpty(oldUser.getCity())){
                            Long province = bizRegionMapper.selectByProName(oldUser.getProvince());
                            if(null!=province){
                                Long city = bizRegionMapper.selectByCityName(province,oldUser.getCity());
                                if(null == city){
                                    city = bizRegionMapper.selectDefaultCityByPid(province);
                                }
                                bizUserInfo.setProvinceId(province);
                                bizUserInfo.setCityId(city);
                            }
                        }
                    }catch (Exception e){
                        log.error("province:{}",e);
                    }
                    try{
                        if(null!=oldUser.getChild1()&&StringUtils.isNotEmpty(oldUser.getChild1())&&!"NULL".equals(oldUser.getChild1())){
                            ChildDto child1 = JSON.parseObject(oldUser.getChild1(),ChildDto.class);
                            Date birth1 = StringUtils.isNotEmpty(child1.getBirthday())?DateUtils.parseDate(child1.getBirthday()):null;
                            if(StringUtils.isEmpty(birthDay)){
                                birthDay = child1.getBirthday();
                            }
                            bizUserChildService.insertBizUserChild(buildBizUserChild(userInfo.getUserId(),child1.getName(),child1.getSex(),birth1));
                        }
                        if(null!=oldUser.getChild2()&&StringUtils.isNotEmpty(oldUser.getChild2())&&!"NULL".equals(oldUser.getChild1())){
                            ChildDto child2 = JSON.parseObject(oldUser.getChild1(),ChildDto.class);
                            Date birth2 = StringUtils.isNotEmpty(child2.getBirthday())?DateUtils.parseDate(child2.getBirthday()):null;
                            bizUserChildService.insertBizUserChild(buildBizUserChild(userInfo.getUserId(),child2.getName(),child2.getSex(),birth2));
                        }
                    }catch (Exception e){
                        log.error("{}",e);
                    }
                    /*try{
                        if(null!=oldUser.getPoints()&&oldUser.getPoints()>0){
                            bizUserAccService.addUserAccBalance(userInfo.getUserId(),new BigDecimal(oldUser.getPoints()),"初始化积分",false);
                        }
                    }catch (Exception e){
                        log.error("{}",e);
                    }*/
                    try{
                        if(null!=oldUser.getCoupons()&&StringUtils.isNotEmpty(oldUser.getCoupons())&&!"NULL".equals(oldUser.getChild1())){
                            List<UserCouponInfo> userCouponInfos = JSON.parseArray(oldUser.getCoupons(),UserCouponInfo.class);
                            for (UserCouponInfo userCouponInfo:userCouponInfos){
                                bizUserCouponMapper.insertBizUserCoupon(buildBizUserCoupon(userInfo.getUserId(),userCouponInfo));
                            }
                        }
                    }catch (Exception e){
                        log.error("{}",e);
                    }
                    bizOldUserMapper.updateById(oldUser.getId());
                    bizUserInfo.setBirthday(StringUtils.isEmpty(birthDay)?null:DateUtils.parseDate(birthDay));
                    bizUserInfoMapper.updateBizUserInfo(bizUserInfo);
                }
            }catch (Exception e){
                log.error("checkOldUser>>{}",e);
            }
        }
    }

    private BizUserCoupon buildBizUserCoupon(Long userId,UserCouponInfo userCouponInfo){
        BizUserCoupon bizUserCoupon = new BizUserCoupon();
        bizUserCoupon.setUserId(userId);
        bizUserCoupon.setVerificationCode(genVerificationCode());
        bizUserCoupon.setCouponId(userCouponInfo.getId());
        bizUserCoupon.setStartTime(DateUtils.parseDate(userCouponInfo.getStartAt()));
        bizUserCoupon.setEndTime(DateUtils.parseDate(userCouponInfo.getEndAt()));
        bizUserCoupon.setStatus(0L);
        bizUserCoupon.setDelFlag("0");
        bizUserCoupon.setCreateBy("system");
        bizUserCoupon.setUpdateBy("system");
        bizUserCoupon.setCreateTime(DateUtils.getNowDate());
        bizUserCoupon.setUpdateTime(DateUtils.getNowDate());
        return bizUserCoupon;
    }

    private String genVerificationCode(){
        String code = RandomStringUtils.randomNumeric(13);
        if(null!=bizUserCouponMapper.selectByVerificationCode(code)){
            return genVerificationCode();
        }
        return code;
    }

    private BizUserChild buildBizUserChild(Long userId,String name,String sex, Date birthday){
        BizUserChild bizUserChild = new BizUserChild();
        bizUserChild.setUserId(userId);
        bizUserChild.setName(name);
        bizUserChild.setSex(sex);
        bizUserChild.setBirthday(birthday);
        return bizUserChild;
    }

    // 入会赠送
    @Scheduled(cron = "0/10 * * * * ?")
    protected void registSendCoupon(){
        try {
            log.info(">>>>registSendCoupon");
            List<BizCouponSetting> bizCouponSettings = bizCouponSettingMapper.selectByTime();
            if(CollectionUtils.isEmpty(bizCouponSettings)){
                return;
            }
            log.info(">>>>registSendCoupon:bizCouponSettings:{}",bizCouponSettings.size());
            for (BizCouponSetting setting:bizCouponSettings){
                List<BizUserInfo> users = new ArrayList<>();
                if("1".equals(setting.getIsAll())){
                    users = bizUserInfoMapper.checkAllBizUserInfo();
                    log.info(">>>>registSendCoupon-allusers:{}",users.size());
                    if(CollectionUtils.isEmpty(users)){
                        continue;
                    }
                    for (BizUserInfo user:users){
                        try{
                            bizUserCouponService.saveOnce(setting.getCouponId(),user.getUserId(),"regist");
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }else{
                    users = bizUserInfoMapper.checkBizUserInfo();
                    log.info(">>>>registSendCoupon-users:{}",users.size());
                    if(CollectionUtils.isEmpty(users)){
                        continue;
                    }
                    for (BizUserInfo user:users){
                        try{
                            bizUserCouponService.saveOnce(setting.getCouponId(),user.getUserId(),"regist");
                            user.setMemo("1");
                            bizUserInfoMapper.updateBizUserInfo(user);
                        }catch (Exception e){
                            log.error(">>>>registSendCoupon:{},{}",JSON.toJSONString(setting),e);
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 注册赠送优惠券
     */
//    @Scheduled(cron = "0/3 * * * * ?")
//    protected void checkBizUserAcc(){
//        try {
//            List<BizUserInfo> users = bizUserInfoMapper.checkBizUserInfo();
//            if(CollectionUtils.isEmpty(users)){
//                return;
//            }
//            for (BizUserInfo user:users){
//                sendCoupon(user.getUserId(),"register","send.user.coupon");
//                user.setMemo("1");
//                bizUserInfoMapper.updateBizUserInfo(user);
//            }
//        }catch (Exception e){
//            log.error("checkBizUserAcc1:{}",e);
//        }
//    }

//    /**
//     * 全量会员注册赠送优惠券
//     */
//    @Scheduled(cron = "0/3 * * * * ?")
//    protected void checkAllBizUserAcc(){
//        try {
//            List<BizUserInfo> users = bizUserInfoMapper.checkAllBizUserInfo();
//            if(CollectionUtils.isEmpty(users)){
//                return;
//            }
//            for (BizUserInfo user:users){
//                sendCoupon(user.getUserId(),"register","send.user.activity.coupon");
//                //user.setMemo("1");
//                //bizUserInfoMapper.updateBizUserInfo(user);
//            }
//        }catch (Exception e){
//            log.error("checkBizUserAcc1:{}",e);
//        }
//    }

    // 检查会员孩子生日并发送生日券
    @Scheduled(cron = "0 15 0 * * ?")
    protected void checkBizUserBirthday(){
        try {
            List<Long> userIds = bizUserInfoMapper.checkBizUserChildBirthday();
            if(CollectionUtils.isEmpty(userIds)){
                return;
            }
            log.error(">>检查会员孩子生日并发送生日券{}",userIds.size());
            for (Long userId:userIds){
                int childs = userIds.stream().filter(p->p==userId.intValue()).collect(Collectors.toList()).size();
                if(bizUserInfoMapper.duplicateCheck(userId,8L)>=childs){
                    continue;
                }
                sendCoupon(userId,"birthday","send.user.birth.coupon");
            }
        }catch (Exception e){
            log.error("checkBizUserAcc1:{}",e);
        }
    }

    // SS22春夏会员专享活动
//    @Scheduled(cron = "0 0 15 24 2 ?")
//    protected void ss22Activity(){
//        try {
//            List<Long> userIds = bizUserInfoMapper.selectSs22ActivityUserIds();
//            if(CollectionUtils.isEmpty(userIds)){
//                return;
//            }
//            for (Long userId:userIds){
//                sendCoupon(userId,"ss22Activity","send.user.activity.coupon");
//            }
//        }catch (Exception e){
//            log.error("checkBizUserAcc1:{}",e);
//        }
//    }

    /**
     * 邀请奖励
     */
    @Scheduled(cron = "0/10 * * * * ?")
    protected void checkInviteUser(){
        bizUserCouponService.checkBizUserCoupon();

        List<InviteMobileBo> bos = bizUserInfoMapper.selectInviteMobileBo();
        if(CollectionUtils.isEmpty(bos)){
            return;
        }
        for (InviteMobileBo bo:bos){
            // 查询邀请人
            Long inviteUserId = bizUserInfoMapper.selectUserIdByMobile(bo.getMobile());
            if(null==inviteUserId){
                continue;
            }
            Integer sum = bizUserAccRecordMapper.sumUserAccByScene(inviteUserId,AccScene.邀请.name());
            if(null!=sum&&sum>3000){
                continue;
            }
            sum = null == sum ? 0 : sum;
            Integer amt = 300;
            if(sum+amt>3000){
                amt = 3000-sum;
            }
            bizUserAccService.addUserAccBalance(inviteUserId,new BigDecimal(amt),AccScene.邀请.name(), true);
            bizUserInfoMapper.updateInviteMobile(bo.getUserId());
        }
    }

    private void sendCoupon(Long userId,String from,String key){
        try {
            String couponIds = bizUserInfoMapper.getValByParamCode(key);
            if(StringUtils.isEmpty(couponIds)){
                return;
            }
            String[] ids = couponIds.split(",");
            for (String id:ids){
                try {
                    if("birthday".equals(from)){
                        bizUserCouponService.save(Long.valueOf(id),userId,from);
                    }else{
                        bizUserCouponService.saveOnce(Long.valueOf(id),userId,from);
                    }
                }catch (Exception e){
                    log.error("sendCoupon:{},{}",userId,e);
                }
            }
        }catch (Exception e){
            log.error("{}",e);
        }
    }

    private String genSerialNum(String mobile){
        String serialNum = DigestUtils.md5Hex("re"+mobile).substring(8,24);
        if(null != bizUserInfoMapper.selectBizUserInfoBySerialNum(serialNum)){
            return genSerialNum(mobile);
        }
        return serialNum;
    }

    @Override
    public LoginVo posLogin(String grant_type,String appId, String appSecret) {
        if(!appSecret.equals(DigestUtils.sha1Hex("d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN"))){ // todo d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN
            throw new ServiceException("秘钥错误");
        }
        Map<String,Object> result = tokenService.createPosToken(appId,appSecret);
        LoginVo vo = new LoginVo(grant_type);
        if(null!=result){
            vo.setAccess_token(result.get("access_token")+"");
            vo.setExpires_in(Integer.valueOf(result.get("expires_in")+""));
            vo.setRefresh_token(result.get("refresh_token")+"");
            return vo;
        }
        throw new ServiceException("生成Token失败");
    }

    @Override
    public LoginVo refresToken(String grant_type,String refreshToken) {
        LoginUser loginUser = tokenService.getLoginUser(refreshToken);
        if(null == loginUser){
            throw new ServiceException("获取用户状态失败");
        }
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= 0)
        {
            throw new ServiceException("刷新Token失效,请重新获取Token");
        }
        // todo d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN
        return posLogin(grant_type,"XtWVyfAo5XDNlrPD2VIaQIlkt704Zgug",DigestUtils.md5Hex("d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN"));
    }

    // 发券监控1
    @Scheduled(cron = "0 0 0/1 * * ?")
    protected void monitor1(){
        try {
            List<BizCouponSetting> bizCouponSettings = bizCouponSettingMapper.selectByTime();
            if(CollectionUtils.isEmpty(bizCouponSettings)){
                return;
            }
            for (BizCouponSetting setting:bizCouponSettings){
                List<BizUserInfo> users = new ArrayList<>();
                if("1".equals(setting.getIsAll())){
                    users = bizUserInfoMapper.checkAllBizUserInfo();
                    int counts = bizUserCouponMapper.countByCouponIdToday(setting.getCouponId());
                    if(users.size()!=counts){
                        String content = setting.getName()+"[全量发券ID:("+setting.getCouponId()+")今天应发:"+users.size()+"今天实发:"+counts+"]";
                        EmailUtils.sendExceptionMail("入会发券异常",content);
                    }
                }else{
                    users = bizUserInfoMapper.checkBizUserInfo2();
                    int counts = bizUserCouponMapper.countByCouponIdToday(setting.getCouponId());
                    if(users.size()!=counts){
                        String content = setting.getName()+"[仅新会员ID:("+setting.getCouponId()+")今天应发:"+users.size()+"今天实发:"+counts+"]";
                        EmailUtils.sendExceptionMail("入会发券异常",content);
                    }
                }
            }
        }catch (Exception e){
            log.error("monitor1:{}",e);
        }
    }

    // 发券监控2
    @Scheduled(cron = "0 0 0/1 * * ?")
    protected void monitor2(){
        try {
            List<Long> userIds = bizUserInfoMapper.checkBizUserChildBirthday();
            if(CollectionUtils.isEmpty(userIds)){
                return;
            }
            int counts = bizUserCouponMapper.countByCouponIdMonth(8L);
            if(userIds.size()!=counts){
                String content = "生日折扣券[仅新会员ID:("+8+")本月应发:"+userIds.size()+"本月实发:"+counts+"]";
                EmailUtils.sendExceptionMail("生日折扣券发券异常",content);
            }
        }catch (Exception e){
            log.error("monitor2:{}",e);
        }
    }
}
