package com.demo.account.info.service;

import java.util.List;
import java.util.Optional;

import com.demo.account.info.model.AccInfo;

public interface AccountInfoService {
	
	public Optional<AccInfo> findCustInfoByAccNo(Integer accNo);
	public Optional<List<AccInfo>> findCustInfoByCustId(String custId);
}
