package monitoring.monitoring;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import monitoring.pojo.BasicObject;
import monitoring.pojo.ReportObject;
import net.miginfocom.swing.MigLayout;

public class DisplayNotes extends JPanel {

	public static void main(String[] args) throws IOException {
		createAndShowGUI();

	}

	public void dispalyNotes() throws IOException {
		Map<String, ReportObject> report = new TreeMap<String, ReportObject>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				try {
					return Util.getDate(o2).compareTo(Util.getDate(o1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return -1;
			}
		});
		
		report.putAll(Util.getReport(-1));

		for (String day : report.keySet()) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());

			SwingLink link = new SwingLink(day, day);
			p.add(link, "wrap");
			BasicObject reportObj = report.get(day).getObject(ProgressReport.NOTE);
			for (String item : reportObj.getAllItemName()) {
				p.add(new JLabel(item), "span, grow");

			}
			add(p);
		}
	}

	public static void createAndShowGUI() throws IOException {
		// Create and set up the window.

		JFrame frame = new JFrame("Neo's Progress Note");
		DisplayNotes report = new DisplayNotes();
		report.dispalyNotes();
		// Add content to the window.

		report.setLayout(new BoxLayout(report, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(report);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		frame.add(scrollPane, BorderLayout.CENTER);

		// Display the window.
		frame.pack();

		frame.setVisible(true);
		frame.setSize(new Dimension(1200, 800));

	}
}
