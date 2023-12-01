package com.micro.reima.mapper;

import com.micro.reima.domain.SysUserInfo;
import com.micro.reima.model.admin.BizUserInfoVo;
import com.micro.reima.model.admin.UserInfo;
import com.micro.reima.model.bo.BizUserInfoBo;
import com.micro.reima.model.bo.InviteMobileBo;
import com.micro.reima.model.bo.RefreshUserIno;
import com.micro.reima.model.bo.UserMobileBo;
import com.micro.reima.model.pos.MemberCouponBody;
import com.micro.reima.model.pos.MemberCouponVo;
import com.micro.reima.model.pos.MemberInfoVo;
import com.micro.reima.model.vo.SimpleUserInfo;
import com.micro.reima.model.vo.UserInfoVo;
import com.micro.system.api.domain.SysUser;
import java.util.List;
import com.micro.reima.domain.BizUserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizUserInfoMapper 
{
    List<BizUserInfoBo> syncUser();
    List<SimpleUserInfo> syncUserWithoutMemberId();
    int updateMemberIdByUserId(@Param("userId") Long userId,@Param("memberId") String memberId);
    List<SimpleUserInfo> selectSimpleUserInfos();
    int updateUserSyncTime(@Param("id") Long id);

    public String getValByParamCode(@Param("code") String code);

    public List<InviteMobileBo> selectInviteMobileBo();

    public int updateInviteMobile(Long userId);

    public SysUserInfo selectSysUserByUserId(@Param("userId") Long userId);

    public String selectMobileByMemberCode(@Param("memberCode") String memberCode);

    public String selectMemberCodeByMobile(@Param("mobile") String mobile);

    public UserInfoVo selectUserInfoVoByUserId(Long userId);

    public BizUserInfo selectBizUserInfoByUserId(Long userId);

    public List<BizUserInfo> checkBizUserInfo();

    public List<BizUserInfo> checkBizUserInfo2();

    public List<BizUserInfo> checkAllBizUserInfo();

    public List<Long> selectAllUserIdWithMobile();

    public List<Long> checkBizUserChildBirthday();

    int duplicateCheck(@Param("userId") Long userId,@Param("couponId") Long couponId);

    public List<Long> selectSs22ActivityUserIds();

    public List<UserMobileBo> selectUserWithoutSerialNum();

    public List<UserMobileBo> selectUserwithoutBarCodeUrl();

    public int updateVipCodeUrlByUserId(@Param("userId") Long userId,@Param("url") String url);

    public BizUserInfo selectBizUserInfoBySerialNum(@Param("serialNum") String serialNum);

    public int updateUserSerialNum(@Param("userId") Long userId,@Param("serialNum") String serialNum);

    public Long selectUserIdByMobile(@Param("mobile") String mobile);

    public MemberInfoVo getMemberinfobymobile(@Param("mobile") String mobile);

    public int updateSysUserByUserId(@Param("userId") Long userId,@Param("sex") String sex,@Param("nickName") String nickName,@Param("createBy") String createBy);

    public List<UserInfo> selectUserInfos();

    public List<RefreshUserIno> selectRefreshUserInfo();

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public BizUserInfoVo selectBizUserInfoById(Long id);

    /**
     * 查询用户列表
     * 
     * @param bizUserInfo 用户
     * @return 用户集合
     */
    public List<BizUserInfoVo> selectBizUserInfoList(BizUserInfo bizUserInfo);

    /**
     * 新增用户
     * 
     * @param bizUserInfo 用户
     * @return 结果
     */
    public int insertBizUserInfo(BizUserInfo bizUserInfo);

    /**
     * 修改用户
     * 
     * @param bizUserInfo 用户
     * @return 结果
     */
    public int updateBizUserInfo(BizUserInfo bizUserInfo);

    /**
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteBizUserInfoById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserInfoByIds(Long[] ids);
}
