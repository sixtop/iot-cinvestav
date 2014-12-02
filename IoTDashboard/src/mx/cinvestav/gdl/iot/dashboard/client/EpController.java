package mx.cinvestav.gdl.iot.dashboard.client;

import java.util.ArrayList;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.FormPanel.SubmitEvent;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpController implements EntryPoint {
	private TextBox tbControlerS = new TextBox();
	
	private DialogBox dialogBox = new DialogBox();
	private Button btClose = new Button("Close");
	private Label lbDialogBox = new Label();

	private ListBox listNameProperty=new ListBox();
	private ListBox listValueProperty=new ListBox();
	private ListBox listActiveProperty=new ListBox();
	
	private TextBox name = new TextBox();
	private TextBox value = new TextBox();
	private CheckBox active = new CheckBox();
	
	
	private FormPanel form = new FormPanel();
	private VerticalPanel formPanel = new VerticalPanel();
	
	private Button btSaveController = new Button("Save controller");
	private Button btCancelController = new Button("Cancel");
	private HorizontalPanel buttonsPanel = new HorizontalPanel();
	
	
	private FlexTable tableFields = new FlexTable();
	private TextBox tbId = new TextBox();
	private TextBox tbName = new TextBox();
	private TextBox tbDescription = new TextBox();
	private TextBox tbLocation = new TextBox();
	

	private VerticalPanel propertyPanel = new VerticalPanel();
	private Label lbProperty = new Label();
	private FlexTable tableProperty = new FlexTable();
	
	private Button btAddProperty = new Button("Add");
	
	private ArrayList<String> property = new ArrayList<String>();

	public void onModuleLoad() {
		form.setAction(GWT.getHostPageBaseURL() + "addEntityServlet");
		
		form.setMethod(FormPanel.METHOD_POST);
	    form.setEncoding(FormPanel.ENCODING_URLENCODED);
	    
		tableFields.setText(0, 0,"Id: ");
		tableFields.setWidget(0,1,tbId);
		
		tableFields.setText(1, 0,"Name: ");
		tableFields.setWidget(1,1,tbName);
		
		tableFields.setText(2, 0,"Description: ");
		tableFields.setWidget(2,1,tbDescription);
		
		tableFields.setText(3, 0,"Location: ");
		tableFields.setWidget(3,1,tbLocation);
		
		tbId.setName("tbIdS");
		tbId.setEnabled(false);
		
		tbName.setName("tbNameS");
		tbDescription.setName("tbDescriptionS");
		tbLocation.setName("tbLocationS");
		listNameProperty.setName("listNamePropertyS");
		listValueProperty.setName("listValuePropertyS");
		listActiveProperty.setName("listActivePropertyS");
		
		
	    tbControlerS.setName("IdOperation");
		tbControlerS.setText("Controller");//Id Controller
		tbControlerS.setVisible(false);
		
		tableProperty.setText(0, 0, "Name");
		tableProperty.setText(0, 1, "Value");
		tableProperty.setText(0, 2, "Active");
		tableProperty.setText(0, 3, "       ");
		
		tableProperty.getCellFormatter().addStyleName(0, 0, "headerTableProperty");
		tableProperty.getCellFormatter().addStyleName(0, 1, "headerTableProperty");
		tableProperty.getCellFormatter().addStyleName(0, 2, "headerTableProperty");
		tableProperty.getCellFormatter().addStyleName(0, 3, "headerTableProperty");
		

		tableProperty.addStyleName("tableProperty");
		tableProperty.setCellPadding(3);
		
		lbProperty.setText("Property");
		lbProperty.setStyleName("lbProperty");
		
		propertyPanel.add(lbProperty);
		propertyPanel.add(btAddProperty);
		propertyPanel.add(tableProperty);
		
		formPanel.add(tableFields);
	    formPanel.add(propertyPanel);
	    
	    btSaveController.addStyleName("btSave");
	    btCancelController.addStyleName("btSave");
	    
	    
	    buttonsPanel.add(btSaveController);
	    buttonsPanel.add(btCancelController);
	    formPanel.add(buttonsPanel);
	    
	    form.setWidget(formPanel);
	    
	     DecoratorPanel decoratorPanel = new DecoratorPanel();
	      
	      decoratorPanel.add(form);
	      // Add the widgets to the root panel.
	    RootPanel.get("formContainer").add(decoratorPanel);
	    

		
		RootPanel.get("formContainer").add(tbControlerS);
	    
	    dialogBox.add(btClose);
	    
	    btClose.addClickHandler(new ClickHandler() {
	              public void onClick(ClickEvent event) {
	                dialogBox.hide();
	              }
	            });
	    
	     
		btAddProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btAddProperty.setEnabled(false);
				addProperty();
			}
		});
		
		
		 // Add a 'submit' button.
	     btSaveController.addClickHandler(new ClickHandler() {
	         @Override
	         public void onClick(ClickEvent event) {
	            form.submit();					
	         }
	      });
	      
	     // Add an event handler to the form.
	      form.addSubmitHandler(new FormPanel.SubmitHandler() {
	         @Override
	         public void onSubmit(SubmitEvent event) {
	            // This event is fired just before the form is submitted. 
	            // We can take this opportunity to perform validation.
	            if (tbName.getText().length() == 0) {
	            	
	            	dialogBox.setGlassEnabled(true);
	        	    dialogBox.setAnimationEnabled(true);
	        	    dialogBox.center();
	        	    dialogBox.setText("No more....");
	            	dialogBox.show();
	            	
	               event.cancel();
	            }
	            
	         }
	      });

	     form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
	         @Override
	         public void onSubmitComplete(SubmitCompleteEvent event) {
	            // When the form submission is successfully completed,
	            // this event is fired. Assuming the service returned 
	            // a response of type text/html, we can get the result
	            // here.
	            Window.alert(event.getResults());					
	         }
	      });
	     
	      
	}


	private void addProperty() {
		int row = tableProperty.getRowCount();
		
		TextBox name = new TextBox();
		TextBox value = new TextBox();
		CheckBox active = new CheckBox();
		
		tableProperty.setWidget(row, 0, name);
		tableProperty.setWidget(row, 1, value);
		tableProperty.setWidget(row, 2, active);
		
		// Add a button to remove this stock from the table.
		Button saveProperty = new Button("Save");
		saveProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btAddProperty.setEnabled(true);
				saveProperty();
			}
		});
		
		Button cancelProperty = new Button("Cancel");
		
		cancelProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				btAddProperty.setEnabled(true);
			}
		});
		
		HorizontalPanel buttonsPanel=new HorizontalPanel();
		buttonsPanel.add(saveProperty);
		buttonsPanel.add(cancelProperty);
		
		tableProperty.setWidget(row, 3, buttonsPanel);
		
	}
	
	private void saveProperty() {
		tableProperty.removeRow(tableProperty.getRowCount()-1);
		
		final String symboln = name.getText().toUpperCase().trim();
		final String symbolv = value.getText().toUpperCase().trim();
		CheckBox symbola=new CheckBox();
		
		property.add(symboln);
		
		if(active.getValue() == true){
			symbola.setValue(true);
		}else{
			symbola.setValue(false);
		}
		symbola.setName("sfgdfg");
		
		name.setText("");
		value.setText("");
		active.setValue(false);
		
		int row = tableProperty.getRowCount();
		tableProperty.setText(row, 0, symboln);
		tableProperty.setText(row, 1, symbolv);
		tableProperty.setWidget(row, 2, symbola);
		
		listNameProperty.addItem(symboln);
		listValueProperty.addItem(symbolv);
		listActiveProperty.addItem(symbola.getValue()+"");
		
		
		Button removeProperty = new Button("Remove");
		
		removeProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int removedIndex = property.indexOf(symboln);
				property.remove(removedIndex);
				tableProperty.removeRow(removedIndex + 1);
				
				
				listNameProperty.removeItem(removedIndex);
				listValueProperty.removeItem(removedIndex);
				listActiveProperty.removeItem(removedIndex);
				
				
			}
		});
		
		tableProperty.setWidget(row, 3, removeProperty);		
	}
	
	private void saveController() {
		/*
		
		
//		EntityManager em = null;
//		EntityTransaction tx = null;
//		
//		try{
//			em = DAO.createEntityManager();
//			tx = em.getTransaction();
//			tx.begin();
//			
//			
//			
//			Controller x=new Controller();
//			x.setDescription(tbName.getText());
//			x.setDescription(tbLocation.getText());
//			x.setDescription(tbDescription.getText());
//			Map<Integer, ControllerProperty> props = x.getProperties();
//			
//			for(int i=0;i<tableProperty.getRowCount();i++){
//				
//				ControllerProperty cP=new ControllerProperty();
//				cP.setName(tableProperty.getText(i, 0));
//				cP.setValue(tableProperty.getText(i, 1));
//				cP.setActive(((CheckBox)tableProperty.getWidget(i,2)).getValue());
//				em.persist(cP);
//				props.put(cP.getId(), cP);
//				
//			}
//			em.persist(x);
//			
//			
//			tx.commit();
//		}
//		catch(Exception e)
//		{
//			if(tx!=null) 
//			{
//				tx.rollback();
//			}
//		}
//		finally
//		{
//			if(em!=null) 
//			{
//				em.close();			
//			}
//		}
		
		
		*/
		
	}

	
}
