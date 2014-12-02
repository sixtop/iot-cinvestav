package mx.cinvestav.gdl.iot.dashboard.client;

import java.util.ArrayList;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import mx.cinvestav.gdl.iot.dao.Controller;
import mx.cinvestav.gdl.iot.dao.ControllerProperty;
import mx.cinvestav.gdl.iot.dao.DAO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpController implements EntryPoint {
	private Button btSave = new Button("Save");
	private Button btCancel = new Button("Cancel");
	private TextBox tbId = new TextBox();
	private TextBox tbName = new TextBox();
	private TextBox tbDescription = new TextBox();
	private TextBox tbLocation = new TextBox();

	private VerticalPanel propertyContainer = new VerticalPanel();
	private FlexTable tableProperty = new FlexTable();
	
	private TextBox name = new TextBox();
	private TextBox value = new TextBox();
	private CheckBox active = new CheckBox();
	
	private Button btAddProperty = new Button("Add");
	private ArrayList<String> property = new ArrayList<String>();

	public void onModuleLoad() {
		RootPanel.get("tbIdContainer").add(tbId);
		RootPanel.get("tbNameContainer").add(tbName);
		RootPanel.get("tbDescriptionContainer").add(tbDescription);
		RootPanel.get("tbLocationContainer").add(tbLocation);

		RootPanel.get("btSaveContainer").add(btSave);
		RootPanel.get("btCancelContainer").add(btCancel);

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

		propertyContainer.add(btAddProperty);
		propertyContainer.add(tableProperty);
		
		RootPanel.get("propertyContainer").add(propertyContainer);

		btAddProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addProperty();
			}
		});
		
		btSave.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				saveController();
			}
		});

	}


	private void addProperty() {
		int row = tableProperty.getRowCount();
		
		tableProperty.setWidget(row, 0, name);
		tableProperty.setWidget(row, 1, value);
		tableProperty.setWidget(row, 2, active);
		
		// Add a button to remove this stock from the table.
		Button saveProperty = new Button("Save");
		
		saveProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				saveProperty();
			}
		});
		
		tableProperty.setWidget(row, 3, saveProperty);
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
		
		name.setText("");
		value.setText("");
		active.setValue(false);
		
		int row = tableProperty.getRowCount();
		tableProperty.setText(row, 0, symboln);
		tableProperty.setText(row, 1, symbolv);
		tableProperty.setWidget(row, 2, symbola);
		
		Button removeProperty = new Button("Remove");
		
		removeProperty.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				int removedIndex = property.indexOf(symboln);
				property.remove(removedIndex);
				tableProperty.removeRow(removedIndex + 1);
			}
		});
		
		tableProperty.setWidget(row, 3, removeProperty);		
	}
	
	private void saveController() {
		
		
		EntityManager em = null;
		EntityTransaction tx = null;
		
		try{
			em = DAO.createEntityManager();
			tx = em.getTransaction();
			tx.begin();
			
			
			
			Controller x=new Controller();
			x.setDescription(tbName.getText());
			x.setDescription(tbLocation.getText());
			x.setDescription(tbDescription.getText());
			Map<Integer, ControllerProperty> props = x.getProperties();
			
			for(int i=0;i<tableProperty.getRowCount();i++){
				
				ControllerProperty cP=new ControllerProperty();
				cP.setName(tableProperty.getText(i, 0));
				cP.setValue(tableProperty.getText(i, 1));
				cP.setActive(((CheckBox)tableProperty.getWidget(i,2)).getValue());
				em.persist(cP);
				props.put(cP.getId(), cP);
				
			}
			em.persist(x);
			
			
			tx.commit();
		}
		catch(Exception e)
		{
			if(tx!=null) tx.rollback();
			if(em!=null) em.close();
		}
		
		
		
		
	}

	
}