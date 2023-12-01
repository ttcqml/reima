package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizExchangeRecord;
import com.micro.reima.model.admin.BizExchangeRecordVo;
import com.micro.reima.model.admin.ExpressImportVo;
import com.micro.reima.model.bo.ExchangeBody;
import com.micro.reima.model.vo.ExCouponVo;
import com.micro.reima.model.vo.ExProductVo;
import com.micro.reima.service.IBizExchangeRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 兑换历史Controller
 * 
 * @author micro
 * @date 2021-12-25
 */
@Api(tags = "兑换历史")
@RestController
@RequestMapping("/exchange/record")
public class BizExchangeRecordController extends BaseController
{
    @Autowired
    private IBizExchangeRecordService bizExchangeRecordService;

    // ---------------------------前台接口---------------------------//

    @PostMapping("/app/exchange")
    @ApiOperation("[前台]兑换(需要登录)")
    public R save(@RequestBody ExchangeBody body)
    {
        Long userId = SecurityUtils.getUserId();
        if(null == userId){
            return R.fail("请登录");
        }
        bizExchangeRecordService.exchange(body,userId);
        return R.ok();
    }

    @GetMapping("/app/exproduct/list")
    @ApiOperation("[前台]查询兑换商品历史(需要登录)")
    public TableDataInfo<List<ExProductVo>> appExproductList()
    {
        startPage();
        List<ExProductVo> list = bizExchangeRecordService.exProductList(SecurityUtils.getUserId());
        return getDataTable(list);
    }

    @GetMapping("/app/excoupon/list")
    @ApiOperation("[前台]查询兑换优惠券历史(需要登录)")
    public TableDataInfo<List<ExCouponVo>> appExcouponList()
    {
        startPage();
        List<ExCouponVo> list = bizExchangeRecordService.exCouponList(SecurityUtils.getUserId());
        return getDataTable(list);
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询兑换历史列表
     */
    @RequiresPermissions("reima:record:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询兑换历史列表")
    public TableDataInfo<List<BizExchangeRecordVo>> list(BizExchangeRecordVo bizExchangeRecordVo)
    {
        startPage();
        List<BizExchangeRecordVo> list = bizExchangeRecordService.selectBizExchangeRecordList(bizExchangeRecordVo);
        return getDataTable(list);
    }

    /**
     * 导出兑换历史列表
     */
    @RequiresPermissions("reima:record:export")
    @Log(title = "兑换历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出兑换历史列表")
    public void export(HttpServletResponse response, BizExchangeRecordVo bizExchangeRecord)
    {
        List<BizExchangeRecordVo> list = bizExchangeRecordService.selectBizExchangeRecordList(bizExchangeRecord);
        ExcelUtil<BizExchangeRecordVo> util = new ExcelUtil<BizExchangeRecordVo>(BizExchangeRecordVo.class);
        util.exportExcel(response, list, "兑换历史数据");
    }

    @ApiOperation("导入快递信息")
    @RequiresPermissions("reima:record:edit")
    @Log(title = "导入快递信息", businessType = BusinessType.INSERT)
    @PostMapping("/importFromExcel")
    public R importFromExcel(MultipartFile file) throws Exception {
        ExcelUtil<ExpressImportVo> util = new ExcelUtil<ExpressImportVo>(ExpressImportVo.class);
        List<ExpressImportVo> list = util.importExcel(file.getInputStream());
        if(CollectionUtils.isEmpty(list)){
            return R.fail("导入数据不能为空");
        }
        for (ExpressImportVo vo:list){
            if(StringUtils.isNotEmpty(vo.getOrderSn())){
                bizExchangeRecordService.updateExpressInfo(vo.getOrderSn(),vo.getExpress(),vo.getExpressNum());
            }
        }
        return R.ok();
    }

    /**
     * 获取兑换历史详细信息
     */
    @RequiresPermissions("reima:record:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取兑换历史详细信息")
    public R<BizExchangeRecordVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizExchangeRecordService.selectBizExchangeRecordById(id));
    }

    /**
     * 新增兑换历史
     */
    @RequiresPermissions("reima:record:add")
    @Log(title = "兑换历史", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增兑换历史")
    public R add(@RequestBody BizExchangeRecord bizExchangeRecord)
    {
        return R.auto(bizExchangeRecordService.insertBizExchangeRecord(bizExchangeRecord));
    }

    /**
     * 修改兑换历史
     */
    @RequiresPermissions("reima:record:edit")
    @Log(title = "兑换历史", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改兑换历史")
    public R edit(@RequestBody BizExchangeRecord bizExchangeRecord)
    {
        return R.auto(bizExchangeRecordService.updateBizExchangeRecord(bizExchangeRecord));
    }

    /**
     * 删除兑换历史
     */
    @RequiresPermissions("reima:record:remove")
    @Log(title = "兑换历史", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除兑换历史")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizExchangeRecordService.deleteBizExchangeRecordByIds(ids));
    }
}
