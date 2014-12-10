package mx.cinvestav.gdl.iot.webpage.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;

import com.google.gwt.user.client.ui.FlexTable;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;

import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpLogin implements EntryPoint {
	private VerticalPanel formContainer=new VerticalPanel();
	
	private FlexTable tableLogin=new FlexTable();
	
	private TextBox tbUserName=new TextBox();
	private TextBox tbPassword=new TextBox();
	
	private Button btLogin = new Button("Login");
	
	@Override
	public void onModuleLoad() {
		
		tableLogin.setText(0,0,"User");
		tableLogin.setWidget(0, 1,tbUserName);
		
		tableLogin.setText(1,0,"Password");
		tableLogin.setWidget(1, 1,tbPassword);
		
		formContainer.add(tableLogin);
		formContainer.add(btLogin);
		
		formContainer.setCellHorizontalAlignment(btLogin, HasHorizontalAlignment.ALIGN_CENTER);
		    
		RootPanel.get("formContainer").add(formContainer);
	    
	    btLogin.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	            
	            	  
	              }
	            });
	    
	  
	}
}