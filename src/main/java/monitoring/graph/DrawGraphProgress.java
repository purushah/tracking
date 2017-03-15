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

public class DrawGraphProgress extends Graph {
	public DrawGraphProgress(String title)  {
		super(title);
	}

	public DrawGraphProgress(String string, JPanel graphPanel, Dimension dimension) {
		super(string, graphPanel, dimension);
	}

	public DrawGraphProgress(String name, List<String> graphList, String date, String offset) {
		super(name, graphList);
		this.date= date;
		this.offset = offset;
	}

	protected Map<String, List<Pair<String, String>>> getDataAsProgress() throws IOException, ParseException {

		Map<String, ReportObject> report = getReport();
		List<String> list = getFilterList();

		Map<String, List<Pair<String, String>>> progressObjectMap = new HashMap<String, List<Pair<String, String>>>();

		for (String pp : list) {
			if (!graphList.isEmpty()) {
				if (!graphList.contains(pp)) {
					continue;
				}
			}
			for (String date : report.keySet()) {
				System.out.println(pp + date);
				ProgressObject value = ((Progress) report.get(date).getObject(getXAxis())).get(pp);
				if (value == null) {
					continue;
				}
				if (!progressObjectMap.containsKey(value.getName())) {
					progressObjectMap.put(value.getName(), new ArrayList<Pair<String, String>>());
				}
				progressObjectMap.get(value.getName())
						.add(new Pair<String, String>(date, String.valueOf(getValue(value))));

			}
		}
		return progressObjectMap;
	}

	protected Double getValue(ProgressObject p) {

		if (p != null && p.getProgress() != null) {
			return Double.parseDouble(p.getProgress());
		} else {
			return 0.0;
		}
	}

	public static void main(final String[] args) throws IOException, ParseException {
		final Graph demo = new DrawGraphProgress("Progress");
		demo.drawGraph();
	}

	@Override
	protected String getXAxis() {
		return ProgressReport.PROGRESS;

	}

	@Override
	protected String getYAxis() {
		return "Date";

	}

	private List<String> getProgressList() throws IOException {
		List<String> vegitables = Util.getFileList(ProgressReport.PROGRESS);
		List<String> newResult = new ArrayList<String>();
		for (String vegi : vegitables) {
			String name = vegi;
			if (vegi.contains(ProgressConstant.seperator)) {
				name = vegi.split(ProgressConstant.seperator)[0];
				newResult.add(name);
			} else {
				newResult.add(vegi);
			}

		}
		return newResult;
	}
	protected List<String> getFilterList() throws IOException {
		return getProgressList();
	}
}
