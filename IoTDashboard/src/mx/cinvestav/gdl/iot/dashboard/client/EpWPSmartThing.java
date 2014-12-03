package mx.cinvestav.gdl.iot.dashboard.client;

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

public class EpWPSmartThing implements EntryPoint {

	private FormPanel form = new FormPanel();
	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btAddSmartThing = new Button("Add SmartThing");
	
	private FlexTable tableSmartThing = new FlexTable();
	private HorizontalPanel buttonsPanel = new HorizontalPanel();
	
	@Override
	public void onModuleLoad() {
		
		tableSmartThing.setText(0, 0, "ID");
		tableSmartThing.setText(0, 1, "Name");
		tableSmartThing.setText(0, 2, "Description");
		
		tableSmartThing.getCellFormatter().addStyleName(0, 0, "headerTableProperty");
		tableSmartThing.getCellFormatter().addStyleName(0, 1, "headerTableProperty");
		tableSmartThing.getCellFormatter().addStyleName(0, 2, "headerTableProperty");
		
		tableSmartThing.addStyleName("tableProperty");
		tableSmartThing.setCellPadding(3);
		
		formPanel.add(btAddSmartThing);
	    formPanel.add(tableSmartThing);
	  
	    form.setWidget(formPanel);
	    
	    DecoratorPanel decoratorPanel = new DecoratorPanel();
	    decoratorPanel.add(form);
	    
	    RootPanel.get("formContainer").add(decoratorPanel);
	    
	    btAddSmartThing.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                Window.Location.replace("addSmartThing.jsp");
	              }
	            });
	    
	    /////////////////////////////////////////// 
	    
	   
	    Button btEditSmartThing = new Button("Edit");
	    Button btDeleteSmartThing = new Button("Delete");
	    
	    buttonsPanel.add(btEditSmartThing);
	    buttonsPanel.add(btDeleteSmartThing);
	    btEditSmartThing.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            }
          });
	    
	    
	    btDeleteSmartThing.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
              
            }
          });

	    
	}
}