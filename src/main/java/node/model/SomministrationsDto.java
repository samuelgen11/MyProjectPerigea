package node.model;

import java.time.ZonedDateTime;

public class SomministrationsDto {


	private String codIstat;
	private String municipality;
	private String province;
	private Integer totDose1;
	private Integer totDose2;
	private String abbreviation;
	private ZonedDateTime date;

	public String getCodIstat() {
		return codIstat;
	}

	public void setCodIstat(String codIstat) {
		this.codIstat = codIstat;
	}

	public String getMunicipality() {
		return municipality;
	}

	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String provincee) {
		this.province = provincee;
	}

	public Integer getTotDose1() {
		return totDose1;
	}

	public void setTotDose1(Integer totDose1) {
		this.totDose1 = totDose1;
	}

	public Integer getTotDose2() {
		return totDose2;
	}

	public void setTotDose2(Integer totDose2) {
		this.totDose2 = totDose2;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public ZonedDateTime getDate() {
		return date;
	}

	public void setDate(ZonedDateTime zonedDateTimeNow) {
		this.date = zonedDateTimeNow;
	}

	

	public SomministrationsDto() {
		super();
	}
	
}