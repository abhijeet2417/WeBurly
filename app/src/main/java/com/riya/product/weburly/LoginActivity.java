package com.riya.product.weburly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       // getActionBar().hide();

        //hello it is guit test please igoner
        //hello test

        TextView txt_registaration=(TextView) findViewById(R.id.txt_registaration);
        Button btn_signup=(Button) findViewById(R.id.btn_signup);
        Button btn_cancel= (Button) findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LoginActivity.this,MainActivity.class);
                startActivity(it);
                finish();
            }
        });

        txt_registaration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(it);
            }
        });
    }
}
