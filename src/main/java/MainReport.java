import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import monitoring.graph.DrawGraphProgress;
import monitoring.graph.DrawMultiGraph;
import monitoring.graph.FilterConfirmBox;
import monitoring.graph.Graph;
import monitoring.monitoring.ProgressReport;
import monitoring.monitoring.Util;

public class MainReport {
	public MainReport(String title) throws IOException {
	}

	public static void main(String[] args) throws IOException, ParseException {

		JPanel graphPanel = new JPanel();
		JPanel detailsPanel = new JPanel();

		final Graph demo = new DrawGraphProgress("Last 5 days Progress", graphPanel, new Dimension(550, 750));
		demo.setNumberOfDays(5);
		JFrame frame = new JFrame("Neo Progress");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(1, 2));
		frame.add(graphPanel);
		frame.add(detailsPanel);
		detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
		// frame.add(main, BorderLayout.PAGE_START);
		// setupToolBar(frame);

		addSupplementDetails(detailsPanel);
		detailsPanel.add(new JLabel("   "));

		addNote(detailsPanel);

		frame.pack();
		frame.setVisible(true);
		frame.setJMenuBar(getMenuBar());

		demo.drawGraphWithoutFrame();
		frame.setSize(new Dimension(1200, 900));
		// frame.getContentPane().add(detailsPanel);
	}

	private static void addSupplementDetails(JPanel detailsPanel) throws IOException {
		JLabel name = new JLabel("Current Supplements");
		name.setFont(new Font("Serif", Font.BOLD, 22));

		detailsPanel.add(name);
		Set<String> supplements = Util.getCurrentReport(ProgressReport.SUPPLEMENTS);
		for (String supplement : supplements) {
			detailsPanel.add(new JLabel(supplement));
		}

	}

	private static void addNote(JPanel detailsPanel) throws IOException {
		JLabel name = new JLabel("Note");
		name.setFont(new Font("Serif", Font.BOLD, 22));

		detailsPanel.add(name);
		Set<String> supplements = Util.getCurrentReport(ProgressReport.NOTE);
		for (String supplement : supplements) {
			detailsPanel.add(new JLabel(supplement));
		}
	}

	public static JMenuBar getMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("Option");

		JMenuItem settingMenu = new JMenuItem("Graph");
		settingMenu.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				FilterConfirmBox graphFilter = new FilterConfirmBox("Select ", getGraphList());
				List<String> graphList = graphFilter.getFilter();
				String name = graphList.toString();
				try {
					new DrawMultiGraph(name, graphList).drawGraph();
				} catch (IOException e1) {
					e1.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		menu.add(settingMenu);

		JMenuItem reportMenu = new JMenuItem("Report");
		reportMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ProgressReport.createAndShowGUI();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		menu.add(reportMenu);
		menuBar.add(menu);
		return menuBar;
	}

	public static void setupToolBar(JFrame frame) {
		JToolBar toolBar = new javax.swing.JToolBar();
		JButton save = new javax.swing.JButton("Save");
		toolBar.add(save);
		frame.add(toolBar, BorderLayout.PAGE_END);
	}

	public static List<String> getGraphList() {
		List<String> current = new ArrayList<String>();
		current.add(ProgressReport.SUPPLEMENTS);
		current.add(ProgressReport.PROGRESS);
		current.add(ProgressReport.VEGI);
		current.add(ProgressReport.THERAPY);
		current.add(ProgressReport.DETOX_BATH);
		current.add(ProgressReport.HOMOEPATHY);
		current.add(ProgressReport.BEHAVIOUR);
		return current;

	}
}
