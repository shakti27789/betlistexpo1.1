package com.khelobet.exposure.Dao;

import java.util.ArrayList;

import com.khelobet.exposure.bean.UserLiability;

public interface EXNativeQueryDao {

	public	ArrayList<UserLiability> userPnl(String marketId, String userId, String partnership,Integer usertype,String gropuBypartnership);
	
}
