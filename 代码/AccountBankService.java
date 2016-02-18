package com.hoss.account.service;

import com.hoss.account.exception.HaowuAccountException;
import com.hoss.account.model.domain.BrokerBankCard;
import com.hoss.account.model.dto.BankPojo;
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
public interface AccountBankService {

    /**
     * 获取银行卡列表 :状态是已审核的银行卡
     * @param walletCusId
     * @return
     * @throws HaowuAccountException
     */
    public List<SocietyBankCardPojo> getBankCardList(Long walletCusId) throws HaowuAccountException;

    /**
     *  添加银行卡
     *
     * @param bankCard
     * @return
     * @throws HaowuAccountException
     */
    public String addBankCard(BrokerBankCard bankCard)throws HaowuAccountException;

    /**
     * 更新默认的银行卡
     * @param brokerId 经纪人的Id
     * @param bankCardNo 银行卡号
     * @throws HaowuAccountException
     */
    public String updateDefaultBankCard(Long brokerId,String bankCardNo)throws HaowuAccountException;

    /**
     * 查询出所有的银行
     * @return
     * @throws HaowuAccountException
     */
    public 	List<BankPojo> findBankList()throws HaowuAccountException;

    /**
     * 获取默认银行卡
     * @param brokerId
     * @return
     */
    public BrokerBankCardBean getBrokerDefaultBankCard(Long brokerId);

    /**
     * 解绑银行卡
     * @param bankCardId
     */
    public String deleteBrokerBankCard(Long bankCardId) throws HaowuAccountException;
}
