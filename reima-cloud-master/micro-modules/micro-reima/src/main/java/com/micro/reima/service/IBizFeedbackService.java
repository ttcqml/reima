package com.micro.reima.service;

import java.util.List;
import com.micro.reima.domain.BizFeedback;

/**
 * 帮助反馈Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizFeedbackService 
{
    /**
     * 查询帮助反馈
     * 
     * @param id 帮助反馈主键
     * @return 帮助反馈
     */
    public BizFeedback selectBizFeedbackById(Long id);

    /**
     * 查询帮助反馈列表
     * 
     * @param bizFeedback 帮助反馈
     * @return 帮助反馈集合
     */
    public List<BizFeedback> selectBizFeedbackList(BizFeedback bizFeedback);

    /**
     * 新增帮助反馈
     * 
     * @param bizFeedback 帮助反馈
     * @return 结果
     */
    public int insertBizFeedback(BizFeedback bizFeedback);

    /**
     * 修改帮助反馈
     * 
     * @param bizFeedback 帮助反馈
     * @return 结果
     */
    public int updateBizFeedback(BizFeedback bizFeedback);

    /**
     * 批量删除帮助反馈
     * 
     * @param ids 需要删除的帮助反馈主键集合
     * @return 结果
     */
    public int deleteBizFeedbackByIds(Long[] ids);

    /**
     * 删除帮助反馈信息
     * 
     * @param id 帮助反馈主键
     * @return 结果
     */
    public int deleteBizFeedbackById(Long id);
}
