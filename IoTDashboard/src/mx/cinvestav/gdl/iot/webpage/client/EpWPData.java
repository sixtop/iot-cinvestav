package mx.cinvestav.gdl.iot.webpage.client;

import java.util.List;

import mx.cinvestav.gdl.iot.webpage.dto.ControllerDTO;
import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SensorDTO;
import mx.cinvestav.gdl.iot.webpage.dto.SmartThingDTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
<<<<<<< HEAD
import com.google.gwt.user.client.ui.DialogBox;
=======
>>>>>>> branch 'master' of https://github.com/sixtop/iot-cinvestav.git
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DateBox.DefaultFormat;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ChartType;
import com.googlecode.gwt.charts.client.ChartWrapper;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.controls.Dashboard;
import com.googlecode.gwt.charts.client.controls.filter.ChartRangeFilter;
import com.googlecode.gwt.charts.client.controls.filter.ChartRangeFilterOptions;
import com.googlecode.gwt.charts.client.controls.filter.ChartRangeFilterState;
import com.googlecode.gwt.charts.client.controls.filter.ChartRangeFilterStateRange;
import com.googlecode.gwt.charts.client.controls.filter.ChartRangeFilterUi;
import com.googlecode.gwt.charts.client.corechart.LineChartOptions;
import com.googlecode.gwt.charts.client.options.ChartArea;
import com.googlecode.gwt.charts.client.options.CurveType;
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendPosition;


public class EpWPData implements EntryPoint {
<<<<<<< HEAD
	private DialogBox dbWait = new DialogBox();
=======
>>>>>>> branch 'master' of https://github.com/sixtop/iot-cinvestav.git
	private Dashboard dashboard;
	private ChartWrapper<LineChartOptions> lineChart;
	private ChartRangeFilter numberRangeFilter;
	private FlexTable table=new FlexTable();
	 
	 
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
<<<<<<< HEAD
		showDialogWait();
=======
		
>>>>>>> branch 'master' of https://github.com/sixtop/iot-cinvestav.git
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

		DefaultFormat format = new DateBox.DefaultFormat(DateTimeFormat.getFormat("MMMM dd yyyy"));
		dbFrom.setFormat(format);
		dbTo.setFormat(format);
		dbFrom.getDatePicker().setYearArrowsVisible(true);
		dbTo.getDatePicker().setYearArrowsVisible(true);

