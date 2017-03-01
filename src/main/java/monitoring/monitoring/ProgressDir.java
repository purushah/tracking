package monitoring.monitoring;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class ProgressDir extends Frame implements WindowListener, ActionListener {

	JPanel pane;

	// Variables declaration - do not modify
	private javax.swing.JFileChooser jFileChooser1 = new JFileChooser();
	// End of variables declaration

	public static void main(String[] args) {
		ProgressDir myWindow = new ProgressDir(null);
		myWindow.setSize(350, 100);
		myWindow.setVisible(true);
	}

	public ProgressDir(JPanel pane) {
		this.pane = pane;

	}

	public void actionPerformed(ActionEvent e) {
		jFileChooser1.showOpenDialog(pane);
	}

	public void windowClosing(WindowEvent e) {
		dispose();
		System.exit(0);
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
	}

}