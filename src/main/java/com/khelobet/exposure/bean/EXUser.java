package com.khelobet.exposure.bean;

import java.math.BigDecimal;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "t_user")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EXUser {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	
	@Column(name = "username")
	private String username;

	
	@Column(name = "userid",unique=true)
	private String userid;

	
	@Column(name = "password")
	private String password;
	
	
	
	
	@Column(name = "contact")
	private String contact;
	
	
	
	
	@Column(name = "betlock")
	private Boolean betlock;
	
	

	
	
	
	@Column(name = "usertype")
	private  Integer usertype;

	@Column(name = "accountlock")
	private  Boolean accountlock;

	@Column(name = "active")
	private  Boolean active;

	
	@Column(name = "parentid")
	private  String parentid;
	
	@Column(name = "adminid")
	private String adminid;
	
	@Column(name = "subadminid")
	private String subadminid;
	
	@Column(name = "supermasterid")
	private String supermasterid;
	
	
	@Column(name = "masterid")
	private String masterid;
	
	@Column(name = "dealerid")
	private String dealerid;
	


	
	@Column(name = "adminpartership")
	private  BigDecimal  adminpartership;
	
	@Column(name = "subadminpartnership")
	private  BigDecimal  subadminpartnership;
	
	@Column(name = "supermastepartnership")
	private  BigDecimal  supermastepartnership;

	@Column(name = "masterpartership")
	private  BigDecimal  masterpartership;

	@Column(name = "delearpartership")
	private  BigDecimal  delearpartership;
	

	
	
	@Column(name = "netexposure")
	private  Integer  netexposure;

	@Column(name = "createdon")
	private String createdon;

	@Column(name = "updaedon")
	private String updaedon;
	


	
	
	@Column(name = "appid")
	private Integer appid;
	
	
	
	@Column(name = "ismultiple")
	private Boolean isMultiple;
	
	@Column(name = "passwordtype")
	private String passwordtype;
	
	
	

	@Column(name = "userchain")
	private String userchain;
	
	@Transient
	private  BigDecimal  partnershipratio;
	
	
	
	@Column(name = "oddsloss")
	private  BigDecimal  oddsloss;
	
	
	
	
	@Column(name = "fancyloss")
	private  BigDecimal  fancyloss;
	
	
	
	
	@Column(name = "oddslosscommisiontype")
	private String  oddslosscommisiontype;
	

	
	@Column(name = "fancylosscommisiontype")
	private String  fancylosscommisiontype;
   
	@Column(name = "parentspartnership")
	private Double  parentspartnership;

	
	@Column(name = "availablebalance")
	private BigDecimal  availableBalance;
	

	
	@Transient
	private String  partnership;
	
	@Transient
	private String  partnershipc;
	
	@Transient
	private  BigDecimal  pnlamount;
	
	@Column(name = "uplineamount")
	private  Double  uplineAmount;
	
	@Column(name = "creditref")
	private  BigDecimal  creditRef;
	
	@Transient
	private Double  totalMasterBalance;
	
	@Transient
	private BigDecimal  downlinecreditRef;
	
	@Transient
	private BigDecimal  downlineavailableBalance;
	
	@Transient
	private Double  downlineWithProfitLoss;
	
	@Transient
	private Double  myProfitLoss;
	
	@Column(name = "availablebalancewithpnl")
	private Double  availableBalanceWithPnl;
	
	
	@Transient
	private Double  currentExpo;
	 
	@Transient
	private String  parentPassword;
	
	

	
	@Column(name = "adminpartershipc")
	private  BigDecimal  adminpartershipc;
	
	@Column(name = "subadminpartnershipc")
	private  BigDecimal  subadminpartnershipc;
	
	@Column(name = "supermastepartnershipc")
	private  BigDecimal  supermastepartnershipc;

	@Column(name = "masterpartershipc")
	private  BigDecimal  masterpartershipc;

	@Column(name = "delearpartershipc")
	private  BigDecimal  delearpartershipc;

	@Transient
	private  BigDecimal  partnershipratioc;
	
	
	


	
	@Transient
	private String  userRoll;
	
	@Transient
	private String appurl;
	
	@Transient
	private String appname;
	
	@Column(name = "betdelay")
	private String betDelay;
	
	@Column(name = "exposorelimit")
	private Double exposoreLimit;
	
	@Transient
	private HashMap<String,HashMap<String,String>> betdelaymap ;
 
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getUserid() {
		return userid;
	}



	public void setUserid(String userid) {
		this.userid = userid;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}





	public String getContact() {
		return contact;
	}



	public void setContact(String contact) {
		this.contact = contact;
	}


	public Boolean getBetlock() {
		return betlock;
	}



	public void setBetlock(Boolean betlock) {
		this.betlock = betlock;
	}






	public Integer getUsertype() {
		return usertype;
	}



	public void setUsertype(Integer usertype) {
		this.usertype = usertype;
	}



	public Boolean getAccountlock() {
		return accountlock;
	}



	public void setAccountlock(Boolean accountlock) {
		this.accountlock = accountlock;
	}



	


	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	public String getParentid() {
		return parentid;
	}



	public void setParentid(String parentid) {
		this.parentid = parentid;
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



	public String getMasterid() {
		return masterid;
	}



	public void setMasterid(String masterid) {
		this.masterid = masterid;
	}



	public String getDealerid() {
		return dealerid;
	}



	public void setDealerid(String dealerid) {
		this.dealerid = dealerid;
	}



	public BigDecimal getAdminpartership() {
		return adminpartership;
	}



	public void setAdminpartership(BigDecimal adminpartership) {
		this.adminpartership = adminpartership;
	}



	public BigDecimal getSubadminpartnership() {
		return subadminpartnership;
	}



	public void setSubadminpartnership(BigDecimal subadminpartnership) {
		this.subadminpartnership = subadminpartnership;
	}



	public BigDecimal getSupermastepartnership() {
		return supermastepartnership;
	}



	public void setSupermastepartnership(BigDecimal supermastepartnership) {
		this.supermastepartnership = supermastepartnership;
	}



	public BigDecimal getMasterpartership() {
		return masterpartership;
	}



	public void setMasterpartership(BigDecimal masterpartership) {
		this.masterpartership = masterpartership;
	}



	public BigDecimal getDelearpartership() {
		return delearpartership;
	}



	public void setDelearpartership(BigDecimal delearpartership) {
		this.delearpartership = delearpartership;
	}



	public Integer getNetexposure() {
		return netexposure;
	}



	public void setNetexposure(Integer netexposure) {
		this.netexposure = netexposure;
	}



	public String getCreatedon() {
		return createdon;
	}



	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}



	public String getUpdaedon() {
		return updaedon;
	}



	public void setUpdaedon(String updaedon) {
		this.updaedon = updaedon;
	}






	public Integer getAppid() {
		return appid;
	}



	public void setAppid(Integer appid) {
		this.appid = appid;
	}


	public Boolean getIsMultiple() {
		return isMultiple;
	}



	public void setIsMultiple(Boolean isMultiple) {
		this.isMultiple = isMultiple;
	}



	public String getPasswordtype() {
		return passwordtype;
	}



	public void setPasswordtype(String passwordtype) {
		this.passwordtype = passwordtype;
	}



	public String getUserchain() {
		return userchain;
	}



	public void setUserchain(String userchain) {
		this.userchain = userchain;
	}



	public BigDecimal getPartnershipratio() {
		return partnershipratio;
	}



	public void setPartnershipratio(BigDecimal partnershipratio) {
		this.partnershipratio = partnershipratio;
	}





	public BigDecimal getOddsloss() {
		return oddsloss;
	}



	public void setOddsloss(BigDecimal oddsloss) {
		this.oddsloss = oddsloss;
	}



	



	public BigDecimal getFancyloss() {
		return fancyloss;
	}



	public void setFancyloss(BigDecimal fancyloss) {
		this.fancyloss = fancyloss;
	}



	public String getOddslosscommisiontype() {
		return oddslosscommisiontype;
	}



	public void setOddslosscommisiontype(String oddslosscommisiontype) {
		this.oddslosscommisiontype = oddslosscommisiontype;
	}






	public String getFancylosscommisiontype() {
		return fancylosscommisiontype;
	}



	public void setFancylosscommisiontype(String fancylosscommisiontype) {
		this.fancylosscommisiontype = fancylosscommisiontype;
	}



	public Double getParentspartnership() {
		return parentspartnership;
	}



	public void setParentspartnership(Double parentspartnership) {
		this.parentspartnership = parentspartnership;
	}



	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}



	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}



	public String getPartnership() {
		return partnership;
	}



	public void setPartnership(String partnership) {
		this.partnership = partnership;
	}



	public BigDecimal getPnlamount() {
		return pnlamount;
	}



	public void setPnlamount(BigDecimal pnlamount) {
		this.pnlamount = pnlamount;
	}



	public Double getUplineAmount() {
		return uplineAmount;
	}



	public void setUplineAmount(Double uplineAmount) {
		this.uplineAmount = uplineAmount;
	}



	public BigDecimal getCreditRef() {
		return creditRef;
	}



	public void setCreditRef(BigDecimal creditRef) {
		this.creditRef = creditRef;
	}



	public Double getTotalMasterBalance() {
		return totalMasterBalance;
	}



	public void setTotalMasterBalance(Double totalMasterBalance) {
		this.totalMasterBalance = totalMasterBalance;
	}



	public BigDecimal getDownlinecreditRef() {
		return downlinecreditRef;
	}



	public void setDownlinecreditRef(BigDecimal downlinecreditRef) {
		this.downlinecreditRef = downlinecreditRef;
	}



	public BigDecimal getDownlineavailableBalance() {
		return downlineavailableBalance;
	}



	public void setDownlineavailableBalance(BigDecimal downlineavailableBalance) {
		this.downlineavailableBalance = downlineavailableBalance;
	}



	public Double getDownlineWithProfitLoss() {
		return downlineWithProfitLoss;
	}



	public void setDownlineWithProfitLoss(Double downlineWithProfitLoss) {
		this.downlineWithProfitLoss = downlineWithProfitLoss;
	}



	public Double getMyProfitLoss() {
		return myProfitLoss;
	}



	public void setMyProfitLoss(Double myProfitLoss) {
		this.myProfitLoss = myProfitLoss;
	}



	public Double getAvailableBalanceWithPnl() {
		return availableBalanceWithPnl;
	}



	public void setAvailableBalanceWithPnl(Double availableBalanceWithPnl) {
		this.availableBalanceWithPnl = availableBalanceWithPnl;
	}




	public Double getCurrentExpo() {
		return currentExpo;
	}



	public void setCurrentExpo(Double currentExpo) {
		this.currentExpo = currentExpo;
	}



	public String getParentPassword() {
		return parentPassword;
	}



	public void setParentPassword(String parentPassword) {
		this.parentPassword = parentPassword;
	}



	public BigDecimal getAdminpartershipc() {
		return adminpartershipc;
	}



	public void setAdminpartershipc(BigDecimal adminpartershipc) {
		this.adminpartershipc = adminpartershipc;
	}



	public BigDecimal getSubadminpartnershipc() {
		return subadminpartnershipc;
	}



	public void setSubadminpartnershipc(BigDecimal subadminpartnershipc) {
		this.subadminpartnershipc = subadminpartnershipc;
	}



	public BigDecimal getSupermastepartnershipc() {
		return supermastepartnershipc;
	}



	public void setSupermastepartnershipc(BigDecimal supermastepartnershipc) {
		this.supermastepartnershipc = supermastepartnershipc;
	}



	public BigDecimal getMasterpartershipc() {
		return masterpartershipc;
	}



	public void setMasterpartershipc(BigDecimal masterpartershipc) {
		this.masterpartershipc = masterpartershipc;
	}



	public BigDecimal getDelearpartershipc() {
		return delearpartershipc;
	}



	public void setDelearpartershipc(BigDecimal delearpartershipc) {
		this.delearpartershipc = delearpartershipc;
	}



	public BigDecimal getPartnershipratioc() {
		return partnershipratioc;
	}



	public void setPartnershipratioc(BigDecimal partnershipratioc) {
		this.partnershipratioc = partnershipratioc;
	}






	public String getUserRoll() {
		return userRoll;
	}



	public void setUserRoll(String userRoll) {
		this.userRoll = userRoll;
	}



	public String getAppurl() {
		return appurl;
	}



	public void setAppurl(String appurl) {
		this.appurl = appurl;
	}



	public String getAppname() {
		return appname;
	}



	public void setAppname(String appname) {
		this.appname = appname;
	}



	

	public String getBetDelay() {
		return betDelay;
	}



	public void setBetDelay(String betDelay) {
		this.betDelay = betDelay;
	}



	public HashMap<String, HashMap<String, String>> getBetdelaymap() {
		return betdelaymap;
	}



	public void setBetdelaymap(HashMap<String, HashMap<String, String>> betdelaymap) {
		this.betdelaymap = betdelaymap;
	}



	public Double getExposoreLimit() {
		return exposoreLimit;
	}



	public void setExposoreLimit(Double exposoreLimit) {
		this.exposoreLimit = exposoreLimit;
	}



	public String getPartnershipc() {
		return partnershipc;
	}



	public void setPartnershipc(String partnershipc) {
		this.partnershipc = partnershipc;
	}

}
