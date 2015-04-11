package mx.cinvestav.gdl.iot.webpage.client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import mx.cinvestav.gdl.iot.webpage.dto.MeasureDTO;

import com.google.gwt.i18n.client.DateTimeFormat;

public class GraphUtils
{
	public static String generateStringData(Map<String, List<MeasureDTO>> group)
	{
		boolean empty = true;
		StringBuffer json = new StringBuffer("[");
		Iterator<Entry<String, List<MeasureDTO>>> it = group.entrySet().iterator();
		while (it.hasNext())
		{
			Entry<String, List<MeasureDTO>> next = it.next();
			List<MeasureDTO> value = next.getValue();
			if (value != null && value.size() > 0)
			{
				empty = false;
				json.append("{").append(generateData(next.getValue(), next.getKey())).append("},");
			}
		}
		json.deleteCharAt(json.length() - 1);
		json.append("]");
		if (empty) return null;
		return json.toString();
	}

	private static StringBuffer generateData(List<MeasureDTO> measures, String sensorName)
	{
		StringBuffer json = new StringBuffer("");
		json.append("\"key\":\"").append(sensorName).append("\",");
		//json.append("\"color\":\"").append(color).append("\",");
		json.append("\"values\":[").append(generateArray(measures)).append("]");
		return json;
	}

	private static StringBuffer generateArray(List<MeasureDTO> measures)
	{
		// 2014-03-09T23:06:57.084Z
		DateTimeFormat fmt = DateTimeFormat.getFormat("yyyy-MM-dd'T'HH:mm:ss.SZZ");
		StringBuffer json = new StringBuffer("");
		for (MeasureDTO m : measures)
		{
			json.append("{\"y\":");
			json.append(m.getMeasure()).append(",\"x\":\"");
			String date = fmt.format(m.getMeasure_date());
			json.append(date.substring(0, date.length()-4)+"0000");
			json.append("").append("\"},");
		}
		json.deleteCharAt(json.length() - 1);
		return json;
	}
	
	public static native void hideNVD3() /*-{
		$wnd.hideNVD3();
	}-*/;

	public static native void alert(String msg) /*-{
		$wnd.alert(msg);
	}-*/;

	public static native void generateNVD3(String xAxisTitle, String yAxisTitle, String data) /*-{
		$wnd.generateNVD3(xAxisTitle, yAxisTitle, data);
	}-*/;
}
