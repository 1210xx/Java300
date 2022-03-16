package priv.rj.learning.rorm.po;

import java.sql.*;
import java.util.*;

public class T_user {

	private java.sql.Timestamp regTime;
	private Integer id;
	private String pwd;
	private String username;


	public java.sql.Timestamp getRegTime(){
		return regTime;
	}
	public Integer getId(){
		return id;
	}
	public String getPwd(){
		return pwd;
	}
	public String getUsername(){
		return username;
	}


	public void setRegTime(java.sql.Timestamp regTime){
		this.regTime = regTime;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public void setPwd(String pwd){
		this.pwd = pwd;
	}
	public void setUsername(String username){
		this.username = username;
	}


}
