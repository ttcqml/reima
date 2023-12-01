package com.micro.reima.service.impl;

import java.util.List;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizStore;
import com.micro.reima.mapper.BizStoreMapper;
import com.micro.reima.service.IBizStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * 领取门店Service业务层处理
 * 
 * @author micro
 * @date 2022-10-06
 */
@Service
public class BizStoreServiceImpl implements IBizStoreService
{
    @Autowired
    private BizStoreMapper bizStoreMapper;

    @Override
    @Transactional
    public int importFromExcel(Long productId,List<BizStore> list) {
        bizStoreMapper.deleteByProductId(productId);
        for (BizStore bizStore:list){
            bizStore.setId(null);
            bizStore.setProductId(productId);
            bizStore.setCity(trimAll(bizStore.getCity()));
            bizStore.setName(trimAll(bizStore.getName()));
            bizStore.setAddr(trimAll(bizStore.getAddr()));
            bizStore.setDelFlag("0");
            bizStore.setCreateBy(SecurityUtils.getUsername());
            bizStore.setUpdateBy(SecurityUtils.getUsername());
            bizStore.setCreateTime(DateUtils.getNowDate());
            bizStore.setUpdateTime(DateUtils.getNowDate());
            insertBizStore(bizStore);
        }
        return list.size();
    }

    private String trimAll(String str){
        return StringUtils.isNotEmpty(str)?str.replace(" ",""):str;
    }

    @Override
    public List<String> selectStoreCityList(Long productId) {
        return bizStoreMapper.selectStoreCityList(productId);
    }

    /**
     * 查询领取门店
     * 
     * @param id 领取门店主键
     * @return 领取门店
     */
    @Override
    public BizStore selectBizStoreById(Long id)
    {
        return bizStoreMapper.selectBizStoreById(id);
    }

    /**
     * 查询领取门店列表
     * 
     * @param bizStore 领取门店
     * @return 领取门店
     */
    @Override
    public List<BizStore> selectBizStoreList(BizStore bizStore)
    {
        return bizStoreMapper.selectBizStoreList(bizStore);
    }

    /**
     * 新增领取门店
     * 
     * @param bizStore 领取门店
     * @return 结果
     */
    @Override
    public int insertBizStore(BizStore bizStore)
    {
        bizStore.setCreateTime(DateUtils.getNowDate());
        return bizStoreMapper.insertBizStore(bizStore);
    }

    /**
     * 修改领取门店
     * 
     * @param bizStore 领取门店
     * @return 结果
     */
    @Override
    public int updateBizStore(BizStore bizStore)
    {
        bizStore.setUpdateTime(DateUtils.getNowDate());
        return bizStoreMapper.updateBizStore(bizStore);
    }

    /**
     * 批量删除领取门店
     * 
     * @param ids 需要删除的领取门店主键
     * @return 结果
     */
    @Override
    public int deleteBizStoreByIds(Long[] ids)
    {
        return bizStoreMapper.deleteBizStoreByIds(ids);
    }

    /**
     * 删除领取门店信息
     * 
     * @param id 领取门店主键
     * @return 结果
     */
    @Override
    public int deleteBizStoreById(Long id)
    {
        return bizStoreMapper.deleteBizStoreById(id);
    }
}
