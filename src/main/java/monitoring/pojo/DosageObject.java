package monitoring.pojo;

import org.apache.commons.lang3.StringUtils;

public class DosageObject {

	private String name;
	private String ml;
	private String times;

	public DosageObject(String name, String times, String ml) {
		this.name = name;
		this.ml = ml;
		this.times = times;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMl() {
		return ml;
	}

	public void setMl(String ml) {
		this.ml = ml;
	}

	public String getTimes() {
		return times;
	}

	public void setTimes(String times) {
		this.times = times;
	}

	public double getQuantityinDay() {
		double value = 1;
		if (!StringUtils.isEmpty(getMl())) {
			value *= Double.parseDouble(getMl());
		}
		if (!StringUtils.isEmpty(getTimes())) {
			value *= Double.parseDouble(getTimes());
		}

		return value;
	}

}
