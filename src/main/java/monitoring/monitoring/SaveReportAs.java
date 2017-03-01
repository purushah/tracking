package monitoring.monitoring;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class SaveReportAs extends SaveReport {

	private javax.swing.JFileChooser jFileChooser1;

	public SaveReportAs(JPanel report) {
		super(report);
		jFileChooser1 = new JFileChooser(Util.getPath());
	}

	public void actionPerformed(ActionEvent e) {
		jFileChooser1.showOpenDialog(report);
		File file = jFileChooser1.getSelectedFile();
		String fileName = file.getName();
		String date = file.getName().substring(0, fileName.length() - 4);
		this.date= date;
	}

}
