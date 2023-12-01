package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.reima.domain.BizRegion;
import com.micro.reima.model.vo.SimpleRegionVo;
import com.micro.reima.service.IBizRegionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 行政区域Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "行政区域")
@RestController
@RequestMapping("/region")
public class BizRegionController extends BaseController
{
    @Autowired
    private IBizRegionService bizRegionService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/{pid}")
    @ApiOperation("[前台]查询区域信息(无需登录)")
    public R<List<SimpleRegionVo>> appList(@PathVariable("pid") Long pid)
    {
        return R.ok(bizRegionService.appList(pid));
    }

    // ---------------------------后台接口---------------------------//
    @GetMapping("/adminList/{pid}")
    @RequiresPermissions("reima:region:list")
    @ApiOperation("[后台]查询区域信息")
    public R<List<SimpleRegionVo>> adminList(@PathVariable("pid") Long pid)
    {
        return R.ok(bizRegionService.appList(pid));
    }

    /**
     * 查询行政区域列表
     */
    @RequiresPermissions("reima:region:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询行政区域列表")
    public TableDataInfo<List<BizRegion>> list(BizRegion bizRegion)
    {
        startPage();
        List<BizRegion> list = bizRegionService.selectBizRegionList(bizRegion);
        return getDataTable(list);
    }

    /**
     * 导出行政区域列表
     */
    @RequiresPermissions("reima:region:export")
    @Log(title = "行政区域", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出行政区域列表")
    public void export(HttpServletResponse response, BizRegion bizRegion)
    {
        List<BizRegion> list = bizRegionService.selectBizRegionList(bizRegion);
        ExcelUtil<BizRegion> util = new ExcelUtil<BizRegion>(BizRegion.class);
        util.exportExcel(response, list, "行政区域数据");
    }

    /**
     * 获取行政区域详细信息
     */
    @RequiresPermissions("reima:region:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取行政区域详细信息")
    public R<BizRegion> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizRegionService.selectBizRegionById(id));
    }

    /**
     * 新增行政区域
     */
    @RequiresPermissions("reima:region:add")
    @Log(title = "行政区域", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增行政区域")
    public R add(@RequestBody BizRegion bizRegion)
    {
        return R.auto(bizRegionService.insertBizRegion(bizRegion));
    }

    /**
     * 修改行政区域
     */
    @RequiresPermissions("reima:region:edit")
    @Log(title = "行政区域", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改行政区域")
    public R edit(@RequestBody BizRegion bizRegion)
    {
        return R.auto(bizRegionService.updateBizRegion(bizRegion));
    }

    /**
     * 删除行政区域
     */
    @RequiresPermissions("reima:region:remove")
    @Log(title = "行政区域", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除行政区域")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizRegionService.deleteBizRegionByIds(ids));
    }
}
