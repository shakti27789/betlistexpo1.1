package com.khelobet.exposure.bean;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExFancyBook {



	private Integer rates;

	private Double pnl;

	private String rateRange;

	private String userid;
	
	private String fancyid;
	
	private Double back;
	
	private Double lay;
	
	


}
