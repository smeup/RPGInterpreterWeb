package com.smeup.web.interpreter;

import java.io.Serializable;

public class RpgSource implements Serializable {

	private static final long serialVersionUID = 1L;

	private String content;
	private String rpgParmList;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRpgParmList() {
		return rpgParmList;
	}

	public void setRpgParmList(String rpgParmList) {
		this.rpgParmList = rpgParmList;
	}	
	
}