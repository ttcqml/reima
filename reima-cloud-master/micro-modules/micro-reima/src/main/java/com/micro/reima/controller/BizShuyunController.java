package com.micro.reima.controller;


import com.alibaba.fastjson.JSONObject;
import com.micro.common.core.domain.R;
import com.micro.common.core.web.controller.BaseController;
import com.micro.reima.model.bo.ShuYunPointChangeMsgForm;
import com.micro.reima.model.bo.UpTokenChildForm;
import com.micro.reima.service.IBizMessageService;
import com.micro.reima.service.IBizShuyunSignInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * BizShuyunController
 * @author micro
 * @date 2022-02-14
 */
@Api(tags = "提供给数云接口")
@RestController
@RequestMapping("/shuyun")
@Slf4j
public class BizShuyunController extends BaseController {

    @Autowired
    private IBizShuyunSignInfoService bizShuyunSignInfoService;

    /**
     * 更新数云Token
     */
    @RequestMapping("/callback/upToken")
    public Object shuyunSyncMemberUpToken(@RequestBody List<UpTokenChildForm> form){
        bizShuyunSignInfoService.upToken(form);
        return R.ok();
    }


//    @PostMapping("/notice/pointChange")
//    public R pointChangeNotice(@RequestBody ShuYunPointChangeMsgForm form){
//        return R.ok(bizShuyunSignInfoService.pointChangeNocie(form));
//    }

    /**
     * 积分变更通知
     */
    @RequestMapping(value="/notice/pointChange", method= RequestMethod.POST, produces="application/json;charset=utf-8;")
    public Object syncMemberPoint(@RequestBody String params){
        logger.info("接受数云积分变更通知："+ params);
        JSONObject syncMemJson= JSONObject.parseObject(params);
        ShuYunPointChangeMsgForm shuYunPointChangeMsgForm = new ShuYunPointChangeMsgForm();
        shuYunPointChangeMsgForm.setBalance(syncMemJson.getInteger("point"));
        shuYunPointChangeMsgForm.setNum(syncMemJson.getInteger("point")-syncMemJson.getInteger("beforePoint"));
        shuYunPointChangeMsgForm.setRemark(syncMemJson.getString("source"));
        shuYunPointChangeMsgForm.setMemberNo(syncMemJson.getString("memberId"));
        shuYunPointChangeMsgForm.setReason(syncMemJson.getString("desc"));
        return R.ok(bizShuyunSignInfoService.pointChangeNocie(shuYunPointChangeMsgForm));
    }

}
