package com.micro.reima.service;

import com.micro.reima.model.vo.SimpleProductVo;
import java.util.List;
import com.micro.reima.domain.BizProduct;

/**
 * 商品信息Service接口
 * 
 * @author micro
 * @date 2021-12-25
 */
public interface IBizProductService 
{

    public List<SimpleProductVo> appList();
    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    public BizProduct selectBizProductById(Long id);

    /**
     * 查询商品信息列表
     * 
     * @param bizProduct 商品信息
     * @return 商品信息集合
     */
    public List<BizProduct> selectBizProductList(BizProduct bizProduct);

    /**
     * 新增商品信息
     * 
     * @param bizProduct 商品信息
     * @return 结果
     */
    public int insertBizProduct(BizProduct bizProduct);

    /**
     * 修改商品信息
     * 
     * @param bizProduct 商品信息
     * @return 结果
     */
    public int updateBizProduct(BizProduct bizProduct);

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键集合
     * @return 结果
     */
    public int deleteBizProductByIds(Long[] ids);

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    public int deleteBizProductById(Long id);
}
