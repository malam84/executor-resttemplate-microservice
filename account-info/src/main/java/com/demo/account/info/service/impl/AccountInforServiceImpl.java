package com.demo.account.info.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.account.info.model.AccInfo;
import com.demo.account.info.repository.AccountInfoRepository;
import com.demo.account.info.service.AccountInfoService;


@Service
public class AccountInforServiceImpl implements AccountInfoService {

	@Autowired
	AccountInfoRepository accInfoRepository;

	@Override
	public Optional<AccInfo> findCustInfoByAccNo(Integer accNo) {
		return accInfoRepository.findAccountInfoByAccNo(accNo);
	}

	@Override
	public Optional<List<AccInfo>> findCustInfoByCustId(String custId) {
		return accInfoRepository.findAccountInfoByCustId(custId);
	}

}
