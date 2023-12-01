package com.micro.reima.service.impl;

import com.alibaba.fastjson.JSON;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.ip.OkHttpUtil;
import com.micro.reima.config.ProConfig;
import com.micro.reima.domain.BizPointChangeLog;
import com.micro.reima.domain.BizShopInfo;
import com.micro.reima.domain.BizSku;
import com.micro.reima.domain.BizSpu;
import com.micro.reima.mapper.*;
import com.micro.reima.model.BizPointChangeLogVo;
import com.micro.reima.model.bo.BizUserInfoBo;
import com.micro.reima.model.bo.RefundOrderBo;
import com.micro.reima.model.bo.TradeOrderBo;
import com.micro.reima.model.pos.ProductEntity;
import com.micro.reima.model.pos.ResultEntity;
import com.micro.reima.model.pos.ShopEntity;
import com.micro.reima.model.pos.SkuEntity;
import com.micro.reima.model.vo.ShuYunMemInfoVo;
import com.micro.reima.model.vo.SimpleUserInfo;
import com.micro.reima.service.IBizShopInfoService;
import com.micro.reima.service.IDataSyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class IDataSyncServiceImpl implements IDataSyncService {
  @Autowired
  private ProConfig proConfig;
  @Autowired
  private IBizShopInfoService bizShopInfoService;
  @Autowired
  private BizPointChangeLogMapper bizPointChangeLogMapper;
  @Autowired
  private BizUserInfoMapper bizUserInfoMapper;
  @Autowired
  private BizUserAccRecordMapper bizUserAccRecordMapper;
  @Autowired
  private BizShopInfoMapper bizShopInfoMapper;
  @Autowired
  private BizSpuMapper bizSpuMapper;
  @Autowired
  private BizSkuMapper bizSkuMapper;
  @Autowired
  private BizOrderRecordMapper orderRecordMapper;
  @Autowired
  private BizOrderDetailMapper orderDetailMapper;
  @Autowired
  private BizOldUserMapper bizOldUserMapper;
  @Autowired
  private BizOldOrderMapper bizOldOrderMapper;

  @Override
  @Scheduled(cron = "0 0/1 * * * ? ")
  public void syncMemberInfo() {
    List<BizUserInfoBo> bos = bizUserInfoMapper.syncUser();
    if(CollectionUtils.isEmpty(bos)){
      return;
    }
    for (BizUserInfoBo bizUserInfoBo:bos){
      ShuYunMemInfoVo vo = bizShopInfoService.getMemForShuYun(bizUserInfoBo.getPhonenumber());
      if(null==vo){
        bizShopInfoService.registerMemToShuYun(bizUserInfoBo);
        vo = bizShopInfoService.getMemForShuYun(bizUserInfoBo.getPhonenumber());
      }else{
        bizShopInfoService.updateMemToShuYun(bizUserInfoBo);
      }
      if(null!=vo&&null!=vo.getMemberId()){
        bizUserInfoMapper.updateMemberIdByUserId(bizUserInfoBo.getId(),vo.getMemberId()+"");
      }
      bizUserInfoMapper.updateUserSyncTime(bizUserInfoBo.getId());
    }
  }

  @Override
  @Scheduled(cron = "0 0/5 * * * ? ")
  public void syncMemberId() {
    List<SimpleUserInfo> simpleUserInfos = bizUserInfoMapper.syncUserWithoutMemberId();
    if(CollectionUtils.isEmpty(simpleUserInfos)){
      return;
    }
    for (SimpleUserInfo simpleUserInfo:simpleUserInfos){
      ShuYunMemInfoVo vo = bizShopInfoService.getMemForShuYun(simpleUserInfo.getPhonenumber());
      if(null != vo && null != vo.getMemberId()){
        bizUserInfoMapper.updateMemberIdByUserId(simpleUserInfo.getUserId(),vo.getMemberId()+"");
      }
    }
  }

  @Override
  @Scheduled(cron = "0 0/5 * * * ? ")
  public void syncOldMemberInfo() {
    List<BizUserInfoBo> bos = bizOldUserMapper.syncOldUser();
    if(CollectionUtils.isEmpty(bos)){
      return;
    }
    for (BizUserInfoBo bizUserInfoBo:bos){
      ShuYunMemInfoVo vo = bizShopInfoService.getMemForShuYun(bizUserInfoBo.getPhonenumber());
      if(null==vo){
        bizShopInfoService.registerMemToShuYun(bizUserInfoBo);
      }else{
        bizShopInfoService.updateMemToShuYun(bizUserInfoBo);
      }
      /*if(null!=bizUserInfoBo.getPoints()&&bizUserInfoBo.getPoints()>0){
        // 同步积分
        ChangePointBo pointBo = new ChangePointBo();
        pointBo.setChangePoint(bizUserInfoBo.getPoints());
        pointBo.setPhonenumber(bizUserInfoBo.getPhonenumber());
        pointBo.setSource("OTHER");// 业务来源 CONSUME:消费（负积分） OTHER:其他（可正可负）INIT_POINT:初始化积分(正积分)
        pointBo.setMemo("初始化积分");
        if(!bizShopInfoService.changePointToShuYun(pointBo)){
          continue;
        }
      }*/
      bizOldUserMapper.updateSyncTime(bizUserInfoBo.getId());
    }
  }

  /*@Override
  //@Scheduled(cron = "0 0/1 * * * ? ")
  public void syncMemberPoints() {
    List<ChangePointBo> bos = bizUserAccRecordMapper.syscChangePoint();
    if(CollectionUtils.isEmpty(bos)){
      return;
    }
    for (ChangePointBo bo:bos){
      ShuYunMemInfoVo vo = bizShopInfoService.getMemForShuYun(bo.getPhonenumber());
      if(null==vo){
        continue;
      }
      bizShopInfoService.changePointToShuYun(bo);
      bizUserAccRecordMapper.updateSyncTime(bo.getId());
    }
  }*/

  @Override
  @Scheduled(cron = "0 0/3 * * * ? ")
  public void syncShop() {
    List<BizShopInfo> infos = bizShopInfoMapper.syncBizShopInfo();
    if(CollectionUtils.isEmpty(infos)){
      return;
    }
    for (BizShopInfo info:infos){
      bizShopInfoService.syncShop(info);
      bizShopInfoMapper.updateSyncTime(info.getId());
    }

  }

  /*

   */
  @Override
  @Scheduled(cron = "0 0/4 * * * ? ")
  public void syncProduct() {
    /*List<ProductBo> productBos = bizSpuMapper.syncProductBos();
    if(CollectionUtils.isEmpty(productBos)){
      return;
    }
    for (ProductBo productBo:productBos){
      List<SkuDetail> params = bizSkuMapper.selectByGoodCode(productBo.getProductId());
      if(!CollectionUtils.isEmpty(params)){
        productBo.setSkuDetail(JSON.toJSONString(params));
      }
      bizShopInfoService.syncProduct(productBo);
      bizSpuMapper.updateSyncTime(productBo.getId());
      bizSkuMapper.updateSyncTime(productBo.getProductId());
    }*/
  }

//  @Override
////  @Scheduled(cron = "0 0/1 * * * ? ")
//  public void syncCategroy() {
//    bizShopInfoService.syncProductCategory(null);
//  }


  @Override
  @Scheduled(cron = "0 0/1 * * * ? ")
  public void syncOldOrder() {
    List<TradeOrderBo> tradeOrderBos = bizOldOrderMapper.syncTradeOrder();
    if(!CollectionUtils.isEmpty(tradeOrderBos)){
      for (TradeOrderBo tradeOrderBo:tradeOrderBos){
        tradeOrderBo.setTradeOrderChildBos(bizOldOrderMapper.syncTradeOrderChild(tradeOrderBo.getOrderId()));
        boolean syncRst = bizShopInfoService.syncHistoryTradeOrder(tradeOrderBo);
        log.info("syncOldOrder:{}",syncRst);
        if (syncRst) {
          bizOldOrderMapper.updateSyncTime(tradeOrderBo.getOrderId());
        }
      }
    }
    List<RefundOrderBo> refundOrderBos = bizOldOrderMapper.syncRefundOrder();
    if(!CollectionUtils.isEmpty(refundOrderBos)){
      for (RefundOrderBo refundOrderBo:refundOrderBos){
        boolean syncRst = bizShopInfoService.syncRefundOrder(refundOrderBo);
        if (syncRst) {
          bizOldOrderMapper.updateSyncTime(refundOrderBo.getOrderId());
        }
      }
    }
  }

  @Override
  @Scheduled(cron = "0/30 * * * * ? ")
  public void syncOrder() {
    List<TradeOrderBo> tradeOrderBos = orderRecordMapper.syncTradeOrder();
    if(!CollectionUtils.isEmpty(tradeOrderBos)){
      for (TradeOrderBo tradeOrderBo:tradeOrderBos){
        tradeOrderBo.setTradeOrderChildBos(orderDetailMapper.syncTradeOrderChild(tradeOrderBo.getId()));
        boolean syncRst = bizShopInfoService.syncTradeOrder(tradeOrderBo);
        if (syncRst) {
          orderRecordMapper.updateSyncTime(tradeOrderBo.getId());
        }
      }
    }
    List<RefundOrderBo> refundOrderBos = orderRecordMapper.syncRefundOrder();
    if(!CollectionUtils.isEmpty(refundOrderBos)){
      for (RefundOrderBo refundOrderBo:refundOrderBos){
        boolean syncRst = bizShopInfoService.syncRefundOrder(refundOrderBo);
        if (syncRst) {
          orderRecordMapper.updateSyncTime(refundOrderBo.getId());
        }
      }
    }

  }

  // pos同步至reima
  @Override
  @Scheduled(cron = "0 10 1 * * ?")
  public void syncShopFromPos() {
    Map<String,Object> params = new HashMap<>();
    int page = 0,limit = 100,count = 0;
    do{
      params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.getPreDay()));// DateUtils.getSecondTimestampTwo(DateUtils.getPreDay())
      params.put("page",page);
      params.put("limit",limit);
      String result = OkHttpUtil.iposApi(proConfig.getIposUrl(),"get_shop_list",proConfig.getIposKey(),proConfig.getIposSecret(),params);
      log.info(">>>syncShopFromPos:{}",result);
      ResultEntity entity = JSON.parseObject(result,ResultEntity.class);
      page = page+1;
      if(null!=entity&&1==entity.getStatus()){
        count = entity.getSearch().getCounts();
        List<ShopEntity> shopEntityList = JSON.parseArray(JSON.toJSONString(entity.getData().getList()),ShopEntity.class);
        for (ShopEntity shop:shopEntityList){
          BizShopInfo bizShopInfo = buildFromShopEntity(shop);
          Long id = bizShopInfoMapper.selectBizShopInfoByShopId(bizShopInfo.getShopId());
          if(null!=id){
            bizShopInfo.setId(id);
            bizShopInfoMapper.updateBizShopInfo(bizShopInfo);
          }else{
            bizShopInfoMapper.insertBizShopInfo(bizShopInfo);
          }
        }
      }
      log.info("syncShopFromPos-page:{}-limit:{}-count:{}",page,limit,count);
    }while (page < ((count  +  limit  - 1) / limit));
  }

  private BizShopInfo buildFromShopEntity(ShopEntity shopEntity){
    BizShopInfo bizShopInfo = new BizShopInfo();
    bizShopInfo.setShopId(shopEntity.getShop_code());
    bizShopInfo.setPlatCode(shopEntity.getPostcode());
    bizShopInfo.setShopName(shopEntity.getShop_name());
    bizShopInfo.setAddress(shopEntity.getAddress());
    bizShopInfo.setTelephone(shopEntity.getHandset());
    bizShopInfo.setProvince(shopEntity.getProvince());
    bizShopInfo.setCity(shopEntity.getCity());
    bizShopInfo.setCounty(shopEntity.getCounty());
    bizShopInfo.setPhone(shopEntity.getPhone());
    bizShopInfo.setOrgCode(shopEntity.getOrg_code());
    bizShopInfo.setOrgName(shopEntity.getOrg_name());
    bizShopInfo.setDelFlag("0");
    bizShopInfo.setCreateBy("sync");
    bizShopInfo.setUpdateBy("sync");
    bizShopInfo.setCreateTime(DateUtils.getNowDate());
    bizShopInfo.setUpdateTime(DateUtils.getNowDate());
    return bizShopInfo;
  }

  @Override
  @Scheduled(cron = "0 10 1 * * ?")
  public void syncProductFromPos() {
    Map<String,Object> params = new HashMap<>();
    int page = 0,limit = 100,count = 0;
    do{
      params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.getPreDay()));
      params.put("page",page);
      params.put("limit",limit);
      String result = OkHttpUtil.iposApi(proConfig.getIposUrl(),"get_good_list",proConfig.getIposKey(),proConfig.getIposSecret(),params);
      //log.info(">>>syncProductFromPos:{}",result);
      ResultEntity entity = JSON.parseObject(result,ResultEntity.class);
      page = page+1;
      if(null!=entity&&1==entity.getStatus()){
        count = entity.getSearch().getCounts();
        List<ProductEntity> shopEntityList = JSON.parseArray(JSON.toJSONString(entity.getData().getList()),ProductEntity.class);
        for (ProductEntity productEntity:shopEntityList){
          BizSpu bizSpu = buildBizSpu(productEntity);
          Long id = bizSpuMapper.selectByGoodCode(bizSpu.getGoodCode());
          if(null!=id){
            bizSpu.setId(id);
            bizSpuMapper.updateBizSpu(bizSpu);
          }else{
            bizSpuMapper.insertBizSpu(bizSpu);
          }
        }
      }
      log.info("syncProductFromPos-page:{}-limit:{}-count:{}",page,limit,count);
    }while (page < ((count  +  limit  - 1) / limit));
  }

  private BizSpu buildBizSpu(ProductEntity productEntity){
    BizSpu spu = new BizSpu();
    spu.setGoodCode(productEntity.getGood_code());
    spu.setGoodName(productEntity.getGood_name());
    spu.setGoodShortName(productEntity.getGood_short_name());
    spu.setGoodAliasesName(productEntity.getGood_aliases_name());
    spu.setStatus(productEntity.getStatus());
    spu.setStandardPrice(productEntity.getStandard_price());
    spu.setUnitCode(productEntity.getUnit_code());
    spu.setUnitName(productEntity.getUnit_name());
    spu.setBrandCode(productEntity.getBrand_code());
    spu.setCategoryCode(productEntity.getCategory_code());
    spu.setSeaconCode(productEntity.getSeacon_code());
    spu.setYearCode(productEntity.getYear_code());
    spu.setBrandCode(productEntity.getBrand_code());
    spu.setSeriesCode(productEntity.getSeries_code());
    spu.setDelFlag("0");
    spu.setCreateBy("sync");
    spu.setUpdateBy("sync");
    spu.setCreateTime(DateUtils.getNowDate());
    spu.setUpdateTime(DateUtils.getNowDate());
    return spu;
  }

  @Override
  @Scheduled(cron = "0 10 1 * * ?")
  public void syncSkuFromPos() {
    Map<String,Object> params = new HashMap<>();
    int page = 0,limit = 100,count = 0;
    do{
      // DateUtils.getSecondTimestampTwo(DateUtils.parseDate("2019-01-01"))
      // DateUtils.getSecondTimestampTwo(DateUtils.getPreDay())
      params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.parseDate("2019-08-27")));
      params.put("page",page);
      params.put("limit",limit);
      String result = OkHttpUtil.iposApi(proConfig.getIposUrl(),"get_sku_list",proConfig.getIposKey(),proConfig.getIposSecret(),params);
      log.info(">>>syncSkuFromPos:{}",result);
      ResultEntity entity = JSON.parseObject(result,ResultEntity.class);
      page = page+1;
      if(null!=entity&&1==entity.getStatus()){
        count = entity.getSearch().getCounts();
        List<SkuEntity> skuEntityList = JSON.parseArray(JSON.toJSONString(entity.getData().getList()),SkuEntity.class);
        for (SkuEntity skuEntity:skuEntityList){
          BizSku bizSku = buildBizSku(skuEntity);
          Long id = bizSkuMapper.selectBySkuCode(bizSku.getSkucode());
          if(null!=id){
            bizSku.setId(id);
            bizSkuMapper.updateBizSku(bizSku);
          }else{
            bizSkuMapper.insertBizSku(bizSku);
          }
        }
      }
      log.info("syncSkuFromPos-page:{}-limit:{}-count:{}",page,limit,count);
    }while (page < ((count  +  limit  - 1) / limit));
  }

  private BizSku buildBizSku(SkuEntity skuEntity){
    BizSku sku = new BizSku();
    sku.setGoodCode(skuEntity.getGood_code());
    sku.setGoodName(skuEntity.getGood_name());
    sku.setColorCode(skuEntity.getColor_code());
    sku.setColorName(skuEntity.getColor_name());
    sku.setSizeCode(skuEntity.getSize_code());
    sku.setSizeName(skuEntity.getSize_name());
    sku.setSkucode(skuEntity.getSkucode());
    sku.setGoodShortName(skuEntity.getGood_short_name());
    sku.setGoodAliasesName(skuEntity.getGood_aliases_name());
    sku.setStatus(skuEntity.getStatus());
    sku.setStandardPrice(skuEntity.getStandard_price());
    sku.setUnitCode(skuEntity.getUnit_code());
    sku.setUnitName(skuEntity.getUnit_name());
    sku.setBrandCode(skuEntity.getBrand_code());
    sku.setCategoryCode(skuEntity.getCategory_code());
    sku.setCategoryName(skuEntity.getCategory_name());
    sku.setMediumCategoryCode(skuEntity.getMedium_category_code());
    sku.setMediumCategoryName(skuEntity.getMedium_category_name());
    sku.setSmallCategoryCode(skuEntity.getSmall_category_code());
    sku.setSmallCategoryName(skuEntity.getSmall_category_name());
    sku.setSeaconCode(skuEntity.getSeacon_code());
    sku.setSeaconName(skuEntity.getSeacon_name());
    sku.setYearCode(skuEntity.getYear_code());
    sku.setYearName(skuEntity.getYear_name());
    sku.setBrandCode(skuEntity.getBrand_code());
    sku.setBandName(skuEntity.getBand_name());
    sku.setSeriesCode(skuEntity.getSeries_code());
    sku.setSeriesName(skuEntity.getSeries_name());
    sku.setDelFlag("0");
    sku.setCreateBy("sync");
    sku.setUpdateBy("sync");
    sku.setCreateTime(DateUtils.getNowDate());
    sku.setUpdateTime(DateUtils.getNowDate());
    return sku;
  }

  @Override
  public Integer getPointsByMobile() {
    ShuYunMemInfoVo vo = bizShopInfoService.getMemForShuYun("19810660521");
    System.out.println(JSON.toJSONString(vo));
    if(null==vo){
      return 0;
    }
    return vo.getPoint();
  }

  @Override
  @Scheduled(cron = "0 10 3 * * ?")
  public void refreshShuyunToken() {
    String url = "http://open-client.shuyun.com/client/callback/token/7bda7ec0f8e24b5cb0ed3762d265c088/v2";
    OkHttpUtil.httpGet(url);
  }

  @Override
