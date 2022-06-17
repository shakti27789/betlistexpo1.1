package com.khelobet.exposure.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khelobet.exposure.bean.ExMatchOddsBet;

@Repository
public interface EXMatchOddsBetRepository extends  JpaRepository<ExMatchOddsBet, Integer>{

	
	ArrayList<ExMatchOddsBet> findByMarketid(String marketId);
	
	ArrayList<ExMatchOddsBet> findByMarketidAndMatchidAndUserid(String marketid,Integer matchid,String userid);
}
