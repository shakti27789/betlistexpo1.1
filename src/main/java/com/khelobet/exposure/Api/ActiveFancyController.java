package com.khelobet.exposure.Api;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.khelobet.exposure.Dao.ExMatchOddsBetDao;
import com.khelobet.exposure.Repository.EXMatchOddsBetRepository;
import com.khelobet.exposure.Repository.FancyRepository;
import com.khelobet.exposure.bean.ExFancyBook;
import com.khelobet.exposure.bean.ExMatchOddsBet;
import com.khelobet.exposure.bean.MatchFancy;
import com.khelobet.exposure.bean.ResponseMapping;
import com.khelobet.exposure.bean.ResponseStatus;
import com.khelobet.exposure.util.EXConstants;


@CrossOrigin
@ControllerAdvice
@RestController
@RequestMapping("/api")
public class ActiveFancyController {

	@Autowired
	FancyRepository fancyRepository;
	
	@Autowired
	ExMatchOddsBetDao betlistDao;	
	
	@Autowired
	EXMatchOddsBetRepository betRepo;
	
	@RequestMapping(value = "/activeFancyNew/{eventId}/{Id}/{usertype}", method = RequestMethod.GET)
	public ResponseEntity<Object> activeFancyNew(@PathVariable Integer eventId, @PathVariable String Id,@PathVariable Integer usertype) {

		
		
		ArrayList<HashMap<String, Object>> hm3 = new ArrayList<HashMap<String, Object>>();
		 ArrayList<ExFancyBook> mfancyBookList =new  ArrayList<ExFancyBook>();
		List<String> fancyIds  = new ArrayList<String>();
		
			try {
				ArrayList<MatchFancy> fancyList = fancyRepository.findByEventidAndIsActive(eventId, true);

			 
				  
				for (MatchFancy fancy : fancyList) {
					
					
					fancyIds.add(fancy.getFancyid());
				}	
				
				if(fancyIds.size()>0) {
					mfancyBookList =	getFancyExposure(fancyIds,Id,usertype);
					
				}
				  
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		

		return new ResponseEntity<Object>(mfancyBookList, HttpStatus.OK);
	}
	
	public ArrayList<ExFancyBook> getFancyExposure(List<String> fancyIds,String userid,Integer usertype) throws JSONException {
		
		DecimalFormat df = new DecimalFormat("#0");
		ArrayList<ExFancyBook> fancyExpo = new ArrayList<ExFancyBook>();
		
		// fancyIds  = new ArrayList<String>();
		//fancyIds.add("3041102712")	;
		
		LinkedHashMap<String, ArrayList<ExFancyBook>> hm2 = new LinkedHashMap<String, ArrayList<ExFancyBook>>();
		
		ArrayList<ExMatchOddsBet> fancyList = new ArrayList<>();
		
			if(usertype==4){
				fancyList= betlistDao.getFancyBookExposure("adminid", userid, fancyIds,"usr.adminpartership");			
			}else if(usertype==6){
				fancyList= betlistDao.getFancyBookExposure("adminid", userid, fancyIds,"usr.adminpartership");			
			}else if(usertype==1){
				fancyList =betlistDao.getFancyBookExposure("masterid", userid, fancyIds,"usr.masterpartership");		
			}else if(usertype==0){
				fancyList = betlistDao.getFancyBookExposure("supermasterid", userid,fancyIds,"usr.supermastepartnership");		
			}else if(usertype==5){
				fancyList =betlistDao.getFancyBookExposure("subadminid", userid, fancyIds,"usr.subadminpartnership");						
			}else if(usertype==2){
				fancyList= betlistDao.getFancyBookExposure("dealerid", userid, fancyIds,"usr.delearpartership");		
			}
			
			HashMap<String,Object> hm = new  HashMap<String,Object> ();
			
			for(String mf : fancyIds) {
				
				
				if(mf.contains(EXConstants.Fancy2Market) || mf.contains(EXConstants.Ball) || mf.contains(EXConstants.Fancy2MarketDiamond) ) {
					ArrayList<Integer> sortedlist = new ArrayList<>(); 
					//if(mf.equalsIgnoreCase("304110276")) {
					Integer mindds =0;
					Integer maxodds =0;


					ArrayList<ExMatchOddsBet> tempfancyList = new  ArrayList<ExMatchOddsBet>();
					ArrayList<ExFancyBook> fancyBook = new ArrayList<ExFancyBook>();


					if(fancyList.size()>0){

						for(ExMatchOddsBet bet :fancyList){


							if(mf.equalsIgnoreCase(bet.getMarketid())) {
								sortedlist.add(Integer.parseInt(df.format(bet.getOdds())));
								tempfancyList.add(bet);
							}

						}

						if(sortedlist.size()>0) {
							Collections.sort(sortedlist); 
							mindds = sortedlist.get(0)-25;
							maxodds = sortedlist.get(sortedlist.size()-1)+25;


							int first = 0;
							for(int i=0;i<tempfancyList.size();i++){
								int min=0;
								int max=0;
								Double liab = tempfancyList.get(i).getLiability();
								Double pnl = tempfancyList.get(i).getPnl();
								int odds = Integer.parseInt(df.format(tempfancyList.get(i).getOdds()));
								if(first==0){
									min = odds-25;
									if(min<0) min=0;
									max=odds+25;
									for(int j=mindds;j<=maxodds;j++){
										ExFancyBook book = new ExFancyBook();

										book.setRates(Integer.parseInt(df.format(j)));
										book.setPnl(0.0);
										fancyBook.add(book);
									}

									first=1;
								}
							}

							Double partnership =0.0;

							for(int j=0; j<=fancyBook.size()-1;j++){

								Integer min= fancyBook.get(j).getRates();

								for(int i=0;i<=tempfancyList.size()-1;i++){

									partnership =tempfancyList.get(i).getPartnership();

									if(i==0){
										if( Double.valueOf(fancyBook.get(j).getRates()) <  tempfancyList.get(i).getOdds()){

											if(tempfancyList.get(i).getIsback()==false){
												fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-(Math.round(((tempfancyList.get(i).getPnl())*(partnership.doubleValue()/100.0f))*100.0)/100.0))));

											}else{
												fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+Math.round(((tempfancyList.get(i).getLiability())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

											}

										}else{


											if(tempfancyList.get(i).getIsback()==false){
												fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+ Math.round(((tempfancyList.get(i).getLiability())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

											}else{
												//System.out.println(tempfancyList.get(i).getPnl()+"==>"+tempfancyList.get(i).getMarketid());
												
												fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-Math.round(((tempfancyList.get(i).getPnl())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

											}
										}
									}else{

										if( Double.valueOf(fancyBook.get(j).getRates()) <  tempfancyList.get(i).getOdds()){

											if(tempfancyList.get(i).getIsback()==false){
												if(min<tempfancyList.get(i).getOdds()){
													fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()- Math.round(((tempfancyList.get(i).getPnl())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

												}else{
													fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+Math.round(((tempfancyList.get(i).getLiability())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

												}

											}else{
												fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+Math.round(((tempfancyList.get(i).getLiability())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

											}

										}else{


											if(tempfancyList.get(i).getIsback()==false){

												if(min<tempfancyList.get(i).getOdds()){
													fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-Math.round(((tempfancyList.get(i).getPnl())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

												}else{
													fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+Math.round(((tempfancyList.get(i).getLiability())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

												}



											}else{
												fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-Math.round(((tempfancyList.get(i).getPnl())*(partnership.doubleValue()/100.0f))*100.0)/100.0)));

											}
										}
									}


								}


							}
						}

					}
					Double lowval = 0.00;

					for(int i = 0; i <fancyBook.size(); i++){
						if(fancyBook.get(i).getPnl()< lowval){
							lowval = fancyBook.get(i).getPnl();
						}
					}
					ExFancyBook bn =new ExFancyBook();
					bn.setFancyid(mf);
					bn.setPnl(lowval);
					fancyExpo.add(bn);
				
				}else if(mf.contains(EXConstants.Fancy3Market)) {
					
					Double pnl1=0.0;
					Double pnl2=0.0;
					
					for(ExMatchOddsBet bet :fancyList){


						if(mf.equalsIgnoreCase(bet.getMarketid())) {
                          
							if(bet.getIsback()) {
								pnl1= pnl1-(bet.getPnl()*bet.getPartnership()/100);
								pnl2= pnl2+(bet.getLiability()*bet.getPartnership()/100);
								
							}else {
								pnl1= pnl1+(bet.getLiability()*bet.getPartnership()/100);
								pnl2= pnl2-(bet.getPnl()*bet.getPartnership()/100);
							}
							
						}

					}
					
					ExFancyBook bn =new ExFancyBook();
					
					if(pnl1<=0) {
						bn.setFancyid(mf);
						bn.setPnl(pnl1);
					}else if(pnl2<=0) {
						bn.setFancyid(mf);
						bn.setPnl(pnl2);
					}
					
					fancyExpo.add(bn);
				}else if(mf.contains(EXConstants.OddEvenMarket)) {
					
					Double pnl1=0.0;
					Double pnl2=0.0;
					
					for(ExMatchOddsBet bet :fancyList){


						if(mf.equalsIgnoreCase(bet.getMarketid())) {
                          
							if(bet.getIsback()) {
								pnl1= pnl1-(bet.getPnl()*bet.getPartnership()/100);
								pnl2= pnl2+(bet.getLiability()*bet.getPartnership()/100);
								
							}else {
								pnl1= pnl1+(bet.getLiability()*bet.getPartnership()/100);
								pnl2= pnl2-(bet.getPnl()*bet.getPartnership()/100);
							}
							
							
						}

					}
					
					ExFancyBook bn =new ExFancyBook();
					
					if(pnl1<=0) {
						bn.setFancyid(mf);
						bn.setPnl(pnl1);
					}else if(pnl2<=0) {
						bn.setFancyid(mf);
						bn.setPnl(pnl2);
					}
					fancyExpo.add(bn);
				}
				hm2.put(mf, fancyExpo);
				
				
				
					
					
				//}
				
				}
			
			
		return fancyExpo;	 
		}
	@RequestMapping(value = "/getFancyBookKalyan", method = RequestMethod.GET)
	public ResponseEntity<Object> getFancyBookKalyan(@RequestParam String fancyid,Integer matchId,String userid){
	
		
		ResponseMapping<Object> response = new ResponseMapping<Object>();
		ResponseStatus status = new ResponseStatus();
		
		
		if(userid!=null) {
			ArrayList<ExFancyBook> fancyBook = getFancyBook(fancyid, userid, matchId);
			if(fancyBook.size()>0) {
				status.setCode("200");
				status.setMessage("Success");
				response.setResponseStatus(status);	
				response.setResponseObj(fancyBook);
			}else {
				status.setCode("204");
				status.setMessage("No Record Found.. ");
				response.setResponseStatus(status);		
			}
			 return new ResponseEntity<Object>(response, HttpStatus.OK);
		}
		 return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}

	
	public ArrayList<ExFancyBook> getFancyBook(@RequestParam String fancyid,String userid,Integer matchId) {
	    DecimalFormat df = new DecimalFormat("#00");
		ArrayList<ExMatchOddsBet> fancyList = new ArrayList<>();
		ArrayList<Integer> sortedlist = new ArrayList<>(); 
		fancyList.addAll(betRepo.findByMarketidAndMatchidAndUserid(fancyid,matchId,userid));
		ArrayList<ExFancyBook> fancyBook = new ArrayList<>();
		
		Integer mindds =0;
		Integer maxodds =0;
	if(fancyList.size()>0) {
		for(ExMatchOddsBet bet :fancyList){
			sortedlist.add(Integer.parseInt(df.format(bet.getOdds())));
		}
		 Collections.sort(sortedlist); 
		 mindds = sortedlist.get(0)-25;
		 maxodds = sortedlist.get(sortedlist.size()-1)+25;
	
	
	
	if(!fancyList.isEmpty()){
		int first = 0;
		for(int i=0;i<fancyList.size();i++){
			
			if(first==0){
			
				for(int j=mindds;j<=maxodds;j++){
					ExFancyBook book = new ExFancyBook();
					
					book.setRates(Integer.parseInt(df.format(j)));
					book.setPnl(0.0);
					fancyBook.add(book);
				}
				
				first=1;
			}
		}

		
	  	
            	
		 for(int j=0; j<=fancyBook.size()-1;j++){
            	
			 Integer min= fancyBook.get(j).getRates();
            	
            		for(int i=0;i<=fancyList.size()-1;i++){
                		
            			
            			if(i==0){
            				if( Double.valueOf(fancyBook.get(j).getRates()) <  fancyList.get(i).getOdds()){
	                			
	                			if(fancyList.get(i).getIsback()==false){
	                    			fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+fancyList.get(i).getPnl())));
	                    			
	                    		}else{
	                    			fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-fancyList.get(i).getLiability())));
	                    			
	                    		}
	                			
	                		}else{
	                			
	                			
	                			if(fancyList.get(i).getIsback()==false){
	                    			fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-fancyList.get(i).getLiability())));
	                    			
	                    		}else{
	                    			fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+fancyList.get(i).getPnl())));
	                    			
	                    		}
	                		}
            			}else{
            				
	                		if( Double.valueOf(fancyBook.get(j).getRates()) <  fancyList.get(i).getOdds()){
	                			
	                			if(fancyList.get(i).getIsback()==false){
	                    			if(min<fancyList.get(i).getOdds()){
	                    				fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+fancyList.get(i).getPnl())));
		                    			
	                    			}else{
	                    				fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-fancyList.get(i).getLiability())));
		                    				
	                    			}
	                				
	                    		}else{
	                    			fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-fancyList.get(i).getLiability())));
	                    			
	                    		}
	                			
	                		}else{
	                			
	                			
	                			if(fancyList.get(i).getIsback()==false){
	                    			
	                				if(min<fancyList.get(i).getOdds()){
	                					fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+fancyList.get(i).getPnl())));
		                    			
	                				}else{
	                					fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()-fancyList.get(i).getLiability())));
		                    			
	                				}
	                				
	                    		
	                    			
	                    		}else{
	                    			fancyBook.get(j).setPnl(Double.valueOf(df.format(fancyBook.get(j).getPnl()+fancyList.get(i).getPnl())));
	                    			
	                    		}
	                		}
            			}
            			
            		
                	}
            	
            
            }
            
		}
	}
			
		return fancyBook;		
	
	}	
}
