package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.reima.domain.BizExplain;
import com.micro.reima.service.IBizExplainService;
import io.swagger.annotations.*;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 说明信息Controller
 * 
 * @author micro
 * @date 2021-12-21
 */
@Api(tags = "说明信息")
@RestController
@RequestMapping("/explain")
public class BizExplainController extends BaseController
{
    @Autowired
    private IBizExplainService bizExplainService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/detail")
    @ApiOperation("[前台]查询隐私政策&服务协议(无需登录)")
    public R<String> appList(@ApiParam(value = "标题:隐私政策&服务协议", required = false) @RequestParam(defaultValue = "隐私政策") String title)
    {
        return R.ok(bizExplainService.selectByTitle(title));
    }



    // ---------------------------后台接口---------------------------//
    /**
     * 查询说明信息列表
     */
    @RequiresPermissions("reima:explain:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询说明信息列表")
    public TableDataInfo<List<BizExplain>> list(BizExplain bizExplain)
    {
        startPage();
        List<BizExplain> list = bizExplainService.selectBizExplainList(bizExplain);
        return getDataTable(list);
    }

    /**
     * 导出说明信息列表
     */
    @RequiresPermissions("reima:explain:export")
    @Log(title = "说明信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出说明信息列表")
    public void export(HttpServletResponse response, BizExplain bizExplain)
    {
        List<BizExplain> list = bizExplainService.selectBizExplainList(bizExplain);
        ExcelUtil<BizExplain> util = new ExcelUtil<BizExplain>(BizExplain.class);
        util.exportExcel(response, list, "说明信息数据");
    }

    /**
     * 获取说明信息详细信息
     */
    @RequiresPermissions("reima:explain:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取说明信息详细信息")
    public R<BizExplain> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizExplainService.selectBizExplainById(id));
    }

    /**
     * 新增说明信息
     */
    @RequiresPermissions("reima:explain:add")
    @Log(title = "说明信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增说明信息")
    public R add(@RequestBody BizExplain bizExplain)
    {
        return R.auto(bizExplainService.insertBizExplain(bizExplain));
    }

    /**
     * 修改说明信息
     */
    @RequiresPermissions("reima:explain:edit")
    @Log(title = "说明信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改说明信息")
    public R edit(@RequestBody BizExplain bizExplain)
    {
        return R.auto(bizExplainService.updateBizExplain(bizExplain));
    }

    /**
     * 删除说明信息
     */
    @RequiresPermissions("reima:explain:remove")
    @Log(title = "说明信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除说明信息")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizExplainService.deleteBizExplainByIds(ids));
    }
}
