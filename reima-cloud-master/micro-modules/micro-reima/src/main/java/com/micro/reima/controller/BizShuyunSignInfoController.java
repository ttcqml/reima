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
import com.micro.reima.domain.BizShuyunSignInfo;
import com.micro.reima.service.IBizShuyunSignInfoService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 数云秘钥授权Controller
 * 
 * @author micro
 * @date 2022-01-08
 */
@Api(tags = "数云秘钥授权")
@RestController
@RequestMapping("/shuyun/info")
public class BizShuyunSignInfoController extends BaseController
{
    @Autowired
    private IBizShuyunSignInfoService bizShuyunSignInfoService;

    /**
     * 查询数云秘钥授权列表
     */
    @RequiresPermissions("reima:info:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询数云秘钥授权列表")
    public TableDataInfo<List<BizShuyunSignInfo>> list(BizShuyunSignInfo bizShuyunSignInfo)
    {
        startPage();
        List<BizShuyunSignInfo> list = bizShuyunSignInfoService.selectBizShuyunSignInfoList(bizShuyunSignInfo);
        return getDataTable(list);
    }

    /**
     * 导出数云秘钥授权列表
     */
    @RequiresPermissions("reima:info:export")
    @Log(title = "数云秘钥授权", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出数云秘钥授权列表")
    public void export(HttpServletResponse response, BizShuyunSignInfo bizShuyunSignInfo)
    {
        List<BizShuyunSignInfo> list = bizShuyunSignInfoService.selectBizShuyunSignInfoList(bizShuyunSignInfo);
        ExcelUtil<BizShuyunSignInfo> util = new ExcelUtil<BizShuyunSignInfo>(BizShuyunSignInfo.class);
        util.exportExcel(response, list, "数云秘钥授权数据");
    }

    /**
     * 获取数云秘钥授权详细信息
     */
    @RequiresPermissions("reima:info:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取数云秘钥授权详细信息")
    public R<BizShuyunSignInfo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizShuyunSignInfoService.selectBizShuyunSignInfoById(id));
    }

    /**
     * 新增数云秘钥授权
     */
    @RequiresPermissions("reima:info:add")
    @Log(title = "数云秘钥授权", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增数云秘钥授权")
    public R add(@RequestBody BizShuyunSignInfo bizShuyunSignInfo)
    {
        return R.auto(bizShuyunSignInfoService.insertBizShuyunSignInfo(bizShuyunSignInfo));
    }

    /**
     * 修改数云秘钥授权
     */
    @RequiresPermissions("reima:info:edit")
    @Log(title = "数云秘钥授权", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改数云秘钥授权")
    public R edit(@RequestBody BizShuyunSignInfo bizShuyunSignInfo)
    {
        return R.auto(bizShuyunSignInfoService.updateBizShuyunSignInfo(bizShuyunSignInfo));
    }

    /**
     * 删除数云秘钥授权
     */
    @RequiresPermissions("reima:info:remove")
    @Log(title = "数云秘钥授权", businessType = BusinessType.DELETE)
	  @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除数云秘钥授权")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizShuyunSignInfoService.deleteBizShuyunSignInfoByIds(ids));
    }
}
