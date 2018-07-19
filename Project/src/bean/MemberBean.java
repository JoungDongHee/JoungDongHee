package bean;

public class MemberBean {
	private int MEMBERNUM;
	private String NAME;
	private int AGE;
	private String EMAIL;
	private String PASS;
	private String ID;
	private int PHONE;
	private String Contents_Title;
	private String Contents_Num;
	private String Contents;
	private int Contents_READCOUNT;
	private int Contents_Date;
	
	public String getContents_Title() {
		return Contents_Title;
	}
	public void setContents_Title(String contents_Title) {
		Contents_Title = contents_Title;
	}
	public String getContents_Num() {
		return Contents_Num;
	}
	public void setContents_Num(String contents_Num) {
		Contents_Num = contents_Num;
	}
	public String getContents() {
		return Contents;
	}
	public void setContents(String contents) {
		Contents = contents;
	}
	public int getContents_READCOUNT() {
		return Contents_READCOUNT;
	}
	public void setContents_READCOUNT(int contents_READCOUNT) {
		Contents_READCOUNT = contents_READCOUNT;
	}
	public int getContents_Date() {
		return Contents_Date;
	}
	public void setContents_Date(int contents_Date) {
		Contents_Date = contents_Date;
	}
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
