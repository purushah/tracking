package monitoring.monitoring;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.ListModel;

import org.apache.commons.lang3.StringUtils;

import monitoring.pojo.BasicObject;
import monitoring.pojo.Behaviour;
import monitoring.pojo.DetoxBath;
import monitoring.pojo.DosageObject;
import monitoring.pojo.EssentialOil;
import monitoring.pojo.FrementedFood;
import monitoring.pojo.Fruits;
import monitoring.pojo.IssuesObject;
import monitoring.pojo.Juicing;
import monitoring.pojo.Note;
import monitoring.pojo.Progress;
import monitoring.pojo.ProgressObject;
import monitoring.pojo.SpecialEvent;
import monitoring.pojo.Supplements;
import monitoring.pojo.Therapy;
import monitoring.pojo.Vegitables;

public class SaveReport implements ActionListener {
	JPanel report;
	PrintWriter writer = null;
	String date;

	public SaveReport(JPanel report) {
		this.report = report;
	}

	public SaveReport(JPanel panel, String dayDate) {
		this.report = panel;
		this.date = dayDate;
	}

	public void actionPerformed(ActionEvent e) {
		if (date == null) {
			date = getDate();
		}
		try {

			if(checkFileExist()){
				return;
			}

			writer = new PrintWriter(new FileWriter(Util.getPath() + "/" + date + ".txt"));

			JTabbedPane tabbedPane = (JTabbedPane) (report.getComponent(0));
			int totalTabs = tabbedPane.getTabCount();
			for (int i = 0; i < totalTabs; i++) {
				Component c = tabbedPane.getComponent(i);
				if (!(c instanceof JPanel)) {
					continue;
				}
				
				if (c.getName().equals(ProgressReport.VEGI)) {
					saveVegitables((JPanel) c);
				}
				if (c.getName().equals(ProgressReport.SUPPLEMENTS)) {
					saveSupplements((JPanel) c);
				}
				if (c.getName().equals(ProgressReport.PROGRESS)) {
					saveProgress((JPanel) c);
				}

				if (c.getName().equals(ProgressReport.DETOX_BATH)) {
					saveDetoxBath((JPanel) c);
				}
				if (c.getName().equals(ProgressReport.ESSENTIAL_OIL)) {
					saveEssentialOil((JPanel) c);
				}

				// if (c.getName().equals(ProgressReport.FERMENTATION)) {
				// saveFermentation((JPanel) c);
				// }

				if (c.getName().equals(ProgressReport.FRUITS)) {
					saveFruits((JPanel) c);
				}

				if (c.getName().equals(ProgressReport.HOMOEPATHY)) {
					saveHomeopathy((JPanel) c);
				}

				if (c.getName().equals(ProgressReport.JUICING)) {
					saveJucing((JPanel) c);
				}

				if (c.getName().equals(ProgressReport.SPECAIL_EVENT)) {
					saveSpecialEvent((JPanel) c);
				}
				
				if (c.getName().equals(ProgressReport.ISSUES)) {
					saveIssues((JPanel) c);
				}

				if (c.getName().equals(ProgressReport.THERAPY)) {
					saveTherapy((JPanel) c);
				}
				if (c.getName().equals(ProgressReport.NOTE)) {
					saveNote((JPanel) c);
				}
				if (c.getName().equals(ProgressReport.BEHAVIOUR)) {
					saveBehaviour((JPanel) c);
				}

				// other stuff
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "Invalid Data", JOptionPane.ERROR_MESSAGE);

		}
		writer.close();
	}

	private void saveNote(JPanel panel) throws Exception {
		Note event = new Note();
		for (Component c : panel.getComponents()) {
			if (!StringUtils.isEmpty(c.getName())) {
				if (c.getName().equals(ProgressReport.NOTE)) {
					ListModel model = ((JList) ((JViewport) ((JScrollPane) c).getComponent(0)).getComponent(0))
							.getModel();

					for (int i = 0; i < model.getSize(); i++) {
						event.add(model.getElementAt(i).toString());
					}
				}
			}
		}
		event.write(writer);
	}

	private void saveTherapy(JPanel panel) throws Exception {
		Therapy progressObject = new Therapy();
		for (Component c : panel.getComponents()) {
			JPanel progress = (JPanel) c;
			Component[] pro = progress.getComponents();
			if (((JCheckBox) pro[0]).isSelected()) {
				progressObject.add(new ProgressObject(((JCheckBox) pro[0]).getText(), "1"));
			}
		}

		progressObject.write(writer);
	}

	private void saveBehaviour(JPanel panel) throws Exception {
		Behaviour progressObject = new Behaviour();
		for (Component c : panel.getComponents()) {
			JPanel progress = (JPanel) c;
			Component[] pro = progress.getComponents();
			String name = ((JLabel) pro[0]).getText();
			if (pro[1] instanceof JSpinner) {
				progressObject.add(new ProgressObject(name, ((JSpinner) pro[1]).getValue()));
			} else {
				progressObject.add(
						new ProgressObject(name, Util.getOptionMapForValue(name, ProgressReport.BEHAVIOUR).get(((JComboBox) pro[1]).getSelectedItem())));
			}
		}
		progressObject.write(writer);
	}

