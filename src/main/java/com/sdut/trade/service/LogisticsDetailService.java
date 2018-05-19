package com.sdut.trade.service;

import com.sdut.trade.httpmodel.request.AddLogisticsRequest;
import com.sdut.trade.httpmodel.response.ResponseVO;

/**
 * 类描述：运输明细业务层接口类
 *
 * @author liuzixiang[liuzixiang@baidu.com]
 * @date 2018/5/19
 */
public interface LogisticsDetailService {

    /**
     * 添加单条运输明细数据
     *
     * @param addLogisticsRequest 待处理添加的原生数据（前端传来）
     * @return
     */
    ResponseVO addLogisticsDetail(AddLogisticsRequest addLogisticsRequest);

    /**
     * 查询全部运输明细
     * @return
     */
    ResponseVO getAll();
}
