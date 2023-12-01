package com.micro.reima.mapper;

import java.util.List;
import com.micro.reima.domain.BizStore;
import org.apache.ibatis.annotations.Param;

/**
 * 领取门店Mapper接口
 * 
 * @author micro
 * @date 2022-10-06
 */
public interface BizStoreMapper 
{
//    BizStore selectByAddr(@Param("addr") String addr);

    int deleteByProductId(@Param("productId") Long productId);

    List<String> selectStoreCityList(@Param("productId") Long productId);

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
     * 删除领取门店
     * 
     * @param id 领取门店主键
     * @return 结果
     */
    public int deleteBizStoreById(Long id);

    /**
     * 批量删除领取门店
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizStoreByIds(Long[] ids);
}
