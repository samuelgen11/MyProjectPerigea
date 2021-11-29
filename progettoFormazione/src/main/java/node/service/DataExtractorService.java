package node.service;

import java.text.ParseException;
import java.util.List;

import node.entity.ExecutionEntity;
import node.entity.ProcessEntity;
import node.model.AbbreviationsDto;
import node.model.ExecutionDto;
import node.model.ProcessDto;
import node.model.SomministrationsDto;

public interface DataExtractorService {

	public String dataClient() throws Exception;

	public List<AbbreviationsDto> provinceClient() throws Exception;

	public List<ExecutionDto> getAllEsecutions();

	public List<ProcessDto> getProcessByDate(String date) throws ParseException;

	public List<ProcessDto> getAllProcess();

	public List<AbbreviationsDto> checkProvince() throws Exception;

	public List<SomministrationsDto> checkSomministration() throws Exception;

	public List<SomministrationsDto> fromJsonToJava() throws Exception;

	public List<SomministrationsDto> runProcess() throws Exception;
}
