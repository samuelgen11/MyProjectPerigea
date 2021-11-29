package node.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "esecuzione")
public class ExecutionEntity {

	@Id
	@Column(name = "id_esecuzione")
	private Integer idProcess;
	@Column(name = "tempo")
	private Long time;
	@Column(name = "esito")
	private String result;
	@OneToOne
	@JoinColumn(name = "uuid_proc", referencedColumnName = "uuid_processo")
	private ProcessEntity uuidProc;

	public ProcessEntity getUuidProc() {
		return uuidProc;
	}

	public void setUuidProc(ProcessEntity uuidProc) {
		this.uuidProc = uuidProc;
	}

	public Integer getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(Integer idProcess) {
		this.idProcess = idProcess;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
