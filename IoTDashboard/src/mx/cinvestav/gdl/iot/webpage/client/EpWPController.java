package mx.cinvestav.gdl.iot.webpage.client;


import java.util.Comparator;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;

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
	
	private Button btAddControllerDTO = new Button("Add ControllerDTO");
	private HorizontalPanel buttonsPanel = new HorizontalPanel();
	
	private CellTable<ControllerDTO> tableControllerDTO = new CellTable<ControllerDTO>();
	private ListDataProvider<ControllerDTO> dataProvider = new ListDataProvider<ControllerDTO>();
	private List<ControllerDTO> list;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	private List<ControllerDTO> CONTROLLERS;
	
	
	@Override
	public void onModuleLoad() {
		
		entityService.getEntity(new ControllerDTO(), 1, new AsyncCallback<List<ControllerDTO>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<ControllerDTO> result)
					{
						CONTROLLERS=result;
						  // Add the data to the data provider, which automatically pushes it to the
			  		    // widget.
			  	        List<ControllerDTO> list = dataProvider.getList();
			  		    for (ControllerDTO c : CONTROLLERS) {
			  		      list.add(c);
			  		    }

						
					}
				});				
		
		
		
	    TextColumn<ControllerDTO> idColumn = new TextColumn<ControllerDTO>() {
	      @Override
	      public String getValue(ControllerDTO c) {
	        return c.getId()+"";
	      }
	    };
	    idColumn.setSortable(true);
	    
	    TextColumn<ControllerDTO> nameColumn = new TextColumn<ControllerDTO>() {
	      @Override
	      public String getValue(ControllerDTO c) {
	        return c.getName();
	      }
	    };
	    nameColumn.setSortable(true);

	    TextColumn<ControllerDTO> descriptionColumn = new TextColumn<ControllerDTO>() {
		      @Override
		      public String getValue(ControllerDTO c) {
		        return c.getDescription();
		      }
		    };
		    
	    TextColumn<ControllerDTO> locationColumn = new TextColumn<ControllerDTO>() {
		      @Override
		      public String getValue(ControllerDTO c) {
		        return c.getLocation();
		      }
		    };
	     locationColumn.setSortable(true);
	    
	      Column<ControllerDTO, String> edit = new Column<ControllerDTO, String>(new ButtonCell()) {
	    	  @Override
	    	  public String getValue(ControllerDTO object) {
	    	    // The value to display in the button.
	    	    return object.getName();
	    	  }
	      };
	      
	      Column<ControllerDTO, String> delete = new Column<ControllerDTO, String>(new ButtonCell()) {
	    	  @Override
	    	  public String getValue(ControllerDTO object) {
	    	    return object.getName();
	    	  }
	      };
	          
		 
  		     tableControllerDTO.addColumn(idColumn, "ID");
  			 tableControllerDTO.addColumn(nameColumn, "Name");
  			 tableControllerDTO.addColumn(descriptionColumn, "Description");
  			 tableControllerDTO.addColumn(locationColumn, "Location");
  			 tableControllerDTO.addColumn(edit,"Edit");
  			 tableControllerDTO.addColumn(delete,"Delete");
		 
  			
  		    // Connect the table to the data provider.
  		    dataProvider.addDataDisplay(tableControllerDTO);

  		  
  		    // Add a ColumnSortEvent.ListHandler to connect sorting to the
  		    // java.util.List.
  		    ListHandler<ControllerDTO> idSort = new ListHandler<ControllerDTO>(list);
  		    idSort.setComparator(idColumn,new Comparator<ControllerDTO>() {
  		          public int compare(ControllerDTO o1, ControllerDTO o2) {
  		            if (o1 == o2) {
  		              return 0;
  		            }
  		            
  		            if (o1 != null) {
  		              return (o2 != null) ? o1.getId()+"".compareTo(o2.getId()+"") : 1;
  		            }
  		            return -1;
  		          }
  		        });
  		    tableControllerDTO.addColumnSortHandler(idSort);
  		    tableControllerDTO.getColumnSortList().push(idColumn);

  		    
  		    ListHandler<ControllerDTO> nameSort = new ListHandler<ControllerDTO>(list);
  		    nameSort.setComparator(nameColumn,new Comparator<ControllerDTO>() {
  		          public int compare(ControllerDTO o1, ControllerDTO o2) {
  		            if (o1 == o2) {
  		              return 0;
  		            }
  		            
  		            if (o1 != null) {
  		              return (o2 != null) ? o1.getName().compareTo(o2.getName()) : 1;
  		            }
  		            return -1;
  		          }
  		        });
  		    tableControllerDTO.addColumnSortHandler(nameSort);
  		   tableControllerDTO.getColumnSortList().push(nameColumn);

  		  ListHandler<ControllerDTO> locationSort = new ListHandler<ControllerDTO>(list);
		    locationSort.setComparator(locationColumn,new Comparator<ControllerDTO>() {
		          public int compare(ControllerDTO o1, ControllerDTO o2) {
		            if (o1 == o2) {
		              return 0;
		            }
		            
		            if (o1 != null) {
		              return (o2 != null) ? o1.getLocation().compareTo(o2.getLocation()) : 1;
		            }
		            return -1;
		          }
		        });
		    tableControllerDTO.addColumnSortHandler(locationSort);
		    tableControllerDTO.getColumnSortList().push(locationColumn);

		    formPanel.add(btAddControllerDTO);
		    formPanel.add(tableControllerDTO);
		    form.add(formPanel);
		    
		    DecoratorPanel decoratorPanel = new DecoratorPanel();
		    decoratorPanel.add(form);
		    
		    RootPanel.get("formContainer").add(decoratorPanel);
		    
	    
	    btAddControllerDTO.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addControllerDTO.jsp");
	              }
	            });
	    
	    /////////////////////////////////////////// 
	    
		   
	    Button btEditControllerDTO = new Button("Edit");
	    Button btDeleteControllerDTO = new Button("Delete");
	    
	    buttonsPanel.add(btEditControllerDTO);
	    buttonsPanel.add(btDeleteControllerDTO);
	    btEditControllerDTO.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            }
          });
	    
	    
	    btDeleteControllerDTO.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            
            }
          });

	}
	
}
