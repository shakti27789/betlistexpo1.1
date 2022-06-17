package com.khelobet.exposure.Api;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.khelobet.exposure.Repository.ExMatchOddsBetMongoRepos;
import com.khelobet.exposure.Repository.UserLiabilityMongoRepository;
import com.khelobet.exposure.bean.EXUser;
import com.khelobet.exposure.bean.ExMatchOddsBetMongo;
import com.khelobet.exposure.bean.UserLiabilityMongo;
import com.khelobet.exposure.util.EXConstants;



@ControllerAdvice
@RestController
@CrossOrigin
@RequestMapping("/api")
public class EXCasinoExposureContriller {


	@Autowired
	HttpServletRequest request;

	@Autowired
	ExMatchOddsBetMongoRepos exMatchOddsBetRepo;

	@Autowired
	UserLiabilityMongoRepository userLibRepoMongo; 


	@RequestMapping(value="/getMyProfitT20/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitT20(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{

		if(usertype!=null) {

			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();

			try{
				if(usertype!=null){


					Integer selection1 =1;
					Integer selection3 =3;

					Double pnl1 =0.0;
					Double pnl3 =0.0;

					if(usertype ==4) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}
					if(usertype ==6) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==5) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSubadminid(marketid, true,Id) ;
					}else if(usertype ==0) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSupermasterid(marketid, true,Id) ;
					}else if(usertype ==1) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndMasterid(marketid, true,Id) ;
					}else if(usertype ==2) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndDealerid(marketid, true,Id) ;
					}



					for(UserLiabilityMongo bean : lib){
						/* System.out.println(bean.getMarketid()+"==>"+bean.getAdminpartnership()); */
						
						Double temppnl1 =0.0;
						Double temppnl3 =0.0;
						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
						
							if(bt.getSelectionid() == selection1 ) {
								temppnl1 =temppnl1+ bt.getLiability();	    	
							}else  if(bt.getSelectionid() == selection3 ) {
								temppnl3 =temppnl3+ bt.getLiability();	    	
							}
							
						}

						if(usertype ==4) {
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
						}else if(usertype ==5) {
							pnl1= pnl1+ (temppnl1 * (bean.getSubadminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getSubadminpartnership()/100));
						}else if(usertype ==0) {
							pnl1= pnl1+ (temppnl1 * (bean.getSupermasterpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getSupermasterpartnership()/100));
						}else if(usertype ==1) {
							pnl1= pnl1+ (temppnl1 * (bean.getMasterpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getMasterpartnership()/100));
						}else if(usertype ==2) {
							pnl1= pnl1+ (temppnl1 * (bean.getDealerpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getDealerpartnership()/100));
						}



					}

					hm.put("pnl1", pnl1);
					hm.put("pnl3", pnl3);
					hm.put("selection1", selection1);
					hm.put("selection3", selection3);

					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	@RequestMapping(value="/getMyProfitT20P/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitT20P(@PathVariable String marketid) throws ParseException{


		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 



		try{

			Integer selection1 =1;
			Integer selection2 =2;
			Integer selection3 =3;

			Double pnl1 =0.0;
			Double pnl2 =0.0;
			Double pnl3 =0.0;

			ArrayList<UserLiabilityMongo> lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;
			Integer i =0;		 

			for(UserLiabilityMongo bean : lib){
				i++;
				Double temppnl1 =0.0;
				Double temppnl3 =0.0;
				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {
					if(bt.getSelectionid() == selection1 ) {
						temppnl1 =temppnl1+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() == selection3 ) {
						temppnl3 =temppnl3+ bt.getPnl();	    	
					}
				}
				pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
				pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));


			}

			RestTemplate restTemplate = new RestTemplate();
			hm.put("pnl1", pnl3);
			hm.put("pnl3", pnl1);
			hm.put("selection1", selection1);
			hm.put("selection3", selection3);




			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"tp20.json", String.class);

			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));

			return new ResponseEntity<Object>(hm,HttpStatus.OK);	



		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	@RequestMapping(value="/getMyProfitLucky/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitLucky7(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{

		
		if(usertype!=null) {

			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		
			try{
				if(usertype!=null){




					Double pnl1 =0.0;
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl4 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;



					if(usertype ==4) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==6) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==5) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSubadminid(marketid, true,Id) ;
					}else if(usertype ==0) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSupermasterid(marketid, true,Id) ;
					}else if(usertype ==1) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndMasterid(marketid, true,Id) ;
					}else if(usertype ==2) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndDealerid(marketid, true,Id) ;
					}



					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl4 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;





						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid() == EXConstants.selection1 ) {
								temppnl1 =temppnl1+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
								temppnl2 =temppnl2+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection3 ) {
								temppnl3 =temppnl3+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection4 ) {
								temppnl4 =temppnl4+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
								temppnl5 =temppnl5+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
								temppnl6 =temppnl6+ bt.getPnl();	    	
							}

						}

						if(usertype ==4 || usertype ==6) {
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
							pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));

						} 




					}


					hm.put("pnl1", pnl2);
					hm.put("pnl2",pnl1 );
					hm.put("pnl3", pnl4);
					hm.put("pnl4",pnl3 );
					hm.put("pnl5", pnl6);
					hm.put("pnl6", pnl5);


					hm.put("selection1", EXConstants.selection1);
					hm.put("selection2", EXConstants.selection2);
					hm.put("selection3", EXConstants.selection3);
					hm.put("selection4", EXConstants.selection4);
					hm.put("selection5", EXConstants.selection5);
					hm.put("selection6", EXConstants.selection6);




					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}



	@RequestMapping(value="/getMyProfitLucky7P/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitLucky7P(@PathVariable String marketid) throws ParseException{



		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		RestTemplate restTemplate = new RestTemplate();

		try{



			Double pnl1 =0.0;
			Double pnl2 =0.0;
			Double pnl3 =0.0;
			Double pnl4 =0.0;
			Double pnl5 =0.0;
			Double pnl6 =0.0;



			lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;



			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				Double temppnl2 =0.0;
				Double temppnl3 =0.0;
				Double temppnl4 =0.0;
				Double temppnl5 =0.0;
				Double temppnl6 =0.0;





				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {
					if(bt.getSelectionid() == EXConstants.selection1 ) {
						temppnl1 =temppnl1+ bt.getPnl();    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
						temppnl2 =temppnl2+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection3 ) {
						temppnl3 =temppnl3+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection4 ) {
						temppnl4 =temppnl4+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
						temppnl5 =temppnl5+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
						temppnl6 =temppnl6+ bt.getPnl();	    	
					}

				}


				pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
				pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
				pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
				pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
				pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
				pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));






			}


			hm.put("pnl1", pnl2);
			hm.put("pnl2",pnl1 );
			hm.put("pnl3", pnl4);
			hm.put("pnl4",pnl3 );
			hm.put("pnl5", pnl6);
			hm.put("pnl6", pnl5);


			hm.put("selection1", EXConstants.selection1);
			hm.put("selection2", EXConstants.selection2);
			hm.put("selection3", EXConstants.selection3);
			hm.put("selection4", EXConstants.selection4);
			hm.put("selection5", EXConstants.selection5);
			hm.put("selection6", EXConstants.selection6);


			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"lucky7.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",obj.getString("favour"));
			hm.put("amount",obj.getInt("amount"));


			return new ResponseEntity<Object>(hm,HttpStatus.OK);	



		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	@RequestMapping(value="/getMyProfitVDT20/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVDT20(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{

		
		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();

			try{
				if(usertype!=null){




					Double pnl1 =0.0;
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl4 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;
					Double pnl7 =0.0;
					Double pnl8 =0.0;
					Double pnl22 =0.0;
					Double pnl23 =0.0;
					Double pnl24 =0.0;
					Double pnl25 =0.0;




					if(usertype ==4) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==6) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==5) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSubadminid(marketid, true,Id) ;
					}else if(usertype ==0) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSupermasterid(marketid, true,Id) ;
					}else if(usertype ==1) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndMasterid(marketid, true,Id) ;
					}else if(usertype ==2) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndDealerid(marketid, true,Id) ;
					}



					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl4 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;
						Double temppnl7 =0.0;
						Double temppnl8 =0.0;
						Double temppnl22 =0.0;
						Double temppnl23 =0.0;
						Double temppnl24 =0.0;
						Double temppnl25 =0.0;




						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid() == EXConstants.selection1 ) {
								temppnl1 =temppnl1+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
								temppnl2 =temppnl2+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection3 ) {
								temppnl3 =temppnl3 + bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection4 ) {
								temppnl4 =temppnl4 +  bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
								temppnl5 =temppnl5+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
								temppnl6 =temppnl6+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection7 ) {
								temppnl7 =temppnl7+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection8 ) {
								temppnl8 =temppnl8+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection22 ) {
								temppnl22 =temppnl22+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection23 ) {
								temppnl23 =temppnl23+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection24 ) {
								temppnl24 =temppnl24+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection25 ) {
								temppnl25 =temppnl25+ bt.getPnl();	    	
							}

						}

						if(usertype ==4) {
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
							pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));
							pnl7= pnl7+ (temppnl7 * (bean.getAdminpartnership()/100));
							pnl8= pnl8+ (temppnl8 * (bean.getAdminpartnership()/100));
							pnl22= pnl22+ (temppnl22 * (bean.getAdminpartnership()/100));
							pnl23= pnl23+ (temppnl23 * (bean.getAdminpartnership()/100));
							pnl24= pnl24+ (temppnl24 * (bean.getAdminpartnership()/100));
							pnl25= pnl25+ (temppnl25 * (bean.getAdminpartnership()/100));

						} 
					}


					hm.put("pnl1", pnl2);
					hm.put("pnl2",pnl1);
					hm.put("pnl3", pnl3);
					hm.put("pnl4",pnl4 );
					hm.put("pnl5", pnl6);
					hm.put("pnl6", pnl5);
					hm.put("pnl7", pnl8);
					hm.put("pnl8", pnl7);
					hm.put("pnl22", pnl23);
					hm.put("pnl23", pnl22);
					hm.put("pnl24", pnl25);
					hm.put("pnl25", pnl24);


					hm.put("selection1", EXConstants.selection1);
					hm.put("selection2", EXConstants.selection2);
					hm.put("selection3", EXConstants.selection3);
					hm.put("selection4", EXConstants.selection4);
					hm.put("selection5", EXConstants.selection5);
					hm.put("selection6", EXConstants.selection6);
					hm.put("selection7", EXConstants.selection7);
					hm.put("selection8", EXConstants.selection8);
					hm.put("selection9", EXConstants.selection9);
					hm.put("selection10", EXConstants.selection10);
					hm.put("selection11", EXConstants.selection11);
					hm.put("selection12", EXConstants.selection12);




					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				}
			}catch(Exception e){
				e.printStackTrace();
			}

		
		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	
	
	@RequestMapping(value="/getMyProfitVDT20P/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVDT20P(@PathVariable String marketid) throws ParseException{

		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		RestTemplate restTemplate = new RestTemplate();

		try{




			Double pnl1 =0.0;
			Double pnl2 =0.0;
			Double pnl3 =0.0;
			Double pnl4 =0.0;
			Double pnl5 =0.0;
			Double pnl6 =0.0;
			Double pnl7 =0.0;
			Double pnl8 =0.0;
			Double pnl22 =0.0;
			Double pnl23 =0.0;
			Double pnl24 =0.0;
			Double pnl25 =0.0;




			lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;




			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				Double temppnl2 =0.0;
				Double temppnl3 =0.0;
				Double temppnl4 =0.0;
				Double temppnl5 =0.0;
				Double temppnl6 =0.0;
				Double temppnl7 =0.0;
				Double temppnl8 =0.0;
				Double temppnl22 =0.0;
				Double temppnl23 =0.0;
				Double temppnl24 =0.0;
				Double temppnl25 =0.0;




				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {
					if(bt.getSelectionid() == EXConstants.selection1 ) {
						temppnl1 =temppnl1+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
						temppnl2 =temppnl2+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection3 ) {
						temppnl3 =temppnl3 + bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection4 ) {
						temppnl4 =temppnl4 +  bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
						temppnl5 =temppnl5+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
						temppnl6 =temppnl6+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection7 ) {
						temppnl7 =temppnl7+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection8 ) {
						temppnl8 =temppnl8+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection22 ) {
						temppnl22 =temppnl22+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection23 ) {
						temppnl23 =temppnl23+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection24 ) {
						temppnl24 =temppnl24+ bt.getPnl();	    	
					}else  if(bt.getSelectionid() ==  EXConstants.selection25 ) {
						temppnl25 =temppnl25+ bt.getPnl();	    	
					}

				}


				pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
				pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
				pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
				pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
				pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
				pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));
				pnl7= pnl7+ (temppnl7 * (bean.getAdminpartnership()/100));
				pnl8= pnl8+ (temppnl8 * (bean.getAdminpartnership()/100));
				pnl22= pnl22+ (temppnl22 * (bean.getAdminpartnership()/100));
				pnl23= pnl23+ (temppnl23 * (bean.getAdminpartnership()/100));
				pnl24= pnl24+ (temppnl24 * (bean.getAdminpartnership()/100));
				pnl25= pnl25+ (temppnl25 * (bean.getAdminpartnership()/100));


			}


			hm.put("pnl1", pnl2);
			hm.put("pnl2",pnl1);
			hm.put("pnl3", pnl3);
			hm.put("pnl4",pnl4 );
			hm.put("pnl5", pnl6);
			hm.put("pnl6", pnl5);
			hm.put("pnl7", pnl8);
			hm.put("pnl8", pnl7);
			hm.put("pnl22", pnl23);
			hm.put("pnl23", pnl22);
			hm.put("pnl24", pnl25);
			hm.put("pnl25", pnl24);



			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"dt20.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));


			return new ResponseEntity<Object>(hm,HttpStatus.OK);	



		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
	@RequestMapping(value="/getMyProfitVDTL20/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVDTL20(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{

		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			//  ArrayList<ExMatchOddsBetMongo> betlist	= new  ArrayList<ExMatchOddsBetMongo>();
			try{
				if(usertype!=null){




					Double pnl1 =0.0;
					Double pnl21 =0.0;
					Double pnl41 =0.0;
					Double pnl5 =0.0;
					Double pnl25 =0.0;
					Double pnl45 =0.0;

					Double pnl3 =0.0;
					Double pnl23 =0.0;
					Double pnl43 =0.0;

					Double pnl4 =0.0;
					Double pnl24 =0.0;
					Double pnl44 =0.0;

					Double pnl2 =0.0;
					Double pnl22 =0.0;
					Double pnl42 =0.0;



					if(usertype ==4) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==6) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==5) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSubadminid(marketid, true,Id) ;
					}else if(usertype ==0) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSupermasterid(marketid, true,Id) ;
					}else if(usertype ==1) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndMasterid(marketid, true,Id) ;
					}else if(usertype ==2) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndDealerid(marketid, true,Id) ;
					}



					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl21 =0.0;
						Double temppnl41 =0.0;

						Double temppnl5 =0.0;
						Double temppnl25 =0.0;
						Double temppnl45 =0.0;

						Double temppnl3 =0.0;
						Double temppnl23 =0.0;
						Double temppnl43 =0.0;

						Double temppnl4 =0.0;
						Double temppnl24 =0.0;
						Double temppnl44 =0.0;

						Double temppnl2 =0.0;
						Double temppnl22 =0.0;
						Double temppnl42 =0.0;


						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {



							if(bt.getSelectionid() == EXConstants.selection1){

								temppnl1 =temppnl1-bt.getPnl();
								temppnl21=temppnl21+bt.getLiability();
								temppnl41=temppnl41+bt.getLiability();

							}else if(bt.getSelectionid() == EXConstants.selection21){



								temppnl1 =temppnl1+bt.getLiability();
								temppnl21=temppnl21-bt.getPnl();
								temppnl41=temppnl41+bt.getLiability();

							}else if(bt.getSelectionid() == EXConstants.selection41){

								temppnl1 =temppnl1+bt.getLiability();
								temppnl21=temppnl21+bt.getLiability();
								temppnl41=temppnl41-bt.getPnl();
							}
							/*even pnl*/
							else if(bt.getSelectionid() == EXConstants.selection5){

								temppnl5 =temppnl5+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection25){

								temppnl25 =temppnl25+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection45){

								temppnl45 =temppnl45+bt.getPnl();

							}
							/*odd pnl*/
							else if(bt.getSelectionid() == EXConstants.selection4){

								temppnl4 =temppnl4+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection24){

								temppnl24 =temppnl24+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection44){

								temppnl44 =temppnl44+bt.getPnl();

							}

							/*red pnl*/
							else if(bt.getSelectionid() == EXConstants.selection3){

								temppnl3 =temppnl3+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection23){

								temppnl23 =temppnl23+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection43){

								temppnl43 =temppnl43+bt.getPnl();

							}

							/*black pnl*/
							else if(bt.getSelectionid() == EXConstants.selection2){

								temppnl2 =temppnl2+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection22){

								temppnl22 =temppnl22+bt.getPnl();

							}

							else if(bt.getSelectionid() == EXConstants.selection42){

								temppnl42 =temppnl42+bt.getPnl();

							}

						}

						if(usertype ==4 || usertype ==6) {
							pnl1  = pnl1  + (temppnl1 * (bean.getAdminpartnership()/100));
							pnl21 = pnl21 + (temppnl21 *(bean.getAdminpartnership()/100));
							pnl41 = pnl41 + (temppnl41 * (bean.getAdminpartnership()/100));

							pnl5 = pnl5 + (temppnl5 * (bean.getAdminpartnership()/100));
							pnl25 = pnl25 + (temppnl25 * (bean.getAdminpartnership()/100));
							pnl45 = pnl45 + (temppnl45 * (bean.getAdminpartnership()/100));

							pnl4 = pnl4 + (temppnl4 * (bean.getAdminpartnership()/100));
							pnl24 = pnl24 + (temppnl24 * (bean.getAdminpartnership()/100));
							pnl44 = pnl44 + (temppnl44 * (bean.getAdminpartnership()/100));

							pnl3 = pnl3 + (temppnl3 * (bean.getAdminpartnership()/100));
							pnl23 = pnl23 + (temppnl23 * (bean.getAdminpartnership()/100));
							pnl43 = pnl43 + (temppnl43 * (bean.getAdminpartnership()/100));

							pnl2 = pnl2 + (temppnl2 * (bean.getAdminpartnership()/100));
							pnl22 = pnl22 + (temppnl22 * (bean.getAdminpartnership()/100));
							pnl42 = pnl42 + (temppnl42 * (bean.getAdminpartnership()/100));


						} 
					}

					hm.put("pnl1", pnl1);
					hm.put("pnl21",pnl21 );
					hm.put("pnl41", pnl41);

					hm.put("pnl5", pnl4);
					hm.put("pnl25",pnl24 );
					hm.put("pnl45", pnl44);

					hm.put("pnl4", pnl5);
					hm.put("pnl42",pnl25 );
					hm.put("pnl44", pnl45);

					hm.put("pnl3", pnl2);
					hm.put("pnl23",pnl22 );
					hm.put("pnl43", pnl42);

					hm.put("pnl2", pnl3);
					hm.put("pnl22",pnl23 );
					hm.put("pnl42", pnl43);



					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				}
			}catch(Exception e){
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	
	
	
	@RequestMapping(value="/getMyProfitVAAA/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVAAA(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{



		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		try{

          
			Double pnl1 =0.0;
			Double pnl21 =0.0;
			Double pnl5 =0.0;
			
			Double pnl3 =0.0;
			

			Double pnl4 =0.0;
		

			Double pnl2 =0.0;
			Double pnl22 =0.0;
			
			Double pnl6 =0.0;
			Double pnl7 =0.0;
			DecimalFormat df1 = new DecimalFormat("#0");


			if(usertype ==4 || usertype ==6) {
		
				
		    lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;


			
			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				Double temppnl21 =0.0;
				
				Double temppnl5 =0.0;
				
				Double temppnl3 =0.0;
				
				Double temppnl4 =0.0;
				
				Double temppnl2 =0.0;
				Double temppnl22 =0.0;
				
				Double temppnl6 =0.0;
				Double temppnl7 =0.0;

			
				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {



					if(bt.getSelectionid() == EXConstants.selection1){

						if(bt.getIsback()) {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
						}else {
							temppnl1 =temppnl1+bt.getLiability();;
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
						}



					}else if(bt.getSelectionid() == EXConstants.selection2){


						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
						}

					}else if(bt.getSelectionid() == EXConstants.selection3){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
						}
					}
					/*even pnl*/
					else if(bt.getSelectionid() == EXConstants.selection4){

						temppnl4 =temppnl4+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection5){

						temppnl5 =temppnl5+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection6){

						temppnl6 =temppnl6+bt.getPnl();

					}
					/*odd pnl*/
					else if(bt.getSelectionid() == EXConstants.selection7){

						temppnl7 =temppnl7+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection21){

						temppnl21 =temppnl21+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection22){

						temppnl22 =temppnl22+bt.getPnl();

					}


				}

				pnl1  = pnl1  + (temppnl1 * (bean.getAdminpartnership()/100));
				pnl2 = pnl2 + (temppnl2 *(bean.getAdminpartnership()/100));
				pnl3 = pnl3 + (temppnl3 * (bean.getAdminpartnership()/100));

				pnl4 = pnl4 + (temppnl4 * (bean.getAdminpartnership()/100));
				pnl5 = pnl5 + (temppnl5 * (bean.getAdminpartnership()/100));

				pnl6 = pnl6 + (temppnl6 * (bean.getAdminpartnership()/100));
				pnl7 = pnl7 + (temppnl7 * (bean.getAdminpartnership()/100));

				pnl21 = pnl21 + (temppnl21 * (bean.getAdminpartnership()/100));
				pnl22 = pnl22 + (temppnl22 * (bean.getAdminpartnership()/100));


			}
		}

			hm.put("pnl1", Double.valueOf(df1.format(pnl1)));
			hm.put("pnl2",Double.valueOf(df1.format(pnl2)) );
			hm.put("pnl3", Double.valueOf(df1.format(pnl3)));

			hm.put("pnl4", Double.valueOf(df1.format(pnl5)));
			hm.put("pnl5",Double.valueOf(df1.format(pnl4)) );

			hm.put("pnl6", Double.valueOf(df1.format(pnl7)));
			hm.put("pnl7",Double.valueOf(df1.format(pnl6)) );


			hm.put("pnl21", Double.valueOf(df1.format(pnl22)));
			hm.put("pnl22",Double.valueOf(df1.format(pnl21)));


			RestTemplate restTemplate = new RestTemplate();
			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"aaa.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));

			return new ResponseEntity<Object>(hm,HttpStatus.OK);	


		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	@RequestMapping(value="/getMyProfitVAAAP/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVAAAP(@PathVariable String marketid) throws ParseException{



		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		try{

          
			Double pnl1 =0.0;
			Double pnl21 =0.0;
			Double pnl5 =0.0;
			
			Double pnl3 =0.0;
			

			Double pnl4 =0.0;
		

			Double pnl2 =0.0;
			Double pnl22 =0.0;
			
			Double pnl6 =0.0;
			Double pnl7 =0.0;
			DecimalFormat df1 = new DecimalFormat("#0");


		
				
		    lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;


			
			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				Double temppnl21 =0.0;
				
				Double temppnl5 =0.0;
				
				Double temppnl3 =0.0;
				
				Double temppnl4 =0.0;
				
				Double temppnl2 =0.0;
				Double temppnl22 =0.0;
				
				Double temppnl6 =0.0;
				Double temppnl7 =0.0;

			
				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {



					if(bt.getSelectionid() == EXConstants.selection1){

						if(bt.getIsback()) {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
						}else {
							temppnl1 =temppnl1+bt.getLiability();;
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
						}



					}else if(bt.getSelectionid() == EXConstants.selection2){


						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
						}

					}else if(bt.getSelectionid() == EXConstants.selection3){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
						}
					}
					/*even pnl*/
					else if(bt.getSelectionid() == EXConstants.selection4){

						temppnl4 =temppnl4+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection5){

						temppnl5 =temppnl5+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection6){

						temppnl6 =temppnl6+bt.getPnl();

					}
					/*odd pnl*/
					else if(bt.getSelectionid() == EXConstants.selection7){

						temppnl7 =temppnl7+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection21){

						temppnl21 =temppnl21+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection22){

						temppnl22 =temppnl22+bt.getPnl();

					}


				}

				pnl1  = pnl1  + (temppnl1 * (bean.getAdminpartnership()/100));
				pnl2 = pnl2 + (temppnl2 *(bean.getAdminpartnership()/100));
				pnl3 = pnl3 + (temppnl3 * (bean.getAdminpartnership()/100));

				pnl4 = pnl4 + (temppnl4 * (bean.getAdminpartnership()/100));
				pnl5 = pnl5 + (temppnl5 * (bean.getAdminpartnership()/100));

				pnl6 = pnl6 + (temppnl6 * (bean.getAdminpartnership()/100));
				pnl7 = pnl7 + (temppnl7 * (bean.getAdminpartnership()/100));

				pnl21 = pnl21 + (temppnl21 * (bean.getAdminpartnership()/100));
				pnl22 = pnl22 + (temppnl22 * (bean.getAdminpartnership()/100));


			}
		

			hm.put("pnl1", Double.valueOf(df1.format(pnl1)));
			hm.put("pnl2",Double.valueOf(df1.format(pnl2)) );
			hm.put("pnl3", Double.valueOf(df1.format(pnl3)));

			hm.put("pnl4", Double.valueOf(df1.format(pnl5)));
			hm.put("pnl5",Double.valueOf(df1.format(pnl4)) );

			hm.put("pnl6", Double.valueOf(df1.format(pnl7)));
			hm.put("pnl7",Double.valueOf(df1.format(pnl6)) );


			hm.put("pnl21", Double.valueOf(df1.format(pnl22)));
			hm.put("pnl22",Double.valueOf(df1.format(pnl21)));


			RestTemplate restTemplate = new RestTemplate();
			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"aaa.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));

			return new ResponseEntity<Object>(hm,HttpStatus.OK);	


		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

	@RequestMapping(value="/getMyProfitVDTL20P/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVDTL20P(@PathVariable String marketid) throws ParseException{



		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		try{


			Double pnl1 =0.0;
			Double pnl21 =0.0;
			Double pnl41 =0.0;
			Double pnl5 =0.0;
			Double pnl25 =0.0;
			Double pnl45 =0.0;

			Double pnl3 =0.0;
			Double pnl23 =0.0;
			Double pnl43 =0.0;

			Double pnl4 =0.0;
			Double pnl24 =0.0;
			Double pnl44 =0.0;

			Double pnl2 =0.0;
			Double pnl22 =0.0;
			Double pnl42 =0.0;



			lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;



			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				Double temppnl21 =0.0;
				Double temppnl41 =0.0;

				Double temppnl5 =0.0;
				Double temppnl25 =0.0;
				Double temppnl45 =0.0;

				Double temppnl3 =0.0;
				Double temppnl23 =0.0;
				Double temppnl43 =0.0;

				Double temppnl4 =0.0;
				Double temppnl24 =0.0;
				Double temppnl44 =0.0;

				Double temppnl2 =0.0;
				Double temppnl22 =0.0;
				Double temppnl42 =0.0;


				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {



					if(bt.getSelectionid() == EXConstants.selection1){

						temppnl1 =temppnl1-bt.getPnl();
						temppnl21=temppnl21+bt.getLiability();
						temppnl41=temppnl41+bt.getLiability();

					}else if(bt.getSelectionid() == EXConstants.selection21){



						temppnl1 =temppnl1+bt.getLiability();
						temppnl21=temppnl21-bt.getPnl();
						temppnl41=temppnl41+bt.getLiability();

					}else if(bt.getSelectionid() == EXConstants.selection41){

						temppnl1 =temppnl1+bt.getLiability();
						temppnl21=temppnl21+bt.getLiability();
						temppnl41=temppnl41-bt.getPnl();
					}
					/*even pnl*/
					else if(bt.getSelectionid() == EXConstants.selection5){

						temppnl5 =temppnl5+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection25){

						temppnl25 =temppnl25+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection45){

						temppnl45 =temppnl45+bt.getPnl();

					}
					/*odd pnl*/
					else if(bt.getSelectionid() == EXConstants.selection4){

						temppnl4 =temppnl4+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection24){

						temppnl24 =temppnl24+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection44){

						temppnl44 =temppnl44+bt.getPnl();

					}

					/*red pnl*/
					else if(bt.getSelectionid() == EXConstants.selection3){

						temppnl3 =temppnl3+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection23){

						temppnl23 =temppnl23+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection43){

						temppnl43 =temppnl43+bt.getPnl();

					}

					/*black pnl*/
					else if(bt.getSelectionid() == EXConstants.selection2){

						temppnl2 =temppnl2+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection22){

						temppnl22 =temppnl22+bt.getPnl();

					}

					else if(bt.getSelectionid() == EXConstants.selection42){

						temppnl42 =temppnl42+bt.getPnl();

					}

				}

				pnl1  = pnl1  + (temppnl1 * (bean.getAdminpartnership()/100));
				pnl21 = pnl21 + (temppnl21 *(bean.getAdminpartnership()/100));
				pnl41 = pnl41 + (temppnl41 * (bean.getAdminpartnership()/100));

				pnl5 = pnl5 + (temppnl5 * (bean.getAdminpartnership()/100));
				pnl25 = pnl25 + (temppnl25 * (bean.getAdminpartnership()/100));
				pnl45 = pnl45 + (temppnl45 * (bean.getAdminpartnership()/100));

				pnl4 = pnl4 + (temppnl4 * (bean.getAdminpartnership()/100));
				pnl24 = pnl24 + (temppnl24 * (bean.getAdminpartnership()/100));
				pnl44 = pnl44 + (temppnl44 * (bean.getAdminpartnership()/100));

				pnl3 = pnl3 + (temppnl3 * (bean.getAdminpartnership()/100));
				pnl23 = pnl23 + (temppnl23 * (bean.getAdminpartnership()/100));
				pnl43 = pnl43 + (temppnl43 * (bean.getAdminpartnership()/100));

				pnl2 = pnl2 + (temppnl2 * (bean.getAdminpartnership()/100));
				pnl22 = pnl22 + (temppnl22 * (bean.getAdminpartnership()/100));
				pnl42 = pnl42 + (temppnl42 * (bean.getAdminpartnership()/100));

			}

			hm.put("pnl1", pnl1);
			hm.put("pnl21",pnl21 );
			hm.put("pnl41", pnl41);

			hm.put("pnl5", pnl4);
			hm.put("pnl25",pnl24 );
			hm.put("pnl45", pnl44);

			hm.put("pnl4", pnl5);
			hm.put("pnl24",pnl25 );
			hm.put("pnl44", pnl45);

			hm.put("pnl3", pnl2);
			hm.put("pnl23",pnl22 );
			hm.put("pnl43", pnl42);

			hm.put("pnl2", pnl3);
			hm.put("pnl22",pnl23 );
			hm.put("pnl42", pnl43);


			RestTemplate restTemplate = new RestTemplate();
			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"dtl20.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));

			return new ResponseEntity<Object>(hm,HttpStatus.OK);	


		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
	@RequestMapping(value="/getMyProfitVBWTableP/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVBWTableP(@PathVariable String marketid) throws ParseException{



		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
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
			Double pnl10 =0.0;
			Double pnl11 =0.0;
			Double pnl12 =0.0;
			Double pnl13 =0.0;
			Double pnl14 =0.0;
			
			

			lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

			

			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				
				Double temppnl5 =0.0;
				
				Double temppnl3 =0.0;
				
				Double temppnl4 =0.0;
				
				Double temppnl2 =0.0;
				
				Double temppnl6 =0.0;
				Double temppnl7 =0.0;
				Double temppnl8 =0.0;
				Double temppnl9 =0.0;
				Double temppnl10 =0.0;
				Double temppnl11 =0.0;
				Double temppnl12 =0.0;
				Double temppnl13 =0.0;
				Double temppnl14 =0.0;
				

				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {



					if(bt.getSelectionid() == EXConstants.selection1){

						if(bt.getIsback()) {
							
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
							
						}else {
							
							temppnl1 =temppnl1+bt.getLiability();;
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
							
						}

						
					}else if(bt.getSelectionid() == EXConstants.selection2){


						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
							temppnl3=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
						}

					}else if(bt.getSelectionid() == EXConstants.selection3){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
							
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
						}
					}else if(bt.getSelectionid() == EXConstants.selection4){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
						
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
						}
					}
					else if(bt.getSelectionid() == EXConstants.selection5){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6+bt.getLiability();
							
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6-bt.getPnl();
						}
					}
					else if(bt.getSelectionid() == EXConstants.selection6){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6-bt.getPnl();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6+bt.getLiability();
						}
					}
					
					else if(bt.getSelectionid() == EXConstants.selection7){

						if(bt.getIsback()) {
							temppnl7 =temppnl7+bt.getLiability();
							
						}else {
							temppnl7 =temppnl7-bt.getPnl();
							
						}
					}
					/*even pnl*/
					else if(bt.getSelectionid() == EXConstants.selection8){

						temppnl8 =temppnl8+bt.getLiability();

					}

					else if(bt.getSelectionid() == EXConstants.selection9){

						temppnl9 =temppnl9+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection10){

						temppnl10 =temppnl10+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection11){

						temppnl11 =temppnl11+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection12){

						temppnl12 =temppnl12+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection13){

						temppnl13 =temppnl13+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection14){

						temppnl14 =temppnl14+bt.getLiability();

					}
					
					


				}

				pnl1  = pnl1  + (temppnl1 * (bean.getAdminpartnership()/100));
				pnl2 = pnl2 + (temppnl2 *(bean.getAdminpartnership()/100));
				pnl3 = pnl3 + (temppnl3 * (bean.getAdminpartnership()/100));

				pnl4 = pnl4 + (temppnl4 * (bean.getAdminpartnership()/100));
				pnl5 = pnl5 + (temppnl5 * (bean.getAdminpartnership()/100));

				pnl6 = pnl6 + (temppnl6 * (bean.getAdminpartnership()/100));
				pnl7 = pnl7 + (temppnl7 * (bean.getAdminpartnership()/100));

				pnl8 = pnl8 + (temppnl8 * (bean.getAdminpartnership()/100));
				pnl9 = pnl9 + (temppnl9 * (bean.getAdminpartnership()/100));

				
				pnl10 = pnl10 + (temppnl10 * (bean.getAdminpartnership()/100));
				pnl11 = pnl11 + (temppnl11 * (bean.getAdminpartnership()/100));


				pnl12 = pnl12 + (temppnl2 * (bean.getAdminpartnership()/100));
				pnl13 = pnl13 + (temppnl3 * (bean.getAdminpartnership()/100));
				pnl14 = pnl14 + (temppnl4 * (bean.getAdminpartnership()/100));


			}

			hm.put("pnl1", pnl1);
			hm.put("pnl2",pnl2 );
			hm.put("pnl3", pnl3);

			hm.put("pnl4", pnl4);
			hm.put("pnl5",pnl5 );

			hm.put("pnl6", pnl6);
			
			hm.put("pnl7",pnl7);
			hm.put("pnl8", pnl9);
			hm.put("pnl9",pnl8);
			hm.put("pnl10",pnl1);
			hm.put("pnl11",pnl10);
			hm.put("pnl12",pnl12);
			hm.put("pnl13",pnl13);
			hm.put("pnl14",pnl14);
			


			RestTemplate restTemplate = new RestTemplate();
			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"bwtable.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));

			return new ResponseEntity<Object>(hm,HttpStatus.OK);	


		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
	
	@RequestMapping(value="/getMyProfitVBWTable/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitVBWTable(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{



		LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

		ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
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
			Double pnl10 =0.0;
			Double pnl11 =0.0;
			Double pnl12 =0.0;
			Double pnl13 =0.0;
			Double pnl14 =0.0;
			
			

			lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

			

			for(UserLiabilityMongo bean : lib){

				Double temppnl1 =0.0;
				
				Double temppnl5 =0.0;
				
				Double temppnl3 =0.0;
				
				Double temppnl4 =0.0;
				
				Double temppnl2 =0.0;
				
				Double temppnl6 =0.0;
				Double temppnl7 =0.0;
				Double temppnl8 =0.0;
				Double temppnl9 =0.0;
				Double temppnl10 =0.0;
				Double temppnl11 =0.0;
				Double temppnl12 =0.0;
				Double temppnl13 =0.0;
				Double temppnl14 =0.0;
				

				ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

				for(ExMatchOddsBetMongo bt :betlist) {



					if(bt.getSelectionid() == EXConstants.selection1){

						if(bt.getIsback()) {
							
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
							
						}else {
							
							temppnl1 =temppnl1+bt.getLiability();;
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
							
						}

						
					}else if(bt.getSelectionid() == EXConstants.selection2){


						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
							temppnl3=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
						}

					}else if(bt.getSelectionid() == EXConstants.selection3){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
							
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
						}
					}else if(bt.getSelectionid() == EXConstants.selection4){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6+bt.getLiability();
						
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6-bt.getPnl();
						}
					}
					else if(bt.getSelectionid() == EXConstants.selection5){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6+bt.getLiability();
							
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6-bt.getPnl();
						}
					}
					else if(bt.getSelectionid() == EXConstants.selection6){

						if(bt.getIsback()) {
							temppnl1 =temppnl1+bt.getLiability();
							temppnl2=temppnl2+bt.getLiability();
							temppnl3=temppnl3+bt.getLiability();
							temppnl4=temppnl4+bt.getLiability();
							temppnl5=temppnl5+bt.getLiability();
							temppnl6=temppnl6-bt.getPnl();
						}else {
							temppnl1 =temppnl1-bt.getPnl();
							temppnl2=temppnl2-bt.getPnl();
							temppnl3=temppnl3-bt.getPnl();
							temppnl4=temppnl4-bt.getPnl();
							temppnl5=temppnl5-bt.getPnl();
							temppnl6=temppnl6+bt.getLiability();
						}
					}
					
					else if(bt.getSelectionid() == EXConstants.selection7){

						if(bt.getIsback()) {
							temppnl7 =temppnl7+bt.getLiability();
							
						}else {
							temppnl7 =temppnl7-bt.getPnl();
							
						}
					}
					/*even pnl*/
					else if(bt.getSelectionid() == EXConstants.selection8){

						temppnl8 =temppnl8+bt.getLiability();

					}

					else if(bt.getSelectionid() == EXConstants.selection9){

						temppnl9 =temppnl9+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection10){

						temppnl10 =temppnl10+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection11){

						temppnl11 =temppnl11+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection12){

						temppnl12 =temppnl12+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection13){

						temppnl13 =temppnl13+bt.getLiability();

					}
					
					else if(bt.getSelectionid() == EXConstants.selection14){

						temppnl14 =temppnl14+bt.getLiability();

					}
					
					


				}

				pnl1  = pnl1  + (temppnl1 * (bean.getAdminpartnership()/100));
				pnl2 = pnl2 + (temppnl2 *(bean.getAdminpartnership()/100));
				pnl3 = pnl3 + (temppnl3 * (bean.getAdminpartnership()/100));

				pnl4 = pnl4 + (temppnl4 * (bean.getAdminpartnership()/100));
				pnl5 = pnl5 + (temppnl5 * (bean.getAdminpartnership()/100));

				pnl6 = pnl6 + (temppnl6 * (bean.getAdminpartnership()/100));
				pnl7 = pnl7 + (temppnl7 * (bean.getAdminpartnership()/100));

				pnl8 = pnl8 + (temppnl8 * (bean.getAdminpartnership()/100));
				pnl9 = pnl9 + (temppnl9 * (bean.getAdminpartnership()/100));

				
				pnl10 = pnl10 + (temppnl10 * (bean.getAdminpartnership()/100));
				pnl11 = pnl11 + (temppnl11 * (bean.getAdminpartnership()/100));


				pnl12 = pnl12 + (temppnl2 * (bean.getAdminpartnership()/100));
				pnl13 = pnl13 + (temppnl3 * (bean.getAdminpartnership()/100));
				pnl14 = pnl14 + (temppnl4 * (bean.getAdminpartnership()/100));


			}

			hm.put("pnl1", pnl1);
			hm.put("pnl2",pnl2 );
			hm.put("pnl3", pnl3);

			hm.put("pnl4", pnl4);
			hm.put("pnl5",pnl5 );

			hm.put("pnl6", pnl6);
			
			hm.put("pnl7",pnl7);
			hm.put("pnl8", pnl9);
			hm.put("pnl9",pnl8);
			hm.put("pnl10",pnl1);
			hm.put("pnl11",pnl10);
			hm.put("pnl12",pnl12);
			hm.put("pnl13",pnl13);
			hm.put("pnl14",pnl14);
			


			RestTemplate restTemplate = new RestTemplate();
			String favour = restTemplate.getForObject(EXConstants.firestoreDb+"bwtable.json", String.class);
			JSONObject obj = new  JSONObject(favour);

			hm.put("favour",  obj.getString("favour"));
			hm.put("amount",  obj.getInt("amount"));

			return new ResponseEntity<Object>(hm,HttpStatus.OK);	


		}catch(Exception e){
			e.printStackTrace();
		}



		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
	@RequestMapping(value="/getMyProfitLuckyLive/{marketid}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitLuckyLive(@PathVariable String marketid) throws ParseException{

		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			
			try{
			



					Double pnl1 =0.0;
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl4 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;


					lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;



					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl4 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;





						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid() ==966778 ) {
								temppnl1 =temppnl1+ bt.getLiability();	    	
							}else  if(bt.getSelectionid() ==  966780 ) {
								temppnl2 =temppnl2+ bt.getLiability();	    	
							}else  if(bt.getSelectionid() ==  966784) {
								temppnl3 =temppnl3+ bt.getLiability();	    	
							}else  if(bt.getSelectionid() == 966783 ) {
								temppnl4 =temppnl4+ bt.getLiability();	    	
							}else  if(bt.getSelectionid() ==  966781 ) {
								temppnl5 =temppnl5+ bt.getLiability();	    	
							}else  if(bt.getSelectionid() ==  966782 ) {
								temppnl6 =temppnl6+ bt.getLiability();	    	
							}

						}

					
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
							pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));

						



					}


					hm.put("pnl1", pnl2);
					hm.put("pnl2",pnl1 );
					hm.put("pnl3", pnl4);
					hm.put("pnl4",pnl3 );
					hm.put("pnl5", pnl6);
					hm.put("pnl6", pnl5);



					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				
			}catch(Exception e){
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
	
	
	@RequestMapping(value="/getMyProfitAndarBahar20/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
	public ResponseEntity<Object> getMyProfitAndarBahar20(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{

		
		if(usertype!=null) {

			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
		
			try{
				if(usertype!=null){




					Double pnl1 =0.0;
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl4 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;
					Double pnl7 =0.0;
					Double pnl8 =0.0;
					Double pnl9 =0.0;
					Double pnl10 =0.0;
					Double pnl11 =0.0;
					Double pnl12 =0.0;
					Double pnl13 =0.0;
					
					
					
					Double pnl21 =0.0;
					Double pnl22 =0.0;
					Double pnl23 =0.0;
					Double pnl24 =0.0;
					Double pnl25 =0.0;
					Double pnl26 =0.0;
					Double pnl27 =0.0;
					Double pnl28 =0.0;
					Double pnl29 =0.0;
					Double pnl30 =0.0;
					Double pnl31 =0.0;
					Double pnl32 =0.0;
					Double pnl33 =0.0;
					



					if(usertype ==4) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==6) {
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;

					}else if(usertype ==5) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSubadminid(marketid, true,Id) ;
					}else if(usertype ==0) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndSupermasterid(marketid, true,Id) ;
					}else if(usertype ==1) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndMasterid(marketid, true,Id) ;
					}else if(usertype ==2) {
						lib = userLibRepoMongo.findByMarketidAndIsactiveAndDealerid(marketid, true,Id) ;
					}



					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl4 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;
						Double temppnl7 =0.0;
						Double temppnl8 =0.0;
						Double temppnl9 =0.0;
						Double temppnl10 =0.0;
						Double temppnl11 =0.0;
						Double temppnl12 =0.0;
						Double temppnl13 =0.0;
						
						
						Double temppnl21 =0.0;
						Double temppnl22 =0.0;
						Double temppnl23 =0.0;
						Double temppnl24 =0.0;
						Double temppnl25 =0.0;
						Double temppnl26 =0.0;
						Double temppnl27 =0.0;
						Double temppnl28 =0.0;
						Double temppnl29 =0.0;
						Double temppnl30 =0.0;
						Double temppnl31 =0.0;
						Double temppnl32 =0.0;
						Double temppnl33 =0.0;



						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid() == EXConstants.selection1 ) {
								temppnl21 =temppnl21+ bt.getLiability();	    	 	
							}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
								temppnl22 =temppnl22+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection3 ) {
								temppnl23 =temppnl23+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection4 ) {
								temppnl24 =temppnl24+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
								temppnl25 =temppnl25+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
								temppnl26 =temppnl26+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection7 ) {
								temppnl27 =temppnl27+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection8 ) {
								temppnl27 =temppnl27+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection9 ) {
								temppnl28 =temppnl28+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection10 ) {
								temppnl29 =temppnl29+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection11 ) {
								temppnl30 =temppnl31+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection12 ) {
								temppnl32 =temppnl32+ bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection13 ) {
								temppnl33 =temppnl33+ bt.getPnl();	    	
							}
							
							
							
							
							else  if(bt.getSelectionid() ==  EXConstants.selection21 ) {
								temppnl1 =temppnl1+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection22 ) {
								temppnl2 =temppnl2+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection23 ) {
								temppnl3 =temppnl3+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection24 ) {
								temppnl4 =temppnl4+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection25 ) {
								temppnl5 =temppnl5+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection26 ) {
								temppnl6 =temppnl6+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection27 ) {
								temppnl7 =temppnl8+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection28 ) {
								temppnl8 =temppnl8+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection29 ) {
								temppnl9 =temppnl9+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection30 ) {
								temppnl10 =temppnl10+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection31 ) {
								temppnl11 =temppnl11+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection32 ) {
								temppnl12 =temppnl12+ bt.getPnl();	    	
							}
							else  if(bt.getSelectionid() ==  EXConstants.selection33 ) {
								temppnl13 =temppnl13+ bt.getPnl();	    	
							}
							
							

						}

						if(usertype ==4 || usertype ==6) {
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
							pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));
							pnl7= pnl7+ (temppnl7 * (bean.getAdminpartnership()/100));
							pnl8= pnl8+ (temppnl8 * (bean.getAdminpartnership()/100));
							pnl9= pnl9+ (temppnl9 * (bean.getAdminpartnership()/100));
							pnl10= pnl10+ (temppnl10 * (bean.getAdminpartnership()/100));
							pnl11= pnl11+ (temppnl11 * (bean.getAdminpartnership()/100));
							pnl12= pnl12+ (temppnl12 * (bean.getAdminpartnership()/100));
							pnl13= pnl13+ (temppnl13 * (bean.getAdminpartnership()/100));
							
							
							
							
							
							pnl21= pnl21+ (temppnl21 * (bean.getAdminpartnership()/100));
							pnl22= pnl22+ (temppnl22 *(bean.getAdminpartnership()/100));
							pnl23= pnl23+ (temppnl23 * (bean.getAdminpartnership()/100));
							pnl24= pnl24+ (temppnl24 *(bean.getAdminpartnership()/100));
							pnl25= pnl25+ (temppnl25 *(bean.getAdminpartnership()/100));
							pnl26= pnl26+ (temppnl26 * (bean.getAdminpartnership()/100));
							pnl27= pnl27+ (temppnl27 * (bean.getAdminpartnership()/100));
							pnl28= pnl28+ (temppnl28 * (bean.getAdminpartnership()/100));
							pnl29= pnl29+ (temppnl29 * (bean.getAdminpartnership()/100));
							pnl30= pnl30+ (temppnl30 * (bean.getAdminpartnership()/100));
							pnl31= pnl31+ (temppnl31 * (bean.getAdminpartnership()/100));
							pnl32= pnl32+ (temppnl32 * (bean.getAdminpartnership()/100));
							pnl33= pnl33+ (temppnl33 * (bean.getAdminpartnership()/100));
							
							
						} 




					}


					hm.put("pnl1", pnl1);
					hm.put("pnl2",pnl2 );
					hm.put("pnl3", pnl3);
					hm.put("pnl4",pnl4 );
					hm.put("pnl5", pnl5);
					hm.put("pnl6", pnl6);
					hm.put("pnl7", pnl7);
					hm.put("pnl8", pnl8);
					hm.put("pnl9", pnl9);
					hm.put("pnl10", pnl10);
					hm.put("pnl11", pnl11);
					hm.put("pnl12", pnl12);
					hm.put("pnl13", pnl13);
					hm.put("pnl21", pnl21);
					hm.put("pnl22", pnl22);
					hm.put("pnl23", pnl23);
					hm.put("pnl24", pnl24);
					hm.put("pnl25", pnl25);
					hm.put("pnl26", pnl26);
					hm.put("pnl27", pnl27);
					hm.put("pnl28", pnl28);
					hm.put("pnl29", pnl29);
					hm.put("pnl30", pnl30);
					hm.put("pnl31", pnl31);
					hm.put("pnl32", pnl32);
					hm.put("pnl33", pnl33);
					
					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				}
			}catch(Exception e){
				e.printStackTrace();
			}

		}

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
	
		@RequestMapping(value="/getMyProfitVOpenTeenPatti/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
		public ResponseEntity<Object> getMyProfitVOpenTeenPatti(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id){


		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			
			try{
			
					Double pnl1 =0.0;
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl4 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;
					Double pnl7 =0.0;
					Double pnl8 =0.0;


					lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;


					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl4 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;
						Double temppnl7 =0.0;
						Double temppnl8 =0.0;




						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid()  == EXConstants.selection1) {
								temppnl1 =temppnl1-bt.getPnl();
							}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
								temppnl2 =temppnl2-bt.getPnl();    	
							}else  if(bt.getSelectionid() ==   EXConstants.selection3) {
								temppnl3 =temppnl3-bt.getPnl();    	
							}else  if(bt.getSelectionid() == EXConstants.selection4 ) {
								temppnl4 =temppnl4-bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
								temppnl5 =temppnl5-bt.getPnl();  	
							}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
								temppnl6 =temppnl6-bt.getPnl();    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection7 ) {
								temppnl7 =temppnl7-bt.getPnl();  	
							}else  if(bt.getSelectionid() ==  EXConstants.selection8 ) {
								temppnl8 =temppnl8-bt.getPnl();    	
							}

						}

					
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
							pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));
							pnl7= pnl7+ (temppnl7 * (bean.getAdminpartnership()/100));
							pnl8= pnl8+ (temppnl8 * (bean.getAdminpartnership()/100));

						}


					hm.put("pnl1", pnl1);
					hm.put("pnl2", pnl2);
					hm.put("pnl3", pnl3);
					hm.put("pnl4", pnl4);
					hm.put("pnl5", pnl5);
					hm.put("pnl6", pnl6);
					hm.put("pnl7", pnl7);
					hm.put("pnl8", pnl8);



					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				
			}catch(Exception e){
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
		
		@RequestMapping(value="/getMyProfitVAndarBahar2/{marketid}/{usertype}/{Id}",method=RequestMethod.GET)
		public ResponseEntity<Object> getMyProfitVAndarBahar2(@PathVariable String marketid,@PathVariable Integer usertype,@PathVariable String Id) throws ParseException{


		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			
			try{
			
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;
					Double pnl24 =0.0;
					Double pnl25 =0.0;
					Double pnl20 =0.0;
					Double pnl21 =0.0;
					Double pnl22 =0.0;
					Double pnl23 =0.0;


					lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;


					for(UserLiabilityMongo bean : lib){

						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;
						Double temppnl24 =0.0;
						Double temppnl25 =0.0;
						Double temppnl20 =0.0;
						Double temppnl21 =0.0;
						Double temppnl22 =0.0;
						Double temppnl23 =0.0;




						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid() ==2 ) {
								temppnl2 =temppnl2-bt.getPnl();
							}else  if(bt.getSelectionid() ==  3 ) {
								temppnl3 =temppnl3-bt.getPnl();
							}else  if(bt.getSelectionid() ==  5) {
								temppnl5 =temppnl5-bt.getPnl();
							}else  if(bt.getSelectionid() == 6 ) {
								temppnl6 =temppnl6-bt.getPnl();  	
							}else  if(bt.getSelectionid() ==  24 ) {
								temppnl24 =temppnl24-bt.getPnl();   	
							}else  if(bt.getSelectionid() ==  25 ) {
								temppnl25 =temppnl25-bt.getPnl();   
							}else  if(bt.getSelectionid() ==  20 ) {
								temppnl20 =temppnl20-bt.getPnl();   
							}else  if(bt.getSelectionid() ==  21 ) {
								temppnl21 =temppnl21-bt.getPnl();
							}else  if(bt.getSelectionid() ==  22 ) {
								temppnl22 =temppnl22-bt.getPnl();
							}else  if(bt.getSelectionid() ==  23 ) {
								temppnl23 =temppnl23-bt.getPnl();   
							}

						}

					
							pnl2= pnl2+ (temppnl2 * (bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 * (bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 *(bean.getAdminpartnership()/100));
							pnl24= pnl24+ (temppnl24 *(bean.getAdminpartnership()/100));
							pnl25= pnl25+ (temppnl25 * (bean.getAdminpartnership()/100));
							pnl20= pnl20+ (temppnl20 * (bean.getAdminpartnership()/100));
							pnl21= pnl21+ (temppnl21 * (bean.getAdminpartnership()/100));
							pnl22= pnl22+ (temppnl22 * (bean.getAdminpartnership()/100));
							pnl23= pnl23+ (temppnl23 * (bean.getAdminpartnership()/100));

						}


					hm.put("pnl2", pnl2);
					hm.put("pnl3", pnl3);
					hm.put("pnl5", pnl5);
					hm.put("pnl6", pnl6);
					hm.put("pnl24", pnl25);
					hm.put("pnl25", pnl24);
					hm.put("pnl20", pnl20);
					hm.put("pnl21", pnl21);
					hm.put("pnl22", pnl22);
					hm.put("pnl23", pnl23);



					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				
			}catch(Exception e){
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
	
		@RequestMapping(value="/getMyProfitVAndarBahar2P/{marketid}",method=RequestMethod.GET)
		public ResponseEntity<Object> getMyProfitVAndarBahar2P(@PathVariable String marketid){


		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			
			try{
			
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;
					Double pnl24 =0.0;
					Double pnl25 =0.0;
					Double pnl20 =0.0;
					Double pnl21 =0.0;
					Double pnl22 =0.0;
					Double pnl23 =0.0;


					lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;


					for(UserLiabilityMongo bean : lib){

						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;
						Double temppnl24 =0.0;
						Double temppnl25 =0.0;
						Double temppnl20 =0.0;
						Double temppnl21 =0.0;
						Double temppnl22 =0.0;
						Double temppnl23 =0.0;




						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid() ==2 ) {
								temppnl2 =temppnl2-bt.getPnl();
							}else  if(bt.getSelectionid() ==  3 ) {
								temppnl3 =temppnl3-bt.getPnl();
							}else  if(bt.getSelectionid() ==  5) {
								temppnl5 =temppnl5-(bt.getPnl());
							}else  if(bt.getSelectionid() == 6 ) {
								temppnl6 =temppnl6-bt.getPnl();  	
							}else  if(bt.getSelectionid() ==  24 ) {
								temppnl24 =temppnl24-bt.getPnl();   	
							}else  if(bt.getSelectionid() ==  25 ) {
								temppnl25 =temppnl25-bt.getPnl();   
							}else  if(bt.getSelectionid() ==  20 ) {
								temppnl20 =temppnl20-bt.getPnl();   
							}else  if(bt.getSelectionid() ==  21 ) {
								temppnl21 =temppnl21-bt.getPnl();
							}else  if(bt.getSelectionid() ==  22 ) {
								temppnl22 =temppnl22-bt.getPnl();
							}else  if(bt.getSelectionid() ==  23 ) {
								temppnl23 =temppnl23-bt.getPnl();   
							}

						}

					
							pnl2= pnl2+ (temppnl2 * (bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 * (bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 *(bean.getAdminpartnership()/100));
							pnl24= pnl24+ (temppnl24 *(bean.getAdminpartnership()/100));
							pnl25= pnl25+ (temppnl25 * (bean.getAdminpartnership()/100));
							pnl20= pnl20+ (temppnl20 * (bean.getAdminpartnership()/100));
							pnl21= pnl21+ (temppnl21 * (bean.getAdminpartnership()/100));
							pnl22= pnl22+ (temppnl22 * (bean.getAdminpartnership()/100));
							pnl23= pnl23+ (temppnl23 * (bean.getAdminpartnership()/100));

						}


					hm.put("pnl2", pnl2);
					hm.put("pnl3", pnl3);
					hm.put("pnl5", pnl5);
					hm.put("pnl6", pnl6);
					hm.put("pnl24", pnl24);
					hm.put("pnl25", pnl25);
					hm.put("pnl20", pnl20);
					hm.put("pnl21", pnl21);
					hm.put("pnl22", pnl22);
					hm.put("pnl23", pnl23);

					RestTemplate restTemplate = new RestTemplate();

					String favour = restTemplate.getForObject(EXConstants.firestoreDb+"ab2.json", String.class);

					JSONObject obj = new  JSONObject(favour);

					hm.put("favour",  obj.getString("favour"));
					hm.put("amount",  obj.getInt("amount"));


					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				
			}catch(Exception e){
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}

		@RequestMapping(value="/getMyProfitVOpenTeenPattiP/{marketid}",method=RequestMethod.GET)
		public ResponseEntity<Object> getMyProfitVOpenTeenPattiP(@PathVariable String marketid){


		
			LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

			ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			
			try{
			
					Double pnl1 =0.0;
					Double pnl2 =0.0;
					Double pnl3 =0.0;
					Double pnl4 =0.0;
					Double pnl5 =0.0;
					Double pnl6 =0.0;
					Double pnl7 =0.0;
					Double pnl8 =0.0;


					lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;


					for(UserLiabilityMongo bean : lib){

						Double temppnl1 =0.0;
						Double temppnl2 =0.0;
						Double temppnl3 =0.0;
						Double temppnl4 =0.0;
						Double temppnl5 =0.0;
						Double temppnl6 =0.0;
						Double temppnl7 =0.0;
						Double temppnl8 =0.0;




						ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), true,bean.getUserid());

						for(ExMatchOddsBetMongo bt :betlist) {
							if(bt.getSelectionid()  == EXConstants.selection1) {
								temppnl1 =temppnl1-bt.getPnl();
							}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
								temppnl2 =temppnl2-bt.getPnl();    	
							}else  if(bt.getSelectionid() ==   EXConstants.selection3) {
								temppnl3 =temppnl3-bt.getPnl();    	
							}else  if(bt.getSelectionid() == EXConstants.selection4 ) {
								temppnl4 =temppnl4-bt.getPnl();	    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
								temppnl5 =temppnl5-bt.getPnl();  	
							}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
								temppnl6 =temppnl6-bt.getPnl();    	
							}else  if(bt.getSelectionid() ==  EXConstants.selection7 ) {
								temppnl7 =temppnl7-bt.getPnl();  	
							}else  if(bt.getSelectionid() ==  EXConstants.selection8 ) {
								temppnl8 =temppnl8-bt.getPnl();    	
							}

						}

					
							pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
							pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
							pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
							pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
							pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
							pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));
							pnl7= pnl7+ (temppnl7 * (bean.getAdminpartnership()/100));
							pnl8= pnl8+ (temppnl8 * (bean.getAdminpartnership()/100));

						}


					hm.put("pnl1", pnl1);
					hm.put("pnl2", pnl2);
					hm.put("pnl3", pnl3);
					hm.put("pnl4", pnl4);
					hm.put("pnl5", pnl5);
					hm.put("pnl6", pnl6);
					hm.put("pnl7", pnl7);
					hm.put("pnl8", pnl8);
				
					RestTemplate restTemplate = new RestTemplate();
					String favour = restTemplate.getForObject(EXConstants.firestoreDb+"optp.json", String.class);
					JSONObject obj = new  JSONObject(favour);

					hm.put("favour",  obj.getString("favour"));
					hm.put("amount",  obj.getInt("amount"));


					return new ResponseEntity<Object>(hm,HttpStatus.OK);	


				
			}catch(Exception e){
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

	}
		
		@RequestMapping(value="/getMyProfitVRoulette/{marketid}",method=RequestMethod.GET)
		public ResponseEntity<Object> getMyProfitVRoulette(@PathVariable String marketid) throws ParseException{

			
		
				LinkedHashMap<String, Object> hm =new LinkedHashMap<String, Object>(); 

				ArrayList<UserLiabilityMongo> lib = new  ArrayList<UserLiabilityMongo>();
			
				try{
					


						Double pnl1 =0.0;
						Double pnl2 =0.0;
						Double pnl3 =0.0;
						Double pnl4 =0.0;
						Double pnl5 =0.0;
						Double pnl6 =0.0;
						Double pnl7 =0.0;
						Double pnl8 =0.0;
						Double pnl9 =0.0;
						Double pnl10 =0.0;
						Double pnl11 =0.0;
						Double pnl12 =0.0;
						Double pnl13 =0.0;
						Double pnl14 =0.0;
						Double pnl15 =0.0;
						Double pnl16 =0.0;
						Double pnl17 =0.0;
						Double pnl18 =0.0;
						Double pnl19 =0.0;
						Double pnl20 =0.0;
						
						Double pnl21 =0.0;
						Double pnl22 =0.0;
						Double pnl23 =0.0;
						Double pnl24 =0.0;
						Double pnl25 =0.0;
						Double pnl26 =0.0;
						Double pnl27 =0.0;
						Double pnl28 =0.0;
						Double pnl29 =0.0;
						Double pnl30 =0.0;
						Double pnl31 =0.0;
						Double pnl32 =0.0;
						Double pnl33 =0.0;
						Double pnl34 =0.0;
						Double pnl35 =0.0;
						Double pnl36 =0.0;
						Double pnl37 =0.0;	
						Double pnl38 =0.0;
						Double pnl39 =0.0;
						Double pnl40 =0.0;
						Double pnl41 =0.0;
						Double pnl42 =0.0;
						Double pnl43 =0.0;
						Double pnl44 =0.0;
						Double pnl45 =0.0;
						Double pnl46 =0.0;
						Double pnl47 =0.0;
						Double pnl48 =0.0;
						Double pnl49 =0.0;
						
						
						
						
						lib = userLibRepoMongo.findByMarketidAndIsactive(marketid, true) ;



						for(UserLiabilityMongo bean : lib){

							Double temppnl1 =0.0;
							Double temppnl2 =0.0;
							Double temppnl3 =0.0;
							Double temppnl4 =0.0;
							Double temppnl5 =0.0;
							Double temppnl6 =0.0;
							Double temppnl7 =0.0;
							Double temppnl8 =0.0;
							Double temppnl9 =0.0;
							Double temppnl10 =0.0;
							Double temppnl11 =0.0;
							Double temppnl12 =0.0;
							Double temppnl13 =0.0;
							Double temppnl14 =0.0;
							Double temppnl15 =0.0;
							Double temppnl16 =0.0;
							Double temppnl17 =0.0;
							Double temppnl18 =0.0;
							Double temppnl19 =0.0;
							Double temppnl20 =0.0;
							Double temppnl21 =0.0;
							
							Double temppnl22 =0.0;
							Double temppnl23 =0.0;
							Double temppnl24 =0.0;
							Double temppnl25 =0.0;
							Double temppnl26 =0.0;
							Double temppnl27 =0.0;
							Double temppnl28 =0.0;
							Double temppnl29 =0.0;
							Double temppnl30 =0.0;
							Double temppnl31 =0.0;
							Double temppnl32 =0.0;
							Double temppnl33 =0.0;
							Double temppnl34 =0.0;
							Double temppnl35 =0.0;
							Double temppnl36 =0.0;
							Double temppnl37 =0.0;
							Double temppnl38 =0.0;
							Double temppnl39 =0.0;
							Double temppnl40 =0.0;
							Double temppnl41 =0.0;
							Double temppnl42 =0.0;
							Double temppnl43 =0.0;
							Double temppnl44 =0.0;
							Double temppnl45 =0.0;
							Double temppnl46 =0.0;
							Double temppnl47 =0.0;
							Double temppnl48 =0.0;
							Double temppnl49 =0.0;
							
							
							ArrayList<ExMatchOddsBetMongo> betlist =    exMatchOddsBetRepo.findByMarketidAndIsactiveAndUserid(bean.getMarketid(), 							true,bean.getUserid());

							for(ExMatchOddsBetMongo bt :betlist) {
								if(bt.getSelectionid() == EXConstants.selection1 ) {
									temppnl1 =temppnl1- bt.getPnl();   	 	
								}else  if(bt.getSelectionid() ==  EXConstants.selection2 ) {
									temppnl2 =temppnl2- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection3 ) {
									temppnl3 =temppnl3- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection4 ) {
									temppnl4 =temppnl4- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection5 ) {
									temppnl5 =temppnl5- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection6 ) {
									temppnl6 =temppnl6- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection7 ) {
									temppnl7 =temppnl7- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection8 ) {
									temppnl8 =temppnl8- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection9 ) {
						
									temppnl9 =temppnl9- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection10 ) {
									temppnl10 =temppnl10 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection11 ) {
									temppnl11 =temppnl11 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection12 ) {
									temppnl12 =temppnl12- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection13 ) {
									temppnl13 =temppnl13 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection14 ) {
									temppnl14 =temppnl14 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection15 ) {
									temppnl15 =temppnl15 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection16 ) {
									temppnl16 =temppnl16 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection17 ) {
									temppnl17 =temppnl17- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection18 ) {
									temppnl18 =temppnl18 - bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection19 ) {
									temppnl19 =temppnl19- bt.getPnl();	    	
								}else  if(bt.getSelectionid() ==  EXConstants.selection20 ) {
									temppnl20 =temppnl20- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection21 ) {
									temppnl21 =temppnl21- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection22 ) {
									temppnl22 =temppnl22- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection23 ) {
									temppnl23 =temppnl23- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection24 ) {
									temppnl24 =temppnl24- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection25 ) {
									temppnl25 =temppnl25- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection26 ) {
									temppnl26 =temppnl26- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection27 ) {
									temppnl27 =temppnl27 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection28 ) {
									temppnl28 =temppnl28 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection29 ) {
									temppnl29 =temppnl29 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection30 ) {
									temppnl30 =temppnl30- bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection31 ) {
									temppnl31 =temppnl31 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection32 ) {
									temppnl32 =temppnl32 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection33 ) {
									temppnl33 =temppnl33 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection34 ) {
									temppnl34 =temppnl34 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection35 ) {
									temppnl35 =temppnl35 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection36 ) {
									temppnl36 =temppnl36 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection37 ) {
									temppnl37 =temppnl37 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection38 ) {
									temppnl38 =temppnl38 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection39 ) {
									temppnl39 =temppnl39 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection40 ) {
									temppnl40 =temppnl40 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection41 ) {
									temppnl41 =temppnl41 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection42 ) {
									temppnl42 =temppnl42 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection43 ) {
									temppnl43 =temppnl43 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection44 ) {
									temppnl44 =temppnl44 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection45 ) {
									temppnl45 =temppnl45 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection46 ) {
									temppnl46 =temppnl46 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection47 ) {
									temppnl47 =temppnl47 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection48 ) {
									temppnl48 =temppnl48 - bt.getPnl();	    	
								}
								else  if(bt.getSelectionid() ==  EXConstants.selection49 ) {
									temppnl49 =temppnl49 - bt.getPnl();	    	
								}
								
							
							}

								pnl1= pnl1+ (temppnl1 * (bean.getAdminpartnership()/100));
								pnl2= pnl2+ (temppnl2 *(bean.getAdminpartnership()/100));
								pnl3= pnl3+ (temppnl3 * (bean.getAdminpartnership()/100));
								pnl4= pnl4+ (temppnl4 *(bean.getAdminpartnership()/100));
								pnl5= pnl5+ (temppnl5 *(bean.getAdminpartnership()/100));
								pnl6= pnl6+ (temppnl6 * (bean.getAdminpartnership()/100));
								pnl7= pnl7+ (temppnl7 * (bean.getAdminpartnership()/100));
								pnl8= pnl8+ (temppnl8 * (bean.getAdminpartnership()/100));
								pnl9= pnl9+ (temppnl9 * (bean.getAdminpartnership()/100));
								pnl10= pnl10+ (temppnl10 * (bean.getAdminpartnership()/100));
								pnl11= pnl11+ (temppnl11 * (bean.getAdminpartnership()/100));
								pnl12= pnl12+ (temppnl12 * (bean.getAdminpartnership()/100));
								pnl13= pnl13+ (temppnl13 * (bean.getAdminpartnership()/100));
								pnl14= pnl14+ (temppnl14 * (bean.getAdminpartnership()/100));
								pnl15= pnl15+ (temppnl15 * (bean.getAdminpartnership()/100));
								pnl16= pnl16+ (temppnl16 * (bean.getAdminpartnership()/100));
								pnl17= pnl17+ (temppnl17 * (bean.getAdminpartnership()/100));
								pnl18= pnl18+ (temppnl18 * (bean.getAdminpartnership()/100));
								pnl19= pnl19+ (temppnl19 * (bean.getAdminpartnership()/100));
								pnl20= pnl20+ (temppnl20 * (bean.getAdminpartnership()/100));
								
								
								
								
								pnl21= pnl21+ (temppnl21 * (bean.getAdminpartnership()/100));
								pnl22= pnl22+ (temppnl22 *(bean.getAdminpartnership()/100));
								pnl23= pnl23+ (temppnl23 * (bean.getAdminpartnership()/100));
								pnl24= pnl24+ (temppnl24 *(bean.getAdminpartnership()/100));
								pnl25= pnl25+ (temppnl25 *(bean.getAdminpartnership()/100));
								pnl26= pnl26+ (temppnl26 * (bean.getAdminpartnership()/100));
								pnl27= pnl27+ (temppnl27 * (bean.getAdminpartnership()/100));
								pnl28= pnl28+ (temppnl28 * (bean.getAdminpartnership()/100));
								pnl29= pnl29+ (temppnl29 * (bean.getAdminpartnership()/100));
								pnl30= pnl30+ (temppnl30 * (bean.getAdminpartnership()/100));
								pnl31= pnl31+ (temppnl31 * (bean.getAdminpartnership()/100));
								pnl32= pnl32+ (temppnl32 * (bean.getAdminpartnership()/100));
								pnl33= pnl33+ (temppnl33 * (bean.getAdminpartnership()/100));
								pnl34= pnl34+ (temppnl34 * (bean.getAdminpartnership()/100));
								pnl35= pnl35+ (temppnl35 * (bean.getAdminpartnership()/100));
								pnl36= pnl36+ (temppnl36 * (bean.getAdminpartnership()/100));
								pnl37= pnl37+ (temppnl37 * (bean.getAdminpartnership()/100));
								pnl38= pnl38+ (temppnl38 * (bean.getAdminpartnership()/100));
								pnl39= pnl39+ (temppnl39 * (bean.getAdminpartnership()/100));
								pnl40= pnl40+ (temppnl40 * (bean.getAdminpartnership()/100));
								pnl41= pnl41+ (temppnl41 * (bean.getAdminpartnership()/100));
								pnl42= pnl42+ (temppnl42 * (bean.getAdminpartnership()/100));
								pnl43= pnl43+ (temppnl43 * (bean.getAdminpartnership()/100));
								pnl44= pnl44+ (temppnl44 * (bean.getAdminpartnership()/100));
								pnl45= pnl45+ (temppnl45 * (bean.getAdminpartnership()/100));
								pnl46= pnl46+ (temppnl46 * (bean.getAdminpartnership()/100));
								pnl47= pnl47+ (temppnl47 * (bean.getAdminpartnership()/100));
								pnl48= pnl48+ (temppnl48 * (bean.getAdminpartnership()/100));
								pnl49= pnl49+ (temppnl49 * (bean.getAdminpartnership()/100));
								
							
						}


						hm.put("pnl1", pnl1);
						hm.put("pnl2",pnl2 );
						hm.put("pnl3", pnl3);
						hm.put("pnl4",pnl4 );
						hm.put("pnl5", pnl5);
						hm.put("pnl6", pnl6);
						hm.put("pnl7", pnl7);
						hm.put("pnl8", pnl8);
						hm.put("pnl9", pnl9);
						hm.put("pnl10", pnl10);
						hm.put("pnl11", pnl11);
						hm.put("pnl12", pnl12);
						hm.put("pnl13", pnl13);
						hm.put("pnl14", pnl14);
						hm.put("pnl15", pnl15);
						hm.put("pnl16", pnl16);
						hm.put("pnl17", pnl17);
						hm.put("pnl18", pnl18);
						hm.put("pnl19", pnl19);
						hm.put("pnl20", pnl20);
						hm.put("pnl21", pnl21);
						hm.put("pnl22", pnl22);
						hm.put("pnl23", pnl23);
						hm.put("pnl24", pnl24);
						hm.put("pnl25", pnl25);
						hm.put("pnl26", pnl26);
						hm.put("pnl27", pnl27);
						hm.put("pnl28", pnl28);
						hm.put("pnl29", pnl29);
						hm.put("pnl30", pnl30);
						hm.put("pnl31", pnl31);
						hm.put("pnl32", pnl32);
						hm.put("pnl33", pnl33);
						hm.put("pnl34", pnl34);
						hm.put("pnl35", pnl35);
						hm.put("pnl36", pnl36);
						hm.put("pnl37", pnl37);
						hm.put("pnl38", pnl38);
						hm.put("pnl39", pnl39);
						hm.put("pnl40", pnl40);
						hm.put("pnl41", pnl41);
						hm.put("pnl42", pnl42);
						hm.put("pnl43", pnl43);
						hm.put("pnl44", pnl43);
						hm.put("pnl44", pnl44);
						hm.put("pnl45", pnl45);
						hm.put("pnl46", pnl46);
						hm.put("pnl47", pnl47);
						hm.put("pnl48", pnl48);
						hm.put("pnl49", pnl49);
						
						RestTemplate restTemplate = new RestTemplate();
						String favour = restTemplate.getForObject(EXConstants.firestoreDb+"vroulette.json", String.class);
						JSONObject obj = new  JSONObject(favour);

						hm.put("favour",  obj.getString("favour"));
						hm.put("amount",  obj.getInt("amount"));		
						hm.put("number",  obj.getString("number"));		
							
						return new ResponseEntity<Object>(hm,HttpStatus.OK);	


					
				}catch(Exception e){
					e.printStackTrace();
				}

			

			return new ResponseEntity<Object>(HttpStatus.BAD_REQUEST);		

		}
}
