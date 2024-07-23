package com.connect.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tbl_roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(updatable=false, nullable=false)
	private int id;
	
	@Column(unique=true, nullable=false, length=30)
	@Size(min=3,max=30,message="Role must have atleast 3-30 characters.")
	@NotNull(message="Role is required.")
	private String role;

	@OneToMany(fetch=FetchType.LAZY, orphaned=true)
	private List<User> users = new ArrayList<>();
	
	public Role() {
	}

	public Role(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "[ id: "+id+" role: "+role+"]";
	}
	
}
