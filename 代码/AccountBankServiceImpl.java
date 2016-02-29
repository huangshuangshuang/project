package com.hoss.account.service.impl;

import com.hoss.account.dao.ApprovedRecordDao;
import com.hoss.account.dao.BankDao;
import com.hoss.account.dao.BrokerBankCardDao;
import com.hoss.account.dao.IBankCardDao;
import com.hoss.account.exception.HaowuAccountException;
import com.hoss.account.model.domain.ApprovedRecord;
import com.hoss.account.model.domain.Bank;
import com.hoss.account.model.domain.BrokerBankCard;
import com.hoss.account.model.dto.BankPojo;
import com.hoss.account.model.dto.BrokerBankCardBean;
import com.hoss.account.model.dto.SocietyBankCardPojo;
import com.hoss.account.service.AccountBankService;
import com.hoss.account.util.ConstantsUtil;
import com.hoss.account.util.StringUtil;
import com.hoss.common.config.SystemConfigFactory;
import com.hoss.common.model.CommonSystemConfig;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/2/4
 * Description:钱包银行卡
 * <author>           <time>             <version>        <desc>
 * 黄双双             2016/2/4             00000001        创建文件
 */
@Service
public class AccountBankServiceImpl implements AccountBankService {

    private static final String SUCCESS="1";
    private static final String DEFAULT="-1";
    private static final Logger LOGGER = Logger.getLogger(AccountBankServiceImpl.class);

    @Autowired
    private BrokerBankCardDao brokerBankCardDao;            //经纪人银行卡Dao
    @Autowired
    private BankDao bankDao;                                //银行卡Dao
    @Autowired
    private IBankCardDao iBankCardDao;//
    @Autowired
    private ApprovedRecordDao approvedRecordDao;

    /**
     * 添加银行卡
     *
     * @param bankCard
     * @return
     * @throws HaowuAccountException
     */
    @Override
    @Transactional
    public String addBankCard(BrokerBankCard bankCard) throws HaowuAccountException {
        try {
            BrokerBankCard brokerBankCard = brokerBankCardDao.findByBankNoAndBrokerId(bankCard.getBankNo(), bankCard.getWalletCusId());// 判断银行卡是否存在
            if (null != brokerBankCard) {
                if (ConstantsUtil.BankCardApproveStatus.UNBIND_STATUS.equals(brokerBankCard.getStatus())) {
                    if (bankCard.getIsDefault().equals(ConstantsUtil.BankDefaultStatus.ON_STATUS)) { // 添加时勾选了[设置默认] ,那么 修改之前有默认状态的银行卡为 off
                        brokerBankCardDao.updateByBrokerIdAndIsDefault(bankCard.getWalletCusId(), ConstantsUtil.BankDefaultStatus.ON_STATUS, ConstantsUtil.BankDefaultStatus.OFF_STATUS);
                    }
                    brokerBankCard.setStatus(ConstantsUtil.BankCardApproveStatus.APPROVED_STATUS);//默认为审核通过
                    brokerBankCard.setIsDefault(bankCard.getIsDefault());
                    brokerBankCard.setBankId(bankCard.getBankId());//银行类型Id
                    brokerBankCard.setName(bankCard.getName());                 // 持卡人姓名
                    brokerBankCard.setBankName(bankCard.getBankName());         // 银行名
                    brokerBankCard.setProvinceId(bankCard.getProvinceId()); // 省份 Id
                    brokerBankCard.setCityId(bankCard.getCityId()); // 城市Id
                    brokerBankCardDao.save(brokerBankCard);
                    return SUCCESS;
                }
                return DEFAULT;// 银行卡存在了 不添加
            } else {// 银行卡不存在
                if (bankCard.getIsDefault().equals(ConstantsUtil.BankDefaultStatus.ON_STATUS)) { // 添加时勾选了[设置默认] ,那么 修改之前有默认状态的银行卡为 off
                    brokerBankCardDao.updateByBrokerIdAndIsDefault(bankCard.getWalletCusId(), ConstantsUtil.BankDefaultStatus.ON_STATUS, ConstantsUtil.BankDefaultStatus.OFF_STATUS);
                }
                brokerBankCardDao.save(bankCard);
            }
            return SUCCESS;                                                //成功状态码
        } catch (Exception e) {
            LOGGER.error("添加银行卡出错..:" + e);
            throw new HaowuAccountException("添加银行卡出错");
        }
    }

