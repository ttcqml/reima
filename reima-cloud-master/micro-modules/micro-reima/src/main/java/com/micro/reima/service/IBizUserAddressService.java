package com.micro.reima.service;

import com.micro.reima.model.vo.BizUserAddressVo;
import com.micro.reima.model.vo.SimpleUserAddressVo;
import java.util.List;
import com.micro.reima.domain.BizUserAddress;

/**
 * 用户地址Service接口
 * 
 * @author micro
 * @date 2021-12-25
 */
public interface IBizUserAddressService 
{

    public void save(BizUserAddress bizUserAddress);

    public SimpleUserAddressVo selectBizUserAddressByUserId(Long userId);
    /**
     * 查询用户地址
     * 
     * @param id 用户地址主键
     * @return 用户地址
     */
    public BizUserAddressVo selectBizUserAddressById(Long id);

    /**
     * 查询用户地址列表
     * 
     * @param bizUserAddress 用户地址
     * @return 用户地址集合
     */
    public List<BizUserAddressVo> selectBizUserAddressList(BizUserAddress bizUserAddress);

    /**
     * 新增用户地址
     * 
     * @param bizUserAddress 用户地址
     * @return 结果
     */
    public int insertBizUserAddress(BizUserAddress bizUserAddress);

    /**
     * 修改用户地址
     * 
     * @param bizUserAddress 用户地址
     * @return 结果
     */
    public int updateBizUserAddress(BizUserAddress bizUserAddress);

    /**
     * 批量删除用户地址
     * 
     * @param ids 需要删除的用户地址主键集合
     * @return 结果
     */
    public int deleteBizUserAddressByIds(Long[] ids);

    /**
     * 删除用户地址信息
     * 
     * @param id 用户地址主键
     * @return 结果
     */
    public int deleteBizUserAddressById(Long id);
}
