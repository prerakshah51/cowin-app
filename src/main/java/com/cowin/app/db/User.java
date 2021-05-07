package com.cowin.app.db;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cowin_user")
@NamedQuery(name = "findAllUser", query = "SELECT u FROM User u")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;

	@NotNull
	public String name;

	@NotNull
	public String district;

	@NotNull
	public String email;

	@NotNull
	public Long mobile;

	@NotNull
	public Long pincode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public User() {
		super();
	}

	public User(String name, String district, String email, Long mobile, Long pincode) {
		super();
		this.name = name;
		this.district = district;
		this.email = email;
		this.mobile = mobile;
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", district=" + district + ", email=" + email + ", mobile="
				+ mobile + ", pincode=" + pincode + "]";
	}
}