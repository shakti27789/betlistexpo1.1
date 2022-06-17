package com.khelobet.exposure.Repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.khelobet.exposure.bean.ExMatchOddsBetMongo;

@Repository
public interface ExMatchOddsBetMongoRepos extends MongoRepository<ExMatchOddsBetMongo, String> {

	
	@Query(value="{ 'marketid' : ?0 , 'isactive' : ?1 , 'userid' : ?2 }",fields="{ 'pnl' : 1, 'liability' : 1 ,'selectionid' : 1}")
	 public ArrayList<ExMatchOddsBetMongo> findByMarketidAndIsactiveAndUserid(String marketid, Boolean isactive,String userid);
	
	public Optional<ExMatchOddsBetMongo> findById(String id);
	
	
    ArrayList<ExMatchOddsBetMongo> findByAdminidAndMarketid(String adminid,String marketId);
	
	ArrayList<ExMatchOddsBetMongo> findBySubadminidAndMarketid(String adminid,String marketId);

	ArrayList<ExMatchOddsBetMongo> findBySupermasteridAndMarketid(String adminid,String marketId);

	ArrayList<ExMatchOddsBetMongo> findByMasteridAndMarketid(String adminid,String marketId);

	ArrayList<ExMatchOddsBetMongo> findByDealeridAndMarketid(String adminid,String marketId);
	
   ArrayList<ExMatchOddsBetMongo> findByUseridAndMarketid(String adminid,String marketId);

   public ArrayList<ExMatchOddsBetMongo> findByMarketidAndIsactive(String marketId, Boolean isActive);

   
   
}
