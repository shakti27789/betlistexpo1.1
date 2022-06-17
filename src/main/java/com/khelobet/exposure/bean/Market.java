package com.khelobet.exposure.bean;

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
@Table(name = "t_market")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Market {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	
	@Column(name = "marketid")
	private String marketid;
	
	@Column(name = "seriesid")
	private Integer seriesid;
	
	@Column(name = "sportid")
	private Integer sportid;
	
	@Column(name = "eventid")
	private Integer eventid;
	
	@Column(name = "marketname")
	private String marketname;
	
	@Column(name = "matchname")
	private String matchname;
	
	@Column(name = "status")
	private Boolean status;
	
	@Column(name = "isactive")
	private Boolean isactive;
	
	@Column(name = "createdon")
	private String createdon;
	
	@Column(name = "updatedon")
	private String updatedon;
	
	@Column(name = "subadminid")
	private String subadminid;
	
	@Column(name = "supermasterid")
	private String supermasterid;
		
	@Column(name = "ispause")
	private Boolean ispause;
	
	@Column(name = "pauseByAdmin")
	private Boolean pauseByAdmin;
	
	@Column(name = "profitlimit")
	private Double profitlimit;
	
	@Column(name = "losslimit")
	private Double losslimit;
	
	@Column(name = "isunmatched")
	private Boolean isunmatched;
	
	@Column(name = "currentprofit")
	private Double currentprofit;
	
	@Column(name = "currentLoss")
	private Double currentLoss;
	
	@Column(name = "settingactive")
	private Boolean settingactive;
	
	@Column(name = "oddsprovider")
	private String oddsprovider;
	
	@Column(name = "cricexchageid")
	private String cricexchageid;
	
	@Column(name = "isResult")
	private Boolean isResult;
	
	@Column(name = "appid")
	private Integer appid;
	
	@Column(name = "addautoFancy")
	private Boolean addautoFancy;
	
	@Column(name = "opendate")
	private Date opendate;
	
	@Column(name = "startdate")
	private String startdate;
	
	@Transient
	private String date;
		
	
	@Transient
	private String Ids;
	
	@Transient
	private String selectionids;
	
	@Transient
	private Integer minbet;
	
	@Transient
	private Integer maxbet;
	
	@Transient
	private String runnername;
	
	@Transient
	private String fancyprovider;
	
	@Transient
	private Boolean fancypause;
	
	@Transient
	private String sportName;
	
	@Transient
	private String seriesName;
	
	@Column(name = "inplay")
	private Boolean inPlay;
	

	@Column(name = "stopmarketodds")
	private Boolean stopMarketOdds;

	@Column(name = "is_fs_marked_inactive")
	private Boolean iSfsmarkedInactive;

	@Transient
	private String marketIdWithOutDecimal;
	
	public Boolean getIsResult() {
		return isResult;
	}
	public void setIsResult(Boolean isResult) {
		this.isResult = isResult;
	}
	public String getCricexchageid() {
		return cricexchageid;
	}
	public void setCricexchageid(String cricexchageid) {
		this.cricexchageid = cricexchageid;
	}
	public String getOddsprovider() {
		return oddsprovider;
	}
	public void setOddsprovider(String oddsprovider) {
		this.oddsprovider = oddsprovider;
	}
	public Boolean getIspause() {
		return ispause;
	}
	public void setIspause(Boolean ispause) {
		this.ispause = ispause;
	}
	
	public String getMarketid() {
		return marketid;
	}
	public void setMarketid(String marketid) {
		this.marketid = marketid;
	}
	public Integer getSeriesid() {
		return seriesid;
	}
	public void setSeriesid(Integer seriesid) {
		this.seriesid = seriesid;
	}
	
	public Integer getSportid() {
		return sportid;
	}
	public void setSportid(Integer sportid) {
		this.sportid = sportid;
	}
	public Integer getEventid() {
		return eventid;
	}
	public void setEventid(Integer eventid) {
		this.eventid = eventid;
	}
	public String getMarketname() {
		return marketname;
	}
	public void setMarketname(String marketname) {
		this.marketname = marketname;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getCreatedon() {
		return createdon;
	}
	public void setCreatedon(String createdon) {
		this.createdon = createdon;
	}
	public String getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(String updatedon) {
		this.updatedon = updatedon;
	}
	public Boolean getIsactive() {
		return isactive;
	}
	public void setIsactive(Boolean isactive) {
		this.isactive = isactive;
	}
	public String getMatchname() {
		return matchname;
	}
	public void setMatchname(String matchname) {
		this.matchname = matchname;
	}
	public Double getProfitlimit() {
		return profitlimit;
	}
	public void setProfitlimit(Double profitlimit) {
		this.profitlimit = profitlimit;
	}
	public Double getLosslimit() {
		return losslimit;
	}
	public void setLosslimit(Double losslimit) {
		this.losslimit = losslimit;
	}
	public Boolean getIsunmatched() {
		return isunmatched;
	}
	public void setIsunmatched(Boolean isunmatched) {
		this.isunmatched = isunmatched;
	}
	public Double getCurrentprofit() {
		return currentprofit;
	}
	public void setCurrentprofit(Double currentprofit) {
		this.currentprofit = currentprofit;
	}
	public Double getCurrentLoss() {
		return currentLoss;
	}
	public void setCurrentLoss(Double currentLoss) {
		this.currentLoss = currentLoss;
	}
	public Boolean getSettingactive() {
		return settingactive;
	}
	public void setSettingactive(Boolean settingactive) {
		this.settingactive = settingactive;
	}
	public Boolean getPauseByAdmin() {
		return pauseByAdmin;
	}
	public void setPauseByAdmin(Boolean pauseByAdmin) {
		this.pauseByAdmin = pauseByAdmin;
	}
	public String getSupermasterid() {
		return supermasterid;
	}
	public void setSupermasterid(String supermasterid) {
		this.supermasterid = supermasterid;
	}
	public String getSubadminid() {
		return subadminid;
	}
	public void setSubadminid(String subadminid) {
		this.subadminid = subadminid;
	}
	public Integer getAppid() {
		return appid;
	}
	public void setAppid(Integer appid) {
		this.appid = appid;
	}
	public Boolean getAddautoFancy() {
		return addautoFancy;
	}
	public void setAddautoFancy(Boolean addautoFancy) {
		this.addautoFancy = addautoFancy;
	}
	public Date getOpendate() {
		return opendate;
	}
	public void setOpendate(Date opendate) {
		this.opendate = opendate;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getIds() {
		return Ids;
	}
	public void setIds(String ids) {
		Ids = ids;
	}
	public String getSelectionids() {
		return selectionids;
	}
	public void setSelectionids(String selectionids) {
		this.selectionids = selectionids;
	}
	public String getRunnername() {
		return runnername;
	}
	public void setRunnername(String runnername) {
		this.runnername = runnername;
	}
	public Integer getMinbet() {
		return minbet;
	}
	public void setMinbet(Integer minbet) {
		this.minbet = minbet;
	}
	public Integer getMaxbet() {
		return maxbet;
	}
	public void setMaxbet(Integer maxbet) {
		this.maxbet = maxbet;
	}
	public String getFancyprovider() {
		return fancyprovider;
	}
	public void setFancyprovider(String fancyprovider) {
		this.fancyprovider = fancyprovider;
	}
	public Boolean getFancypause() {
		return fancypause;
	}
	public void setFancypause(Boolean fancypause) {
		this.fancypause = fancypause;
	}
	public String getSportName() {
		return sportName;
	}
	public void setSportName(String sportName) {
		this.sportName = sportName;
	}
	public String getSeriesName() {
		return seriesName;
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	public Boolean getInPlay() {
		return inPlay;
	}
	public void setInPlay(Boolean inPlay) {
		this.inPlay = inPlay;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Boolean getStopMarketOdds() {
		return stopMarketOdds;
	}
	public void setStopMarketOdds(Boolean stopMarketOdds) {
		this.stopMarketOdds = stopMarketOdds;
	}
	public Boolean getiSfsmarkedInactive() {
		return iSfsmarkedInactive;
	}
	public void setiSfsmarkedInactive(Boolean iSfsmarkedInactive) {
		this.iSfsmarkedInactive = iSfsmarkedInactive;
	}
	public String getMarketIdWithOutDecimal() {
		return marketIdWithOutDecimal;
	}
	public void setMarketIdWithOutDecimal(String marketIdWithOutDecimal) {
		this.marketIdWithOutDecimal = marketIdWithOutDecimal;
	}
	

}
