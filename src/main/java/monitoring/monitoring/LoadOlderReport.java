package monitoring.monitoring;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class LoadOlderReport extends LoadReport {
	private javax.swing.JFileChooser jFileChooser1;

	public LoadOlderReport(JPanel report) {
		super(report, null, null);
		jFileChooser1 = new JFileChooser(Util.getPath());
	}

	public void actionPerformed(ActionEvent e) {
		jFileChooser1.showOpenDialog(report);
		File file = jFileChooser1.getSelectedFile();
		String fileName = file.getName();
		String date = file.getName().substring(0, fileName.length() - 4);
		this.date = date;
		super.actionPerformed(e);
	}
}
