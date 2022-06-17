package com.khelobet.exposure.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
 
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.khelobet.exposure.Dao.EXUserPnlDao;
import com.khelobet.exposure.bean.UserLiability;


@Repository
public class EXUserPnlDaoImpl implements EXUserPnlDao {

	
	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public ArrayList<UserLiability> pnlparentIdWise(List<String> ids, Integer usertype, Integer matchId,Integer parentusertype, String marketid) {
		// TODO Auto-generated method stub
		
		
		StringBuffer sb  = new StringBuffer();
		UserLiability lib =null;
		ArrayList<UserLiability> list = new ArrayList<UserLiability>();
		String partnership =null;
		if(parentusertype ==5){
			partnership ="lib.subadminpartnership";
		}else if(parentusertype ==0){
			partnership ="lib.supermasterpartnership";
		}if(parentusertype ==1){
			partnership ="lib.masterpartnership";
		}if(parentusertype ==2){
			partnership ="lib.dealerpartnership";
		}if(parentusertype ==4){
			partnership ="lib.adminpartnership";
		}
		
		
		
		 if(usertype == 5){
			sb.append(" select  -sum(lib.pnl1*"+partnership+" )/100 as pnl1,-sum(lib.pnl2* "+partnership+")/100 as pnl2,-sum(lib.pnl3 * "+partnership+")/100 as pnl3,usr.username,lib.adminid,lib.subadminid,lib.supermasterid,lib.masterid,lib.dealerid ");
			sb.append(" from UserLiability lib,EXUser usr where lib.subadminid = usr.id and lib.matchid =:matchid and lib.subadminid in (:subadminid) and lib.isactive = :isactive and lib.marketid= :marketid  group by lib.subadminid "); 
			
		}else if(usertype == 0){
			sb.append(" select  -sum(lib.pnl1* "+partnership+")/100 as pnl1,-sum(lib.pnl2* "+partnership+")/100 as pnl2,-sum(lib.pnl3 * "+partnership+")/100 as pnl3,usr.username,lib.adminid,lib.subadminid,lib.supermasterid,lib.masterid,lib.dealerid ");
			sb.append(" from UserLiability lib,EXUser usr where lib.supermasterid = usr.id and lib.matchid =:matchid and lib.supermasterid in (:supermasterid)  and lib.isactive = :isactive  and lib.marketid= :marketid group by lib.supermasterid"); 
			
		}else if(usertype == 1){
			sb.append(" select  -sum(lib.pnl1* "+partnership+")/100 as pnl1,-sum(lib.pnl2*"+partnership+")/100 as pnl2,-sum(lib.pnl3 * "+partnership+")/100 as pnl3,usr.username,lib.adminid,lib.subadminid,lib.supermasterid,lib.masterid,lib.dealerid ");
			sb.append(" from UserLiability lib,EXUser usr where lib.masterid = usr.id and lib.matchid =:matchid and lib.masterid in (:masterid)  and lib.isactive = :isactive and lib.marketid= :marketid group by lib.masterid"); 
			
		}else if(usertype == 2){
			sb.append(" select  -sum(lib.pnl1* "+partnership+")/100 as pnl1,-sum(lib.pnl2* "+partnership+")/100 as pnl2,-sum(lib.pnl3 * "+partnership+")/100 as pnl3,usr.username,lib.adminid,lib.subadminid,lib.supermasterid,lib.masterid,lib.dealerid ");
			sb.append(" from UserLiability lib,EXUser usr where lib.dealerid = usr.id and lib.matchid =:matchid and lib.dealerid in (:dealerid) and lib.isactive = :isactive and  lib.marketid= :marketid group by lib.dealerid"); 
			
		}else if(usertype == 3){
			sb.append(" select  -sum(lib.pnl1 * "+partnership+")/100 as pnl1,-sum(lib.pnl2* "+partnership+")/100 as pnl2,-sum(lib.pnl3* "+partnership+")/100 as pnl3,usr.username,lib.adminid,lib.subadminid,lib.supermasterid,lib.masterid,lib.dealerid ");
			sb.append(" from UserLiability lib,EXUser usr where lib.userid = usr.userid and lib.matchid =:matchid  and usr.userid in (:userid) and lib.isactive = :isactive  and lib.marketid= :marketid group by lib.userid "); 
			
		}
		 
		Query query = (Query) entitymanager.createQuery(sb.toString());
		
		query.setBoolean("isactive", true);
		//query.setParameter("type", "Match Odds");
		query.setParameter("matchid",matchId);
		query.setParameter("marketid",marketid);
		if(usertype == 4){
			//query.setParameter("adminid",userid);
		} else if(usertype == 5){
			query.setParameterList("subadminid",ids);
		}else if(usertype == 0){
		query.setParameterList("supermasterid",ids);
		}else if(usertype == 1){
			query.setParameterList("masterid",ids);
		}else if(usertype == 2){
			query.setParameterList("dealerid",ids);
		}else if(usertype == 3){
			query.setParameter("userid",ids);
		}
		
		List<Object[]> result = query.getResultList();
 
		for (Object[] a : result) {
			lib = new UserLiability();
			lib =lib.getpnlChidIdWise(a);
			list.add(lib);
			
		}
		return list;
		
	}

}
