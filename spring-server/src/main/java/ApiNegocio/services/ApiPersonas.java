package ApiNegocio.services;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonFormat.Value;

public class ApiPersonas {
	URL url;

	public boolean validar_id(String id,String token) {
		String query_url = "http://localhost:8090/personas/getPersona";
 		String json = "{\"persona\":[{\"id\": \""+id+"\",\"token\": \""+token+"\"}]}";
		System.out.println(json);
		try {
			//se conecta a micro servicio de personas
			url = new URL(query_url);
			HttpURLConnection com = (HttpURLConnection) url.openConnection();
			com.setConnectTimeout(5000);
			com.setRequestProperty("Content-Type", "application/json");
			com.setRequestProperty("Accept", "application/json");
			com.setDoOutput(true);
			com.setDoInput(true);
			com.setRequestMethod("POST");
			OutputStream os = com.getOutputStream();
			os.write(json.getBytes("UTF-8"));
			os.close();
			//lee los datos que me retorna el micro servicio
			InputStream in = new BufferedInputStream(com.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");

			System.out.println(result);
			JSONObject jsonArray = new JSONObject(result);
			System.out.println(jsonArray.get("id"));
			if (!jsonArray.get("id").equals("") && !jsonArray.get("id").equals(null)) {
				in.close();
				com.disconnect();
				return true;
			}
			in.close();
			com.disconnect();
			return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;

		}

	}
}
