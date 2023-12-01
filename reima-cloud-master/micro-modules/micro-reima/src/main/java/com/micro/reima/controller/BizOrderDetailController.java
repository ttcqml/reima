package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.reima.domain.BizOrderDetail;
import com.micro.reima.service.IBizOrderDetailService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.domain.AjaxResult;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 订单明细Controller
 * 
 * @author micro
 * @date 2022-01-12
 */
@Api(tags = "订单明细")
@RestController
@RequestMapping("/orderDetail")
public class BizOrderDetailController extends BaseController
{
    @Autowired
    private IBizOrderDetailService bizOrderDetailService;

    /**
     * 查询订单明细列表
     */
    @RequiresPermissions("reima:orderDetail:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询订单明细列表")
    public TableDataInfo<List<BizOrderDetail>> list(BizOrderDetail bizOrderDetail)
    {
        startPage();
        List<BizOrderDetail> list = bizOrderDetailService.selectBizOrderDetailList(bizOrderDetail);
        return getDataTable(list);
    }

    /**
     * 导出订单明细列表
     */
    @RequiresPermissions("reima:orderDetail:export")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出订单明细列表")
    public void export(HttpServletResponse response, BizOrderDetail bizOrderDetail)
    {
        List<BizOrderDetail> list = bizOrderDetailService.selectBizOrderDetailList(bizOrderDetail);
        ExcelUtil<BizOrderDetail> util = new ExcelUtil<BizOrderDetail>(BizOrderDetail.class);
        util.exportExcel(response, list, "订单明细数据");
    }

    /**
     * 获取订单明细详细信息
     */
    @RequiresPermissions("reima:orderDetail:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取订单明细详细信息")
    public R<BizOrderDetail> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizOrderDetailService.selectBizOrderDetailById(id));
    }

    /**
     * 新增订单明细
     */
    @RequiresPermissions("reima:orderDetail:add")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增订单明细")
    public R add(@RequestBody BizOrderDetail bizOrderDetail)
    {
        return R.auto(bizOrderDetailService.insertBizOrderDetail(bizOrderDetail));
    }

    /**
     * 修改订单明细
     */
    @RequiresPermissions("reima:orderDetail:edit")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改订单明细")
    public R edit(@RequestBody BizOrderDetail bizOrderDetail)
    {
        return R.auto(bizOrderDetailService.updateBizOrderDetail(bizOrderDetail));
    }

    /**
     * 删除订单明细
     */
    @RequiresPermissions("reima:orderDetail:remove")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除订单明细")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizOrderDetailService.deleteBizOrderDetailByIds(ids));
    }
}
