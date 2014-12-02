package mx.cinvestav.gdl.iot.cloudclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CloudClient
{
//	private static final String url = "http://localhost:8888/_ah/api/iotService/v1/updatedataresponse";
	//	private static final String url = "https://weighty-utility-768.appspot.com/_ah/api/iotService/v1/updatedataresponse";

	private static final String url = "http://localhost:8888/_ah/api/iotService/v1/createController";
	private static GsonBuilder builder = new GsonBuilder();
	private static Gson gson = builder.create();

	public static UpdateDataResponse updateData(UpdateDataRequest req) throws IOException
	{

		URL object = new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();

		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");

		String res = gson.toJson(req);
		System.out.println(res);
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		wr.write(res);
		wr.flush();
		StringBuilder sb = new StringBuilder();
		int HttpResult = con.getResponseCode();
		if (HttpResult == HttpURLConnection.HTTP_OK)
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),
					"utf-8"));
			String line = null;
			while ((line = br.readLine()) != null)
			{
				sb.append(line + "\n");
			}
			br.close();
			System.out.println("" + sb.toString());

		}
		else
		{
			System.out.println(con.getResponseCode() + " " + con.getResponseMessage());
		}
		return gson.fromJson(sb.toString(), UpdateDataResponse.class);
	}

	public static void main(String[] args) throws IOException
	{

		//creamos un object UpdataDataRequest
		UpdateDataRequest request = new UpdateDataRequest();

		SmartThingData[] smartThings = new SmartThingData[1];
		for (int i = 0; i < smartThings.length; i++)
		{
			SensorData[] sensorData = new SensorData[1];
			for (int j = 0; j < sensorData.length; j++)
			{
				Data measures[] = new Data[500];
				for (int k = 0; k < measures.length; k++)
				{
					measures[k] = new Data();
					measures[k].setData("data" + i + j + k);
					Date date = new Date();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ssss");

					measures[k].setTime(format.format(date));
				}
				sensorData[j] = new SensorData();
				sensorData[j].setMeasures(measures);
				sensorData[j].setSensorId(j);
			}
			smartThings[i] = new SmartThingData();
			smartThings[i].setSensorData(sensorData);
			smartThings[i].setSmartThingId(i);
		}
		request.setSmartThingData(smartThings);
		request.setControllerId(0);

		//enviamos
		UpdateDataResponse response = updateData(request);
		System.out.println("message: " + response.getMessage());
	}
}