package mx.cinvestav.gdl.iot.webpage.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.googlecode.gwt.charts.client.ColumnType;
import com.googlecode.gwt.charts.client.DataTable;
import com.googlecode.gwt.charts.client.geochart.GeoChart;
import com.googlecode.gwt.charts.client.geochart.GeoChartColorAxis;
import com.googlecode.gwt.charts.client.geochart.GeoChartOptions;


public class EpIndex implements EntryPoint {
	private GeoChart geoChart;
	
	  public void onModuleLoad() {
		  ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
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
	  
	  private void draw() {
			// Prepare the data
			DataTable dataTable = DataTable.create();
			dataTable.addColumn(ColumnType.STRING, "Country");
			dataTable.addColumn(ColumnType.STRING, "SmartCity");
			dataTable.addRows(6);
			
			dataTable.setValue(0, 0, "United States");
			dataTable.setValue(0, 1, "MIT CityFarm");
			dataTable.setValue(1, 0, "Mexico");
			dataTable.setValue(1, 1, "CityFarm GDL");
			
			// Set options
			GeoChartOptions options = GeoChartOptions.create();
			GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
			JsArrayString colors = JavaScriptObject.createArray().cast();
			colors.push("green");
			colors.push("yellow");
			
			geoChartColorAxis.setColors(colors);
			options.setColorAxis(geoChartColorAxis);
			options.setDatalessRegionColor("gray");

			// Draw the chart
			geoChart.draw(dataTable, options);
		}
	  
	}