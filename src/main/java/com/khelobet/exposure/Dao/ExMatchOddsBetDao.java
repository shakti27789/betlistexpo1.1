package com.khelobet.exposure.Dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.khelobet.exposure.bean.ExMatchOddsBet;


public interface ExMatchOddsBetDao {

	public BigInteger  getMatchedBetsTotalCount(String id,String type,Boolean active,Integer matchid,String marketname);
	
	public ArrayList<ExMatchOddsBet>  getMatchedBets(String id,String type,Boolean active,Integer matchid,String marketname,Integer startpage,Integer endpage,Integer count);
	
	ArrayList<ExMatchOddsBet> getFancyBookExposure(String user, String userid, List<String> fancyids,
			String partnership);
}
