package mx.cinvestav.gdl.iot.webpage.client;



import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;



public class EpIndex implements EntryPoint {
	private Label welcome=new Label();
	
	
	@Override
	public void onModuleLoad() {
		welcome.setText("Welcome User");
		welcome.setStyleName("lbProperty");
		
		
		RootPanel.get("formContainer").add(welcome);
	    
	}
}