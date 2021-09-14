package com.example.to_do_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp_form extends AppCompatActivity {
    DataBaseTask MYDB;
    EditText user, pass, mobilenum, email;
    Button Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_form);
        MYDB = new DataBaseTask(this);
        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.pass);
        mobilenum = (EditText) findViewById(R.id.number);
        email = (EditText) findViewById(R.id.emailid);
        Register = (Button) findViewById(R.id.btn_reg);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user.getText().toString().equals("") || pass.getText().toString().equals("") || mobilenum.getText().toString().equals("") || email.getText().toString().equals(""))
                    Toast.makeText(SignUp_form.this, "Please fill all details", Toast.LENGTH_LONG).show();
                else {
                    boolean ischeckuser = MYDB.checkuser(user.getText().toString());
                    boolean ischeckother = MYDB.checkother(email.getText().toString(), mobilenum.getText().toString());
                    boolean ischeckuserpass = MYDB.checkuserpass(user.getText().toString(), pass.getText().toString());
                    if (ischeckuserpass == false && ischeckuser == false && ischeckother == false) {
                        boolean inserted = MYDB.insertValue(user.getText().toString(), pass.getText().toString(), mobilenum.getText().toString(), email.getText().toString());
                        if (inserted == true) {
                            Toast.makeText(SignUp_form.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), Login_form.class);
                            startActivity(intent);
                        }
                    }

                }
            }
        });
    }
}

