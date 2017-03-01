package monitoring.graph;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class FilterConfirmBox {
	String name;
	JPanel mainPanel = new JPanel();
	List<String> list = new ArrayList<String>();

	public FilterConfirmBox(String name, List<String> list) {
		this.name = name;
		this.list = list;
	}

	public List<String> getFilter() {
		int i = 0;
		mainPanel.setLayout(new GridLayout(18,3));
		mainPanel.setPreferredSize(new Dimension(700, 700));
		JScrollPane scrollFrame = new JScrollPane(mainPanel);
		mainPanel.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(600, 300));
		for (String mc : list) {
			JCheckBox box = new JCheckBox(mc);
			i++;
			if (i > 5) {
				mainPanel.add(box, "wrap");
				i = 0;
			} else {
				mainPanel.add(box);
			}
		}
		int value = JOptionPane.showConfirmDialog(null, mainPanel, null, JOptionPane.OK_CANCEL_OPTION);
		List<String> filter = new ArrayList<String>();

		for (Component c : mainPanel.getComponents()) {
			if (c instanceof JCheckBox) {
				JCheckBox comp = (JCheckBox) c;
				if (comp.isSelected()) {
					filter.add(comp.getText());
				}
			}
		}
		return filter;
	}

}
