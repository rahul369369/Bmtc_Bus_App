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
import java.sql.Statement;

public class User_Signup extends AppCompatActivity {
private EditText rfid,password,name,email,mobileno;
private Button signout;

    private static final String DB_URL = "jdbc:mysql://192.168.43.158:3306/bmtc_bus";
    private static final String USER = "zzzz";
    private static final String PASS = "abc123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__signup);

        rfid=(EditText)findViewById(R.id.editText4);
        password=(EditText)findViewById(R.id.editText5);
        name=(EditText)findViewById(R.id.editText6);
        email=(EditText)findViewById(R.id.editText7);
        mobileno=(EditText)findViewById(R.id.editText8);

        signout=(Button)findViewById(R.id.button6);

      signout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Send objSend = new Send();
              objSend.execute(" ");

              Intent sout=new Intent(getApplicationContext(),User_Login.class);
              startActivity(sout);
          }
      });
    }
    private class Send extends AsyncTask<String,String,String> {
        String text = rfid.getText().toString();
        String text1 = password.getText().toString();
        String text2 = name.getText().toString();
        String text3 = email.getText().toString();
        String text4 = mobileno.getText().toString();

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {

            try{
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL,USER,PASS);

                if(con==null){
                    Toast.makeText(User_Signup.this, "not connected", Toast.LENGTH_SHORT).show();

                }
                else{

                   // String query = "INSERT INTO user_reg (u_rfid,u_password,u_name,u_email,u_mobile) VALUES ('text','text1','text2','text3', 'text4')";

                    PreparedStatement stmt=con.prepareStatement("insert into user_reg (u_rfid,u_password,u_name,u_email,u_mobile) values(?,?,?,?,?)");

                    stmt.setString(1,text);
                    stmt.setString(2,text1);
                    stmt.setString(3,text2);
                    stmt.setString(4,text3);
                    stmt.setString(5,text4);

                    stmt.executeUpdate();

                    Toast.makeText(User_Signup.this, "insert successfully", Toast.LENGTH_SHORT).show();
                }
                con.close();


            }catch (Exception e){
                e.printStackTrace();
            }

            return null;
        }

    }
}
