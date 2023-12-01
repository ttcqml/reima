package com.micro.reima.mapper;

import com.micro.reima.model.vo.BizUserSigninVo;
import com.micro.reima.model.vo.SimpleUserSignVo;
import java.util.List;
import com.micro.reima.domain.BizUserSignin;
import org.apache.ibatis.annotations.Param;

/**
 * 签到记录Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizUserSigninMapper 
{
    public List<SimpleUserSignVo> selectSimpleUserSignVoByUserIdAndMonth(@Param("userId") Long userId, @Param("month") String month);

    public SimpleUserSignVo selectSimpleUserSignVoByUserIdAndSignAt(@Param("userId") Long userId, @Param("signAt") String signAt);

    public List<BizUserSigninVo> selectBizUserSigninVoList(BizUserSigninVo bizUserSignin);

    public BizUserSigninVo selectBizUserSigninVoById(Long id);
    /**
     * 查询签到记录
     * 
     * @param id 签到记录主键
     * @return 签到记录
     */
    public BizUserSignin selectBizUserSigninById(Long id);

    /**
     * 查询签到记录列表
     * 
     * @param bizUserSignin 签到记录
     * @return 签到记录集合
     */
    public List<BizUserSignin> selectBizUserSigninList(BizUserSignin bizUserSignin);

    /**
     * 新增签到记录
     * 
     * @param bizUserSignin 签到记录
     * @return 结果
     */
    public int insertBizUserSignin(BizUserSignin bizUserSignin);

    /**
     * 修改签到记录
     * 
     * @param bizUserSignin 签到记录
     * @return 结果
     */
    public int updateBizUserSignin(BizUserSignin bizUserSignin);

    /**
     * 删除签到记录
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    public int deleteBizUserSigninById(Long id);

    /**
     * 批量删除签到记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizUserSigninByIds(Long[] ids);
}
