package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.reima.domain.BizCouponSetting;
import com.micro.reima.service.IBizCouponSettingService;
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
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 优惠券入会赠送配置Controller
 * 
 * @author micro
 * @date 2022-03-05
 */
@Api(tags = "优惠券入会赠送配置")
@RestController
@RequestMapping("/couponSetting")
public class BizCouponSettingController extends BaseController
{
    @Autowired
    private IBizCouponSettingService bizCouponSettingService;

    /**
     * 查询优惠券入会赠送配置列表
     */
    @RequiresPermissions("forge:couponSetting:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询优惠券入会赠送配置列表")
    public TableDataInfo<List<BizCouponSetting>> list(BizCouponSetting bizCouponSetting)
    {
        startPage();
        List<BizCouponSetting> list = bizCouponSettingService.selectBizCouponSettingList(bizCouponSetting);
        return getDataTable(list);
    }

    /**
     * 导出优惠券入会赠送配置列表
     */
    @RequiresPermissions("forge:couponSetting:export")
    @Log(title = "优惠券入会赠送配置", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出优惠券入会赠送配置列表")
    public void export(HttpServletResponse response, BizCouponSetting bizCouponSetting)
    {
        List<BizCouponSetting> list = bizCouponSettingService.selectBizCouponSettingList(bizCouponSetting);
        ExcelUtil<BizCouponSetting> util = new ExcelUtil<BizCouponSetting>(BizCouponSetting.class);
        util.exportExcel(response, list, "优惠券入会赠送配置数据");
    }

    /**
     * 获取优惠券入会赠送配置详细信息
     */
    @RequiresPermissions("forge:couponSetting:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取优惠券入会赠送配置详细信息")
    public R<BizCouponSetting> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizCouponSettingService.selectBizCouponSettingById(id));
    }

    /**
     * 新增优惠券入会赠送配置
     */
    @RequiresPermissions("forge:couponSetting:add")
    @Log(title = "优惠券入会赠送配置", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增优惠券入会赠送配置")
    public R add(@RequestBody BizCouponSetting bizCouponSetting)
    {
        return R.auto(bizCouponSettingService.insertBizCouponSetting(bizCouponSetting));
    }

    /**
     * 修改优惠券入会赠送配置
     */
    @RequiresPermissions("forge:couponSetting:edit")
    @Log(title = "优惠券入会赠送配置", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改优惠券入会赠送配置")
    public R edit(@RequestBody BizCouponSetting bizCouponSetting)
    {
        return R.auto(bizCouponSettingService.updateBizCouponSetting(bizCouponSetting));
    }

    /**
     * 删除优惠券入会赠送配置
     */
    @RequiresPermissions("forge:couponSetting:remove")
    @Log(title = "优惠券入会赠送配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除优惠券入会赠送配置")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizCouponSettingService.deleteBizCouponSettingByIds(ids));
    }
}
