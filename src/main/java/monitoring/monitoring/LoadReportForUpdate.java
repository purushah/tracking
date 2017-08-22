package monitoring.monitoring;

import java.awt.Component;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import org.apache.commons.lang3.StringUtils;

import monitoring.pojo.IssuesObject;
import monitoring.pojo.SpecialEvent;

public class LoadReportForUpdate extends LoadReport {

	public LoadReportForUpdate(JPanel report, String date) {
		super(report, date);
	}

	protected void loadSpecialEvent(JPanel panel) {
		SpecialEvent specialEvent = (SpecialEvent) objectMap.get(ProgressReport.SPECAIL_EVENT);
		for (Component c : panel.getComponents()) {
			if (!StringUtils.isEmpty(c.getName())) {
				if (c.getName().equals(ProgressReport.SPECAIL_EVENT)) {
					SortedListModel model = (SortedListModel) ((JList) ((JViewport) ((JScrollPane) c).getComponent(0))
							.getComponent(0)).getModel();

					model.addAll(specialEvent.getAll().toArray(new String[0]));
				}
			}
			// other stuff

		}
	}
	
	protected void loadIssues(JPanel panel) {
		IssuesObject specialEvent = (IssuesObject) objectMap.get(ProgressReport.ISSUES);
		for (Component c : panel.getComponents()) {
			if (!StringUtils.isEmpty(c.getName())) {
				if (c.getName().equals(ProgressReport.ISSUES)) {
					SortedListModel model = (SortedListModel) ((JList) ((JViewport) ((JScrollPane) c).getComponent(0))
							.getComponent(0)).getModel();

					model.addAll(specialEvent.getAll().toArray(new String[0]));
				}
			}
			// other stuff
		}
	}


	protected void showLoadMessage() {
		JOptionPane.showMessageDialog(report, "Data loaded from " + date);

	}

}
