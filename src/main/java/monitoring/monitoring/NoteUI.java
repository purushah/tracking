package monitoring.monitoring;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import com.inet.jortho.FileUserDictionary;
import com.inet.jortho.SpellChecker;

public class NoteUI {
	JPanel panel;
	private javax.swing.JButton addNote;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JList<String> sourceList;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JEditorPane eventText;
	protected SortedListModel sourceListModel;

	JPopupMenu popupMenu;
	JMenuItem removeMenu;

	public NoteUI(JPanel panel) throws IOException {
		this.panel = panel;
		initComponents();
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	protected void initComponents() throws IOException {
		sourceListModel = new SortedListModel();
		eventText = new javax.swing.JEditorPane();
		addNote = new javax.swing.JButton();
		scrollPane = new javax.swing.JScrollPane();
		sourceList = new javax.swing.JList(sourceListModel);
		jLabel1 = new javax.swing.JLabel();
		SpellChecker.setUserDictionaryProvider(new FileUserDictionary());
		SpellChecker.registerDictionaries(null, null);

		SpellChecker.register(eventText);

		addNote.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sourceListModel.add(eventText.getText());
				eventText.setText("");
			}
		});

		addNote.setText("Add");

		scrollPane.setViewportView(sourceList);
		jLabel1.setText("Add " + getName());
		scrollPane.setName(ProgressReport.NOTE);

		popupMenu = new JPopupMenu();
		popupMenu.add(removeMenu = new JMenuItem("Remove"));

		sourceList.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent me) {
				// if right mouse button clicked (or me.isPopupTrigger())
				if (SwingUtilities.isRightMouseButton(me) && !sourceList.isSelectionEmpty()
						&& sourceList.locationToIndex(me.getPoint()) == sourceList.getSelectedIndex()) {
					popupMenu.show(sourceList, me.getX(), me.getY());
				}
			}
		});

		removeMenu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeListener(evt);
			}
		});
		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(panel);
		panel.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(14, 14, 14)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
								.addComponent(eventText, javax.swing.GroupLayout.PREFERRED_SIZE, 995,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(addNote))
						.addComponent(scrollPane))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addGroup(
						javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup().addGroup(layout
								.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(addNote)
								.addComponent(eventText, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel1))
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 480,
														javax.swing.GroupLayout.PREFERRED_SIZE)))))
				.addContainerGap(104, Short.MAX_VALUE)));

		// panel.pack();
	}// </editor-fold>

	private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
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

	private void fillListModel(SortedListModel model, ListModel newValues) {
		int size = newValues.getSize();
		for (int i = 0; i < size; i++) {
			model.add(newValues.getElementAt(i));
		}
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

	private void removeListener(java.awt.event.ActionEvent evt) {
		Object selected[] = sourceList.getSelectedValues();
		clearDestinationSelected();

	}

	private void clearDestinationSelected() {
		Object selected[] = sourceList.getSelectedValues();
		for (int i = selected.length - 1; i >= 0; --i) {
			sourceListModel.removeElement(selected[i]);
		}
		sourceList.getSelectionModel().clearSelection();
	}

	protected String getName() {
		return ProgressReport.NOTE;
	}

}
