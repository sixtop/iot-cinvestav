package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Comparator;
import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;

import com.google.gwt.cell.client.ActionCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
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
import com.google.gwt.view.client.ListDataProvider;

public class EpWPSensor implements EntryPoint {
	private DialogBox dbWait = new DialogBox();
	private int index;
	
	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddSensorDTO = new Button("Add Sensor");
	
	private CellTable<SensorDTO> tableSensorDTO = new CellTable<SensorDTO>();
	private ListDataProvider<SensorDTO> dataProvider = new ListDataProvider<SensorDTO>();
	private List<SensorDTO> list;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	private List<SensorDTO> SENSORS;
	
	private DialogBox dialogBox = new DialogBox();
	private Label lbDialogBox=new Label();
	private Button btYes = new Button("Yes");
	private Button btNo = new Button("No");
	private VerticalPanel dialogPanel=new VerticalPanel();
	@Override
	public void onModuleLoad() {
		showDialogWait();
		entityService.getEntity(new SensorDTO(), null, new AsyncCallback<List<SensorDTO>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SensorDTO> result)
					{
						dbWait.hide();
						SENSORS=result;
					
			  		    dataProvider.addDataDisplay(tableSensorDTO);
			  		  
			  	        list = dataProvider.getList();
			  		    for (SensorDTO c : SENSORS) {
			  		      list.add(c);
			  		    }

						
					}
				});				
		
			
	   TextColumn<SensorDTO> idColumn = new TextColumn<SensorDTO>() {
	      @Override
	      public String getValue(SensorDTO c) {
	        return c.getId()+"";
	      }
	    };
	    idColumn.setSortable(true);
	    
	   
	    TextColumn<SensorDTO> nameColumn = new TextColumn<SensorDTO>() {
	      @Override
	      public String getValue(SensorDTO c) {
	        return c.getName();
	      }
	    };
	    nameColumn.setSortable(true);

	    TextColumn<SensorDTO> descriptionColumn = new TextColumn<SensorDTO>() {
		      @Override
		      public String getValue(SensorDTO c) {
		        return c.getDescription();
		      }
		    };
		    
	    TextColumn<SensorDTO> typeColumn = new TextColumn<SensorDTO>() {
		      @Override
		      public String getValue(SensorDTO c) {
		        return c.getSensor_type();
		      }
		    };
	     typeColumn.setSortable(true);
	        
	     TextColumn<SensorDTO> unitColumn = new TextColumn<SensorDTO>() {
		      @Override
		      public String getValue(SensorDTO c) {
		        return c.getUnit();
		      }
		    };
	     unitColumn.setSortable(true);
	       
	     TextColumn<SensorDTO> altitudeColumn = new TextColumn<SensorDTO>() {
		      @Override
		      public String getValue(SensorDTO c) {
		        return c.getAltitude()+"";
		      }
		    };
	     altitudeColumn.setSortable(true);
	     
	     TextColumn<SensorDTO> latitudeColumn = new TextColumn<SensorDTO>() {
		      @Override
		      public String getValue(SensorDTO c) {
		        return c.getLatitude()+"";
		      }
		    };
	     latitudeColumn.setSortable(true);

	     TextColumn<SensorDTO> longitudeColumn = new TextColumn<SensorDTO>() {
		      @Override
		      public String getValue(SensorDTO c) {
		        return c.getLongitude()+"";
		      }
		    };
	     longitudeColumn.setSortable(true);
	     
	     
	       ActionCell<SensorDTO> editAction = new ActionCell<SensorDTO>("Edit", new ActionCell.Delegate<SensorDTO>() {
	            public void execute(SensorDTO c){
	            	Window.Location.replace("addSensor.jsp?idSensor="+c.getId());
	            }
	        });

	       IdentityColumn<SensorDTO> editColumn = new IdentityColumn<>(editAction);
	        
	        ActionCell<SensorDTO> deleteAction = new ActionCell<SensorDTO>("Delete", new ActionCell.Delegate<SensorDTO>() {
	            public void execute(SensorDTO c){
	            	index=c.getId();
	            	
	            	dialogBox.setAnimationEnabled(true);
					dialogBox.setGlassEnabled(true);
				    dialogBox.center();

				    dialogBox.setText("Warning");

				    lbDialogBox.setText("Are you sure to delete the Sensor? ");
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

	        IdentityColumn<SensorDTO> deleteColumn = new IdentityColumn<>(deleteAction);
	
  		     tableSensorDTO.addColumn(idColumn, "ID");
  			 tableSensorDTO.addColumn(nameColumn, "Name");
  			 tableSensorDTO.addColumn(descriptionColumn, "Description");
  			 tableSensorDTO.addColumn(typeColumn, "Type");
  			tableSensorDTO.addColumn(unitColumn, "Unit");
  			tableSensorDTO.addColumn(altitudeColumn, "Altitude");
  			tableSensorDTO.addColumn(latitudeColumn, "Latitude");
  			tableSensorDTO.addColumn(longitudeColumn, "Longitude");
  			 tableSensorDTO.addColumn(editColumn,"Edit");
  			 tableSensorDTO.addColumn(deleteColumn,"Delete");
  			
  		    ListHandler<SensorDTO> sortHandler = new ListHandler<SensorDTO>(dataProvider.getList());
  		    tableSensorDTO.addColumnSortHandler(sortHandler);
  		
  		  sortHandler.setComparator(idColumn,new Comparator<SensorDTO>() {
  			  @Override
  		          public int compare(SensorDTO o1, SensorDTO o2) {
  		              return o1.getId()-o2.getId();
  		          }
  		        });
	
 		    tableSensorDTO.getColumnSortList().push(idColumn);
  		
  		sortHandler.setComparator(nameColumn,new Comparator<SensorDTO>() {
  			@Override
  		          public int compare(SensorDTO o1, SensorDTO o2) {
  		              return  o1.getName().compareTo(o2.getName());
  		          }
  		        });
  	
  		sortHandler.setComparator(typeColumn,new Comparator<SensorDTO>() {
  			@Override
		          public int compare(SensorDTO o1, SensorDTO o2) {
		              return o1.getSensor_type().compareTo(o2.getSensor_type());
  			}
		        });
	
  		sortHandler.setComparator(unitColumn,new Comparator<SensorDTO>() {
  			@Override
		          public int compare(SensorDTO o1, SensorDTO o2) {
		              return o1.getUnit().compareTo(o2.getUnit());
  			}
		        });
  		
  		sortHandler.setComparator(altitudeColumn,new Comparator<SensorDTO>() {
  			@Override
		          public int compare(SensorDTO o1, SensorDTO o2) {
		              return (int)(o1.getAltitude()-o2.getAltitude());
  			}
		        });
  		
  		
  		sortHandler.setComparator(latitudeColumn,new Comparator<SensorDTO>() {
  			@Override
		          public int compare(SensorDTO o1, SensorDTO o2) {
		              return (int)(o1.getLatitude()-o2.getLatitude());
  			}
		        });
	
  		sortHandler.setComparator(longitudeColumn,new Comparator<SensorDTO>() {
  			@Override
		          public int compare(SensorDTO o1, SensorDTO o2) {
		              return (int)(o1.getLongitude()-o2.getLongitude());
  			}
		        });
  		
  		
		  SimplePager pager = new SimplePager(TextLocation.CENTER, true, true);
		  pager.setDisplay(tableSensorDTO);
		  pager.setPageSize(10);  
		    
		    formPanel.add(btAddSensorDTO);
		    formPanel.add(tableSensorDTO);
		    formPanel.setCellHorizontalAlignment(tableSensorDTO, HasHorizontalAlignment.ALIGN_CENTER);
		    formPanel.add(pager);
		    formPanel.setCellHorizontalAlignment(pager, HasHorizontalAlignment.ALIGN_CENTER);
		    
		  
		    RootPanel.get("formContainer").add(formPanel);
		    
	    
		    	btAddSensorDTO.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addSensor.jsp");
	              }
	            });
		    	
		    	btYes.addClickHandler(new ClickHandler() {
		    		 public void onClick(ClickEvent event) {
		            	entityService.deleteEntity(new SensorDTO(), index, new AsyncCallback<Void>()
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
