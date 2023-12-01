package com.micro.reima.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.reima.config.ProConfig;
import com.micro.reima.domain.BizShopInfo;
import com.micro.reima.domain.BizShuyunSignInfo;
import com.micro.reima.mapper.BizShopInfoMapper;
import com.micro.reima.mapper.BizShuyunSignInfoMapper;
import com.micro.reima.mapper.BizSkuMapper;
import com.micro.reima.mapper.BizSpuMapper;
import com.micro.reima.model.BizPointChangeLogVo;
import com.micro.reima.model.bo.*;
import com.micro.reima.model.vo.ShuYunMemInfoVo;
import com.micro.reima.service.IBizShopInfoService;
import com.shuyun.open.sdk.bean.HttpMethod;
import com.shuyun.open.sdk.core.GateWayClient;
import com.shuyun.open.sdk.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

import static com.shuyun.open.sdk.core.GateWayClient.getSign;

/**
 * 店铺Service业务层处理
 * 
 * @author micro
 * @date 2022-01-08
 */
@Service
@Slf4j
public class BizShopInfoServiceImpl implements IBizShopInfoService 
{
    @Autowired
    private BizShopInfoMapper bizShopInfoMapper;

    @Autowired
    private BizShuyunSignInfoMapper bizShuyunSignInfoMapper;

    @Autowired
    private ProConfig proConfig;
    @Autowired
    private BizSpuMapper bizSpuMapper;
    @Autowired
    private BizSkuMapper bizSkuMapper;

    /**
     * 查询店铺
     * 
     * @param id 店铺主键
     * @return 店铺
     */
    @Override
    public BizShopInfo selectBizShopInfoById(Long id)
    {
        return bizShopInfoMapper.selectBizShopInfoById(id);
    }

    /**
     * 查询店铺列表
     * 
     * @param bizShopInfo 店铺
     * @return 店铺
     */
    @Override
    public List<BizShopInfo> selectBizShopInfoList(BizShopInfo bizShopInfo)
    {
        return bizShopInfoMapper.selectBizShopInfoList(bizShopInfo);
    }

    /**
     * 新增店铺
     * 
     * @param bizShopInfo 店铺
     * @return 结果
     */
    @Override
    public int insertBizShopInfo(BizShopInfo bizShopInfo)
    {
        bizShopInfo.setCreateTime(DateUtils.getNowDate());
        return bizShopInfoMapper.insertBizShopInfo(bizShopInfo);
    }

    /**
     * 修改店铺
     * 
     * @param bizShopInfo 店铺
     * @return 结果
     */
    @Override
    public int updateBizShopInfo(BizShopInfo bizShopInfo)
    {
        bizShopInfo.setUpdateTime(DateUtils.getNowDate());
        return bizShopInfoMapper.updateBizShopInfo(bizShopInfo);
    }

    /**
     * 批量删除店铺
     * 
     * @param ids 需要删除的店铺主键
     * @return 结果
     */
    @Override
    public int deleteBizShopInfoByIds(Long[] ids)
    {
        return bizShopInfoMapper.deleteBizShopInfoByIds(ids);
    }

    /**
     * 删除店铺信息
     * 
     * @param id 店铺主键
     * @return 结果
     */
    @Override
    public int deleteBizShopInfoById(Long id)
    {
        return bizShopInfoMapper.deleteBizShopInfoById(id);
    }


