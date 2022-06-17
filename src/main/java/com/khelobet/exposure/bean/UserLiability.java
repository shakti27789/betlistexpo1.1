package com.khelobet.exposure.bean;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "t_libility")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLiability {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column(name = "userid")
	private String userid;

	@Column(name = "dealerid")
	private String dealerid;

	@Column(name = "masterid")
	private String masterid;

	@Column(name = "adminid")
	private String adminid;
	
	@Column(name = "subadminid")
	private String subadminid;

	@Column(name = "supermasterid")
	private String supermasterid;

	@Column(name = "sportid")
	private int sportid;

	@Column(name = "totalpnl")
	private Double totalpnl;

	@Column(name = "liability")
	private Double liability;

	@Column(name = "marketid")
	private String marketid;

	@Column(name = "matchid")
	private Integer matchid;

	
	@Column(name = "type")
	private String type;

	@Column(name = "selection1")
	private int selection1;
	
	

	@Column(name = "adminpartnership")
	private Double adminpartnership = 0.0;

	@Column(name = "masterpartnership")
	private Double masterpartnership = 0.0;

	@Column(name = "dealerpartnership")
	private Double dealerpartnership = 0.0;

	@Column(name = "subadminpartnership")
	private Double subadminpartnership = 0.0;

	@Column(name = "supermasterpartnership")
	private Double supermasterpartnership = 0.0;

	@Transient
	@Column(name = "selection2")
	private int selection2;

	@Transient
	@Column(name = "selection3")
	private int selection3;
	
	@Transient
	@Column(name = "selection4")
	private int selection4;
	
	@Transient
	@Column(name = "selection5")
	private int selection5;
	
	@Transient
	@Column(name = "selection6")
	private int selection6;
	
	@Transient
	@Column(name = "selection7")
	private int selection7;
	
	@Transient
	@Column(name = "selection8")
	private int selection8;
	
	@Transient
	@Column(name = "selection9")
	private int selection9;

	@Column(name = "pnl1")
	private Double pnl1 = 0.0;

	@Column(name = "pnl2")
	private Double pnl2 = 0.0;

	@Column(name = "pnl3")
	private Double pnl3 = 0.0;
	
	@Transient
	@Column(name = "pnl4")
	private Double pnl4 = 0.0;
	
	@Transient
	@Column(name = "pnl5")
	private Double pnl5 = 0.0;
	
	@Transient
	@Column(name = "pnl6")
	private Double pnl6 = 0.0;
	
	@Transient
	@Column(name = "pnl7")
	private Double pnl7 = 0.0;
	
	@Transient
	@Column(name = "pnl8")
	private Double pnl8 = 0.0;
	
	@Transient
	@Column(name = "pnl9")
	private Double pnl9 = 0.0;

	@Column(name = "matchname")
	private String matchname;

	@Column(name = "isactive")
	private Boolean isactive;

	@Column(name = "netpnl")
	private Double netpnl;

	
	@Column(name = "isresultset")
	private Boolean isresultset;

	@Column(name = "oldlibility")
	private Double oldlibility;

	
	@Column(name = "oldpnl1")
	private Double oldpnl1;

	@Column(name = "oldpnl2")
	private Double oldpnl2;

	@Column(name = "oldpnl3")
	private Double oldpnl3;

	
	
	@Column(name = "sbacommission")
	private Double sbaCommission = 0.0;

	@Column(name = "sumcommission")
	private Double sumCommission = 0.0;

	@Column(name = "mastcommission")
	private Double mastCommission = 0.0;

	@Column(name = "dealcommission")
	private Double dealCommission = 0.0;

	@Column(name = "admcommission")
	private Double admCommission = 0.0;

	@Column(name = "updatedon")
	private Date updatedon;

	@Column(name = "createdon")
	private Date createdon;

	
	

	@Column(name = "stack ", columnDefinition = "Integer default '0.0'")
	private Integer stack = 0;

	@Column(name = "adminpnl ", columnDefinition = "Double default '0.0'")
	private Double adminPnl = 0.0;

	@Column(name = "subadminpnl ", columnDefinition = "Double default '0.0'")
	private Double subadminpnl = 0.0;

	@Column(name = "supermasterpnl ", columnDefinition = "Double default '0.0'")
	private Double supermasterpnl = 0.0;

	@Column(name = "masterpnl ", columnDefinition = "Double default '0.0'")
	private Double masterpnl = 0.0;

	@Column(name = "dealerpnl ", columnDefinition = "Double default '0.0'")
	private Double dealerpnl = 0.0;

	@Column(name = "addedtofirestore")
	private Boolean addetoFirestore;

	@Column(name = "islibilityclear")
	private Boolean isLibilityclear;

	@Column(name = "isprofitlossclear")
	private Boolean isProfitLossclear;
	
	@Column(name = "ismongodbupdated")
	private Boolean ismongodbupdated = false;
	
	
	
	
	
	public Integer getId() {
		return id;
	}





	public void setId(Integer id) {
		this.id = id;
	}





	public String getUserid() {
		return userid;
	}





	public void setUserid(String userid) {
		this.userid = userid;
	}





	public String getDealerid() {
		return dealerid;
	}





	public void setDealerid(String dealerid) {
		this.dealerid = dealerid;
	}





	public String getMasterid() {
		return masterid;
	}





	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}





	public String getAdminid() {
		return adminid;
	}





	public void setAdminid(String adminid) {
		this.adminid = adminid;
	}





	public String getSubadminid() {
		return subadminid;
	}





	public void setSubadminid(String subadminid) {
		this.subadminid = subadminid;
	}





	public String getSupermasterid() {
		return supermasterid;
	}





	public void setSupermasterid(String supermasterid) {
		this.supermasterid = supermasterid;
	}





	public int getSportid() {
		return sportid;
	}





	public void setSportid(int sportid) {
		this.sportid = sportid;
	}





	public Double getTotalpnl() {
		return totalpnl;
	}





	public void setTotalpnl(Double totalpnl) {
		this.totalpnl = totalpnl;
	}





	public Double getLiability() {
		return liability;
	}





	public void setLiability(Double liability) {
		this.liability = liability;
	}





	public String getMarketid() {
		return marketid;
	}





	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}





	public Integer getMatchid() {
		return matchid;
	}





	public void setMatchid(Integer matchid) {
		this.matchid = matchid;
	}





	public String getType() {
		return type;
	}





	public void setType(String type) {
		this.type = type;
	}





	public int getSelection1() {
		return selection1;
	}





	public void setSelection1(int selection1) {
		this.selection1 = selection1;
	}





	public Double getAdminpartnership() {
		return adminpartnership;
	}





	public void setAdminpartnership(Double adminpartnership) {
		this.adminpartnership = adminpartnership;
	}





	public Double getMasterpartnership() {
		return masterpartnership;
	}





	public void setMasterpartnership(Double masterpartnership) {
		this.masterpartnership = masterpartnership;
	}





	public Double getDealerpartnership() {
		return dealerpartnership;
	}





	public void setDealerpartnership(Double dealerpartnership) {
		this.dealerpartnership = dealerpartnership;
	}





	public Double getSubadminpartnership() {
		return subadminpartnership;
	}





	public void setSubadminpartnership(Double subadminpartnership) {
		this.subadminpartnership = subadminpartnership;
	}





	public Double getSupermasterpartnership() {
		return supermasterpartnership;
	}





	public void setSupermasterpartnership(Double supermasterpartnership) {
		this.supermasterpartnership = supermasterpartnership;
	}





	public int getSelection2() {
		return selection2;
	}





	public void setSelection2(int selection2) {
		this.selection2 = selection2;
	}





	public int getSelection3() {
		return selection3;
	}





	public void setSelection3(int selection3) {
		this.selection3 = selection3;
	}





	public Double getPnl1() {
		return pnl1;
	}





	public void setPnl1(Double pnl1) {
		this.pnl1 = pnl1;
	}





	public Double getPnl2() {
		return pnl2;
	}





	public void setPnl2(Double pnl2) {
		this.pnl2 = pnl2;
	}





	public Double getPnl3() {
		return pnl3;
	}





	public void setPnl3(Double pnl3) {
		this.pnl3 = pnl3;
	}





	public String getMatchname() {
		return matchname;
	}





	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}





	public Boolean getIsactive() {
		return isactive;
	}





	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}





	public Double getNetpnl() {
		return netpnl;
	}





	public void setNetpnl(Double netpnl) {
		this.netpnl = netpnl;
	}





	public Boolean getIsresultset() {
		return isresultset;
	}





	public void setIsresultset(Boolean isresultset) {
		this.isresultset = isresultset;
	}





	public Double getOldlibility() {
		return oldlibility;
	}





	public void setOldlibility(Double oldlibility) {
		this.oldlibility = oldlibility;
	}





	public Double getOldpnl1() {
		return oldpnl1;
	}





	public void setOldpnl1(Double oldpnl1) {
		this.oldpnl1 = oldpnl1;
	}





	public Double getOldpnl2() {
		return oldpnl2;
	}





	public void setOldpnl2(Double oldpnl2) {
		this.oldpnl2 = oldpnl2;
	}





	public Double getOldpnl3() {
		return oldpnl3;
	}





	public void setOldpnl3(Double oldpnl3) {
		this.oldpnl3 = oldpnl3;
	}





	public Double getSbaCommission() {
		return sbaCommission;
	}





	public void setSbaCommission(Double sbaCommission) {
		this.sbaCommission = sbaCommission;
	}





	public Double getSumCommission() {
		return sumCommission;
	}





	public void setSumCommission(Double sumCommission) {
		this.sumCommission = sumCommission;
	}





	public Double getMastCommission() {
		return mastCommission;
	}





	public void setMastCommission(Double mastCommission) {
		this.mastCommission = mastCommission;
	}





	public Double getDealCommission() {
		return dealCommission;
	}





	public void setDealCommission(Double dealCommission) {
		this.dealCommission = dealCommission;
	}





	public Double getAdmCommission() {
		return admCommission;
	}





	public void setAdmCommission(Double admCommission) {
		this.admCommission = admCommission;
	}





	public Date getUpdatedon() {
		return updatedon;
	}





	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}





	public Date getCreatedon() {
		return createdon;
	}





	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}





	public Integer getStack() {
		return stack;
	}





	public void setStack(Integer stack) {
		this.stack = stack;
	}





	public Double getAdminPnl() {
		return adminPnl;
	}





	public void setAdminPnl(Double adminPnl) {
		this.adminPnl = adminPnl;
	}





	public Double getSubadminpnl() {
		return subadminpnl;
	}





	public void setSubadminpnl(Double subadminpnl) {
		this.subadminpnl = subadminpnl;
	}





	public Double getSupermasterpnl() {
		return supermasterpnl;
	}





	public void setSupermasterpnl(Double supermasterpnl) {
		this.supermasterpnl = supermasterpnl;
	}





	public Double getMasterpnl() {
		return masterpnl;
	}





	public void setMasterpnl(Double masterpnl) {
		this.masterpnl = masterpnl;
	}





	public Double getDealerpnl() {
		return dealerpnl;
	}





	public void setDealerpnl(Double dealerpnl) {
		this.dealerpnl = dealerpnl;
	}





	public Boolean getAddetoFirestore() {
		return addetoFirestore;
	}





	public void setAddetoFirestore(Boolean addetoFirestore) {
		this.addetoFirestore = addetoFirestore;
	}





	public Boolean getIsLibilityclear() {
		return isLibilityclear;
	}





	public void setIsLibilityclear(Boolean isLibilityclear) {
		this.isLibilityclear = isLibilityclear;
	}





	public Boolean getIsProfitLossclear() {
		return isProfitLossclear;
	}





	public void setIsProfitLossclear(Boolean isProfitLossclear) {
		this.isProfitLossclear = isProfitLossclear;
	}





	public Boolean getIsmongodbupdated() {
		return ismongodbupdated;
	}





	public void setIsmongodbupdated(Boolean ismongodbupdated) {
		this.ismongodbupdated = ismongodbupdated;
	}





	public UserLiability getpnlChidIdWise(Object[] row) {
		DecimalFormat df = new DecimalFormat("#0");
		
		UserLiability bean = new  UserLiability();
		if((Double) row[0]!=null) {
			bean.setPnl1(Double.valueOf(df.format((Double) row[0])));
			bean.setPnl2(Double.valueOf(df.format((Double) row[1])));
			bean.setPnl3(Double.valueOf(df.format((Double) row[2])));
			bean.setUserid((String) row[3]);
			bean.setAdminid((String) row[4]);
			bean.setSubadminid((String) row[5]);
			bean.setSupermasterid((String) row[6]);
			bean.setMasterid((String) row[7]);
			bean.setDealerid((String) row[8]);
		}else {
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
