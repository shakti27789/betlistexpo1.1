package com.khelobet.exposure.bean;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
@Data
@Entity
@Table(name = "t_betlist")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExMatchOddsBet {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column(name = "userid")
	private String userid;

	@Column(name = "matchid")
	private Integer matchid;

	@Column(name = "isback")
	private Boolean isback;

	@Column(name = "islay")
	private Boolean islay;

	@Column(name = "pricevalue")
	private Double pricevalue;

	@Column(name = "odds")
	private Double odds;

	@Column(name = "placetime")
	private String placeTime;

	@Column(name = "matchedtime")
	private String matchedtime;

	@Column(name = "marketid")
	private String marketid;

	@Column(name = "marketname")
	private String marketname;

	@Column(name = "status")
	private String status;

	@Column(name = "selectionid")
	private Integer selectionid;

	@Column(name = "selectionname")
	private String selectionname;

	@Column(name = "updatedon")
	private Date updatedon;


	@Column(name = "result")
	private Integer result;

	@Column(name = "resultid")
	private Integer resultid = 0;;

	@Column(name = "resultname")
	private String resultname;

	@Column(name = "deletedby")
	private String deletedBy;

	@Column(name = "type")
	private String type;

	@Column(name = "selectionids")
	private String selectionids;

	@Column(name = "date")
	private String date;

	@Column(name = "parentid")
	private String parentid;

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

	
	@Column(name = "createdon")
	private Date createdon;

	

	@Column(name = "stake")
	private int stake;

	@Column(name = "matchname")
	private String matchname;

	@Column(name = "sportid")
	private int sportid;

	@Column(name = "isactive")
	private Boolean isactive;

	@Column(name = "pnl")
	private Double pnl;

	@Column(name = "liability")
	private Double liability;

	@Column(name = "netpnl")
	private Double netpnl;

	@Column(name = "isdeleted")
	private Boolean isdeleted;

	@Column(name = "userip")
	private String Userip;

	@Column(name = "series")
	private Integer series;

	@Column(name = "userchain")
	private String userchain;

	
	@javax.persistence.Transient
	private Integer serialno;

	@Transient
	private String remarks;

	@javax.persistence.Transient
	private String eventType;

	@Column(name = "oldpnl")
	private Double oldpnl = 0.0;

	@javax.persistence.Transient
	private Double partnership;

	@Column(name = "addedtofirestore")
	private Boolean addetoFirestore;
	
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




	public Integer getMatchid() {
		return matchid;
	}




	public void setMatchid(Integer matchid) {
		this.matchid = matchid;
	}




	public Boolean getIsback() {
		return isback;
	}




	public void setIsback(Boolean isback) {
		this.isback = isback;
	}




	public Boolean getIslay() {
		return islay;
	}




	public void setIslay(Boolean islay) {
		this.islay = islay;
	}




	public Double getPricevalue() {
		return pricevalue;
	}




	public void setPricevalue(Double pricevalue) {
		this.pricevalue = pricevalue;
	}




	public Double getOdds() {
		return odds;
	}




	public void setOdds(Double odds) {
		this.odds = odds;
	}




	public String getPlaceTime() {
		return placeTime;
	}




	public void setPlaceTime(String placeTime) {
		this.placeTime = placeTime;
	}




	public String getMatchedtime() {
		return matchedtime;
	}




	public void setMatchedtime(String matchedtime) {
		this.matchedtime = matchedtime;
	}




	public String getMarketid() {
		return marketid;
	}




	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}




	public String getMarketname() {
		return marketname;
	}




	public void setMarketname(String marketname) {
		this.marketname = marketname;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
	}




	public int getSelectionid() {
		return selectionid;
	}




	public void setSelectionid(int selectionid) {
		this.selectionid = selectionid;
	}




	public String getSelectionname() {
		return selectionname;
	}




	public void setSelectionname(String selectionname) {
		this.selectionname = selectionname;
	}




	public Date getUpdatedon() {
		return updatedon;
	}




	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}




	public Integer getResult() {
		return result;
	}




	public void setResult(Integer result) {
		this.result = result;
	}




	public Integer getResultid() {
		return resultid;
	}




	public void setResultid(Integer resultid) {
		this.resultid = resultid;
	}




	public String getResultname() {
		return resultname;
	}




	public void setResultname(String resultname) {
		this.resultname = resultname;
	}




	public String getDeletedBy() {
		return deletedBy;
	}




	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}




	public String getType() {
		return type;
	}




	public void setType(String type) {
		this.type = type;
	}




	public String getSelectionids() {
		return selectionids;
	}




	public void setSelectionids(String selectionids) {
		this.selectionids = selectionids;
	}




	public String getDate() {
		return date;
	}




	public void setDate(String date) {
		this.date = date;
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




	public Date getCreatedon() {
		return createdon;
	}




	public void setCreatedon(Date createdon) {
		this.createdon = createdon;
	}




	public int getStake() {
		return stake;
	}




	public void setStake(int stake) {
		this.stake = stake;
	}




	public String getMatchname() {
		return matchname;
	}




	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}




	public int getSportid() {
		return sportid;
	}




	public void setSportid(int sportid) {
		this.sportid = sportid;
	}




	public Boolean getIsactive() {
		return isactive;
	}




	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}




	public Double getPnl() {
		return pnl;
	}




	public void setPnl(Double pnl) {
		this.pnl = pnl;
	}




	public Double getLiability() {
		return liability;
	}




	public void setLiability(Double liability) {
		this.liability = liability;
	}




	public Double getNetpnl() {
		return netpnl;
	}




	public void setNetpnl(Double netpnl) {
		this.netpnl = netpnl;
	}




	public Boolean getIsdeleted() {
		return isdeleted;
	}




	public void setIsdeleted(Boolean isdeleted) {
		this.isdeleted = isdeleted;
	}




	public String getUserip() {
		return Userip;
	}




	public void setUserip(String userip) {
		Userip = userip;
	}




	public Integer getSeries() {
		return series;
	}




	public void setSeries(Integer series) {
		this.series = series;
	}




	public String getUserchain() {
		return userchain;
	}




	public void setUserchain(String userchain) {
		this.userchain = userchain;
	}




	public Integer getSerialno() {
		return serialno;
	}




	public void setSerialno(Integer serialno) {
		this.serialno = serialno;
	}




	public String getRemarks() {
		return remarks;
	}




	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}




	public String getEventType() {
		return eventType;
	}




	public void setEventType(String eventType) {
		this.eventType = eventType;
	}




	public Double getOldpnl() {
		return oldpnl;
	}




	public void setOldpnl(Double oldpnl) {
		this.oldpnl = oldpnl;
	}




	public Double getPartnership() {
		return partnership;
	}




	public void setPartnership(Double partnership) {
		this.partnership = partnership;
	}




	public Boolean getAddetoFirestore() {
		return addetoFirestore;
	}




	public void setAddetoFirestore(Boolean addetoFirestore) {
		this.addetoFirestore = addetoFirestore;
	}




	public Boolean getIsmongodbupdated() {
		return ismongodbupdated;
	}




	public void setIsmongodbupdated(Boolean ismongodbupdated) {
		this.ismongodbupdated = ismongodbupdated;
	}




	public ExMatchOddsBet matchedBet (Object[] row) {
	
		
		ExMatchOddsBet bean = new  ExMatchOddsBet();
		bean.setSeries((Integer) row[0]);
		bean.setOdds((Double) row[1]);
		bean.setStake((Integer) row[2]);
		bean.setIsback((Boolean) row[3]);
		bean.setUserid((String) row[4]);
		bean.setPnl((Double) row[5]);
		bean.setLiability((Double) row[6]);
		bean.setMarketname((String) row[7]);
		bean.setMatchedtime((String) row[8]);
		bean.setSelectionname((String) row[9]);
		bean.setSelectionid((Integer) row[10]);
		return bean;
		
	}
	
	
	
	


	public ExMatchOddsBet getFancyBook(Object[] row) {

		ExMatchOddsBet bean = new ExMatchOddsBet();
		bean.setStake(Integer.parseInt(row[0].toString()));
		bean.setOdds((Double) row[1]);
		bean.setPnl((Double) row[2]);
		bean.setLiability((Double) row[3]);
		bean.setPartnership(new BigDecimal(row[4].toString()).doubleValue());
		bean.setIsback((Boolean) row[5]);
		bean.setMarketid((String) row[6]);
		return bean;
	}

}
