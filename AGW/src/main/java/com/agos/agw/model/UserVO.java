package com.agos.agw.model;

import java.util.Date;

public class UserVO {
	private String usr_idx;
	private String usr_id;
	private String usr_pw;
	private String usr_nm;
	private String usr_gender;
	private String usr_email;
	private Date usr_crea_date;
	private String usr_position;
	private String usr_right;
	private int usr_appr;
	private Date usr_birth;
	private String usr_phone;
	private String usr_address;
	private String usr_phone2;

	public String getUsr_idx() {
		return usr_idx;
	}
	public void setUsr_idx(String usr_idx) {
		this.usr_idx = usr_idx;
	}
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_pw() {
		return usr_pw;
	}
	public void setUsr_pw(String usr_pw) {
		this.usr_pw = usr_pw;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	public String getUsr_gender() {
		return usr_gender;
	}
	public void setUsr_gender(String usr_gender) {
		this.usr_gender = usr_gender;
	}
	public String getUsr_email() {
		return usr_email;
	}
	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	public Date getUsr_crea_date() {
		return usr_crea_date;
	}
	public void setUsr_crea_date(Date usr_crea_date) {
		this.usr_crea_date = usr_crea_date;
	}
	public String getUsr_position() {
		return usr_position;
	}
	public void setUsr_position(String usr_position) {
		this.usr_position = usr_position;
	}
	public String getUsr_right() {
		return usr_right;
	}
	public void setUsr_right(String usr_right) {
		this.usr_right = usr_right;
	}
	public int getUsr_appr() {
		return usr_appr;
	}
	public void setUsr_appr(int usr_appr) {
		this.usr_appr = usr_appr;
	}
	public Date getUsr_birth() {
		return usr_birth;
	}
	public void setUsr_birth(Date usr_birth) {
		this.usr_birth = usr_birth;
	}
	public String getUsr_phone() {
		return usr_phone;
	}
	public void setUsr_phone(String usr_phone) {
		this.usr_phone = usr_phone;
	}
	public String getUsr_address() {
		return usr_address;
	}
	public void setUsr_address(String usr_address) {
		this.usr_address = usr_address;
	}
	public String getUsr_phone2() {
		return usr_phone2;
	}
	public void setUsr_phone2(String usr_phone2) {
		this.usr_phone2 = usr_phone2;
	}
	@Override
	public String toString() {
		return "UserVO [usr_idx=" + usr_idx + ", usr_id=" + usr_id + ", usr_pw=" + usr_pw + ", usr_nm=" + usr_nm
				+ ", usr_gender=" + usr_gender + ", usr_email=" + usr_email + ", usr_crea_date=" + usr_crea_date
				+ ", usr_position=" + usr_position + ", usr_right=" + usr_right + ", usr_appr=" + usr_appr
				+ ", usr_birth=" + usr_birth + ", usr_phone=" + usr_phone + ", usr_address=" + usr_address
				+ ", usr_phone2=" + usr_phone2 + "]";
	}
	
}