//  @Scheduled(cron = "0 52 21 * * ?")
  public void syncChangeLog(){
    log.error("syncChangeLog...");
    List<SimpleUserInfo> userInfos = bizUserInfoMapper.selectSimpleUserInfos();
    if(CollectionUtils.isEmpty(userInfos)){
      return;
    }
    for (SimpleUserInfo info:userInfos) {
      log.error("syncChangeLog...info:{}",info.getUserId());
      try {
        List<BizPointChangeLogVo> logs = bizShopInfoService.changeLog(info.getPhonenumber());
        if (CollectionUtils.isEmpty(logs)) {
          continue;
        }
        log.error("syncChangeLog...logs:{}",logs.size());
        for (BizPointChangeLogVo vo : logs) {
          BizPointChangeLog bizPointChangeLog = new BizPointChangeLog();
          BeanUtils.copyProperties(vo,bizPointChangeLog);
          bizPointChangeLog.setSequen(vo.getSequence());
          bizPointChangeLog.setMemo(vo.getDesc());
          bizPointChangeLog.setUserId(info.getUserId());
          bizPointChangeLog.setMobile(info.getPhonenumber());
          bizPointChangeLog.setUpdateTime(DateUtils.getNowDate());
          bizPointChangeLogMapper.insertBizPointChangeLog(bizPointChangeLog);
        }
      } catch (Exception e) {
        log.error("syncChangeLog:{}", e);
      }
    }
  }
}
