package mx.cinvestav.gdl.iot.webpage.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpLogin implements EntryPoint {
	private FormPanel form = new FormPanel();
	private VerticalPanel formPanel = new VerticalPanel();
	
	private TextBox tbUserName=new TextBox();
	private TextBox tbPassword=new TextBox();
	
	private FlexTable tableLogin = new FlexTable();
	private HorizontalPanel buttonPanel=new HorizontalPanel();
	
	private Button btLogin = new Button("Login");
	
	@Override
	public void onModuleLoad() {
		
		Label userName=new Label();
		userName.setText("User");
		tableLogin.setWidget(0, 0,userName);
		
		tableLogin.setWidget(0, 1,tbUserName);
		
		Label password=new Label();
		password.setText("Password");
		tableLogin.setWidget(1, 0,password);
		
		tableLogin.setWidget(1, 1,tbPassword);
		tableLogin.setWidget(2, 3,btLogin);
		tableLogin.setWidget(2, 2,btLogin);
		
		formPanel.add(btLogin);
		
	    form.setWidget(formPanel);
	    
	    
	    DecoratorPanel decoratorPanel = new DecoratorPanel();
	    decoratorPanel.add(form);
	    
	    RootPanel.get("formContainer").add(decoratorPanel);
	    
	    btLogin.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                //TODO
	            	  
	              }
	            });
	    
	  
	}
}