package monitoring.monitoring;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.swing.AbstractListModel;
import javax.swing.JPanel;
import javax.swing.ListModel;

public class SpecialEventUI {
	JPanel panel;
	// Variables declaration - do not modify
	private javax.swing.JButton addEvent;
	private javax.swing.JButton addButton;
	private javax.swing.JButton removeButton;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JList<String> sourceList;
	private javax.swing.JList<String> destList;
	private javax.swing.JScrollPane sourceScrollPanel;
	private javax.swing.JScrollPane destScrollPanel;
	private javax.swing.JTextField eventText;
	private SortedListModel sourceListModel;
	private SortedListModel destListModel;

	public SpecialEventUI(JPanel panel) throws IOException {
		this.panel = panel;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() throws IOException {
		List<String> sepecialEvent = Util.getFileList(ProgressReport.SPECAIL_EVENT);
		sourceListModel = new SortedListModel();
		sourceListModel.addAll(sepecialEvent.toArray(new String[0]));

		destListModel = new SortedListModel();


		eventText = new javax.swing.JTextField();
		addEvent = new javax.swing.JButton();
		sourceScrollPanel = new javax.swing.JScrollPane();
		sourceList = new javax.swing.JList(sourceListModel);
		destScrollPanel = new javax.swing.JScrollPane();
		destList = new javax.swing.JList(destListModel);
		addButton = new javax.swing.JButton();
		removeButton = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();

		eventText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		addEvent.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sourceListModel.add(eventText.getText());
			}
		});

		addEvent.setText("Add");
		sourceScrollPanel.setName("SourceList");
		sourceScrollPanel.setViewportView(sourceList);
		destScrollPanel.setViewportView(destList);
		destScrollPanel.setName(ProgressReport.SPECAIL_EVENT);

		addButton.setText("Add >>     ");
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addListener(evt);
			}
		});
		

		removeButton.setText("Remove <<");
		removeButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeListener(evt);
			}
		});

		jLabel1.setText("Add Event");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(14, 14, 14)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(eventText, javax.swing.GroupLayout.PREFERRED_SIZE, 495,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(addEvent))
						.addComponent(sourceScrollPanel))
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(addButton)
						.addComponent(removeButton))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(destScrollPanel,
						javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
						.addComponent(destScrollPanel, javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(addEvent)
										.addComponent(eventText, javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(jLabel1))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup().addGap(200, 200, 200)
												.addComponent(addButton).addGap(30, 30, 30).addComponent(removeButton))
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(sourceScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 480,
														javax.swing.GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(104, Short.MAX_VALUE)));

		// panel.pack();
	}// </editor-fold>

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void addListener(java.awt.event.ActionEvent evt) {
		Object selected[] = sourceList.getSelectedValues();
		addDestinationElements(selected);
	}

	private void removeListener(java.awt.event.ActionEvent evt) {
			Object selected[] = destList.getSelectedValues();
		      clearDestinationSelected();


	}
	
	private void clearDestinationSelected() {
	    Object selected[] = destList.getSelectedValues();
	    for (int i = selected.length - 1; i >= 0; --i) {
	      destListModel.removeElement(selected[i]);
	    }
	    destList.getSelectionModel().clearSelection();
	  }

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.
		 * html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(SpecialEventUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(SpecialEventUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(SpecialEventUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(SpecialEventUI.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

	}

	// End of variables declaration

	private class AddListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Object selected[] = sourceList.getSelectedValues();
			addDestinationElements(selected);
		}

	}

	public void addDestinationElements(ListModel newValue) {
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel model, ListModel newValues) {
		int size = newValues.getSize();
		for (int i = 0; i < size; i++) {
			model.add(newValues.getElementAt(i));
		}
	}

	public void addDestinationElements(Object newValue[]) {
		fillListModel(destListModel, newValue);
	}

	private void fillListModel(SortedListModel model, Object newValues[]) {
		model.addAll(newValues);
	}

	private void clearSourceSelected() {
		Object selected[] = sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			sourceListModel.removeElement(selected[i]);
		}
		sourceList.getSelectionModel().clearSelection();
	}

	public void addSourceElements(ListModel newValue) {
		fillListModel(sourceListModel, newValue);
	}

	public void setSourceElements(ListModel newValue) {
		addSourceElements(newValue);
	}
}

