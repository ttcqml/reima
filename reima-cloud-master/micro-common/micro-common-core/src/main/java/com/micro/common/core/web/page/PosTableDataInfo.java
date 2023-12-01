package com.micro.common.core.web.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;

/**
 * 表格分页数据对象
 * 
 * @author micro
 */
@ApiModel("PosTableDataInfo")
public class PosTableDataInfo<T> implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 总记录数 */
    @ApiModelProperty("总记录数")
    private long total_count;

    @ApiModelProperty("总记录数")
    private long total_page;

    /** 列表数据 */
    @ApiModelProperty("列表数据")
    private T results;

    /**
     * 表格数据对象
     */
    public PosTableDataInfo()
    {
    }

    /**
     * 分页
     *
     * @param list 列表数据
     * @param total 总记录数
     */
    public PosTableDataInfo(T list, int total)
    {
        this.results = list;
        this.total_count = total;
    }

    public long getTotal_count()
    {
        return total_count;
    }

    public void setTotal_count(long total_count)
    {
        this.total_count = total_count;
    }

    public T getResults()
    {
        return results;
    }

    public void setResults(T results)
    {
        this.results = results;
    }

    public long getTotal_page() {
        return total_page;
    }

    public void setTotal_page(long total_page) {
        this.total_page = total_page;
    }
}