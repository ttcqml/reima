package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.reima.domain.BizProduct;
import com.micro.reima.model.vo.SimpleProductVo;
import com.micro.reima.service.IBizProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 商品信息Controller
 * 
 * @author micro
 * @date 2021-12-25
 */
@Api(tags = "商品信息")
@RestController
@RequestMapping("/product")
public class BizProductController extends BaseController
{
    @Autowired
    private IBizProductService bizProductService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询商品列表(无需登录)")
    public TableDataInfo<List<SimpleProductVo>> appList()
    {
        startPage();
        List<SimpleProductVo> list = bizProductService.appList();
        return getDataTable(list);
    }

    @GetMapping("/app/{id}")
    @ApiOperation("[前台]查询商品详情(无需登录)")
    public R<BizProduct> appDetail(@PathVariable("id") Long id)
    {
        return R.ok(bizProductService.selectBizProductById(id));
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询商品信息列表
     */
    @RequiresPermissions("reima:product:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询商品信息列表")
    public TableDataInfo<List<BizProduct>> list(BizProduct bizProduct)
    {
        startPage();
        List<BizProduct> list = bizProductService.selectBizProductList(bizProduct);
        return getDataTable(list);
    }

    /**
     * 导出商品信息列表
     */
    @RequiresPermissions("reima:product:export")
    @Log(title = "商品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出商品信息列表")
    public void export(HttpServletResponse response, BizProduct bizProduct)
    {
        List<BizProduct> list = bizProductService.selectBizProductList(bizProduct);
        ExcelUtil<BizProduct> util = new ExcelUtil<BizProduct>(BizProduct.class);
        util.exportExcel(response, list, "商品信息数据");
    }

    /**
     * 获取商品信息详细信息
     */
    @RequiresPermissions("reima:product:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取商品信息详细信息")
    public R<BizProduct> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizProductService.selectBizProductById(id));
    }

    /**
     * 新增商品信息
     */
    @RequiresPermissions("reima:product:add")
    @Log(title = "商品信息", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增商品信息")
    public R add(@RequestBody BizProduct bizProduct)
    {
        return R.auto(bizProductService.insertBizProduct(bizProduct));
    }

    /**
     * 修改商品信息
     */
    @RequiresPermissions("reima:product:edit")
    @Log(title = "商品信息", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改商品信息")
    public R edit(@RequestBody BizProduct bizProduct)
    {
        return R.auto(bizProductService.updateBizProduct(bizProduct));
    }

    /**
     * 删除商品信息
     */
    @RequiresPermissions("reima:product:remove")
    @Log(title = "商品信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除商品信息")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizProductService.deleteBizProductByIds(ids));
    }
}
