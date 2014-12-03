package mx.cinvestav.gdl.iot.webpage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpWPSensor implements EntryPoint {
	private FormPanel form = new FormPanel();
	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddController = new Button("Add controller");
	
	private FlexTable tableController = new FlexTable();
	private HorizontalPanel buttonsPanel = new HorizontalPanel();
	
	@Override
	public void onModuleLoad() {
		
		tableController.setText(0, 0, "ID");
		tableController.setText(0, 1, "Name");
		tableController.setText(0, 2, "Description");
		tableController.setText(0, 3, "Type");
		tableController.setText(0, 4, "Status");
		tableController.setText(0, 5, "Measure unit");
		tableController.setText(0, 6, "Unit");
		tableController.setText(0, 7, "   ");
		
		tableController.getCellFormatter().addStyleName(0, 0, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 1, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 2, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 3, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 4, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 5, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 6, "headerTableProperty");
		tableController.getCellFormatter().addStyleName(0, 7, "headerTableProperty");
		
		tableController.addStyleName("tableProperty");
		tableController.setCellPadding(3);
		
		formPanel.add(btAddController);
	    formPanel.add(tableController);
	  
	    form.setWidget(formPanel);
	    
	    DecoratorPanel decoratorPanel = new DecoratorPanel();
	    decoratorPanel.add(form);
	    
	    RootPanel.get("formContainer").add(decoratorPanel);
	    
	    btAddController.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addSensor.jsp");
	              }
	            });
	    
	    /////////////////////////////////////////// 
	    	    Button btEditSensor = new Button("Edit");
	    Button btDeleteSensor = new Button("Delete");
	    
	    buttonsPanel.add(btEditSensor);
	    buttonsPanel.add(btDeleteSensor);
	    btEditSensor.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            }
          });
	    
	    
	    btDeleteSensor.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            }
          });

	    
	}
}
