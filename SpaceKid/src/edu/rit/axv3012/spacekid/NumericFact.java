package edu.rit.axv3012.spacekid;

import java.io.InputStream;
import java.net.URL;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class NumericFact extends Activity {
	TextView txtNumeric;
	String numericFact;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.numeric_facts);

		txtNumeric = (TextView) findViewById(R.id.txtNumericF);
		consumeWebService(null);

	}

	public void consumeWebService(RequestParams params) {
		AsyncHttpClient client = new AsyncHttpClient();
		client.get("http://numbersapi.com/random/math?json",
				new AsyncHttpResponseHandler() {

					@Override
					public void onFailure(int statusCode, Header[] headers,
							byte[] responseBody, Throwable error) {
						if (statusCode == 404) {
							System.out.println("Requested resource not found!");
						}

					}

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] responseBody) {
						// responseTextView.setText(new String(responseBody));
						try {
							JSONObject reader = new JSONObject(new String(
									responseBody));

							String numericF = reader.getString("text");
							txtNumeric.setText(numericF);

						} catch (JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						try {
							JSONArray array = new JSONArray(new String(
									responseBody));

						} catch (JSONException e) {
							e.printStackTrace();
						}
					}

				});

	}
}
