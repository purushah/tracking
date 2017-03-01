package monitoring.monitoring;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.apache.commons.lang3.StringUtils;

import monitoring.pojo.BasicObject;
import monitoring.pojo.Behaviour;
import monitoring.pojo.DosageObject;
import monitoring.pojo.Note;
import monitoring.pojo.Progress;
import monitoring.pojo.ProgressObject;
import monitoring.pojo.Supplements;
import monitoring.pojo.Therapy;

public class LoadReport implements ActionListener {
	JPanel report;
	Map<String, BasicObject> objectMap = new HashMap<String, BasicObject>();

	String date;

	public LoadReport(JPanel report, String date) {
		this.report = report;
		this.date = date;
	}

	public LoadReport(JPanel report) {
		this.report = report;
	}

	public void actionPerformed(ActionEvent e) {

		objectMap = Util.getReport(date);

		JTabbedPane tabbedPane = (JTabbedPane) (report.getComponent(0));
		int totalTabs = tabbedPane.getTabCount();
		for (int i = 0; i < totalTabs; i++) {
			Component c = tabbedPane.getComponent(i);
			if (c.getName().equals(ProgressReport.VEGI)) {
				loadVegitables((JPanel) c);
			}
			if (c.getName().equals(ProgressReport.SUPPLEMENTS)) {
				loadSupplements((JPanel) c);
			}
			if (c.getName().equals(ProgressReport.PROGRESS)) {
				try {
					loadProgress((JPanel) c);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (c.getName().equals(ProgressReport.DETOX_BATH)) {
				loadDetoxBath((JPanel) c);
			}
			if (c.getName().equals(ProgressReport.ESSENTIAL_OIL)) {
				loadEssentialOil((JPanel) c);
			}

			// if (c.getName().equals(ProgressReport.FERMENTATION)) {
			// loadFermentation((JPanel) c);
			// }

			if (c.getName().equals(ProgressReport.FRUITS)) {
				loadFruits((JPanel) c);
			}

			if (c.getName().equals(ProgressReport.HOMOEPATHY)) {
				loadHomeopathy((JPanel) c);
			}

			if (c.getName().equals(ProgressReport.JUICING)) {
				loadJucing((JPanel) c);
			}

			if (c.getName().equals(ProgressReport.SPECAIL_EVENT)) {
				loadSpecialEvent((JPanel) c);
			}

			if (c.getName().equals(ProgressReport.THERAPY)) {
				loadTherapy((JPanel) c);
			}
			if (c.getName().equals(ProgressReport.BEHAVIOUR)) {
				loadBehaviour((JPanel) c);
			}

		}

		JOptionPane.showMessageDialog(report, "Data loaded from " + date);

	}

	private void loadNote(JPanel panel) {
		Note note = (Note) objectMap.get(ProgressReport.NOTE);
		for (Component c : panel.getComponents()) {
			if (!StringUtils.isEmpty(c.getName())) {
				if (c.getName().equals(ProgressReport.NOTE)) {
					SortedListModel model = (SortedListModel) ((JList) ((JViewport) ((JScrollPane) c).getComponent(0))
							.getComponent(0)).getModel();

					model.addAll(note.getAll().toArray(new String[0]));
				}
			}
			// other stuff

		}
	}

	private void loadTherapy(JPanel panel) {
		Therapy progress = (Therapy) objectMap.get(ProgressReport.THERAPY);
		for (Component c : panel.getComponents()) {
			Component[] pro = ((JPanel) c).getComponents();
			System.out.println("((JCheckBox) pro[0]).getText())" + ((JCheckBox) pro[0]).getText());
			if (progress.contains(((JCheckBox) pro[0]).getText())) {
				ProgressObject objcet = progress.get(((JCheckBox) pro[0]).getText());
				((JCheckBox) pro[0]).setSelected(true);
				((JSpinner) pro[1]).setValue(Double.parseDouble(objcet.getProgress()));
			}
		}
	}

	private void loadBehaviour(JPanel panel) {
		Behaviour progress = (Behaviour) objectMap.get(ProgressReport.BEHAVIOUR);
		if (progress == null) {
			return;
		}
		for (Component c : panel.getComponents()) {
			Component[] pro = ((JPanel) c).getComponents();
			System.out.println("((JCheckBox) pro[0]).getText())" + ((JCheckBox) pro[0]).getText());
			if (progress.contains(((JCheckBox) pro[0]).getText())) {
				ProgressObject objcet = progress.get(((JCheckBox) pro[0]).getText());
				((JCheckBox) pro[0]).setSelected(true);
				((JSpinner) pro[1]).setValue(Double.parseDouble(objcet.getProgress()));
			}
		}
	}

	private void loadSpecialEvent(JPanel panel) {

	}

	private void loadJucing(JPanel panel) {
		loadObject(panel, objectMap.get(ProgressReport.JUICING));

	}

	private void loadHomeopathy(JPanel panel) {
		Homeopathy homeopathy = (Homeopathy) objectMap.get(ProgressReport.HOMOEPATHY);
		for (Component c : panel.getComponents()) {
			JPanel supplement = (JPanel) c;
			Component[] sup = supplement.getComponents();

			if (homeopathy.contains(((JCheckBox) sup[0]).getText())) {
				DosageObject objcet = homeopathy.get(((JCheckBox) sup[0]).getText());
				((JCheckBox) sup[0]).setSelected(true);
				((JTextField) sup[1]).setText(objcet.getTimes());
				((JTextField) sup[3]).setText(objcet.getMl());
			}
			// other stuff

		}
	}

	private void loadFruits(JPanel panel) {
		loadObject(panel, objectMap.get(ProgressReport.FRUITS));

	}

	// private void loadFermentation(JPanel panel) {
	// loadObject(panel, objectMap.get(ProgressReport.FERMENTATION));
	//
	// }

	private void loadEssentialOil(JPanel panel) {
		loadObject(panel, objectMap.get(ProgressReport.ESSENTIAL_OIL));

	}

	private void readReport(BufferedReader reader) throws IOException {
		String sCurrentLine;
		BasicObject currentObject = null;
		while ((sCurrentLine = reader.readLine()) != null) {
			if (sCurrentLine.startsWith(ProgressConstant.section_start)) {
				if (objectMap.containsKey(sCurrentLine)) {
					currentObject = objectMap.get(sCurrentLine);
				} else {
					String name = StringUtils.substring(sCurrentLine, ProgressConstant.section_start.length());
					currentObject = ObjectFactory.getObject(name);
					objectMap.put(name, currentObject);
				}
			} else {
				if (!StringUtils.isEmpty(sCurrentLine)) {
					currentObject.readObject(sCurrentLine);
				}
			}

		}
	}

	public void loadVegitables(JPanel panel) {
		loadObject(panel, objectMap.get(ProgressReport.VEGI));

	}

	public void loadDetoxBath(JPanel panel) {
		loadObject(panel, objectMap.get(ProgressReport.DETOX_BATH));

	}

	public void loadObject(JPanel panel, BasicObject object) {
		for (Component c : panel.getComponents()) {
			JCheckBox comp = (JCheckBox) c;
			if (object.contains(comp.getText())) {
				comp.setSelected(true);
			}
		}
	}

	public void loadSupplements(JPanel supplements) {
		Supplements supplementsObject = (Supplements) objectMap.get(ProgressReport.SUPPLEMENTS);
		for (Component c : supplements.getComponents()) {
			JPanel supplement = (JPanel) c;
			Component[] sup = supplement.getComponents();

			if (supplementsObject.contains(((JCheckBox) sup[0]).getText())) {
				DosageObject objcet = supplementsObject.get(((JCheckBox) sup[0]).getText());
				((JCheckBox) sup[0]).setSelected(true);
				((JTextField) sup[1]).setText(objcet.getTimes());
				((JTextField) sup[3]).setText(objcet.getMl());
			}
			// other stuff
		}
	}

	public void loadProgress(JPanel panel) throws IOException {
		Progress progress = (Progress) objectMap.get(ProgressReport.PROGRESS);
		for (Component c : panel.getComponents()) {
			Component[] pro = ((JPanel) c).getComponents();
			if (progress.contains(((JLabel) pro[0]).getText())) {
				ProgressObject objcet = progress.get(((JLabel) pro[0]).getText());
				if (pro[1] instanceof JSpinner) {
					((JSpinner) pro[1]).setValue(Integer.parseInt(objcet.getProgress()));
					} else {
					((JComboBox) pro[1]).setSelectedItem(getOption(objcet.getProgress()));
				}
			}
		}

	}
	
	public String getOption(String value) throws IOException {
	    Map<String, String> result = Util.getOptionMap();
	    for(String k: result.keySet()){
	        if(result.get(k).equals(value)){
	        	return k;
	        }
	    }
	    
	    return null;
	}
}
