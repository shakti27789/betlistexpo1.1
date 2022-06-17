package com.khelobet.exposure.Dao.impl;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.khelobet.exposure.Dao.ExMatchOddsBetMongoCustomRepository;
import com.khelobet.exposure.bean.ExMatchOddsBetMongo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ExMatchOddsBetMongoCustomRepositoryImpl implements ExMatchOddsBetMongoCustomRepository {
	
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<ExMatchOddsBetMongo> findBetListByProperties(Integer matchid, Boolean isActive,String userid,Integer usertype,String serchBy,String marketname,Integer limit,String marketId) {
	
	DateFormat formatter = null;
	Date startDate1 = null, endDate1 = null;
	
	final Query query = new Query();// .with(page);
	
	try {
	
//	     query.fields().include("id").include("name");
		final List<Criteria> criteria = new ArrayList<>();
		if (matchid != null)
			criteria.add(Criteria.where("matchid").is(matchid));
		if (marketId != null)
			criteria.add(Criteria.where("marketid").is(marketId));
		if (marketname != null)
		 criteria.add(Criteria.where("marketname").is(marketname));
		
		if (isActive != null)
			criteria.add(Criteria.where("isactive").is(isActive));
		if (usertype  == 4)
			criteria.add(Criteria.where("adminid").is(userid));
		if (usertype  == 6)
			criteria.add(Criteria.where("adminid").is("1"));
		if (usertype  == 5)
			criteria.add(Criteria.where("subadminid").is(userid));
		if (usertype  == 0)
			criteria.add(Criteria.where("supermasterid").in(userid));
		if (usertype  == 1)
			criteria.add(Criteria.where("masterid").is(userid));
		if (usertype  == 2)
			criteria.add(Criteria.where("dealerid").is(userid));
		
		   
		if (!criteria.isEmpty()) {
			if(limit>0) {
				query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])))	.with(Sort.by(Sort.Direction.DESC, "matchedtime")).limit(100);
						
			}else {
				query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])))	.with(Sort.by(Sort.Direction.DESC, "matchedtime"));
				
			}
		}
		//log.info("query: " + query);
		//log.info("Query Result:" + mongoTemplate.find(query, ExMatchOddsBetMongo.class));
		query.fields().include("series");
		query.fields().include("userid");
		query.fields().include("marketid");
		query.fields().include("stack");
		query.fields().include("isback");
		query.fields().include("islay");
		query.fields().include("selectionname");
		query.fields().include("pnl");
		query.fields().include("marketname");
		query.fields().include("liability");
		query.fields().include("matchedtime");
		query.fields().include("odds");
		query.fields().include("stake");
		query.fields().include("pricevalue");
		
	} catch(Exception ex) {
		log.info("Exception Occured: "+ex.getMessage());
	}

	return mongoTemplate.find(query, ExMatchOddsBetMongo.class);
	  
}
	

	public long  findBetListByPropertiesCount(Integer matchid, Boolean isActive,String userid,Integer usertype,String serchBy,String marketname,String marketId) {
		
		DateFormat formatter = null;
		Date startDate1 = null, endDate1 = null;
		
		final Query query = new Query();// .with(page);
		
		try {
		
//		     query.fields().include("id").include("name");
			final List<Criteria> criteria = new ArrayList<>();
			if (matchid != null)
				criteria.add(Criteria.where("matchid").is(matchid));
			if (marketId != null)
				criteria.add(Criteria.where("marketid").is(marketId));
			if (marketname != null)
			 criteria.add(Criteria.where("marketname").is(marketname));
				
			if (isActive != null)
				criteria.add(Criteria.where("isactive").is(isActive));
			if (usertype  == 4)
				criteria.add(Criteria.where("adminid").is(userid));
			if (usertype  == 6)
				criteria.add(Criteria.where("adminid").is("1"));
			if (usertype  == 5)
				criteria.add(Criteria.where("subadminid").is(userid));
			if (usertype  == 0)
				criteria.add(Criteria.where("supermasterid").in(userid));
			if (usertype  == 1)
				criteria.add(Criteria.where("masterid").is(userid));
			if (usertype  == 2)
				criteria.add(Criteria.where("dealerid").is(userid));
			
		
			    
			if (!criteria.isEmpty()) {
				query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])))	.with(Sort.by(Sort.Direction.DESC, "series"));
				
			}
			
			
		} catch(Exception ex) {
			//log.info("Exception Occured: "+ex.getMessage());
			ex.printStackTrace();
		}
		long count = mongoTemplate.count(query, ExMatchOddsBetMongo.class);
		return count;
		
	}


	@Override
	public List<ExMatchOddsBetMongo> findBetListByPropertiesByMarketId(Integer matchId, Boolean isActive, String userid,
			Integer usertype, String serchBy, String marketname, Integer limit) {
		
		
		final Query query = new Query();// .with(page);
		
		try {
		
//		     query.fields().include("id").include("name");
			final List<Criteria> criteria = new ArrayList<>();
			if (matchId != null)
				criteria.add(Criteria.where("matchid").is(matchId));
			if (isActive != null)
				criteria.add(Criteria.where("isactive").is(isActive));
			if (usertype  == 4)
				criteria.add(Criteria.where("adminid").is(userid));
			if (usertype  == 6)
				criteria.add(Criteria.where("adminid").is("1"));
			if (usertype  == 5)
				criteria.add(Criteria.where("subadminid").is(userid));
			if (usertype  == 0)
				criteria.add(Criteria.where("supermasterid").in(userid));
			if (usertype  == 1)
				criteria.add(Criteria.where("masterid").is(userid));
			if (usertype  == 2)
				criteria.add(Criteria.where("dealerid").is(userid));
			
			 
			if (!criteria.isEmpty()) {
				if(limit>0) {
					query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])))	.with(Sort.by(Sort.Direction.DESC, "matchedtime")).limit(100);
							
				}else {
					query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])))	.with(Sort.by(Sort.Direction.DESC, "matchedtime"));
					
				}
			}
			//log.info("query: " + query);
			//log.info("Query Result:" + mongoTemplate.find(query, ExMatchOddsBetMongo.class));
			query.fields().include("series");
			query.fields().include("userid");
			query.fields().include("marketid");
			query.fields().include("stack");
			query.fields().include("isback");
			query.fields().include("islay");
			query.fields().include("selectionname");
			query.fields().include("pnl");
			query.fields().include("marketname");
			query.fields().include("liability");
			query.fields().include("matchedtime");
			query.fields().include("odds");
			query.fields().include("stake");
			
			
		} catch(Exception ex) {
			//log.info("Exception Occured: "+ex.getMessage());
			ex.printStackTrace();
		}

		return mongoTemplate.find(query, ExMatchOddsBetMongo.class);
		  
}


	@Override
	public long findBetListByPropertiesCountByMarketId(Integer matchId, Boolean isActive, String userid,
			Integer usertype, String serchBy, String marketname) {
		
		
		final Query query = new Query();// .with(page);
		
		try {
		
//		     query.fields().include("id").include("name");
			final List<Criteria> criteria = new ArrayList<>();
			if (matchId != null )
				criteria.add(Criteria.where("matchid").is(matchId));
			if (isActive != null)
				criteria.add(Criteria.where("isactive").is(isActive));
			if (usertype  == 4)
				criteria.add(Criteria.where("adminid").is(userid));
			if (usertype  == 6)
				criteria.add(Criteria.where("adminid").is("1"));
			if (usertype  == 5)
				criteria.add(Criteria.where("subadminid").is(userid));
			if (usertype  == 0)
				criteria.add(Criteria.where("supermasterid").in(userid));
			if (usertype  == 1)
				criteria.add(Criteria.where("masterid").is(userid));
			if (usertype  == 2)
				criteria.add(Criteria.where("dealerid").is(userid));
			
			    
			if (!criteria.isEmpty()) {
				query.addCriteria(new Criteria().andOperator(criteria.toArray(new Criteria[criteria.size()])))	.with(Sort.by(Sort.Direction.DESC, "series"));
				
			}
			
			
		} catch(Exception ex) {
			//log.info("Exception Occured: "+ex.getMessage());
			ex.printStackTrace();
		}
		long count = mongoTemplate.count(query, ExMatchOddsBetMongo.class);
		return count;
		
	}

}
