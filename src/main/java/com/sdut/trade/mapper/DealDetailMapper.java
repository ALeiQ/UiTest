package com.sdut.trade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sdut.trade.entity.DealDetail;
import com.sdut.trade.entity.DealDetailExample;

public interface DealDetailMapper {
    long countByExample(DealDetailExample example);

    int deleteByExample(DealDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DealDetail record);

    int insertSelective(DealDetail record);

    List<DealDetail> selectByExample(DealDetailExample example);

    DealDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DealDetail record, @Param("example") DealDetailExample example);

    int updateByExample(@Param("record") DealDetail record, @Param("example") DealDetailExample example);

    int updateByPrimaryKeySelective(DealDetail record);

    int updateByPrimaryKey(DealDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<DealDetail> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table deal_detail
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<DealDetail> list, @Param("selective") DealDetail.Column... selective);
}