package com.micro.common.core.web.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.List;

/**
 * 表格分页数据对象
 * 
 * @author micro
 */
@ApiModel("TableDataInfo")
public class TableDataInfo<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    @ApiModelProperty("总记录数")
    private long total;

    /** 列表数据 */
    @ApiModelProperty("列表数据")
    private T rows;

    /** 消息状态码 */
    @ApiModelProperty("返回码 200成功 500失败")
    private int code;

    /** 消息内容 */
    @ApiModelProperty("提示信息")
    private String msg;

    /**
     * 表格数据对象
     */
    public TableDataInfo()
    {
    }

    /**
     * 分页
     * 
     * @param list 列表数据
     * @param total 总记录数
     */
    public TableDataInfo(T list, int total)
    {
        this.rows = list;
        this.total = total;
    }

    public long getTotal()
    {
        return total;
    }

    public void setTotal(long total)
    {
        this.total = total;
    }

    public T getRows()
    {
        return rows;
    }

    public void setRows(T rows)
    {
        this.rows = rows;
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
}