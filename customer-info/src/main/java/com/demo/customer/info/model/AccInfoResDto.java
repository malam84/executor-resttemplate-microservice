package com.demo.customer.info.model;

import lombok.Setter;
import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Setter
@NoArgsConstructor
public class AccInfoResDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer accNumber;
	private int amount;
	private String accType;
	private String branchName;
}
