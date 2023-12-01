package com.micro.reima.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.nacos.common.utils.MD5Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.ip.OkHttpUtil;
import com.micro.reima.model.pos.ResultEntity;
import com.shuyun.open.sdk.bean.HttpMethod;
import com.shuyun.open.sdk.core.GateWayClient;
import com.shuyun.open.sdk.utils.HttpClientUtil;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static com.shuyun.open.sdk.core.GateWayClient.getSign;


public class Test {
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

    public static void main(String[] args) {
        String mobile = "13674117348";
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
        queryShuYunMemParams.put("endCreated","2022-04-26 23:59:59 ");
        queryShuYunMemParams.put("pageSize","100");
        queryShuYunMemParams.put("currentPage","1");
        String requestBody = null;

        String result = askGateWayLocal2(HttpMethod.GET,
                "http://sapi.shuyun.com/loyalty2-interface/1.0/member/point/changelog/search",
                queryShuYunMemParams,
                null,
                "7bda7ec0f8e24b5cb0ed3762d265c088",
                "30C149B6D2EABE33EBB8F544633C4B9B",
                "RkMxQjIxMUY4QjJDNDUzNzQ3M0U1RDdDQTAxRjVBMEY",
                "shuyun.loyalty.member.point.changelog.search");
        System.out.println("线下会员积分变更记录："+ result);
        JSONObject rgtJson = JSONObject.parseObject(result);
        if ("10000".equals(rgtJson.getString("code"))) {
            //return true;
        }


//        String url = "http://101.200.232.173/ipos/web/api/api.php",key = "base",secret = "123456";
//        Map<String,Object> params = new HashMap<>();
//        params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.parseDateByPattern("2021-01-01",DateUtils.YYYY_MM_DD)));
//        params.put("page",1);
//        params.put("limit",10);
//        String result = OkHttpUtil.iposApi(url,"get_shop_list",key,secret,params);
//        ResultEntity entity = JSON.parseObject(result,ResultEntity.class);
//        System.out.println("get_shop_list:\n"+JSON.toJSONString(entity));
//        System.out.println(entity.getSearch().getCounts());

//        String url = "http://101.200.232.173/ipos/web/api/api.php",key = "base",secret = "123456";
//        Map<String,Object> params = new HashMap<>();
//        params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.parseDateByPattern("2021-01-01",DateUtils.YYYY_MM_DD)));
//        params.put("page",1);
//        params.put("limit",10);
//        String result = OkHttpUtil.iposApi(url,"get_shop_list",key,secret,params);
//        ResultEntity entity = JSON.parseObject(result,ResultEntity.class);
//        System.out.println("get_shop_list:\n"+JSON.toJSONString(entity));
//        System.out.println(entity.getSearch().getCounts());

//    result = iposApi(url,"get_good_list",key,secret,params);
//    System.out.println("get_good_list:\n"+result);
//
//    result = iposApi(url,"get_sku_list",key,secret,params);
//    System.out.println("get_sku_list:\n"+result);

//        Map<String,Object> rgtParams = new HashMap<>(10);
//        rgtParams.put("tenant_name", "REIMA");
//        rgtParams.put("app_id", "7bda7ec0f8e24b5cb0ed3762d265c088");
//        Map<String,String> rgtChdPrs = new HashMap<>(3);
//        rgtChdPrs.put("shop_id", "8888");
//        rgtChdPrs.put("shop_name", "Reima小程序");
//        rgtChdPrs.put("plat_code", "OFFLINE");
//        JSONArray chilAry = new JSONArray();
//        chilAry.add(0,rgtChdPrs);
//        rgtParams.put("shops", chilAry);
//        String requestBody = null;
//        try {
//            requestBody = new ObjectMapper().writeValueAsString(rgtParams);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
//        String result = GateWayClient.askGateWay(HttpMethod.POST,
//            "http://open-api.shuyun.com",
//            null,
//            requestBody,
//            "7bda7ec0f8e24b5cb0ed3762d265c088",
//            "30C149B6D2EABE33EBB8F544633C4B9B",
//            "OUU4NzJCREI0RjVFOUNGMkE1RTY0MEY4NTM0MEI3Rjg",
//            "shuyun.base.shop.batch.register");
//        System.out.println(result);
//        JSONObject rgtJson = JSONObject.parseObject(result);
//        if ("10000".equals(rgtJson.getString("code"))) {
//
//        }
    }
}
