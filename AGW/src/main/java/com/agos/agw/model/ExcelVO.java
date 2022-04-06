package com.agos.agw.model;

public class ExcelVO {
	private String usr_id;
	private String usr_nm;
	private String usr_email;
	private String usr_position;
	private String usr_phone;
	private String usr_address;
	
	public String getUsr_id() {
		return usr_id;
	}
	public void setUsr_id(String usr_id) {
		this.usr_id = usr_id;
	}
	public String getUsr_nm() {
		return usr_nm;
	}
	public void setUsr_nm(String usr_nm) {
		this.usr_nm = usr_nm;
	}
	public String getUsr_email() {
		return usr_email;
	}
	public void setUsr_email(String usr_email) {
		this.usr_email = usr_email;
	}
	public String getUsr_position() {
		return usr_position;
	}
	public void setUsr_position(String usr_position) {
		this.usr_position = usr_position;
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
	
	@Override
	public String toString() {
		return "ExcelVO [usr_id=" + usr_id + ", usr_nm=" + usr_nm + ", usr_email=" + usr_email + ", usr_position="
				+ usr_position + ", usr_phone=" + usr_phone + ", usr_address=" + usr_address + "]";
	}

	
}
