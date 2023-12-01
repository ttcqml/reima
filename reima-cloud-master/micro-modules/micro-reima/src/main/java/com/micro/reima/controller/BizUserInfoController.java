package com.micro.reima.controller;

import com.alibaba.fastjson.JSON;
import com.micro.common.core.domain.R;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.poi.ExcelUtil;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.log.annotation.Log;
import com.micro.common.log.enums.BusinessType;
import com.micro.common.security.annotation.RequiresPermissions;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.domain.BizOldUser;
import com.micro.reima.domain.BizUserChild;
import com.micro.reima.domain.BizUserInfo;
import com.micro.reima.domain.SysUserInfo;
import com.micro.reima.model.admin.BizUserInfoVo;
import com.micro.reima.model.bo.BizUserChildInfo;
import com.micro.reima.model.bo.BizUserInfoBo;
import com.micro.reima.model.vo.UserInfoVo;
import com.micro.reima.service.IBizUserChildService;
import com.micro.reima.service.IBizUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户Controller
 * 
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "用户信息")
@RestController
@RequestMapping("/user/info")
public class BizUserInfoController extends BaseController
{
    @Autowired
    private IBizUserInfoService bizUserInfoService;
    @Autowired
    private IBizUserChildService bizUserChildService;

    // ---------------------------前台接口---------------------------//
    @GetMapping(value = "/app/simple")
    @ApiOperation("[前台]获取个人信息(需要登录)")
    public R<UserInfoVo> simple()
    {
        Long userId = SecurityUtils.getUserId();
        logger.info("simple>>>>>>>userId:{}",userId);
        if(null == userId){
            return R.fail("请登录");
        }
        return R.ok(bizUserInfoService.selectUserInfoVoByUserId(userId));
    }

    @GetMapping(value = "/app/detail")
    @ApiOperation("[前台]获取个人详细信息(需要登录)")
    public R<BizUserInfoBo> detail()
    {
        Long userId = SecurityUtils.getUserId();
        if(null == userId){
            return R.fail("请登录");
        }
        logger.info("detail>>>>>>>userId:{},token:{}",userId,SecurityUtils.getToken());
        SysUserInfo sysUser = bizUserInfoService.selectSysUserById(userId);
        if(null == sysUser){
            return R.fail("用户信息不存在");
        }
        BizUserInfoBo vo = new BizUserInfoBo();
        vo.setUsername(sysUser.getUserName());
        vo.setSex(sysUser.getSex());
        vo.setPhonenumber(sysUser.getPhonenumber());
        BizUserInfo bizUserInfo = bizUserInfoService.selectBizUserInfoByUserId(userId);
        if(null == bizUserInfo){
            return R.fail("用户信息不存在");
        }
        vo.setId(bizUserInfo.getId());
        vo.setBirthday(null!=bizUserInfo.getBirthday()?DateUtils.parseDateToStr("yyyy-MM-dd",bizUserInfo.getBirthday()):null);
        vo.setChildNum(bizUserInfo.getChildNum());
        vo.setProvinceId(bizUserInfo.getProvinceId());
        vo.setCityId(bizUserInfo.getCityId());
        vo.setCreateTime(DateUtils.parseDateToStr("yyyy-MM-dd",sysUser.getCreateTime()));
        List<BizUserChild> bizUserChilds = bizUserChildService.selectBizUserChildsByUserId(userId);
        if(!CollectionUtils.isEmpty(bizUserChilds)){
            List<BizUserChildInfo> bizUserChildInfoBos = new ArrayList<>();
            for (BizUserChild child:bizUserChilds){
                BizUserChildInfo info = new BizUserChildInfo();
                info.setId(child.getId());
                info.setName(child.getName());
                info.setSex(child.getSex());
                info.setBirthday(null!=child.getBirthday()?DateUtils.parseDateToStr("yyyy-MM-dd",child.getBirthday()):null);
                bizUserChildInfoBos.add(info);
            }
            vo.setBizUserChildInfoBoList(bizUserChildInfoBos);
        }
        return R.ok(vo);
    }

