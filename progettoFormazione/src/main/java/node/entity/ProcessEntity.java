package node.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "processo")
public class ProcessEntity {

	@Id
	@Column(name = "uuid_processo")
	private int uuidProcess;
	@Column(name = "data_ora")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@Column(name = "status")
	private String status;
	@OneToOne(mappedBy = "uuidProc")
	private ExecutionEntity execution;

	public int getUuidProcess() {
		return uuidProcess;
	}

	public void setUuidProcess(int uuidProcess) {
		this.uuidProcess = uuidProcess;
	}

	public ExecutionEntity getExecution() {
		return execution;
	}

	public void setExecution(ExecutionEntity execution) {
		this.execution = execution;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
