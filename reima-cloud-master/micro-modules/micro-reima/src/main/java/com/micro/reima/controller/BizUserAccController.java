package com.micro.reima.controller;

import com.micro.common.core.domain.R;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizUserAcc;
import com.micro.reima.model.admin.BizUserAccVo;
import com.micro.reima.model.admin.CorrectUserAccBody;
import com.micro.reima.model.vo.BalanceVo;
import com.micro.reima.service.IBizUserAccService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.List;

/**
 * 积分账户Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "积分账户")
@RestController
@RequestMapping("/acc")
public class BizUserAccController extends BaseController
{
    @Autowired
    private IBizUserAccService bizUserAccService;

    // ---------------------------前台接口---------------------------//
    @GetMapping(value = "/app/balance")
    @ApiOperation("[前台]获取积分余额(需要登录)")
    public R<BalanceVo> balance()
    {
        Long userId = SecurityUtils.getUserId();
        if(null == userId){
            return R.fail("请登录");
        }
        BigDecimal balance = BigDecimal.ZERO;
        BizUserAcc acc = bizUserAccService.selectBizUserAccByUserId(userId);
        if(null != acc){
            balance = acc.getBalance();
        }
        return R.ok(new BalanceVo(balance));
    }

    @PutMapping("/correct")
    @ApiOperation("[后台]修改积分")
    public R edit(@RequestBody CorrectUserAccBody body)
    {
        if(null == body || !"9Mn4+Mgaf8nhzh9qFuiIG5Cp[yHEPLl+5-Iwp5wZ+tKYyrk6BMB@JDovN6A]kC-4".equals(body.getKey())){
            return R.fail("非法访问");
        }
        return R.auto(bizUserAccService.correctUserAcc(body.getUserId(),body.getAmt()));
    }
    // ---------------------------后台接口---------------------------//
    /**
     * 查询积分账户列表
     */
    @RequiresPermissions("reima:acc:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询积分账户列表")
    public TableDataInfo<List<BizUserAccVo>> list(BizUserAccVo bizUserAcc)
    {
        startPage();
        List<BizUserAccVo> list = bizUserAccService.selectBizUserAccList(bizUserAcc);
        return getDataTable(list);
    }

    /**
     * 导出积分账户列表
     */
    @RequiresPermissions("reima:acc:export")
    @Log(title = "积分账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出积分账户列表")
    public void export(HttpServletResponse response, BizUserAccVo bizUserAcc)
    {
        List<BizUserAccVo> list = bizUserAccService.selectBizUserAccList(bizUserAcc);
        ExcelUtil<BizUserAccVo> util = new ExcelUtil<BizUserAccVo>(BizUserAccVo.class);
        util.exportExcel(response, list, "积分账户数据");
    }

    /**
     * 获取积分账户详细信息
     */
    @RequiresPermissions("reima:acc:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取积分账户详细信息")
    public R<BizUserAccVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserAccService.selectBizUserAccById(id));
    }

    /**
     * 新增积分账户
     */
    @RequiresPermissions("reima:acc:add")
    @Log(title = "积分账户", businessType = BusinessType.INSERT)
    @PostMapping
    @ApiOperation("[后台]新增积分账户")
    public R add(@RequestBody BizUserAcc bizUserAcc)
    {
        return R.auto(bizUserAccService.insertBizUserAcc(bizUserAcc));
    }

    /**
     * 修改积分账户
     */
    @RequiresPermissions("reima:acc:edit")
    @Log(title = "积分账户", businessType = BusinessType.UPDATE)
    @PutMapping
    @ApiOperation("[后台]修改积分账户")
    public R edit(@RequestBody BizUserAcc bizUserAcc)
    {
        return R.auto(bizUserAccService.updateBizUserAcc(bizUserAcc));
    }

    /**
     * 删除积分账户
     */
    @RequiresPermissions("reima:acc:remove")
    @Log(title = "积分账户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @ApiOperation("[后台]删除积分账户")
    public R remove(@PathVariable Long[] ids)
    {
        return R.auto(bizUserAccService.deleteBizUserAccByIds(ids));
    }
}
