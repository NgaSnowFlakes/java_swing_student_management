package Controllers;

public class Grade {
	private String masv;
	private int english;
	private int tech;
	private int pe;
	private double DTB;
	public Grade(String masv, int english, int tech, int pe, double dTB) {
		super();
		this.masv = masv;
		this.english = english;
		this.tech = tech;
		this.pe = pe;
		DTB = dTB;
	}
	public String getMasv() {
		return masv;
	}
	public void setMasv(String masv) {
		this.masv = masv;
	}
	public int getEnglish() {
		return english;
	}
	public void setEnglish(int english) {
		this.english = english;
	}
	public int getTech() {
		return tech;
	}
	public void setTech(int tech) {
		this.tech = tech;
	}
	public int getPe() {
		return pe;
	}
	public void setPe(int pe) {
		this.pe = pe;
	}
	public double getDTB() {
		return DTB;
	}
	public void setDTB(double dTB) {
		DTB = dTB;
	}
	
}
