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
import com.micro.reima.domain.BizPayDetail;
import com.micro.reima.service.IBizPayDetailService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.domain.AjaxResult;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 支付明细Controller
 * 
 * @author micro
 * @date 2022-01-12
 */
@Api(tags = "支付明细")
@RestController
@RequestMapping("/payDetail")
public class BizPayDetailController extends BaseController
{
    @Autowired
    private IBizPayDetailService bizPayDetailService;

    /**
     * 查询支付明细列表
     */
    @RequiresPermissions("reima:payDetail:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询支付明细列表")
    public TableDataInfo<List<BizPayDetail>> list(BizPayDetail bizPayDetail)
    {
        startPage();
        List<BizPayDetail> list = bizPayDetailService.selectBizPayDetailList(bizPayDetail);
        return getDataTable(list);
    }

    /**
     * 导出支付明细列表
     */
    @RequiresPermissions("reima:payDetail:export")
    @Log(title = "支付明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出支付明细列表")
    public void export(HttpServletResponse response, BizPayDetail bizPayDetail)
    {
        List<BizPayDetail> list = bizPayDetailService.selectBizPayDetailList(bizPayDetail);
        ExcelUtil<BizPayDetail> util = new ExcelUtil<BizPayDetail>(BizPayDetail.class);
        util.exportExcel(response, list, "支付明细数据");
    }

    /**
     * 获取支付明细详细信息
     */
    @RequiresPermissions("reima:payDetail:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取支付明细详细信息")
    public R<BizPayDetail> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizPayDetailService.selectBizPayDetailById(id));
    }

    /**
     * 新增支付明细
     */
    @RequiresPermissions("reima:payDetail:add")
    @Log(title = "支付明细", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增支付明细")
    public R add(@RequestBody BizPayDetail bizPayDetail)
    {
        return R.auto(bizPayDetailService.insertBizPayDetail(bizPayDetail));
    }

    /**
     * 修改支付明细
     */
    @RequiresPermissions("reima:payDetail:edit")
    @Log(title = "支付明细", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改支付明细")
    public R edit(@RequestBody BizPayDetail bizPayDetail)
    {
        return R.auto(bizPayDetailService.updateBizPayDetail(bizPayDetail));
    }

    /**
     * 删除支付明细
     */
    @RequiresPermissions("reima:payDetail:remove")
    @Log(title = "支付明细", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除支付明细")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizPayDetailService.deleteBizPayDetailByIds(ids));
    }
}
