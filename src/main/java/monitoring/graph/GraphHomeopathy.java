package monitoring.graph;

import java.awt.Dimension;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JPanel;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import monitoring.monitoring.ProgressConstant;
import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;
import monitoring.pojo.DosageObject;
import monitoring.pojo.Progress;
import monitoring.pojo.ProgressObject;
import monitoring.pojo.ReportObject;
import monitoring.pojo.Supplements;

public class GraphHomeopathy extends DrawGraphSupplements {
	
	public GraphHomeopathy(String name) {
		super(name);
	}
	
	public GraphHomeopathy(String name, List<String> graphList, String date, String offset) {
		super(name, graphList, date, offset);
	}
	
	

	
	public static void main(final String[] args) throws IOException, ParseException {
		final Graph demo = new DrawGraphProgress(ProgressReport.HOMOEPATHY);
		demo.drawGraph();
	}

	@Override
	protected String getXAxis() {
		return ProgressReport.HOMOEPATHY;

	}

	@Override
	protected String getYAxis() {
		return "Date";

	}

	
	protected List<String> getFilterList() throws IOException {
		return Util.getFileList(ProgressReport.HOMOEPATHY);
	}
}
