package node.controller;

import java.util.List;

import org.apache.kafka.clients.admin.RecordsToDelete;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import node.kafka.KafkaService;
import node.model.JSONObject;
import node.mongo.MongoDB;

@RestController

@RequestMapping("/dataAggregator")
public class Controller {

	@Autowired
	private KafkaService kafkaService;

	private MongoDB mongo = new MongoDB();

	@Value(value = "${topicName}")
	private String topicName;

	@PostMapping(value = "/consumer")
	@ResponseBody
	public void fromTopicToJava() throws Exception {
		kafkaService.consumeMessages(topicName);

	}

//	@PostMapping(value = "/insertToMongoDB")
//	@ResponseBody
//	public  void insertToMongoDB() throws Exception {
//		mongo.insertToMongoDB();	
//	}

}
