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
	private GsonBuilder builder = new GsonBuilder();
	private Gson gson = builder.create();

	
	public static void updateData() throws IOException {
		
        URL object = new URL(url);

        HttpURLConnection con = (HttpURLConnection) object.openConnection();

        con.setDoOutput(true);
        con.setDoInput(true);
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestMethod("POST");
        /*
        JSONObject cred = new JSONObject();

        JSONObject auth = new JSONObject();

        JSONObject parent = new JSONObject();

        cred.put("username", "adm");

        cred.put("password", "pwd");

        auth.put("tenantName", "adm");

        auth.put("passwordCredentials", cred.toString());

        parent.put("auth", auth.toString());

        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

        wr.write(parent.toString());

        wr.flush();*/
        
        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
        wr.write("{ \"controllerId\": \"1\",\"sensorData\": [{ \"measures\": [ { \"data\": \"ASD\",   \"time\": \"ASD\" } ],   \"sensorId\": \"2\"  } ]}");

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
	}
	public static void main(String[] args) throws IOException{
		updateData();
	}
}
