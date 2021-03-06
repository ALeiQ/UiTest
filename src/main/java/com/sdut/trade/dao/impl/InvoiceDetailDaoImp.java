package com.sdut.trade.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.ListUtils;

import com.sdut.trade.dao.InvoiceDetailDao;
import com.sdut.trade.entity.InvoiceDetail;
import com.sdut.trade.entity.InvoiceDetailExample;
import com.sdut.trade.enums.impl.EnableEnum;
import com.sdut.trade.mapper.InvoiceDetailMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 类描述：开票信息明细表Dao层实现类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/20
 */
@Slf4j
@Component
public class InvoiceDetailDaoImp implements InvoiceDetailDao {

    @Autowired
    InvoiceDetailMapper invoiceDetailMapper;

    /**
     * 查询票务信息Id为queryId的发票详情
     *
     * @param queryId
     *
     * @return
     */
    @Override
    public List<InvoiceDetail> getAllByInvoiceId(Integer queryId) {

        InvoiceDetailExample invoiceDetailExample = new InvoiceDetailExample();

        invoiceDetailExample.setOrderByClause("id asc");

        invoiceDetailExample.createCriteria()
                .andInvoiceIdEqualTo(queryId)
                .andEnableEqualTo(EnableEnum.ENABLE.isValue());

        return invoiceDetailMapper.selectByExample(invoiceDetailExample);
    }

    /**
     * 批量添加开票附加信息
     *
     * @param invoiceDetailList
     *
     * @return
     */
    @Override
    public int addInvoiceDetails(List<InvoiceDetail> invoiceDetailList) {

        if (ListUtils.isEmpty(invoiceDetailList)) {
            return 0;
        }

        return invoiceDetailMapper.batchInsert(invoiceDetailList);
    }

    /**
     * 通过开票附表id删除开票附加信息
     *
     * @param delId
     * @param deleteDate
     *
     * @return
     */
    @Override
    public int delInvoiceDetailsById(int delId, Date deleteDate) {

        InvoiceDetail invoiceDetail = new InvoiceDetail();

        invoiceDetail.setId(delId);
        invoiceDetail.setEnable(EnableEnum.DISABLE.isValue());
        invoiceDetail.setDeleteDate(deleteDate);

        return invoiceDetailMapper.updateByPrimaryKeySelective(invoiceDetail);
    }

    /**
     * 通过开票id删除开票附加信息
     *
     * @param delId
     * @param deleteDate
     *
     * @return
     */
    @Override
    public int delInvoiceDetailsByInvoiceId(int delId, Date deleteDate) {

        InvoiceDetailExample invoiceDetailExample = new InvoiceDetailExample();

        invoiceDetailExample.createCriteria()
                .andInvoiceIdEqualTo(delId);

        InvoiceDetail invoiceDetail = new InvoiceDetail();

        invoiceDetail.setEnable(EnableEnum.DISABLE.isValue());
        invoiceDetail.setDeleteDate(deleteDate);

        return invoiceDetailMapper.updateByExampleSelective(invoiceDetail, invoiceDetailExample);
    }

    /**
     * 更新开票信息
     *
     * @param updateDetail
     *
     * @return
     */
    @Override
    public int updateInvoiceDetail(InvoiceDetail updateDetail) {

        return invoiceDetailMapper.updateByPrimaryKey(updateDetail);
    }
}
