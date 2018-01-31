package com.example.lenovo.bmtc_bus_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class User_after_Login extends AppCompatActivity {
private TextView textView2;
Button balance,sinout,map,refresh,station;


    private static final String DB_URL = "jdbc:mysql://192.168.43.158:3306/bmtc_bus";
    private static final String USER = "zzzz";
    private static final String PASS = "abc123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_after__login);

        refresh = (Button)findViewById(R.id.refresh);
        textView2=(TextView)findViewById(R.id.textView2);
        balance=(Button)findViewById(R.id.button5);
        sinout=(Button)findViewById(R.id.button9);
        map=(Button)findViewById(R.id.button10);
      //  station=(Button)findViewById(R.id.button2);
        sinout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ual=new Intent(getApplicationContext(),User_Login.class);
                startActivity(ual);
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map=new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(map);
            }
        });
        balance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bl=new Intent(getApplicationContext(),Balance.class);
                startActivity(bl);
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new MyTask().execute();

                Toast.makeText(User_after_Login.this, "REFRESH...", Toast.LENGTH_SHORT).show();


            }
        });

        /*Intent i = getIntent();
        String str2 = i.getStringExtra("balance");
        textView2.setText(str2);*/
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


                final ResultSet rs = st.executeQuery("select * from u_balance");

                rs.next();

                fName = rs.getString(6);
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

            textView2.setText(fName);


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
