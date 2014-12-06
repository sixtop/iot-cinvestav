package mx.cinvestav.gdl.iot.webpage.client;


import java.util.Comparator;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dao.Controller;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class EpWPController implements EntryPoint {
	private FormPanel form = new FormPanel();
	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddController = new Button("Add Controller");
	private HorizontalPanel buttonsPanel = new HorizontalPanel();
	
	private CellTable<Controller> tableController = new CellTable<Controller>();
	private ListDataProvider<Controller> dataProvider = new ListDataProvider<Controller>();
	private List<Controller> list;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	private List<Controller> CONTROLLERS;
	
	
	@Override
	public void onModuleLoad() {
		
		entityService.getEntity(new Controller(), 1, new AsyncCallback<List<Controller>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<Controller> result)
					{
						CONTROLLERS=result;
						  // Add the data to the data provider, which automatically pushes it to the
			  		    // widget.
			  	        List<Controller> list = dataProvider.getList();
			  		    for (Controller c : CONTROLLERS) {
			  		      list.add(c);
			  		    }

						
					}
				});				
		
		
		
	    TextColumn<Controller> idColumn = new TextColumn<Controller>() {
	      @Override
	      public String getValue(Controller c) {
	        return c.getId()+"";
	      }
	    };
	    idColumn.setSortable(true);
	    
	    TextColumn<Controller> nameColumn = new TextColumn<Controller>() {
	      @Override
	      public String getValue(Controller c) {
	        return c.getName();
	      }
	    };
	    nameColumn.setSortable(true);

	    TextColumn<Controller> descriptionColumn = new TextColumn<Controller>() {
		      @Override
		      public String getValue(Controller c) {
		        return c.getDescription();
		      }
		    };
		    
	    TextColumn<Controller> locationColumn = new TextColumn<Controller>() {
		      @Override
		      public String getValue(Controller c) {
		        return c.getLocation();
		      }
		    };
	     locationColumn.setSortable(true);
	    
	      Column<Controller, String> edit = new Column<Controller, String>(new ButtonCell()) {
	    	  @Override
	    	  public String getValue(Controller object) {
	    	    // The value to display in the button.
	    	    return object.getName();
	    	  }
	      };
	      
	      Column<Controller, String> delete = new Column<Controller, String>(new ButtonCell()) {
	    	  @Override
	    	  public String getValue(Controller object) {
	    	    return object.getName();
	    	  }
	      };
	          
		 
  		     tableController.addColumn(idColumn, "ID");
  			 tableController.addColumn(nameColumn, "Name");
  			 tableController.addColumn(descriptionColumn, "Description");
  			 tableController.addColumn(locationColumn, "Location");
  			 tableController.addColumn(edit,"Edit");
  			 tableController.addColumn(delete,"Delete");
		 
  			
  		    // Connect the table to the data provider.
  		    dataProvider.addDataDisplay(tableController);

  		  
  		    // Add a ColumnSortEvent.ListHandler to connect sorting to the
  		    // java.util.List.
  		    ListHandler<Controller> idSort = new ListHandler<Controller>(list);
  		    idSort.setComparator(idColumn,new Comparator<Controller>() {
  		          public int compare(Controller o1, Controller o2) {
  		            if (o1 == o2) {
  		              return 0;
  		            }
  		            
  		            if (o1 != null) {
  		              return (o2 != null) ? o1.getId()+"".compareTo(o2.getId()+"") : 1;
  		            }
  		            return -1;
  		          }
  		        });
  		    tableController.addColumnSortHandler(idSort);
  		    tableController.getColumnSortList().push(idColumn);

  		    
  		    ListHandler<Controller> nameSort = new ListHandler<Controller>(list);
  		    nameSort.setComparator(nameColumn,new Comparator<Controller>() {
  		          public int compare(Controller o1, Controller o2) {
  		            if (o1 == o2) {
  		              return 0;
  		            }
  		            
  		            if (o1 != null) {
  		              return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
  		            }
  		            return -1;
  		          }
  		        });
  		    tableController.addColumnSortHandler(nameSort);
  		   tableController.getColumnSortList().push(nameColumn);

  		  ListHandler<Controller> locationSort = new ListHandler<Controller>(list);
		    locationSort.setComparator(locationColumn,new Comparator<Controller>() {
		          public int compare(Controller o1, Controller o2) {
		            if (o1 == o2) {
		              return 0;
		            }
		            
		            if (o1 != null) {
		              return (o2 != null) ? o1.getLocation().compareTo(o2.getLocation()) : 1;
		            }
		            return -1;
		          }
		        });
		    tableController.addColumnSortHandler(locationSort);
		    tableController.getColumnSortList().push(locationColumn);

		    formPanel.add(btAddController);
		    formPanel.add(tableController);
		    form.add(formPanel);
		    
		    DecoratorPanel decoratorPanel = new DecoratorPanel();
		    decoratorPanel.add(form);
		    
		    RootPanel.get("formContainer").add(decoratorPanel);
		    
	    
	    btAddController.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addController.jsp");
	              }
	            });
	    
	    /////////////////////////////////////////// 
	    
		   
	    Button btEditController = new Button("Edit");
	    Button btDeleteController = new Button("Delete");
	    
	    buttonsPanel.add(btEditController);
	    buttonsPanel.add(btDeleteController);
	    btEditController.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            }
          });
	    
	    
	    btDeleteController.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            
            }
          });

	}
	
}
