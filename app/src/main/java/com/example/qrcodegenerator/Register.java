package com.example.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    Button registerbutton;
    TextInputLayout layoutPassword;
    TextInputEditText txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        layoutPassword = findViewById(R.id.layoutpass);
        txtPassword = findViewById(R.id.txt_pass);

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
        registerbutton = findViewById(R.id.register_btn);
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
            }
        });

    }
}