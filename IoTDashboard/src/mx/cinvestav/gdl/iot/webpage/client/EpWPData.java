package mx.cinvestav.gdl.iot.webpage.client;

import java.sql.Date;
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
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
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
import com.googlecode.gwt.charts.client.options.Legend;
import com.googlecode.gwt.charts.client.options.LegendPosition;


public class EpWPData implements EntryPoint {
	private DockLayoutPanel dp;
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

		entityService.getEntity(new ControllerDTO(), null,
				new AsyncCallback<List<ControllerDTO>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					@Override
					public void onSuccess(List<ControllerDTO> result) {
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
				
				// Window.alert("GENERA Sensor"+lbIdSensor.getItemText(lbSensor.getSelectedIndex()));
				Window.alert("GENERA Sensor");

				ChartLoader chartLoader = new ChartLoader(ChartPackage.CONTROLS);
				chartLoader.loadApi(new Runnable() {
					
					
					@Override
					public void run() {
						
						table.setWidget(0,1,getDashboardWidget());
						table.setWidget(1,1,getLineChart());
						table.setWidget(3,1,getNumberRangeFilter());
						
						draw();
					}
				});
				
				table.setWidth("100%");
				table.setHeight("90%");
				RootPanel.get("chart").add(table);
				
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

	private void draw() {
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
		chartRangeFilterUi.setMinRangeSize(2 * 24 * 60 * 60 * 1000); // 2 days in milliseconds
		chartRangeFilterOptions.setUi(chartRangeFilterUi);
		ChartRangeFilterStateRange stateRange = ChartRangeFilterStateRange.create();
		stateRange.setStart(new Date(2012, 2, 9));
		stateRange.setEnd(new Date(2012, 3, 20));
		//stateRange.setEnd(DateUtils.create(2012, 3, 20));
		ChartRangeFilterState controlState = ChartRangeFilterState.create();
		controlState.setRange(stateRange);
		numberRangeFilter.setState(controlState);
		numberRangeFilter.setOptions(chartRangeFilterOptions);

		// Set chart options
		LineChartOptions lineChartOptions = LineChartOptions.create();
		lineChartOptions.setLineWidth(3);
		lineChartOptions.setLegend(Legend.create(LegendPosition.NONE));
		lineChartOptions.setChartArea(chartArea);
		lineChart.setOptions(lineChartOptions);

		// Generate random data
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.DATE, "Date");
		dataTable.addColumn(ColumnType.NUMBER, "Stock value");
		dataTable.addRows(121);

		double open, close = 300;
		double low, high;
		for (int day = 1; day < 121; ++day) {
			double change = (Math.sin(day / 2.5 + Math.PI) + Math.sin(day / 3) - Math.cos(day * 0.7)) * 150;
			change = change >= 0 ? change + 10 : change - 10;
			open = close;
			close = Math.max(50, open + change);
			low = Math.min(open, close) - (Math.cos(day * 1.7) + 1) * 15;
			low = Math.max(0, low);
			high = Math.max(open, close) + (Math.cos(day * 1.3) + 1) * 15;
			dataTable.setValue(day, 0, new Date(2012, 1, day));
			dataTable.setValue(day, 1, Math.round(high));
		}

		// Draw the chart
		dashboard.bind(numberRangeFilter, lineChart);
		dashboard.draw(dataTable);
	}
}