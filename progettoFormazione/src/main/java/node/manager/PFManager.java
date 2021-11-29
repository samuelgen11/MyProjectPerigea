package node.manager;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import node.controller.Controller;
import node.entity.AbbreviationsEntity;
import node.entity.ExecutionEntity;
import node.entity.ProcessEntity;
import node.model.AbbreviationsDto;
import node.model.ExecutionDto;
import node.model.ProcessDto;
import node.model.SomministrationsDto;
import node.repository.AbbreviationsRepository;
import node.repository.ExecutionRepository;
import node.repository.ProcessRepository;

public class PFManager {

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	public ProcessRepository processRepository;

	@Autowired
	public ExecutionRepository esecutionRepository;

	@Autowired
	public AbbreviationsRepository abbRepository;

	public List<SomministrationsDto> getModifiedDataList(String jsonString, List<AbbreviationsDto> province)
			throws Exception {
		List<SomministrationsDto> listSommDto = new ArrayList<>();
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(jsonString);
		ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("UTC"));
		jsonNode.forEach(data1Row -> {
			String extractedIstatCode = data1Row.get("codistat_comune_dom").asText();
			String extractedMunicipality = data1Row.get("comune_dom").asText();
			String extractedProvince = data1Row.get("provincia_dom").asText();
			int extractedTotDose1 = data1Row.get("tot_dose1").asInt();
			int extractedTotDose2 = data1Row.get("tot_dose2").asInt();
			SomministrationsDto sommDto = new SomministrationsDto();
			sommDto.setCodIstat(extractedIstatCode);
			sommDto.setMunicipality(extractedMunicipality);
			sommDto.setProvince(extractedProvince);
			sommDto.setTotDose1(extractedTotDose1);
			sommDto.setTotDose2(extractedTotDose2);
			for (AbbreviationsDto s : province) {
				if (sommDto.getProvince().equalsIgnoreCase(s.getNome())) {
					sommDto.setAbbreviation(s.getSigla());
				} else {
					LOGGER.error("Sigla not found");
				}
			}
			sommDto.setDate(zonedDateTimeNow);
			listSommDto.add(sommDto);
		});
		LOGGER.info("List return correctly");
		return listSommDto;
	}

	public List<ExecutionDto> getExecutionDtoListFromDB(List<ExecutionEntity> list) {
		return list.stream().map(entity -> {
			ExecutionDto dto = new ExecutionDto();
			dto.setId(entity.getIdProcess());
			dto.setTime(entity.getTime());
			dto.setResult(entity.getResult());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<ProcessDto> getProcessDtoListFromDB(List<ProcessEntity> list) {
		return list.stream().map(entity -> {
			ProcessDto dto = new ProcessDto();
			dto.setUuid(entity.getUuidProcess());
			dto.setDateTime(entity.getDate());
			dto.setStatus(entity.getStatus());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<AbbreviationsDto> getAbbreviationsDtoListFromDB(List<AbbreviationsEntity> list) {
		return list.stream().map(entity -> {
			AbbreviationsDto dto = new AbbreviationsDto();
			dto.setCodice(entity.getCodice());
			dto.setNome(entity.getNome());
			dto.setRegione(entity.getRegione());
			dto.setSigla(entity.getSigla());
			return dto;
		}).collect(Collectors.toList());
	}

	public List<AbbreviationsEntity> getAbbreviationsListForDB(List<AbbreviationsDto> list) {
		List<AbbreviationsEntity> abbEntityList = new ArrayList<>();
		for (AbbreviationsDto abbDto : list) {
			AbbreviationsEntity abbEntity = new AbbreviationsEntity();
			abbEntity.setCodice(abbDto.getCodice());
			abbEntity.setNome(abbDto.getNome());
			abbEntity.setSigla(abbDto.getSigla());
			abbEntity.setRegione(abbDto.getRegione());
			abbEntityList.add(abbEntity);
		}
		return abbEntityList;
	}
	
	public ProcessEntity getProcessEntity(ProcessDto procDto) {
		ProcessEntity procEntity = new ProcessEntity();
		procEntity.setUuidProcess(procDto.getUuid());
		procEntity.setDate(procDto.getDateTime());
		procEntity.setStatus(procDto.getStatus());
		return procEntity;
	}
	
	public ExecutionEntity getExecutionEntity(ExecutionDto exeDto) {
		Random i = new Random();
		ExecutionEntity exeEntity = new ExecutionEntity();
		exeEntity.setIdProcess(i.nextInt());
		exeEntity.setTime(exeDto.getTime());
		exeEntity.setResult(exeDto.getResult());
		return exeEntity;
	}

}
