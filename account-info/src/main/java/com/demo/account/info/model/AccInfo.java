package com.demo.account.info.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tbl_acc")
public class AccInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name="acc_no", nullable = false)
	private Integer accNumber;
	
	@Column(name="acc_amt", nullable = false)
	private int amount;
	
	@Column(name="acc_type", nullable = false)
	private String accType;
	
	@Column(name="acc_branch", nullable = false)
	private String branchName;
	
	@Column(name="acc_status", nullable = false)
	private boolean status;
	
	@Column(name="cust_id", nullable = false)
	private String customerId;
	
	@Column(name="created_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	
}
