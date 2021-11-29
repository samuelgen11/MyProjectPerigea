package node.entity;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SOMMINISTRAZIONI")
public class SomministrationsEntity {

	@Id
	@Column(name = "codice")
	private String codice;
	@Column(name = "comune")
	private String comune;
	@Column(name = "provincia")
	private String provincia;
	@Column(name = "sigla")
	private String sigla;
	@Column(name = "totDos1")
	private Integer dose1;
	@Column(name = "totDos2")
	private Integer dose2;
	@Column(name = "data")
	private String data;

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getDose1() {
		return dose1;
	}

	public void setDose1(Integer dose1) {
		this.dose1 = dose1;
	}

	public Integer getDose2() {
		return dose2;
	}

	public void setDose2(Integer dose2) {
		this.dose2 = dose2;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	
	
}