	@SuppressWarnings("rawtypes")
	private void saveSpecialEvent(JPanel panel) throws Exception {
		SpecialEvent event = new SpecialEvent();
		List<String> newList = new ArrayList<String>();
		for (Component c : panel.getComponents()) {
			if (!StringUtils.isEmpty(c.getName())) {
				if (c.getName().equals(ProgressReport.SPECAIL_EVENT)) {
					ListModel model = ((JList) ((JViewport) ((JScrollPane) c).getComponent(0)).getComponent(0))
							.getModel();

					for (int i = 0; i < model.getSize(); i++) {
						event.add(model.getElementAt(i).toString());
					}
				}
				if (c.getName().equals("SourceList")) {
					ListModel model = ((JList) ((JViewport) ((JScrollPane) c).getComponent(0)).getComponent(0))
							.getModel();

					for (int i = 0; i < model.getSize(); i++) {
						newList.add(model.getElementAt(i).toString());
					}
				}

			}
		}
		Util.writeFileList(ProgressReport.SPECAIL_EVENT, newList);
		event.write(writer);

	}
	
	
	private void saveIssues(JPanel panel) throws Exception {
		IssuesObject event = new IssuesObject();
		List<String> newList = new ArrayList<String>();
		for (Component c : panel.getComponents()) {
			if (!StringUtils.isEmpty(c.getName())) {
				if (c.getName().equals(ProgressReport.ISSUES)) {
					ListModel model = ((JList) ((JViewport) ((JScrollPane) c).getComponent(0)).getComponent(0))
							.getModel();

					for (int i = 0; i < model.getSize(); i++) {
						event.add(model.getElementAt(i).toString());
						newList.add(model.getElementAt(i).toString());
					}
				}
			}
		}
		if (!newList.isEmpty()) {
			Util.writeFileList(ProgressReport.ISSUES, newList);
		}
		event.write(writer);

	}

	private void saveJucing(JPanel c) throws Exception {
		saveObject(c, new Juicing());

	}

	private void saveHomeopathy(JPanel c) throws Exception {
		saveSupplements(c, new Homeopathy());

	}

	private void saveFruits(JPanel c) throws Exception {
		saveObject(c, new Fruits());

	}

	private void saveFermentation(JPanel c) throws Exception {
		saveObject(c, new FrementedFood());

	}

	private void saveEssentialOil(JPanel c) throws Exception {
		saveObject(c, new EssentialOil());

	}

	public void saveVegitables(JPanel panel) throws Exception {
		saveObject(panel, new Vegitables());

	}

	public void saveDetoxBath(JPanel panel) throws Exception {
		saveObject(panel, new DetoxBath());

	}

	public void saveObject(JPanel panel, BasicObject object) throws Exception {
		for (Component c : panel.getComponents()) {
			JCheckBox comp = (JCheckBox) c;
			if (comp.isSelected()) {
				object.add(comp.getText());
			}
		}
		object.write(writer);
	}

	public void saveSupplements(JPanel supplements) throws Exception {
		saveSupplements(supplements, new Supplements());

	}

	public void saveSupplements(JPanel supplements, Supplements supplementsObject) throws Exception {
		for (Component c : supplements.getComponents()) {
			JPanel supplement = (JPanel) c;
			Component[] sup = supplement.getComponents();

			if (((JCheckBox) sup[0]).isSelected()) {
				supplementsObject.add(new DosageObject(((JCheckBox) sup[0]).getText(), ((JTextField) sup[1]).getText(),
						((JTextField) sup[3]).getText()));
			}
			// other stuff
		}
		supplementsObject.write(writer);
	}

	// public void saveProgress(JPanel supplements) {
	// Supplements supplementsObject = new Supplements();
	// for (Component c : supplements.getComponents()) {
	// JPanel supplement = (JPanel) c;
	// Component[] sup = supplement.getComponents();
	//
	// if (((JCheckBox) sup[0]).isSelected()) {
	// supplementsObject.addSupplements(new DosageObject(((JCheckBox)
	// sup[0]).getText(),
	// ((JTextField) sup[1]).getText(), ((JTextField) sup[3]).getText()));
	// }
	// // other stuff
	// }
	// supplementsObject.write(writer);
	// }

	public void saveProgress(JPanel panel) throws Exception {
		Progress progressObject = new Progress();
		for (Component c : panel.getComponents()) {
			JPanel progress = (JPanel) c;
			Component[] pro = progress.getComponents();
			String name = ((JLabel) pro[0]).getText();
			if (pro[1] instanceof JSpinner) {
				progressObject.add(new ProgressObject(name, ((JSpinner) pro[1]).getValue()));
			} else {
				progressObject.add(
						new ProgressObject(name, Util.getOptionMapForValue(name, ProgressReport.PROGRESS).get(((JComboBox) pro[1]).getSelectedItem())));
			}
		}

		progressObject.write(writer);
	}

	private String getDate() {
		return new SimpleDateFormat("MM-dd-yyyy").format(new Date());

	}
	
	protected boolean checkFileExist(){
		if (new File(Util.getPath() + "/" + date + ".txt").exists()) {
			JOptionPane.showMessageDialog(null, "" + date + " is already there", "Error",
					JOptionPane.ERROR_MESSAGE);
			return true;
		}
		else{
			return false;
		}

	}
}
