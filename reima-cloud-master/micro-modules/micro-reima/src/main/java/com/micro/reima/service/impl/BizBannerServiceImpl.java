package com.micro.reima.service.impl;

import com.micro.reima.model.vo.SimpleBannerVo;
import java.util.List;
import com.micro.common.core.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.micro.reima.mapper.BizBannerMapper;
import com.micro.reima.domain.BizBanner;
import com.micro.reima.service.IBizBannerService;

/**
 * 广告Service业务层处理
 * 
 * @author micro
 * @date 2021-12-11
 */
@Service
public class BizBannerServiceImpl implements IBizBannerService 
{
    @Autowired
    private BizBannerMapper bizBannerMapper;

    @Override
    public List<SimpleBannerVo> appList() {
        return bizBannerMapper.appList();
    }

    /**
     * 查询广告
     * 
     * @param id 广告主键
     * @return 广告
     */
    @Override
    public BizBanner selectBizBannerById(Long id)
    {
        return bizBannerMapper.selectBizBannerById(id);
    }

    /**
     * 查询广告列表
     * 
     * @param bizBanner 广告
     * @return 广告
     */
    @Override
    public List<BizBanner> selectBizBannerList(BizBanner bizBanner)
    {
        return bizBannerMapper.selectBizBannerList(bizBanner);
    }

    /**
     * 新增广告
     * 
     * @param bizBanner 广告
     * @return 结果
     */
    @Override
    public int insertBizBanner(BizBanner bizBanner)
    {
        bizBanner.setCreateTime(DateUtils.getNowDate());
        return bizBannerMapper.insertBizBanner(bizBanner);
    }

    /**
     * 修改广告
     * 
     * @param bizBanner 广告
     * @return 结果
     */
    @Override
    public int updateBizBanner(BizBanner bizBanner)
    {
        bizBanner.setUpdateTime(DateUtils.getNowDate());
        return bizBannerMapper.updateBizBanner(bizBanner);
    }

    /**
     * 批量删除广告
     * 
     * @param ids 需要删除的广告主键
     * @return 结果
     */
    @Override
    public int deleteBizBannerByIds(Long[] ids)
    {
        return bizBannerMapper.deleteBizBannerByIds(ids);
    }

    /**
     * 删除广告信息
     * 
     * @param id 广告主键
     * @return 结果
     */
    @Override
    public int deleteBizBannerById(Long id)
    {
        return bizBannerMapper.deleteBizBannerById(id);
    }
}
