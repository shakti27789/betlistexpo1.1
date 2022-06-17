package com.khelobet.exposure.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Document(collection = "t_betlist")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExMatchOddsBetMongo {

	@Id
	public String id;

	private String userid;

	private Integer matchid;

	private Boolean isback;

	private Boolean islay;

	private Double pricevalue;

	private Double odds;

	private String placeTime;

	private String matchedtime;

	private String marketid;

	private String marketname;

	private String status;

	private Integer selectionid;

	private String selectionname;

	private Date updatedon;


	private Integer result;

	private Integer resultid = 0;;

	private String resultname;

	private String deletedBy;

	private String type;

	private String selectionids;

	private String date;

	private String parentid;

	private String adminid;

	private String subadminid;

	private String supermasterid;

	private String masterid;

	private String dealerid;

	
	private Date createdon;

	

	private int stake;

	private String matchname;

	private int sportid;

	private Boolean isactive;

	private Double pnl;

	private Double liability;

	private Double netpnl;

	private Boolean isdeleted;

	private String Userip;

	private Integer series;

	private String userchain;

	
	private Integer serialno;

	private String remarks;

	private String eventType;

	private Double oldpnl = 0.0;

	private Double partnership;

	private Boolean addetoFirestore;

	public ExMatchOddsBetMongo getInstance(Object[] row) {

		ExMatchOddsBetMongo bean = new ExMatchOddsBetMongo();
//		bean.setId((Integer) row[0]);

		bean.setAdminid((String) row[6]);
		bean.setSubadminid((String) row[7]);
		bean.setSupermasterid((String) row[8]);
		bean.setMasterid((String) row[9]);
		bean.setDealerid((String) row[10]);
		bean.setUserid((String) row[11]);
		bean.setMatchid((Integer) row[12]);
		bean.setOdds((Double) row[13]);
		bean.setIsback((Boolean) row[14]);
		bean.setIslay((Boolean) row[15]);
		bean.setMarketname((String) row[16]);
		bean.setPricevalue((Double) row[17]);
		bean.setStatus((String) row[18]);
		bean.setSelectionid((Integer) row[19]);
		bean.setSelectionname((String) row[20]);
		bean.setParentid((String) row[21]);
		bean.setStake((Integer) row[22]);
		bean.setUserchain((String) row[23]);
		bean.setMatchname((String) row[24]);
		bean.setPnl((Double) row[25]);
		bean.setLiability((Double) row[26]);
		bean.setIsactive((Boolean) row[27]);
		bean.setMarketid((String) row[28]);

		return bean;
	}

	public ExMatchOddsBetMongo getMainDateWiseBets(Object[] row) {

		ExMatchOddsBetMongo bean = new ExMatchOddsBetMongo();
		bean.setUserid((String) row[0]);

		bean.setMatchname((String) row[1]);
		bean.setSportid((Integer) row[2]);
		bean.setResultid((Integer) row[3]);
		bean.setType((String) row[4]);
		bean.setMarketid((String) row[5]);
		bean.setUserchain((String) row[6]);
		bean.setResult((Integer) row[7]);
		bean.setPnl((Double) row[8]);
		bean.setNetpnl((Double) row[9]);

		bean.setDate((String) row[10]);

		return bean;

	}

	public ExMatchOddsBetMongo getUserExposure(Object[] row) {

		ExMatchOddsBetMongo bean = new ExMatchOddsBetMongo();

		bean.setLiability((Double) row[0]);

		return bean;
	}

	public ExMatchOddsBetMongo getFancyBook(Object[] row) {

		ExMatchOddsBetMongo bean = new ExMatchOddsBetMongo();
		bean.setStake(Integer.parseInt(row[0].toString()));
		bean.setOdds((Double) row[1]);
		bean.setPnl((Double) row[2]);
		bean.setLiability((Double) row[3]);
		bean.setPartnership(new BigDecimal(row[4].toString()).doubleValue());
		bean.setIsback((Boolean) row[5]);
		return bean;
	}

	public ExMatchOddsBetMongo betHistory(Object[] row) {

		ExMatchOddsBetMongo bean = new ExMatchOddsBetMongo();
		bean.setUserid((String) row[0]);
		bean.setMatchname((String) row[1]);
		bean.setSelectionname((String) row[2]);
		bean.setMarketname((String) row[3]);
		bean.setOdds((Double) row[4]);
		bean.setStake((Integer) row[5]);
		bean.setNetpnl((Double) row[6]);
		bean.setPlaceTime((String) row[7]);
		bean.setMatchedtime((String) row[8]);
		bean.setIsback((Boolean) row[9]);
		BigInteger b1 = new BigInteger(row[10].toString());
		bean.setSerialno(b1.intValue());
		return bean;
	}
}
