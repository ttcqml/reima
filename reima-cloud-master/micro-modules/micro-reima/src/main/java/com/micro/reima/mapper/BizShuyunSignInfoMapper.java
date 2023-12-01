package com.micro.reima.mapper;

import java.util.List;
import com.micro.reima.domain.BizShuyunSignInfo;
import com.micro.reima.model.admin.MemberDto;
import org.apache.ibatis.annotations.Param;

/**
 * 数云秘钥授权Mapper接口
 * 
 * @author micro
 * @date 2022-01-08
 */
public interface BizShuyunSignInfoMapper
{
    MemberDto selectOpenIdByMemberId(@Param("memberId") String memberId);
    /**
     * 查询数云秘钥授权
     * 
     * @param id 数云秘钥授权主键
     * @return 数云秘钥授权
     */
    public BizShuyunSignInfo selectBizShuyunSignInfoById(Long id);

    /**
     * 查询数云秘钥授权列表
     * 
     * @param bizShuyunSignInfo 数云秘钥授权
     * @return 数云秘钥授权集合
     */
    public List<BizShuyunSignInfo> selectBizShuyunSignInfoList(BizShuyunSignInfo bizShuyunSignInfo);

    /**
     * 新增数云秘钥授权
     * 
     * @param bizShuyunSignInfo 数云秘钥授权
     * @return 结果
     */
    public int insertBizShuyunSignInfo(BizShuyunSignInfo bizShuyunSignInfo);

    /**
     * 修改数云秘钥授权
     * 
     * @param bizShuyunSignInfo 数云秘钥授权
     * @return 结果
     */
    public int updateBizShuyunSignInfo(BizShuyunSignInfo bizShuyunSignInfo);

    /**
     * 删除数云秘钥授权
     * 
     * @param id 数云秘钥授权主键
     * @return 结果
     */
    public int deleteBizShuyunSignInfoById(Long id);

    /**
     * 批量删除数云秘钥授权
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBizShuyunSignInfoByIds(Long[] ids);
}
