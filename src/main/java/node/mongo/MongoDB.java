package node.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import node.entity.SomministrationsEntity;
import node.model.DataConverter;
import node.model.SomministrationsDto;

@Service
public class MongoDB {

	@Value("${collectionName}")
	private String collectionName;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	

	public void insertToMongoDB(SomministrationsDto sommDto) {
		SomministrationsEntity entity= new SomministrationsEntity();
		DataConverter dateConv = new DataConverter();
		entity.setCodIstat(sommDto.getCodIstat());
		entity.setMunicipality(sommDto.getMunicipality());
		entity.setProvince(sommDto.getProvince());
		entity.setAbbreviation(sommDto.getAbbreviation());
		entity.setTotDose1(sommDto.getTotDose1());
		entity.setTotDose2(sommDto.getTotDose2());
		entity.setDate(dateConv.convert(sommDto.getDate()));
		mongoTemplate.insert(entity, collectionName);
	}
}
