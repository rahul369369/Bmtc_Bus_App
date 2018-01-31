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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class User_Login extends AppCompatActivity {
    private EditText Rfid, Password;

    private Button login, signup;

    private static final String DB_URL = "jdbc:mysql://192.168.43.158:3306/bmtc_bus";
    private static final String USER = "zzzz";
    private static final String PASS = "abc123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);

        Rfid = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);

        login = (Button) findViewById(R.id.button3);
        signup = (Button) findViewById(R.id.button4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyTask().execute();
                //Toast.makeText(MainActivity.this, "Hello onClick:", Toast.LENGTH_SHORT).show();
                Toast.makeText(User_Login.this, "Hello: onClick", Toast.LENGTH_SHORT).show();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sinp = new Intent(getApplicationContext(), User_Signup.class);
                startActivity(sinp);
            }
        });
    }
        public class MyTask extends AsyncTask<Void, Void, Void> {

            private String rf = Rfid.getText().toString();
            private String pass = Password.getText().toString();

            private String Rfid_db = "";
            private String pass_db = "";


            @Override
            protected Void doInBackground(Void... args0) {

                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
                    Statement st = con.createStatement();
                    final ResultSet rs = st.executeQuery("select * from user_reg");
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

            if(Rfid_db.equals(rf) && pass_db.equals(pass)) {
                Intent i = new Intent(getApplicationContext(), User_after_Login.class);
                startActivity(i);
            }
            else
            {
                Toast.makeText(User_Login.this, "Wrong Username or Password:", Toast.LENGTH_SHORT).show();
            }
                super.onPostExecute(result);

                }
        }
}