package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizUserSignin;
import com.micro.reima.model.vo.BizUserSigninVo;
import com.micro.reima.model.vo.SimpleUserSignVo;
import com.micro.reima.service.IBizUserSigninService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 签到记录Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "签到记录")
@RestController
@RequestMapping("/signin")
public class BizUserSigninController extends BaseController
{
    @Autowired
    private IBizUserSigninService bizUserSigninService;

    // ---------------------------前台接口---------------------------//
    @PostMapping("/app/add")
    @ApiOperation("[前台]签到")
    public R appSignin()
    {
        boolean result = bizUserSigninService.appSignin(SecurityUtils.getUserId());
        return result?R.ok():R.fail("已签到");
    }

    @GetMapping("/app/list")
    @ApiOperation("[前台]查询签到记录(需要登录)")
    public R<List<SimpleUserSignVo>> appList(@ApiParam(value = "月份,默认当前月 yyyy-MM", required = false) @RequestParam(defaultValue = "") String month)
    {
        if(StringUtils.isEmpty(month)){
            month = DateUtils.parseDateToStr("yyyy-MM",DateUtils.getNowDate());
        }
        List<SimpleUserSignVo> list = bizUserSigninService.selectSimpleUserSignVoByUserIdAndMonth(SecurityUtils.getUserId(),month);
        return R.ok(list);
    }
    // ---------------------------后台接口---------------------------//
    /**
     * 查询签到记录列表
     */
    @RequiresPermissions("reima:signin:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询签到记录列表")
    public TableDataInfo<List<BizUserSigninVo>> list(BizUserSigninVo bizUserSignin)
    {
        startPage();
        List<BizUserSigninVo> list = bizUserSigninService.selectBizUserSigninVoList(bizUserSignin);
        return getDataTable(list);
    }

    /**
     * 导出签到记录列表
     */
    @RequiresPermissions("reima:signin:export")
    @Log(title = "签到记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出签到记录列表")
    public void export(HttpServletResponse response, BizUserSigninVo bizUserSignin)
    {
        List<BizUserSigninVo> list = bizUserSigninService.selectBizUserSigninVoList(bizUserSignin);
        ExcelUtil<BizUserSigninVo> util = new ExcelUtil<BizUserSigninVo>(BizUserSigninVo.class);
        util.exportExcel(response, list, "签到记录数据");
    }

    /**
     * 获取签到记录详细信息
     */
    @RequiresPermissions("reima:signin:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取签到记录详细信息")
    public R<BizUserSigninVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserSigninService.selectBizUserSigninVoById(id));
    }

//    /**
//     * 新增签到记录
//     */
//    @RequiresPermissions("reima:signin:add")
//    @Log(title = "签到记录", businessType = BusinessType.INSERT)
//    @PostMapping
//    @ApiOperation("[后台]新增签到记录")
//    public R add(@RequestBody BizUserSignin bizUserSignin)
//    {
//        return R.auto(bizUserSigninService.insertBizUserSignin(bizUserSignin));
//    }
//
//    /**
//     * 修改签到记录
//     */
//    @RequiresPermissions("reima:signin:edit")
//    @Log(title = "签到记录", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperation("[后台]修改签到记录")
//    public R edit(@RequestBody BizUserSignin bizUserSignin)
//    {
//        return R.auto(bizUserSigninService.updateBizUserSignin(bizUserSignin));
//    }
//
//    /**
//     * 删除签到记录
//     */
//    @RequiresPermissions("reima:signin:remove")
//    @Log(title = "签到记录", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    @ApiOperation("[后台]删除签到记录")
//    public R remove(@PathVariable Long[] ids)
//    {
//        return R.auto(bizUserSigninService.deleteBizUserSigninByIds(ids));
//    }
}
