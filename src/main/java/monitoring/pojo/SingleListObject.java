package monitoring.pojo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import monitoring.monitoring.Util;
import monitoring.pojo.BasicObject;

public abstract class SingleListObject extends BasicObject {
	protected List<String> objects = new ArrayList<String>();

	public void addFruits(String object) {
		objects.add(object);
	}

	@Override
	public void add(Object t) {
		objects.add(t.toString());
	}

	@Override
	public void writeObject(PrintWriter bw) {
		Util.writeObject(bw, objects);

	}

	@Override
	public void readObject(String bw) {
		objects.add(bw);
	}
	public boolean contains(String object){
		return objects.contains(object);
	}
	
	public List<String> getAll(){
		return objects;
	}
	
	@Override
	public Set<String> getAllItemName() {
		Set<String> current = new HashSet<String>(getAll());
		return current;
	}


}