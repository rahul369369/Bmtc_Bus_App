package com.example.lenovo.bmtc_bus_app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Bus_Driver_Login extends AppCompatActivity {
    EditText driverid, pass1;
    Button logi;
    private static final String DB_URL = "jdbc:mysql://192.168.43.158:3306/bmtc_bus";
    private static final String USER = "zzzz";
    private static final String PASS = "abc123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus__driver__login);

        driverid = (EditText) findViewById(R.id.editText9);
        pass1 = (EditText) findViewById(R.id.editText10);

        logi = (Button) findViewById(R.id.button7);
        logi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  new Bus_Driver_Login().MyTask().execute();
                new MyTask().execute();

                //Toast.makeText(MainActivity.this, "Hello onClick:", Toast.LENGTH_SHORT).show();
                Toast.makeText(Bus_Driver_Login.this, "Hello: onClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private class MyTask extends AsyncTask<Void, Void, Void> {

        private String rf = driverid.getText().toString();
        private String pass = pass1.getText().toString();

        private String Rfid_db = "";
        private String pass_db = "";


        @Override
        protected Void doInBackground(Void... args0) {

            try {

                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement st = con.createStatement();
                final ResultSet rs = st.executeQuery("select * from driver_info");
                rs.next();

                Rfid_db = rs.getString(2);
                pass_db = rs.getString(3);

            } catch (Exception e) {

                e.printStackTrace();

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {

           /* login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String str1 = name.getText().toString();

                    if (fName.equals(str1))
                    {
                        Intent i = new Intent(getApplicationContext(), User_after_login.class);
                        startActivity(i);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Wrong Username:", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
            if (Rfid_db.equals(rf) && pass_db.equals(pass)) {
                Intent i = new Intent(getApplicationContext(), Driver_after_Login.class);
                startActivity(i);
            }
            else{

                Toast.makeText(Bus_Driver_Login.this, "Wrong Username or Password:", Toast.LENGTH_SHORT).show();
            }

            super.onPostExecute(result);

        }
    }
}