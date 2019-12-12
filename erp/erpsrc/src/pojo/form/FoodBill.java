package pojo.form;

import java.util.Date;

public class FoodBill {
	private String cbillC;
	private Date dbusiness;
	private String ctable = "";
	private String cperiod = "";
	private String cshift = "";
	private Integer iguestnum = 0;
	private Date dtbilltime;
	private Date dtsettletime;
	private Double nfoodamt = 0.0;
	private Double ndisamt = 0.0;
	private Double nroundamt = 0.0;
	private Double noughtamt = 0.0;
	private String ccreateman = "";
	private String csettleman = "";
	private String sremark = "";
	private Double npayamt;
	private String ssendman;
	private String cbranchC;
	private String cbranchN;
	private String cdismancurN;
	private Double npresentamt;
	private String cdiscurwhy;

	public String getCbillC() {
		return this.cbillC;
	}

	public void setCbillC(String cbillC) {
		this.cbillC = cbillC;
	}

	public Date getDbusiness() {
		return this.dbusiness;
	}

	public void setDbusiness(Date dbusiness) {
		this.dbusiness = dbusiness;
	}

	public String getCtable() {
		return this.ctable;
	}

	public void setCtable(String ctable) {
		this.ctable = ctable;
	}

	public String getCperiod() {
		return this.cperiod;
	}

	public void setCperiod(String cperiod) {
		this.cperiod = cperiod;
	}

	public String getCshift() {
		return this.cshift;
	}

	public void setCshift(String cshift) {
		this.cshift = cshift;
	}

	public Integer getIguestnum() {
		return this.iguestnum;
	}

	public void setIguestnum(Integer iguestnum) {
		this.iguestnum = iguestnum;
	}

	public Date getDtbilltime() {
		return this.dtbilltime;
	}

	public void setDtbilltime(Date dtbilltime) {
		this.dtbilltime = dtbilltime;
	}

	public Date getDtsettletime() {
		return this.dtsettletime;
	}

	public void setDtsettletime(Date dtsettletime) {
		this.dtsettletime = dtsettletime;
	}

	public Double getNfoodamt() {
		return this.nfoodamt;
	}

	public void setNfoodamt(Double nfoodamt) {
		this.nfoodamt = nfoodamt;
	}

	public Double getNdisamt() {
		return this.ndisamt;
	}

	public void setNdisamt(Double ndisamt) {
		this.ndisamt = ndisamt;
	}

	public Double getNroundamt() {
		return this.nroundamt;
	}

	public void setNroundamt(Double nroundamt) {
		this.nroundamt = nroundamt;
	}

	public Double getNoughtamt() {
		return this.noughtamt;
	}

	public void setNoughtamt(Double noughtamt) {
		this.noughtamt = noughtamt;
	}

	public String getCcreateman() {
		return this.ccreateman;
	}

	public void setCcreateman(String ccreateman) {
		this.ccreateman = ccreateman;
	}

	public String getCsettleman() {
		return this.csettleman;
	}

	public void setCsettleman(String csettleman) {
		this.csettleman = csettleman;
	}
	
	public String getSremark() {
		return this.sremark;
	}

	public void setSremark(String sremark) {
		this.sremark = sremark;
	}

	public Double getNpayamt() {
		return this.npayamt;
	}

	public void setNpayamt(Double npayamt) {
		this.npayamt = npayamt;
	}

	public String getSsendman() {
		return this.ssendman;
	}

	public void setSsendman(String ssendman) {
		this.ssendman = ssendman;
	}

	public String getCbranchC() {
		return this.cbranchC;
	}

	public void setCbranchC(String cbranchC) {
		this.cbranchC = cbranchC;
	}

	public String getCbranchN() {
		return this.cbranchN;
	}

	public void setCbranchN(String cbranchN) {
		this.cbranchN = cbranchN;
	}

	public String getCdismancurN() {
		return this.cdismancurN;
	}

	public void setCdismancurN(String cdismancurN) {
		this.cdismancurN = cdismancurN;
	}

	public Double getNpresentamt() {
		return this.npresentamt;
	}

	public void setNpresentamt(Double npresentamt) {
		this.npresentamt = npresentamt;
	}

	public String getCdiscurwhy() {
		return this.cdiscurwhy;
	}

	public void setCdiscurwhy(String cdiscurwhy) {
		this.cdiscurwhy = cdiscurwhy;
	}
}