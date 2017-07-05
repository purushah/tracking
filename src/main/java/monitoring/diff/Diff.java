package monitoring.diff;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;
import monitoring.pojo.BasicObject;
import monitoring.pojo.ReportObject;

public class Diff extends JPanel {
	private String oldFilename;
	private String newFilename;
	private ColorPane leftTextArea;
	private ColorPane rightTextArea;

	public static Color ADDED_COLOR = Color.yellow;
	public static Color DELETED_COLOR = Color.red;
	public static Color MODIFIED_COLOR = Color.green;
	public static Color DEFULT = Color.BLACK;

	private void createInterface() {
		setLayout(new BorderLayout());
		JPanel localJPanel1 = new JPanel();
		add(localJPanel1);
		localJPanel1.setLayout(new GridLayout(0, 2));
		JPanel localJPanel2 = new JPanel();
		JPanel localJPanel3 = new JPanel();
		localJPanel2.setLayout(new BorderLayout());
		localJPanel3.setLayout(new BorderLayout());
		localJPanel1.add(localJPanel2);
		localJPanel1.add(localJPanel3);
		this.leftTextArea = new ColorPane();
		this.rightTextArea = new ColorPane();
		JScrollPane localJScrollPane1 = new JScrollPane(this.leftTextArea);
		JScrollPane localJScrollPane2 = new JScrollPane(this.rightTextArea);

		localJPanel2.add(localJScrollPane1, "Center");
		localJPanel3.add(localJScrollPane2, "Center");
		localJScrollPane1.setVerticalScrollBarPolicy(22);
		localJScrollPane1.setHorizontalScrollBarPolicy(32);
		localJScrollPane2.setVerticalScrollBarPolicy(22);
		localJScrollPane2.setHorizontalScrollBarPolicy(32);

		localJScrollPane1.getHorizontalScrollBar().setModel(localJScrollPane2.getHorizontalScrollBar().getModel());
		localJScrollPane1.getVerticalScrollBar().setModel(localJScrollPane2.getVerticalScrollBar().getModel());

	}

	public static void main(String[] args) throws IOException {
		JFrame frame = new JFrame("Diff");
		Diff diffPanel = new Diff();
		diffPanel.createInterface();
		frame.add(diffPanel);
		frame.pack();
		frame.setSize(1400, 800);
		frame.show();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		diffPanel.printDiff();
	}

	public void printDiff() throws IOException {
		Map<String, ReportObject> report = Util.getReport(-1);

		List<String> dates = new ArrayList<String>();
		dates.addAll(report.keySet());

		Collections.sort(dates, new Comparator<String>() {
			public int compare(String o1, String o2) {
				try {
					return Util.getDate(o2).compareTo(Util.getDate(o1));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				return 0;
			}
		});

		for (int i = 0; i < dates.size() - 1;) {
			String date1 = dates.get(i);
			String date2 = dates.get(++i);
			addText(leftTextArea, date1, DEFULT);
			addText(rightTextArea, date2, DEFULT);

			ReportObject report1 = report.get(date1);
			ReportObject report2 = report.get(date2);
			compareVegitable(report1, report2);
			compareSupplement(report1, report2);

		}
	}

	private void compareVegitable(ReportObject report1, ReportObject report2) {
		addText(leftTextArea, "", DEFULT);
		addText(rightTextArea, "", DEFULT);

		addText(leftTextArea, ProgressReport.VEGI, DEFULT);
		addText(rightTextArea, ProgressReport.VEGI, DEFULT);
		BasicObject veg1 = report1.getObject(ProgressReport.VEGI);
		BasicObject veg2 = report2.getObject(ProgressReport.VEGI);
		if (veg1 != null) {
			for (String name : veg1.getAllItemName()) {
				if (veg2 != null) {
					if (!veg2.getAllItemName().contains(name)) {
						addText(leftTextArea, name, DELETED_COLOR);
						addText(rightTextArea, "", DELETED_COLOR);
					}
				}
			}
		}

		if (veg2 != null) {
			for (String name : veg2.getAllItemName()) {
				if (veg1 != null) {
					if (veg1.getAllItemName() != null) {

						if (!veg1.getAllItemName().contains(name)) {
							addText(rightTextArea, name, DELETED_COLOR);
							addText(leftTextArea, "", DELETED_COLOR);
						}
					}
				}
			}
		}
	}

	private void compareSupplement(ReportObject report1, ReportObject report2) {
		
		addText(leftTextArea, "", DEFULT);
		addText(rightTextArea, "", DEFULT);

		addText(leftTextArea, ProgressReport.SUPPLEMENTS, DEFULT);
		addText(rightTextArea, ProgressReport.SUPPLEMENTS, DEFULT);
		BasicObject veg1 = report1.getObject(ProgressReport.SUPPLEMENTS);
		BasicObject veg2 = report2.getObject(ProgressReport.SUPPLEMENTS);
		for (String name : veg1.getAllItemName()) {
			if (!veg2.getAllItemName().contains(name)) {
				addText(leftTextArea, name, DELETED_COLOR);
				addText(rightTextArea, "", DELETED_COLOR);
			}
		}

		for (String name : veg2.getAllItemName()) {
			if (!veg1.getAllItemName().contains(name)) {
				addText(rightTextArea, name, DELETED_COLOR);
				addText(leftTextArea, "", DELETED_COLOR);
			}
		}
	}

	public void addText(ColorPane textPane, String text, Color color) {
		textPane.append(color, text + "\n");
	}

}
