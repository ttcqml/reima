package com.micro.reima.service.impl;

import com.micro.reima.model.vo.BizUserAddressVo;
import com.micro.reima.model.vo.SimpleUserAddressVo;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizUserAddressMapper;
import com.micro.reima.domain.BizUserAddress;
import com.micro.reima.service.IBizUserAddressService;

/**
 * 用户地址Service业务层处理
 * 
 * @author micro
 * @date 2021-12-25
 */
@Service
public class BizUserAddressServiceImpl implements IBizUserAddressService 
{
    @Autowired
    private BizUserAddressMapper bizUserAddressMapper;

    @Override
    public SimpleUserAddressVo selectBizUserAddressByUserId(Long userId) {
        return bizUserAddressMapper.selectSimpleUserAddressVoByUserId(userId);
    }

    @Override
    public void save(BizUserAddress bizUserAddress) {
        BizUserAddress exsit = bizUserAddressMapper.selectBizUserAddressByUserId(bizUserAddress.getUserId());
        if(null == exsit){
            bizUserAddress.setDelFlag("0");
            bizUserAddress.setIsDefault("1");
            bizUserAddress.setCreateBy("system");
            bizUserAddress.setUpdateBy("system");
            bizUserAddress.setCreateTime(DateUtils.getNowDate());
            bizUserAddress.setUpdateTime(DateUtils.getNowDate());
            bizUserAddressMapper.insertBizUserAddress(bizUserAddress);
            return;
        }
        exsit.setTel(bizUserAddress.getTel());
        exsit.setName(bizUserAddress.getName());
        exsit.setProvince(bizUserAddress.getProvince());
        exsit.setCity(bizUserAddress.getCity());
        exsit.setArea(bizUserAddress.getArea());
        exsit.setAddress(bizUserAddress.getAddress());
        exsit.setUpdateTime(DateUtils.getNowDate());
        bizUserAddressMapper.updateBizUserAddress(bizUserAddress);
    }

    /**
     * 查询用户地址
     * 
     * @param id 用户地址主键
     * @return 用户地址
     */
    @Override
    public BizUserAddressVo selectBizUserAddressById(Long id)
    {
        return bizUserAddressMapper.selectBizUserAddressById(id);
    }

    /**
     * 查询用户地址列表
     * 
     * @param bizUserAddress 用户地址
     * @return 用户地址
     */
    @Override
    public List<BizUserAddressVo> selectBizUserAddressList(BizUserAddress bizUserAddress)
    {
        return bizUserAddressMapper.selectBizUserAddressList(bizUserAddress);
    }

    /**
     * 新增用户地址
     * 
     * @param bizUserAddress 用户地址
     * @return 结果
     */
    @Override
    public int insertBizUserAddress(BizUserAddress bizUserAddress)
    {
        bizUserAddress.setCreateTime(DateUtils.getNowDate());
        return bizUserAddressMapper.insertBizUserAddress(bizUserAddress);
    }

    /**
     * 修改用户地址
     * 
     * @param bizUserAddress 用户地址
     * @return 结果
     */
    @Override
    public int updateBizUserAddress(BizUserAddress bizUserAddress)
    {
        bizUserAddress.setUpdateTime(DateUtils.getNowDate());
        return bizUserAddressMapper.updateBizUserAddress(bizUserAddress);
    }

    /**
     * 批量删除用户地址
     * 
     * @param ids 需要删除的用户地址主键
     * @return 结果
     */
    @Override
    public int deleteBizUserAddressByIds(Long[] ids)
    {
        return bizUserAddressMapper.deleteBizUserAddressByIds(ids);
    }

    /**
     * 删除用户地址信息
     * 
     * @param id 用户地址主键
     * @return 结果
     */
    @Override
    public int deleteBizUserAddressById(Long id)
    {
        return bizUserAddressMapper.deleteBizUserAddressById(id);
    }
}
