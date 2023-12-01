package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.reima.domain.BizStore;
import com.micro.reima.model.vo.BizStoreExport;
import com.micro.reima.model.vo.SimpleProductVo;
import com.micro.reima.service.IBizStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * 领取门店Controller
 * 
 * @author micro
 * @date 2022-10-06
 */
@Api(tags = "领取门店")
@RestController
@RequestMapping("/store")
public class BizStoreController extends BaseController
{
    @Autowired
    private IBizStoreService bizStoreService;

    // ---------------------------前台接口---------------------------//

    // 根据商品主键查询门店所在城市列表
    @GetMapping("/app/cityList/{productId}")
    @ApiOperation("[前台-二期]根据商品主键查询门店所在城市列表(无需登录)")
    public R<List<String>> appCityList(@PathVariable("productId") Long productId)
    {
        return R.ok(bizStoreService.selectStoreCityList(productId));
    }

    // 根据商品主键和城市名查询门店列表
    @GetMapping("/app/cityList")
    @ApiOperation("[前台-二期]根据商品主键和城市名查询门店列表(无需登录)")
    public R<List<BizStore>> appStoreList(BizStore bizStore)
    {
        return R.ok(bizStoreService.selectBizStoreList(bizStore));
    }

    /**
     * 查询领取门店列表
     */
    @RequiresPermissions("forge:store:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询领取门店列表")
    public TableDataInfo<List<BizStore>> list(BizStore bizStore)
    {
        startPage();
        List<BizStore> list = bizStoreService.selectBizStoreList(bizStore);
        return getDataTable(list);
    }

    @ApiOperation("导入")
    @RequiresPermissions("forge:store:export")
    @Log(title = "导入", businessType = BusinessType.INSERT)
    @PostMapping("/importFromExcel/{productId}")
    public R importFromExcel(@PathVariable("productId") Long productId,MultipartFile file) throws Exception {
        ExcelUtil<BizStore> util = new ExcelUtil<BizStore>(BizStore.class);
        List<BizStore> list = util.importExcel(file.getInputStream());
        if(CollectionUtils.isEmpty(list)){
            return R.fail("上传Excel为空");
        }

        return R.auto(bizStoreService.importFromExcel(productId,list));
    }

    /**
     * 导出领取门店列表
     */
    @RequiresPermissions("forge:store:export")
    @Log(title = "领取门店", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出领取门店列表")
    public void export(HttpServletResponse response, BizStore bizStore)
    {
        List<BizStore> list = bizStoreService.selectBizStoreList(bizStore);
        List<BizStoreExport> bizStoreExports = new ArrayList<>();
        for (BizStore item:list){
            BizStoreExport bizStoreExport = new BizStoreExport();
            bizStoreExport.setCity(item.getCity());
            bizStoreExport.setName(item.getName());
            bizStoreExport.setAddr(item.getAddr());
            bizStoreExports.add(bizStoreExport);
        }
        ExcelUtil<BizStoreExport> util = new ExcelUtil<BizStoreExport>(BizStoreExport.class);
        util.exportExcel(response, bizStoreExports, "领取门店数据");
    }

    /**
     * 获取领取门店详细信息
     */
    @RequiresPermissions("forge:store:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取领取门店详细信息")
    public R<BizStore> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizStoreService.selectBizStoreById(id));
    }

    /**
     * 新增领取门店
     */
    @RequiresPermissions("forge:store:add")
    @Log(title = "领取门店", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增领取门店")
    public R add(@RequestBody BizStore bizStore)
    {
        return R.auto(bizStoreService.insertBizStore(bizStore));
    }

    /**
     * 修改领取门店
     */
    @RequiresPermissions("forge:store:edit")
    @Log(title = "领取门店", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改领取门店")
    public R edit(@RequestBody BizStore bizStore)
    {
        return R.auto(bizStoreService.updateBizStore(bizStore));
    }

    /**
     * 删除领取门店
     */
    @RequiresPermissions("forge:store:remove")
    @Log(title = "领取门店", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除领取门店")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizStoreService.deleteBizStoreByIds(ids));
    }
}
