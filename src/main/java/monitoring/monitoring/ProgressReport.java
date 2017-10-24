/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring.monitoring;

import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ProgressReport extends JPanel {
	public static String VEGI = "Vegetables";
	public static String FRUITS = "Fruits";
	public static String JUICING = "Juicing";
	public static String Report = "Report";
	public static String MAIL = "Discussion";
	public static String SUPPLEMENTS = "Supplements";
	public static String HOMOEPATHY = "Homeopathy";
	public static String THERAPY = "Therapy";
	public static String ESSENTIAL_OIL = "Essential oil";
	public static String DETOX_BATH = "Detox bath";
	public static String CHIROPRACTIC = "Chiropractic";
	public static String PROGRESS = "Progress";
	public static String SPECAIL_EVENT = "Special event";
	public static String NOTE = "Note";
	public static String ATEC = "ATEC";
	public static String BEHAVIOUR = "Behaviour";
	public static String ISSUES = "Issues";
	public static String OPTION_LIST = "option";
	public static String FOOD_COLOR_LIST = "food_color";

	public static boolean shutdown = false;

	JTabbedPane tabbedPane = new JTabbedPane();

	String date;

	public ProgressReport() throws Exception {
		this(Util.getDayDate(0));
	}

	public ProgressReport(String date) throws Exception {
		super(new GridLayout(1, 1));
		this.date = date;
		addVegetables();
		addFruits();
		addJucing();
		addDetoxBath();
		addTherapy();
		addSupplements();
		addHomeopathy();
		addEssentailOil();
		addBehaviour();
		addSpecailEvent();
		addNote();
		addIssues();
		addReport();
		addMail();
		addProgress();
		addATEC();
		add(tabbedPane);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}

	private void addNote() throws IOException {
		JComponent progressPanel = makeTextPanel(NOTE);
		new NoteUIProgress((JPanel) progressPanel);
		tabbedPane.add(NOTE, progressPanel);

	}
	
	private void addIssues() throws IOException {
		JComponent progressPanel = makeTextPanel(ISSUES);
		new IssuesUI((JPanel) progressPanel);
		tabbedPane.add(ISSUES, progressPanel);

	}


	private void addReport() throws Exception {
		JComponent reportPanel = makeTextPanel(Report);
        JScrollPane scrollPane = new JScrollPane(reportPanel);
        tabbedPane.add(scrollPane);        
		new ReportUI((JPanel) reportPanel, date);
		tabbedPane.add(Report, scrollPane);

	}
	
	private void addMail() throws Exception {
		JComponent mailPanel = makeTextPanel(MAIL);
        JScrollPane scrollPane = new JScrollPane(mailPanel);
        tabbedPane.add(scrollPane);        
		new MailUI((JPanel) mailPanel, date);
		tabbedPane.add(MAIL, scrollPane);

	}


	protected JPanel makeTextPanel(String name) {
		JPanel panel = new JPanel(false);
		panel.setName(name);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		return panel;
	}

	/** Returns an ImageIcon, or null if the path was invalid. */
	protected static ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = ProgressReport.class.getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event dispatch thread.
	 */
	public static void createAndShowGUI() throws Exception {
		// Create and set up the window.

		JFrame frame = new JFrame("Neo's Progress report");
		ProgressReport report = new ProgressReport();
		// Add content to the window.
		frame.add(report, BorderLayout.CENTER);
		frame.setJMenuBar(getMenuBar(report));
		setupToolBar(frame, report);

		if (shutdown) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		// Display the window.
		frame.pack();

		frame.setVisible(true);
		frame.setSize(new Dimension(1200, 800));

	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		shutdown = true;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					// Turn off metal's use of bold fonts
					UIManager.put("swing.boldMetal", Boolean.FALSE);
					createAndShowGUI();
				} catch (Exception ex) {
					Logger.getLogger(ProgressReport.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	private void addVegetables() throws IOException {
		JComponent vegetablesPanel = makeTextPanel(VEGI);
		vegetablesPanel.setLayout(new GridLayout(19, 5));
		tabbedPane.addTab(VEGI, vegetablesPanel);
		addToTabwithColor(vegetablesPanel, VEGI, Util.getColorMap());

	}

	private void addFruits() throws IOException {
		JComponent fruitsPanel = makeTextPanel(FRUITS);
		tabbedPane.addTab(FRUITS, fruitsPanel);
		addToTab(fruitsPanel, FRUITS);
	}

	private void addJucing() throws IOException {
		JComponent jucingPanel = makeTextPanel(JUICING);
		tabbedPane.addTab(JUICING, jucingPanel);
		addToTab(jucingPanel, JUICING);
	}

	private void addTherapy() throws IOException {
		JPanel progressPanel = makeTextPanel(THERAPY);
		tabbedPane.addTab(THERAPY, progressPanel);
		progressPanel.setLayout(new GridLayout(18, 1));

		List<String> vegitables = Util.getFileList(THERAPY);
		for (String vegi : vegitables) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			JCheckBox boxes = new JCheckBox(vegi);
			p.add(boxes, "w 190!");
			progressPanel.add(p);
		}

	}

	private void addBehaviour() throws IOException {
		JPanel progressPanel = makeTextPanel(BEHAVIOUR);
		tabbedPane.addTab(BEHAVIOUR, progressPanel);
		progressPanel.setLayout(new GridLayout(18, 1));

		List<String> vegitables = Util.getFileList(BEHAVIOUR);
		updatePane(vegitables, progressPanel);

	}

	private void updateBehaviour(JComponent panel1) throws IOException {
		panel1.removeAll();
		panel1.setLayout(new GridLayout(18, 1));

		List<String> vegitables = Util.getFileList(BEHAVIOUR);
		for (String vegi : vegitables) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			JCheckBox boxes = new JCheckBox(vegi);
			p.add(boxes, "w 190!");
			JSpinner spinner = getSpinner((JPanel) panel1);
			p.add(spinner, "w 70!");
			panel1.add(p);
			p.add(new JLabel("Times"));

		}

	}

	private void addDetoxBath() throws IOException {
		JComponent detoxBathPanel = makeTextPanel(DETOX_BATH);
		tabbedPane.addTab(DETOX_BATH, detoxBathPanel);
		addToTab(detoxBathPanel, DETOX_BATH);
	}

	private void addEssentailOil() throws IOException {
		JComponent essentailOilPanel = makeTextPanel(ESSENTIAL_OIL);
		tabbedPane.addTab(ESSENTIAL_OIL, essentailOilPanel);
		addToTab(essentailOilPanel, ESSENTIAL_OIL);
	}

	private void addHomeopathy() throws IOException {
		JComponent homeopathyPanel = makeTextPanel(HOMOEPATHY);
		tabbedPane.addTab(HOMOEPATHY, homeopathyPanel);
		homeopathyPanel.setLayout(new GridLayout(18, 4));

		List<String> homepathy = Util.getFileList(HOMOEPATHY);
		for (String object : homepathy) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			p.add(new JCheckBox(object), "w 190!");
			JTextField ml = new JTextField(10);
			p.add(ml);
			p.add(new JLabel("Times"));
			JTextField times = new JTextField(10);
			p.add(times);
			p.add(new JLabel("ML"));
			homeopathyPanel.add(p);
		}

	}

	private void addToTab(JComponent panel1, String name) throws IOException {
		List<String> list = Util.getFileList(name);
		for (String item : list) {
			JCheckBox boxes = new JCheckBox(item);
			panel1.add(boxes);
		}
	}

	private void refeshTab(JComponent panel1, String name) throws IOException {
		panel1.removeAll();
		List<String> list = Util.getFileList(name);
		for (String item : list) {
			JCheckBox boxes = new JCheckBox(item);
			panel1.add(boxes);
		}
	}

	private void addToTabwithColor(JComponent panel1, String name, Map<String, String> colorMap) throws IOException {
		List<String> list = Util.getFileList(name);
		for (String item : list) {
			JCheckBox boxes = new JCheckBox(item);
			System.out.println(item);

			System.out.println(item + " " + ObjectFactory.getColor(colorMap.get(item)));
			if (ObjectFactory.getColor(colorMap.get(item)) != null) {
				boxes.setForeground(ObjectFactory.getColor(colorMap.get(item)));
			}
			panel1.add(boxes);
		}
	}

	private void addSupplements() throws IOException {
		JComponent supplementPanel = makeTextPanel(SUPPLEMENTS);
		supplementPanel.setLayout(new GridLayout(18, 4));
		tabbedPane.addTab(SUPPLEMENTS, supplementPanel);
		List<String> vegitables = Util.getFileList(SUPPLEMENTS);
		int i = 0;
		for (String vegi : vegitables) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			p.add(new JCheckBox(vegi), "w 190!");
			JTextField ml = new JTextField(3);
			p.add(ml);
			p.add(new JLabel("Times"));
			JTextField times = new JTextField(3);
			p.add(times);
			p.add(new JLabel("Ml/Mg"));
			supplementPanel.add(p);
		}
	}

	private void updateSupplements(JComponent supplementPanel) throws IOException {
		supplementPanel.removeAll();
		supplementPanel.setLayout(new GridLayout(18, 4));
		tabbedPane.addTab(SUPPLEMENTS, supplementPanel);
		List<String> vegitables = Util.getFileList(SUPPLEMENTS);
		int i = 0;
		for (String vegi : vegitables) {
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			p.add(new JCheckBox(vegi), "w 190!");
			JTextField ml = new JTextField(3);
			p.add(ml);
			p.add(new JLabel("Times"));
			JTextField times = new JTextField(3);
			p.add(times);
			p.add(new JLabel("Ml/Mg"));
			supplementPanel.add(p);
		}
	}

	private void addProgress() throws IOException {

		JPanel progressPanel = makeTextPanel(PROGRESS);
		tabbedPane.addTab(PROGRESS, progressPanel);
		progressPanel.setLayout(new GridLayout(18, 1));

		// ImageIcon icon =
		// createImageIcon("file:///Users/purushah/temp/icon.png");
		Icon icon1 = UIManager.getIcon("OptionPane.informationIcon");
		ImageIcon icon = new ImageIcon(new ImageIcon("OptionPane.informationIcon").getImage().getScaledInstance(200,
				200, Image.SCALE_DEFAULT));

		List<String> vegitables = Util.getFileList(PROGRESS);
		updatePane(vegitables, progressPanel);
	}

	private void updatePane(List<String> objects, JPanel panel) throws IOException {
		for (String object : objects) {
			String name = object;
			boolean isOption = false;
			String option = null;
			boolean JspinnerListerner = false;
			String toolTip = null;
			if (object.contains(ProgressConstant.seperator)) {
				name = object.split(ProgressConstant.seperator)[0];
				if (object.split(ProgressConstant.seperator).length > 1) {
					if (object.split(ProgressConstant.seperator)[1].trim().endsWith("option")) {
						isOption = true;
						option = object.split(ProgressConstant.seperator)[1].trim();
					}
				}
				if (object.split(ProgressConstant.seperator).length > 2) {
					toolTip = object.split(ProgressConstant.seperator)[1];
				}
				if (name.equals("Sleep - Wakeup") || name.equals("Sleep - At")) {
					JspinnerListerner = true;
				}
			}
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			JLabel label = new JLabel(name) {
				public void mouseClicked(MouseEvent me) {
					System.out.println("Icon clicked");
				}
			};
			if (toolTip != null) {
				// p.add(icon1);
				label.setToolTipText(toolTip);
				label.setIcon(Util.getInfoIcon(label));

			}

			p.add(label, "w 190!");
			if (isOption) {
				p.add(getOptionList(option), "w 125!");
			} else {
				JSpinner spinner = getSpinner(panel, JspinnerListerner);
				p.add(spinner, "w 120!");
			}
			panel.add(p);

		}
	}

	private void addATEC() throws IOException {
		JPanel progressPanel = makeTextPanel(ATEC);
		tabbedPane.addTab(ATEC, progressPanel);
		progressPanel.setLayout(new GridLayout(18, 1));

		List<String> vegitables = Util.getFileList(ATEC);
		for (String vegi : vegitables) {
			String name = vegi;
			boolean JspinnerListerner = false;
			JPanel p = new JPanel();
			p.setLayout(new MigLayout());
			JLabel label = new JLabel(name);
			p.add(label, "w 190!");
			JSpinner spinner = getSpinner(progressPanel, JspinnerListerner);
			p.add(spinner, "w 120!");
			progressPanel.add(p);
		}

	}

	private void addSpecailEvent() throws IOException {

		JComponent progressPanel = makeTextPanel(SPECAIL_EVENT);
		new SpecialEventUI((JPanel) progressPanel);
		tabbedPane.add(SPECAIL_EVENT, progressPanel);
	}

	public static JMenuBar getMenuBar(ProgressReport report) {
		JMenuBar menuBar = new JMenuBar();
		JMenu settingMenu = new JMenu("Setting");
		JMenuItem submenu = new JMenuItem("List Path");
		submenu.addActionListener(new ProgressDir(null));
		settingMenu.add(submenu);
		menuBar.add(settingMenu);

		return menuBar;
	}

	public void actionPerformed(ActionEvent e) {
	}

	public static void setupToolBar(JFrame frame, ProgressReport panel) {
		JToolBar toolBar = new javax.swing.JToolBar();
		JButton save = new javax.swing.JButton("Save");
		save.addActionListener(new SaveReport(panel));
		JButton saveAs = new javax.swing.JButton("SaveAs");
		saveAs.addActionListener(new SaveReportAs(panel));

		JButton previousDay = new javax.swing.JButton("Save PreviousDay");
		previousDay.addActionListener(new SaveReport(panel, Util.getDayDate(-1)));

		JButton load = new javax.swing.JButton("Load");
		load.addActionListener(new LoadOlderReport(panel));

		JButton loadPreviousDay = new javax.swing.JButton("Load Previous Day");
		loadPreviousDay.addActionListener(new LoadReport(panel, Util.getDayDate(-1)));

		JButton loadToday = new javax.swing.JButton("Load Today");
		loadToday.addActionListener(new LoadReport(panel, Util.getDayDate(0)));

		JButton addNewItem = new javax.swing.JButton("Add");
		addNewItem.addActionListener(new NewItemUI(panel));

		toolBar.add(save);
		toolBar.addSeparator();
		toolBar.add(saveAs);
		toolBar.addSeparator();
		toolBar.add(previousDay);
		toolBar.addSeparator();
		toolBar.add(load);
		toolBar.addSeparator();
		toolBar.add(loadPreviousDay);
		toolBar.addSeparator();
		toolBar.add(loadToday);
		toolBar.addSeparator();
		toolBar.add(addNewItem);

		frame.add(toolBar, BorderLayout.PAGE_START);

	}

	public void update(JComponent c) throws IOException {

		if (c.getName().equals(ProgressReport.VEGI)) {
			refeshTab((JComponent) c, ProgressReport.VEGI);
		}
		if (c.getName().equals(ProgressReport.SUPPLEMENTS)) {
			updateSupplements(c);
		}
		if (c.getName().equals(ProgressReport.PROGRESS)) {
			addProgress();
		}

		if (c.getName().equals(ProgressReport.DETOX_BATH)) {
			addDetoxBath();
		}
		if (c.getName().equals(ProgressReport.ESSENTIAL_OIL)) {
			refeshTab((JComponent) c, ProgressReport.ESSENTIAL_OIL);
		}

		// if (c.getName().equals(ProgressReport.FERMENTATION)) {
		// loadFermentation((JPanel) c);
		// }

		if (c.getName().equals(ProgressReport.FRUITS)) {
			refeshTab((JComponent) c, ProgressReport.FRUITS);
		}

		if (c.getName().equals(ProgressReport.HOMOEPATHY)) {
			addHomeopathy();
		}

		if (c.getName().equals(ProgressReport.JUICING)) {
			refeshTab((JComponent) c, ProgressReport.JUICING);
		}

		if (c.getName().equals(ProgressReport.THERAPY)) {
			addTherapy();
		}
		if (c.getName().equals(ProgressReport.BEHAVIOUR)) {
			updateBehaviour(c);
		}
	}

	public String getTextwithSpacing(String text, int length) {
		return String.format("%-" + length + "." + length + "s", text);

	}

	private JComboBox<String> getOptionList(String option) throws IOException {
		JComboBox<String> jlst = new JComboBox(Util.getOptionList(option).toArray(new String[0]));
		return jlst;

	}

	private JSpinner getSpinner(JPanel panel) {
		return getSpinner(panel, false);
	}

	private JSpinner getSpinner(JPanel panel, boolean listner) {
		JSpinner spinner = new JSpinner(new SpinnerNumberModel(0.0, -1000.0, 1000.0, 1.0));
		// if (listner) {
		// spinner.addChangeListener(new JSpinnerChangeListener(panel));
		// }
		return spinner;
	}

}