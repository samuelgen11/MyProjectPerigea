package node.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import node.entity.ExecutionEntity;
import node.entity.ProcessEntity;
import node.kafka.KafkaService;
import node.model.AbbreviationsDto;
import node.model.ExecutionDto;
import node.model.ProcessDto;
import node.model.SomministrationsDto;
import node.repository.ExecutionRepository;
import node.repository.ProcessRepository;
import node.service.DataExtractorService;

@RestController

@RequestMapping("/Vaccini")
public class Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Value("${spring.application.name}")
	private String appName;

	@Value("${topicName}")
	private String topicName;

	@Autowired
	DataExtractorService service;

	@Autowired
	KafkaTemplate<String, SomministrationsDto> kafkaJsonTemplate;

	@Autowired
	private KafkaService kafkaService;

	@GetMapping("/dataClient")
	public ResponseEntity<String> dataClient() {
		try {
			String resultRequest = service.dataClient();
			return ResponseEntity.ok().body("The process ended succesfully");
		} catch (Exception exc) {
			return ResponseEntity.ok().body("Status failure.");
		}
	}

	@GetMapping("/provinceExtractor")
	public ResponseEntity<List<AbbreviationsDto>> provinceClient() throws Exception {
		try {
			List<AbbreviationsDto> resultRequest = service.provinceClient();
			return ResponseEntity.ok().body(resultRequest);
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;

		}
	}

	@GetMapping("/fromJsonToJava")
	public ResponseEntity<String> fromJsonToJava() {
		try {
			List<SomministrationsDto> resultRequest = service.runProcess();
			return ResponseEntity.ok().body("The process ended succesfully");
		} catch (Exception exc) {
			return ResponseEntity.ok().body("Status failure.");
		}
	}

	@PostMapping(value = "/postSomm")
	@ResponseBody
	public List<SomministrationsDto> postJsonMessage() throws Exception {
		List<SomministrationsDto> listSommDto = service.runProcess();
		kafkaService.sendMessage(topicName, listSommDto);
		return listSommDto;
	}

	@GetMapping("/getListEsecutions")
	public ResponseEntity<List<ExecutionDto>> getAllEsecutions() {
		List<ExecutionDto> response = service.getAllEsecutions();
		if (!response.isEmpty()) {
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/getProcessByDate")
	@ResponseBody
	public ResponseEntity<List<ProcessDto>> getProcessByDate(@RequestParam String date) throws ParseException {
		List<ProcessDto> resultRequest = service.getProcessByDate(date);
		return ResponseEntity.ok().body(resultRequest);
	}

	@GetMapping("/getListProcess")
	public ResponseEntity<List<ProcessDto>> getAllProcess() {
		List<ProcessDto> response = service.getAllProcess();
		if (!response.isEmpty()) {
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
