package com.micro.reima.mapper;

import com.micro.reima.domain.BizSku;
import java.util.Map;

import com.micro.reima.model.bo.ProductSkuPo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * skuMapper接口
 *
 * @author micro
 * @date 2022-01-19
 */
public interface BizSkuMapper
{
    public Long selectBySkuCode(@Param("skuCode") String skuCode);
    public List<ProductSkuPo> selectByGoodCode(@Param("goodCode") String goodCode);
    public Integer updateSyncTime(@Param("goodCode") String goodCode);
    /**
     * 查询sku
     *
     * @param id sku主键
     * @return sku
     */
    public BizSku selectBizSkuById(Long id);

    /**
     * 查询sku列表
     *
     * @param bizSku sku
     * @return sku集合
     */
    public List<BizSku> selectBizSkuList(BizSku bizSku);

    /**
     * 新增sku
     *
     * @param bizSku sku
     * @return 结果
     */
    public int insertBizSku(BizSku bizSku);

    /**
     * 修改sku
     *
     * @param bizSku sku
     * @return 结果
     */
    public int updateBizSku(BizSku bizSku);

    /**
     * 删除sku
     *
     * @param id sku主键
     * @return 结果
     */
    public int deleteBizSkuById(Long id);

    /**
     * 批量删除sku
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizSkuByIds(Long[] ids);
}
