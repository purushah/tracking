package monitoring.monitoring;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import javax.imageio.ImageIO;
import javax.media.CannotRealizeException;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import org.apache.commons.io.FileUtils;
import monitoring.pojo.ReportObject;
import net.miginfocom.swing.MigLayout;

public class DisplayReport extends JPanel {

	private static final int width = 400;

	private static final int height = 400;

	public static void main(String[] args) throws IOException, CannotRealizeException {
		createAndShowGUI();

	}

	public void dispalyNotes() throws IOException, CannotRealizeException {
		Map<String, ReportObject> report = new TreeMap<String, ReportObject>(new Comparator<String>() {
			public int compare(String o1, String o2) {
				try {
					return Util.getDate(o2).compareTo(Util.getDate(o1));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return -1;
			}
		});

		report.putAll(Util.getReport(-1));

		for (String day : report.keySet()) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());

			SwingLink link = new SwingLink(day, day);
			p.add(link, "wrap");
			File f = new File(Util.getPath(), day);
			pouplateVideos(f);
			pouplateImage(f);
			add(p);
		}
	}

	public static void createAndShowGUI() throws IOException, CannotRealizeException {
		// Create and set up the window.

		JFrame frame = new JFrame("Neo's Progress Note");
		DisplayReport report = new DisplayReport();
		report.dispalyNotes();
		// Add content to the window.

		report.setLayout(new BoxLayout(report, BoxLayout.Y_AXIS));
		JScrollPane scrollPane = new JScrollPane(report);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		frame.add(scrollPane, BorderLayout.CENTER);

		// Display the window.
		frame.pack();

		frame.setVisible(true);
		frame.setSize(new Dimension(1200, 800));

	}

	public void pouplateVideos(File dir) throws CannotRealizeException, MalformedURLException, IOException {
		if (dir.exists()) {
			String[] extensions = { "mp4", "mov", "m4v", "MOV" };
			Collection<File> allMovies = FileUtils.listFiles(dir, extensions, true);
			for (File file : allMovies) {
				addVideo(file);
			}
		}
	}

	public void pouplateImage(File dir) throws CannotRealizeException, MalformedURLException, IOException {
		if (dir.exists()) {
			String[] extensions = { "jpeg", "png", "jpg", "JPG" };
			for (File file : FileUtils.listFiles(dir, extensions, true)) {
				addImage(file, null);
			}
		}
	}

	public void addVideo(File file) throws CannotRealizeException, MalformedURLException, IOException {
		addImage(new File(Util.getPath() + "/click.png"), file);
	}

	public void addImage(final File file, final File actualFile)
			throws CannotRealizeException, MalformedURLException, IOException {

		ImageIcon img = new ImageIcon(ImageIO.read(file).getScaledInstance(width, height, Image.SCALE_SMOOTH));
		JLabel imgLabel = new JLabel(img);
		imgLabel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (actualFile != null) {
					new VideoPlayer().play(actualFile.getAbsolutePath());
				}
			}
		});
		add(imgLabel, "wrap");
		String name = actualFile == null ? file.getAbsolutePath() : actualFile.getAbsolutePath();
		File cpationFile = new File(name.substring(0, name.lastIndexOf(".")) + ".txt");
		if (cpationFile.exists()) {
			add(new JLabel(Files.readAllLines(cpationFile.toPath()).get(0)), "wrap");
		}

	}
}
