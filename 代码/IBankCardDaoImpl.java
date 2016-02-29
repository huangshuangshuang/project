package com.hoss.account.daoImpl;

import com.hoss.account.dao.IBankCardDao;
import com.hoss.account.model.dto.BrokerBankCardBean;
import com.hoss.account.model.dto.SocietyBankCardPojo;
import com.hoss.account.util.ConstantsUtil;
import com.hoss.query.help.QueryDevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/2/4
 * Description:钱包银行卡
 * <author>           <time>             <version>        <desc>
 * 黄双双             2016/2/4             00000001        创建文件
 */
@Repository
public class IBankCardDaoImpl implements IBankCardDao {

    private static final String PREFIX = IBankCardDaoImpl.class.getName() + ".";
    @Autowired
    private QueryDevice queryDevice;
	/**
	 * 查询银行卡列表
	 */
	@Override
	public List<SocietyBankCardPojo> getCardList(long walletCusId, String status) {

        return queryDevice.queryList(SocietyBankCardPojo.class,PREFIX+"getCardList",walletCusId,status);
	}
	
	/**
	 *  查询默认的银行卡
	 */
	@Override
	public BrokerBankCardBean getDefaultBankCard(Long walletCusId) {

        return queryDevice.queryOne(BrokerBankCardBean.class,PREFIX+"getDefaultBankCard",walletCusId,
                ConstantsUtil.BankCardApproveStatus.APPROVED_STATUS,ConstantsUtil.BankDefaultStatus.ON_STATUS);
	}
}
