package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Comparator;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingPropertyDTO;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.IdentityColumn;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

public class EpWPSmartThing implements EntryPoint {
	private DialogBox dbWait = new DialogBox();
	private int index;
	
	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddSmartThingDTO = new Button("Add SmartThing");
	
	private CellTable<SmartThingDTO> tableSmartThingDTO = new CellTable<SmartThingDTO>();
	private ListDataProvider<SmartThingDTO> dataProvider = new ListDataProvider<SmartThingDTO>();
	private List<SmartThingDTO> list;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	private List<SmartThingDTO> SMARTTHINGS;
	
	private DialogBox dialogBox = new DialogBox();
	private Label lbDialogBox=new Label();
	private Button btYes = new Button("Yes");
	private Button btNo = new Button("No");
	private VerticalPanel dialogPanel=new VerticalPanel();
	@Override
	public void onModuleLoad() {
		showDialogWait();
		entityService.getEntity(new SmartThingDTO(), null, new AsyncCallback<List<SmartThingDTO>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SmartThingDTO> result)
					{
						dbWait.hide();
						SMARTTHINGS=result;
					
			  		    dataProvider.addDataDisplay(tableSmartThingDTO);
			  		  
			  	        list = dataProvider.getList();
			  		    for (SmartThingDTO c : SMARTTHINGS) {
			  		      list.add(c);
			  		    }

						
					}
				});				
		
			
	   TextColumn<SmartThingDTO> idColumn = new TextColumn<SmartThingDTO>() {
	      @Override
	      public String getValue(SmartThingDTO c) {
	        return c.getId()+"";
	      }
	    };
	    idColumn.setSortable(true);
	    
	   
	    TextColumn<SmartThingDTO> nameColumn = new TextColumn<SmartThingDTO>() {
	      @Override
	      public String getValue(SmartThingDTO c) {
	        return c.getName();
	      }
	    };
	    nameColumn.setSortable(true);

	    TextColumn<SmartThingDTO> descriptionColumn = new TextColumn<SmartThingDTO>() {
		      @Override
		      public String getValue(SmartThingDTO c) {
		        return c.getDescription();
		      }
		    };
		    
	   
	       ActionCell<SmartThingDTO> editAction = new ActionCell<SmartThingDTO>("Edit", new ActionCell.Delegate<SmartThingDTO>() {
	            public void execute(SmartThingDTO c){
	            	Window.Location.replace("addSmartThing.jsp?idSmartThing="+c.getId());
	            }
	        });

	        Column<SmartThingDTO, ActionCell<SmartThingDTO>> editColumn = (new IdentityColumn(editAction));
	        
	        ActionCell<SmartThingDTO> deleteAction = new ActionCell<SmartThingDTO>("Delete", new ActionCell.Delegate<SmartThingDTO>() {
	            public void execute(SmartThingDTO c){
	            	index=c.getId();
	            	
	            	dialogBox.setAnimationEnabled(true);
					dialogBox.setGlassEnabled(true);
				    dialogBox.center();

				    dialogBox.setText("Warning");

				    lbDialogBox.setText("Are you sure to delete the SmartThing? ");
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

	        Column<SmartThingDTO, ActionCell<SmartThingDTO>> deleteColumn = (new IdentityColumn(deleteAction));
	
  		     tableSmartThingDTO.addColumn(idColumn, "ID");
  			 tableSmartThingDTO.addColumn(nameColumn, "Name");
  			 tableSmartThingDTO.addColumn(descriptionColumn, "Description");
  			 tableSmartThingDTO.addColumn(editColumn,"Edit");
  			 tableSmartThingDTO.addColumn(deleteColumn,"Delete");
  			
  			 
  		    ListHandler<SmartThingDTO> sortHandler = new ListHandler<SmartThingDTO>(dataProvider.getList());
  		    tableSmartThingDTO.addColumnSortHandler(sortHandler);
  		
  		  sortHandler.setComparator(idColumn,new Comparator<SmartThingDTO>() {
  			  @Override
  		          public int compare(SmartThingDTO o1, SmartThingDTO o2) {
  		              return o1.getId()-o2.getId();
  		          }
  		        });
	
 		    tableSmartThingDTO.getColumnSortList().push(idColumn);
  		
  		sortHandler.setComparator(nameColumn,new Comparator<SmartThingDTO>() {
  			@Override
  		          public int compare(SmartThingDTO o1, SmartThingDTO o2) {
  		              return  o1.getName().compareTo(o2.getName());
  		          }
  		        });
  	
		  SimplePager pager = new SimplePager(TextLocation.CENTER, true, true);
		  pager.setDisplay(tableSmartThingDTO);
		  pager.setPageSize(10);  
		    
		    formPanel.add(btAddSmartThingDTO);
		    formPanel.add(tableSmartThingDTO);
		    formPanel.setCellHorizontalAlignment(tableSmartThingDTO, HasHorizontalAlignment.ALIGN_CENTER);
		    formPanel.add(pager);
		    formPanel.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);
		    
		
		    RootPanel.get("formContainer").add(formPanel);
		    
	    
		    	btAddSmartThingDTO.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addSmartThing.jsp");
	              }
	            });
		    	
		    	btYes.addClickHandler(new ClickHandler() {
		    		 public void onClick(ClickEvent event) {
		            	  entityService.deleteEntity(new SmartThingDTO(), index, new AsyncCallback<Void>()
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