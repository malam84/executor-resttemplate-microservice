package com.demo.customer.info.model;

import java.time.LocalDate;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerInfo {
	
	private String customerId;
	private String customerName;
	private String customerEmail;
	private LocalDate dob;
	private List<AccInfoResDto> accInfo;

}
