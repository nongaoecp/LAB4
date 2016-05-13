package com.example.ao.lab3;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL("http://soeleh.com:8888");
            Scanner sc = new Scanner(url.openStream());
            StringBuffer buf = new StringBuffer();
            while(sc.hasNext()){
                buf.append(sc.next());
            }
            JSONObject jsonObject = new JSONObject(buf.toString());
            JSONArray dataArr = jsonObject.getJSONArray("data");
            TableLayout tlb = (TableLayout)findViewById(R.id.tlb);

            for(int i=0; i<dataArr.length(); i++){
                TableRow tRow = new TableRow(this);
                TextView txt = new TextView(this);
                txt.setText(dataArr.get(i).toString());
                tRow.addView(txt);
                tlb.addView(tRow);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
