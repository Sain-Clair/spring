package kr.co.ictedu.mvc.dto;

public class BoardCommVO {

	private int ucode;
	private String uwriter;
	private String ucontent;
	private String reip;
	private String uregdate;
	private int num;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getUcode() {
		return ucode;
	}
	public void setUcode(int ucode) {
		this.ucode = ucode;
	}
	public String getUwriter() {
		return uwriter;
	}
	public void setUwriter(String uwriter) {
		this.uwriter = uwriter;
	}
	public String getUcontent() {
		return ucontent;
	}
	public void setUcontent(String ucontent) {
		this.ucontent = ucontent;
	}
	public String getReip() {
		return reip;
	}
	public void setReip(String reip) {
		this.reip = reip;
	}
	public String getUregdate() {
		return uregdate;
	}
	public void setUregdate(String uregdate) {
		this.uregdate = uregdate;
	}


}
