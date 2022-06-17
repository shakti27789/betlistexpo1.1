package com.khelobet.exposure.Dao;

import java.util.ArrayList;
import java.util.List;

import com.khelobet.exposure.bean.UserLiability;



public interface EXUserPnlDao {

	ArrayList<UserLiability> pnlparentIdWise(List<String> ids,Integer usertype,Integer eventid,Integer parentusertype,String marketid);
}
