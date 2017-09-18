/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoring.monitoring;

import javax.swing.JToolBar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


@SuppressWarnings("serial")
public class UpdateProgressReport extends ProgressReport {

	int dateOffset;
	public UpdateProgressReport(int dateOffset) throws Exception {
		super(Util.getDayDate(dateOffset));
		this.dateOffset = dateOffset;
	}

	public static void createAndShowGUI(int dateOffset) throws Exception {
		// Create and set up the window.

		UpdateProgressReport report = new UpdateProgressReport(dateOffset);
		JFrame frame = new JFrame("Neo's Progress report for " + Util.getDayDate(dateOffset));

		// Add content to the window.
		frame.add(report, BorderLayout.CENTER);
		frame.setJMenuBar(getMenuBar(report));
		setupToolBar(frame, report, dateOffset);
		new LoadReportForUpdate(report, Util.getDayDate(dateOffset)).actionPerformed(null);


		if (shutdown) {
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		// Display the window.
		frame.pack();

		frame.setVisible(true);
		frame.setSize(new Dimension(1400, 850));

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
					createAndShowGUI(-1);
				} catch (Exception ex) {
					Logger.getLogger(UpdateProgressReport.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public static JMenuBar getMenuBar(UpdateProgressReport report) {
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

	public  static void setupToolBar(JFrame frame, UpdateProgressReport panel, int dateOffset) {
		JToolBar toolBar = new javax.swing.JToolBar();
		JButton update = new javax.swing.JButton("update");
		update.addActionListener(new UpdateReport(panel, Util.getDayDate(dateOffset)));
		
		JButton loadPreviousDay = new javax.swing.JButton("Load Previous Day");
		loadPreviousDay.addActionListener(new LoadReport(panel, ProgressReport.SUPPLEMENTS, Util.getDayDate(dateOffset-1)));
		
		toolBar.add(update);
		toolBar.addSeparator();
		toolBar.add(loadPreviousDay);
		frame.add(toolBar, BorderLayout.PAGE_START);
	}
}