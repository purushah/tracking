package monitoring.monitoring;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

@SuppressWarnings("deprecation,rawtypes")
public class NewItemUI implements ActionListener {
	JPanel panel;
	private javax.swing.JButton addNote;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JList<String> sourceList;
	private javax.swing.JScrollPane scrollPane;
	private javax.swing.JTextField eventText;
	private SortedListModel sourceListModel;
	ProgressReport report;

	JPopupMenu popupMenu;
	JMenuItem removeMenu;

	public NewItemUI(ProgressReport report) {
		this.panel = new JPanel();
		this.report = report;
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {
		sourceListModel = new SortedListModel();
		eventText = new javax.swing.JTextField();
		addNote = new javax.swing.JButton();
		scrollPane = new javax.swing.JScrollPane();
		sourceList = new javax.swing.JList(sourceListModel);
		jLabel1 = new javax.swing.JLabel();

		eventText.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		addNote.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sourceListModel.add(eventText.getText());
				eventText.setText("");
			}
		});

		addNote.setText("Add");

		scrollPane.setViewportView(sourceList);
		jLabel1.setText("Add");
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
								.addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 10, Short.MAX_VALUE)
								.addComponent(eventText, javax.swing.GroupLayout.PREFERRED_SIZE, 625,
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
												.addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 210,
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
	 * @throws IOException
	 */
	public static void main(String args[]) throws IOException {
		JPanel mainPanel = new JPanel();

		mainPanel.setLayout(new GridLayout(18, 3));
		mainPanel.setPreferredSize(new Dimension(800, 300));
		JScrollPane scrollFrame = new JScrollPane(mainPanel);
		mainPanel.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800, 250));
		new NewItemUI(null);
		int value = JOptionPane.showConfirmDialog(null, mainPanel, null, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

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

	public List<String> getItem() throws IOException {

		panel.removeAll();
		panel.setLayout(new GridLayout(18, 3));
		panel.setPreferredSize(new Dimension(800, 300));
		JScrollPane scrollFrame = new JScrollPane(panel);
		panel.setAutoscrolls(true);
		scrollFrame.setPreferredSize(new Dimension(800, 250));
		initComponents();

		int input = JOptionPane.showConfirmDialog(null, panel, null, JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		if (input == JOptionPane.CANCEL_OPTION) {
			return null;
		}
		List<String> item = new ArrayList<String>();

		for (int i = 0; i < sourceListModel.getSize(); i++) {
			item.add(sourceListModel.getElementAt(i).toString());
		}
		return item;

	}

	public void actionPerformed(ActionEvent e) {
		try {
			List<String> items = getItem();
			if (items == null) {
				return;
			}
			JTabbedPane tabbedPane = (JTabbedPane) (report.getComponent(0));
			int selIndex = tabbedPane.getSelectedIndex();
			Util.updateFileList(tabbedPane.getComponent(selIndex).getName(), items);
			report.update((JComponent) tabbedPane.getComponent(selIndex));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
