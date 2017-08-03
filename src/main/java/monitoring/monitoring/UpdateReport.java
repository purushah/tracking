package monitoring.monitoring;

import javax.swing.JPanel;

public class UpdateReport extends SaveReport {

		
	public UpdateReport(JPanel report, String date) {
		super(report, date);
		// TODO Auto-generated constructor stub
	}

	protected boolean checkFileExist() {
		return false;
	}

}
