package monitoring.graph;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;
import monitoring.pojo.BasicObject;
import monitoring.pojo.DosageObject;
import monitoring.pojo.ReportObject;
import monitoring.pojo.Supplements;
import monitoring.pojo.Vegitables;

public class GraphVegitable extends Graph {

	public GraphVegitable(String title) throws IOException {
		super(title);
	}

	public GraphVegitable(String name, List<String> graphList, String date, String offset) {
		super(name, graphList);
		this.date = date;
		this.offset = offset;

	}

	protected Map<String, List<Pair<String, String>>> getDataAsProgress() throws IOException, ParseException {

		Map<String, ReportObject> report = getReport();
		List<String> list = getFileList();

		Map<String, List<Pair<String, String>>> progressObjectMap = new HashMap<String, List<Pair<String, String>>>();

		for (String pp : list) {
			if (!graphList.isEmpty()) {
				if (!graphList.contains(pp)) {
					continue;
				}
			}
			double i =1;
			for (String date : report.keySet()) {
				boolean value = ((BasicObject) report.get(date).getObject(getName())).contains(pp);
				System.out.println(pp + date);
				if (!value) {
					continue;
				}
				if (!progressObjectMap.containsKey(pp)) {
					progressObjectMap.put(pp, new ArrayList<Pair<String, String>>());
				}
				progressObjectMap.get(pp).add(new Pair<String, String>(date, String.valueOf(i)));
				//i+=.1;

			}
		}
		return progressObjectMap;
	}

	public static void main(final String[] args) throws IOException, ParseException {
		final Graph demo = new GraphVegitable(ProgressReport.VEGI);
		demo.drawGraph();
	}

	@Override
	protected String getXAxis() {
		return ProgressReport.VEGI;

	}

	@Override
	protected String getYAxis() {
		return "Date";

	}

	protected List<String> getFileList() throws IOException {
		return Util.getFileList(ProgressReport.VEGI);
	}

}
