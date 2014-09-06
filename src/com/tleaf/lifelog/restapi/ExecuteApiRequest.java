package com.tleaf.lifelog.restapi;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.utils.URLEncodedUtils;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

public class ExecuteApiRequest extends AsyncTask<ApiRequest, Void, String> {

	@Override
	protected String doInBackground(ApiRequest... requests) {

		String url = requests[0].getUrl();
		url = url
				+ "?"
				+ URLEncodedUtils.format(requests[0].getNameValuePairs(),
						"UTF-8");

		String response = null;

		Log.i("ExecuteRequest", url);

		try {

			URL Url = new URL(url);

			// Opening Connection and Setting Headers.
			HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
			conn.setRequestMethod( requests[0].getRequestMethod() );
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);

			// Writing Json Object mapped String into URLConnection
			DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
			Gson gson = new Gson();
			wr.writeChars(gson.toJson(requests[0].getKeyValueMap()));

			// Receiving Response
			response = inputStreamToString(conn.getInputStream());
			Log.i("HttpResponseLine", response);

		} catch (IOException e) {
			Log.i("IOException", "Exception Occured while opening URLConnection or DataOutputStream or Resopnse Receiving");
			e.printStackTrace();
		}

		return response;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}

	private String inputStreamToString(InputStream is) throws IOException {
		String s = "";
		String line = "";

		// Wrap a BufferedReader around the InputStream
		BufferedReader rd = new BufferedReader(new InputStreamReader(is));

		// Read response until the end
		while ((line = rd.readLine()) != null) {
			s += line;
		}

		// Return full string
		return s;
	}
}
