package com.example.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    EditText uname,email,phone,pass,conf_pass;
    Button registerbutton;
    TextInputLayout layoutPassword;
    TextInputEditText txtPassword;
    FirebaseDatabase db;
    DatabaseReference ref;
    User user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        layoutPassword = findViewById(R.id.layoutpass);
        txtPassword = findViewById(R.id.txt_pass);
        uname = findViewById(R.id.txt_name);
        email = findViewById(R.id.txt_email);
        phone = findViewById(R.id.txt_phone);
        pass = findViewById(R.id.txt_pass);
        conf_pass = findViewById(R.id.txt_confpass);
        registerbutton = findViewById(R.id.register_btn);

        //write to database
        db = FirebaseDatabase.getInstance();
        ref = db.getReference("user info");
        user = new User();

        //input validation
        txtPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String password = charSequence.toString();
                if (password.length() == 4) {
                    Pattern pattern = Pattern.compile("^[0-9]{4}$");
                    Matcher matcher = pattern.matcher(password);
                    boolean isPwdContainsDigits = matcher.find();
                    if(isPwdContainsDigits){
                        layoutPassword.setHelperText("Valid password");
                        layoutPassword.setError("");
                    } else {
                        layoutPassword.setHelperText("");
                        layoutPassword.setError("invalid password!");
                    }

                } else {
                    layoutPassword.setHelperText("Enter 4 digits");
                    layoutPassword.setError("Enter 4 digits!");
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Uname = uname.getText().toString();
                String Email = email.getText().toString();
                String Phone = phone.getText().toString();
                String Passwd = pass.getText().toString();
                String Conf_passwd = conf_pass.getText().toString();

                if (Uname.isEmpty() || Email.isEmpty() || Phone.isEmpty() || Passwd.isEmpty() ||
                        Conf_passwd.isEmpty()) {
                    Toast.makeText(Register.this, "Please fill all the fields",
                            Toast.LENGTH_SHORT).show();
                } else if (Passwd.equals(Conf_passwd)) {
                    Intent i = new Intent(getApplicationContext(), Login.class);
                    startActivity(i);

                } else {
                    Toast.makeText(Register.this, "Password do not match!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}