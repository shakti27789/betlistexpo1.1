package com.khelobet.exposure.Repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.khelobet.exposure.bean.MatchFancy;


@Repository
public interface FancyRepository extends JpaRepository<MatchFancy, String>{

	public ArrayList<MatchFancy> findByEventidAndIsActive(Integer eventId,Boolean isActive);

}
