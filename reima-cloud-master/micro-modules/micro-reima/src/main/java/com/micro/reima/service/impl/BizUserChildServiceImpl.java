package com.micro.reima.service.impl;

import com.micro.common.core.exception.ServiceException;
import com.micro.reima.domain.BizUserInfo;
import com.micro.reima.model.admin.BizUserChildVo;
import com.micro.reima.model.bo.BizUserChildInfo;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizUserChildMapper;
import com.micro.reima.domain.BizUserChild;
import com.micro.reima.service.IBizUserChildService;
import org.springframework.util.CollectionUtils;

/**
 * 用户孩子Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizUserChildServiceImpl implements IBizUserChildService 
{
    @Autowired
    private BizUserChildMapper bizUserChildMapper;

    @Override
    public List<BizUserChild> selectBizUserChildsByUserId(Long userId) {
        return bizUserChildMapper.selectBizUserChildsByUserId(userId);
    }

    @Override
    public void saveBizUserChildInfo(List<BizUserChildInfo> bos, Long userId) {
        if(CollectionUtils.isEmpty(bos)){
            return;
        }
        for (BizUserChildInfo childInfoBo:bos){
            if(null == childInfoBo.getId()){
                // 新增
                if(StringUtils.isEmpty(childInfoBo.getName())&&StringUtils.isEmpty(childInfoBo.getBirthday())){
                    continue;
                }
                BizUserChild bizUserChild = BizUserChild.buildBizUserChild(childInfoBo,userId);
                boolean has = !StringUtils.isEmpty(childInfoBo.getBirthday()) && !"请选择日期".equals(childInfoBo.getBirthday());
                bizUserChild.setBirthday(has?DateUtils.parseDate(childInfoBo.getBirthday()):null);
                bizUserChildMapper.insertBizUserChild(bizUserChild);
            }else{
                // 更新
                BizUserChild bizUserChild = bizUserChildMapper.selectBizUserChildById(childInfoBo.getId());
                if(null == bizUserChild){
                    throw new ServiceException("数据不存在");
                }
                bizUserChild.setUserId(userId);
                bizUserChild.setName(childInfoBo.getName());
                bizUserChild.setSex(childInfoBo.getSex());
                boolean has = !StringUtils.isEmpty(childInfoBo.getBirthday()) && !"请选择日期".equals(childInfoBo.getBirthday());
                bizUserChild.setBirthday(has?DateUtils.parseDate(childInfoBo.getBirthday()):null);
                bizUserChildMapper.updateBizUserChild(bizUserChild);
            }
        }
    }

    /**
     * 查询用户孩子
     * 
     * @param id 用户孩子主键
     * @return 用户孩子
     */
    @Override
    public BizUserChildVo selectBizUserChildById(Long id)
    {
        return bizUserChildMapper.selectBizUserChildById(id);
    }

    /**
     * 查询用户孩子列表
     * 
     * @param bizUserChild 用户孩子
     * @return 用户孩子
     */
    @Override
    public List<BizUserChildVo> selectBizUserChildList(BizUserChildVo bizUserChild)
    {
        return bizUserChildMapper.selectBizUserChildList(bizUserChild);
    }

    /**
     * 新增用户孩子
     * 
     * @param bizUserChild 用户孩子
     * @return 结果
     */
    @Override
    public int insertBizUserChild(BizUserChild bizUserChild)
    {
        bizUserChild.setCreateTime(DateUtils.getNowDate());
        return bizUserChildMapper.insertBizUserChild(bizUserChild);
    }

    /**
     * 修改用户孩子
     * 
     * @param bizUserChild 用户孩子
     * @return 结果
     */
    @Override
    public int updateBizUserChild(BizUserChild bizUserChild)
    {
        bizUserChild.setUpdateTime(DateUtils.getNowDate());
        return bizUserChildMapper.updateBizUserChild(bizUserChild);
    }

    /**
     * 批量删除用户孩子
     * 
     * @param ids 需要删除的用户孩子主键
     * @return 结果
     */
    @Override
    public int deleteBizUserChildByIds(Long[] ids)
    {
        return bizUserChildMapper.deleteBizUserChildByIds(ids);
    }

    /**
     * 删除用户孩子信息
     * 
     * @param id 用户孩子主键
     * @return 结果
     */
    @Override
    public int deleteBizUserChildById(Long id)
    {
        return bizUserChildMapper.deleteBizUserChildById(id);
    }
}
