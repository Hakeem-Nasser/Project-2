package com.example.login;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.ims.RegistrationManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.jar.Attributes;

public class registration extends AppCompatActivity {
    EditText etEmail,etPass,etName,etConPass;
    Button btnLogin, view;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etConPass = findViewById(R.id.etConPass);
        btnLogin = findViewById(R.id.btnReg);
        view = findViewById(R.id.btnView);
        db = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, email, pass, conPass;
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                pass = etPass.getText().toString();
                conPass = etConPass.getText().toString();

                Boolean checkbtnlogin = db.insertuserdata(name, email, pass);

                Intent i = new Intent(registration.this, webb.class);
                startActivity(i);
                finish();


                if (name.equals("")) {
                    Toast.makeText(registration.this, "Name Required", Toast.LENGTH_SHORT).show();

                } else if (email.equals("")) {

                    Toast.makeText(registration.this, "Email Required", Toast.LENGTH_SHORT).show();
                } else if (pass.equals("")) {

                    Toast.makeText(registration.this, "Password Required", Toast.LENGTH_SHORT).show();
                } else if (conPass.equals("")) {

                    Toast.makeText(registration.this, "Confirm password", Toast.LENGTH_SHORT).show();
                } else if (!conPass.equals("pass")) {

                    Toast.makeText(registration.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    //authentication
                }
            }


        });
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.getdata();
                if (res.getCount() == 0) {

                    Toast.makeText(registration.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;

                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name:" + res.getString(0) + "\n");
                    buffer.append("Email:" + res.getString(1) + "\n");
                    buffer.append("Password:" + res.getString(2) + "\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(registration.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }

}