package monitoring.monitoring;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShowFileLabel extends JLabel {
	private static final long serialVersionUID = 8273875024682878518L;

	public ShowFileLabel(File file) {
		super();
		setup(file);
	}

	public void setup(final File file) {
		setText(Util.getFileNameWithoutExtension(file));
		setToolTipText(file.getName());
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					show(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mouseEntered(MouseEvent e) {
				setText(Util.getFileNameWithoutExtension(file), false);
			}

			public void mouseExited(MouseEvent e) {
				setText(Util.getFileNameWithoutExtension(file), true);
			}
		});
	}

	@Override
	public void setText(String text) {
		setText(text, true);
	}

	public void setText(String text, boolean ul) {
		String link = ul ? "<u>" + text + "</u>" : text;
		super.setText("<html><span style=\"color: #000099;\">" + link + "</span></html>");
	}

	private void show(File file) throws IOException {
		new FileFrame(file).show();
	}

	public static void main(String[] args) {
		ShowFileLabel link = new ShowFileLabel(new File(
				"/Users/purushah/Documents/mydoc/doc/neo report/progress/items/10-26-2017/Neo biting at school.mail"));
		JFrame f = new JFrame("");
		f.setSize(400, 400);
		f.add(link);
		f.setVisible(true);
		f.show();
	}
}