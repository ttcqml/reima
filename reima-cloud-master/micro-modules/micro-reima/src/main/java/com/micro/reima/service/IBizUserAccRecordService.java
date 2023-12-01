package com.micro.reima.service;

import com.micro.reima.model.admin.BizUserAccRecordVo;
import com.micro.reima.model.vo.UserAccRecordVo;
import java.util.List;
import com.micro.reima.domain.BizUserAccRecord;

/**
 * 余额明细Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizUserAccRecordService 
{

    public List<UserAccRecordVo> selectUserAccRecordVoByUserId(Long userId,String startTime,String endTime);

    /**
     * 查询余额明细
     * 
     * @param id 余额明细主键
     * @return 余额明细
     */
    public BizUserAccRecordVo selectBizUserAccRecordById(Long id);

    /**
     * 查询余额明细列表
     * 
     * @param bizUserAccRecord 余额明细
     * @return 余额明细集合
     */
    public List<BizUserAccRecordVo> selectBizUserAccRecordList(BizUserAccRecordVo bizUserAccRecord);

    /**
     * 新增余额明细
     * 
     * @param bizUserAccRecord 余额明细
     * @return 结果
     */
    public int insertBizUserAccRecord(BizUserAccRecord bizUserAccRecord);

    /**
     * 修改余额明细
     * 
     * @param bizUserAccRecord 余额明细
     * @return 结果
     */
    public int updateBizUserAccRecord(BizUserAccRecord bizUserAccRecord);

    /**
     * 批量删除余额明细
     * 
     * @param ids 需要删除的余额明细主键集合
     * @return 结果
     */
    public int deleteBizUserAccRecordByIds(Long[] ids);

    /**
     * 删除余额明细信息
     * 
     * @param id 余额明细主键
     * @return 结果
     */
    public int deleteBizUserAccRecordById(Long id);
}
