package monitoring.monitoring;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class FileFrame {
	JFrame f;
	File file;

	public FileFrame(File file) {
		this.file = file;
		f = new JFrame(Util.getFileNameWithoutExtension(file));
	}

	public void show() throws IOException {
		f.setSize(800, 500);
        f.setLocation(100, 100);

		JPanel panel = new JPanel();
		panel.setLayout(new MigLayout());
		JScrollPane jScrollPane = new JScrollPane(panel);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		f.getContentPane().add(jScrollPane);

		
		for (String line : Files.readAllLines(file.toPath(), Charset.defaultCharset())) {
			panel.add(new JLabel(line), "wrap");
		}

		f.show();
	}

}
