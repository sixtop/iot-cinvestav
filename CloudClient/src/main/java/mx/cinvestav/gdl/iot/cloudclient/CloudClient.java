package mx.cinvestav.gdl.iot.cloudclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CloudClient {
	private static final String url = "https://weighty-utility-768.appspot.com/_ah/api/iotService/v1/updatedataresponse";
	private static GsonBuilder builder = new GsonBuilder();
	private static Gson gson = builder.create();

	
	public static UpdateDataResponse updateData(UpdateDataRequest req) throws IOException {
		
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");

        String res= gson.toJson(req);
        System.out.println(res);
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write(res);
        wr.flush();
		StringBuilder sb = new StringBuilder();
        int HttpResult = con.getResponseCode();
        if (HttpResult == HttpURLConnection.HTTP_OK) {
            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            System.out.println("" + sb.toString());
            
        } else {
            System.out.println(con.getResponseCode() +" "+ con.getResponseMessage());
        }
        return gson.fromJson(sb.toString(), UpdateDataResponse.class);
	}
	public static void main(String[] args) throws IOException{
		
		//creamos un object UpdataDataRequest
		UpdateDataRequest request = new UpdateDataRequest();
		request.setControllerId("a123");
		SensorData[] sensorData = new SensorData[1];
		SensorData data = new SensorData();
		Measure measures[] = new Measure[1];
		Measure measure = new Measure();
		measure.setData("data");
		measure.setTime("time");
		measures[0]=measure;
		data.setMeasures(measures);
		data.setSensorId("111a");
		sensorData[0]=data;
		request.setSensorData(sensorData);
		request.setSensorData(sensorData);
		
		//enviamos
		UpdateDataResponse response=updateData(request);
		System.out.println("message: "+response.getMessage());
	}
}
