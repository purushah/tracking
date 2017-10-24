package monitoring.monitoring;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;

import javax.media.CannotRealizeException;
import javax.swing.JPanel;

/**
 *
 * @author purushah
 */
public class MailUI {

	JPanel panel;
	String date;

	/**
	 * Creates new form NewJDialog
	 */
	public MailUI(JPanel reportPanel) throws CannotRealizeException, MalformedURLException, IOException {
		this.panel = reportPanel;
		initComponents();
	}

	public MailUI(JPanel reportPanel, String date) throws CannotRealizeException, MalformedURLException, IOException {
		this(reportPanel);
		this.date = date;
	}

	private void initComponents() {

		jScrollPane1 = new javax.swing.JScrollPane();
		mailArea = new javax.swing.JTextArea();
		addMail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel("Mail subject");
        jTextField1 = new javax.swing.JTextField();


		mailArea.setColumns(20);
		mailArea.setRows(5);
		jScrollPane1.setViewportView(mailArea);

		addMail.setText("Add");
		addMail.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addMail(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(21, 21, 21)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 720,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 853,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(addMail, javax.swing.GroupLayout.PREFERRED_SIZE, 115,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(72, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addGap(12, 12, 12)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jLabel1).addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(addMail)
						.addGap(37, 37, 37)));

	}// </editor-fold>

	private void addMail(java.awt.event.ActionEvent evt) {
		File dir = new File(Util.getPathDir(date));
		File file = new File(jTextField1.getText());
		File newFile = new File(dir, file.getName());
		if (!dir.exists()) {
			dir.mkdir();
		}
		try {
			Files.write(new File(dir, Util.getFileNameWithoutExtension(file) + ".txt").toPath(),
					mailArea.getText().getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Variables declaration - do not modify
	private javax.swing.JButton addMail;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JTextArea mailArea;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JTextField jTextField1;

	// End of variables declaration
}
