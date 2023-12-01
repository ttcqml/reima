package com.micro.reima.service.impl;

import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizFeedbackMapper;
import com.micro.reima.domain.BizFeedback;
import com.micro.reima.service.IBizFeedbackService;

/**
 * 帮助反馈Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizFeedbackServiceImpl implements IBizFeedbackService 
{
    @Autowired
    private BizFeedbackMapper bizFeedbackMapper;

    /**
     * 查询帮助反馈
     * 
     * @param id 帮助反馈主键
     * @return 帮助反馈
     */
    @Override
    public BizFeedback selectBizFeedbackById(Long id)
    {
        return bizFeedbackMapper.selectBizFeedbackById(id);
    }

    /**
     * 查询帮助反馈列表
     * 
     * @param bizFeedback 帮助反馈
     * @return 帮助反馈
     */
    @Override
    public List<BizFeedback> selectBizFeedbackList(BizFeedback bizFeedback)
    {
        return bizFeedbackMapper.selectBizFeedbackList(bizFeedback);
    }

    /**
     * 新增帮助反馈
     * 
     * @param bizFeedback 帮助反馈
     * @return 结果
     */
    @Override
    public int insertBizFeedback(BizFeedback bizFeedback)
    {
        bizFeedback.setCreateTime(DateUtils.getNowDate());
        return bizFeedbackMapper.insertBizFeedback(bizFeedback);
    }

    /**
     * 修改帮助反馈
     * 
     * @param bizFeedback 帮助反馈
     * @return 结果
     */
    @Override
    public int updateBizFeedback(BizFeedback bizFeedback)
    {
        bizFeedback.setUpdateTime(DateUtils.getNowDate());
        return bizFeedbackMapper.updateBizFeedback(bizFeedback);
    }

    /**
     * 批量删除帮助反馈
     * 
     * @param ids 需要删除的帮助反馈主键
     * @return 结果
     */
    @Override
    public int deleteBizFeedbackByIds(Long[] ids)
    {
        return bizFeedbackMapper.deleteBizFeedbackByIds(ids);
    }

    /**
     * 删除帮助反馈信息
     * 
     * @param id 帮助反馈主键
     * @return 结果
     */
    @Override
    public int deleteBizFeedbackById(Long id)
    {
        return bizFeedbackMapper.deleteBizFeedbackById(id);
    }
}
