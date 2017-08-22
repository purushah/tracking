package monitoring.monitoring;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.JPanel;

public class IssuesUI extends NoteUIProgress {

	public IssuesUI(JPanel panel) throws IOException {
		super(panel);
	}
	
	protected void initComponents() throws IOException {
		super.initComponents();
		List<String> sepecialEvent = Util.getFileList(ProgressReport.ISSUES);
		Collections.sort(sepecialEvent);
		sourceListModel.addAll(sepecialEvent.toArray(new String[0]));

	}

	protected String getName() {
		return ProgressReport.ISSUES;
	}
}
