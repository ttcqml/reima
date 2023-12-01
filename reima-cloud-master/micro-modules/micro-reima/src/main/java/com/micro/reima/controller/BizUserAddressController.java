package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizUserAddress;
import com.micro.reima.model.vo.BizUserAddressVo;
import com.micro.reima.model.vo.SimpleUserAddressVo;
import com.micro.reima.service.IBizUserAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户地址Controller
 * 
 * @author micro
 * @date 2021-12-25
 */
@Api(tags = "用户地址")
@RestController
@RequestMapping("/address")
public class BizUserAddressController extends BaseController
{
    @Autowired
    private IBizUserAddressService bizUserAddressService;
    // ---------------------------前台接口---------------------------//

    @GetMapping("/app/detail")
    @ApiOperation("[前台]查询用户默认地址")
    public R<SimpleUserAddressVo> detail()
    {
        return R.ok(bizUserAddressService.selectBizUserAddressByUserId(SecurityUtils.getUserId()));
    }

    @Deprecated
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询用户地址列表")
    public TableDataInfo<List<BizUserAddressVo>> list()
    {
        startPage();
        BizUserAddress bizUserAddress = new BizUserAddress();
        bizUserAddress.setUserId(SecurityUtils.getUserId());
        List<BizUserAddressVo> list = bizUserAddressService.selectBizUserAddressList(bizUserAddress);
        return getDataTable(list);
    }

    /**
     * 新增用户地址
     */
    @Deprecated
    @PostMapping("/app")
    @ApiOperation("[前台]新增用户地址")
    public R appAdd(@RequestBody BizUserAddress bizUserAddress)
    {
        bizUserAddress.setUserId(SecurityUtils.getUserId());
        return R.auto(bizUserAddressService.insertBizUserAddress(bizUserAddress));
    }

    /**
     * 修改用户地址
     */
    @Deprecated
    @PutMapping("/app")
    @ApiOperation("[前台]修改用户地址")
    public R appEdit(@RequestBody BizUserAddress bizUserAddress)
    {
        return R.auto(bizUserAddressService.updateBizUserAddress(bizUserAddress));
    }

    /**
     * 删除用户地址
     */
    @Deprecated
    @DeleteMapping("/app/{ids}")
    @ApiOperation("[前台]删除用户地址")
    public R appRemove(@PathVariable Long[] ids)
    {
        return R.auto(bizUserAddressService.deleteBizUserAddressByIds(ids));
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询用户地址列表
     */
    @RequiresPermissions("reima:address:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询用户地址列表")
    public TableDataInfo<List<BizUserAddressVo>> list(BizUserAddress bizUserAddress)
    {
        startPage();
        List<BizUserAddressVo> list = bizUserAddressService.selectBizUserAddressList(bizUserAddress);
        return getDataTable(list);
    }

    /**
     * 导出用户地址列表
     */
    @RequiresPermissions("reima:address:export")
    @Log(title = "用户地址", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出用户地址列表")
    public void export(HttpServletResponse response, BizUserAddress bizUserAddress)
    {
        List<BizUserAddressVo> list = bizUserAddressService.selectBizUserAddressList(bizUserAddress);
        ExcelUtil<BizUserAddressVo> util = new ExcelUtil<BizUserAddressVo>(BizUserAddressVo.class);
        util.exportExcel(response, list, "用户地址数据");
    }

    /**
     * 获取用户地址详细信息
     */
    @RequiresPermissions("reima:address:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取用户地址详细信息")
    public R<BizUserAddressVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserAddressService.selectBizUserAddressById(id));
    }

//    /**
//     * 新增用户地址
//     */
//    @RequiresPermissions("reima:address:add")
//    @Log(title = "用户地址", businessType = BusinessType.INSERT)
//    @PostMapping
//    @ApiOperation("[后台]新增用户地址")
//    public R add(@RequestBody BizUserAddress bizUserAddress)
//    {
//        return R.auto(bizUserAddressService.insertBizUserAddress(bizUserAddress));
//    }
//
//    /**
//     * 修改用户地址
//     */
//    @RequiresPermissions("reima:address:edit")
//    @Log(title = "用户地址", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperation("[后台]修改用户地址")
//    public R edit(@RequestBody BizUserAddress bizUserAddress)
//    {
//        return R.auto(bizUserAddressService.updateBizUserAddress(bizUserAddress));
//    }

    /**
     * 删除用户地址
     */
    @RequiresPermissions("reima:address:remove")
    @Log(title = "用户地址", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除用户地址")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizUserAddressService.deleteBizUserAddressByIds(ids));
    }
}
