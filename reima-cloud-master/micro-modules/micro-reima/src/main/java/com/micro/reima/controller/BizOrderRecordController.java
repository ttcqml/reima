package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizOrderRecord;
import com.micro.reima.model.admin.BizOrderRecordVo;
import com.micro.reima.model.vo.UserOrderRecordVo;
import com.micro.reima.service.IBizOrderRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 订单明细Controller
 * 
 * @author micro
 * @date 2022-01-05
 */
@Api(tags = "订单明细")
@RestController
@RequestMapping("/order/record")
public class BizOrderRecordController extends BaseController
{
    @Autowired
    private IBizOrderRecordService bizOrderRecordService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询订单明细(需要登录)")
    public TableDataInfo<List<UserOrderRecordVo>> appList(@ApiParam(value = "开始日期 yyyy-MM-dd", required = false) @RequestParam(defaultValue = "") String startTime,
        @ApiParam(value = "结束日期 yyyy-MM-dd", required = false) @RequestParam(defaultValue = "") String endTime)
    {
        startPage();
        List<UserOrderRecordVo> list = bizOrderRecordService.selectUserOrderRecordVoByUserId(SecurityUtils.getUserId(),startTime,endTime);
        return getDataTable(list);
    }
    // ---------------------------后台接口---------------------------//
    /**
     * 查询订单明细列表
     */
    @RequiresPermissions("reima:record:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询订单明细列表")
    public TableDataInfo<List<BizOrderRecordVo>> list(BizOrderRecordVo bizOrderRecord)
    {
        startPage();
        List<BizOrderRecordVo> list = bizOrderRecordService.selectBizOrderRecordList(bizOrderRecord);
        return getDataTable(list);
    }

    /**
     * 导出订单明细列表
     */
    @RequiresPermissions("reima:record:export")
    @Log(title = "订单明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出订单明细列表")
    public void export(HttpServletResponse response, BizOrderRecordVo bizOrderRecord)
    {
        List<BizOrderRecordVo> list = bizOrderRecordService.selectBizOrderRecordList(bizOrderRecord);
        ExcelUtil<BizOrderRecordVo> util = new ExcelUtil<BizOrderRecordVo>(BizOrderRecordVo.class);
        util.exportExcel(response, list, "订单明细数据");
    }

    /**
     * 获取订单明细详细信息
     */
    @RequiresPermissions("reima:record:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取订单明细详细信息")
    public R<BizOrderRecordVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizOrderRecordService.selectBizOrderRecordById(id));
    }

    /**
     * 新增订单明细
     */
    @RequiresPermissions("reima:record:add")
    @Log(title = "订单明细", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增订单明细")
    public R add(@RequestBody BizOrderRecord bizOrderRecord)
    {
        return R.auto(bizOrderRecordService.insertBizOrderRecord(bizOrderRecord));
    }

    /**
     * 修改订单明细
     */
    @RequiresPermissions("reima:record:edit")
    @Log(title = "订单明细", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改订单明细")
    public R edit(@RequestBody BizOrderRecord bizOrderRecord)
    {
        return R.auto(bizOrderRecordService.updateBizOrderRecord(bizOrderRecord));
    }

    /**
     * 删除订单明细
     */
    @RequiresPermissions("reima:record:remove")
    @Log(title = "订单明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除订单明细")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizOrderRecordService.deleteBizOrderRecordByIds(ids));
    }
}
