package mx.cinvestav.gdl.iot.webpage.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.nio.ch.WindowsAsynchronousChannelProvider;
import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingDTO;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
//import com.googlecode.gwt.charts.client.ChartLoader;
//import com.googlecode.gwt.charts.client.ChartPackage;
//import com.googlecode.gwt.charts.client.ColumnType;
//import com.googlecode.gwt.charts.client.DataTable;
//import com.googlecode.gwt.charts.client.corechart.LineChart;
//import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
//import com.googlecode.gwt.charts.client.options.HAxis;
//import com.googlecode.gwt.charts.client.options.VAxis;

public class EpWPDatas extends IoTEntryPoint
{
	private DialogBox dbWait = new DialogBox();

	private VerticalPanel formPanel = new VerticalPanel();

	private VerticalPanel formChart = new VerticalPanel();

	private FlexTable tableData = new FlexTable();
	private ListBox lbController = new ListBox();

	private ListBox lbSmartThing = new ListBox();

	private ListBox lbSensor = new ListBox();
	private ListBox lbTypeSensor = new ListBox();

	private DateBox dbFrom = new DateBox();
	private DateBox dbTo = new DateBox();

	private Button btGenerate = new Button("Generate");
	private List<ControllerDTO> CONTROLLERS;
	private List<SmartThingDTO> SMARTTHINGS;
	private List<SensorDTO> SENSORS;
	
	private Map<String, List<MeasureDTO>> group;

	private static final EntityStoreServiceAsync entityService = GWT.create(EntityStoreService.class);

	@Override
	public void continueModuleLoad()
	{

		showDialogWait();
		entityService.getEntity(new ControllerDTO(), null, new AsyncCallback<List<ControllerDTO>>() {

			@Override
			public void onFailure(Throwable caught)
			{
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(List<ControllerDTO> result)
			{
				dbWait.hide();
				CONTROLLERS = result;
				lbController.addItem("Select...");

				for (ControllerDTO c : CONTROLLERS)
				{
					lbController.addItem(c.getName(), c.getId() + "");
				}
			}
		});

		tableData.setText(0, 0, "Controller: ");
		tableData.setWidget(0, 1, lbController);

		tableData.setText(1, 0, "SmartThing: ");
		tableData.setWidget(1, 1, lbSmartThing);

		tableData.setText(2, 0, "Type sensor: ");
		tableData.setWidget(2, 1, lbTypeSensor);

		tableData.setText(3, 0, "From date: ");
		tableData.setWidget(3, 1, dbFrom);

		tableData.setText(4, 0, "To date: ");
		tableData.setWidget(4, 1, dbTo);

		lbSmartThing.setEnabled(false);
		lbSensor.setEnabled(false);

		formPanel.add(tableData);
		formPanel.add(btGenerate);
		formPanel.setCellHorizontalAlignment(btGenerate, HasHorizontalAlignment.ALIGN_CENTER);
		RootPanel.get("formContainer").add(formPanel);

		DefaultFormat format = new DateBox.DefaultFormat(DateTimeFormat.getFormat("MMMM dd yyyy"));
		dbFrom.setFormat(format);
		dbTo.setFormat(format);
		dbFrom.getDatePicker().setYearArrowsVisible(true);
		dbTo.getDatePicker().setYearArrowsVisible(true);

		lbController.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event)
			{

				final int idController = Integer.parseInt(lbController.getValue(lbController.getSelectedIndex()));

				entityService.getEntity(new SmartThingDTO(), null, new AsyncCallback<List<SmartThingDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SmartThingDTO> result)
					{
						SMARTTHINGS = result;
						lbSmartThing.clear();

						lbSmartThing.addItem("Select...");

						lbSmartThing.setEnabled(true);

						for (SmartThingDTO c : SMARTTHINGS)
						{
							if (c.getIdcontroller() == idController)
							{
								lbSmartThing.addItem(c.getName(), c.getId() + "");
							}
						}

					}
				});

			}
		});

		lbSmartThing.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event)
			{

				final int idSmartThing = Integer.parseInt(lbSmartThing.getValue(lbSmartThing.getSelectedIndex()));
				entityService.getEntity(new SensorDTO(), null, new AsyncCallback<List<SensorDTO>>() {

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<SensorDTO> result)
					{
						SENSORS = result;
						lbSensor.clear();

						lbTypeSensor.clear();
						lbTypeSensor.addItem("Select...");
						lbSensor.setEnabled(true);

						for (SensorDTO c : SENSORS)
						{
							if (c.getIdthing() == idSmartThing)
							{
								lbSensor.addItem(c.getSensor_type(), c.getId() + "");
							}
						}

						ArrayList<String> typeSensor = new ArrayList<String>();

						for (int i = 0; i < lbSensor.getItemCount(); i++)
						{
							if (!typeSensor.contains(lbSensor.getItemText(i)))
							{
								typeSensor.add(lbSensor.getItemText(i));
								lbTypeSensor.addItem(lbSensor.getItemText(i));
							}
						}

					}
				});

			}
		});

		btGenerate.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event)
			{
				group = new HashMap<String, List<MeasureDTO>>();
				formChart.clear();

				final String type = lbTypeSensor.getItemText(lbTypeSensor.getSelectedIndex());
				

				for (int i = 0; i < SENSORS.size(); i++)
				{
					if (SENSORS.get(i).getSensor_type().equals(type))
					{

						final String name = SENSORS.get(i).getName();
						final String test = "wsdf"; 
						

						entityService.getSensorData(SENSORS.get(i).getId(), dbFrom.getValue(), dbTo.getValue(),
								new AsyncCallback<List<MeasureDTO>>() {

									@Override
									public void onFailure(Throwable caught)
									{
										// TODO Auto-generated method stub

									}

									@Override
									public void onSuccess(final List<MeasureDTO> result)
									{
										group.put(name, result);
										if (group.size() == SENSORS.size())
										{
											String data = GraphUtils.generateStringData(group);
											GraphUtils.generateNVD3("Measure", "Date", data);
										}
									}

								});
					}
				}

			}
		});
	}

	public void showDialogWait()
	{

		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);
		dbWait.center();

		VerticalPanel dialogContents = new VerticalPanel();

		dialogContents.setSpacing(4);

		Image image = new Image();

		image.setUrl(GWT.getHostPageBaseURL() + "images/loading2.gif");

		dialogContents.add(image);
		dialogContents.setCellHorizontalAlignment(image, HasHorizontalAlignment.ALIGN_CENTER);

		dbWait.setWidget(dialogContents);
		dbWait.show();

	}
}
