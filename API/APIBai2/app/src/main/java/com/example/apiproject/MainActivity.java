package com.example.apiproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView tvCode, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCode = findViewById(R.id.tv_code);
        tvDescription = findViewById(R.id.tv_description);

        requestAccessToken();
    }

    private void requestAccessToken() {
        String url = "https://flow-fbmj.onrender.com/auth/local";
        JSONObject body = new JSONObject();
        try {
            body.put("username", "John");
            body.put("password", "123");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                body,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String code = response.getString("access_token");
                            String description = "Access token received successfully.";
                            tvCode.setText(code);
                            tvDescription.setText(description);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String code = "Error";
                        String description = "Failed to receive access token.";
                        tvCode.setText(code);
                        tvDescription.setText(description);
                    }
                }
        );

        Volley.newRequestQueue(this).add(request);
    }
}