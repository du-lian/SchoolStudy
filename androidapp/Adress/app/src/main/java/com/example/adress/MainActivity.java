package com.example.adress;

import android.R;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Spinner spProvince;
    private Spinner spCity;
    private Spinner spArea;
    private ArrayAdapter<String> adapter;
    private String[] provinceList;
    private String[] cityList;
    private String[] areaList;
    private int select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spProvince = findViewById(R.id.sp_province);
        spCity     = findViewById(R.id.sp_city);
        spArea     = findViewById(R.id.sp_area);

                initJsonData();



    }



    private void initJsonData() {
        try {
            InputStream inputStream = getResources().getAssets().open("data.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer stringBuffer = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line !=null){
                stringBuffer.append(line);
                line = bufferedReader.readLine();
            }


            final JSONArray jsonArray = new JSONArray(stringBuffer.toString());
            provinceList = new String[jsonArray.length()];
            for(int i = 0; i<jsonArray.length() ; i++){
                JSONObject provinceObject =  jsonArray.getJSONObject(i);
                String province = provinceObject.getString("name");
                provinceList[i] = province;

//                Log.e(TAG,province);
                adapter = new ArrayAdapter<String>(this, R.layout.simple_spinner_item,provinceList);
                adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                spProvince.setAdapter(adapter);

                spProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        try {
                            select=position;
                            JSONObject provinceObject = jsonArray.getJSONObject(position);
                            JSONArray cityJson = provinceObject.getJSONArray("city");
                            cityList = new String[cityJson.length()];
                            for(int j=0;j<cityJson.length();j++){
                                JSONObject cityObject = cityJson.getJSONObject(j);
                                String cityName = cityObject.getString("name");
                                cityList[j] = cityName;
                            }
                            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, cityList);
                            adapter2.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                            spCity.setAdapter(adapter2);

                            spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    try {
                                        JSONObject provinceObject = jsonArray.getJSONObject(select);
                                        JSONArray  cityJson = provinceObject.getJSONArray("city");
                                        JSONObject cityObject = cityJson.getJSONObject(position);
                                        JSONArray area = cityObject.getJSONArray("area");
                                        areaList = new String[area.length()];
                                        for (int k=0; k<areaList.length; k++){
                                            String areaName = area.getString(k);
                                            areaList[k] = areaName;
                                        }
                                        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_item, areaList);
                                        adapter3.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
                                        spArea.setAdapter(adapter3);
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Log.e(TAG,"请选择你的省份");
                    }
                });


            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }







    }

    private void areaSpinner(List<String> areaList) {


    }

    private void citySpinner(List<String> cityList,int index) {
        adapter =new ArrayAdapter<String>(this, R.layout.simple_spinner_item,cityList);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spCity.setAdapter(adapter);

        spCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) parent.getItemAtPosition(index);
                if (str.equals("请选择")) {
                    spArea.setVisibility(View.INVISIBLE);
                } else {
                    //显示第三个Spinner3
                    spArea.setVisibility(View.VISIBLE);


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.e(TAG,"请选择你的城市");
            }
        });
    }



}
