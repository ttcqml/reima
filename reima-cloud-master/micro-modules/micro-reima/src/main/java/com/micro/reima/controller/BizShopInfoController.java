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
import com.micro.reima.domain.BizShopInfo;
import com.micro.reima.service.IBizShopInfoService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 店铺Controller
 * 
 * @author micro
 * @date 2022-01-08
 */
@Api(tags = "店铺")
@RestController
@RequestMapping("/shop/info")
public class BizShopInfoController extends BaseController
{
    @Autowired
    private IBizShopInfoService bizShopInfoService;

    /**
     * 查询店铺列表
     */
    @RequiresPermissions("reima:info:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询店铺列表")
    public TableDataInfo<List<BizShopInfo>> list(BizShopInfo bizShopInfo)
    {
        startPage();
        List<BizShopInfo> list = bizShopInfoService.selectBizShopInfoList(bizShopInfo);
        return getDataTable(list);
    }

    /**
     * 导出店铺列表
     */
    @RequiresPermissions("reima:info:export")
    @Log(title = "店铺", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出店铺列表")
    public void export(HttpServletResponse response, BizShopInfo bizShopInfo)
    {
        List<BizShopInfo> list = bizShopInfoService.selectBizShopInfoList(bizShopInfo);
        ExcelUtil<BizShopInfo> util = new ExcelUtil<BizShopInfo>(BizShopInfo.class);
        util.exportExcel(response, list, "店铺数据");
    }

    /**
     * 获取店铺详细信息
     */
    @RequiresPermissions("reima:info:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取店铺详细信息")
    public R<BizShopInfo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizShopInfoService.selectBizShopInfoById(id));
    }

    /**
     * 新增店铺
     */
    @RequiresPermissions("reima:info:add")
    @Log(title = "店铺", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增店铺")
    public R add(@RequestBody BizShopInfo bizShopInfo)
    {
        return R.auto(bizShopInfoService.insertBizShopInfo(bizShopInfo));
    }

    /**
     * 修改店铺
     */
    @RequiresPermissions("reima:info:edit")
    @Log(title = "店铺", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改店铺")
    public R edit(@RequestBody BizShopInfo bizShopInfo)
    {
        return R.auto(bizShopInfoService.updateBizShopInfo(bizShopInfo));
    }

    /**
     * 删除店铺
     */
    @RequiresPermissions("reima:info:remove")
    @Log(title = "店铺", businessType = BusinessType.DELETE)
	  @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除店铺")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizShopInfoService.deleteBizShopInfoByIds(ids));
    }
}
