package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizUserAccRecord;
import com.micro.reima.model.admin.BizUserAccRecordVo;
import com.micro.reima.model.vo.UserAccRecordVo;
import com.micro.reima.service.IBizUserAccRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 积分明细Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "积分明细")
@RestController
@RequestMapping("/acc/record")
public class BizUserAccRecordController extends BaseController
{
    @Autowired
    private IBizUserAccRecordService bizUserAccRecordService;

    // ---------------------------前台接口---------------------------//
    @GetMapping("/app/list")
    @ApiOperation("[前台]查询积分明细(需要登录)")
    public TableDataInfo<List<UserAccRecordVo>> appList(@ApiParam(value = "开始日期 yyyy-MM-dd", required = false) @RequestParam(defaultValue = "") String startTime,
        @ApiParam(value = "结束日期 yyyy-MM-dd", required = false) @RequestParam(defaultValue = "") String endTime)
    {
        startPage();
        List<UserAccRecordVo> list = bizUserAccRecordService.selectUserAccRecordVoByUserId(SecurityUtils.getUserId(),startTime,endTime);
        return getDataTable(list);
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询余额明细列表
     */
    @RequiresPermissions("reima:record:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询余额明细列表")
    public TableDataInfo<List<BizUserAccRecordVo>> list(BizUserAccRecordVo bizUserAccRecord)
    {
        startPage();
        List<BizUserAccRecordVo> list = bizUserAccRecordService.selectBizUserAccRecordList(bizUserAccRecord);
        return getDataTable(list);
    }

    /**
     * 导出余额明细列表
     */
    @RequiresPermissions("reima:record:export")
    @Log(title = "余额明细", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出余额明细列表")
    public void export(HttpServletResponse response, BizUserAccRecordVo bizUserAccRecord)
    {
        List<BizUserAccRecordVo> list = bizUserAccRecordService.selectBizUserAccRecordList(bizUserAccRecord);
        ExcelUtil<BizUserAccRecordVo> util = new ExcelUtil<BizUserAccRecordVo>(BizUserAccRecordVo.class);
        util.exportExcel(response, list, "余额明细数据");
    }

    /**
     * 获取余额明细详细信息
     */
    @RequiresPermissions("reima:record:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取余额明细详细信息")
    public R<BizUserAccRecordVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserAccRecordService.selectBizUserAccRecordById(id));
    }

    /**
     * 新增余额明细
     */
    @RequiresPermissions("reima:record:add")
    @Log(title = "余额明细", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增余额明细")
    public R add(@RequestBody BizUserAccRecord bizUserAccRecord)
    {
        return R.auto(bizUserAccRecordService.insertBizUserAccRecord(bizUserAccRecord));
    }

    /**
     * 修改余额明细
     */
    @RequiresPermissions("reima:record:edit")
    @Log(title = "余额明细", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改余额明细")
    public R edit(@RequestBody BizUserAccRecord bizUserAccRecord)
    {
        return R.auto(bizUserAccRecordService.updateBizUserAccRecord(bizUserAccRecord));
    }

    /**
     * 删除余额明细
     */
    @RequiresPermissions("reima:record:remove")
    @Log(title = "余额明细", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除余额明细")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizUserAccRecordService.deleteBizUserAccRecordByIds(ids));
    }
}
