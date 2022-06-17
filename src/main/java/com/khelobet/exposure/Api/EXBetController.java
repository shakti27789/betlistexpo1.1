package com.khelobet.exposure.Api;

import java.io.IOException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khelobet.exposure.Dao.EXUserPnlDao;
import com.khelobet.exposure.Dao.ExMatchOddsBetDao;
import com.khelobet.exposure.Dao.ExMatchOddsBetMongoCustomRepository;
import com.khelobet.exposure.Dao.impl.EXNativeQueryImpl;
import com.khelobet.exposure.Repository.EXMarketRepository;
import com.khelobet.exposure.Repository.EXUserRepository;
import com.khelobet.exposure.Repository.ExMatchOddsBetMongoRepos;
import com.khelobet.exposure.Repository.SelectionIdRepository;
import com.khelobet.exposure.Repository.UserLiabilityMongoRepository;
import com.khelobet.exposure.bean.EXUser;
import com.khelobet.exposure.bean.ExMatchOddsBet;
import com.khelobet.exposure.bean.ExMatchOddsBetMongo;
import com.khelobet.exposure.bean.Market;
import com.khelobet.exposure.bean.SelectionIds;
import com.khelobet.exposure.bean.UserLiability;
import com.khelobet.exposure.bean.UserLiabilityMongo;
import com.khelobet.exposure.util.EXConstants;


@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping("/api")
public class EXBetController {

	

	@Autowired
	ExMatchOddsBetDao betlistDao;
	
	@Autowired
	EXUserPnlDao userPnlDao;
	
	@Autowired
	EXMarketRepository marketRepository;
	
	@Autowired
	EXUserRepository userRepo;
	
    @Autowired
    UserLiabilityMongoRepository userLibMongo;
    
    @Autowired
	EXNativeQueryImpl nativeQueryDao;
	
    @Autowired
	ExMatchOddsBetMongoRepos exMatchOddsBetRepo;
    
    

	@Autowired
	SelectionIdRepository selectionIdRepository;


	@Autowired
	UserLiabilityMongoRepository userLibRepoMongo; 
	
	@Autowired
	ExMatchOddsBetMongoRepos exMatchOddsBetMongoRepos;
	
