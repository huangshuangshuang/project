package com.hoss.account.controller;

import com.hoss.account.exception.HaowuAccountException;
import com.hoss.account.model.domain.BrokerBankCard;
import com.hoss.account.model.dto.BankPojo;
import com.hoss.account.model.dto.DataMsg;
import com.hoss.account.model.dto.SocietyBankCardPojo;
import com.hoss.account.service.AccountBankService;
import com.hoss.account.service.AccountWithdrawService;
import com.hoss.account.util.SocietyUtils;
import com.hoss.core.dto.BaseResponse;
import com.hoss.core.util.ConstantsUtil;
import com.hoss.core.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/2/4
 * Description:钱包银行卡
 * <author>           <time>             <version>        <desc>
 * 黄双双             2016/2/4             00000001        创建文件
 */
@Controller
@RequestMapping("bank")
public class AccountBankController {
    private static final Logger logger = Logger.getLogger(AccountWalletsController.class);

    @Autowired
    private AccountBankService accountBankService;
    @Autowired
    private AccountWithdrawService accountWithdrawService;

    /**
     * 获取银行卡列表
     *
     * @param walletCusId 用户ID
     * @return
     */
    @RequestMapping("/getCreditCardList")
    @ResponseBody
    public BaseResponse getBankCardList(Long walletCusId) {
        try {
            List<SocietyBankCardPojo> list = accountBankService.getBankCardList(walletCusId);
            return BaseResponse.success(list);
        } catch (Exception e) {
            logger.info(e.getMessage(), e);
            e.printStackTrace();
            return BaseResponse.error("获取银行卡列表出错");
        }
    }

    /**
     * 在我的银行卡列表中 设置一张默认的银行卡
     * @param walletCusId 用户ID
     * @param bankCardNo 银行卡号
     * @return
     */
    @RequestMapping("/setDefaultCreditCard")
    @ResponseBody
    public BaseResponse setDefaultBankCard(Long walletCusId, String bankCardNo) {
        if (StringUtil.isNullString(bankCardNo)) {
            return BaseResponse.paramError("输入银行卡不能为空");
        }
        try {
            String result=accountBankService.updateDefaultBankCard(walletCusId, bankCardNo);
            return BaseResponse.success(result);
        } catch (HaowuAccountException e) {
            logger.info(e.getMessage(), e);
            return BaseResponse.error("设置默认的银行卡出错");
        }
    }

    /**
     * 添加银行卡前需要提取密码的校验
     * @param walletCusId 用户ID
     * @return
     */
    @RequestMapping("/addCreditCardValidate")
    @ResponseBody
    public BaseResponse addCreditCardValidateWithdrawPasswd(Long walletCusId) {

        if (!accountWithdrawService.isExistsWithdrawPasswd(walletCusId)){
            return BaseResponse.success(new DataMsg(SocietyUtils.ResponseStatusCode.NO_WITHDRAW_PASSWD, "未设置提现密码"));
        }

        try {
            List list = accountBankService.getBankCardList(walletCusId);
            if (list == null || list.size() == 0) {
                return BaseResponse.success(new DataMsg(SocietyUtils.ResponseStatusCode.NEED_VALIDATE_WITHDRAW_PASSWD, "需要验证提现密码"));
            }
        } catch (HaowuAccountException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }
        return BaseResponse.success();
    }

    /**
     * 是否有银行卡校验
     *@param walletCusId 用户ID
     * @return
     */
    @RequestMapping("/bindCreditCardValidate")
    @ResponseBody
    public BaseResponse bindCreditCardValidate(Long walletCusId) {
        try {
            List list = accountBankService.getBankCardList(walletCusId);
            if (list == null || list.size() == 0) {
                return BaseResponse.success(new DataMsg(SocietyUtils.ResponseStatusCode.NO_BIND_BANK_CARD, "未绑定银行卡"));
            }
        } catch (HaowuAccountException e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException();
        }

        if (!accountWithdrawService.isExistsWithdrawPasswd(walletCusId)) {
            return BaseResponse.success(new DataMsg(SocietyUtils.ResponseStatusCode.NO_WITHDRAW_PASSWD, "未设置提现密码"));
        }
        return BaseResponse.success();
    }

