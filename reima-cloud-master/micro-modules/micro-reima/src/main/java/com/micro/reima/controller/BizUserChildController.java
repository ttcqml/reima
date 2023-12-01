package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.reima.model.admin.BizUserChildVo;
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
import com.micro.reima.domain.BizUserChild;
import com.micro.reima.service.IBizUserChildService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 用户孩子Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "用户孩子")
@RestController
@RequestMapping("/user/child")
public class BizUserChildController extends BaseController
{
    @Autowired
    private IBizUserChildService bizUserChildService;
    // ---------------------------前台接口---------------------------//

    // ---------------------------后台接口---------------------------//
    /**
     * 查询用户孩子列表
     */
    @RequiresPermissions("reima:child:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询用户孩子列表")
    public TableDataInfo<List<BizUserChildVo>> list(BizUserChildVo bizUserChild)
    {
        startPage();
        List<BizUserChildVo> list = bizUserChildService.selectBizUserChildList(bizUserChild);
        return getDataTable(list);
    }

    /**
     * 导出用户孩子列表
     */
    @RequiresPermissions("reima:child:export")
    @Log(title = "用户孩子", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出用户孩子列表")
    public void export(HttpServletResponse response, BizUserChildVo bizUserChild)
    {
        List<BizUserChildVo> list = bizUserChildService.selectBizUserChildList(bizUserChild);
        ExcelUtil<BizUserChildVo> util = new ExcelUtil<BizUserChildVo>(BizUserChildVo.class);
        util.exportExcel(response, list, "用户孩子数据");
    }

    /**
     * 获取用户孩子详细信息
     */
    @RequiresPermissions("reima:child:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取用户孩子详细信息")
    public R<BizUserChildVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserChildService.selectBizUserChildById(id));
    }

    /**
     * 新增用户孩子
     */
    @RequiresPermissions("reima:child:add")
    @Log(title = "用户孩子", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增用户孩子")
    public R add(@RequestBody BizUserChild bizUserChild)
    {
        return R.auto(bizUserChildService.insertBizUserChild(bizUserChild));
    }

    /**
     * 修改用户孩子
     */
    @RequiresPermissions("reima:child:edit")
    @Log(title = "用户孩子", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改用户孩子")
    public R edit(@RequestBody BizUserChild bizUserChild)
    {
        return R.auto(bizUserChildService.updateBizUserChild(bizUserChild));
    }

    /**
     * 删除用户孩子
     */
    @RequiresPermissions("reima:child:remove")
    @Log(title = "用户孩子", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除用户孩子")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizUserChildService.deleteBizUserChildByIds(ids));
    }
}
