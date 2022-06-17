package com.khelobet.exposure.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khelobet.exposure.bean.Market;


public interface EXMarketRepository  extends JpaRepository<Market, Integer> {

	public ArrayList<Market> findByEventidAndStatus(Integer eventId,Boolean status);
	
	public ArrayList<Market> findByEventidAndIsactiveAndStatus(Integer eventid, Boolean isactive, Boolean status);
	
} 
