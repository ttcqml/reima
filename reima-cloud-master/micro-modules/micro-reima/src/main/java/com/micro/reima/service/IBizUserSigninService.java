package com.micro.reima.service;

import com.micro.reima.model.vo.BizUserSigninVo;
import com.micro.reima.model.vo.SimpleUserSignVo;
import java.util.List;
import com.micro.reima.domain.BizUserSignin;

/**
 * 签到记录Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizUserSigninService 
{
    boolean appSignin(Long userId);

    public List<SimpleUserSignVo> selectSimpleUserSignVoByUserIdAndMonth(Long userId,String month);

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
     * 批量删除签到记录
     * 
     * @param ids 需要删除的签到记录主键集合
     * @return 结果
     */
    public int deleteBizUserSigninByIds(Long[] ids);

    /**
     * 删除签到记录信息
     * 
     * @param id 签到记录主键
     * @return 结果
     */
    public int deleteBizUserSigninById(Long id);
}
