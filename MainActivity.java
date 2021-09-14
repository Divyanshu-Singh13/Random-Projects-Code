package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Creating Variables
    private EditText Height;
    private EditText Weight;
    private Button Evaluate;
    private TextView H;
    private TextView U;
    private TextView ov;
    private TextView ob;

    private double bmi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing variables to their respective View in XML

        Height = (EditText) findViewById(R.id.height);
        Weight = (EditText) findViewById(R.id.weight);
        Evaluate = (Button) findViewById(R.id.evaluate);
        H = (TextView) findViewById(R.id.Healthy);
        U = (TextView) findViewById(R.id.Underweight);
        ov = (TextView) findViewById(R.id.Overweight);
        ob = (TextView) findViewById(R.id.Obese);


        //Input Height code
        Height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Height.setText(Height.getText());
            }
        });
        // Input Weight
        Weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Weight.setText(Weight.getText());
            }
        });
        // on Button Click
        Evaluate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double h ;
                double h1 = Double.parseDouble(String.valueOf(Height.getText()));
                      h = Math.pow(h1,2);
                double h2 = Double.parseDouble(String.valueOf(Weight.getText()));
               bmi = h2 / h;
               if(bmi <18.5){
                   U.setText("Underweight");
                   ob.setText("");
                   ov.setText("");
                   H.setText("");

               }
               else if (bmi == 18.5 || bmi > 18.5 && bmi <= 24.9){
                   H.setText("Heathy");
                   ob.setText("");
                   ov.setText("");
                   U.setText("");
               }
               else if (bmi == 25.0 || bmi > 25.0 && bmi <= 29.9){
                   ov.setText("Overweight");
                   U.setText("");
                   ob.setText("");
                   H.setText("");
               }
               else if(bmi > 29.0){
                   ob.setText("Obese");
                   U.setText("");
                   ov.setText("");
                   H.setText("");
               }
            }
        });
    }
}