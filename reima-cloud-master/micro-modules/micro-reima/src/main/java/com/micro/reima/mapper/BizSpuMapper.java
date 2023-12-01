package com.micro.reima.mapper;

import com.micro.reima.domain.BizSpu;
import com.micro.reima.model.bo.ProductBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * spuMapper接口
 *
 * @author micro
 * @date 2022-01-19
 */
public interface BizSpuMapper
{
    public Long selectByGoodCode(@Param("goodCode") String goodCode);

    public List<ProductBo> syncProductBos();

    public List<ProductBo> syncProductByGoodCode(@Param("goodCode") String goodCode);

    public Integer updateSyncTime(Long id);

    /**
     * 查询spu
     *
     * @param id spu主键
     * @return spu
     */
    public BizSpu selectBizSpuById(Long id);

    /**
     * 查询spu列表
     *
     * @param bizSpu spu
     * @return spu集合
     */
    public List<BizSpu> selectBizSpuList(BizSpu bizSpu);

    /**
     * 新增spu
     *
     * @param bizSpu spu
     * @return 结果
     */
    public int insertBizSpu(BizSpu bizSpu);

    /**
     * 修改spu
     *
     * @param bizSpu spu
     * @return 结果
     */
    public int updateBizSpu(BizSpu bizSpu);

    /**
     * 删除spu
     *
     * @param id spu主键
     * @return 结果
     */
    public int deleteBizSpuById(Long id);

    /**
     * 批量删除spu
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizSpuByIds(Long[] ids);
}
