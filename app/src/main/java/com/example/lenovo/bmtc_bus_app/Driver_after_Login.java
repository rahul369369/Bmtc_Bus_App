package com.example.lenovo.bmtc_bus_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Driver_after_Login extends AppCompatActivity {
private Button button8,map2,passenger;
private TextView tv;


    private static final String DB_URL = "jdbc:mysql://192.168.43.158:3306/bmtc_bus";
    private static final String USER = "zzzz";
    private static final String PASS = "abc123";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_after__login);
        tv = (TextView)findViewById(R.id.textView);
        button8=(Button)findViewById(R.id.button8);
        map2=(Button)findViewById(R.id.button11);
        passenger = (Button)findViewById(R.id.button12);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent bdln=new Intent(getApplicationContext(),Bus_Driver_Login.class);

                startActivity(bdln);
            }
        });
        map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map2=new Intent(getApplicationContext(),DriverMapsActivity2.class);
                startActivity(map2);
            }
        });

        passenger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Driver_after_Login.MyTask().execute();

                Toast.makeText(Driver_after_Login.this, "total passenger refresh", Toast.LENGTH_SHORT).show();



            }
        });
    }

    private class MyTask extends AsyncTask<Void,Void,Void> {

        private String fName="";

        @Override
        protected Void doInBackground(Void... args0) {

            try{

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL,USER,PASS);

                //String str = "select * from client";
                //PreparedStatement st = con.prepareStatement(str);



                Statement st = con.createStatement();


                final ResultSet rs = st.executeQuery("SELECT MAX(b_id) from u_balance");

                rs.next();

                fName = rs.getString(1);
                // Toast.makeText(MainActivity.this, "Success: ", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e){

                e.printStackTrace();
                //Toast.makeText(MainActivity.this, "Exception: ", Toast.LENGTH_SHORT).show();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

            tv.setText(fName);


           /* login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str1 = name.getText().toString();

                    if (fName.equals(str1))
                    {
                        Intent i = new Intent(getApplicationContext(), Main2Activity.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong Username:", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/

            super.onPostExecute(result);
        }
    }
}
