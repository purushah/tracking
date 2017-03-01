package monitoring.pojo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import monitoring.monitoring.ProgressConstant;
import monitoring.monitoring.ProgressReport;

public class Supplements extends BasicObject {

	private List<DosageObject> progress = new ArrayList<DosageObject>();

	public void add(Object obj) {
		this.progress.add((DosageObject) obj);
	}

	@Override
	public String getName() {
		return ProgressReport.SUPPLEMENTS;
	}

	@Override
	public void writeObject(PrintWriter bw) throws Exception {
		validate();
		for (DosageObject dose : progress) {
			bw.println(dose.getName() + ProgressConstant.seperator + dose.getTimes() + ProgressConstant.seperator
					+ dose.getMl());
		}
	}

	@Override
	public void readObject(String bw) {
		System.out.println(bw);
		if (!StringUtils.isEmpty(bw)) {
			if (bw.split(ProgressConstant.seperator).length > 1) {
				add(new DosageObject(bw.split(ProgressConstant.seperator)[0], bw.split(ProgressConstant.seperator)[1],
						bw.split(ProgressConstant.seperator)[2]));
			} else {
				add(new DosageObject(bw.split(ProgressConstant.seperator)[0], "", ""));

			}
		}

	}

	@Override
	public boolean contains(String object) {
		for (DosageObject dose : progress) {
			if (dose.getName().equals(object)) {
				return true;
			}
		}
		return false;
	}

	public DosageObject get(String object) {
		for (DosageObject dose : progress) {
			if (dose.getName().equals(object)) {
				return dose;
			}
		}
		return null;
	}

	@Override
	public Set<String> getAllItemName() {
		Set<String> current = new HashSet<String>();

		for (DosageObject dose : progress) {
			current.add(dose.getName());

		}
		return current;
	}

	public void validate() throws Exception {
		for (DosageObject dose : progress) {
			if (StringUtils.isEmpty(dose.getMl()) || StringUtils.isEmpty(dose.getTimes())) {
				throw new Exception(getName() + " :  Invalid date " + dose.getName());
			}
		}
	}
}
