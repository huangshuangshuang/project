package com.hoss.account.dao;


import com.hoss.account.model.dto.BrokerBankCardBean;
import com.hoss.account.model.dto.SocietyBankCardPojo;

import java.util.List;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/2/4
 * Description:钱包银行卡
 * <author>           <time>             <version>        <desc>
 * 黄双双             2016/2/4             00000001        创建文件
 */
public interface IBankCardDao {

	/**
	 * 查询银行卡列表
	 * @param brokerId 经纪人Id
	 * @param status 状态 默认是审核成功
	 * @return 银行列表
	 */
	public List<SocietyBankCardPojo> getCardList(long brokerId, String status);

    /**
     * 查询默认银行卡
     * @param brokerId
     * @return
     */
	public BrokerBankCardBean getDefaultBankCard(Long brokerId);

}
