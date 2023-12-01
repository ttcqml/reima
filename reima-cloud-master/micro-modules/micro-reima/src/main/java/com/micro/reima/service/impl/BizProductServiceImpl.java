package com.micro.reima.service.impl;

import com.micro.common.core.utils.ip.OkHttpUtil;
import com.micro.reima.config.ProConfig;
import com.micro.reima.model.vo.SimpleProductVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizProductMapper;
import com.micro.reima.domain.BizProduct;
import com.micro.reima.service.IBizProductService;

/**
 * 商品信息Service业务层处理
 * 
 * @author micro
 * @date 2021-12-25
 */
@Service
public class BizProductServiceImpl implements IBizProductService 
{
    @Autowired
    private BizProductMapper bizProductMapper;
    @Autowired
    private ProConfig proConfig;

    @Override
    public List<SimpleProductVo> appList() {
        return bizProductMapper.appList();
    }

    /**
     * 查询商品信息
     * 
     * @param id 商品信息主键
     * @return 商品信息
     */
    @Override
    public BizProduct selectBizProductById(Long id)
    {
        return bizProductMapper.selectBizProductById(id);
    }

    /**
     * 查询商品信息列表
     * 
     * @param bizProduct 商品信息
     * @return 商品信息
     */
    @Override
    public List<BizProduct> selectBizProductList(BizProduct bizProduct)
    {
        return bizProductMapper.selectBizProductList(bizProduct);
    }

    /**
     * 新增商品信息
     * 
     * @param bizProduct 商品信息
     * @return 结果
     */
    @Override
    public int insertBizProduct(BizProduct bizProduct)
    {
        bizProduct.setId(null);
        if(null == bizProduct.getWay()){
            bizProduct.setWay("1");
        }
        bizProduct.setCreateTime(DateUtils.getNowDate());
        return bizProductMapper.insertBizProduct(bizProduct);
    }

    /**
     * 修改商品信息
     * 
     * @param bizProduct 商品信息
     * @return 结果
     */
    @Override
    public int updateBizProduct(BizProduct bizProduct)
    {
        bizProduct.setUpdateTime(DateUtils.getNowDate());
        return bizProductMapper.updateBizProduct(bizProduct);
    }

    /**
     * 批量删除商品信息
     * 
     * @param ids 需要删除的商品信息主键
     * @return 结果
     */
    @Override
    public int deleteBizProductByIds(Long[] ids)
    {
        return bizProductMapper.deleteBizProductByIds(ids);
    }

    /**
     * 删除商品信息信息
     * 
     * @param id 商品信息主键
     * @return 结果
     */
    @Override
    public int deleteBizProductById(Long id)
    {
        return bizProductMapper.deleteBizProductById(id);
    }

    // todo
    private void syncFromPos(){
        Map<String,Object> params = new HashMap<>();
        params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.parseDateByPattern("2021-01-01",DateUtils.YYYY_MM_DD)));
        // params.put("page",1);
        // params.put("limit",10);
        String result = OkHttpUtil.iposApi(proConfig.getIposUrl(),"get_shop_list",proConfig.getIposKey(),proConfig.getIposSecret(),params);

        System.out.println("get_good_list:\n"+result);
    }
}
