package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.reima.domain.BizMedia;
import com.micro.reima.model.vo.SimpleMediaVo;
import com.micro.reima.service.IBizMediaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 最新活动Controller
 * 
 * @author micro
 * @date 2021-12-21
 */
@Api(tags = "最新活动")
@RestController
@RequestMapping("/media")
public class BizMediaController extends BaseController
{
    @Autowired
    private IBizMediaService bizMediaService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询最新活动列表(无需登录)")
    public TableDataInfo<List<SimpleMediaVo>> appList()
    {
        startPage();
        List<SimpleMediaVo> list = bizMediaService.appList();
        return getDataTable(list);
    }

    @GetMapping(value = "/app/{id}")
    @ApiOperation("[前台]获取最新活动详细信息(无需登录)")
    public R<BizMedia> appDetail(@PathVariable("id") Long id)
    {
        return R.ok(bizMediaService.selectBizMediaById(id));
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询资讯媒体列表
     */
    @RequiresPermissions("reima:media:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询资讯媒体列表")
    public TableDataInfo<List<BizMedia>> list(BizMedia bizMedia)
    {
        startPage();
        List<BizMedia> list = bizMediaService.selectBizMediaList(bizMedia);
        return getDataTable(list);
    }

    /**
     * 导出资讯媒体列表
     */
    @RequiresPermissions("reima:media:export")
    @Log(title = "资讯媒体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出资讯媒体列表")
    public void export(HttpServletResponse response, BizMedia bizMedia)
    {
        List<BizMedia> list = bizMediaService.selectBizMediaList(bizMedia);
        ExcelUtil<BizMedia> util = new ExcelUtil<BizMedia>(BizMedia.class);
        util.exportExcel(response, list, "资讯媒体数据");
    }

    /**
     * 获取资讯媒体详细信息
     */
    @RequiresPermissions("reima:media:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取资讯媒体详细信息")
    public R<BizMedia> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizMediaService.selectBizMediaById(id));
    }

    /**
     * 新增资讯媒体
     */
    @RequiresPermissions("reima:media:add")
    @Log(title = "资讯媒体", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增资讯媒体")
    public R add(@RequestBody BizMedia bizMedia)
    {
        return R.auto(bizMediaService.insertBizMedia(bizMedia));
    }

    /**
     * 修改资讯媒体
     */
    @RequiresPermissions("reima:media:edit")
    @Log(title = "资讯媒体", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改资讯媒体")
    public R edit(@RequestBody BizMedia bizMedia)
    {
        return R.auto(bizMediaService.updateBizMedia(bizMedia));
    }

    /**
     * 删除资讯媒体
     */
    @RequiresPermissions("reima:media:remove")
    @Log(title = "资讯媒体", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除资讯媒体")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizMediaService.deleteBizMediaByIds(ids));
    }
}
