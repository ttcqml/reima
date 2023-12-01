package com.micro.reima.mapper;

import com.micro.reima.model.admin.BizUserAccRecordVo;
import com.micro.reima.model.bo.ChangePointBo;
import com.micro.reima.model.vo.UserAccRecordVo;
import java.util.Date;
import java.util.List;
import com.micro.reima.domain.BizUserAccRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 余额明细Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizUserAccRecordMapper 
{
    public List<ChangePointBo> syscChangePoint();

    public Integer updateSyncTime(@Param("id") Long id);

    public Integer sumUserAccByScene(@Param("userId") Long userId,@Param("scene") String scene);

    public List<UserAccRecordVo> selectUserAccRecordVoByUserId(@Param("userId") Long userId, @Param("startTime") String startTime,@Param("endTime") String endTime);

    public List<BizUserAccRecord> selectBizUserAccRecordListByUserIdAndMemo(@Param("userId") Long userId,@Param("memo") String memo);
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
     * 删除余额明细
     * 
     * @param id 余额明细主键
     * @return 结果
     */
    public int deleteBizUserAccRecordById(Long id);

    /**
     * 批量删除余额明细
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserAccRecordByIds(Long[] ids);
}
