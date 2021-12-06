package node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import node.kafka.KafkaService;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableMongoRepositories(basePackages = "node.repository")
public class Application {
	
	

	public static void main(String[] args) {
		ConfigurableApplicationContext context=SpringApplication.run(Application.class, args);
		KafkaService consumer = context.getBean(KafkaService.class);
		String topicName="somministrazioni-anticovid19-lombardia";
		try {
			consumer.consumeMessages(topicName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
