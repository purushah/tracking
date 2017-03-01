package monitoring.graph;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;
import monitoring.pojo.DosageObject;
import monitoring.pojo.ReportObject;
import monitoring.pojo.Supplements;

public class DrawGraphSupplements extends Graph {

	public DrawGraphSupplements(String title) {
		super(title);
	}

	public DrawGraphSupplements(String name, List<String> graphList, String date, String offset) {
		super(name, graphList);
		this.date = date;
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
				DosageObject value = ((Supplements) report.get(date).getObject(getName())).get(pp);
				System.out.println(pp + date);
				if (value == null) {
					value = new DosageObject(pp, "1", "0");
				}
				if (!progressObjectMap.containsKey(value.getName())) {
					progressObjectMap.put(value.getName(), new ArrayList<Pair<String, String>>());
				}
				progressObjectMap.get(value.getName())
						.add(new Pair<String, String>(date, String.valueOf(value.getQuantityinDay())));

			}
		}
		return progressObjectMap;
	}

	public static void main(final String[] args) throws IOException, ParseException {
		final Graph demo = new DrawGraphSupplements(ProgressReport.SUPPLEMENTS);
		demo.drawGraph();
	}

	@Override
	protected String getXAxis() {
		return ProgressReport.SUPPLEMENTS;

	}

	@Override
	protected String getYAxis() {
		return "Date";

	}

}
