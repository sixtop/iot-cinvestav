package mx.cinvestav.gdl.iot.dashboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;


public class EpController implements EntryPoint{
    private Button btSave = new Button("Save");
    private Button btCancel = new Button("Cancel");
    private TextBox tbId = new TextBox();
    private TextBox tbName = new TextBox();
    private TextBox tbDescription = new TextBox();
    private TextBox tbLocation = new TextBox();
   

    private FlexTable tableProperty= new FlexTable();
    private HorizontalPanel panelProperties = new HorizontalPanel();
    private Button btAdd = new Button("Add");
    
	public void onModuleLoad() {
		tableProperty.setText(0,1,"Id");
		tableProperty.setText(0,2,"Name");
		tableProperty.setText(0,3,"Value");
		tableProperty.setText(0,4,"Active");
		
		panelProperties.add(tableProperty);
		panelProperties.add(btAdd);
		
		RootPanel.get("propertyContainer").add(panelProperties);
		RootPanel.get("tbIdContainer").add(tbId);
		RootPanel.get("tbNameContainer").add(tbName);
		RootPanel.get("tbDescriptionContainer").add(tbDescription);
		RootPanel.get("tbLocationContainer").add(tbLocation);
		
		RootPanel.get("btSaveContainer").add(btSave);
		RootPanel.get("btCancelContainer").add(btCancel);
		
		 
	    btAdd.addClickHandler(new ClickHandler() {
	      public void onClick(ClickEvent event) {
	        addProperty();
	      }
	    });
	}
	
	 private void addProperty() {
		   
		  }

	
	
}
