package com.micro.reima.service;

import java.util.List;
import com.micro.reima.domain.BizRegion;
import com.micro.reima.model.vo.SimpleRegionVo;

/**
 * 行政区域Service接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface IBizRegionService 
{
    public List<SimpleRegionVo> appList(Long pid);
    /**
     * 查询行政区域
     * 
     * @param id 行政区域主键
     * @return 行政区域
     */
    public BizRegion selectBizRegionById(Long id);

    /**
     * 查询行政区域列表
     * 
     * @param bizRegion 行政区域
     * @return 行政区域集合
     */
    public List<BizRegion> selectBizRegionList(BizRegion bizRegion);

    /**
     * 新增行政区域
     * 
     * @param bizRegion 行政区域
     * @return 结果
     */
    public int insertBizRegion(BizRegion bizRegion);

    /**
     * 修改行政区域
     * 
     * @param bizRegion 行政区域
     * @return 结果
     */
    public int updateBizRegion(BizRegion bizRegion);

    /**
     * 批量删除行政区域
     * 
     * @param ids 需要删除的行政区域主键集合
     * @return 结果
     */
    public int deleteBizRegionByIds(Long[] ids);

    /**
     * 删除行政区域信息
     * 
     * @param id 行政区域主键
     * @return 结果
     */
    public int deleteBizRegionById(Long id);
}
