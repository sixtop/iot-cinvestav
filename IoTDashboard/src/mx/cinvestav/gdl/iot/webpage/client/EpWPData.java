package mx.cinvestav.gdl.iot.webpage.client;

import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class EpWPData implements EntryPoint {
	private VerticalPanel formPanel=new VerticalPanel();
	
	private FlexTable tableData=new FlexTable();
	
	private ListBox lbController=new ListBox();
	private ListBox lbIdController=new ListBox();
	
	private ListBox lbSmartThing=new ListBox();
	private ListBox lbIdSmartThing=new ListBox();
	
	private ListBox lbSensor=new ListBox();
	private ListBox lbIdSensor=new ListBox();
	
	
	private DateBox dbFrom = new DateBox();
	private DateBox dbTo = new DateBox();
	
	private Button btGenerate = new Button("Generate");
	private List<ControllerDTO> CONTROLLERS;
	private List<SensorDTO> SENSORS;
	
	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);
	
	@Override
	public void onModuleLoad() {
		
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
						CONTROLLERS=result;
					
			  		    for (ControllerDTO c : CONTROLLERS) {
			  		      lbController.addItem(c.getName());
			  		      lbIdController.addItem(c.getId()+"");
			  		    }
					}
				});				
		
		
		
		tableData.setText(0,0,"Controller: ");
		tableData.setWidget(0,1,lbController);
		
		tableData.setText(1,0,"Sensor: ");
		tableData.setWidget(1,1,lbSensor);
		
		tableData.setText(2,0,"From: ");
		tableData.setWidget(2,1,dbFrom);
		
		tableData.setText(3,0,"To: ");
		tableData.setWidget(3,1,dbTo);
		
		formPanel.add(tableData);
		formPanel.add(btGenerate);
	    formPanel.setCellHorizontalAlignment(btGenerate, HasHorizontalAlignment.ALIGN_CENTER);
	    RootPanel.get("formContainer").add(formPanel);
	    
	    dbFrom.getDatePicker().setYearArrowsVisible(true);
	    dbTo.getDatePicker().setYearArrowsVisible(true);

   /* DateTimeFormat dateFormat = DateTimeFormat.getFullDateFormat();
    dbFrom.setFormat(new DateBox.DefaultFormat(dateFormat));
    dbTo.setFormat(new DateBox.DefaultFormat(dateFormat));
    */
	    lbController.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				
			
				
			}
		});
	  
	  
	    btGenerate.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                Window.alert("GENERA");
              }
            });
	    
	    
	}
}