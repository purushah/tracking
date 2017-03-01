package monitoring.graph;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import monitoring.monitoring.Util;
import monitoring.pojo.ReportObject;

public abstract class Graph {
	String name;

	Map<String, ReportObject> objectMap = new HashMap<String, ReportObject>();
	int numberOfDays = -1;

	List<String> graphList = new ArrayList<String>();
	String date;
	String offset;
	JPanel mainPanel = new JPanel();
	ChartPanel chartPanel;
	Dimension dimension;

	public Graph(final String name) {
		this.name = name;
	}

	public Graph(final String name, JPanel report, Dimension dimension) {
		this(name);
		this.mainPanel = report;
		this.dimension = dimension;
	}

	public Graph(String name, List<String> graphList) {
		this(name);
		this.graphList = graphList;
	}

	public void drawGraphWithoutFrame() throws IOException, ParseException {

		final TimeSeriesCollection dataset = getDataset();
		final JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		if (dimension == null) {
			chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
		} else {
			chartPanel.setPreferredSize(dimension);
		}
		mainPanel.add(chartPanel);
	}

	public void drawGraph() throws IOException, ParseException {
		JFrame frame = new JFrame("DrawGraphpp");
		frame.add(mainPanel);
		frame.setJMenuBar(getMenuBar());

		final TimeSeriesCollection dataset = getDataset();
		final JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart, false);
		if (dimension == null) {
			chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
		} else {
			chartPanel.setPreferredSize(dimension);
		}
		mainPanel.add(chartPanel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public void reDrawGraph() throws IOException, ParseException {
		JFrame frame = new JFrame("DrawGraphpp");
		frame.add(mainPanel);
		frame.setJMenuBar(getMenuBar());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainPanel.removeAll();
		final TimeSeriesCollection dataset = getDataset();
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart, false);
		chartPanel.setPreferredSize(new java.awt.Dimension(1200, 800));
		mainPanel.add(chartPanel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	protected TimeSeriesCollection getDataset() throws IOException, ParseException {

		Map<String, List<Pair<String, String>>> progressObjectMap = getDataAsProgress();
		final TimeSeriesCollection dataset = new TimeSeriesCollection();
		for (String object : progressObjectMap.keySet()) {
			TimeSeries xySeries = new TimeSeries(object);
			for (Pair<String, String> pair : progressObjectMap.get(object)) {
				Day day = new Day(Util.getDate(pair.getFirst()));
				xySeries.add(day, Double.parseDouble(pair.getSecond()));
			}
			dataset.addSeries(xySeries);
		}
		return dataset;
	}

	private JFreeChart createChart(final XYDataset dataset) {

		final JFreeChart chart = ChartFactory.createTimeSeriesChart(name, getYAxis(), getXAxis(), dataset, true, true,
				true);

		chart.setBackgroundPaint(Color.white);
		final XYPlot plot = chart.getXYPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setDomainGridlinePaint(Color.white);
		plot.setRangeGridlinePaint(Color.white);

		final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesShapesVisible(1, true);
		plot.setRenderer(renderer);
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("dd-MM-yyyy"));

		return chart;

	}

	public JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Option");
		JMenuItem filterMenu = new JMenuItem("Filter");
		filterMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FilterConfirmBox graphFilter = new FilterConfirmBox(name, getFilterList());
					graphList.addAll(graphFilter.getFilter());
					reDrawGraph();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		JMenuItem dateMenu = new JMenuItem("Date");
		dateMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					DateFilter graphFilter = new DateFilter(name);
					graphFilter.showFilter();
					date = graphFilter.getDate();
					offset = graphFilter.getOffset();
					reDrawGraph();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		menu.add(filterMenu);
		menu.add(dateMenu);
		menuBar.add(menu);
		return menuBar;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

	protected abstract Map<String, List<Pair<String, String>>> getDataAsProgress() throws IOException, ParseException;

	protected abstract String getXAxis();

	protected abstract String getYAxis();

	protected List<String> getFilterList() throws IOException {
		return Util.getFileList(name);

	}

	protected Map<String, ReportObject> getReport() throws IOException {
		if (date != null) {
			try {
				return Util.getReport(date, Integer.parseInt(offset));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return Util.getReport(numberOfDays);
	}
	
	public String getName(){
		return name;
	}

}