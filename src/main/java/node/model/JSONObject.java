package node.model;

public class JSONObject {
	private SomministrationsDto jsonString;

	public SomministrationsDto getJsonString() {
		return jsonString;
	}

	public void setJsonString(SomministrationsDto jsonString) {
		this.jsonString = jsonString;
	}

	public JSONObject(SomministrationsDto jsonString) {
		super();
		this.jsonString = jsonString;
	}

	@Override
	public String toString() {
		return "JSONObject [jsonString=" + jsonString + "]";
	}

	
}
