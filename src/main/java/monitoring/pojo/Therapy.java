package monitoring.pojo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import monitoring.monitoring.ProgressConstant;
import monitoring.monitoring.ProgressReport;

public class Therapy extends BasicObject {

	private List<ProgressObject> progress = new ArrayList<ProgressObject>();

	public void add(Object object) {
		this.progress.add((ProgressObject) object);
	}

	public void writeObject(PrintWriter bw) {
		for (ProgressObject obj : progress) {
			bw.println(obj.getName() + ProgressConstant.seperator + obj.getProgress());
		}
	}

	@Override
	public void readObject(String progress) {
		if (progress.split(ProgressConstant.seperator).length > 1) {
			add(new ProgressObject(progress.split(ProgressConstant.seperator)[0],
					progress.split(ProgressConstant.seperator)[1]));
		} else {
			add(new ProgressObject(progress.split(ProgressConstant.seperator)[0], "1"));

		}

	}

	@Override
	public String getName() {
		return ProgressReport.THERAPY;
	}

	@Override
	public boolean contains(String object) {
		for (ProgressObject obj : progress) {
			if (obj.getName().equals(object)) {
				return true;
			}
		}
		return false;
	}

	public ProgressObject get(String object) {
		for (ProgressObject obj : progress) {
			if (obj.getName().equals(object)) {
				return obj;
			}
		}
		return null;
	}

	public List<ProgressObject> getList() {
		return progress;
	}

	@Override
	public Set<String> getAllItemName() {
		Set<String> current = new HashSet<String>();

		for (ProgressObject dose : progress) {
			current.add(dose.getName());

		}
		return current;
	}

}
