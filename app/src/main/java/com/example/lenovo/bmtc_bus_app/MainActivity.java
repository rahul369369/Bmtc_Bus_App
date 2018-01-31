package com.example.lenovo.bmtc_bus_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Driver;


    public class MainActivity extends AppCompatActivity {


        private LinearLayout mainLayout;
        Button user,bus_driver;

        private int[] images = {R.drawable.dd1, R.drawable.dd2, R.drawable.dd3,
                R.drawable.dd4, R.drawable.dd5, R.drawable.dd6, R.drawable.dd7};

        private View cell;
        private TextView text;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setContentView(R.layout.activity_main);

            mainLayout = (LinearLayout) findViewById(R.id.linearLayout1);
            user=(Button)findViewById(R.id.button4);
            bus_driver=(Button)findViewById(R.id.button3);

            for (int i = 0; i < images.length; i++){
                cell = getLayoutInflater().inflate(R.layout.cell, null);

                final ImageView imageView = (ImageView) cell.findViewById(R.id._image);
                imageView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this,
                                (CharSequence) imageView.getTag(), Toast.LENGTH_SHORT).show();
                    }
                });



                imageView.setTag("BUS NUMBER"+(i+1));
                imageView.setImageResource(images[i]);
                mainLayout.addView(cell);

                user.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View v) {
                        Intent userlog= new Intent(getApplicationContext(),User_Login.class);
                        startActivity(userlog);
                    }
                });

                bus_driver.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent bus = new Intent(getApplicationContext(),Bus_Driver_Login.class);
                        startActivity(bus);
                    }
                });


            }
        }
    }
