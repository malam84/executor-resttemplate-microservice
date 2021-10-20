package com.demo.customer.info.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.demo.customer.info.exception.RecordNotFoundException;
import com.demo.customer.info.model.AccInfoResDto;
import com.demo.customer.info.model.CustomerInfo;

@RestController
@RequestMapping("/api")
public class CustomerInformation {
	
	ExecutorService executor;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value="/accountexecutorsev", method=RequestMethod.POST)
	public ResponseEntity<List<AccInfoResDto>> getCustomerInformation(@RequestBody List<Integer> accIdlst) {
		
		
		//ConcurrentHashMap<Integer, Object> accInfoResp = new ConcurrentHashMap<>();
		List<AccInfoResDto> accInfoRes = new ArrayList<AccInfoResDto>();
		
		/* 
		 * @newCachedThreadPool
		 * Create new thread if needed else used previous thread
		 * Used for small unit of task, better performance
		 * drawback, if server load high better option to use @FixedThreadPool  
		 */
		executor = Executors.newCachedThreadPool();
		
		/*
		 * @Future
		 * Future return object after task finished
		 * Create a list to hold the Future object associated with Callable
		 */
		List<Future<AccInfoResDto>>  futureLst= new ArrayList<Future<AccInfoResDto>>();
		for (int i = 0; i < accIdlst.size(); i++) {
			int k = i;
			//Submit tasks to be executed by thread pool
			Future<AccInfoResDto> future = executor.submit(() -> {
        		return callExternalServices(accIdlst.get(k), "http://localhost:8081/api/accinfo");
     		});
			futureLst.add(future);
		} 

		//Implementing sync call to external services without any blocking by executor
		AccInfoResDto  accObj = restTemplate.getForObject("http://localhost:8081/api/accinfo?accNo="+10001, AccInfoResDto.class);
		accInfoRes.add(accObj);
		
		
		try {
			//get information from external api
			for (int i = 0; i < futureLst.size(); i++) {
			   
				Future<AccInfoResDto> future = (Future<AccInfoResDto>) futureLst.get(i);
			    //Future.get() waits for task to get completed
			    AccInfoResDto accInfo = future.get(); 
			    accInfoRes.add(accInfo);
			}
		
		} catch (InterruptedException e) {
			e.printStackTrace();
			return new ResponseEntity<>(accInfoRes, HttpStatus.OK);
		
		} catch (ExecutionException e) {
			e.printStackTrace();
			String[] str = e.getLocalizedMessage().split(":", 3);
			executor.shutdown();
			throw new RecordNotFoundException(str[str.length-1]);
		} 
		
		//shut down the executor service
		executor.shutdown();
		
	  return  new ResponseEntity<>(accInfoRes, HttpStatus.OK);
    }

	/*
	 * 
	 * @Executor Callable method
	 * @calling external using rest template
	 * @return AccInfoResDto
	 * 
	*/
	public AccInfoResDto callExternalServices(Integer value, String url) {
		String apiUrl = url + "?accNo=" +value;
		AccInfoResDto accInfo = restTemplate.getForObject(apiUrl, AccInfoResDto.class);
		return accInfo;
	}
	
	/*
	 * @customerdetail API is an example to call microservices synchronously
	 * */
	@RequestMapping(value="/customerdetail", method = RequestMethod.GET)
	public ResponseEntity<CustomerInfo> getCustomerDetail(@RequestParam String custId) {
		
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomerId("cust003");
		customerInfo.setCustomerName("xyz");
		customerInfo.setCustomerEmail("xyz@gmail.com");
		customerInfo.setDob(LocalDate.parse("1985-01-07"));
		List<AccInfoResDto> accInfolst = this.exchangeAsList("http://localhost:8081/api/accinfodetail?custId="+custId,  new ParameterizedTypeReference<List<AccInfoResDto>>() {});
		customerInfo.setAccInfo(accInfolst);
		return new ResponseEntity<> (customerInfo, HttpStatus.OK);
	}
	
	/* 
	 * Calling parameterize reference using rest template
	 */
	public <T> List<T> exchangeAsList(String uri, ParameterizedTypeReference<List<T>> responseType) {
	    return restTemplate.exchange(uri, HttpMethod.GET, null, responseType).getBody();
	}
}