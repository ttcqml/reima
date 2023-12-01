package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.reima.domain.BizCoupon;
import com.micro.reima.model.admin.BizCouponInfo;
import com.micro.reima.model.admin.UserMobile;
import com.micro.reima.model.vo.SimpleCouponVo;
import com.micro.reima.service.IBizCouponService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 优惠券Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "优惠券")
@RestController
@RequestMapping("/coupon")
public class BizCouponController extends BaseController
{
    @Autowired
    private IBizCouponService bizCouponService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询优惠券列表(无需登录)")
    public TableDataInfo<List<SimpleCouponVo>> appList()
    {
        startPage();
        List<SimpleCouponVo> list = bizCouponService.appList();
        return getDataTable(list);
    }

    @GetMapping("/app/{id}")
    @ApiOperation("[前台]查询优惠券详情(无需登录)")
    public R<BizCoupon> appDetail(@PathVariable("id") Long id)
    {
        return R.ok(bizCouponService.selectBizCouponById(id));
    }

    // ---------------------------后台接口---------------------------//
    @PostMapping("/send")
    @ApiOperation("[后台]发送优惠券")
    public R send(@RequestBody UserMobile userMobile)
    {
        return R.auto(bizCouponService.send(userMobile));
    }

    @PostMapping("/sendBatch/{id}")
    @ApiOperation("[后台]全量发送优惠券")
    public R sendBatch(@RequestBody @PathVariable("id") Long id)
    {
        return R.auto(bizCouponService.sendBatch(id));
    }

    @GetMapping("/kvList")
    @ApiOperation("[后台]查询优惠券详情(无需登录)")
    public R<List<BizCouponInfo>> kvList()
    {
        return R.ok(bizCouponService.kvList());
    }

    /**
     * 查询优惠券列表
     */
    @RequiresPermissions("reima:coupon:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询优惠券列表")
    public TableDataInfo<List<BizCoupon>> list(BizCoupon bizCoupon)
    {
        startPage();
        List<BizCoupon> list = bizCouponService.selectBizCouponList(bizCoupon);
        return getDataTable(list);
    }

    /**
     * 导出优惠券列表
     */
    @RequiresPermissions("reima:coupon:export")
    @Log(title = "优惠券", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出优惠券列表")
    public void export(HttpServletResponse response, BizCoupon bizCoupon)
    {
        List<BizCoupon> list = bizCouponService.selectBizCouponList(bizCoupon);
        ExcelUtil<BizCoupon> util = new ExcelUtil<BizCoupon>(BizCoupon.class);
        util.exportExcel(response, list, "优惠券数据");
    }

    /**
     * 获取优惠券详细信息
     */
    @RequiresPermissions("reima:coupon:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取优惠券详细信息")
    public R<BizCoupon> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizCouponService.selectBizCouponById(id));
    }

    /**
     * 新增优惠券
     */
    @RequiresPermissions("reima:coupon:add")
    @Log(title = "优惠券", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增优惠券")
    public R add(@RequestBody BizCoupon bizCoupon)
    {
        return R.auto(bizCouponService.insertBizCoupon(bizCoupon));
    }

    /**
     * 修改优惠券
     */
    @RequiresPermissions("reima:coupon:edit")
    @Log(title = "优惠券", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改优惠券")
    public R edit(@RequestBody BizCoupon bizCoupon)
    {
        return R.auto(bizCouponService.updateBizCoupon(bizCoupon));
    }

    /**
     * 删除优惠券
     */
    @RequiresPermissions("reima:coupon:remove")
    @Log(title = "优惠券", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除优惠券")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizCouponService.deleteBizCouponByIds(ids));
    }
}
