package com.micro.reima.service.impl;

import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.StringUtils;
import com.micro.reima.domain.BizUserAccRecord;
import com.micro.reima.domain.BizUserInfo;
import com.micro.reima.mapper.BizUserAccRecordMapper;
import com.micro.reima.mapper.BizUserInfoMapper;
import com.micro.reima.model.admin.BizUserAccVo;
import com.micro.reima.model.bo.ChangePointBo;
import com.micro.reima.model.vo.ShuYunMemInfoVo;
import com.micro.reima.model.vo.SimpleUserInfo;
import com.micro.reima.model.vo.UserInfoVo;
import com.micro.reima.service.IBizShopInfoService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizUserAccMapper;
import com.micro.reima.domain.BizUserAcc;
import com.micro.reima.service.IBizUserAccService;
import org.springframework.util.CollectionUtils;

/**
 * 积分账户Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizUserAccServiceImpl implements IBizUserAccService 
{
    @Autowired
    private BizUserAccMapper bizUserAccMapper;
    @Autowired
    private BizUserAccRecordMapper bizUserAccRecordMapper;
    @Autowired
    private BizUserInfoMapper bizUserInfoMapper;
    @Autowired
    private IBizShopInfoService bizShopInfoService;

    @Override
    public void editBalance(Long userId, BigDecimal amt, String remark) {
        BizUserAcc acc = bizUserAccMapper.selectBizUserAccByUserId(userId);
        if(null == acc){
            throw new ServiceException("账户信息不存在");
        }
        acc.setBalance(acc.getBalance().add(amt));
        acc.setUpdateTime(DateUtils.getNowDate());
        bizUserAccMapper.updateBizUserAcc(acc);
        bizUserAccRecordMapper.insertBizUserAccRecord(BizUserAccRecord.buildBizUserAccRecord(userId,amt,remark));
    }

    @Override
    public BizUserAcc selectBizUserAccByUserId(Long userId) {
        return bizUserAccMapper.selectBizUserAccByUserId(userId);
    }

    @Override
    public void addUserAccBalance(Long userId, BigDecimal amt, String memo,boolean canRepeat) {
        // 创建记录
        if(!canRepeat){
            List<BizUserAccRecord> records = bizUserAccRecordMapper.selectBizUserAccRecordListByUserIdAndMemo(userId,memo);
            if(!CollectionUtils.isEmpty(records)){
                return;
            }
        }
        UserInfoVo userInfoVo = bizUserInfoMapper.selectUserInfoVoByUserId(userId);
        if(null!=userInfoVo&& StringUtils.isNotEmpty(userInfoVo.getPhonenumber())){
            ChangePointBo pointBo = new ChangePointBo();
            pointBo.setChangePoint(amt.intValue());
            pointBo.setPhonenumber(userInfoVo.getPhonenumber());
            pointBo.setSource("OTHER");// 业务来源 CONSUME:消费（负积分） OTHER:其他（可正可负）INIT_POINT:初始化积分(正积分)
            pointBo.setMemo(memo);
            if(!bizShopInfoService.changePointToShuYun(pointBo)){
                return;
            }
        }
        try {
            BizUserAcc bizUserAcc = bizUserAccMapper.selectBizUserAccByUserId(userId);
            if(null == bizUserAcc){
                bizUserAcc = buildBizUserAcc(userId);
                bizUserAccMapper.insertBizUserAcc(bizUserAcc);
            }
            bizUserAccRecordMapper.insertBizUserAccRecord(buildBizUserAccRecord(userId,amt,memo));
            bizUserAcc.setBalance(bizUserAcc.getBalance().add(amt));
            bizUserAcc.setUpdateTime(DateUtils.getNowDate());
            bizUserAccMapper.updateBizUserAcc(bizUserAcc);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    @Override
    public void subUserAccBalance(Long userId, BigDecimal amt, String memo) {
        UserInfoVo userInfoVo = bizUserInfoMapper.selectUserInfoVoByUserId(userId);
        if(null!=userInfoVo&& StringUtils.isNotEmpty(userInfoVo.getPhonenumber())){
            ShuYunMemInfoVo shuYunMemInfoVo = bizShopInfoService.getMemForShuYun(userInfoVo.getPhonenumber());
            if(null!=shuYunMemInfoVo&&amt.compareTo(new BigDecimal(shuYunMemInfoVo.getPoint()))==1){
                throw new ServiceException("积分余额不足");
            }
            ChangePointBo pointBo = new ChangePointBo();
            pointBo.setChangePoint(amt.negate().intValue());
            pointBo.setPhonenumber(userInfoVo.getPhonenumber());
            pointBo.setSource("CONSUME");// 业务来源 CONSUME:消费（负积分） OTHER:其他（可正可负）INIT_POINT:初始化积分(正积分)
            pointBo.setMemo(memo);
            if(!bizShopInfoService.changePointToShuYun(pointBo)){
                throw new ServiceException("积分扣除失败");
            }
        }
        BizUserAcc bizUserAcc = bizUserAccMapper.selectBizUserAccByUserId(userId);
        if(null == bizUserAcc){
            bizUserAccMapper.insertBizUserAcc(buildBizUserAcc(userId));
            throw new ServiceException("账户信息不存在");
        }
        if(amt.compareTo(bizUserAcc.getBalance())==1){
            throw new ServiceException("积分余额不足");
        }
        bizUserAccRecordMapper.insertBizUserAccRecord(buildBizUserAccRecord(userId,amt.negate(),memo));
        bizUserAcc.setBalance(bizUserAcc.getBalance().subtract(amt));
        bizUserAcc.setUpdateTime(DateUtils.getNowDate());
        bizUserAccMapper.updateBizUserAcc(bizUserAcc);
    }

    private BizUserAcc buildBizUserAcc(Long userId){
        BizUserAcc bizUserAcc = new BizUserAcc();
        bizUserAcc.setUserId(userId);
        bizUserAcc.setBalance(BigDecimal.ZERO);
        bizUserAcc.setDelFlag("0");
        bizUserAcc.setCreateTime(DateUtils.getNowDate());
        bizUserAcc.setUpdateTime(DateUtils.getNowDate());
        bizUserAcc.setCreateBy("system");
        bizUserAcc.setUpdateBy("system");
        return bizUserAcc;
    }

    private BizUserAccRecord buildBizUserAccRecord(Long userId,BigDecimal amt,String memo){
        BizUserAccRecord bizUserAccRecord = new BizUserAccRecord();
        bizUserAccRecord.setUserId(userId);
        bizUserAccRecord.setAmt(amt);
        bizUserAccRecord.setMemo(memo);
        bizUserAccRecord.setStatus("0");
        bizUserAccRecord.setDelFlag("0");
        bizUserAccRecord.setCreateTime(DateUtils.getNowDate());
        bizUserAccRecord.setUpdateTime(DateUtils.getNowDate());
        bizUserAccRecord.setCreateBy("system");
        bizUserAccRecord.setUpdateBy("system");
        return bizUserAccRecord;
    }

    /**
     * 查询积分账户
     * 
     * @param id 积分账户主键
     * @return 积分账户
     */
    @Override
    public BizUserAccVo selectBizUserAccById(Long id)
    {
        return bizUserAccMapper.selectBizUserAccById(id);
    }

    /**
     * 查询积分账户列表
     * 
     * @param bizUserAcc 积分账户
     * @return 积分账户
     */
    @Override
    public List<BizUserAccVo> selectBizUserAccList(BizUserAccVo bizUserAcc)
    {
        return bizUserAccMapper.selectBizUserAccList(bizUserAcc);
    }

    /**
     * 新增积分账户
     * 
     * @param bizUserAcc 积分账户
     * @return 结果
     */
    @Override
    public int insertBizUserAcc(BizUserAcc bizUserAcc)
    {
        bizUserAcc.setCreateTime(DateUtils.getNowDate());
        return bizUserAccMapper.insertBizUserAcc(bizUserAcc);
    }

    /**
     * 修改积分账户
     * 
     * @param bizUserAcc 积分账户
     * @return 结果
     */
    @Override
    public int updateBizUserAcc(BizUserAcc bizUserAcc)
    {
        bizUserAcc.setUpdateTime(DateUtils.getNowDate());
        return bizUserAccMapper.updateBizUserAcc(bizUserAcc);
    }

    /**
     * 批量删除积分账户
     * 
     * @param ids 需要删除的积分账户主键
     * @return 结果
     */
    @Override
    public int deleteBizUserAccByIds(Long[] ids)
    {
        return bizUserAccMapper.deleteBizUserAccByIds(ids);
    }

    /**
     * 删除积分账户信息
     * 
     * @param id 积分账户主键
     * @return 结果
     */
    @Override
    public int deleteBizUserAccById(Long id)
    {
        return bizUserAccMapper.deleteBizUserAccById(id);
    }

    @Scheduled(cron = "0 0 3 * * ?")
    protected void syncUserAcc(){
        List<SimpleUserInfo> infos = bizUserInfoMapper.selectSimpleUserInfos();
        if(CollectionUtils.isEmpty(infos)){
            return;
        }
        for (SimpleUserInfo vo:infos){
            // 获取数云积分余额
            ShuYunMemInfoVo shuYunMemInfoVo = bizShopInfoService.getMemForShuYun(vo.getPhonenumber());
            if(null!=shuYunMemInfoVo){
                BigDecimal balance = null!=shuYunMemInfoVo.getPoint()?new BigDecimal(shuYunMemInfoVo.getPoint()):BigDecimal.ZERO;
                bizUserAccMapper.updateUserPoints(vo.getUserId(),balance);
            }
        }
    }

    // 积分修正
    public int correctUserAcc(Long userId,BigDecimal amt){
        UserInfoVo userInfoVo = bizUserInfoMapper.selectUserInfoVoByUserId(userId);
        if(null!=userInfoVo&& StringUtils.isNotEmpty(userInfoVo.getPhonenumber())){
            ShuYunMemInfoVo shuYunMemInfoVo = bizShopInfoService.getMemForShuYun(userInfoVo.getPhonenumber());
            if(null!=shuYunMemInfoVo&&amt.compareTo(new BigDecimal(shuYunMemInfoVo.getPoint()))==1){
                throw new ServiceException("积分余额不足");
            }
            ChangePointBo pointBo = new ChangePointBo();
            pointBo.setChangePoint(amt.negate().intValue());
            pointBo.setPhonenumber(userInfoVo.getPhonenumber());
            pointBo.setSource("OTHER");// 业务来源 CONSUME:消费（负积分） OTHER:其他（可正可负）INIT_POINT:初始化积分(正积分)
            pointBo.setMemo("完善信息积分修正");
            if(!bizShopInfoService.changePointToShuYun(pointBo)){
                return 0;
            }
        }
        BizUserAcc bizUserAcc = bizUserAccMapper.selectBizUserAccByUserId(userId);
        if(null == bizUserAcc){
            bizUserAccMapper.insertBizUserAcc(buildBizUserAcc(userId));
            throw new ServiceException("账户信息不存在");
        }
        if(amt.compareTo(bizUserAcc.getBalance())==1){
            throw new ServiceException("积分余额不足");
        }
        bizUserAccRecordMapper.insertBizUserAccRecord(buildBizUserAccRecord(userId,amt.negate(),"完善信息积分修正"));
        bizUserAcc.setBalance(bizUserAcc.getBalance().subtract(amt));
        bizUserAcc.setUpdateTime(DateUtils.getNowDate());
        return bizUserAccMapper.updateBizUserAcc(bizUserAcc);
    }

}
