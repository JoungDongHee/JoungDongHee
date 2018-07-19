package bean;

public class MemberBean {
	private int MEMBERNUM;
	private String NAME;
	private int AGE;
	private String EMAIL;
	private String PASS;
	private String ID;
	private int PHONE;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getGENDER() {
		return GENDER;
	}
	public void setGENDER(String gENDER) {
		GENDER = gENDER;
	}
	private String GENDER;
	
	public int getMEMBERNUM() {
		return MEMBERNUM;
	}
	public void setMEMBERNUM(int mEMBERNUM) {
		MEMBERNUM = mEMBERNUM;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public int getAGE() {
		return AGE;
	}
	public void setAGE(int aGE) {
		AGE = aGE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getPASS() {
		return PASS;
	}
	public void setPASS(String pASS) {
		PASS = pASS;
	}
	public int getPHONE() {
		return PHONE;
	}
	public void setPHONE(int pHONE) {
		PHONE = pHONE;
	}

}
