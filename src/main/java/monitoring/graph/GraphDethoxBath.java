package monitoring.graph;

import java.io.IOException;
import java.util.List;

import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;

public class GraphDethoxBath extends GraphVegitable {

	public GraphDethoxBath(String title) throws IOException {
		super(title);
	}

	public GraphDethoxBath(String name, List<String> graphList, String date, String offset) {
		super(name, graphList, date, offset);
	}

	protected String getXAxis() {
		return ProgressReport.DETOX_BATH;

	}

	@Override
	protected String getYAxis() {
		return "Date";

	}

	protected List<String> getFileList() throws IOException {
		return Util.getFileList(ProgressReport.DETOX_BATH);
	}

}
