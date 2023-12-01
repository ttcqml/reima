package com.micro.reima.mapper;

import com.micro.reima.domain.BizOldUser;
import com.micro.reima.model.bo.BizUserInfoBo;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 老用户信息Mapper接口
 * 
 * @author micro
 * @date 2022-01-24
 */
public interface BizOldUserMapper 
{

    public BizOldUser selectBizOldUserByMobile(@Param("mobile") String mobile);

    public List<BizUserInfoBo> syncOldUser();
    public int updateSyncTime(Long id);
    public int updateById(Long id);
    /**
     * 查询老用户信息
     * 
     * @param id 老用户信息主键
     * @return 老用户信息
     */
    public BizOldUser selectBizOldUserById(Long id);

    /**
     * 查询老用户信息列表
     * 
     * @param bizOldUser 老用户信息
     * @return 老用户信息集合
     */
    public List<BizOldUser> selectBizOldUserList(BizOldUser bizOldUser);

    public List<BizOldUser> exportBizOldUserList();

    /**
     * 新增老用户信息
     * 
     * @param bizOldUser 老用户信息
     * @return 结果
     */
    public int insertBizOldUser(BizOldUser bizOldUser);

    /**
     * 修改老用户信息
     * 
     * @param bizOldUser 老用户信息
     * @return 结果
     */
    public int updateBizOldUser(BizOldUser bizOldUser);

    /**
     * 删除老用户信息
     * 
     * @param id 老用户信息主键
     * @return 结果
     */
    public int deleteBizOldUserById(Long id);

    /**
     * 批量删除老用户信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizOldUserByIds(Long[] ids);
}
