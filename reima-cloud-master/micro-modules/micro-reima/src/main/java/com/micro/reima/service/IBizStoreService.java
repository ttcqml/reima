package com.micro.reima.service;

import com.micro.reima.domain.BizStore;

import java.util.List;
/**
 * 领取门店Service接口
 * 
 * @author micro
 * @date 2022-10-06
 */
public interface IBizStoreService 
{

    int importFromExcel(Long productId,List<BizStore> list);

    List<String> selectStoreCityList(Long productId);
    /**
     * 查询领取门店
     * 
     * @param id 领取门店主键
     * @return 领取门店
     */
    public BizStore selectBizStoreById(Long id);

    /**
     * 查询领取门店列表
     * 
     * @param bizStore 领取门店
     * @return 领取门店集合
     */
    public List<BizStore> selectBizStoreList(BizStore bizStore);

    /**
     * 新增领取门店
     * 
     * @param bizStore 领取门店
     * @return 结果
     */
    public int insertBizStore(BizStore bizStore);

    /**
     * 修改领取门店
     * 
     * @param bizStore 领取门店
     * @return 结果
     */
    public int updateBizStore(BizStore bizStore);

    /**
     * 批量删除领取门店
     * 
     * @param ids 需要删除的领取门店主键集合
     * @return 结果
     */
    public int deleteBizStoreByIds(Long[] ids);

    /**
     * 删除领取门店信息
     * 
     * @param id 领取门店主键
     * @return 结果
     */
    public int deleteBizStoreById(Long id);
}