    @PostMapping("/app/save")
    @ApiOperation("[前台]完善个人信息(需要登录)")
    public R save(@RequestBody BizUserInfoBo bizUserInfoBo)
    {
        Long userId = SecurityUtils.getUserId();
        if(null == userId){
            return R.fail("请登录");
        }
        logger.error(">>save info:{}", JSON.toJSONString(bizUserInfoBo));
        bizUserInfoService.saveBizUserInfo(bizUserInfoBo, userId);
        return R.ok();
    }

    @PostMapping("/app/bar")
    @ApiOperation("[前台]条形码")
    public R barCode(){
        Long userId = SecurityUtils.getUserId();
        if(null == userId){
            return R.fail("请登录");
        }
        return R.ok(bizUserInfoService.barCode(userId));
    }

    // ---------------------------后台接口---------------------------//
    /**
     * 查询用户列表
     */
    @RequiresPermissions("reima:info:list")
    @GetMapping("/list")
    @ApiOperation("[后台]查询用户列表")
    public TableDataInfo<List<BizUserInfoVo>> list(BizUserInfo bizUserInfo)
    {
        startPage();
        List<BizUserInfoVo> list = bizUserInfoService.selectBizUserInfoList(bizUserInfo);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @RequiresPermissions("reima:info:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ApiOperation("[后台]导出用户列表")
    public void export(HttpServletResponse response, BizUserInfo bizUserInfo)
    {
        List<BizUserInfoVo> list = bizUserInfoService.selectBizUserInfoList(bizUserInfo);
        ExcelUtil<BizUserInfoVo> util = new ExcelUtil<BizUserInfoVo>(BizUserInfoVo.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 导出老用户列表
     */
    @RequiresPermissions("reima:info:export")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/exportOld")
    @ApiOperation("[后台]导出老用户列表")
    public void exportOldUser(HttpServletResponse response, BizOldUser bizOldUser)
    {
        List<BizOldUser> list = bizUserInfoService.exportBizOldUserList();
        ExcelUtil<BizOldUser> util = new ExcelUtil<BizOldUser>(BizOldUser.class);
        util.exportExcel(response, list, "老用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @RequiresPermissions("reima:info:query")
    @GetMapping(value = "/{id}")
    @ApiOperation("[后台]获取用户详细信息")
    public R<BizUserInfoVo> getInfo(@PathVariable("id") Long id)
    {
        return R.ok(bizUserInfoService.selectBizUserInfoById(id));
    }

//    /**
//     * 新增用户
//     */
//    @RequiresPermissions("reima:info:add")
//    @Log(title = "用户", businessType = BusinessType.INSERT)
//    @PostMapping
//    @ApiOperation("[后台]新增用户")
//    public R add(@RequestBody BizUserInfo bizUserInfo)
//    {
//        return R.auto(bizUserInfoService.insertBizUserInfo(bizUserInfo));
//    }

//    /**
//     * 修改用户
//     */
//    @RequiresPermissions("reima:info:edit")
//    @Log(title = "用户", businessType = BusinessType.UPDATE)
//    @PutMapping
//    @ApiOperation("[后台]修改用户")
//    public R edit(@RequestBody BizUserInfo bizUserInfo)
//    {
//        return R.auto(bizUserInfoService.updateBizUserInfo(bizUserInfo));
//    }

//    /**
//     * 删除用户
//     */
//    @RequiresPermissions("reima:info:remove")
//    @Log(title = "用户", businessType = BusinessType.DELETE)
//    @DeleteMapping("/{ids}")
//    @ApiOperation("[后台]删除用户")
//    public R remove(@PathVariable Long[] ids)
//    {
//        return R.auto(bizUserInfoService.deleteBizUserInfoByIds(ids));
//    }
}
