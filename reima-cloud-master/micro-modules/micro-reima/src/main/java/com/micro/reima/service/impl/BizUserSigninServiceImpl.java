package com.micro.reima.service.impl;

import com.micro.common.core.enums.AccScene;
import com.micro.reima.mapper.BizUserInfoMapper;
import com.micro.reima.model.vo.BizUserSigninVo;
import com.micro.reima.model.vo.SimpleUserSignVo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import com.micro.reima.service.IBizUserAccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizUserSigninMapper;
import com.micro.reima.domain.BizUserSignin;
import com.micro.reima.service.IBizUserSigninService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 签到记录Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizUserSigninServiceImpl implements IBizUserSigninService 
{
    @Autowired
    private IBizUserAccService bizUserAccService;

    @Autowired
    private BizUserInfoMapper bizUserInfoMapper;
    @Autowired
    private BizUserSigninMapper bizUserSigninMapper;

    @Override
    @Transactional
    public boolean appSignin(Long userId) {
        String signAt = DateUtils.parseDateToStr("yyyy-MM-dd",DateUtils.getNowDate());
        SimpleUserSignVo vo = bizUserSigninMapper.selectSimpleUserSignVoByUserIdAndSignAt(userId,signAt);
        if(null == vo){
            BizUserSignin signin = new BizUserSignin();
            signin.setUserId(userId);
            signin.setSignAt(DateUtils.getNowDate());
            signin.setDelFlag("0");
            signin.setCreateTime(DateUtils.getNowDate());
            signin.setUpdateTime(DateUtils.getNowDate());
            signin.setCreateBy("system");
            signin.setUpdateBy("system");
            bizUserSigninMapper.insertBizUserSignin(signin);
            String val = bizUserInfoMapper.getValByParamCode("sign.points");
            if(null != val || !"0" .equals(val)){
                bizUserAccService.addUserAccBalance(userId,new BigDecimal(val), AccScene.签到.name(),true);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<SimpleUserSignVo> selectSimpleUserSignVoByUserIdAndMonth(Long userId,
        String month) {
        return bizUserSigninMapper.selectSimpleUserSignVoByUserIdAndMonth(userId,month);
    }

    @Override
    public List<BizUserSigninVo> selectBizUserSigninVoList(BizUserSigninVo bizUserSignin) {
        return bizUserSigninMapper.selectBizUserSigninVoList(bizUserSignin);
    }

    @Override
    public BizUserSigninVo selectBizUserSigninVoById(Long id) {
        return bizUserSigninMapper.selectBizUserSigninVoById(id);
    }

    /**
     * 查询签到记录
     * 
     * @param id 签到记录主键
     * @return 签到记录
     */
    @Override
    public BizUserSignin selectBizUserSigninById(Long id)
    {
        return bizUserSigninMapper.selectBizUserSigninById(id);
    }

    /**
     * 查询签到记录列表
     * 
     * @param bizUserSignin 签到记录
     * @return 签到记录
     */
    @Override
    public List<BizUserSignin> selectBizUserSigninList(BizUserSignin bizUserSignin)
    {
        return bizUserSigninMapper.selectBizUserSigninList(bizUserSignin);
    }

    /**
     * 新增签到记录
     * 
     * @param bizUserSignin 签到记录
     * @return 结果
     */
    @Override
    public int insertBizUserSignin(BizUserSignin bizUserSignin)
    {
        bizUserSignin.setCreateTime(DateUtils.getNowDate());
        return bizUserSigninMapper.insertBizUserSignin(bizUserSignin);
    }

    /**
     * 修改签到记录
     * 
     * @param bizUserSignin 签到记录
     * @return 结果
     */
    @Override
    public int updateBizUserSignin(BizUserSignin bizUserSignin)
    {
        bizUserSignin.setUpdateTime(DateUtils.getNowDate());
        return bizUserSigninMapper.updateBizUserSignin(bizUserSignin);
    }

    /**
     * 批量删除签到记录
     * 
     * @param ids 需要删除的签到记录主键
     * @return 结果
     */
    @Override
    public int deleteBizUserSigninByIds(Long[] ids)
    {
        return bizUserSigninMapper.deleteBizUserSigninByIds(ids);
    }

    /**
     * 删除签到记录信息
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    @Override
    public int deleteBizUserSigninById(Long id)
    {
        return bizUserSigninMapper.deleteBizUserSigninById(id);
    }
}
