package com.khelobet.exposure.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khelobet.exposure.bean.EXUser;


public interface EXUserRepository extends JpaRepository<EXUser, Integer> {

	public ArrayList<EXUser> findByParentidAndActive(String parentId,Boolean active);
}
