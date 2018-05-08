package com.aminmemariani.apps.bmicalculator;
import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.aminmemariani.apps.bmicalculator.dataModel.Bmi;
import com.aminmemariani.apps.bmicalculator.databinding.ActivityMainBinding;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView Weight;
    TextView Height;
    Dialog d;
    boolean green = false;
    private ActivityMainBinding viewHandler;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        d= new Dialog(MainActivity.this);
        d.setContentView(R.layout.credit);
        d.setCanceledOnTouchOutside(true);
        d.show();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewHandler = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TextView t = viewHandler.txtBmi;
        List<Bmi> b = Bmi.find(Bmi.class, null, null, null, "Date DESC", "1");
        if (b.isEmpty()) {Toast.makeText(getApplicationContext(), "No Record", Toast.LENGTH_SHORT).show();
        } else {
            Bmi lastBmi = new Bmi();
            lastBmi = b.get(b.size()-1);
            t.setText(lastBmi.getBmi());
            TextView s = viewHandler.txtStatus;
            float bmi = Float.valueOf(t.getText().toString());
            String status = null;
            if (bmi <= 18.5)
                status = "Slim";
            else if (bmi > 18.5 && bmi <= 25){
                status = "Normal";
                green = true;
            }
            else if (bmi > 25 && bmi <= 29.9){
                green = false;
                status = "Over weight";
            }
            else if (bmi > 29.9 && bmi <= 34.9){
                status = "First Level Fat";
                green = false;
            }
            else if (bmi > 34.9 && bmi <= 39.9) {
                status = "Second Level Fat";
                green = false;
            }
            else if (bmi > 39.9){
                status = "Third Level Fat";
                green = false;
            }
            s.setText(status);
            if (green) {
                s.setTextColor(Color.GREEN);
            }else {s.setTextColor(Color.RED);}
        }
    }
    public void showHistory(View v) {
        Intent i = new Intent(this, History.class);
        startActivity(i);
    }

    public void addNewBmi() {
        d = new Dialog(MainActivity.this);
        d.setContentView(R.layout.add_bmi);
        d.setCanceledOnTouchOutside(true);
        d.setTitle("Add BMI");
        d.show();
    }

    public void calculateBmi() {
        Weight = (TextView) d.findViewById(R.id.weight);
        Height = (TextView) d.findViewById(R.id.height);
        if (Weight.getText().toString().equals("") || Height.getText().toString().equals("")) {
            Toast.makeText(MainActivity.this, "Oops! fill up the form please!", Toast.LENGTH_SHORT).show();
        } else {
            Float weight = Float.valueOf(Weight.getText().toString());
            Float height = Float.valueOf(Height.getText().toString());
            float bmi = weight / (height * height);
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.US);
            Date date = new Date();
            Bmi bmiData = new Bmi(String.valueOf(bmi), dateFormat.format(date));
            bmiData.save();
            String status = "";
            if (bmi <= 18.5){
                status = "Slim";
                green = false;
            }
            else if (bmi > 18.5 && bmi <= 25){
                status = "Normal";
                green=true;
            }
            else if (bmi > 25 && bmi <= 29.9){
                status = "Over weight";
                green=false;
            }
            else if (bmi > 29.9 && bmi <= 34.9){
                status = "First Level Fat";
                green = false;
            }
            else if (bmi > 34.9 && bmi <= 39.9){
                status = "Second Level Fat";
                green = false;
            }
            else if (bmi > 39.9){
                status = "Third Level Fat";
                green = false;
            }
            float normalWeightLow = 18.5f * height * height;
            float normalWeightHigh = 25 * height * height;
            String normalWeightLimit = String.valueOf(normalWeightLow) + " to " + String.valueOf(normalWeightHigh);
            TextView stat = (TextView) findViewById(R.id.txt_status);
            stat.setText(status);
            TextView BmiText = (TextView) findViewById(R.id.txt_bmi);
            BmiText.setText(String.valueOf(bmi));
            TextView range = (TextView) findViewById(R.id.range);
            range.setText(normalWeightLimit);
            TextView ideal = (TextView) findViewById(R.id.ideal);
            ideal.setText(String.valueOf(22*height * height));
            if (green) {
                stat.setTextColor(Color.GREEN);
            }else {
                stat.setTextColor(Color.RED);
            }d.hide();
        }
    }
    public void ok(View v){d.hide();}
}