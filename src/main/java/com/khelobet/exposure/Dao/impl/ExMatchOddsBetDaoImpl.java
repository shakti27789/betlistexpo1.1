package com.khelobet.exposure.Dao.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.khelobet.exposure.Dao.ExMatchOddsBetDao;
import com.khelobet.exposure.bean.ExMatchOddsBet;

@Repository
public class ExMatchOddsBetDaoImpl implements ExMatchOddsBetDao {

	@PersistenceContext
	private EntityManager entitymanager;
	
	@Override
	public BigInteger getMatchedBetsTotalCount(String id, String type, Boolean active, Integer matchid, String marketname) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		
		StringBuffer sb  = new StringBuffer();
		
		sb.append(" select count(*) from  t_betlist where ");
		
	
		if(type.equalsIgnoreCase("adminid")){
			sb.append(" adminid=:adminid and");
		}
		if(type.equalsIgnoreCase("masterid")){
			sb.append(" masterid=:masterid and");
		}
		if(type.equalsIgnoreCase("dealerid")){
			sb.append(" dealerid=:dealerid and");
		}
		if(type.equalsIgnoreCase("subadminid")){
			sb.append(" subadminid=:subadminid and");
		}
		if(type.equalsIgnoreCase("supermasterid")){
			sb.append(" supermasterid=:supermasterid and");
		}
		sb.append(" matchid=:matchid and");
		sb.append(" isactive=:isactive  and marketname =:marketname order by series desc");
		
		
		Query query = (Query) entitymanager.createNativeQuery(sb.toString());
		
		
		if(type.equalsIgnoreCase("adminid")){
			query.setParameter("adminid", id);
		}
		if(type.equalsIgnoreCase("masterid")){
			query.setParameter("masterid", id);
		}
		if(type.equalsIgnoreCase("dealerid")){
			query.setParameter("dealerid", id);
		}
		if(type.equalsIgnoreCase("subadminid")){
			query.setParameter("subadminid", id);
		}
		if(type.equalsIgnoreCase("supermasterid")){
			query.setParameter("supermasterid", id);
		}
		query.setParameter("matchid", matchid);
		query.setBoolean("isactive", true);
		query.setParameter("marketname", marketname);
		
	
		
	
		
		BigInteger count = (BigInteger)query.uniqueResult();
		Long c=(long) 0;
		return count;
	
	}

	@Override
	public ArrayList<ExMatchOddsBet> getMatchedBets(String id, String type, Boolean active, Integer matchid,
			String marketname, Integer startpage, Integer endpage, Integer limit) {
		// TODO Auto-generated method stub
		
		StringBuffer sb  = new StringBuffer();
		
		sb.append("select series,odds,stake,isback,userid,pnl,liability,marketname,matchedtime,selectionname,selectionid  from  t_betlist where ");
		
	
		if(type.equalsIgnoreCase("adminid")){
			sb.append(" adminid=:adminid and");
		}
		if(type.equalsIgnoreCase("masterid")){
			sb.append(" masterid=:masterid and");
		}
		if(type.equalsIgnoreCase("dealerid")){
			sb.append(" dealerid=:dealerid and");
		}
		if(type.equalsIgnoreCase("subadminid")){
			sb.append(" subadminid=:subadminid and");
		}
		if(type.equalsIgnoreCase("supermasterid")){
			sb.append(" supermasterid=:supermasterid and");
		}
		sb.append(" matchid=:matchid and");
		sb.append(" isactive=:isactive  and marketname =:marketname order by matchedtime desc ");
		if(limit>0) {
			sb.append(" limit 100");
		}
		
		Query query = (Query) entitymanager.createNativeQuery(sb.toString());
		
		if(type.equalsIgnoreCase("adminid")){
			query.setParameter("adminid", id);
		}
		if(type.equalsIgnoreCase("masterid")){
			query.setParameter("masterid", id);
		}
		if(type.equalsIgnoreCase("dealerid")){
			query.setParameter("dealerid", id);
		}
		if(type.equalsIgnoreCase("subadminid")){
			query.setParameter("subadminid", id);
		}
		if(type.equalsIgnoreCase("supermasterid")){
			query.setParameter("supermasterid", id);
		}
		query.setParameter("matchid", matchid);
		query.setBoolean("isactive", true);
		query.setParameter("marketname", marketname);
	
		/*
		 * if(endpage>0) { if(startpage >-1){ query.setFirstResult(startpage);
		 * query.setMaxResults(0 + endpage); } }
		 */
	
		List<Object[]> resultList = query.getResultList();
		ExMatchOddsBet  betlist =null;
		ArrayList<ExMatchOddsBet> list = new ArrayList<ExMatchOddsBet>();
		for (Object[] a : resultList) {
			betlist = new ExMatchOddsBet();
			betlist =betlist.matchedBet(a);
			list.add(betlist);
			
		}
	
		
		//ArrayList<ExMatchOddsBet> resultList = (ArrayList<ExMatchOddsBet>) query.list();
		return list;
	}

	@Override
	public ArrayList<ExMatchOddsBet> getFancyBookExposure(String user, String userid, List<String> fancyids,
			String partnership) {
		// TODO Auto-generated method stub
		ArrayList<ExMatchOddsBet> list = new ArrayList<ExMatchOddsBet>();
		
			StringBuffer sb  = new StringBuffer();

			//sb.append("select sum(bt.stake) as stake ,bt.odds,sum(bt.pnl) as pnl,sum(bt.liability) as liblity from  ExMatchOddsBet bt  where bt.marketid =:marketid and bt.isactive =:isactive and bt."+user+" =:userid and bt.matchid =:matchid order by bt.odds desc   ");
			
			sb.append("select  bt.stake,bt.odds,bt.pnl,bt.liability,"+partnership+",bt.isback,bt.marketid  from  ExMatchOddsBet bt, EXUser usr  where bt.isactive =:isactive and bt."+user+" =:userid and bt.userid=usr.userid and bt.marketid IN :marketid  order by odds desc   ");
			
			
			Query query = (Query) entitymanager.createQuery(sb.toString());

			query.setParameter("userid", userid);
			query.setParameter("marketid", fancyids);
			query.setBoolean("isactive", true);
			
			
			//sb.append("  sportid in (:sportids)");
			
			
			List<Object[]> resultList1 = query.getResultList();
			ArrayList<ExMatchOddsBet> resultList =null;
			ExMatchOddsBet betlist=null;
			for (Object[] a : resultList1) {
				betlist = new ExMatchOddsBet();
				betlist =betlist.getFancyBook(a);
				list.add(betlist);
				}
			
			return list;

	}

}
