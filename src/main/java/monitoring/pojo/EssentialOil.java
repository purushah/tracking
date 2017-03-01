package monitoring.pojo;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import monitoring.monitoring.ProgressReport;

public class EssentialOil extends SingleListObject {
	private String name = ProgressReport.ESSENTIAL_OIL;

	@Override
	public String getName() {
		return name;
	}

}
