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

public class Balance extends AppCompatActivity {

    EditText name, cardno;
    EditText cvv, mm, rs;
    Button recharge;


    private static final String DB_URL = "jdbc:mysql://192.168.43.158:3306/bmtc_bus";
    private static final String USER = "zzzz";
    private static final String PASS = "abc123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        rs = (EditText) findViewById(R.id.rs);
        name = (EditText) findViewById(R.id.name);
        cardno = (EditText) findViewById(R.id.cardno);
        cvv = (EditText) findViewById(R.id.cvv);
        mm = (EditText) findViewById(R.id.mm);


        recharge = (Button) findViewById(R.id.recharge);

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Balance.Send objSend = new Balance.Send();
                objSend.execute(" ");

                Intent balanc = new Intent(getApplicationContext(), User_after_Login.class);
                // String str1 = rs.getText().toString();
                // balanc.putExtra("balance",str1);
                startActivity(balanc);
            }
        });
    }


    private class Send extends AsyncTask<String, String, String> {
        String text = name.getText().toString();
        String text1 = cardno.getText().toString();
        String text2 = cvv.getText().toString();
        String text3 = mm.getText().toString();
        String text4 = rs.getText().toString();
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection(DB_URL, USER, PASS);

                if (con == null) {
                    Toast.makeText(Balance.this, "not connected", Toast.LENGTH_SHORT).show();

                } else {

                    // String query = "INSERT INTO user_reg (u_rfid,u_password,u_name,u_email,u_mobile) VALUES ('text','text1','text2','text3', 'text4')";

                    PreparedStatement stmt = con.prepareStatement("insert into u_balance(b_name,b_cardno,b_cvv,b_valid,b_rupees) values(?,?,?,?,?)");
                    stmt.setString(1, text);
                    stmt.setString(2, text1);
                    stmt.setString(3, text2);
                    stmt.setString(4, text3);
                    stmt.setString(5, text4);

                    stmt.executeUpdate();

                    Toast.makeText(Balance.this, "Recharge successfully....", Toast.LENGTH_SHORT).show();
                }
                con.close();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
