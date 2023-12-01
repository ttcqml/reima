package com.micro.common.core.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.micro.common.core.constant.Constants;
import com.micro.common.core.utils.IdUtils;
import com.micro.common.core.utils.sign.MD5;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.bouncycastle.crypto.digests.MD5Digest;

import java.io.Serializable;

/**
 * 响应信息主体
 *
 * @author micro
 */
@ApiModel("R")
@JsonInclude(value = Include.ALWAYS)
public class PosResult<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 成功 */
    public static final int SUCCESS = 2000;

    /** 失败 */
    public static final int FAIL = Constants.FAIL;

    @ApiModelProperty("返回码 2000成功 500失败")
    private int code;
    @ApiModelProperty("返回说明")
    private String msg;
    @ApiModelProperty("返回结果")
    private T result;
    @ApiModelProperty("使用appid&timestamp&password组合MD5加密处理")
    private String signature;
    @ApiModelProperty("时间戳，例如“1526366974000”")
    private String timestamp;

    public static <T> PosResult<T> ok()
    {
        return restResult(null, SUCCESS, "Success");
    }

    public static <T> PosResult<T> ok(T data)
    {
        return restResult(data, SUCCESS, "Success");
    }

    public static <T> PosResult<T> ok(T data, String msg)
    {
        return restResult(data, SUCCESS, msg);
    }

    public static <T> PosResult<T> fail()
    {
        return restResult(null, FAIL, "Error");
    }

    public static <T> PosResult<T> fail(String msg)
    {
        return restResult(null, FAIL, msg);
    }

    public static <T> PosResult<T> fail(T data)
    {
        return restResult(data, FAIL, "Error");
    }

    public static <T> PosResult<T> fail(T data, String msg)
    {
        return restResult(data, FAIL, msg);
    }

    public static <T> PosResult<T> fail(int code, String msg)
    {
        return restResult(null, code, msg);
    }

    public static <T> PosResult<T> auto(int rows)
    {
        return rows > 0 ? PosResult.ok() : PosResult.fail();
    }

    private static <T> PosResult<T> restResult(T data, int code, String msg)
    {
        PosResult<T> apiResult = new PosResult<>();
        apiResult.setCode(code);
        apiResult.setResult(data);
        apiResult.setMsg(msg);
        apiResult.setTimestamp(IdUtils.timestamp());
        //TODO signature使用appid&timestamp&password组合MD5加密处理
        apiResult.setSignature(DigestUtils.md5Hex("XtWVyfAo5XDNlrPD2VIaQIlkt704Zgug&"+apiResult.getTimestamp()+"&d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN"));
        return apiResult;
    }



    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public T getResult()
    {
        return result;
    }

    public void setResult(T result)
    {
        this.result = result;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
