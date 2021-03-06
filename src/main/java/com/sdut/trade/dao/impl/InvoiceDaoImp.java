package com.sdut.trade.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sdut.trade.dao.InvoiceDao;
import com.sdut.trade.entity.InvoiceInfo;
import com.sdut.trade.entity.InvoiceInfoExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.InvoiceInfoMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票明细Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Component
@Slf4j
public class InvoiceDaoImp implements InvoiceDao {

    @Autowired
    InvoiceInfoMapper invoiceInfoMapper;

    /**
     * 获取所有发票信息
     *
     * @return
     */
    @Override
    public List<InvoiceInfo> getAll() {

        InvoiceInfoExample invoiceInfoExample = new InvoiceInfoExample();

        invoiceInfoExample.setOrderByClause("id desc");

        invoiceInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return invoiceInfoMapper.selectByExample(invoiceInfoExample);
    }

    /**
     * 获取该流向的所有发票信息（1.进项 2.销项 3.中转）
     *
     * @param direction
     *
     * @return
     */
    @Override
    public List<InvoiceInfo> getAllByDirection(Integer direction) {

        InvoiceInfoExample invoiceInfoExample = new InvoiceInfoExample();

        invoiceInfoExample.setOrderByClause("id desc");

        invoiceInfoExample.createCriteria()
                .andEnableEqualTo(EnableEnum.ENABLE.isValue())
                .andDirectionEqualTo(direction);

        return invoiceInfoMapper.selectByExample(invoiceInfoExample);
    }

    /**
     * 获取指定id的发票信息
     *
     * @param invoiceId
     *
     * @return
     */
    @Override
    public InvoiceInfo getById(int invoiceId) {
        return invoiceInfoMapper.selectByPrimaryKey(invoiceId);
    }

    /**
     * 添加开票简要信息
     *
     * @param invoiceInfo
     *
     * @return id
     */
    @Override
    public int addInvoiceInfo(InvoiceInfo invoiceInfo) {
        invoiceInfoMapper.insertSelective(invoiceInfo);
        return invoiceInfo.getId();
    }

    /**
     * 更新开票简要信息
     *
     * @param invoiceInfo
     *
     * @return
     */
    @Override
    public int updateInvoiceInfo(InvoiceInfo invoiceInfo) {
        invoiceInfoMapper.updateByPrimaryKey(invoiceInfo);
        return invoiceInfo.getId();
    }

    /**
     * 通过开票详情id删除数据
     *
     * @param delId
     * @param deleteDate
     *
     * @return
     */
    @Override
    public int delInvoiceById(int delId, Date deleteDate) {

        InvoiceInfo invoiceInfo = new InvoiceInfo();

        invoiceInfo.setId(delId);
        invoiceInfo.setEnable(EnableEnum.DISABLE.isValue());
        invoiceInfo.setDeleteDate(deleteDate);

        return invoiceInfoMapper.updateByPrimaryKeySelective(invoiceInfo);
    }
}
