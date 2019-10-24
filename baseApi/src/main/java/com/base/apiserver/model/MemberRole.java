package com.base.apiserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Entity
@Table(name = "MemberRoles")
public class MemberRole {
	
	@Id
	@Column(length = 50)
	private String id;
	
	@Column(nullable = false, length = 50)
	private String name;
}
