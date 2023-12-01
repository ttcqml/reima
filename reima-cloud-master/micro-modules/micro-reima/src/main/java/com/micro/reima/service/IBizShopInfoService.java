package com.micro.reima.service;

import com.micro.reima.domain.BizPointChangeLog;
import com.micro.reima.model.BizPointChangeLogVo;
import com.micro.reima.model.bo.BizUserInfoBo;
import com.micro.reima.model.bo.ChangePointBo;
import com.micro.reima.model.bo.ProductBo;
import com.micro.reima.model.bo.ProductCategoryBo;
import com.micro.reima.model.bo.RefundOrderBo;
import com.micro.reima.model.bo.TradeOrderBo;
import com.micro.reima.model.vo.ShuYunMemInfoVo;
import java.util.List;
import com.micro.reima.domain.BizShopInfo;

/**
 * 店铺Service接口
 * 
 * @author micro
 * @date 2022-01-08
 */
public interface IBizShopInfoService 
{
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
     * 批量删除店铺
     * 
     * @param ids 需要删除的店铺主键集合
     * @return 结果
     */
    public int deleteBizShopInfoByIds(Long[] ids);

    /**
     * 删除店铺信息
     * 
     * @param id 店铺主键
     * @return 结果
     */
    public int deleteBizShopInfoById(Long id);

    public Boolean registerMemToShuYun(BizUserInfoBo bizUserInfoBo);

    public Boolean updateMemToShuYun(BizUserInfoBo bizUserInfoBo);

    public ShuYunMemInfoVo getMemForShuYun(String phonenumber);

    public Boolean changePointToShuYun(ChangePointBo changePointBo);

    public Boolean syncShop(BizShopInfo bizShopInfo);

    public Boolean syncProductCategory(ProductCategoryBo productCategoryBo);

    public Boolean syncProduct(ProductBo productBo);

    public Boolean syncTradeOrder(TradeOrderBo tradeOrderBo);

    public Boolean syncHistoryTradeOrder(TradeOrderBo tradeOrderBo);

    public Boolean syncRefundOrder(RefundOrderBo refundOrderBo);

    public List<BizPointChangeLogVo> changeLog(String mobile);
}
