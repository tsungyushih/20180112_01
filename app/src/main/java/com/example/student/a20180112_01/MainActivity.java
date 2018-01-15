package com.example.student.a20180112_01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        StringRequest request = new StringRequest("http://data.ntpc.gov.tw/od/data/api/BF90FA7E-C358-4CDA-B579-B6C84ADC96A1?$format=json",  //台北市開放平台的資料網址
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("NET", response);
//                        方法一、用JsonArray與JSONObject
//
//                        try {
//                            JSONArray array = new JSONArray(response);
//
//                            int i;
//                            for(i=0;i<array.length();i++)
//                            {
//                                JSONObject obj = array.getJSONObject(i);
//                                String str = obj.getString("district");
//                                Log.d("NET1",str);
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                        public void onErrorResponse(VolleyError error) {
//                            }
//

//                      方法二、使用Gson的函式庫
                        Gson gson = new Gson();
                        Animal[] houses = gson.fromJson(response, Animal[].class);

                        for (Animal a : houses) {
                            Log.d("NET", a.district + "," + a.address);
                        }
                    }
                }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {

                }
        });
                queue.add(request);
                queue.start();
    }
}
