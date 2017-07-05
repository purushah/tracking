package monitoring.calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

public class Planner extends JPanel {

	String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December" };

	CalendarModel model = new CalendarModel();
	java.util.Date date = new Date();
	Calendar cal = Calendar.getInstance();

	JTable table = new JTable(model);
	JLabel label = new JLabel();
	JFrame frame;

	public Planner() {
		super();

	}

	public void displayPlanner() {

		frame = new JFrame("Planner - " + getTime());
		Planner planner = new Planner();
		cal.setTime(date);

		model.setMonth((Calendar)cal.clone());
		frame.add(label);
		frame.add(planner);
		setupToolBar(frame, planner);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(true);
		table.setGridColor(Color.BLUE);
		table.setRowSelectionAllowed(false);
		model.addTableModelListener(new SaveCalander(model));
		planner.add(table);

		table.setDefaultRenderer(Object.class, new TextAreaRenderer());
		table.setDefaultEditor(Object.class, new TextAreaEditor());

		table.setRowHeight(155);
		JScrollPane scroll = new JScrollPane(table);
		frame.add(scroll);

		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.yellow);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();

		frame.setVisible(true);
		frame.setSize(new Dimension(1300, 850));
	}

	public static void main(String[] args) {
		Planner app = new Planner();
		app.displayPlanner();
	}

	public class ComboHandler implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			model.setMonth((Calendar)cal.clone());
			table.repaint();
		}
	}

	public class ListHandler implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			model.setMonth((Calendar)cal.clone());
			table.repaint();
		}
	}

	public void setupToolBar(JFrame frame, JPanel panel) {
		JToolBar toolBar = new javax.swing.JToolBar();
		JButton previoud = new javax.swing.JButton("<<");
		previoud.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cal.add(Calendar.MONTH, -1);
				readloadTable();
			}
		});
		JButton next = new javax.swing.JButton(">>");
		next.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				cal.add(Calendar.MONTH, 1);
				readloadTable();
			}
		});

		toolBar.add(previoud);
		toolBar.addSeparator();
		toolBar.add(next);
		toolBar.addSeparator();

		frame.add(toolBar, BorderLayout.PAGE_START);
	}

	public String getTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy");
		return dateFormat.format(cal.getTime());
	}

	public void readloadTable() {
		frame.setTitle("Planner - " + getTime());
		model.setMonth((Calendar)cal.clone());
		model.fireTableDataChanged();
	}
}
