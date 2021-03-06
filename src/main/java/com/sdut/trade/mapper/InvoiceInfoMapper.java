package com.sdut.trade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sdut.trade.entity.InvoiceInfo;
import com.sdut.trade.entity.InvoiceInfoExample;

public interface InvoiceInfoMapper {
    long countByExample(InvoiceInfoExample example);

    int deleteByExample(InvoiceInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(InvoiceInfo record);

    int insertSelective(InvoiceInfo record);

    List<InvoiceInfo> selectByExample(InvoiceInfoExample example);

    InvoiceInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") InvoiceInfo record, @Param("example") InvoiceInfoExample example);

    int updateByExample(@Param("record") InvoiceInfo record, @Param("example") InvoiceInfoExample example);

    int updateByPrimaryKeySelective(InvoiceInfo record);

    int updateByPrimaryKey(InvoiceInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsert(@Param("list") List<InvoiceInfo> list);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table invoice_info
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    int batchInsertSelective(@Param("list") List<InvoiceInfo> list,
                             @Param("selective") InvoiceInfo.Column... selective);
}