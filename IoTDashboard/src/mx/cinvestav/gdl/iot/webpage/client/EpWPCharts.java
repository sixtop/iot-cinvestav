package mx.cinvestav.gdl.iot.webpage.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EpWPCharts extends IoTEntryPoint
{

	private Button btSensor=new Button("Statistics by sensor");
	private Button btSensorByType=new Button("Statistics by type of sensor");
	private VerticalPanel formPanel=new VerticalPanel();
	
	
	@Override
	public void continueModuleLoad()
	{
	
		formPanel.add(btSensor);
		formPanel.add(btSensorByType);
		formPanel.setCellHorizontalAlignment(btSensor, HasHorizontalAlignment.ALIGN_LEFT);
		formPanel.setCellHorizontalAlignment(btSensorByType, HasHorizontalAlignment.ALIGN_LEFT);

		RootPanel.get("formContainer").add(formPanel);

		btSensor.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				Window.Location.replace("wpData.jsp");
			}
		});

		btSensorByType.addClickHandler(new ClickHandler()
		{
			public void onClick(ClickEvent event)
			{
				Window.Location.replace("wpDatas.jsp");
			}
		});


	}

}
