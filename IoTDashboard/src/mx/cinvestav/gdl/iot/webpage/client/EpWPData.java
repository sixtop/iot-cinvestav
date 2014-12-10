package mx.cinvestav.gdl.iot.webpage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

public class EpWPData implements EntryPoint {
	private VerticalPanel formContainer=new VerticalPanel();
	
	private FlexTable tableDate=new FlexTable();
	
	private ListBox lbController=new ListBox();
	private ListBox lbSensor=new ListBox();
	
	
	private Button btGenerate = new Button("Generate");
	
	@Override
	public void onModuleLoad() {
		
 	    // Create a DateBox
    DateTimeFormat dateFormat = DateTimeFormat.getLongDateFormat();
    DateBox dateBox = new DateBox();
    dateBox.setFormat(new DateBox.DefaultFormat(dateFormat));
    dateBox.getDatePicker().setYearArrowsVisible(true);

    // Combine the widgets into a panel and return them
    VerticalPanel vPanel = new VerticalPanel();
   
	RootPanel.get("formContainer").add(dateBox);
	
	
   
	  
	}
}