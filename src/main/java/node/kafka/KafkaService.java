package node.kafka;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import node.controller.Controller;
import node.entity.SomministrationsEntity;
import node.model.SomministrationsDto;
import node.mongo.MongoDB;
import node.repository.SomministrationsRepository;


@Service
public class KafkaService {
	@Value(value = "${kafka.bootstrapAddress}")
	private String bootstrapAddress;

	@Value(value = "${spring.kafka.consumer.group-id}")
	private String groupId;

	private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

	@Autowired
	private KafkaConsumer<String, SomministrationsDto> kafkaConsumer;

	@Autowired
	private MongoDB mongo;
	
	@Autowired
	private  SomministrationsRepository sommRepository;
	
	
	public void consumeMessages(String topicName) {
		int recordCount = 0;
		List<SomministrationsEntity> entityList = sommRepository.findAll();
		List<SomministrationsDto> messagesfromKafka = new ArrayList<>();
		kafkaConsumer.subscribe(Arrays.asList(topicName));
		TopicPartition topicPartition = new TopicPartition(topicName, 0);
		List<TopicPartition> list = new ArrayList<>();
		list.add(topicPartition);
		LOGGER.info("Subscribed to topic " + kafkaConsumer.listTopics());
		Date start = new Date();
		try {
			while (true) {
				ConsumerRecords<String, SomministrationsDto> records = kafkaConsumer.poll(1000);
				recordCount = records.count();
				LOGGER.info("recordCount " + recordCount);
				for (ConsumerRecord<String, SomministrationsDto> record : records) {
					SomministrationsDto dto = new SomministrationsDto();
					dto = record.value();
					messagesfromKafka.add(dto);
				}
				Date now = new Date();
				if (messagesfromKafka.size() >= 500 || now.getTime() - start.getTime() > 900000) {
					for (SomministrationsDto sommDto : messagesfromKafka) {
						mongo.insertToMongoDB(sommDto);
						LOGGER.info("Dato inserito a DB");
					}
					start = now;
				
				}
			}
		}

		catch (Exception ex) { 
			ex.printStackTrace();
			kafkaConsumer.close();
		}
	}


	private void findAll() {
		// TODO Auto-generated method stub
		
	}
}