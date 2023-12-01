package com.micro.common.core.utils.ip;

import com.alibaba.fastjson.JSON;
import com.micro.common.core.utils.IdUtils;
import com.micro.common.core.utils.StringUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.util.CollectionUtils;

public class OkHttpUtil {

  private static final Logger log = LoggerFactory.getLogger(OkHttpUtil.class);

  private static final String HTTP_JSON = "application/json; charset=utf-8";
  private static final String HTTP_FORM = "application/x-www-form-urlencoded; charset=utf-8";

  private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
      .connectTimeout(120, TimeUnit.SECONDS)
      .readTimeout(120, TimeUnit.SECONDS)
      .writeTimeout(120, TimeUnit.SECONDS)
      .build();

  /**
   * get请求
   * 对于小文档，响应体上的string()方法非常方便和高效。
   * 但是，如果响应主体很大(大于1 MB)，则应避免string()，
   * 因为它会将整个文档加载到内存中。在这种情况下，将主体处理为流。
   */
  public static String httpGet(String url) {
    if (url == null || "".equals(url)) {
      log.error("url为null!");
      return "";
    }
    Request.Builder builder = new Request.Builder();
    Request request = builder.get().url(url).build();
    try {
      Response response = okHttpClient.newCall(request).execute();
      if (response.code() == 200) {
        log.info("http GET 请求成功; [url={}]", url);
        return response.body().string();
      } else {
        log.warn("Http GET 请求失败; [errorCode = {} , url={}]", response.code(), url);
      }
    } catch (IOException e) {
      throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
    }
    return null;
  }

  public static String httpGet(String url, Map<String, String> headers) {
    if (CollectionUtils.isEmpty(headers)) {
      return httpGet(url);
    }
    Request.Builder builder = new Request.Builder();
    headers.forEach((String key, String value) -> builder.header(key, value));
    Request request = builder.get().url(url).build();
    try {
      Response response = okHttpClient.newCall(request).execute();
      if (response.code() == 200) {
        log.info("http GET 请求成功; [url={}]", url);
        return response.body().string();
      } else {
        log.warn("Http GET 请求失败; [errorxxCode = {} , url={}]", response.code(), url);
      }
    } catch (IOException e) {
      throw new RuntimeException("同步http GET 请求失败,url:" + url, e);
    }
    return null;
  }

  /**
   * 同步 POST调用 无Header
   */
  public static String httpPostJson(String url, String json) {
    if (url == null || "".equals(url)) {
      log.error("url为null!");
      return "";
    }
    MediaType JSON = MediaType.parse(HTTP_JSON);
    RequestBody body = RequestBody.create(JSON, json);
    Request.Builder requestBuilder = new Request.Builder().url(url);
    Request request = requestBuilder.post(body).build();
    try {
      Response response = okHttpClient.newCall(request).execute();
      if (response.code() == 200) {
        log.info("http Post 请求成功:\n [url={}, requestContent={}]", url, json);
        return response.body().string();
      } else {
        log.warn("Http POST 请求失败:\n [ errorCode = {}, url={}, param={}]", response.code(), url, json);
      }
    } catch (IOException e) {
      throw new RuntimeException("同步http请求失败,url:" + url, e);
    }
    return null;
  }

  /**
   * 同步 POST调用 有Header
   */
  public static String httpPostJson(String url, Map<String, String> headers, String json) {
    if (CollectionUtils.isEmpty(headers)) {
      httpPostJson(url, json);
    }
    MediaType JSON = MediaType.parse(HTTP_JSON);
    RequestBody body = RequestBody.create(JSON, json);
    Request.Builder requestBuilder = new Request.Builder().url(url);
    headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
    Request request = requestBuilder.post(body).build();
    try {
      Response response = okHttpClient.newCall(request).execute();
      if (response.code() == 200) {
        log.info("http Post 请求成功; [url={}, requestContent={}]", url, json);
        return response.body().string();
      } else {
        log.warn("Http POST 请求失败; [ errorCode = {}, url={}, param={}]", response.code(), url, json);
      }
    } catch (IOException e) {
      throw new RuntimeException("同步http请求失败,url:" + url, e);
    }
    return null;
  }

