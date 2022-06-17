package com.khelobet.exposure.bean;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ResponseBean {

	private String type;
	private String message;
	private String title;
	private String balance;
	private String matchBet;
	private String rate;
	private Integer amount;
	private String team;
	private String mode;
	private String run;
	
	private LinkedHashMap<String,ArrayList<?>> hashmap;
	
	private ArrayList<?> data;
	
	
	
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public LinkedHashMap<String,ArrayList<?>> getHashmap() {
		return hashmap;
	}
	public void setHashmap(LinkedHashMap<String,ArrayList<?>> hashmap) {
		this.hashmap = hashmap;
	}
	public ArrayList<?> getData() {
		return data;
	}
	public void setData(ArrayList<?> data) {
		this.data = data;
	}
	public String getMatchBet() {
		return matchBet;
	}
	public void setMatchBet(String matchBet) {
		this.matchBet = matchBet;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getRun() {
		return run;
	}
	public void setRun(String run) {
		this.run = run;
	}
	
	
}
