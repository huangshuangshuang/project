package com.hoss.account.dao;

import com.hoss.account.model.domain.BrokerBankCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Copyright (C), 2012-2015, 上海好屋网信息技术有限公司
 * Author:   黄双双
 * Date:     2016/2/4
 * Description:钱包银行卡
 * <author>           <time>             <version>        <desc>
 * 黄双双             2016/2/4             00000001        创建文件
 */
@Repository
public interface BrokerBankCardDao extends JpaRepository<BrokerBankCard, Long>{
	
	/**
	 * 更新状态为默认(on)的为off
	 * 
	 * @param brokerId 经纪人的Id
	 * @param defaultStatus  银行卡默认的状态码
	 * @param unDefaultStatus 银行卡不是默认的状态码
	 */
	@Modifying
	@Query(nativeQuery=true,value="update wallet_cus_bank_card set is_default=?3 where wallet_cus_id =?1 and is_default=?2")
	public void updateByBrokerIdAndIsDefault(long brokerId, String defaultStatus, String unDefaultStatus);
	
	/**
	 * 根据银行卡号更新 默认的状态
	 * 
	 * @param bankNo 银行卡
	 * @param defaultStatus 默认的状态码
	 */
	@Modifying
	@Query(nativeQuery = true, value = "update wallet_cus_bank_card set is_default=?2 where bank_no=?1 and wallet_cus_id=?3")
	public void updateByBankNoAndIsDefault(String bankNo, String defaultStatus,long brokerId);

	/**
	 * 查询银行卡的信息
	 * @param bankNo
	 * @param brokerId
	 * @return
	 */
	@Query(nativeQuery=true,value="select * from wallet_cus_bank_card where bank_no=?1 and wallet_cus_id=?2")
	public BrokerBankCard findByBankNoAndBrokerId(String bankNo, long brokerId);

    /**
     * 查询银行卡的信息
     * @param status
     * @param isDefault
     * @return
     */
    @Query(nativeQuery = true,value="select * from wallet_cus_bank_card where status=?1 and is_default=?2 order by create_time desc limit 1")
    public BrokerBankCard findBankCardIsNotDefault(String status, String isDefault);
}