    /**
     * 用户添加银行卡
     *
     * @param brokerBankCard 银行卡信息
     * @return
     */
    @RequestMapping("/addCreditCard")
    @ResponseBody
    public Object addCreditCard(BrokerBankCard brokerBankCard) {

        if (brokerBankCard == null) {
            return BaseResponse.error("银行卡信息不能为空");
        }
        if (brokerBankCard.getBankId() == null || StringUtil.isNullString(brokerBankCard.getBankId().toString())) {
            return BaseResponse.error("银行ID不能为空");
        }
        if (StringUtil.isNullString(brokerBankCard.getBankName())) {
            return BaseResponse.error("银行名字不能为空");
        }
        if (StringUtil.isNullString(brokerBankCard.getName())) {
            return BaseResponse.error("持卡人姓名不能为空");
        }
        if (StringUtil.isNullString(brokerBankCard.getBankNo())) {
            return BaseResponse.error("卡号不能为空");
        }
        if (StringUtil.isNullString(brokerBankCard.getIsDefault())) {
            return BaseResponse.error("是否默认状态选择不能为空");
        }
        if (!brokerBankCard.getIsDefault().equals(ConstantsUtil.BankDefaultStatus.ON_STATUS) && !brokerBankCard.getIsDefault().equals(ConstantsUtil.BankDefaultStatus.OFF_STATUS)) {
            return BaseResponse.error("默认状态选择错误");
        }
        if (brokerBankCard.getCityId().toString() == null || StringUtil.isNullString(brokerBankCard.getCityId().toString())) {
            return BaseResponse.error("银行卡所在城市不能为空");
        }
        if (brokerBankCard.getProvinceId() == null || StringUtil.isNullString(brokerBankCard.getProvinceId().toString())) {
            return BaseResponse.error("银行卡所在省不能为空");
        }
        if (brokerBankCard.getWalletCusId()==null||StringUtil.isNullString(brokerBankCard.getWalletCusId().toString())){
            return BaseResponse.error("钱包主人ID不能为空");
        }

        try {
            if (StringUtil.isNullString(brokerBankCard.getIsDefault()) || !ConstantsUtil.BankDefaultStatus.ON_STATUS.equals(brokerBankCard.getIsDefault())) {
                List list = accountBankService.getBankCardList(brokerBankCard.getWalletCusId());
                if (list == null || list.size() == 0) {
                    brokerBankCard.setIsDefault(ConstantsUtil.BankDefaultStatus.ON_STATUS);
                } else {
                    brokerBankCard.setIsDefault(ConstantsUtil.BankDefaultStatus.OFF_STATUS);
                }
            }
            brokerBankCard.setWalletCusId(brokerBankCard.getWalletCusId());         // 经纪人Id
            brokerBankCard.setStatus(ConstantsUtil.BankCardApproveStatus.APPROVED_STATUS); // 默认是审核通过

			/*调用业务方法*/
            String repStatus = accountBankService.addBankCard(brokerBankCard);// 返回状态1成功 -1 银行卡存在
            return BaseResponse.success(repStatus);

        } catch (HaowuAccountException e) {
            logger.info(e.getMessage(), e);
            return BaseResponse.error("用户添加银行卡出错");
        }
    }

    /**
     * 用户解绑银行卡
     *
     * @param bankCardId 银行卡ID
     * @return
     */
    @RequestMapping("/unBindCreditCard")
    @ResponseBody
    public BaseResponse unBindCreditCard(String bankCardId) {

        if (StringUtil.isNullString(bankCardId)) {
            return BaseResponse.error("未选择需要解绑的银行卡");
        }
        try {
            String result=accountBankService.deleteBrokerBankCard(Long.valueOf(bankCardId));
            return BaseResponse.success(result);
        } catch (HaowuAccountException e) {
            return BaseResponse.error(e.getMessage());
        }
    }

    /**
     * 获取银行的列表
     *
     * @return
     */
    @RequestMapping("/getBankList")
    @ResponseBody
    public Object getBankList() {
        try {
			/*调用业务方法*/
            List<BankPojo> list = accountBankService.findBankList();
            return BaseResponse.success(list);
        } catch (HaowuAccountException e) {
            logger.info(e.getMessage(), e);
            return BaseResponse.error("获取银行的列表出错");
        }
    }

}
