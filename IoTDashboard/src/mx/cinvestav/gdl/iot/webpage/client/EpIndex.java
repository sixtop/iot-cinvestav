package mx.cinvestav.gdl.iot.webpage.client;

import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.ajaxloader.ArrayHelper;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;


public class EpIndex extends IoTEntryPoint
{
	private GeoChart geoChart;
	
	
	public void continueModuleLoad()
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
		chartLoader.loadApi(new Runnable() {

			@Override
			public void run() {
				DecoratorPanel chartPanel = new DecoratorPanel();
				chartPanel.add(getGeoChart());
	
				RootPanel.get("chart").add(chartPanel);
				draw();
			}
		});
		
	
	}

	private Widget getGeoChart()
	{
		if (geoChart == null)
		{
			geoChart = new GeoChart();
		}
		return geoChart;
	}

	
	private void draw(){
		DataTable dataTable = DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Country");
		dataTable.addColumn(ColumnType.STRING, "CityFarm");
		
		dataTable.addRows(2);
		dataTable.setValue(0, 0, "MX");
		dataTable.setValue(0, 1, "CityFarm Gdl");
		dataTable.setValue(1, 0, "US");
		dataTable.setValue(1, 1, "CityFarm MIT");
		
		// Set options
		GeoChartOptions options = GeoChartOptions.create();
		options.setHeight(500);
		options.setWidth(900);
		
		GeoChartColorAxis colorsAxis = GeoChartColorAxis.create();
		colorsAxis.setValues(ArrayHelper.toJsArrayNumber(1,40,100,400,4000));
		colorsAxis.setColors(ArrayHelper.toJsArrayString("#FFE6E6","#FF4D4D","#FF0000","#CC0000","#4C0000"));
		options.setColorAxis(colorsAxis);
		
		options.setColorAxis(colorsAxis);
		
		options.setDatalessRegionColor("gray");

		// Draw the chart
		geoChart.draw(dataTable, options);
		
	}

}