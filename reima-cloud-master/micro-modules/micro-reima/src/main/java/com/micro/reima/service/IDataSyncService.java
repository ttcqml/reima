package com.micro.reima.service;


public interface IDataSyncService {

  // reima 同步至 数云
  // 同步会员信息到数云
  void syncMemberInfo();
  void syncOldMemberInfo();
  // 同步会员积分记录到数云
//  void syncMemberPoints();
//  // 同步店铺到数云
  void syncShop();
  // 同步商品
  void syncProduct();
//  // 同步类目
//  void syncCategroy();
  // 同步订单、退单
  void syncOrder();
  // 同步老订单
  void syncOldOrder();

  // 同步memberId
  void syncMemberId();

  // pos 同步至 reima
  void syncShopFromPos();
  void syncProductFromPos();
  void syncSkuFromPos();

  Integer getPointsByMobile();

  public void syncChangeLog();

  void refreshShuyunToken();

}
