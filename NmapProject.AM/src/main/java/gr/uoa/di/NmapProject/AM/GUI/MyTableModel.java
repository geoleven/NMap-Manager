package gr.uoa.di.NmapProject.AM.GUI;

import java.util.LinkedList;

import javax.swing.table.AbstractTableModel;

import gr.uoa.di.NmapProject.AM.DB.SAInfoStatus;


/**
 * Custom table model for the status monitor.
 * @author George
 *
 */
public class MyTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -2615206236252688576L;

	private static String[] columnNames = { "Status", "ID", "Device Name", "Interface IP", "Interface MacAddr",
			"OS Version", "NMap Version", "Is accepted", "Union Hash" };

	private LinkedList<SAInfoStatus> list;

	// public MyTableModel(LinkedList<SAInfoStatus> l) {
	// list = new LinkedList<SAInfoStatus>();
	// if (l != null) {
	// for (int counter = 0; counter < l.size(); counter++) {
	// list.add(l.get(counter));
	// }
	// }
	// }

	/**
	 * Creates a custom table model with a list in it.
	 * @param l The list.
	 */
	public MyTableModel(LinkedList<SAInfoStatus> l) {
		list = l;
	}

	// public void changeList(LinkedList<SAInfoStatus> l) {
	// if (l != null) {
	// while(!list.isEmpty())
	// list.removeLast();
	// for (int counter = 0; counter < l.size(); counter++) {
	// list.add(l.get(counter));
	//
	// }
	// }
	// fireTableDataChanged();
	// }

	/**
	 * Removes the old list with a totally new one.
	 * @param l The new list.
	 */
	public void changeList(LinkedList<SAInfoStatus> l) {
		list = l;
		fireTableDataChanged();
	}

	/**
	 * Adds only an element to the end of the already existing list.
	 * @param e The element to add.
	 */
	public void addElement(SAInfoStatus e) {
		// Adds the element in the last position in the list
		list.add(e);
		fireTableRowsInserted(list.size() - 1, list.size() - 1);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return (Object) (list.get(rowIndex).status ? "Online" : "Offline");
		case 1:
			return (Object) list.get(rowIndex).id;
		case 2:
			return (Object) list.get(rowIndex).deviceName;
		case 3:
			return (Object) list.get(rowIndex).interfaceIP;
		case 4:
			return (Object) list.get(rowIndex).interfaceMacAddr;
		case 5:
			return (Object) list.get(rowIndex).osVersion;
		case 6:
			return (Object) list.get(rowIndex).nMapVersion;
		case 7:
			return (Object) (list.get(rowIndex).isAccepted ? "Yes" : "No");
		case 8:
			return (Object) list.get(rowIndex).unionHash;
		}
		return null;
	}
}
