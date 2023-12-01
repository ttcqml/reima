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
import com.micro.reima.domain.BizFeedback;
import com.micro.reima.service.IBizFeedbackService;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.domain.AjaxResult;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.page.TableDataInfo;

/**
 * 帮助反馈Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "反馈留言")
@RestController
@Deprecated
@RequestMapping("/feedback")
public class BizFeedbackController extends BaseController
{
    @Autowired
    private IBizFeedbackService bizFeedbackService;

    // ---------------------------前台接口---------------------------//

    // ---------------------------后台接口---------------------------//
    /**
     * 查询帮助反馈列表
     */
    @RequiresPermissions("reima:feedback:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询帮助反馈列表")
    public TableDataInfo<List<BizFeedback>> list(BizFeedback bizFeedback)
    {
        startPage();
        List<BizFeedback> list = bizFeedbackService.selectBizFeedbackList(bizFeedback);
        return getDataTable(list);
    }

    /**
     * 导出帮助反馈列表
     */
    @RequiresPermissions("reima:feedback:export")
    @Log(title = "帮助反馈", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出帮助反馈列表")
    public void export(HttpServletResponse response, BizFeedback bizFeedback)
    {
        List<BizFeedback> list = bizFeedbackService.selectBizFeedbackList(bizFeedback);
        ExcelUtil<BizFeedback> util = new ExcelUtil<BizFeedback>(BizFeedback.class);
        util.exportExcel(response, list, "帮助反馈数据");
    }

    /**
     * 获取帮助反馈详细信息
     */
    @RequiresPermissions("reima:feedback:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取帮助反馈详细信息")
    public R<BizFeedback> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizFeedbackService.selectBizFeedbackById(id));
    }

    /**
     * 新增帮助反馈
     */
    @RequiresPermissions("reima:feedback:add")
    @Log(title = "帮助反馈", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增帮助反馈")
    public R add(@RequestBody BizFeedback bizFeedback)
    {
        return R.auto(bizFeedbackService.insertBizFeedback(bizFeedback));
    }

    /**
     * 修改帮助反馈
     */
    @RequiresPermissions("reima:feedback:edit")
    @Log(title = "帮助反馈", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改帮助反馈")
    public R edit(@RequestBody BizFeedback bizFeedback)
    {
        return R.auto(bizFeedbackService.updateBizFeedback(bizFeedback));
    }

    /**
     * 删除帮助反馈
     */
    @RequiresPermissions("reima:feedback:remove")
    @Log(title = "帮助反馈", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除帮助反馈")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizFeedbackService.deleteBizFeedbackByIds(ids));
    }
}
