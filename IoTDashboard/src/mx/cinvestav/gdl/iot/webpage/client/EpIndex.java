package mx.cinvestav.gdl.iot.webpage.client;



import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;



public class EpIndex implements EntryPoint {
	 // A simple data type that represents a contact.
	  private static class Contact {
	    private final String address;
	    private final String name;

	    public Contact(String name, String address) {
	      this.name = name;
	      this.address = address;
	    }
	  }

	  // The list of data to display.
	  private static List<Contact> CONTACTS = Arrays.asList(new Contact("John",
	      "123 Fourth Road"), new Contact("Mary", "222 Lancer Lane"), new Contact(
	      "Zander", "94 Road Street"));

	  public void onModuleLoad() {

	    // Create a CellTable.
	    CellTable<Contact> table = new CellTable<Contact>();

	    // Create name column.
	    TextColumn<Contact> nameColumn = new TextColumn<Contact>() {
	      @Override
	      public String getValue(Contact contact) {
	        return contact.name;
	      }
	    };

	    // Make the name column sortable.
	    nameColumn.setSortable(true);

	    // Create address column.
	    TextColumn<Contact> addressColumn = new TextColumn<Contact>() {
	      @Override
	      public String getValue(Contact contact) {
	        return contact.address;
	      }
	    };

	    addressColumn.setSortable(true);
	    // Add the columns.
	    table.addColumn(nameColumn, "Name");
	    table.addColumn(addressColumn, "Address");

	    // Create a data provider.
	    ListDataProvider<Contact> dataProvider = new ListDataProvider<Contact>();

	    // Connect the table to the data provider.
	    dataProvider.addDataDisplay(table);

	    // Add the data to the data provider, which automatically pushes it to the
	    // widget.
	    List<Contact> list = dataProvider.getList();
	    for (Contact contact : CONTACTS) {
	      list.add(contact);
	    }

	    // Add a ColumnSortEvent.ListHandler to connect sorting to the
	    // java.util.List.
	    ListHandler<Contact> columnSortHandler = new ListHandler<Contact>(list);
	    columnSortHandler.setComparator(nameColumn,
	        new Comparator<Contact>() {
	          public int compare(Contact o1, Contact o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.name.compareTo(o2.name) : 1;
	            }
	            return -1;
	          }
	        });
	    table.addColumnSortHandler(columnSortHandler);

	    // We know that the data is sorted alphabetically by default.
	    table.getColumnSortList().push(nameColumn);

	    
	    // Add a ColumnSortEvent.ListHandler to connect sorting to the
	    // java.util.List.
	    ListHandler<Contact> columnSortHandler2 = new ListHandler<Contact>(list);
	    columnSortHandler2.setComparator(addressColumn,
	        new Comparator<Contact>() {
	          public int compare(Contact o1, Contact o2) {
	            if (o1 == o2) {
	              return 0;
	            }

	            // Compare the name columns.
	            if (o1 != null) {
	              return (o2 != null) ? o1.address.compareTo(o2.address) : 1;
	            }
	            return -1;
	          }
	        });
	   table.addColumnSortHandler(columnSortHandler2);

	    // We know that the data is sorted alphabetically by default.
	//    table.getColumnSortList().push(nameColumn);
	     
	    
	    // Add it to the root panel.
	    RootPanel.get().add(table);
	  }
	}