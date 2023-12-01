package com.micro.reima.service.impl;

import com.micro.common.core.utils.StringUtils;
import com.micro.reima.model.admin.BizUserAccRecordVo;
import com.micro.reima.model.vo.UserAccRecordVo;
import java.util.Date;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizUserAccRecordMapper;
import com.micro.reima.domain.BizUserAccRecord;
import com.micro.reima.service.IBizUserAccRecordService;

/**
 * 余额明细Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizUserAccRecordServiceImpl implements IBizUserAccRecordService 
{
    @Autowired
    private BizUserAccRecordMapper bizUserAccRecordMapper;

    @Override
    public List<UserAccRecordVo> selectUserAccRecordVoByUserId(Long userId,String startTime,String endTime) {
        return bizUserAccRecordMapper.selectUserAccRecordVoByUserId(userId,startTime,endTime);
    }

    /**
     * 查询余额明细
     * 
     * @param id 余额明细主键
     * @return 余额明细
     */
    @Override
    public BizUserAccRecordVo selectBizUserAccRecordById(Long id)
    {
        return bizUserAccRecordMapper.selectBizUserAccRecordById(id);
    }

    /**
     * 查询余额明细列表
     * 
     * @param bizUserAccRecord 余额明细
     * @return 余额明细
     */
    @Override
    public List<BizUserAccRecordVo> selectBizUserAccRecordList(BizUserAccRecordVo bizUserAccRecord)
    {
        return bizUserAccRecordMapper.selectBizUserAccRecordList(bizUserAccRecord);
    }

    /**
     * 新增余额明细
     * 
     * @param bizUserAccRecord 余额明细
     * @return 结果
     */
    @Override
    public int insertBizUserAccRecord(BizUserAccRecord bizUserAccRecord)
    {
        bizUserAccRecord.setCreateTime(DateUtils.getNowDate());
        return bizUserAccRecordMapper.insertBizUserAccRecord(bizUserAccRecord);
    }

    /**
     * 修改余额明细
     * 
     * @param bizUserAccRecord 余额明细
     * @return 结果
     */
    @Override
    public int updateBizUserAccRecord(BizUserAccRecord bizUserAccRecord)
    {
        bizUserAccRecord.setUpdateTime(DateUtils.getNowDate());
        return bizUserAccRecordMapper.updateBizUserAccRecord(bizUserAccRecord);
    }

    /**
     * 批量删除余额明细
     * 
     * @param ids 需要删除的余额明细主键
     * @return 结果
     */
    @Override
    public int deleteBizUserAccRecordByIds(Long[] ids)
    {
        return bizUserAccRecordMapper.deleteBizUserAccRecordByIds(ids);
    }

    /**
     * 删除余额明细信息
     * 
     * @param id 余额明细主键
     * @return 结果
     */
    @Override
    public int deleteBizUserAccRecordById(Long id)
    {
        return bizUserAccRecordMapper.deleteBizUserAccRecordById(id);
    }
}
