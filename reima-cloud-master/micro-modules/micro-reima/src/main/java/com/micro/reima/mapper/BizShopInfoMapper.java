package com.micro.reima.mapper;

import java.util.List;
import com.micro.reima.domain.BizShopInfo;

/**
 * 店铺Mapper接口
 * 
 * @author micro
 * @date 2022-01-08
 */
public interface BizShopInfoMapper 
{
    public List<BizShopInfo> syncBizShopInfo();
    public Integer updateSyncTime(Long id);

    public Long selectBizShopInfoByShopId(String shopId);
    /**
     * 查询店铺
     * 
     * @param id 店铺主键
     * @return 店铺
     */
    public BizShopInfo selectBizShopInfoById(Long id);

    /**
     * 查询店铺列表
     * 
     * @param bizShopInfo 店铺
     * @return 店铺集合
     */
    public List<BizShopInfo> selectBizShopInfoList(BizShopInfo bizShopInfo);

    /**
     * 新增店铺
     * 
     * @param bizShopInfo 店铺
     * @return 结果
     */
    public int insertBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 修改店铺
     * 
     * @param bizShopInfo 店铺
     * @return 结果
     */
    public int updateBizShopInfo(BizShopInfo bizShopInfo);

    /**
     * 删除店铺
     * 
     * @param id 店铺主键
     * @return 结果
     */
    public int deleteBizShopInfoById(Long id);

    /**
     * 批量删除店铺
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizShopInfoByIds(Long[] ids);
}
