package com.khelobet.exposure.bean;

import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Transient;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;


@Data
@Document(collection = "t_libility")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLiabilityMongo {

	@Id
	String id;

	@Field(name = "userid")
	private String userid;

	@Field(name = "dealerid")
	private String dealerid;

	@Field(name = "masterid")
	private String masterid;

	@Field(name = "adminid")
	private String adminid;
	
	@Field(name = "subadminid")
	private String subadminid;

	@Field(name = "supermasterid")
	private String supermasterid;

	@Field(name = "sportid")
	private int sportid;

	@Field(name = "totalpnl")
	private Double totalpnl;

	@Field(name = "liability")
	private Double liability;

	@Field(name = "marketid")
	private String marketid;

	@Field(name = "matchid")
	private Integer matchid;

	
	@Field(name = "type")
	private String type;

	@Field(name = "selection1")
	private int selection1;
	
	@Transient
	private int selection4;
	
	@Transient
	private int selection5;
	
	@Transient
	private int selection6;
	
	@Transient
	private int selection7;
	
	@Transient
	private int selection8;
	
	@Transient
	private int selection9;

	@Transient
	private int selection10;
	
	@Transient
	private int selection11;
	
	@Transient
	private int selection12;
	
	@Transient
	private int selection13;
	
	@Transient
	private int selection14;
	
	@Transient
	private int selection15;
	
	@Transient
	private int selection16;
	
	
	@Field(name = "adminpartnership")
	private Double adminpartnership = 0.0;

	@Field(name = "masterpartnership")
	private Double masterpartnership = 0.0;

	@Field(name = "dealerpartnership")
	private Double dealerpartnership = 0.0;

	@Field(name = "subadminpartnership")
	private Double subadminpartnership = 0.0;

	@Field(name = "supermasterpartnership")
	private Double supermasterpartnership = 0.0;

	@Field(name = "selection2")
	private int selection2;

	@Field(name = "selection3")
	private int selection3;

	@Field(name = "pnl1")
	private Double pnl1 = 0.0;

	@Field(name = "pnl2")
	private Double pnl2 = 0.0;

	@Field(name = "pnl3")
	private Double pnl3 = 0.0;
	
	@Transient
	private Double pnl4 = 0.0;
	
	@Transient
	private Double pnl5 = 0.0;
	
	@Transient
	private Double pnl6 = 0.0;
	
	@Transient
	private Double pnl7 = 0.0;
	
	@Transient
	private Double pnl8 = 0.0;
	
	@Transient
	private Double pnl9 = 0.0;
	
	@Transient
	private Double pnl10 = 0.0;
	
	@Transient
	private Double pnl11 = 0.0;
	
	@Transient
	private Double pnl12 = 0.0;
	
	@Transient
	private Double pnl13 = 0.0;
	
	@Transient
	private Double pnl14 = 0.0;
	
	@Transient
	private Double pnl15 = 0.0;
	
	@Transient
	private Double pnl16 = 0.0;
	

	@Field(name = "matchname")
	private String matchname;

	@Field(name = "isactive")
	private Boolean isactive;

	@Field(name = "netpnl")
	private Double netpnl;

	
	@Field(name = "isresultset")
	private Boolean isresultset;

	@Field(name = "oldlibility")
	private Double oldlibility;

	
	@Field(name = "oldpnl1")
	private Double oldpnl1;

	@Field(name = "oldpnl2")
	private Double oldpnl2;

	@Field(name = "oldpnl3")
	private Double oldpnl3;

	
	
	@Field(name = "sbacommission")
	private Double sbaCommission = 0.0;

	@Field(name = "sumcommission")
	private Double sumCommission = 0.0;

	@Field(name = "mastcommission")
	private Double mastCommission = 0.0;

	@Field(name = "dealcommission")
	private Double dealCommission = 0.0;

	@Field(name = "admcommission")
	private Double admCommission = 0.0;

	@Field(name = "updatedon")
	private Date updatedon;

	@Field(name = "createdon")
	private Date createdon;

	
	
	@Field(name = "admwincommssion")
	private Double admwincommssion = 0.0;

	@Field(name = "sbawincommssion")
	private Double sbawincommssion = 0.0;

	@Field(name = "sumwincommssion")
	private Double sumwincommssion = 0.0;

	@Field(name = "mastwincommssion")
	private Double mastwincommssion = 0.0;

	@Field(name = "dealwincommssion ")
	private Double dealwincommssion = 0.0;

	@Field(name = "stack ")
	private Integer stack = 0;

	@Field(name = "adminpnl ")
	private Double adminPnl = 0.0;

	@Field(name = "subadminpnl ")
	private Double subadminpnl = 0.0;

	@Field(name = "supermasterpnl ")
	private Double supermasterpnl = 0.0;

	@Field(name = "masterpnl ")
	private Double masterpnl = 0.0;

	@Field(name = "dealerpnl ")
	private Double dealerpnl = 0.0;

	@Field(name = "addedtofirestore")
	private Boolean addetoFirestore;

	@Field(name = "islibilityclear")
	private Boolean isLibilityclear;

	@Field(name = "isprofitlossclear")
	private Boolean isProfitLossclear;

	public UserLiabilityMongo getChildLiability(Object[] row) {

		UserLiabilityMongo bean = new UserLiabilityMongo();

		bean.setLiability((Double) row[0]);
		bean.setIsactive(true);
		bean.setUserid((String) row[2]);

		return bean;
	}

	public UserLiabilityMongo getpnlChidIdWise(Object[] row) {
		DecimalFormat df = new DecimalFormat("#0");

		UserLiabilityMongo bean = new UserLiabilityMongo();
		if ((Double) row[0] != null) {
			bean.setPnl1(Double.valueOf(df.format((Double) row[0])));
			bean.setPnl2(Double.valueOf(df.format((Double) row[1])));
			bean.setPnl3(Double.valueOf(df.format((Double) row[2])));
			bean.setUserid((String) row[3]);
			bean.setAdminid((String) row[4]);
			bean.setSubadminid((String) row[5]);
			bean.setSupermasterid((String) row[6]);
			bean.setMasterid((String) row[7]);
			bean.setDealerid((String) row[8]);
		} else {
			bean.setPnl1((Double) row[0]);
			bean.setPnl2((Double) row[1]);
			bean.setPnl3((Double) row[2]);
			bean.setUserid((String) row[3]);
			bean.setAdminid((String) row[4]);
			bean.setSubadminid((String) row[5]);
			bean.setSupermasterid((String) row[6]);
			bean.setMasterid((String) row[7]);
		}

		return bean;
	}

}
