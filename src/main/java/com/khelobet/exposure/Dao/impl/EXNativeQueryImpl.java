package com.khelobet.exposure.Dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.khelobet.exposure.Dao.EXNativeQueryDao;
import com.khelobet.exposure.bean.UserLiability;

@Repository
public class EXNativeQueryImpl  implements EXNativeQueryDao {

	
	@PersistenceContext
	private EntityManager entitymanager;
	
	
	@Override
	public ArrayList<UserLiability> userPnl(String marketId, String userId,String partnership,Integer userType,String gropuBypartnership) {
		// TODO Auto-generated method stub
		
		StringBuffer sb  = new StringBuffer();
		ArrayList<UserLiability> list = new ArrayList<UserLiability>();
		
		sb.append("select sum(lib.pnl1)*"+partnership +" as pnl1,");
		sb.append("sum(lib.pnl2)*"+partnership+" as pnl2,");
		
		sb.append("sum(lib.pnl3)*"+partnership+" as pnl3, mkt.marketid,lib.selection1,lib.selection2,lib.selection3");
		
		sb.append(" From t_libility lib ,t_market mkt  where lib.marketid=?");
		sb.append(" and mkt.isactive=true and mkt.marketid=? and " );
		if(userType == 4){
			sb.append("lib.adminid=?" );
		}else if(userType == 6){
			sb.append("lib.adminid=?" );
		}else if(userType == 5){
			sb.append("lib.subadminid=?" );
		}else if(userType == 0){
			sb.append("lib.supermasterid=?" );
		}else if(userType == 1){
			sb.append("lib.masterid=?" );
		}else{
			sb.append("lib.dealerid=?" );	
		}
		sb.append(" group by "+gropuBypartnership );	
		
		UserLiability bean =null;
		try{
			Query query = (Query) entitymanager.createNativeQuery(sb.toString());
			query.setParameter(1, marketId);
			query.setParameter(2, marketId);
			query.setParameter(3, userId);
			
			List<Object[]> resultList = query.getResultList();
			for (Object[] a : resultList) {
				
				if(a[0]!=null){
					bean = new UserLiability();
					bean.setPnl1((Double)a[0]);
					bean.setPnl2((Double)a[1]);
					bean.setPnl3((Double)a[2]);
					bean.setMarketid(a[3].toString());
					bean.setSelection1((Integer)a[4]);
					bean.setSelection2((Integer)a[5]);
					bean.setSelection3((Integer)a[6]);
					list.add(bean);
				}
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	
		return list;
	}
}
