package mx.cinvestav.gdl.iot.webpage.client;

import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.corechart.LineChart;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.VAxis;
import com.googlecode.gwt.charts.client.table.Table;
import com.googlecode.gwt.charts.client.table.TableOptions;

public class EpWPDatas implements EntryPoint {
	private DialogBox dbWait = new DialogBox();
	
	private LineChart lineChart;

	private VerticalPanel formPanel = new VerticalPanel();

	private FlexTable tableData = new FlexTable();

	private ListBox lbController = new ListBox();
	private ListBox lbIdController = new ListBox();

	private ListBox lbSmartThing = new ListBox();
	private ListBox lbIdSmartThing = new ListBox();

	private ListBox lbSensor = new ListBox();
	private ListBox lbIdSensor = new ListBox();

	private DateBox dbFrom = new DateBox();
	private DateBox dbTo = new DateBox();

	private Button btGenerate = new Button("Generate");
	private List<ControllerDTO> CONTROLLERS;
	private List<SmartThingDTO> SMARTTHINGS;
	private List<SensorDTO> SENSORS;

	private static final EntityStoreServiceAsync entityService = GWT
			.create(EntityStoreService.class);

	@Override
	public void onModuleLoad() {

		showDialogWait();
		entityService.getEntity(new ControllerDTO(), null,
				new AsyncCallback<List<ControllerDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<ControllerDTO> result) {
						dbWait.hide();
						CONTROLLERS = result;
						lbController.addItem("Select...");
						lbIdController.addItem("-");

						for (ControllerDTO c : CONTROLLERS) {
							lbController.addItem(c.getName());
							lbIdController.addItem(c.getId() + "");
						}
					}
				});

		tableData.setText(0, 0, "Controller: ");
		tableData.setWidget(0, 1, lbController);

		tableData.setText(1, 0, "SmartThing: ");
		tableData.setWidget(1, 1, lbSmartThing);

		tableData.setText(2, 0, "Sensor: ");
		tableData.setWidget(2, 1, lbSensor);

		tableData.setText(3, 0, "From date: ");
		tableData.setWidget(3, 1, dbFrom);

		tableData.setText(4, 0, "To date: ");
		tableData.setWidget(4, 1, dbTo);

		lbSmartThing.setEnabled(false);
		lbSensor.setEnabled(false);

		formPanel.add(tableData);
		formPanel.add(btGenerate);
		formPanel.setCellHorizontalAlignment(btGenerate,
				HasHorizontalAlignment.ALIGN_CENTER);
		RootPanel.get("formContainer").add(formPanel);

		dbFrom.getDatePicker().setYearArrowsVisible(true);
		dbTo.getDatePicker().setYearArrowsVisible(true);

		/*
		 * DateTimeFormat dateFormat = DateTimeFormat.getFullDateFormat();
		 * dbFrom.setFormat(new DateBox.DefaultFormat(dateFormat));
		 * dbTo.setFormat(new DateBox.DefaultFormat(dateFormat));
		 */
		lbController.addChangeHandler(new ChangeHandler() {
			
			public void onChange(ChangeEvent event) {

				final int idController = Integer.parseInt(lbIdController
						.getValue(lbController.getSelectedIndex()));

				Window.alert("Controller" + idController);

				entityService.getEntity(new SmartThingDTO(), null,
						new AsyncCallback<List<SmartThingDTO>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
							}

							@Override
							public void onSuccess(List<SmartThingDTO> result) {
								SMARTTHINGS = result;
								lbSmartThing.clear();
								lbIdSmartThing.clear();

								lbSmartThing.addItem("Select...");
								lbIdSmartThing.addItem("-");

								lbSmartThing.setEnabled(true);

								for (SmartThingDTO c : SMARTTHINGS) {
									if (c.getIdcontroller() == idController) {
										lbSmartThing.addItem(c.getName());
										lbIdSmartThing.addItem(c.getId() + "");
									}
								}

							}
						});

			}
		});

		lbSmartThing.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {

				final int idSmartThing = Integer.parseInt(lbIdSmartThing
						.getValue(lbSmartThing.getSelectedIndex()));

				Window.alert("idSmart" + idSmartThing);

				entityService.getEntity(new SensorDTO(), null,
						new AsyncCallback<List<SensorDTO>>() {

							@Override
							public void onFailure(Throwable caught) {
								// TODO Auto-generated method stub
							}

							@Override
							public void onSuccess(List<SensorDTO> result) {
								SENSORS = result;
								lbSensor.clear();
								lbIdSensor.clear();
								lbSensor.addItem("Select...");
								lbIdSensor.addItem("-");
								lbSensor.setEnabled(true);

								for (SensorDTO c : SENSORS) {
									if (c.getIdthing() == idSmartThing) {
										lbSensor.addItem(c.getName());
										lbIdSensor.addItem(c.getId() + "");
									}
								}
							}
						});

			}
		});

		btGenerate.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				// Window.alert("GENERA Sensor"+lbIdSensor.getItemText(lbSensor.getSelectedIndex()));
				Window.alert("GENERA Sensor");

				
				ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
				chartLoader.loadApi(new Runnable() {
					@Override
					public void run() {
						DecoratorPanel chartPanel =new DecoratorPanel();
						chartPanel.add(getLineChart());
					
						RootPanel.get("chart").add(chartPanel);
						
						drawLineChart();

					}

				});
				
			}
		});

	}

	private Widget getLineChart() {
		if (lineChart == null) {
			lineChart = new LineChart();
		}
		return lineChart;
	}
	

	private void drawLineChart() {
		String[] countries = new String[] { "Austria", "Bulgaria", "Denmark",
				"Greece" };
		int[] years = new int[] { 2003, 2004, 2005, 2006, 2007, 2008 };
		int[][] values = new int[][] {
				{ 1336060, 1538156, 1576579, 1600652, 1968113, 1901067 },
				{ 400361, 366849, 440514, 434552, 393032, 517206 },
				{ 1001582, 1119450, 993360, 1004163, 979198, 916965 },
				{ 997974, 941795, 930593, 897127, 1080887, 1056036 } };

		// Prepare the data
		DataTable dataTable = DataTable.create();
		
		dataTable.addColumn(ColumnType.STRING, "Year");
		for (int i = 0; i < countries.length; i++) {
			dataTable.addColumn(ColumnType.NUMBER, countries[i]);
		}
		
		dataTable.addRows(years.length);
		for (int i = 0; i < years.length; i++) {
			dataTable.setValue(i, 0, String.valueOf(years[i]));
		}
		
		
		for (int col = 0; col < values.length; col++) {
			for (int row = 0; row < values[col].length; row++) {
				dataTable.setValue(row, col + 1, values[col][row]);
			}
		}

		// Set options
		LineChartOptions options = LineChartOptions.create();
		options.setWidth(800);
		options.setHeight(500);
		options.setBackgroundColor("white");
		options.setFontName("Tahoma");
		options.setTitle("Sensor Information");
		options.setHAxis(HAxis.create("Date"));
		options.setVAxis(VAxis.create("Value"));

		// Draw the chart
		lineChart.draw(dataTable, options);
	}
	
public void showDialogWait(){
		
		dbWait.setAnimationEnabled(true);
		dbWait.setGlassEnabled(true);
		dbWait.setModal(true);
		dbWait.center();

	    VerticalPanel dialogContents = new VerticalPanel();
	    
	    dialogContents.setSpacing(4);
	    
	    Image image = new Image();
	    
	    image.setUrl(GWT.getHostPageBaseURL()+"images/loading2.gif");
	    
	    
	    dialogContents.add(image);
	    dialogContents.setCellHorizontalAlignment(image, HasHorizontalAlignment.ALIGN_CENTER);
	    
	    dbWait.setWidget(dialogContents);
	    dbWait.show();
		
	}
	
}