package com.micro.reima.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.micro.common.core.utils.DateUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.reima.domain.BizShuyunSignInfo;
import com.micro.reima.mapper.BizShuyunSignInfoMapper;
import com.micro.reima.model.admin.MemberDto;
import com.micro.reima.model.bo.ShuYunPointChangeMsgForm;
import com.micro.reima.model.bo.UpTokenChildForm;
import com.micro.reima.service.IBizMessageService;
import com.micro.reima.service.IBizShuyunSignInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * 数云秘钥授权Service业务层处理
 * 
 * @author micro
 * @date 2022-01-08
 */
@Service
@Slf4j
public class BizShuyunSignInfoServiceImpl implements IBizShuyunSignInfoService 
{
    @Autowired
    private IBizMessageService bizMessageService;
    @Autowired
    private BizShuyunSignInfoMapper bizShuyunSignInfoMapper;

    /**
     * 数云Token开始更新
     * @param upTokenChildFormList
     */
    @Override
    public void upToken(List<UpTokenChildForm> upTokenChildFormList) {
        log.info("数云Token开始更新:" + JSONObject.toJSONString(upTokenChildFormList));
        for (Iterator<UpTokenChildForm> iterator = upTokenChildFormList.iterator(); iterator.hasNext(); ) {
            UpTokenChildForm next =  iterator.next();
            if (next.getAuthValue().equals("reima")) {
                BizShuyunSignInfo sysip = bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(1L);
                sysip.setAppId(next.getAppId());
                sysip.setToken(next.getAccessToken());
                bizShuyunSignInfoMapper.updateBizShuyunSignInfo(sysip);
                log.info("数云Token更新完成" + JSONObject.toJSONString(sysip));
            }
        }
    }

    @Override
    public int pointChangeNocie(ShuYunPointChangeMsgForm form) {
        MemberDto dto = bizShuyunSignInfoMapper.selectOpenIdByMemberId(form.getMemberNo());
        if(null == dto || StringUtils.isEmpty(dto.getOpenId())){
            return 0;
        }
        bizMessageService.sendPointChangeNotice(dto.getOpenId(),dto.getPhonenumber(),form.getNum()+"",form.getReason(),form.getBalance()+"",form.getRemark());
        return 1;
    }

    /**
     * 查询数云秘钥授权
     * 
     * @param id 数云秘钥授权主键
     * @return 数云秘钥授权
     */
    @Override
    public BizShuyunSignInfo selectBizShuyunSignInfoById(Long id)
    {
        return bizShuyunSignInfoMapper.selectBizShuyunSignInfoById(id);
    }

    /**
     * 查询数云秘钥授权列表
     * 
     * @param bizShuyunSignInfo 数云秘钥授权
     * @return 数云秘钥授权
     */
    @Override
    public List<BizShuyunSignInfo> selectBizShuyunSignInfoList(BizShuyunSignInfo bizShuyunSignInfo)
    {
        return bizShuyunSignInfoMapper.selectBizShuyunSignInfoList(bizShuyunSignInfo);
    }

    /**
     * 新增数云秘钥授权
     * 
     * @param bizShuyunSignInfo 数云秘钥授权
     * @return 结果
     */
    @Override
    public int insertBizShuyunSignInfo(BizShuyunSignInfo bizShuyunSignInfo)
    {
        bizShuyunSignInfo.setCreateTime(DateUtils.getNowDate());
        return bizShuyunSignInfoMapper.insertBizShuyunSignInfo(bizShuyunSignInfo);
    }

    /**
     * 修改数云秘钥授权
     * 
     * @param bizShuyunSignInfo 数云秘钥授权
     * @return 结果
     */
    @Override
    public int updateBizShuyunSignInfo(BizShuyunSignInfo bizShuyunSignInfo)
    {
        bizShuyunSignInfo.setUpdateTime(DateUtils.getNowDate());
        return bizShuyunSignInfoMapper.updateBizShuyunSignInfo(bizShuyunSignInfo);
    }

    /**
     * 批量删除数云秘钥授权
     * 
     * @param ids 需要删除的数云秘钥授权主键
     * @return 结果
     */
    @Override
    public int deleteBizShuyunSignInfoByIds(Long[] ids)
    {
        return bizShuyunSignInfoMapper.deleteBizShuyunSignInfoByIds(ids);
    }

    /**
     * 删除数云秘钥授权信息
     * 
     * @param id 数云秘钥授权主键
     * @return 结果
     */
    @Override
    public int deleteBizShuyunSignInfoById(Long id)
    {
        return bizShuyunSignInfoMapper.deleteBizShuyunSignInfoById(id);
    }
}
