package monitoring.graph;

import java.io.IOException;
import java.util.List;

import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;

public class GraphTherapy extends DrawGraphProgress{

	public GraphTherapy(String title) throws IOException {
		super(title);
	}
	
	public GraphTherapy(String name, List<String> graphList, String date, String offset) {
		super(name, graphList, date, offset);
	}

	@Override
	protected String getXAxis() {
		return ProgressReport.THERAPY;

	}
	
	protected List<String> getFilterList() throws IOException {
		return Util.getFileList(getName());
	}

}
