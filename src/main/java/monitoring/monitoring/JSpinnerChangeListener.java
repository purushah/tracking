package monitoring.monitoring;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class JSpinnerChangeListener implements ChangeListener {
	JPanel panel;

	public JSpinnerChangeListener(JPanel panel) {
		this.panel = panel;
	}

	public void stateChanged(ChangeEvent e) {
		for (Component c : panel.getComponents()) {
			JPanel progress = (JPanel) c;
			Component[] pro = progress.getComponents();
			if (((JLabel) pro[0]).getText().equals("Sleep - Total")) {
				if (Double.parseDouble(((JSpinner) pro[1]).getValue().toString()) >= 0.0) {
					((JSpinner) pro[1]).addChangeListener(null);
					((JSpinner) pro[1]).setValue(getSleepTime());
				}
			}
		}
	}

	public String getSleepTime() {
		double spletAt = 0, wokeUp = 0;
		for (Component c : panel.getComponents()) {
			JPanel progress = (JPanel) c;
			Component[] pro = progress.getComponents();
			if (((JLabel) pro[0]).getText().equals("Sleep - At")) {
				wokeUp = Double.parseDouble(((JSpinner) pro[1]).getValue().toString());
			}
			if (((JLabel) pro[0]).getText().equals("Sleep - Wakeup")) {
				spletAt = Double.parseDouble(((JSpinner) pro[1]).getValue().toString());
			}
		}
		return String.valueOf(spletAt + 12 - wokeUp);
	}

}
