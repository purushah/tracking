package monitoring.pojo;

public class ProgressObject {

	private String name;
	private String progress;

	public ProgressObject(String name, String progress) {
		this.name = name;
		this.progress = progress;
	}

	public ProgressObject(String text, Object value) {
		this(text, value.toString());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}

	public Double getValue(ProgressObject p) {

		if (p != null && p.getProgress() != null) {
			return Double.parseDouble(p.getProgress());
		} else {
			return 0.0;
		}
	}
	@Override
	public String toString() {
		return name + " : " + progress;
	}

}
