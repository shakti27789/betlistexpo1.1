package com.khelobet.exposure.Dao;

import java.util.List;

import com.khelobet.exposure.bean.ExMatchOddsBetMongo;

public interface ExMatchOddsBetMongoCustomRepository {

	
	
	List<ExMatchOddsBetMongo> findBetListByProperties(Integer matchid, Boolean isActive, String userid,Integer usertype, String serchBy,String marketname,Integer limit,String marketId);

	long  findBetListByPropertiesCount(Integer matchid, Boolean isActive, String userid,Integer usertype, String serchBy,String marketname,String marketId);

	
	List<ExMatchOddsBetMongo> findBetListByPropertiesByMarketId(Integer matchId, Boolean isActive, String userid,Integer usertype, String serchBy,String marketname,Integer limit);

	long  findBetListByPropertiesCountByMarketId(Integer matchId, Boolean isActive, String userid,Integer usertype, String serchBy,String marketname);

}
