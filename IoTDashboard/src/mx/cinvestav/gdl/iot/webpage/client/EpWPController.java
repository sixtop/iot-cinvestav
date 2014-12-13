package mx.cinvestav.gdl.iot.webpage.client;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import com.google.gwt.view.client.ListDataProvider;
import com.sun.java.swing.plaf.windows.resources.windows;

public class EpWPController implements EntryPoint {
	private DialogBox dbWait = new DialogBox();
	private int index;

	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddControllerDTO = new Button("Add Controller");
	
	private CellTable<ControllerDTO> tableControllerDTO = new CellTable<ControllerDTO>();
	private ListDataProvider<ControllerDTO> dataProvider = new ListDataProvider<ControllerDTO>();
	private List<ControllerDTO> list;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	private List<ControllerDTO> CONTROLLERS;
	
	private DialogBox dialogBox = new DialogBox();
	private Label lbDialogBox=new Label();
	private Button btYes = new Button("Yes");
	private Button btNo = new Button("No");
	private VerticalPanel dialogPanel=new VerticalPanel();
	
	
	@Override
	public void onModuleLoad() {
		
		showDialogWait();
		entityService.getEntity(new ControllerDTO(), null, new AsyncCallback<List<ControllerDTO>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<ControllerDTO> result)
					{
						dbWait.hide();
						CONTROLLERS=result;
					
			  		    dataProvider.addDataDisplay(tableControllerDTO);
			  		  
			  	        list = dataProvider.getList();
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
	        
	       ActionCell<ControllerDTO> editAction = new ActionCell<ControllerDTO>("Edit", new ActionCell.Delegate<ControllerDTO>() {
	            public void execute(ControllerDTO c){
	            	Window.Location.replace("addController.jsp?idController="+c.getId());
	            }
	        });

	       IdentityColumn<ControllerDTO> identityColumn = new IdentityColumn<>(editAction);
	        //Column<ControllerDTO, ActionCell<ControllerDTO>> editColumn = identityColumn;
	        
	        ActionCell<ControllerDTO> deleteAction = new ActionCell<ControllerDTO>("Delete", new ActionCell.Delegate<ControllerDTO>() {
	            public void execute(ControllerDTO c){
	            	index=c.getId();
	            	
	            	dialogBox.setAnimationEnabled(true);
					dialogBox.setGlassEnabled(true);
				    dialogBox.center();

				    dialogBox.setText("Warning");

				    lbDialogBox.setText("Are you sure to delete the controller? ");
				    dialogPanel.add(lbDialogBox);
				    dialogPanel.setCellHorizontalAlignment(lbDialogBox, HasHorizontalAlignment.ALIGN_CENTER);

				    HorizontalPanel buttonPanel=new HorizontalPanel();
				    buttonPanel.add(btYes);
				    buttonPanel.add(btNo);
				    
				    dialogPanel.add(buttonPanel);
				    dialogPanel.setCellHorizontalAlignment(buttonPanel, HasHorizontalAlignment.ALIGN_CENTER);
				    
				    dialogBox.add(dialogPanel);
				    
				    dialogBox.show();
				    
	            }
	        });

	        Column<ControllerDTO, ActionCell<ControllerDTO>> deleteColumn = (new IdentityColumn(deleteAction));
	
  		     tableControllerDTO.addColumn(idColumn, "ID");
  			 tableControllerDTO.addColumn(nameColumn, "Name");
  			 tableControllerDTO.addColumn(descriptionColumn, "Description");
  			 tableControllerDTO.addColumn(locationColumn, "Location");
  			 tableControllerDTO.addColumn(identityColumn,"Edit");
  			 tableControllerDTO.addColumn(deleteColumn,"Delete");
  			
  		    ListHandler<ControllerDTO> sortHandler = new ListHandler<ControllerDTO>(dataProvider.getList());
  		    tableControllerDTO.addColumnSortHandler(sortHandler);
  		
  		  sortHandler.setComparator(idColumn,new Comparator<ControllerDTO>() {
  			  @Override
  		          public int compare(ControllerDTO o1, ControllerDTO o2) {
  		              return o1.getId()-o2.getId();
  		          }
  		        });
	
 		    tableControllerDTO.getColumnSortList().push(idColumn);
  		
  		sortHandler.setComparator(nameColumn,new Comparator<ControllerDTO>() {
  			@Override
  		          public int compare(ControllerDTO o1, ControllerDTO o2) {
  		              return  o1.getName().compareTo(o2.getName());
  		          }
  		        });
  	
  		sortHandler.setComparator(locationColumn,new Comparator<ControllerDTO>() {
  			@Override
		          public int compare(ControllerDTO o1, ControllerDTO o2) {
		              return o1.getLocation().compareTo(o2.getLocation());
  			}
		        });
		 
				 
		  SimplePager pager = new SimplePager(TextLocation.CENTER, true, true);
		  pager.setDisplay(tableControllerDTO);
		  pager.setPageSize(10);  
		    
		    formPanel.add(btAddControllerDTO);
		    formPanel.add(tableControllerDTO);
		    formPanel.setCellHorizontalAlignment(tableControllerDTO, HasHorizontalAlignment.ALIGN_CENTER);
		    formPanel.add(pager);
		    formPanel.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);
		    
		 
		    
		    RootPanel.get("formContainer").add(formPanel);
		    
	    
		    	btAddControllerDTO.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addController.jsp");
	              }
	            });
		    	
		    	btYes.addClickHandler(new ClickHandler() {
		              public void onClick(ClickEvent event) {
		            	  entityService.deleteEntity(new ControllerDTO(), index, new AsyncCallback<Void>()
						{

							@Override
							public void onFailure(Throwable caught)
							{
								Window.alert("no deletion!" + caught.getMessage());
								
							}

							@Override
							public void onSuccess(Void result)
							{
								Window.alert("Deletion ok");								
							}
						});
		            	Window.alert("SE ELIMINA "+index);
		            	dialogBox.hide();
		            	Window.Location.reload();
		            	
		              }
		            });
		    
		    btNo.addClickHandler(new ClickHandler() {
	            public void onClick(ClickEvent event) {
	              dialogBox.hide();
	            }
	          });
	}
	
public void showDialogWait(){
		
		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);
		dbWait.center();

	    VerticalPanel dialogContents = new VerticalPanel();
	    
	    dialogContents.setSpacing(4);
	    
	    Image image = new Image();
	    
	    image.setUrl(GWT.getHostPageBaseURL()+"images/loading2.gif");
	    
	    
	    dialogContents.add(image);
	    dialogContents.setCellHorizontalAlignment(image, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    dbWait.setWidget(dialogContents);
	    dbWait.show();
		
	}
}
