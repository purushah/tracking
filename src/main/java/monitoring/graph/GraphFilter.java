package monitoring.graph;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import monitoring.monitoring.Util;

public class GraphFilter {
	String name;
	JPanel mainPanel = new JPanel();

	public GraphFilter(String name) {
		this.name = name;
	}

	public List<String> getFilter() {
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		List<String> list = null;
		try {
			list = Util.getFileList(name);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		for (String mc : list) {
			JCheckBox box = new JCheckBox(mc);
			mainPanel.add(box);
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
