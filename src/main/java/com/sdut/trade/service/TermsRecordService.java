package com.sdut.trade.service;

import java.util.Date;
import java.util.List;

import com.sdut.trade.enums.impl.TermsRecordTypeEnum;
import com.sdut.trade.httpmodel.request.AddTermsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：常用名词增删记录业务层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/10
 */
public interface TermsRecordService {

    /**
     * 批量添加常用名词添加记录
     *
     * @param addTermsRequestList 待添加数据列表
     * @param createDate 添加时间
     * @param addType 添加类型
     * @return 数据库添加结果
     */
    ResponseVO addRecords(TermsRecordTypeEnum addType, List<AddTermsRequest> addTermsRequestList, Date createDate);

    /**
     * 删除单条常用名词添加记录
     *
     * @param delTermsRequest 待删除数据列表
     * @param delDate 删除时间
     * @param delType 删除类型
     * @return 数据库添加结果
     */
    ResponseVO delRecord(TermsRecordTypeEnum delType, AddTermsRequest delTermsRequest, Date delDate);

}
