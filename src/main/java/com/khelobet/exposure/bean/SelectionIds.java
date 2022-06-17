package com.khelobet.exposure.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@Table(name = "t_selectionid")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectionIds {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	@Column(name = "selectionid")
	private Integer selectionid;

	@Column(name = "runnerName")
	private String runnerName;

	@Column(name = "marketid")
	private String marketid;

}
