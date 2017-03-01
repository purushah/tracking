package monitoring.graph;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

import monitoring.monitoring.ObjectFactory;
import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;

public class DrawMultiGraph extends Graph {
	List<String> items;

	public DrawMultiGraph(String title) {
		super(title);
	}

	public DrawMultiGraph(String title, List<String> items) {
		this(title);
		this.items = items;

	}

	@Override
	protected Map<String, List<Pair<String, String>>> getDataAsProgress() throws IOException, ParseException {
		Map<String, List<Pair<String, String>>> progressObjectMap = new HashMap<String, List<Pair<String, String>>>();

		for (String item : items) {
			progressObjectMap.putAll(ObjectFactory.getObjectForGraph(item, graphList,  date, offset).getDataAsProgress());
		}
		return progressObjectMap;
	}

	@Override
	protected String getXAxis() {
		return ProgressReport.PROGRESS;

	}

	@Override
	protected String getYAxis() {
		return "Date";

	}

	@Override
	protected List<String> getFilterList() throws IOException {
		List<String> list = new ArrayList<String>();
		for (String item : items) {
			list.addAll(ObjectFactory.getObjectForGraph(item).getFilterList());
		}
		return list;
	}
}
