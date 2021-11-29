package node.kafka;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import node.model.SomministrationsDto;

@Service
public class KafkaService{
	
	@Autowired
	private KafkaTemplate<String, SomministrationsDto> kafkaTemplate;
	
	public void sendMessage(String topicName, List<SomministrationsDto> somministrations) {
		for(SomministrationsDto sommDto: somministrations) {
			kafkaTemplate.send(topicName ,sommDto);
		}
        
//	    ListenableFuture<SendResult<String, List<SomministrationsDto>>> future = 
//	      kafkaTemplate.send("somministrazioni-anticovid19-lombardia", somministrations);
//		
//	    future.addCallback(new ListenableFutureCallback<SendResult<String, List<SomministrationsDto>>>() {
//
//	        @Override
//	        public void onSuccess(SendResult<String, List<SomministrationsDto>> result) {
//	            System.out.println("Sent message=[" + somministrations + 
//	              "] with offset=[" + result.getRecordMetadata().offset() + "]");
//	        }
//	        @Override
//	        public void onFailure(Throwable ex) {
//	            System.out.println("Unable to send message=[" 
//	              + somministrations + "] due to : " + ex.getMessage());
//	        }
//	    });
	}
}