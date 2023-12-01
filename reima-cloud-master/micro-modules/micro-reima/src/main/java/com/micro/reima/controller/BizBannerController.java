package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.reima.model.vo.SimpleBannerVo;
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
import com.micro.reima.domain.BizBanner;
import com.micro.reima.service.IBizBannerService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 广告Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "轮播图")
@RestController
@RequestMapping("/banner")
public class BizBannerController extends BaseController
{
    @Autowired
    private IBizBannerService bizBannerService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询轮播图列表(无需登录)")
    public R<List<SimpleBannerVo>> appList()
    {
        return R.ok(bizBannerService.appList());
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询广告列表
     */
    @RequiresPermissions("reima:banner:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询广告列表")
    public TableDataInfo<List<BizBanner>> list(BizBanner bizBanner)
    {
        startPage();
        List<BizBanner> list = bizBannerService.selectBizBannerList(bizBanner);
        return getDataTable(list);
    }

    /**
     * 导出广告列表
     */
    @RequiresPermissions("reima:banner:export")
    @Log(title = "广告", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出广告列表")
    public void export(HttpServletResponse response, BizBanner bizBanner)
    {
        List<BizBanner> list = bizBannerService.selectBizBannerList(bizBanner);
        ExcelUtil<BizBanner> util = new ExcelUtil<BizBanner>(BizBanner.class);
        util.exportExcel(response, list, "广告数据");
    }

    /**
     * 获取广告详细信息
     */
    @RequiresPermissions("reima:banner:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取广告详细信息")
    public R<BizBanner> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizBannerService.selectBizBannerById(id));
    }

    /**
     * 新增广告
     */
    @RequiresPermissions("reima:banner:add")
    @Log(title = "广告", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增广告")
    public R add(@RequestBody BizBanner bizBanner)
    {
        return R.auto(bizBannerService.insertBizBanner(bizBanner));
    }

    /**
     * 修改广告
     */
    @RequiresPermissions("reima:banner:edit")
    @Log(title = "广告", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改广告")
    public R edit(@RequestBody BizBanner bizBanner)
    {
        return R.auto(bizBannerService.updateBizBanner(bizBanner));
    }

    /**
     * 删除广告
     */
    @RequiresPermissions("reima:banner:remove")
    @Log(title = "广告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除广告")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizBannerService.deleteBizBannerByIds(ids));
    }
}
