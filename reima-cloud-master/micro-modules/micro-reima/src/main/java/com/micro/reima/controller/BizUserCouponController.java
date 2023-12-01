package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizUserCoupon;
import com.micro.reima.model.admin.BizUserCouponVo;
import com.micro.reima.model.vo.SimpleUserCouponVo;
import com.micro.reima.service.IBizUserCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户优惠券Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "用户优惠券")
@RestController
@RequestMapping("/user/coupon")
public class BizUserCouponController extends BaseController
{
    @Autowired
    private IBizUserCouponService bizUserCouponService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询用户优惠券列表(需要登录)")
    public TableDataInfo<List<SimpleUserCouponVo>> appList(@ApiParam(value = "使用状态:如果是0则未使用,如果是1则已使用,如果是2则已过期", required = false) @RequestParam(defaultValue = "0") Integer status)
    {
        startPage();
        List<SimpleUserCouponVo> list = bizUserCouponService.appList(SecurityUtils.getUserId(),status);
        return getDataTable(list);
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询用户优惠券列表
     */
    @RequiresPermissions("reima:coupon:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询用户优惠券列表")
    public TableDataInfo<List<BizUserCouponVo>> list(BizUserCouponVo bizUserCoupon)
    {
        startPage();
        List<BizUserCouponVo> list = bizUserCouponService.selectBizUserCouponList(bizUserCoupon);
        return getDataTable(list);
    }

    /**
     * 导出用户优惠券列表
     */
    @RequiresPermissions("reima:coupon:export")
    @Log(title = "用户优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出用户优惠券列表")
    public void export(HttpServletResponse response, BizUserCouponVo bizUserCoupon)
    {
        List<BizUserCouponVo> list = bizUserCouponService.selectBizUserCouponList(bizUserCoupon);
        ExcelUtil<BizUserCouponVo> util = new ExcelUtil<BizUserCouponVo>(BizUserCouponVo.class);
        util.exportExcel(response, list, "用户优惠券数据");
    }

    /**
     * 获取用户优惠券详细信息
     */
    @RequiresPermissions("reima:coupon:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取用户优惠券详细信息")
    public R<BizUserCouponVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserCouponService.selectBizUserCouponById(id));
    }

    /**
     * 新增用户优惠券
     */
    @RequiresPermissions("reima:coupon:add")
    @Log(title = "用户优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增用户优惠券")
    public R add(@RequestBody BizUserCoupon bizUserCoupon)
    {
        return R.auto(bizUserCouponService.insertBizUserCoupon(bizUserCoupon));
    }

//    /**
//     * 修改用户优惠券
//     */
//    @RequiresPermissions("reima:coupon:edit")
//    @Log(title = "用户优惠券", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperation("[后台]修改用户优惠券")
//    public R edit(@RequestBody BizUserCoupon bizUserCoupon)
//    {
//        return R.auto(bizUserCouponService.updateBizUserCoupon(bizUserCoupon));
//    }

    /**
     * 删除用户优惠券
     */
    @RequiresPermissions("reima:coupon:remove")
    @Log(title = "用户优惠券", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除用户优惠券")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizUserCouponService.deleteBizUserCouponByIds(ids));
    }
}