	@Autowired
	ExMatchOddsBetMongoCustomRepository mongoCustomRepo;

	
	@RequestMapping(value = "/testApi",method = RequestMethod.GET)
	public boolean testApi(){

		
		System.out.println("shakti");
		
		return true;
  

	}
	
	
	@RequestMapping(value = "/getAdminMatchedBets",method = RequestMethod.GET)
	public ResponseEntity<Object> getAdminMatchedBets(@RequestParam Integer eventid,@RequestParam Integer startpage,@RequestParam Integer endpage,@RequestParam String Id,@RequestParam Integer userType){
		
		
		ArrayList<ExMatchOddsBet> bets = null;
		ArrayList<ExMatchOddsBet> fancybets = null;
		ArrayList<ExMatchOddsBet> bookmakerbets = null;
		
		BigInteger matchoddsbetcount;
		BigInteger fancybetcount;
		BigInteger bookmakerbetcount;
		if(Id!=null){
			
			try {
				if(userType ==4) {
					
					matchoddsbetcount =betlistDao.getMatchedBetsTotalCount(Id, "adminid", true, eventid,EXConstants.MatchOdds);
					bets =betlistDao.getMatchedBets(Id, "adminid", true, eventid,EXConstants.MatchOdds,startpage,endpage,100);
					fancybetcount =betlistDao.getMatchedBetsTotalCount(Id, "adminid", true, eventid,EXConstants.Fancy);
					fancybets =betlistDao.getMatchedBets(Id, "adminid", true, eventid,EXConstants.Fancy,startpage,endpage,100);

					bookmakerbetcount =betlistDao.getMatchedBetsTotalCount(Id, "adminid", true, eventid,EXConstants.Bookmaker);
					bookmakerbets =betlistDao.getMatchedBets(Id, "adminid", true, eventid,EXConstants.Bookmaker,startpage,endpage,100);

				}else if(userType==6) {
					matchoddsbetcount =betlistDao.getMatchedBetsTotalCount("1", "adminid", true, eventid,EXConstants.MatchOdds);
					bets =betlistDao.getMatchedBets("1", "adminid", true, eventid,EXConstants.MatchOdds,startpage,endpage,100);
					fancybetcount =betlistDao.getMatchedBetsTotalCount("1", "adminid", true, eventid,EXConstants.Fancy);
					fancybets =betlistDao.getMatchedBets("1", "adminid", true, eventid,EXConstants.Fancy,startpage,endpage,100);

					bookmakerbetcount =betlistDao.getMatchedBetsTotalCount("1", "adminid", true, eventid,EXConstants.Bookmaker);
					bookmakerbets =betlistDao.getMatchedBets("1", "adminid", true, eventid,EXConstants.Bookmaker,startpage,endpage,100);

				}else if(userType==5) {
					matchoddsbetcount =betlistDao.getMatchedBetsTotalCount(Id, "subadminid", true, eventid,EXConstants.MatchOdds);
					bets =betlistDao.getMatchedBets(Id, "subadminid", true, eventid,EXConstants.MatchOdds,startpage,endpage,100);
					fancybetcount =betlistDao.getMatchedBetsTotalCount(Id, "subadminid", true, eventid,EXConstants.Fancy);
					fancybets =betlistDao.getMatchedBets(Id, "subadminid", true, eventid,EXConstants.Fancy,startpage,endpage,100);
					bookmakerbetcount =betlistDao.getMatchedBetsTotalCount(Id, "subadminid", true, eventid,EXConstants.Bookmaker);
					bookmakerbets =betlistDao.getMatchedBets(Id, "subadminid", true, eventid,EXConstants.Bookmaker,startpage,endpage,100);

				}else if(userType==0) {
					matchoddsbetcount =betlistDao.getMatchedBetsTotalCount(Id, "supermasterid", true, eventid,EXConstants.MatchOdds);
					bets =betlistDao.getMatchedBets(Id, "supermasterid", true, eventid,EXConstants.MatchOdds,startpage,endpage,100);
					fancybetcount =betlistDao.getMatchedBetsTotalCount(Id, "supermasterid", true, eventid,EXConstants.Fancy);
					fancybets =betlistDao.getMatchedBets(Id, "supermasterid", true, eventid,EXConstants.Fancy,startpage,endpage,100);

					bookmakerbetcount =betlistDao.getMatchedBetsTotalCount(Id, "supermasterid", true, eventid,EXConstants.Bookmaker);
					bookmakerbets =betlistDao.getMatchedBets(Id, "supermasterid", true, eventid,EXConstants.Bookmaker,startpage,endpage,100);

				}else if(userType==1) {
					matchoddsbetcount =betlistDao.getMatchedBetsTotalCount(Id, "masterid", true, eventid,EXConstants.MatchOdds);
					bets =betlistDao.getMatchedBets(Id, "masterid", true, eventid,EXConstants.MatchOdds,startpage,endpage,100);
					fancybetcount =betlistDao.getMatchedBetsTotalCount(Id, "masterid", true, eventid,EXConstants.Fancy);
					fancybets =betlistDao.getMatchedBets(Id, "masterid", true, eventid,EXConstants.Fancy,startpage,endpage,100);
					bookmakerbetcount =betlistDao.getMatchedBetsTotalCount(Id, "masterid", true, eventid,EXConstants.Bookmaker);
					bookmakerbets =betlistDao.getMatchedBets(Id, "masterid", true, eventid,EXConstants.Bookmaker,startpage,endpage,100);


				}else {
					matchoddsbetcount =betlistDao.getMatchedBetsTotalCount(Id, "dealerid", true, eventid,EXConstants.MatchOdds);
					bets =betlistDao.getMatchedBets(Id, "dealerid", true, eventid,EXConstants.MatchOdds,startpage,endpage,100);
					fancybetcount =betlistDao.getMatchedBetsTotalCount(Id, "dealerid", true, eventid,EXConstants.Fancy);
					fancybets =betlistDao.getMatchedBets(Id, "dealerid", true, eventid,EXConstants.Fancy,startpage,endpage,100);

					bookmakerbetcount =betlistDao.getMatchedBetsTotalCount(Id, "dealerid", true, eventid,EXConstants.Bookmaker);
					bookmakerbets =betlistDao.getMatchedBets(Id, "dealerid", true, eventid,EXConstants.Bookmaker,startpage,endpage,100);

				}

				
					ArrayList<ExMatchOddsBet> matchedBets = new ArrayList<>();
					ArrayList<ExMatchOddsBet> fancyBets = new ArrayList<>();
					
					LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
					
					
						map.put("fancy", fancybets);
						map.put("fancycount", fancybetcount);
					
					
						map.put("matched", bets);
						map.put("matchedcount", matchoddsbetcount);
						
						map.put("bookmakerbets", bookmakerbets);
						map.put("bookmakerbetcount", bookmakerbetcount);
					
					
					return new ResponseEntity<Object>(map,HttpStatus.OK);	
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
			
		}
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value="/getAddminBet/{matchid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getAddminBet(@PathVariable Integer matchid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException {
	

		List<ExMatchOddsBetMongo> fancybets = new ArrayList<>();
		List<ExMatchOddsBetMongo> bets = new ArrayList<>();
		List<ExMatchOddsBetMongo> bookmakerbets = new ArrayList<>();
		List<ExMatchOddsBetMongo> bookmaker3bets = new ArrayList<>();
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		
		

		
			try {
				
					String serchBy = null;
					
						if (usertype == 4) {
							serchBy = "adminid";
							
							
						} else if (usertype == 6) {
							serchBy = "adminid";
						} else if (usertype == 5) {
							serchBy = "subadminid";
						} else if (usertype == 0) {
							serchBy = "supermasterid";
						} else if (usertype == 1) {
							serchBy = "masterid";
						} else if (usertype == 2) {
							serchBy = "dealerid";
						} 
						
						long matchoddsbetcount =0;
						long fancybetcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,EXConstants.Fancy,null);
						fancybets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,EXConstants.Fancy,100,null);
						
						if(matchid ==28102621) {
							 matchoddsbetcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,EXConstants.Winner,null);
							bets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,EXConstants.Winner,100,null);
						
						}else if(matchid ==28127348) {
							 matchoddsbetcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,EXConstants.Winner,null);
							bets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,EXConstants.Winner,100,null);
						
						}else {
							matchoddsbetcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,EXConstants.MatchOdds,null);
							bets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,EXConstants.MatchOdds,100,null);
						
						}
						
						long bookmakerbetcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,EXConstants.Bookmaker,null);
						bookmakerbets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,EXConstants.Bookmaker,100,null);
						
						long bookmaker3betcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,EXConstants.Bookmaker3,null);
						bookmaker3bets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,EXConstants.Bookmaker3,100,null);
						
					
					
					map.put("fancy", fancybets);
					map.put("fancycount", fancybetcount);
				
				
					map.put("matched", bets);
					map.put("matchedcount", matchoddsbetcount);
					
					map.put("bookmakerbets", bookmakerbets);
					map.put("bookmakerbetcount", bookmakerbetcount);
					
					map.put("bookmaker3bets", bookmaker3bets);
					map.put("bookmaker3betcount", bookmaker3betcount);
					
					
			} catch (Exception e) {
				
			}
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	
	
	
	
	@RequestMapping(value="/getBetsByMarketId/{matchid}/{usertype}/{Id}/{marketId}",method=RequestMethod.GET)
	public ResponseEntity<Object> getBetsByMarketId(@PathVariable Integer matchid,@PathVariable Integer usertype,@PathVariable String Id,@PathVariable String marketId) throws ParseException {
	

		List<ExMatchOddsBetMongo> bets = new ArrayList<>();
		LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
		
		

		
			try {
				
					String serchBy = null;
					
						if (usertype == 4) {
							serchBy = "adminid";
							
							
						} else if (usertype == 6) {
							serchBy = "adminid";
						} else if (usertype == 5) {
							serchBy = "subadminid";
						} else if (usertype == 0) {
							serchBy = "supermasterid";
						} else if (usertype == 1) {
							serchBy = "masterid";
						} else if (usertype == 2) {
							serchBy = "dealerid";
						} 
						
				
						//long betcount = mongoCustomRepo.findBetListByPropertiesCount(matchid, true,  Id,  usertype, serchBy,null,marketId);
						bets = mongoCustomRepo.findBetListByProperties(matchid, true,  Id,  usertype, serchBy,null,100,marketId);
							
				
					
					map.put("fancy", bets);
					//map.put("fancycount", betcount);
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return new ResponseEntity<Object>(map, HttpStatus.OK);
	}
	
	
	
	

	
		@RequestMapping(value = "/getAdminViewMoreBets/{eventId}/{Id}/{userType}/{type}",method = RequestMethod.GET)
		public ResponseEntity<Object>  getAdminViewMoreBets(@PathVariable Integer eventId,@PathVariable String Id,@PathVariable Integer userType,@PathVariable String type){
			
			ArrayList<ExMatchOddsBet> bets = new ArrayList<ExMatchOddsBet>();
			
				try {
					if(userType == 4) {
						
						if(type.equalsIgnoreCase(EXConstants.MatchOdds)) {
							bets =betlistDao.getMatchedBets(Id, "adminid", true, eventId,EXConstants.MatchOdds,0,0,0);
						
							
						}else if(type.equalsIgnoreCase(EXConstants.Bookmaker)) {
							bets =betlistDao.getMatchedBets(Id, "adminid", true, eventId,EXConstants.Bookmaker,0,0,0);

						}else {
							bets =betlistDao.getMatchedBets(Id, "adminid", true, eventId,EXConstants.Fancy,0,0,0);
							
						}
						
						
					}else if(userType ==6) {
						
						
						
						if(type.equalsIgnoreCase(EXConstants.MatchOdds)) {
							bets =betlistDao.getMatchedBets("1", "adminid", true, eventId,EXConstants.MatchOdds,0,0,0);
								
						}else if(type.equalsIgnoreCase(EXConstants.Bookmaker)) {
							bets =betlistDao.getMatchedBets("1", "adminid", true, eventId,EXConstants.Bookmaker,0,0,0);

						}else {
							bets =betlistDao.getMatchedBets("1", "adminid", true, eventId,EXConstants.Fancy,0,0,0);

						}
					}else if(userType ==5) {
						
						if(type.equalsIgnoreCase(EXConstants.MatchOdds)) {
							bets =betlistDao.getMatchedBets(Id, "subadminid", true, eventId,EXConstants.MatchOdds,0,0,0);
								
						}else if(type.equalsIgnoreCase(EXConstants.Bookmaker)) {
							bets =betlistDao.getMatchedBets(Id, "subadminid", true, eventId,EXConstants.Bookmaker,0,0,0);

						}else {
							bets =betlistDao.getMatchedBets(Id, "subadminid", true, eventId,EXConstants.Fancy,0,0,0);
							
							
						}
						
					}else if(userType ==0) {
						
						
						if(type.equalsIgnoreCase(EXConstants.MatchOdds)) {
							bets =betlistDao.getMatchedBets(Id, "supermasterid", true, eventId,EXConstants.MatchOdds,0,0,0);
								
						}else if(type.equalsIgnoreCase(EXConstants.Bookmaker)) {
							bets =betlistDao.getMatchedBets(Id, "supermasterid", true, eventId,EXConstants.Bookmaker,0,0,0);

						}else {
							bets =betlistDao.getMatchedBets(Id, "supermasterid", true, eventId,EXConstants.Fancy,0,0,0);

						}
					}else if(userType ==1) {
						
						if(type.equalsIgnoreCase(EXConstants.MatchOdds)) {
							bets =betlistDao.getMatchedBets(Id, "masterid", true, eventId,EXConstants.MatchOdds,0,0,0);
								
						}else if(type.equalsIgnoreCase(EXConstants.Bookmaker)) {
							bets =betlistDao.getMatchedBets(Id, "masterid", true, eventId,EXConstants.Bookmaker,0,0,0);

							
						}else {
							bets =betlistDao.getMatchedBets(Id, "masterid", true, eventId,EXConstants.Fancy,0,0,0);
							
						}

					}else {
						
						if(type.equalsIgnoreCase(EXConstants.MatchOdds)) {
							bets =betlistDao.getMatchedBets(Id, "dealerid", true, eventId,EXConstants.MatchOdds,0,0,0);
								
						}else if(type.equalsIgnoreCase(EXConstants.Bookmaker)) {
							bets =betlistDao.getMatchedBets(Id, "dealerid", true, eventId,EXConstants.Bookmaker,0,0,0);
							
							
						}else {
							bets =betlistDao.getMatchedBets(Id, "dealerid", true, eventId,EXConstants.Fancy,0,0,0);

						}
					}

					
						
						LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
						
						map.put("matched", bets);
						
						
					   return new ResponseEntity<Object>(map,HttpStatus.OK);
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				
				
			
			return new ResponseEntity<Object>(HttpStatus.OK);
			
			
		}
	 
	 
		@RequestMapping(value="/getUserProfitNew",method=RequestMethod.POST)
		public ResponseEntity<Object> getUserProfitNew(@RequestBody LinkedHashMap<String,String> filterData){
		
			DecimalFormat df = new DecimalFormat("#0.00");
			HashMap<String,ArrayList<UserLiability>> hm = new	HashMap<String,ArrayList<UserLiability>>();
			
				ArrayList<Market> mktlist = marketRepository.findByEventidAndStatus(Integer.parseInt(filterData.get("eventid")), true);				if(mktlist.size()>0) {
						for(Market mkt : mktlist) {
							ArrayList<UserLiability> usersLiability = new ArrayList<>();
							
							ArrayList<UserLiability> usersLiabilitytemp = new ArrayList<>();
							new ArrayList<>();
							
							List<String> ids  = new ArrayList<String>();
							List<String> userids  = new ArrayList<String>();
					
			  
						new ArrayList<String>(); 
						new HashMap<String,String>();
						new UserLiability();
						ArrayList<EXUser> userlist = userRepo.findByParentidAndActive(filterData.get("Id").toString(),true);
						
						if(Integer.parseInt(filterData.get("userType")) == 4){
							for(EXUser bean: userlist){
							   if(bean.getUsertype() ==5){
								  ids.add(bean.getId().toString());
							   }	
							}
							
							usersLiability =   userPnlDao.pnlparentIdWise(ids,5,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							 
						}else if(Integer.parseInt(filterData.get("userType")) == 6){
							userlist = userRepo.findByParentidAndActive("1",true);
							
							for(EXUser bean: userlist){
							   if(bean.getUsertype() ==5){
								   ids.add(bean.getId().toString());
							   }
							}
							usersLiability =   userPnlDao.pnlparentIdWise(ids,5,Integer.parseInt(filterData.get("eventid")),4,mkt.getMarketid());
							
						}
						
						
						else if(Integer.parseInt(filterData.get("userType")) == 5){
								for(EXUser bean: userlist){
							  
								 
								if(bean.getUsertype() ==0){
								    ids.add(bean.getId().toString()); 
								     
							   }else if(bean.getUsertype() ==3){
								       
								   userids.add(bean.getUserid()); 
								   
							   }	
							  
					 		}
							
							usersLiabilitytemp =   userPnlDao.pnlparentIdWise(ids,0,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							usersLiability.addAll(usersLiabilitytemp);
								
								
							
							usersLiabilitytemp =   userPnlDao.pnlparentIdWise(userids,3,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							usersLiability.addAll(usersLiabilitytemp);
							
						}else if(Integer.parseInt(filterData.get("userType")) == 0){
							for(EXUser bean: userlist){
							 if(bean.getUsertype() ==1){
								  	
								 ids.add(bean.getId().toString()); 
								     
							   }else if(bean.getUsertype() ==3){
								   	
								  userids.add(bean.getUserid().toString()); 
								     
							   }	
								 
							}
							
							usersLiabilitytemp =   userPnlDao.pnlparentIdWise(ids,1,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							usersLiability.addAll(usersLiabilitytemp);
							if(userids.size()>0) {
								usersLiabilitytemp =   userPnlDao.pnlparentIdWise(userids,3,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
								usersLiability.addAll(usersLiabilitytemp);
							}
							
						}else if(Integer.parseInt(filterData.get("userType")) == 1){
							for(EXUser bean: userlist){
							 	if(bean.getUsertype() ==2){
								  	
								ids.add(bean.getId().toString()); 
									   
							   }else if(bean.getUsertype() ==3){
								  	
								userids.add(bean.getUserid().toString()); 
								   
							   }
							}
							
							usersLiabilitytemp =   userPnlDao.pnlparentIdWise(ids,2,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							usersLiability.addAll(usersLiabilitytemp);
							
							usersLiabilitytemp =   userPnlDao.pnlparentIdWise(userids,3,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							usersLiability.addAll(usersLiabilitytemp);
						}else if(Integer.parseInt(filterData.get("userType")) == 2){
							for(EXUser bean: userlist){
								userids.add(bean.getUserid()); 
								   	
							    
							}
							usersLiabilitytemp =   userPnlDao.pnlparentIdWise(userids,3,Integer.parseInt(filterData.get("eventid")),Integer.parseInt(filterData.get("userType")),mkt.getMarketid());
							usersLiability.addAll(usersLiabilitytemp);
						}
						
						
						 if(mkt.getMarketname().equalsIgnoreCase(EXConstants.MatchOdds)) {
							 hm.put("bf", usersLiability);
						 }else if(mkt.getMarketname().equalsIgnoreCase(EXConstants.Bookmaker)) {
							 hm.put("bm", usersLiability);
						 }else if(mkt.getMarketname().equalsIgnoreCase(EXConstants.Bookmaker3)) {
							 hm.put("bm3", usersLiability);
						 }
						
						
					}
						return new ResponseEntity<Object>(hm,HttpStatus.OK);
					}
				
			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		
		}
		
		@RequestMapping(value="/getMyProfit",method=RequestMethod.POST)
		public ResponseEntity<Object> getMyProfit(@RequestBody LinkedHashMap<String,String> requestData) throws ParseException{

			DecimalFormat df = new DecimalFormat("#0");
			
		
			String parentId =null;;
			ArrayList<UserLiabilityMongo> lib1 = new ArrayList<UserLiabilityMongo> ();
			parentId = requestData.get("id").toString();
			
		         ArrayList<UserLiability> laibility= new ArrayList<UserLiability>();

		         ArrayList<UserLiabilityMongo> laibilitymongo= new ArrayList<UserLiabilityMongo>();
		        
		         
				   try{
					    ArrayList<Market> mrkt = marketRepository.findByEventidAndIsactiveAndStatus(Integer.parseInt(requestData.get("eventId")), true, true);
						 if(mrkt.size()>0){
							 for(Market bean : mrkt){

							//	 System.out.println(bean.getMarketid());
								 if(requestData.get("usertype").equalsIgnoreCase("4")){
									
									 lib1 =userLibMongo.findByAdminidAndMarketid(parentId.toString(), bean.getMarketid());


								 }else if(requestData.get("usertype").equalsIgnoreCase("6")){

									 lib1 =userLibMongo.findByAdminidAndMarketid("1", bean.getMarketid());

								 }else if(requestData.get("usertype").equalsIgnoreCase("5")){

									 lib1 =userLibMongo.findBySubadminidAndMarketid(parentId.toString(), bean.getMarketid());

								 }else if(requestData.get("usertype").equalsIgnoreCase("0")){
									 lib1 =userLibMongo.findBySupermasteridAndMarketid(parentId.toString(), bean.getMarketid());

								 }else if(requestData.get("usertype").equalsIgnoreCase("1")){

									 lib1 =userLibMongo.findByMasteridAndMarketid(parentId.toString(), bean.getMarketid());

								 }else if(requestData.get("usertype").equalsIgnoreCase("2")){

									 lib1 =userLibMongo.findByDealeridAndMarketid(parentId.toString(), bean.getMarketid());
 
								 }
								 if(bean.getMarketid().equalsIgnoreCase("1.194720868") || bean.getMarketid().equalsIgnoreCase("1.172017987")) {
									UserLiabilityMongo libmongo = getMyProfitWinner(bean, requestData,lib1);
									laibilitymongo.add(libmongo);
									 
								 }else {
									 UserLiability  libb = null;
									 ArrayList<UserLiability> usersLiability = new   ArrayList<UserLiability>();
									 Double pnl1 =0.0;
									 Double pnl2 =0.0;
									 Double pnl3 =0.0;
									 Integer selection1 =0;
									 Integer selection2 =0;
									 Integer selection3 =0;
									 String marketId  =null;
									 for (UserLiabilityMongo document : lib1) {
										 
										 libb = new  UserLiability();

										 if(requestData.get("usertype").equalsIgnoreCase("4")) {
											 libb.setPnl1(document.getPnl1()*document.getAdminpartnership()/100);
											 libb.setPnl2(document.getPnl2()*document.getAdminpartnership()/100);
											 libb.setPnl3(document.getPnl3()*document.getAdminpartnership()/100);

										 }else  if(requestData.get("usertype").equalsIgnoreCase("5")) {
											 libb.setPnl1(document.getPnl1()*document.getSubadminpartnership()/100);
											 libb.setPnl2(document.getPnl2()*document.getSubadminpartnership()/100);
											 libb.setPnl3(document.getPnl3()*document.getSubadminpartnership()/100);

										 }else  if(requestData.get("usertype").equalsIgnoreCase("0")) {
											 libb.setPnl1(document.getPnl1()*document.getSupermasterpartnership()/100);
											 libb.setPnl2(document.getPnl2()*document.getSupermasterpartnership()/100);
											 libb.setPnl3(document.getPnl3()*document.getSupermasterpartnership()/100);

										 }else  if(requestData.get("usertype").equalsIgnoreCase("1")) {
											 libb.setPnl1(document.getPnl1()*document.getMasterpartnership()/100);
											 libb.setPnl2(document.getPnl2()*document.getMasterpartnership()/100);
											 libb.setPnl3(document.getPnl3()*document.getMasterpartnership()/100);

										 }
										 else  if(requestData.get("usertype").equalsIgnoreCase("2")) {
											 libb.setPnl1(document.getPnl1()*document.getDealerpartnership()/100);
											 libb.setPnl2(document.getPnl2()*document.getDealerpartnership()/100);
											 libb.setPnl3(document.getPnl3()*document.getDealerpartnership()/100);

										 }else  if(requestData.get("usertype").equalsIgnoreCase("6")) {
											 libb.setPnl1(document.getPnl1()*document.getAdminpartnership()/100);
											 libb.setPnl2(document.getPnl2()*document.getAdminpartnership()/100);
											 libb.setPnl3(document.getPnl3()*document.getAdminpartnership()/100);

										 }


										 libb.setSelection1(document.getSelection1());
										 libb.setSelection2(document.getSelection2());
										 libb.setSelection3(document.getSelection3());
										 libb.setMarketid(document.getMarketid());
										 usersLiability.add(libb);

										 pnl1 =pnl1+libb.getPnl1();
										 pnl2 =pnl2+libb.getPnl2();
										 pnl3 =pnl3+libb.getPnl3();
										 selection1 =libb.getSelection1();
										 selection2=libb.getSelection2();
										 selection3=libb.getSelection3();
										 marketId =libb.getMarketid();
									 }

									 UserLiabilityMongo  lib = new UserLiabilityMongo();
									 if(usersLiability.size()>0){




										 lib.setMarketid(marketId);
										 lib.setPnl1(Double.valueOf(df.format(-pnl1)));
										 lib.setPnl2(Double.valueOf(df.format(-pnl2)));
										 lib.setPnl3(Double.valueOf(df.format(-pnl3)));
										 lib.setSelection1(selection1);
										 lib.setSelection2(selection2);
										 lib.setSelection3(selection3);
										 laibilitymongo.add(lib);
									 }    
								 }

							
							 }
							   return new ResponseEntity<Object>(laibilitymongo,HttpStatus.OK);	
						   }
						    
					   
				   }catch(Exception e){
					   e.printStackTrace();
				   }
					
			

			 return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		
		
		}
		
		public UserLiabilityMongo getMyProfitWinner(Market bean, LinkedHashMap<String,String> requestData,ArrayList<UserLiabilityMongo> lib) throws ParseException{

			DecimalFormat df = new DecimalFormat("#0");
			
		
			String parentId =null;;
			ArrayList<UserLiabilityMongo> lib1 = new ArrayList<UserLiabilityMongo> ();
			parentId = requestData.get("id").toString();
			
			ArrayList<ExMatchOddsBetMongo> betlist = new ArrayList<ExMatchOddsBetMongo>();
		        
		         
				   try{
					 		 
								    Double pnl1 = 0.0;
									Double pnl2 = 0.0;
									Double pnl3 = 0.0;
									Double pnl4 = 0.0;
									Double pnl5 = 0.0;
									Double pnl6 = 0.0;
									Double pnl7 = 0.0;
									Double pnl8 = 0.0;
									Double pnl9 = 0.0;
									Double pnl10 = 0.0;
									Double pnl11 = 0.0;
									Double pnl12 = 0.0;
									Double pnl13 = 0.0;
									Double pnl14 = 0.0;
									Double pnl15 = 0.0;
									Double pnl16 = 0.0;
									
									
									 Integer selection1 =0;
									 Integer selection2 =0;
									 Integer selection3 =0;
									 Integer selection4 =0;
									 Integer selection5 =0;
									 Integer selection6 =0;
									 Integer selection7 =0;
									 Integer selection8 =0;
									 Integer selection9 =0;
									 Integer selection10 =0;
									 Integer selection11 =0;
									 Integer selection12 =0;
									 Integer selection13 =0;
									 Integer selection14 =0;
									 Integer selection15 =0;
									 Integer selection16 =0;
									 
									 
									
									 
									 
									 
									  if(bean.getMarketid().equalsIgnoreCase("1.193814712")) {
										      selection1 =17162689;
											  selection2 =10782634;
											  selection3 =10782589;
											  selection4 =10782588;
											  selection5 =10782635;
											  selection6 =10791812;
											
											 
									  }else   if(bean.getMarketid().equalsIgnoreCase("1.194720868")) {
									    
										  
										  selection1 =2954281;
										  selection2 =22121561;
										  selection3 =4164048;
										  selection4 =2954263;
										  selection5 =38528100;
										  selection6 =2954266;
										  selection7 =2954260;
										  selection8 =42821393;
										  selection9 =42821394;
										  selection10 =7671295;
										  selection11 =152530;
										  selection12 =228749;
										  selection13 =144199;
										  selection14 =152533;
										  selection15 =2857977;
										  selection16 =3786092;
										  
										 
								  }
									
									
									
									UserLiabilityMongo liblity =null;
								 
								
								   
								  
									for (UserLiabilityMongo libbean : lib) {
									

									    Double tempnl1 = 0.0;
										Double tempnl2 = 0.0;
										Double tempnl3 = 0.0;
										Double tempnl4 = 0.0;
										Double tempnl5 = 0.0;
										Double tempnl6 = 0.0;
										Double tempnl7 = 0.0;
										Double tempnl8 = 0.0;
										Double tempnl9 = 0.0;
										Double tempnl10 = 0.0;
										Double tempnl11 = 0.0;
										Double tempnl12 = 0.0;
										Double tempnl13 = 0.0;
										Double tempnl14 = 0.0;
										Double tempnl15 = 0.0;
										Double tempnl16 = 0.0;
										
											
										
										betlist = exMatchOddsBetRepo.findByUseridAndMarketid(libbean.getUserid(),bean.getMarketid());

												 for(ExMatchOddsBetMongo bt :betlist) {

													
								//World cup t20 odd pnl
														
														
														if(bt.getSelectionid() == 2954281){
															
															if(bt.getIsback()) {
																
																tempnl1 =tempnl1-bt.getPnl();
																tempnl2=tempnl2+bt.getLiability();
																tempnl3=tempnl3+bt.getLiability();
																tempnl4=tempnl4+bt.getLiability();
																tempnl5=tempnl5+bt.getLiability();
																tempnl6=tempnl6+bt.getLiability();
																tempnl7=tempnl7+bt.getLiability();
																tempnl8=tempnl8+bt.getLiability();
																tempnl9=tempnl9+bt.getLiability();
																tempnl10=tempnl10+bt.getLiability();
																/*tempnl11=tempnl11+bt.getLiability();
																tempnl12=tempnl12+bt.getLiability();
																tempnl13=tempnl13+bt.getLiability();
																tempnl14=tempnl14+bt.getLiability();
																tempnl15=tempnl15+bt.getLiability();
																tempnl16=tempnl16+bt.getLiability();*/
																
															}else {
																
																tempnl1 =tempnl1+bt.getLiability();
																tempnl2=tempnl2-bt.getPnl();
																tempnl3=tempnl3-bt.getPnl();
																tempnl4=tempnl4-bt.getPnl();
																tempnl5=tempnl5-bt.getPnl();
																tempnl6=tempnl6-bt.getPnl();
																tempnl7=tempnl7-bt.getPnl();
																tempnl8=tempnl8-bt.getPnl();
																tempnl9=tempnl9-bt.getPnl();
																tempnl10=tempnl10-bt.getPnl();
																/*tempnl11=tempnl11-bt.getPnl();
																tempnl12=tempnl12-bt.getPnl();
																tempnl13=tempnl13-bt.getPnl();
																tempnl14=tempnl14-bt.getPnl();
																tempnl15=tempnl15-bt.getPnl();
																tempnl16=tempnl16-bt.getPnl();*/
																
															}
														}else if(bt.getSelectionid() == 22121561){

															
															if(bt.getIsback()) {
																	
																	tempnl1 =tempnl1+bt.getLiability();
																	tempnl2=tempnl2-bt.getPnl();
																	tempnl3=tempnl3+bt.getLiability();
																	tempnl4=tempnl4+bt.getLiability();
																	tempnl5=tempnl5+bt.getLiability();
																	tempnl6=tempnl6+bt.getLiability();
																	tempnl7=tempnl7+bt.getLiability();
																	tempnl8=tempnl8+bt.getLiability();
																	tempnl9=tempnl9+bt.getLiability();
																	tempnl10=tempnl10+bt.getLiability();
																	/*tempnl11=tempnl11+bt.getLiability();
																	tempnl12=tempnl12+bt.getLiability();
																	tempnl13=tempnl13+bt.getLiability();
																	tempnl14=tempnl14+bt.getLiability();
																	tempnl15=tempnl15+bt.getLiability();
																	tempnl16=tempnl16+bt.getLiability();
																*/	
																}else {
																	
																	tempnl1 =tempnl1-bt.getPnl();
																	tempnl2=tempnl2+bt.getLiability();
																	tempnl3=tempnl3-bt.getPnl();
																	tempnl4=tempnl4-bt.getPnl();
																	tempnl5=tempnl5-bt.getPnl();
																	tempnl6=tempnl6-bt.getPnl();
																	tempnl7=tempnl7-bt.getPnl();
																	tempnl8=tempnl8-bt.getPnl();
																	tempnl9=tempnl9-bt.getPnl();
																	tempnl10=tempnl10-bt.getPnl();
																/*	tempnl11=tempnl11-bt.getPnl();
																	tempnl12=tempnl12-bt.getPnl();
																	tempnl13=tempnl13-bt.getPnl();
																	tempnl14=tempnl14-bt.getPnl();
																	tempnl15=tempnl15-bt.getPnl();
																	tempnl16=tempnl16-bt.getPnl();
																*/	
																}
																
															}else if(bt.getSelectionid() ==4164048 ){

																
																if(bt.getIsback()) {
																	
																	tempnl1 =tempnl1+bt.getLiability();
																	tempnl2=tempnl2+bt.getLiability();
																	tempnl3=tempnl3-bt.getPnl();
																	tempnl4=tempnl4+bt.getLiability();
																	tempnl5=tempnl5+bt.getLiability();
																	tempnl6=tempnl6+bt.getLiability();
																	tempnl7=tempnl7+bt.getLiability();
																	tempnl8=tempnl8+bt.getLiability();
																	tempnl9=tempnl9+bt.getLiability();
																	tempnl10=tempnl10+bt.getLiability();
																/*	tempnl11=tempnl11+bt.getLiability();
																	tempnl12=tempnl12+bt.getLiability();
																	tempnl13=tempnl13+bt.getLiability();
																	tempnl14=tempnl14+bt.getLiability();
																	tempnl15=tempnl15+bt.getLiability();
																	tempnl16=tempnl16+bt.getLiability();
															*/		
																}else {
																	
																	tempnl1 =tempnl1-bt.getPnl();
																	tempnl2=tempnl2-bt.getPnl();
																	tempnl3=tempnl3+bt.getLiability();
																	tempnl4=tempnl4-bt.getPnl();
																	tempnl5=tempnl5-bt.getPnl();
																	tempnl6=tempnl6-bt.getPnl();
																	tempnl7=tempnl7-bt.getPnl();
																	tempnl8=tempnl8-bt.getPnl();
																	tempnl9=tempnl9-bt.getPnl();
																	tempnl10=tempnl10-bt.getPnl();
																	/*tempnl11=tempnl11-bt.getPnl();
																	tempnl12=tempnl12-bt.getPnl();
																	tempnl13=tempnl13-bt.getPnl();
																	tempnl14=tempnl14-bt.getPnl();
																	tempnl15=tempnl15-bt.getPnl();
																*/	tempnl16=tempnl16-bt.getPnl();
																	
																}
																 
															}else if(bt.getSelectionid() == 2954263){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
															/*		tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
																*/	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																/*	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	*/
																}
																
															}else if(bt.getSelectionid() == 38528100){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
															/*		tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
															*/		
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
															/*		tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																*/	
																}
																
															}else if(bt.getSelectionid() == 2954266){

																
																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();;
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																/*	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();*/
																	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																/*	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																*/	
																}
																  
															}else if(bt.getSelectionid() == 2954260){

																
																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
															/*		tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
															*/		
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																/*	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																*/	
																}
															}
															else if(bt.getSelectionid() == 42821393){
			
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8-bt.getPnl();
															/*		tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
															*/		
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																/*	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	*/
																}
															}
															
															if(bt.getSelectionid() == 42821394){
																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10+bt.getLiability();
													/*				tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
														*/			
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10-bt.getPnl();
																/*	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	*/
																	
																}
															}
															else if(bt.getSelectionid() == 7671295){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10-bt.getPnl();
															/*		tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
																*/	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10+bt.getLiability();
																/*	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																*/	
																}
																
															}/*else if(bt.getSelectionid() ==152530 ){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
																	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	
																}
																 
															}else if(bt.getSelectionid() == 228749){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
																	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	
																}
																
															}else if(bt.getSelectionid() == 144199){

																
																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
																	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	
																}
																
															}else if(bt.getSelectionid() == 152533){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16+bt.getLiability();
																	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16-bt.getPnl();
																	
																}
																  
															}else if(bt.getSelectionid() == 2857977){

																
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16+bt.getLiability();
																	
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16-bt.getPnl();
																	
																}
															}
															else if(bt.getSelectionid() == 3786092){
			
																if(bt.getIsback()) {
																	
																	tempnl1 = tempnl1+bt.getLiability();
																	tempnl2=  tempnl2+bt.getLiability();
																	tempnl3=  tempnl3+bt.getLiability();
																	tempnl4=  tempnl4+bt.getLiability();
																	tempnl5=  tempnl5+bt.getLiability();
																	tempnl6=  tempnl6+bt.getLiability();
																	tempnl7=  tempnl7+bt.getLiability();
																	tempnl8=  tempnl8+bt.getLiability();
																	tempnl9=  tempnl9+bt.getLiability();
																	tempnl10= tempnl10+bt.getLiability();
																	tempnl11= tempnl11+bt.getLiability();
																	tempnl12= tempnl12+bt.getLiability();
																	tempnl13= tempnl13+bt.getLiability();
																	tempnl14= tempnl14+bt.getLiability();
																	tempnl15= tempnl15+bt.getLiability();
																	tempnl16= tempnl16-bt.getPnl();
																}else {
																	
																	tempnl1 = tempnl1-bt.getPnl();
																	tempnl2=  tempnl2-bt.getPnl();
																	tempnl3=  tempnl3-bt.getPnl();
																	tempnl4=  tempnl4-bt.getPnl();
																	tempnl5=  tempnl5-bt.getPnl();
																	tempnl6=  tempnl6-bt.getPnl();
																	tempnl7=  tempnl7-bt.getPnl();
																	tempnl8=  tempnl8-bt.getPnl();
																	tempnl9=  tempnl9-bt.getPnl();
																	tempnl10= tempnl10-bt.getPnl();
																	tempnl11= tempnl11-bt.getPnl();
																	tempnl12= tempnl12-bt.getPnl();
																	tempnl13= tempnl13-bt.getPnl();
																	tempnl14= tempnl14-bt.getPnl();
																	tempnl15= tempnl15-bt.getPnl();
																	tempnl16= tempnl16+bt.getLiability();
																	
																}
															}
														*/
														
											
											 }
										 
										 
							
								 
								 if(requestData.get("usertype").equalsIgnoreCase("4")) {
								
								     tempnl1 = tempnl1*libbean.getAdminpartnership()/100;
									 tempnl2 = tempnl2*libbean.getAdminpartnership()/100;
									 tempnl3 = tempnl3*libbean.getAdminpartnership()/100;
									 tempnl4 = tempnl4*libbean.getAdminpartnership()/100;
									 tempnl5 = tempnl5*libbean.getAdminpartnership()/100;
									 tempnl6 = tempnl6*libbean.getAdminpartnership()/100;
									 tempnl7 = tempnl7*libbean.getAdminpartnership()/100;
									 tempnl8 = tempnl8*libbean.getAdminpartnership()/100;
									 tempnl9 = tempnl9*libbean.getAdminpartnership()/100;
									 tempnl10 = tempnl10*libbean.getAdminpartnership()/100;
								/*	 tempnl11 = tempnl11*libbean.getAdminpartnership()/100;
									 tempnl12 = tempnl12*libbean.getAdminpartnership()/100;
									 tempnl13 = tempnl13*libbean.getAdminpartnership()/100;
									 tempnl14 = tempnl14*libbean.getAdminpartnership()/100;
									 tempnl15 = tempnl15*libbean.getAdminpartnership()/100;
									 tempnl16 = tempnl16*libbean.getAdminpartnership()/100;
								*/	 
									 pnl1 += tempnl1;
									 pnl2 += tempnl2;
									 pnl3 += tempnl3;
									 pnl4 += tempnl4;
									 pnl5 += tempnl5;
									 pnl6 += tempnl6;
									 pnl7 += tempnl7;
									 pnl8 += tempnl8;
									 pnl9 += tempnl9;
									 pnl10 += tempnl10;
									/* pnl11 += tempnl11;
									 pnl12 += tempnl12;
									 pnl13 += tempnl13;
									 pnl14 += tempnl14;
									 pnl15 += tempnl15;
									 pnl16 += tempnl16;
*/
								 }else  if(requestData.get("usertype").equalsIgnoreCase("6")) {
									 
									 tempnl1 = tempnl1*libbean.getAdminpartnership()/100;
									 tempnl2 = tempnl2*libbean.getAdminpartnership()/100;
									 tempnl3 = tempnl3*libbean.getAdminpartnership()/100;
									 tempnl4 = tempnl4*libbean.getAdminpartnership()/100;
									 tempnl5 = tempnl5*libbean.getAdminpartnership()/100;
									 tempnl6 = tempnl6*libbean.getAdminpartnership()/100;
									 tempnl7 = tempnl7*libbean.getAdminpartnership()/100;
									 tempnl8 = tempnl8*libbean.getAdminpartnership()/100;
									 tempnl9 = tempnl9*libbean.getAdminpartnership()/100;
									 tempnl10 = tempnl10*libbean.getAdminpartnership()/100;
								/*	 tempnl11 = tempnl11*libbean.getAdminpartnership()/100;
									 tempnl12 = tempnl12*libbean.getAdminpartnership()/100;
									 tempnl13 = tempnl13*libbean.getAdminpartnership()/100;
									 tempnl14 = tempnl14*libbean.getAdminpartnership()/100;
									 tempnl15 = tempnl15*libbean.getAdminpartnership()/100;
									 tempnl16 = tempnl16*libbean.getAdminpartnership()/100;
									*/ 
									 pnl1 += tempnl1;
									 pnl2 += tempnl2;
									 pnl3 += tempnl3;
									 pnl4 += tempnl4;
									 pnl5 += tempnl5;
									 pnl6 += tempnl6;
									 pnl7 += tempnl7;
									 pnl8 += tempnl8;
									 pnl9 += tempnl9;
									 pnl10 += tempnl10;
									/* pnl11 += tempnl11;
									 pnl12 += tempnl12;
									 pnl13 += tempnl13;
									 pnl14 += tempnl14;
									 pnl15 += tempnl15;
									 pnl16 += tempnl16;
									 */
								 }else  if(requestData.get("usertype").equalsIgnoreCase("5")) {
									
									 tempnl1 = tempnl1*libbean.getSubadminpartnership()/100;
									 tempnl2 = tempnl2*libbean.getSubadminpartnership()/100;
									 tempnl3 = tempnl3*libbean.getSubadminpartnership()/100;
									 tempnl4 = tempnl4*libbean.getSubadminpartnership()/100;
									 tempnl5 = tempnl5*libbean.getSubadminpartnership()/100;
									 tempnl6 = tempnl6*libbean.getSubadminpartnership()/100;
									 tempnl7 = tempnl7*libbean.getSubadminpartnership()/100;
									 tempnl8 = tempnl8*libbean.getSubadminpartnership()/100;
									 tempnl9 = tempnl9*libbean.getSubadminpartnership()/100;
									 tempnl10 = tempnl10*libbean.getSubadminpartnership()/100;
								/*	 tempnl11 = tempnl11*libbean.getSubadminpartnership()/100;
									 tempnl12 = tempnl12*libbean.getSubadminpartnership()/100;
									 tempnl13 = tempnl13*libbean.getSubadminpartnership()/100;
									 tempnl14 = tempnl14*libbean.getSubadminpartnership()/100;
									 tempnl15 = tempnl15*libbean.getSubadminpartnership()/100;
									 tempnl16 = tempnl16*libbean.getSubadminpartnership()/100;
									*/ 
									 pnl1 += tempnl1;
									 pnl2 += tempnl2;
									 pnl3 += tempnl3;
									 pnl4 += tempnl4;
									 pnl5 += tempnl5;
									 pnl6 += tempnl6;
									 pnl7 += tempnl7;
									 pnl8 += tempnl8;
									 pnl9 += tempnl9;
									 pnl10 += tempnl10;
								/*	 pnl11 += tempnl11;
									 pnl12 += tempnl12;
									 pnl13 += tempnl13;
									 pnl14 += tempnl14;
									 pnl15 += tempnl15;
									 pnl16 += tempnl16;
									 */
								 }else  if(requestData.get("usertype").equalsIgnoreCase("0")) {
									 
									 tempnl1 = tempnl1*libbean.getSupermasterpartnership()/100;
									 tempnl2 = tempnl2*libbean.getSupermasterpartnership()/100;
									 tempnl3 = tempnl3*libbean.getSupermasterpartnership()/100;
									 tempnl4 = tempnl4*libbean.getSupermasterpartnership()/100;
									 tempnl5 = tempnl5*libbean.getSupermasterpartnership()/100;
									 tempnl6 = tempnl6*libbean.getSupermasterpartnership()/100;
									 tempnl7 = tempnl7*libbean.getSupermasterpartnership()/100;
									 tempnl8 = tempnl8*libbean.getSupermasterpartnership()/100;
									 tempnl9 = tempnl9*libbean.getSupermasterpartnership()/100;
									 tempnl10 = tempnl10*libbean.getSupermasterpartnership()/100;
									/* tempnl11 = tempnl11*libbean.getSupermasterpartnership()/100;
									 tempnl12 = tempnl12*libbean.getSupermasterpartnership()/100;
									 tempnl13 = tempnl13*libbean.getSupermasterpartnership()/100;
									 tempnl14 = tempnl14*libbean.getSupermasterpartnership()/100;
									 tempnl15 = tempnl15*libbean.getSupermasterpartnership()/100;
									 tempnl16 = tempnl16*libbean.getSupermasterpartnership()/100;
									*/ 
									 pnl1 += tempnl1;
									 pnl2 += tempnl2;
									 pnl3 += tempnl3;
									 pnl4 += tempnl4;
									 pnl5 += tempnl5;
									 pnl6 += tempnl6;
									 pnl7 += tempnl7;
									 pnl8 += tempnl8;
									 pnl9 += tempnl9;
									 pnl10 += tempnl10;
								/*	 pnl11 += tempnl11;
									 pnl12 += tempnl12;
									 pnl13 += tempnl13;
									 pnl14 += tempnl14;
									 pnl15 += tempnl15;
									 pnl16 += tempnl16;
								*/	
								 }else  if(requestData.get("usertype").equalsIgnoreCase("1")) {
									
									 tempnl1 = tempnl1*libbean.getMasterpartnership()/100;
									 tempnl2 = tempnl2*libbean.getMasterpartnership()/100;
									 tempnl3 = tempnl3*libbean.getMasterpartnership()/100;
									 tempnl4 = tempnl4*libbean.getMasterpartnership()/100;
									 tempnl5 = tempnl5*libbean.getMasterpartnership()/100;
									 tempnl6 = tempnl6*libbean.getMasterpartnership()/100;
									 tempnl7 = tempnl7*libbean.getMasterpartnership()/100;
									 tempnl8 = tempnl8*libbean.getMasterpartnership()/100;
									 tempnl9 = tempnl9*libbean.getMasterpartnership()/100;
									 tempnl10 = tempnl10*libbean.getMasterpartnership()/100;
								/*	 tempnl11 = tempnl11*libbean.getMasterpartnership()/100;
									 tempnl12 = tempnl12*libbean.getMasterpartnership()/100;
									 tempnl13 = tempnl13*libbean.getMasterpartnership()/100;
									 tempnl14 = tempnl14*libbean.getMasterpartnership()/100;
									 tempnl15 = tempnl15*libbean.getMasterpartnership()/100;
									 tempnl16 = tempnl16*libbean.getMasterpartnership()/100;
									*/ 
									 pnl1 += tempnl1;
									 pnl2 += tempnl2;
									 pnl3 += tempnl3;
									 pnl4 += tempnl4;
									 pnl5 += tempnl5;
									 pnl6 += tempnl6;
									 pnl7 += tempnl7;
									 pnl8 += tempnl8;
									 pnl9 += tempnl9;
									 pnl10 += tempnl10;
								/*	 pnl11 += tempnl11;
									 pnl12 += tempnl12;
									 pnl13 += tempnl13;
									 pnl14 += tempnl14;
									 pnl15 += tempnl15;
									 pnl16 += tempnl16;
*/
								 }else  if(requestData.get("usertype").equalsIgnoreCase("2")) {
									
									 tempnl1 = tempnl1*libbean.getDealerpartnership()/100;
									 tempnl2 = tempnl2*libbean.getDealerpartnership()/100;
									 tempnl3 = tempnl3*libbean.getDealerpartnership()/100;
									 tempnl4 = tempnl4*libbean.getDealerpartnership()/100;
									 tempnl5 = tempnl5*libbean.getDealerpartnership()/100;
									 tempnl6 = tempnl6*libbean.getDealerpartnership()/100;
									 tempnl7 = tempnl7*libbean.getDealerpartnership()/100;
									 tempnl8 = tempnl8*libbean.getDealerpartnership()/100;
									 tempnl9 = tempnl9*libbean.getDealerpartnership()/100;
									 tempnl10 = tempnl10*libbean.getDealerpartnership()/100;
								/*	 tempnl11 = tempnl11*libbean.getDealerpartnership()/100;
									 tempnl12 = tempnl12*libbean.getDealerpartnership()/100;
									 tempnl13 = tempnl13*libbean.getDealerpartnership()/100;
									 tempnl14 = tempnl14*libbean.getDealerpartnership()/100;
									 tempnl15 = tempnl15*libbean.getDealerpartnership()/100;
									 tempnl16 = tempnl16*libbean.getDealerpartnership()/100;
									*/ 
									 pnl1 += tempnl1;
									 pnl2 += tempnl2;
									 pnl3 += tempnl3;
									 pnl4 += tempnl4;
									 pnl5 += tempnl5;
									 pnl6 += tempnl6;
									 pnl7 += tempnl7;
									 pnl8 += tempnl8;
									 pnl9 += tempnl9;
									 pnl10 += tempnl10;
									/* pnl11 += tempnl11;
									 pnl12 += tempnl12;
									 pnl13 += tempnl13;
									 pnl14 += tempnl14;
									 pnl15 += tempnl15;
									 pnl16 += tempnl16;
									*/
									// jacksynder9229 Koimoi@360
								 }
								
						}		 
									
									//DecimalFormat df = new DecimalFormat("#0.00");
									liblity = new UserLiabilityMongo();
									liblity.setPnl1(Double.valueOf(df.format(pnl1)));
									liblity.setPnl2(Double.valueOf(df.format(pnl2)));
									liblity.setPnl3(Double.valueOf(df.format(pnl3)));
									liblity.setPnl4(Double.valueOf(df.format(pnl4)));
									liblity.setPnl5(Double.valueOf(df.format(pnl5)));
									liblity.setPnl6(Double.valueOf(df.format(pnl6)));
									liblity.setPnl7(Double.valueOf(df.format(pnl7)));
									liblity.setPnl8(Double.valueOf(df.format(pnl8)));
									liblity.setPnl9(Double.valueOf(df.format(pnl9)));
									liblity.setPnl10(Double.valueOf(df.format(pnl10)));
									/*liblity.setPnl11(Double.valueOf(df.format(pnl11)));
									liblity.setPnl12(Double.valueOf(df.format(pnl12)));
									liblity.setPnl13(Double.valueOf(df.format(pnl13)));
									liblity.setPnl14(Double.valueOf(df.format(pnl14)));
									liblity.setPnl15(Double.valueOf(df.format(pnl15)));
									liblity.setPnl16(Double.valueOf(df.format(pnl16)));*/
									liblity.setSelection1(selection1);
									liblity.setSelection2(selection2);
									liblity.setSelection3(selection3);
									liblity.setSelection4(selection4);
									liblity.setSelection5(selection5);
									liblity.setSelection6(selection6);
									liblity.setSelection7(selection7);
									liblity.setSelection8(selection8);
									liblity.setSelection9(selection9);
									liblity.setSelection10(selection10);
									/*liblity.setSelection11(selection11);
									liblity.setSelection12(selection12);
									liblity.setSelection13(selection13);
									liblity.setSelection14(selection14);
									liblity.setSelection15(selection15);
									liblity.setSelection16(selection16);*/
									liblity.setMarketid(bean.getMarketid());
									
									
					
							 
				
						return liblity	;  
			
						    
					   
				   }catch(Exception e){
					   e.printStackTrace();
				   }
				return null;
					
			

			
		}
		
		
		@RequestMapping(value="/getMyProfitMySql",method=RequestMethod.POST)
		public ResponseEntity<Object> getMyProfitMySql(@RequestBody LinkedHashMap<String,String> requestData) throws ParseException{

			String partnership =null;
			DecimalFormat df = new DecimalFormat("#0");
			
			String gropuBypartnership =null;
			String parentId =null;
			String serchBy =null;
			if(requestData.get("usertype")!=null) {
				if(requestData.get("usertype").equalsIgnoreCase("4")){
				
					gropuBypartnership ="lib.adminpartnership";
					parentId = requestData.get("id");
					partnership = "adminpartnership/100";
					serchBy ="adminid";
				}else if(requestData.get("usertype").equalsIgnoreCase("6")){
			
					gropuBypartnership ="lib.adminpartnership";
					parentId = requestData.get("id");
					partnership = "adminpartnership/100";
					serchBy ="adminid";
				}else if(requestData.get("usertype").equalsIgnoreCase("5")){
					
					gropuBypartnership ="lib.subadminpartnership";
					parentId = requestData.get("id");
					partnership = "subadminpartnership/100";
					serchBy ="subadminid";
				}else if(requestData.get("usertype").equalsIgnoreCase("0")){
					gropuBypartnership ="lib.supermasterpartnership";
					parentId = requestData.get("id");
					partnership = "supermasterpartnership/100";
					serchBy ="supermasterid";
				}else if(requestData.get("usertype").equalsIgnoreCase("1")){
					
					gropuBypartnership ="lib.masterpartnership";
					parentId = requestData.get("id");
					partnership = "masterpartnership/100";
					serchBy ="masterid";
				}else {
				
					gropuBypartnership ="lib.dealerpartnership";
					parentId = requestData.get("id");
					partnership = "dealerpartnership/100";
					serchBy ="dealerid";
				}
		         ArrayList<UserLiability> laibility= new ArrayList<UserLiability>();
		         LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 
		        
		         
		         
				   try{
					
						   ArrayList<Market> mrkt = marketRepository.findByEventidAndIsactiveAndStatus(Integer.parseInt(requestData.get("eventId")), true, true);
						   
						   
						
								 
						   if(mrkt.size()>0){
							   for(Market bean : mrkt){
								  
								   
								   
							
										// System.out.println(pnl1);
										   
								   
								   UserLiability  lib = new UserLiability();
								   ArrayList<UserLiability> usersLiability  = nativeQueryDao.userPnl(bean.getMarketid(),parentId.toString(), partnership, Integer.parseInt(requestData.get("usertype")),gropuBypartnership);
								   Double pnl1 =0.0;
									Double pnl2 =0.0;
									Double pnl3 =0.0;
									Integer selection1 =0;
									Integer selection2 =0;
									Integer selection3 =0;
									String marketId  =null;
								   if(usersLiability.size()>0){

									
														
														for(UserLiability libbean : usersLiability){
															pnl1 =pnl1+libbean.getPnl1();
															pnl2 =pnl2+libbean.getPnl2();
															pnl3 =pnl3+libbean.getPnl3();
															selection1 =libbean.getSelection1();
															selection2=libbean.getSelection2();
															selection3=libbean.getSelection3();
															marketId =lib.getMarketid();
														}
														lib.setMarketid(marketId);
														lib.setPnl1(Double.valueOf(df.format(-pnl1)));
														lib.setPnl2(Double.valueOf(df.format(-pnl2)));
														lib.setPnl3(Double.valueOf(df.format(-pnl3)));
														lib.setSelection1(selection1);
														lib.setSelection2(selection2);
														lib.setSelection3(selection3);
														laibility.add(lib);
								   }    
							   }
							   return new ResponseEntity<Object>(laibility,HttpStatus.OK);	
						   }
						    
					   
				   }catch(Exception e){
					   e.printStackTrace();
				   }
					
			}

			 return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		
		
		}
		@RequestMapping(value="/getAddminBetCasino/{matchid}/{usertype}/{Id}",method=RequestMethod.GET)
		public ResponseEntity<Object> getAddminBetCasino(@PathVariable Integer matchid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException {
		

			
			List<ExMatchOddsBetMongo> bets = new ArrayList<>();
			LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
			
			

			
				try {
					
						String serchBy = null;
						
							if (usertype == 4) {
								serchBy = "adminid";
								
								
							} else if (usertype == 6) {
								serchBy = "adminid";
							} else if (usertype == 5) {
								serchBy = "subadminid";
							} else if (usertype == 0) {
								serchBy = "supermasterid";
							} else if (usertype == 1) {
								serchBy = "masterid";
							} else if (usertype == 2) {
								serchBy = "dealerid";
							} 
							
					
							
							
							long matchoddsbetcount = mongoCustomRepo.findBetListByPropertiesCountByMarketId(matchid, true,  Id,  usertype, serchBy,null);
							bets = mongoCustomRepo.findBetListByPropertiesByMarketId(matchid, true,  Id,  usertype, serchBy,null,100);
						
						/*	for(ExMatchOddsBetMongo bt :bets) {
								List<ExMatchOddsBetMongo> bets2 =	exMatchOddsBetMongoRepos.findByMarketidAndIsactive(bt.getMarketid(), true);
								for(ExMatchOddsBetMongo bt2  : bets2) {
									bt2.setIsactive(false);
									exMatchOddsBetMongoRepos.save(bt2);
									
								}
							}
						*/
						
					
						map.put("matched", bets);
						map.put("matchedcount", matchoddsbetcount);
						
					
				} catch (Exception e) {
					
				}
			
			return new ResponseEntity<Object>(map, HttpStatus.OK);
		}
		
		@RequestMapping(value = "/getBetsPush",method = RequestMethod.POST)
		public ResponseEntity<Object> getBetsPush() throws IOException{
			
			//System.out.println(bets.getMatchid());
		ArrayList<UserLiabilityMongo> bets1 =	userLibRepoMongo.findByMatchidAndIsactive(2102101733, true);
			
			Integer i=0;
			for( UserLiabilityMongo data : bets1) {
				
				UserLiabilityMongo obj = 	userLibRepoMongo.findByUseridAndMarketid(data.getUserid(),data.getMarketid());
				
				if(obj == null) {
				//	
					ExMatchOddsBet userLiabilitySqlObj = new ExMatchOddsBet();
					BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
					try {
						BeanUtils.copyProperties(userLiabilitySqlObj,data);
					} catch (Exception e) {
					//	log.info("Exception Occured: " + e.getMessage());
					} 
					
					userLiabilitySqlObj.setAddetoFirestore(true);
				//	placebetrepository.save(userLiabilitySqlObj);
					
					System.out.println(i++);
					
				}
				//System.out.println(i++);
				
				
			}
			
			return null;
			
		}
}