    @Override
    public Boolean syncShop(BizShopInfo bizShopInfo) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        Map<String,Object> rgtParams = new HashMap<>(10);
        rgtParams.put("tenant_name", bizShuyunSignInfo.getAppKey());
        rgtParams.put("app_id", bizShuyunSignInfo.getAppId());
        Map<String,String> rgtChdPrs = new HashMap<>(3);
        rgtChdPrs.put("shop_id", bizShopInfo.getShopId());
        rgtChdPrs.put("shop_name", bizShopInfo.getShopName());
        rgtChdPrs.put("plat_code", "OFFLINE");
        JSONArray chilAry = new JSONArray();
        chilAry.add(0,rgtChdPrs);
        rgtParams.put("shops", chilAry);
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(rgtParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String result = askGateWay(HttpMethod.POST,
                proConfig.getShuYunUrlHost(),
                null,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.base.shop.batch.register");
        log.info("店铺注册结果："+ result);
        JSONObject rgtJson = JSONObject.parseObject(result);
        if ("10000".equals(rgtJson.getString("code"))) {
            return true;
        }
        return false;
    }

    @Override
    public Boolean syncProductCategory(ProductCategoryBo productCategoryBo) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String,String> rgtParams = new HashMap<>(10);
        rgtParams.put("parent_category_id", "0");
        rgtParams.put("category_name", "线下店铺类目");
        rgtParams.put("category_id", "8888");
        rgtParams.put("created", sdf.format(new Date()));
        rgtParams.put("modified", sdf.format(new Date()));
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(rgtParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String result = askGateWay(HttpMethod.POST,
                proConfig.getShuYunUrlHost(),
                null,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.base.product.category.sync");
        log.info("同步商品类目父类结果："+ result);
        JSONObject rgtJson = JSONObject.parseObject(result);
        if ("10000".equals(rgtJson.getString("code"))) {
//            log.info("同步商品类目父类成功");
            return true;
        }
        return false;
    }

    @Override
    public Boolean syncProduct(ProductBo productBo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        Map<String, Object> rgtParams = new HashMap<>(10);

//        BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
        rgtParams.put("shop_id", productBo.getShopId());

        /*BizShopInfo bizShopInfo = new BizShopInfo();*/
//        bizShopInfo.setShopId(productBo.getShopId());
        /*List<BizShopInfo> bizShopInfos = bizShopInfoMapper.selectBizShopInfoList(bizShopInfo);
        if (bizShopInfos.isEmpty()) {
            return false;
        }*/
        rgtParams.put("shop_name", productBo.getShopId());
        rgtParams.put("product_id", productBo.getProductId());
        rgtParams.put("outer_product_id", productBo.getProductId());
        rgtParams.put("product_name", productBo.getProductName());
        rgtParams.put("price", new BigDecimal(productBo.getPrice())
                .setScale(2,BigDecimal.ROUND_DOWN));
        rgtParams.put("category_id", "8888");
        rgtParams.put("modified", sdf.format(new Date()));
        rgtParams.put("status", "SY_ONLINE");
        List<ProductChildBo> productChildBos = productBo.getProductChildBos();
        JSONArray chilAry = new JSONArray();
        for (ProductChildBo productChildBo : productChildBos) {
            Map<String, Object> rgtChdPrs = new HashMap<>(10);
            /***子项****/
            rgtChdPrs.put("sku_detail", productChildBo.getSkuDetail());
            rgtChdPrs.put("status", 1);
            rgtChdPrs.put("price", new BigDecimal(productChildBo.getSkuPrice())
                    .setScale(2,BigDecimal.ROUND_DOWN));
            rgtChdPrs.put("sku_id", productChildBo.getSkuId());
            rgtChdPrs.put("outer_sku_id", productChildBo.getSkuId());
            chilAry.add(0,rgtChdPrs);
        }
        rgtParams.put("skus", chilAry);
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(rgtParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String result = askGateWay(HttpMethod.POST,
                proConfig.getShuYunUrlHost(),
                null,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.base.product.sync");
        JSONObject rgtJson = JSONObject.parseObject(result);
        log.info("同步商品结果："+ result);
        if ("10000".equals(rgtJson.getString("code"))) {
//            log.info("同步商品类目父类成功");
            return true;
        }
        return false;
    }

    @Override
    public Boolean syncTradeOrder(TradeOrderBo tradeOrderBo) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
            //BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
            String shopId = tradeOrderBo.getShopId();
            /**同步商品**/
            List<TradeOrderChildBo> tradeOrderChildBos1 = tradeOrderBo.getTradeOrderChildBos();
            List<TradeOrderChildBo> tradeOrderChildBos2 = new ArrayList<>();
            for (TradeOrderChildBo tradeOrderChildBo : tradeOrderChildBos1) {
                TradeOrderChildBo tradeOrderChildBo1 = new TradeOrderChildBo();
                BeanUtils.copyProperties(tradeOrderChildBo, tradeOrderChildBo1);
                log.info("tradeOrderChildBo:"+JSONObject.toJSONString(tradeOrderChildBo));
                List<ProductBo> productBos = bizSpuMapper.syncProductByGoodCode(tradeOrderChildBo.getProductId().trim());
                log.info("productBos:"+JSONObject.toJSONString(productBos));
                if(CollectionUtils.isEmpty(productBos)){
                    return false;
                }
                ProductBo productBo = productBos.get(0);
                tradeOrderChildBo1.setProductName(productBo.getProductName());
                List<ProductSkuPo> params = bizSkuMapper.selectByGoodCode(productBo.getProductId());
                //log.info("params:"+JSONObject.toJSONString(params));
                if(params != null && params.size() > 0){
                    List<ProductChildBo> childBos = new ArrayList<>();
                    for (ProductSkuPo param : params) {
                        ProductChildBo productChildBo = new ProductChildBo();
                        productChildBo.setSkuId(param.getSkucode());
                        productChildBo.setSkuPrice(param.getStandard_price());
                        StringBuffer sf = new StringBuffer();
                        sf.append(param.getColor_name());
                        sf.append(":");
                        sf.append(param.getColor_code());
                        sf.append(";");
                        sf.append(param.getSize_name());
                        sf.append(":");
                        sf.append(param.getSize_code());
                        productChildBo.setSkuDetail(sf.toString());
                        childBos.add(productChildBo);
                    }
                    productBo.setProductChildBos(childBos);
                }
                productBo.setShopId(shopId);
                syncProduct(productBo);
                bizSpuMapper.updateSyncTime(productBo.getId());
                bizSkuMapper.updateSyncTime(productBo.getProductId());
            }

            Map<String, Object> shuyunOrder = new HashMap<>(10);
            shuyunOrder.put("order_id", tradeOrderBo.getOrderId());
            shuyunOrder.put("shop_id", shopId);
            try {
                String id = MD5Utils.md5Hex((tradeOrderBo.getPlatAccount() + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
                shuyunOrder.put("plat_account", id);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            shuyunOrder.put("receiver_mobile", tradeOrderBo.getReceiverMobile());
            shuyunOrder.put("created", tradeOrderBo.getCreated());
            shuyunOrder.put("pay_time", tradeOrderBo.getPayTime());
            shuyunOrder.put("endtime", tradeOrderBo.getPayTime());
            shuyunOrder.put("is_refund", "SY_REFUND_NONE");
            shuyunOrder.put("payment", new BigDecimal(tradeOrderBo.getPayment().trim())
                    .setScale(2,BigDecimal.ROUND_DOWN));

            shuyunOrder.put("order_status", "TRADE_FINISHED");
            shuyunOrder.put("product_num",tradeOrderBo.getProductNum());
            shuyunOrder.put("post_fee", 0);
            shuyunOrder.put("adjust_fee", new BigDecimal(tradeOrderBo.getAdjustFee().trim())
                    .setScale(2,BigDecimal.ROUND_DOWN));
            shuyunOrder.put("modified", sdf.format(new Date()));
            shuyunOrder.put("is_presale", 0);
            shuyunOrder.put("delivery_type", "SY_EXPRESS");
            shuyunOrder.put("trade_source", "1");
            shuyunOrder.put("trade_type", "FIXED");
            shuyunOrder.put("is_refund", "SY_REFUND_NONE");

            List<TradeOrderChildBo> tradeOrderChildBos = tradeOrderBo.getTradeOrderChildBos();
            /**组装子订单*/
            List<Map<String, Object>> childOrders = new ArrayList<>();
            for (TradeOrderChildBo tradeOrderChildBo : tradeOrderChildBos) {
                Map<String, Object> orderChild = new HashMap<>(10);
                orderChild.put("order_item_id", tradeOrderChildBo.getOrderItemId().trim());
                orderChild.put("product_name", tradeOrderChildBo.getProductName());
                orderChild.put("product_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("outer_product_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("sku_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("outer_sku_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("product_num", tradeOrderChildBo.getProductNum());
                orderChild.put("price", new BigDecimal(tradeOrderChildBo.getPrice().trim())
                        .setScale(2,BigDecimal.ROUND_DOWN));
                orderChild.put("discount_fee", new BigDecimal(tradeOrderChildBo.getDiscountFee().trim())
                        .setScale(2,BigDecimal.ROUND_DOWN));
                orderChild.put("adjust_fee", 0);
                orderChild.put("is_refund", 0);
                childOrders.add(orderChild);
            }
            shuyunOrder.put("orders", childOrders);

            String requestBody = null;
            try {
                requestBody = new ObjectMapper().writeValueAsString(shuyunOrder);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String result = askGateWay(HttpMethod.POST,
                    proConfig.getShuYunUrlHost(),
                    null,
                    requestBody,
                    bizShuyunSignInfo.getAppId(),
                    bizShuyunSignInfo.getSecret(),
                    bizShuyunSignInfo.getToken(),
                    "shuyun.base.trade.sync");
            log.info("syncTradeOrder："+ result);
            JSONObject rgtJson = JSONObject.parseObject(result);
            log.info("syncTradeOrder:{}",rgtJson);
            if ("10000".equals(rgtJson.getString("code"))) {
                log.info("syncTradeOrder 成功");
                return true;
            }
        } catch (Exception e) {
            log.info("", e);
        }
        return false;
    }

    @Override
    public Boolean syncRefundOrder(RefundOrderBo refundOrderBo) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
        Map<String, Object> shuyunOrder = new HashMap<>(10);
        shuyunOrder.put("order_id", refundOrderBo.getOrderId());
        shuyunOrder.put("refund_reason", "线下退款");
        shuyunOrder.put("order_item_id", refundOrderBo.getOrderItemId());
        shuyunOrder.put("product_id", refundOrderBo.getProductId().trim());
        shuyunOrder.put("shop_id", refundOrderBo.getShopId());
        shuyunOrder.put("refund_fee", new BigDecimal(refundOrderBo.getRefundFee().trim())
                .setScale(2,BigDecimal.ROUND_DOWN));
        shuyunOrder.put("sku_id", refundOrderBo.getSkuId().trim());
        shuyunOrder.put("refund_status", "SY_REFUND_SUCC");
        shuyunOrder.put("good_return", "SY_RETURN_FEE_GOOD");
        shuyunOrder.put("refund_id", refundOrderBo.getOrderItemId());
        shuyunOrder.put("modified", sdf.format(new Date()));
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(shuyunOrder);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String result = askGateWay(HttpMethod.POST,
                proConfig.getShuYunUrlHost(),
                null,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.base.refund.sync");
        JSONObject rgtJson = JSONObject.parseObject(result);
        log.info("同步商品类目父类结果："+ result);
        if ("10000".equals(rgtJson.getString("code"))) {
//            log.info("同步商品类目父类成功");
            // 更新主订单下的所有子订单
            return true;
        }
        return false;
    }

    @Override
    public Boolean registerMemToShuYun(BizUserInfoBo bizUserInfoBo) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
        Map<String, String> rgtParams = new HashMap<>(10);
        rgtParams.put("platCode", bizShopInfo.getPlatCode());
        rgtParams.put("shopId", bizShopInfo.getShopId());
        if (bizUserInfoBo.getSex() != null && "0".equals(bizUserInfoBo.getSex())) {
            rgtParams.put("gender", "M");
        } else if (bizUserInfoBo.getSex() != null && "1".equals(bizUserInfoBo.getSex())) {
            rgtParams.put("gender","F");
        }
        try {
            String id = MD5Utils.md5Hex((bizUserInfoBo.getPhonenumber() + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
            rgtParams.put("id", id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(bizUserInfoBo.getUsername())){
            rgtParams.put("name", bizUserInfoBo.getUsername());
        }

        rgtParams.put("mobile", bizUserInfoBo.getPhonenumber());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if (bizUserInfoBo.getBirthday() != null&& StringUtils.isNotEmpty(bizUserInfoBo.getBirthday())) {
            rgtParams.put("birthday", bizUserInfoBo.getBirthday());
        }
        if (bizUserInfoBo.getCreateTime() != null) {
            rgtParams.put("created",bizUserInfoBo.getCreateTime());
        } else {
            rgtParams.put("created",sdf.format(new Date()));
        }
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(rgtParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String result = GateWayClient.askGateWay(HttpMethod.POST,
                proConfig.getShuYunUrlHost(),
                null,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.loyalty.member.register");
        JSONObject rgtJson = JSONObject.parseObject(result);
        log.info("registerMemToShuYun:{}",result);
        if ("10000".equals(rgtJson.getString("code"))) {
            JSONObject data = rgtJson.getJSONObject("data");
            return true;
        } else {
            JSONObject message = rgtJson.getJSONObject("message");
            if (("手机号[" + bizUserInfoBo.getPhonenumber() + "]已被注册").equals(message.getString("message"))
                    || "该会员已经注册".equals(message.getString("message"))) {
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public Boolean updateMemToShuYun(BizUserInfoBo bizUserInfoBo) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
        Map<String, String> rgtParams = new HashMap<>(10);
        rgtParams.put("platCode", bizShopInfo.getPlatCode());
        rgtParams.put("shopId", bizShopInfo.getShopId());
        if (bizUserInfoBo.getSex() != null && "0".equals(bizUserInfoBo.getSex().trim())) {
            rgtParams.put("gender", "F");
        } else if (bizUserInfoBo.getSex() != null && "1".equals(bizUserInfoBo.getSex().trim())) {
            rgtParams.put("gender","M");
        } else if (bizUserInfoBo.getSex() != null ) {
            rgtParams.put("gender", bizUserInfoBo.getSex());
        } else {
            rgtParams.put("gender", "F");
        }
        try {
            String id = MD5Utils.md5Hex((bizUserInfoBo.getPhonenumber() + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
            rgtParams.put("id", id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        rgtParams.put("name", bizUserInfoBo.getUsername());
        rgtParams.put("mobile", bizUserInfoBo.getPhonenumber());
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(rgtParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String result = GateWayClient.askGateWay(HttpMethod.PUT,
                proConfig.getShuYunUrlHost(),
                rgtParams,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.loyalty.member.modify");
        JSONObject rgtJson = JSONObject.parseObject(result);
        log.info("updateMemToShuYun:{}",result);
        if ("10000".equals(rgtJson.getString("code"))) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Boolean syncHistoryTradeOrder(TradeOrderBo tradeOrderBo) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
            //BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
            String shopId = tradeOrderBo.getShopId();
            /**同步商品**/
            List<TradeOrderChildBo> tradeOrderChildBos1 = tradeOrderBo.getTradeOrderChildBos();

            Map<String, Object> shuyunOrder = new HashMap<>(10);
            shuyunOrder.put("order_id", tradeOrderBo.getOrderId());
            shuyunOrder.put("shop_id", shopId);
            try {
                String id = MD5Utils.md5Hex((tradeOrderBo.getPlatAccount() + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
                shuyunOrder.put("plat_account", id);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            shuyunOrder.put("receiver_mobile", tradeOrderBo.getReceiverMobile());
            shuyunOrder.put("created", tradeOrderBo.getCreated());
            shuyunOrder.put("pay_time", tradeOrderBo.getPayTime());
            shuyunOrder.put("endtime", tradeOrderBo.getPayTime());
            shuyunOrder.put("is_refund", "SY_REFUND_NONE");
            shuyunOrder.put("payment", new BigDecimal(tradeOrderBo.getPayment().trim())
                    .setScale(2,BigDecimal.ROUND_DOWN));

            shuyunOrder.put("order_status", "TRADE_FINISHED");
            shuyunOrder.put("product_num",tradeOrderBo.getProductNum());
            shuyunOrder.put("post_fee", 0);
            shuyunOrder.put("adjust_fee", new BigDecimal(tradeOrderBo.getAdjustFee().trim())
                    .setScale(2,BigDecimal.ROUND_DOWN));
            shuyunOrder.put("modified", sdf.format(new Date()));
            shuyunOrder.put("is_presale", 0);
            shuyunOrder.put("delivery_type", "SY_EXPRESS");
            shuyunOrder.put("trade_source", "1");
            shuyunOrder.put("trade_type", "FIXED");
            shuyunOrder.put("is_refund", "SY_REFUND_NONE");

            List<TradeOrderChildBo> tradeOrderChildBos = tradeOrderBo.getTradeOrderChildBos();
            /**组装子订单*/
            List<Map<String, Object>> childOrders = new ArrayList<>();
            for (TradeOrderChildBo tradeOrderChildBo : tradeOrderChildBos) {
                Map<String, Object> orderChild = new HashMap<>(10);
                orderChild.put("order_item_id", tradeOrderChildBo.getOrderItemId().trim());
                orderChild.put("product_name", tradeOrderChildBo.getProductName());
                orderChild.put("product_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("outer_product_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("sku_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("outer_sku_id", tradeOrderChildBo.getProductId().trim());
                orderChild.put("product_num", tradeOrderChildBo.getProductNum());
                orderChild.put("price", new BigDecimal(tradeOrderChildBo.getPrice().trim())
                        .setScale(2,BigDecimal.ROUND_DOWN));
                orderChild.put("discount_fee", new BigDecimal(tradeOrderChildBo.getDiscountFee().trim())
                        .setScale(2,BigDecimal.ROUND_DOWN));
                orderChild.put("adjust_fee", 0);
                orderChild.put("is_refund", 0);
                childOrders.add(orderChild);
            }
            shuyunOrder.put("orders", childOrders);

            String requestBody = null;
            try {
                requestBody = new ObjectMapper().writeValueAsString(shuyunOrder);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            String result = askGateWay(HttpMethod.POST,
                    proConfig.getShuYunUrlHost(),
                    null,
                    requestBody,
                    bizShuyunSignInfo.getAppId(),
                    bizShuyunSignInfo.getSecret(),
                    bizShuyunSignInfo.getToken(),
                    "shuyun.base.trade.sync");
            log.info("syncHistoryTradeOrder："+ result);
            JSONObject rgtJson = JSONObject.parseObject(result);
            log.info("syncHistoryTradeOrder:{}",rgtJson);
            if ("10000".equals(rgtJson.getString("code"))) {
                log.info("syncHistoryTradeOrder 成功");
                return true;
            }
        } catch (Exception e) {
            log.info("", e);
        }
        return false;
    }

    @Override
    public Boolean changePointToShuYun(ChangePointBo changePointBo) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        /*Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.HOUR_OF_DAY, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND, 59);
        c.add(Calendar.DAY_OF_YEAR, 180);
        String expiredTime = sdf.format(c.getTime());*/
        Map<String, String> coverGradeParams = new HashMap<>(10);
        coverGradeParams.put("platCode", bizShopInfo.getPlatCode());
        coverGradeParams.put("created", sdf.format(new Date()));
        try {
            String id = MD5Utils.md5Hex((changePointBo.getPhonenumber() + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
            coverGradeParams.put("id", id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        coverGradeParams.put("shopId", bizShopInfo.getShopId());
        coverGradeParams.put("sequence", System.currentTimeMillis() + "");
//        coverGradeParams.put("expired", expiredTime);
        coverGradeParams.put("changePoint", changePointBo.getChangePoint().toString());
        coverGradeParams.put("source", changePointBo.getSource());
        coverGradeParams.put("desc", changePointBo.getMemo());
        String requestBody = null;
        try {
            requestBody = new ObjectMapper().writeValueAsString(coverGradeParams);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String changePointRst =  GateWayClient.askGateWay(HttpMethod.POST,
                proConfig.getShuYunUrlHost(),
                null,
                requestBody,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.loyalty.member.point.change");
        JSONObject rgtJson = JSONObject.parseObject(changePointRst);
        log.info("changePointToShuYun:{}",rgtJson);
        if ("10000".equals(rgtJson.getString("code"))) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ShuYunMemInfoVo getMemForShuYun(String phonenumber) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        BizShopInfo bizShopInfo = bizShopInfoMapper.selectBizShopInfoById(1l);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, String> queryShuYunMemParams = new HashMap<>(10);
        queryShuYunMemParams.put("platCode", bizShopInfo.getPlatCode());
        queryShuYunMemParams.put("shopId", bizShopInfo.getShopId());
        try {
            String id = MD5Utils.md5Hex((phonenumber + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
            queryShuYunMemParams.put("id", id);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        String queryMemInfoRst = GateWayClient.askGateWay(HttpMethod.GET,
                proConfig.getShuYunUrlHost(),
                queryShuYunMemParams,
                "",
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.loyalty.member.get");
        JSONObject queryMemInfo = JSONObject.parseObject(queryMemInfoRst);
        log.info("queryMemInfo:{}",queryMemInfo);
        if ("10000".equals(queryMemInfo.getString("code"))) {
            JSONObject memInfoJSONObject = queryMemInfo.getJSONObject("data");
            ShuYunMemInfoVo shuYunMemInfoVo = new ShuYunMemInfoVo();
            shuYunMemInfoVo.setBirthday(memInfoJSONObject.getString("birthday"));
            shuYunMemInfoVo.setGender(memInfoJSONObject.getString("gender"));
            shuYunMemInfoVo.setCreated(memInfoJSONObject.getString("created"));
            shuYunMemInfoVo.setMobile(memInfoJSONObject.getString("mobile"));
            shuYunMemInfoVo.setGuideId(memInfoJSONObject.getString("guideId"));
            shuYunMemInfoVo.setGradeExpired(memInfoJSONObject.getString("gradeExpired"));
            shuYunMemInfoVo.setGainedPoint(memInfoJSONObject.getInteger("gainedPoint"));
            shuYunMemInfoVo.setPoint(memInfoJSONObject.getInteger("point"));
            shuYunMemInfoVo.setPlatCode(memInfoJSONObject.getString("platCode"));
            shuYunMemInfoVo.setExpiredPoint(memInfoJSONObject.getInteger("expiredPoint"));
            shuYunMemInfoVo.setGradeModified(memInfoJSONObject.getString("gradeModified"));
            shuYunMemInfoVo.setGrade(memInfoJSONObject.getInteger("grade"));
            shuYunMemInfoVo.setName(memInfoJSONObject.getString("name"));
            shuYunMemInfoVo.setConsumedPoint(memInfoJSONObject.getInteger("consumedPoint"));
            shuYunMemInfoVo.setId(memInfoJSONObject.getString("id"));
            shuYunMemInfoVo.setShopId(memInfoJSONObject.getString("shopId"));
            shuYunMemInfoVo.setMemberId(memInfoJSONObject.getInteger("memberId"));
            return shuYunMemInfoVo;
        } else {
            return null;
        }
    }

    public static String askGateWayLocal(HttpMethod httpMethod, String url, Map<String, String> queryParams, String bodyParams, String appId, String secret, String token, String actionMethod) {
        String currentTime = String.valueOf(System.currentTimeMillis());
        String sign = getSign(queryParams, currentTime, secret);
        Map<String, String> header = new HashMap();
        header.put("Gateway-Authid", appId);
        header.put("Gateway-Request-Time", currentTime);
        header.put("Gateway-Sign", sign);
        header.put("Gateway-Action-Method", actionMethod);
        if (!org.springframework.util.StringUtils.isEmpty(token)) {
            header.put("Gateway-Access-Token", token);
        }
        header.put("partner", "bpps");
        String result = HttpClientUtil.doRequest(httpMethod, url, queryParams, bodyParams, header);
        return result;
    }

    public static String askGateWay(HttpMethod httpMethod, String url, Map<String, String> queryParams, String bodyParams, String appId, String secret, String token, String actionMethod) {
        String currentTime = String.valueOf(System.currentTimeMillis());
        String sign = getSign(queryParams, currentTime, secret);
        Map<String, String> header = new HashMap();
        header.put("Gateway-Authid", appId);
        header.put("Gateway-Request-Time", currentTime);
        header.put("Gateway-Sign", sign);
        header.put("platform", "offline");
        header.put("Gateway-Action-Method", actionMethod);
        if (!org.springframework.util.StringUtils.isEmpty(token)) {
            header.put("Gateway-Access-Token", token);
        }
        String result = HttpClientUtil.doRequest(httpMethod, url, queryParams, bodyParams, header);
        return result;
    }


    @Override
    public List<BizPointChangeLogVo> changeLog(String mobile) {
        BizShuyunSignInfo bizShuyunSignInfo = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
        Map<String, String> queryShuYunMemParams = new HashMap<>(10);
        queryShuYunMemParams.put("platCode", "OFFLINE");
        queryShuYunMemParams.put("shopId", "8888");
        String id = "";
        try {
            id = MD5Utils.md5Hex((mobile + "Reima").getBytes(StandardCharsets.UTF_8)).substring(8,24);
        }catch (Exception e){
            e.printStackTrace();
        }
        queryShuYunMemParams.put("id",id);// 平台客户ID
        queryShuYunMemParams.put("startCreated","2022-01-01 00:00:00 ");
        queryShuYunMemParams.put("endCreated","2022-04-27 23:59:59 ");
        queryShuYunMemParams.put("pageSize","500");
        queryShuYunMemParams.put("currentPage","1");

        String result = askGateWayLocal2(HttpMethod.GET,
                proConfig.getShuYunUrlHost(),
                queryShuYunMemParams,
                null,
                bizShuyunSignInfo.getAppId(),
                bizShuyunSignInfo.getSecret(),
                bizShuyunSignInfo.getToken(),
                "shuyun.loyalty.member.point.changelog.search");
        log.info("线下会员积分变更记录："+ result);
        JSONObject rgtJson = JSONObject.parseObject(result);
        if ("10000".equals(rgtJson.getString("code"))) {
            // 保存记录
            return JSON.parseArray(rgtJson.getJSONObject("data").getString("list"),BizPointChangeLogVo.class);
        }
        return null;
    }

    public static String askGateWayLocal2(HttpMethod httpMethod, String url, Map<String, String> queryParams, String bodyParams, String appId, String secret, String token, String actionMethod) {
        String currentTime = String.valueOf(System.currentTimeMillis());
        String sign = getSign(queryParams, currentTime, secret);
        Map<String, String> header = new HashMap();
        header.put("Gateway-Authid", appId);
        header.put("Gateway-Request-Time", currentTime);
        header.put("Gateway-Sign", sign);
        header.put("Gateway-Action-Method", actionMethod);
        if (!org.springframework.util.StringUtils.isEmpty(token)) {
            header.put("Gateway-Access-Token", token);
        }
        header.put("partner", "reima");
        String result = HttpClientUtil.doRequest(httpMethod, url, queryParams, bodyParams, header);
        return result;
    }
}
