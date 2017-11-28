package com.example.sindhu.jsonexample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
String colors;
TextView textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview= findViewById(R.id.text);
        textview.setMovementMethod(new ScrollingMovementMethod());

        /*Defining Json string */
         colors = " [\n" +

                " {\n" +

                "   \"color\": \"black\",\n" +

                "   \"category\" : \"hue\",\n" +

                "   \"type\": \"primary\",\n" +

                "   \"code\": {\n" +

                "     \"rgba\": [255, 255, 255, 1],\n" +

                "     \"hex\": \"#000\"\n" +

                "   }\n" +

                "  },\n" +

                "   {\n" +

                "   \"color\": \"white\", \n" +

                "   \"category\": \"value\", \n" +

                "   \"code\": {\n" +

                "   \"rgba\": [0, 0, 0, 1], \n " +

                "   \"hex\": \"#FFF\" \n" +

                "   }\n" +

                "   }, \n" +

                "   {\n" +

                "   \"color\": \"red\", \n" +

                "   \"category\": \"value\", \n" +

                "   \"type\": \"primary\", \n" +

                "   \"code\": {\n" +

                "   \"rgba\": [255, 0, 0, 1], \n " +

                "   \"hex\": \"#FF0\" \n" +

                "   }\n" +

                "   }, \n" +

                "   {\n" +

                "   \"color\": \"blue\", \n" +

                "   \"category\": \"hue\", \n" +

                "   \"type\": \"primary\", \n" +

                "   \"code\": {\n" +

                "   \"rgba\": [0, 0, 255, 1], \n" +

                "   \"hex\": \"#00F\" \n" +

                "   }\n" +

                "   }, \n" +

                "   {\n" +

                "   \"color\": \"yellow\", \n" +

                "   \"category\": \"hue\", \n" +

                "   \"type\": \"primary\", \n" +

                "   \"code\": {\n" +

                "   \"rgba\": [255, 255, 0, 1], \n" +

                "   \"hex\": \"#FF0\" \n" +

                "   }\n" +

                "   }, \n" +

                "  {\n" +

                "    \"color\": \"green\",\n" +

                "    \"category\" : \"hue\",\n" +

                "    \"type\": \"secondary\",\n" +

                "    \"code\": {\n" +

                "    \"rgba\": [0, 255, 0, 1],\n" +

                "    \"hex\": \"#0F0\"\n" +

                "     }\n" +

                "   }\n" +

                " ]";

    }
    /*Concatination of color values when they have green component*/
    public void listMethod(View view) throws JSONException
    {
        String concatString="";

        try {

            JSONArray colorsData = (JSONArray) new JSONTokener(colors).nextValue();
            for(int i=0;i<colorsData.length();i++) {

                JSONObject getColor = colorsData.getJSONObject(i);
                JSONObject getColorCode = getColor.getJSONObject("code");
                JSONArray getrgba = (JSONArray) getColorCode.get("rgba");

                String getSecondIndexValue = Integer.toString(getrgba.getInt(1));

                if (getSecondIndexValue.equals("255"))
                    concatString+=" "+(String)getColor.get("color");
            }

            textview.setText(concatString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /*count number of green components*/
    public void countMethod(View view) throws JSONException
    {
        int countValue=0;

        try {
            JSONArray colorsData = (JSONArray) new JSONTokener(colors).nextValue();

            for(int i=0;i<colorsData.length();i++) {

                JSONObject getColor = colorsData.getJSONObject(i);
                JSONObject getColorCode = getColor.getJSONObject("code");
                JSONArray getrgba = (JSONArray) getColorCode.get("rgba");

                String getSecondIndexValue = Integer.toString(getrgba.getInt(1));

                if (getSecondIndexValue.equals("255"))
                    countValue++;
            }

            textview.setText(Integer.toString(countValue));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
/*Adding new value to Json string*/
    public void modifyMethod(View view) throws JSONException
    {
        try {

            JSONArray colorsData = (JSONArray) new JSONTokener(colors).nextValue();

            JSONArray rgbaArray=new JSONArray();
            JSONObject putColor = new JSONObject();
            JSONObject putCode = new JSONObject();

            rgbaArray.put(255);
            rgbaArray.put(165);
            rgbaArray.put(1);
            rgbaArray.put(0);

            putCode.put("rgba",rgbaArray);
            putCode.put("hex","#FA0");

            putColor.put("color", "orange");
            putColor.put("category","hue");
            putColor.put("code", putCode);

            colorsData.put(putColor);

            textview.setText(colorsData.toString(2));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

