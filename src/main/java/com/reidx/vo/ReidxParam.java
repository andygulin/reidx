package com.reidx.vo;

import java.io.Serializable;

public class ReidxParam implements Serializable {

	private static final long serialVersionUID = 6842483948219256910L;
	private String reidxId;
	private String num;
	private String type;

	public String getReidxId() {
		return reidxId;
	}

	public void setReidxId(String reidxId) {
		this.reidxId = reidxId;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "id:" + this.reidxId + " -> " + "num:" + this.num + " -> "
				+ "type:" + this.type;
	}

}