    /**
     * 更新默认的银行卡
     *
     * @param walletCusId   经纪人Id
     * @param bankCardNo 银行卡号
     * @throws HaowuAccountException
     */
    @Override
    @Transactional
    public String updateDefaultBankCard(Long walletCusId, String bankCardNo) throws HaowuAccountException {
        try {
            BrokerBankCard card = brokerBankCardDao.findByBankNoAndBrokerId(bankCardNo, walletCusId);
            if (null != card) {//说明有此银行卡
                brokerBankCardDao.updateByBrokerIdAndIsDefault(walletCusId, ConstantsUtil.BankDefaultStatus.ON_STATUS, ConstantsUtil.BankDefaultStatus.OFF_STATUS);
                brokerBankCardDao.updateByBankNoAndIsDefault(bankCardNo, ConstantsUtil.BankDefaultStatus.ON_STATUS,walletCusId);
                return SUCCESS;
            } else {
                LOGGER.error(" 没有对应的银行卡号,不可以设置默认的银行卡!");
                return DEFAULT;
            }
        } catch (Exception e) {
            LOGGER.error("更新默认的银行卡出错:" + e);
            return DEFAULT;
        }
    }

    /**
     * 查询出所有的银行
     *
     * @return 所有银行的列表
     * @throws HaowuAccountException
     */
    @Override
    public List<BankPojo> findBankList() throws HaowuAccountException {
        List<Bank> list = bankDao.findAll();
        List bankBeans = new ArrayList<BankPojo>();
        if (null != list) {
            String picUrl = SystemConfigFactory.getInstance(CommonSystemConfig.class).getConfigValue(ConstantsUtil.PHP_BANKPIC_HOST, ConstantsUtil.PHP_BANKPIC_DEFAUT_URL);
            for (Bank bank : list) {
                BankPojo bankBean = new BankPojo();
                bankBean.setBankId(bank.getId());//银行卡类型Id
                bankBean.setFullCode(bank.getFullCode());
                bankBean.setIconUrl(picUrl + StringUtil.formatString(bank.getIconUrl()));
                bankBean.setPcIconUrl(picUrl + StringUtil.formatString(bank.getPcIconUrl()));
                bankBean.setName(bank.getName());
                bankBean.setShortCode(bank.getShortCode());
                bankBeans.add(bankBean);
            }
        }
        return bankBeans;
    }

    /**
     * 获取经纪人默认的银行
     *
     * @param walletCusId
     * @return
     */
    @Override
    public BrokerBankCardBean getBrokerDefaultBankCard(Long walletCusId) {
        return iBankCardDao.getDefaultBankCard(walletCusId);
    }

    /**
     * 查询银行卡列表
     *
     * @param walletCusId
     * @return
     * @throws HaowuAccountException
     */
    @Override
    public List<SocietyBankCardPojo> getBankCardList(Long walletCusId) throws HaowuAccountException {
        try {
            List<SocietyBankCardPojo> list = iBankCardDao.getCardList(walletCusId, ConstantsUtil.BankCardApproveStatus.APPROVED_STATUS);
            if (null != list && list.size() > 0) {
                String picUrl = SystemConfigFactory.getInstance(CommonSystemConfig.class).getConfigValue(ConstantsUtil.PHP_BANKPIC_HOST, ConstantsUtil.PHP_BANKPIC_DEFAUT_URL);
                for (int i = 0; i < list.size(); i++) {
                    list.get(i).setBankUrl(picUrl + StringUtil.formatString(list.get(i).getBankUrl()));
                    list.get(i).setPcBankUrl(picUrl + StringUtil.formatString(list.get(i).getPcBankUrl()));
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(" 查询银行卡列表出错..:" + e);
            throw new HaowuAccountException(e.getMessage());
        }
    }

    /**
     * 删除/解绑银行卡
     *
     * @param bankCardId
     */
    @Override
    @Transactional
    public String deleteBrokerBankCard(Long bankCardId){
        BrokerBankCard card = brokerBankCardDao.findOne(bankCardId);
        if (card == null) {
            return "解绑的银行卡号不存在";
        }

        List<ApprovedRecord> approvedRecord = approvedRecordDao.findByBankIdAndResult(bankCardId,
                ConstantsUtil.ClientFollowUpStatus.STATUS_BROKERAGE_APPLIED);
        if (approvedRecord != null && approvedRecord.size() > 0) {
            return "该银行卡还在申请提佣中，不能解绑";
        }

        card.setStatus(ConstantsUtil.BankCardApproveStatus.UNBIND_STATUS);
        String isDefault = card.getIsDefault();
        card.setIsDefault(ConstantsUtil.BankDefaultStatus.OFF_STATUS);
        brokerBankCardDao.save(card);

        //如果解绑的银行卡为默认的银行卡，则设置最近绑定的银行卡为默认
        if (ConstantsUtil.BankDefaultStatus.ON_STATUS.equals(isDefault)) {
            BrokerBankCard brokerBankCard = brokerBankCardDao.findBankCardIsNotDefault(ConstantsUtil.BankCardApproveStatus.APPROVED_STATUS,
                    ConstantsUtil.BankDefaultStatus.OFF_STATUS);
            if (brokerBankCard != null) {
                brokerBankCard.setIsDefault(ConstantsUtil.BankDefaultStatus.ON_STATUS);
                brokerBankCardDao.saveAndFlush(brokerBankCard);
            }
        }
        return "解绑成功";
    }


}
