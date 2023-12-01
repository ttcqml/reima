package com.micro.reima.service;

import com.micro.reima.domain.BizOldUser;
import com.micro.reima.domain.BizUserInfo;
import com.micro.reima.domain.SysUserInfo;
import com.micro.reima.model.admin.BizUserInfoVo;
import com.micro.reima.model.bo.BizUserInfoBo;
import com.micro.reima.model.pos.LoginVo;
import com.micro.reima.model.pos.MemberCouponBody;
import com.micro.reima.model.pos.MemberCouponVo;
import com.micro.reima.model.pos.MemberInfoVo;
import com.micro.reima.model.vo.UserInfoVo;
import com.micro.system.api.domain.SysUser;
import java.util.List;

/**
 * 用户Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizUserInfoService 
{

    public SysUserInfo selectSysUserById(Long userId);

    public String selectMobileByMemberCode(String memberCode);

    public String selectMemberCodeByMobile(String mobile);

    public BizUserInfo selectBizUserInfoByUserId(Long userId);

    public UserInfoVo selectUserInfoVoByUserId(Long userId);

    public void saveBizUserInfo(BizUserInfoBo bo,Long userId);

    public String barCode(Long userId);

    MemberInfoVo getMemberinfobymobile(String mobile);

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

    public List<BizOldUser> selectBizOldUserList(BizOldUser bizOldUser);

    public List<BizOldUser> exportBizOldUserList();
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
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteBizUserInfoByIds(Long[] ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteBizUserInfoById(Long id);

    public LoginVo posLogin(String grant_type,String appId,String appSecret);

    public LoginVo refresToken(String grant_type,String refreshToken);
}
