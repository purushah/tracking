package monitoring.graph;

import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DateFilter {
	String name;
	JPanel mainPanel = new JPanel();

	String date;
	String offset;

	public DateFilter(String name) {
		this.name = name;
	}

	public void showFilter() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		mainPanel.add(new JLabel("Date"));
		JTextField date = new JTextField("");
		mainPanel.add(date);
		mainPanel.add(new JLabel("OffSet"));
		JTextField offset = new JTextField("");
		mainPanel.add(offset);

		int value = JOptionPane.showConfirmDialog(null, mainPanel, null, JOptionPane.OK_CANCEL_OPTION);
		this.date = date.getText();
		this.offset = offset.getText();

	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

}
