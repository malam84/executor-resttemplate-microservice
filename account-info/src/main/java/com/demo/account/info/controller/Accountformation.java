package com.demo.account.info.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.account.info.exception.RecordNotFoundException;
import com.demo.account.info.model.AccInfo;
import com.demo.account.info.service.AccountInfoService;

@RestController
@RequestMapping("/api")
public class Accountformation {
	
	@Autowired
	RestTemplate  restTemplate;
	
	@Autowired
	AccountInfoService accountInfoService;
	
	/**
	 * 
	 * @param accNo
	 * @return accInfo object
	 * 
	 */
	@RequestMapping(value="/accinfo", method = RequestMethod.GET)
	public ResponseEntity<AccInfo> getAccountInfoByAccNo(@RequestParam Integer accNo) {
		
		Optional<AccInfo> accInfo = accountInfoService.findCustInfoByAccNo(accNo);
		if (accInfo.isPresent()) {
		    return new ResponseEntity<>(accInfo.get(), HttpStatus.OK);
		} else {
			throw new RecordNotFoundException("Invalid account number " + accNo);
	    }
	}
	
	
	/**
	 * 
	 * @param custId
	 * @return accInfo object
	 * 
	 */
	@RequestMapping(value="/accinfodetail", method = RequestMethod.GET)
	public ResponseEntity<List<AccInfo>> getAccountInfoByCustId(@RequestParam String custId) {
		
		Optional<List<AccInfo>> accInfo = accountInfoService.findCustInfoByCustId(custId);
		if (accInfo.isPresent()) {
		    return new ResponseEntity<>(accInfo.get(), HttpStatus.OK);
		} else {
		    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
