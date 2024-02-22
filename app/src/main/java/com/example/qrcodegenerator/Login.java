package com.example.qrcodegenerator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button login_btn;
    EditText phone,pass;
    FirebaseDatabase db;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_btn = findViewById(R.id.login_btn);
        phone = findViewById(R.id.text_phone);
        pass = findViewById(R.id.txt_pass);

        db = FirebaseDatabase.getInstance();
        ref = db.getReference("user info");

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Phone = phone.getText().toString().trim();
                String Passwd = pass.getText().toString().trim();
                if (Phone.isEmpty() || Passwd.isEmpty()){
                    Toast.makeText(Login.this, "Please fill in all the fields!",
                            Toast.LENGTH_SHORT).show();
                }else {
                    // Read from the database
                    ref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            // This method is called once with the initial value and again
                            // whenever data at this location is updated.
                            if (dataSnapshot.hasChild(Phone)){
                                String pass = dataSnapshot.child(Phone).child("passwd").getValue(String.class);
                                if (Passwd.equals(pass)){
                                    Intent i = new Intent(getApplicationContext(), QrGenerator.class);
                                    startActivity(i);
                                }else {
                                    Toast.makeText(Login.this, "Invalid phone number and password!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }else {
                                Toast.makeText(Login.this, "Invalid input!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                        }
                    });
                }
            }
        });
    }
}