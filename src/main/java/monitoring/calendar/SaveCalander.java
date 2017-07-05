package monitoring.calendar;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SaveCalander implements TableModelListener {

	TableModel tableModel;

	public SaveCalander(TableModel tableModel) {
		this.tableModel = tableModel;
	}

	public void tableChanged(TableModelEvent tme) {
		if (tme.getType() == TableModelEvent.UPDATE) {
			System.out.println("");
			System.out.println("Cell " + tme.getFirstRow() + ", " + tme.getColumn() + " changed. The new value: "
					+ tableModel.getValueAt(tme.getFirstRow(), tme.getColumn()));
		}
	}

}
