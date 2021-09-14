package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login_form extends AppCompatActivity {
    DataBaseTask MYdb;
    EditText user , pass;
    Button reg , log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        MYdb = new DataBaseTask(this);
        user = (EditText) findViewById(R.id.user);
        pass = (EditText) findViewById(R.id.password);
        reg = (Button) findViewById(R.id.btn_register);
        log = (Button) findViewById(R.id.btn_log);


        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().equals("") || pass.getText().toString().equals(""))
                    Toast.makeText(Login_form.this, "Please Fill All Details", Toast.LENGTH_LONG).show();
                 else {
                    boolean userexists = MYdb.checkuser(user.getText().toString());
                    boolean userpassexists = MYdb.checkuserpass(user.getText().toString(), pass.getText().toString());
                    if (userexists == true && userpassexists == true) {
                        Toast.makeText(Login_form.this, "Login Successfull", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                    else{
                        Toast.makeText(Login_form.this, "Don't have an account \n Register To Create Account", Toast.LENGTH_LONG).show();

                    }

                }
            }
        });
    }


    public void btn_signupform(View view) {
        startActivity(new Intent(getApplicationContext(),SignUp_form.class));
    }
}