		/*
		 * DateTimeFormat dateFormat = DateTimeFormat.getFullDateFormat();
		 * dbFrom.setFormat(new DateBox.DefaultFormat(dateFormat));
		 * dbTo.setFormat(new DateBox.DefaultFormat(dateFormat));
		 */
		lbController.addChangeHandler(new ChangeHandler() {
			@Override
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
				
<<<<<<< HEAD
=======
				// Window.alert("GENERA Sensor"+);
>>>>>>> branch 'master' of https://github.com/sixtop/iot-cinvestav.git
				Window.alert("GENERA Sensor");
				String sid = lbIdSensor.getItemText(lbSensor.getSelectedIndex());
<<<<<<< HEAD
				showDialogWait();
=======
>>>>>>> branch 'master' of https://github.com/sixtop/iot-cinvestav.git
				entityService.getSensorData(Integer.parseInt(sid), dbFrom.getValue(), dbTo.getValue(), new AsyncCallback<List<MeasureDTO>>()
				{

					@Override
					public void onFailure(Throwable caught)
					{
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(final List<MeasureDTO> result)
					{
<<<<<<< HEAD
						dbWait.hide();
=======
>>>>>>> branch 'master' of https://github.com/sixtop/iot-cinvestav.git
						ChartLoader chartLoader = new ChartLoader(ChartPackage.CONTROLS);
						chartLoader.loadApi(new Runnable() {
							
							
							@Override
							public void run() {
								
								table.setWidget(0,1,getDashboardWidget());
								table.setWidget(1,1,getLineChart());
								table.setWidget(3,1,getNumberRangeFilter());
								
								draw(result);
								table.setWidth("100%");
								table.setHeight("90%");
								RootPanel.get("chart").add(table);
							}
						});
					}
				});
			}
		});
	}


	private Dashboard getDashboardWidget() {
		if (dashboard == null) {
			dashboard = new Dashboard();
		}
		return dashboard;
	}

	private ChartWrapper<LineChartOptions> getLineChart() {
		if (lineChart == null) {
			lineChart = new ChartWrapper<LineChartOptions>();
			lineChart.setChartType(ChartType.LINE);
		}
		return lineChart;
	}

	private ChartRangeFilter getNumberRangeFilter() {
		if (numberRangeFilter == null) {
			numberRangeFilter = new ChartRangeFilter();
		}
		return numberRangeFilter;
	}

	private void draw(List<MeasureDTO> result) 
	{
		// Set control options
		ChartRangeFilterOptions chartRangeFilterOptions = ChartRangeFilterOptions.create();
		chartRangeFilterOptions.setFilterColumnIndex(0); // Filter by the date axis
		
		LineChartOptions controlChartOptions = LineChartOptions.create();
		controlChartOptions.setHeight(100);
		
		ChartArea chartArea = ChartArea.create();
		chartArea.setWidth("90%");
		chartArea.setHeight("90%");
		controlChartOptions.setChartArea(chartArea);
		
		ChartRangeFilterUi chartRangeFilterUi = ChartRangeFilterUi.create();
		chartRangeFilterUi.setChartType(ChartType.LINE);
		chartRangeFilterUi.setChartOptions(controlChartOptions);
//		chartRangeFilterUi.setMinRangeSize(2 * 24 * 60 * 60 * 1000); // 2 days in milliseconds
		chartRangeFilterUi.setMinRangeSize(1000); // 2 days in milliseconds
		chartRangeFilterOptions.setUi(chartRangeFilterUi);
		ChartRangeFilterStateRange stateRange = ChartRangeFilterStateRange.create();
		stateRange.setStart(dbFrom.getValue());
		stateRange.setEnd(dbTo.getValue());
		ChartRangeFilterState controlState = ChartRangeFilterState.create();
		controlState.setRange(stateRange);
		numberRangeFilter.setState(controlState);
		numberRangeFilter.setOptions(chartRangeFilterOptions);

		// Set chart options
		LineChartOptions chart = LineChartOptions.create();
		chart.setLineWidth(3);
		chart.setLegend(Legend.create(LegendPosition.NONE));
		chart.setChartArea(chartArea);
		chart.setCurveType(CurveType.NONE);
		lineChart.setOptions(chart);

		// Generate random data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.DATE, "Date");
		dataTable.addColumn(ColumnType.NUMBER, "Measure");
		dataTable.addRows(result.size());
		
		//Window.alert("datasize:" + result.size());
		
		
		for(int rows = 0; rows < result.size(); rows++)
		{
			dataTable.setValue(rows, 0, result.get(rows).getMeasure_date());
			dataTable.setValue(rows, 1, Double.parseDouble(result.get(rows).getMeasure()));
		}

//		double open, close = 300;
//		double low, high;
//		for (int day = 1; day < 121; ++day) {
//			double change = (Math.sin(day / 2.5 + Math.PI) + Math.sin(day / 3) - Math.cos(day * 0.7)) * 150;
//			change = change >= 0 ? change + 10 : change - 10;
//			open = close;
//			close = Math.max(50, open + change);
//			low = Math.min(open, close) - (Math.cos(day * 1.7) + 1) * 15;
//			low = Math.max(0, low);
//			high = Math.max(open, close) + (Math.cos(day * 1.3) + 1) * 15;
//			dataTable.setValue(day, 0, new Date(2012, 1, day));
//			dataTable.setValue(day, 1, Math.round(high));
//		}

//		double open, close = 300;
//		double low, high;
//		for (int day = 1; day < 121; ++day) {
//			double change = (Math.sin(day / 2.5 + Math.PI) + Math.sin(day / 3) - Math.cos(day * 0.7)) * 150;
//			change = change >= 0 ? change + 10 : change - 10;
//			open = close;
//			close = Math.max(50, open + change);
//			low = Math.min(open, close) - (Math.cos(day * 1.7) + 1) * 15;
//			low = Math.max(0, low);
//			high = Math.max(open, close) + (Math.cos(day * 1.3) + 1) * 15;
//			dataTable.setValue(day, 0, new Date(2012, 1, day));
//			dataTable.setValue(day, 1, Math.round(high));
//		}

		// Draw the chart
		dashboard.bind(numberRangeFilter, lineChart);
		dashboard.draw(dataTable);
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
