package monitoring.pojo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import monitoring.monitoring.ProgressReport;

public class DetoxBath extends SingleListObject {
	private String name = ProgressReport.DETOX_BATH;

	@Override
	public String getName() {
		return name;
	}

}
