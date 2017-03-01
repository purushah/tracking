package monitoring.pojo;

import java.util.HashMap;
import java.util.Map;

public class ReportObject{

	Map<String, BasicObject> objectMap = new HashMap<String, BasicObject>();

	public ReportObject() {
	}

	public BasicObject getObject(String object) {
		return objectMap.get(object);
	}

	public boolean containsKey(String key) {
		return objectMap.containsKey(key);
	}

	public void putObject(String name, BasicObject object) {
		objectMap.put(name, object);
	}

	
}