  /**
   * 提交表单
   */
  public static String postDataByForm(String url, String content, Map<String, String> headers) {
    MediaType JSON = MediaType.parse(HTTP_FORM);
    RequestBody body = RequestBody.create(JSON, content);

    Request.Builder requestBuilder = new Request.Builder().url(url);
    if (headers != null && headers.size() > 0) {
      headers.forEach((k, v) -> requestBuilder.addHeader(k, v));
    }
    Request request = requestBuilder
        .post(body)
        .build();
    Response response = null;
    try {
      response = okHttpClient.newCall(request).execute();
      if (response.code() == 200) {
        String result = response.body().string();
        log.info("postDataByForm; [postUrl={}, requestContent={}, responseBody={}]", url, content, result);
        return result;
      } else {
        log.warn("Http Post Form请求失败,[url={}, param={}]", url, content);
      }
    } catch (IOException e) {
      log.error("Http Post Form请求失败,[url={}, param={}]", url, content, e);
      throw new RuntimeException("Http Post Form请求失败,url:" + url);
    }
    return null;
  }

  /**
   * 异步Http调用参考模板：Get、Post、Put
   * 需要异步调用的接口一般情况下你需要定制一个专门的Http方法
   */
  @Deprecated
  public static Future<Boolean> asyncHttpByJson(HttpMethod httpMethod, String url, Map<String, String> headers, String content) {
    MediaType JSON = MediaType.parse(HTTP_JSON);
    RequestBody body = RequestBody.create(JSON, content);

    Request.Builder requestBuilder = new Request.Builder()
        .url(url);

    if (!CollectionUtils.isEmpty(headers)) {
      headers.forEach((key, value) -> requestBuilder.header(key, value));
    }

    switch (httpMethod) {
      case GET:
        requestBuilder.get();
        break;
      case POST:
        requestBuilder.post(body);
        break;
      default:
    }
    Request request = requestBuilder.build();
    Call call = okHttpClient.newCall(request);
    call.enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        log.error("异步http {} 请求失败,[url={}, param={}]", httpMethod.name(), url, content);
        throw new RuntimeException("异步http请求失败,url:" + url);
      }
      @Override
      public void onResponse(Call call, final Response response) throws IOException {
        if (response.code() == 200) {
          System.out.println("需要加入异步回调操作");
        } else {
          log.error("异步http {} 请求失败,错误码为{},请求参数为[url={}, param={}]", httpMethod.name(), response.code(), url, content);
        }
      }
    });
    return new AsyncResult(true);
  }

  /**
   * lambda表达式异步调用http模板,不建议使用
   */
  public static void asyncCall(Request request, Consumer<Exception> failure, Consumer<Response> respConsumer) {
    okHttpClient.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
        failure.accept(e);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
        respConsumer.accept(response);
      }
    });
  }

  public static String iposApi(String url,String method,String key,String secret,Map<String,Object> params){
    Map<String,Object> requestParams = new HashMap<>();
    requestParams.put("method",method);
    requestParams.put("key",key);
    requestParams.put("time", IdUtils.timestamp());
    requestParams.put("params",params);

    Map<String,Object> result = new LinkedHashMap<>();
    requestParams.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .forEachOrdered(x->result.put(x.getKey(),x.getValue()));

    String signStr = key+JSON.toJSONString(result)+secret;
    log.info("signStr:{}",signStr);
    String sign = DigestUtils.md5Hex(signStr);
    requestParams.put("sign",sign);
    return StringUtils.decodeUnicode(httpPostJson(url,JSON.toJSONString(requestParams)));
  }

  public static void main(String[] args) {
    String url="https://channel-ue46.shuyun.com:18081/reima/pos";
//    Map<String,String> tokenMap = new HashMap<>();
//    tokenMap.put("grant_type","custom");
//    tokenMap.put("login_name","XtWVyfAo5XDNlrPD2VIaQIlkt704Zgug");
//    tokenMap.put("password",DigestUtils.sha1Hex("d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN"));
//    tokenMap.put("company","reima");
//    String tokenParams = "grant_type="+"custom&"+"login_name="+"XtWVyfAo5XDNlrPD2VIaQIlkt704Zgug&"+"password="+DigestUtils.sha1Hex("d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN")+"&company="+"reima";
//    System.out.println(httpGet(url+"/security/oauth/token?"+tokenParams));

//    String token = "eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjotMSwidXNlcl9rZXkiOiIwZTBiOWUzOC1kYmE3LTQ5NjktYTM4ZC01OWUyZDAzOTExNDYiLCJ1c2VybmFtZSI6Ilh0V1Z5ZkFvNVhETmxyUEQyVklhUUlsa3Q3MDRaZ3VnODBhNzVhY2IwZTQ1ZWJhYzhmM2VmYzQ5NmVlNjQ1NTlmZWI0ZjM5YiJ9.YyXRAj1pgoZM2QcYQooZQ53PFnCASQd6jcm7jCPEYoiy4AGNT5g815_R5sbXs7T5bsL7Mn1TXLEx9vMAy9eTHw";
//    Map<String,String> params = new HashMap<>();
//    params.put("access_token",token);
//    params.put("request_id",IdUtils.simpleUUID());
//    params.put("timestamp",IdUtils.timestamp());
////    String requestBody = "{\"bu_content\":{\"transaction_date\":\"2022-01-17T15:10:54+08:00\",\"store_code\":\"P10034\",\"total_discount\":\"0.4511\",\"invoice_no\":\"HHH_xpd200327000157\",\"member_code\":\"19810660521\",\"original_invoice_no\":\"\",\"channel_code\":\"POS\",\"transaction_details\":[{\"real_amount\":\"180\",\"product_spec_code\":\"\",\"quantity\":\"1\",\"discount\":\"0.45\",\"real_price\":\"180\",\"product_code\":\"510224BS-4909-005\",\"unit_price\":\"399.00\",\"employee_code\":\"100023\"}],\"employee_code\":\"100023\",\"transaction_type_code\":\"purchase\",\"total_amount\":\"180\",\"total_quantity\":\"1\",\"purchase_date\":\"2022-01-17T15:10:54+08:00\",\"transaction_tenders\":[{\"real_amount\":\"180.00\",\"source_no\":\"\",\"tender_type_code\":\"000\"}]}}";
//    String requestBody = "{\"bu_content\":{\"coupon_serial_no\":\"9171828625231\"}}";
//    String signString = params.get("request_id")+"&"+params.get("timestamp")+"&"+requestBody+"&";
//    params.put("signature",DigestUtils.md5Hex(signString));
//    String result = httpPostJson(url+"/getCoupon",params,requestBody);
//    System.out.println("result:"+result);

//    String url = "http://101.200.232.173/ipos/web/api/api.php",key = "base",secret = "123456";
//    Map<String,Object> params = new HashMap<>();
//    params.put("lastchanged", DateUtils.getSecondTimestampTwo(DateUtils.parseDateByPattern("2021-01-01",DateUtils.YYYY_MM_DD)));
////    params.put("page",1);
////    params.put("limit",10);
//    String result = iposApi(url,"get_shop_list",key,secret,params);
//
//    System.out.println("get_shop_list:\n"+result);
////
////    result = iposApi(url,"get_good_list",key,secret,params);
////    System.out.println("get_good_list:\n"+result);
////
////    result = iposApi(url,"get_sku_list",key,secret,params);
////    System.out.println("get_sku_list:\n"+result);
  }

}