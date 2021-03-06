package monitoring.pojo;

import java.util.Map;
import java.util.TreeMap;

public class ReportObject{

	Map<String, BasicObject> objectMap = new TreeMap<String, BasicObject>(String.CASE_INSENSITIVE_ORDER);
	

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
