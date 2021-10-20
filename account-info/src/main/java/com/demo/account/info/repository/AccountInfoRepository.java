package com.demo.account.info.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.account.info.model.AccInfo;

/**
 * 
 * @author malam84
 * 
 **/

@Repository
@Transactional
public interface AccountInfoRepository extends JpaRepository<AccInfo, Long> {
	
	@Query("SELECT a from AccInfo a WHERE a.accNumber = :acc_no")
	Optional<AccInfo> findAccountInfoByAccNo(@Param("acc_no") Integer accNo);
	
	@Query("SELECT a from AccInfo a WHERE a.customerId = :cust_id")
	Optional<List<AccInfo>> findAccountInfoByCustId(@Param("cust_id") String custId);

}
