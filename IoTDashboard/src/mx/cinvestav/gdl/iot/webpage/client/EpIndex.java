package mx.cinvestav.gdl.iot.webpage.client;

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

public class EpIndex extends IoTEntryPoint
{
	private GeoChart geoChart;

	public void continueModuleLoad()
	{
		ChartLoader chartLoader = new ChartLoader(ChartPackage.GEOCHART);
		chartLoader.loadApi(new Runnable()
		{

			@Override
			public void run()
			{
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

	private void draw()
	{
		// Prepare the data
				DataTable dataTable = DataTable.create();
				dataTable.addColumn(ColumnType.STRING, "Country");
				dataTable.addColumn(ColumnType.NUMBER, "Popularity");
				dataTable.addRows(6);
				dataTable.setValue(0, 0, "Germany");
				dataTable.setValue(0, 1, 200);
				dataTable.setValue(1, 0, "United States");
				dataTable.setValue(1, 1, 300);
				dataTable.setValue(2, 0, "Brazil");
				dataTable.setValue(2, 1, 400);
				dataTable.setValue(3, 0, "Canada");
				dataTable.setValue(3, 1, 500);
				dataTable.setValue(4, 0, "France");
				dataTable.setValue(4, 1, 600);
				dataTable.setValue(5, 0, "RU");
				dataTable.setValue(5, 1, 700);

				// Set options
				GeoChartOptions options = GeoChartOptions.create();
				GeoChartColorAxis geoChartColorAxis = GeoChartColorAxis.create();
			//	geoChartColorAxis.setColors("green", "yellow", "red");
				options.setColorAxis(geoChartColorAxis);
				options.setDatalessRegionColor("gray");

				// Draw the chart
				geoChart.draw(dataTable, options);
	}

}