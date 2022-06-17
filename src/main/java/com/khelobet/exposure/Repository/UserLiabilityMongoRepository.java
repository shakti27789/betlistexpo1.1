package com.khelobet.exposure.Repository;

import java.util.ArrayList;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.khelobet.exposure.bean.UserLiabilityMongo;

public interface UserLiabilityMongoRepository extends MongoRepository<UserLiabilityMongo, String> {

	
	@Query(value="{ 'adminid' : ?0 , 'marketid' : ?1 }",fields="{ 'marketid' : 1,'adminpartnership' : 1 ,'pnl1' : 1,'pnl2' : 1,'pnl3' : 1,'selection1' : 1,'selection2' : 1,'selection3' : 1,'userid' : 1}")
	ArrayList<UserLiabilityMongo> findByAdminidAndMarketid(String adminid,String marketid);
	
	@Query(value="{ 'subadminid' : ?0 , 'marketid' : ?1 }",fields="{ 'marketid' : 1,'subadminpartnership' : 1 ,'pnl1' : 1,'pnl2' : 1,'pnl3' : 1,'selection1' : 1,'selection2' : 1,'selection3' : 1,'userid' : 1}")
	ArrayList<UserLiabilityMongo> findBySubadminidAndMarketid(String subadminid,String marketid);

	@Query(value="{ 'supermasterid' : ?0 , 'marketid' : ?1 }",fields="{ 'marketid' : 1,'supermasterpartnership' : 1 ,'pnl1' : 1,'pnl2' : 1,'pnl3' : 1,'selection1' : 1,'selection2' : 1,'selection3' : 1,'userid' : 1}")
	ArrayList<UserLiabilityMongo> findBySupermasteridAndMarketid(String supermasterid,String marketid);

	
	@Query(value="{ 'masterid' : ?0 , 'marketid' : ?1 }",fields="{ 'marketid' : 1,'masterpartnership' : 1 ,'pnl1' : 1,'pnl2' : 1,'pnl3' : 1,'selection1' : 1,'selection2' : 1,'selection3' : 1,'userid' : 1}")
	ArrayList<UserLiabilityMongo> findByMasteridAndMarketid(String masterid,String marketid);

	@Query(value="{ 'dealerid' : ?0 , 'marketid' : ?1 }",fields="{ 'marketid' : 1,'dealerpartnership' : 1 ,'pnl1' : 1,'pnl2' : 1,'pnl3' : 1,'selection1' : 1,'selection2' : 1,'selection3' : 1,'userid' : 1}")
	ArrayList<UserLiabilityMongo> findByDealeridAndMarketid(String dealerid,String marketid);
	
	@Query(value="{ 'marketid' : ?0 , 'isactive' : ?1 }",fields="{ 'marketid' : 1, 'userid' : 1 ,'adminpartnership' : 1 ,'subadminpartnership' : 1,'userid' : 1}")
	ArrayList<UserLiabilityMongo> findByMarketidAndIsactive(String marketid,Boolean IsActive);
	
	ArrayList<UserLiabilityMongo> findByMarketidAndIsactiveAndSubadminid(String marketid,Boolean IsActive,String adminid);
	
	ArrayList<UserLiabilityMongo> findByMarketidAndIsactiveAndSupermasterid(String marketid,Boolean IsActive,String adminid);
	
	ArrayList<UserLiabilityMongo> findByMarketidAndIsactiveAndMasterid(String marketid,Boolean IsActive,String adminid);
	
	ArrayList<UserLiabilityMongo> findByMarketidAndIsactiveAndDealerid(String marketid,Boolean IsActive,String adminid);
	
  public ArrayList<UserLiabilityMongo> 	findByMatchidAndIsactive(Integer matchId,Boolean isActive);
  
  public UserLiabilityMongo 	findByUseridAndMarketid(String matchId,String isActive);

  
  ArrayList<UserLiabilityMongo> findByMarketid(String marketid);
	
	
}
