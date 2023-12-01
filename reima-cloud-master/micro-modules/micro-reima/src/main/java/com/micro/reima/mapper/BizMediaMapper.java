package com.micro.reima.mapper;

import com.micro.reima.domain.BizMedia;
import com.micro.reima.model.vo.SimpleMediaVo;
import java.util.List;

/**
 * 资讯媒体Mapper接口
 * 
 * @author micro
 * @date 2021-12-21
 */
public interface BizMediaMapper 
{

    public List<SimpleMediaVo> appList();
    /**
     * 查询资讯媒体
     * 
     * @param id 资讯媒体主键
     * @return 资讯媒体
     */
    public BizMedia selectBizMediaById(Long id);

    /**
     * 查询资讯媒体列表
     * 
     * @param bizMedia 资讯媒体
     * @return 资讯媒体集合
     */
    public List<BizMedia> selectBizMediaList(BizMedia bizMedia);

    /**
     * 新增资讯媒体
     * 
     * @param bizMedia 资讯媒体
     * @return 结果
     */
    public int insertBizMedia(BizMedia bizMedia);

    /**
     * 修改资讯媒体
     * 
     * @param bizMedia 资讯媒体
     * @return 结果
     */
    public int updateBizMedia(BizMedia bizMedia);

    /**
     * 删除资讯媒体
     * 
     * @param id 资讯媒体主键
     * @return 结果
     */
    public int deleteBizMediaById(Long id);

    /**
     * 批量删除资讯媒体
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizMediaByIds(Long[] ids);
}
