package com.khelobet.exposure.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.transaction.TransactionScoped;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "t_matchfancy")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MatchFancy {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@Column(name = "fancyid")
	private String fancyid;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "sportid")
	private Integer sportId;
	
	@Column(name = "sportname")
	private String sportname;
	

	@Column(name = "matchname")
	private String matchname;
	
	@Column(name = "oddstype")
	private String oddstype;
	
	
	
	@Column(name = "status")
	private String status;

	
	@Column(name = "maxliabilityperMarket")
	private Integer maxLiabilityPerMarket;
	
	@Column(name = "betdelay")
	private Integer betDelay;
	
	@Column(name = "isbettable")
	private Integer isBettable;
	

	
	@Column(name = "isplay")
	private Boolean isplay;
	

	
	@Column(name = "minbet")
	private Double minbet;
	
	@Column(name = "maxbet")
	private Double maxbet;
	

	@Column(name = "maxliabilityperbet")
	private Integer maxLiabilityPerBet;
	
	@Column(name = "eventid")
	private Integer eventid;
	
	@Column(name = "runnerid")
	private String runnerid;
	
	@Column(name = "issuspendedbyadmin")
	private Boolean issuspendedByAdmin;
	
	@Column(name = "skyfancyid")
	private String skyfancyid;
	
	@Column(name = "isactive")
	private Boolean isActive;
	
	@Column(name = "is_fs_marked_inactive")
	private Boolean iSfsmarkedInactive;
	

	@Column(name = "isresultfinished")
	private Boolean isresultFinished;
	
	@Column(name = "mtype")
	private String mtype;
	
	@Column(name = "isshow")
	private Boolean isshow;
	
	@Transient
	private String result;

	
	@Transient
	private String ids;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFancyid() {
		return fancyid;
	}

	public void setFancyid(String fancyid) {
		this.fancyid = fancyid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSportId() {
		return sportId;
	}

	public void setSportId(Integer sportId) {
		this.sportId = sportId;
	}

	public String getSportname() {
		return sportname;
	}

	public void setSportname(String sportname) {
		this.sportname = sportname;
	}

	
	public String getMatchname() {
		return matchname;
	}

	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getMaxLiabilityPerMarket() {
		return maxLiabilityPerMarket;
	}

	public void setMaxLiabilityPerMarket(Integer maxLiabilityPerMarket) {
		this.maxLiabilityPerMarket = maxLiabilityPerMarket;
	}

	public Integer getBetDelay() {
		return betDelay;
	}

	public void setBetDelay(Integer betDelay) {
		this.betDelay = betDelay;
	}

	public Integer getIsBettable() {
		return isBettable;
	}

	public void setIsBettable(Integer isBettable) {
		this.isBettable = isBettable;
	}

	public Boolean getIsplay() {
		return isplay;
	}

	public void setIsplay(Boolean isplay) {
		this.isplay = isplay;
	}

	public Double getMinbet() {
		return minbet;
	}

	public void setMinbet(Double minbet) {
		this.minbet = minbet;
	}

	public Double getMaxbet() {
		return maxbet;
	}

	public void setMaxbet(Double maxbet) {
		this.maxbet = maxbet;
	}

	public Integer getMaxLiabilityPerBet() {
		return maxLiabilityPerBet;
	}

	public void setMaxLiabilityPerBet(Integer maxLiabilityPerBet) {
		this.maxLiabilityPerBet = maxLiabilityPerBet;
	}

	public Integer getEventid() {
		return eventid;
	}

	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}

	public String getRunnerid() {
		return runnerid;
	}

	public void setRunnerid(String runnerid) {
		this.runnerid = runnerid;
	}

	public Boolean getIssuspendedByAdmin() {
		return issuspendedByAdmin;
	}

	public void setIssuspendedByAdmin(Boolean issuspendedByAdmin) {
		this.issuspendedByAdmin = issuspendedByAdmin;
	}

	public String getSkyfancyid() {
		return skyfancyid;
	}

	public void setSkyfancyid(String skyfancyid) {
		this.skyfancyid = skyfancyid;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Boolean getiSfsmarkedInactive() {
		return iSfsmarkedInactive;
	}

	public void setiSfsmarkedInactive(Boolean iSfsmarkedInactive) {
		this.iSfsmarkedInactive = iSfsmarkedInactive;
	}

	public Boolean getIsresultFinished() {
		return isresultFinished;
	}

	public void setIsresultFinished(Boolean isresultFinished) {
		this.isresultFinished = isresultFinished;
	}

	public String getMtype() {
		return mtype;
	}

	public void setMtype(String mtype) {
		this.mtype = mtype;
	}

	public Boolean getIsshow() {
		return isshow;
	}

	public void setIsshow(Boolean isshow) {
		this.isshow = isshow;
	}

	public String getOddstype() {
		return oddstype;
	}

	public void setOddstype(String oddstype) {
		this.oddstype = oddstype;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	
	
}