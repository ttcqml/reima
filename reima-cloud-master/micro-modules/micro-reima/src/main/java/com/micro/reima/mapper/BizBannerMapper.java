package com.micro.reima.mapper;

import com.micro.reima.model.vo.SimpleBannerVo;
import java.util.List;
import com.micro.reima.domain.BizBanner;

/**
 * 广告Mapper接口
 * 
 * @author micro
 * @date 2021-12-11
 */
public interface BizBannerMapper 
{

    public List<SimpleBannerVo> appList();

    /**
     * 查询广告
     * 
     * @param id 广告主键
     * @return 广告
     */
    public BizBanner selectBizBannerById(Long id);

    /**
     * 查询广告列表
     * 
     * @param bizBanner 广告
     * @return 广告集合
     */
    public List<BizBanner> selectBizBannerList(BizBanner bizBanner);

    /**
     * 新增广告
     * 
     * @param bizBanner 广告
     * @return 结果
     */
    public int insertBizBanner(BizBanner bizBanner);

    /**
     * 修改广告
     * 
     * @param bizBanner 广告
     * @return 结果
     */
    public int updateBizBanner(BizBanner bizBanner);

    /**
     * 删除广告
     * 
     * @param id 广告主键
     * @return 结果
     */
    public int deleteBizBannerById(Long id);

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizBannerByIds(Long[] ids);
}
