package com.example.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QrGenerator extends AppCompatActivity {
    EditText edit_input;
    Button btn_generate;
    ImageView img_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_generator);
        edit_input = findViewById(R.id.edit_input);
        btn_generate = findViewById(R.id.btn_generate);
        img_qr = findViewById(R.id.img_qr);

        btn_generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateQR();
            }

            private void generateQR() {
                String text = edit_input.getText().toString().trim();
                if (text.isEmpty()) {
                    Toast.makeText(QrGenerator.this, "Please enter amount", Toast.LENGTH_SHORT).show();
                }else {
                MultiFormatWriter writer = new MultiFormatWriter();
                try {
                    BitMatrix matrix = writer.encode(text, BarcodeFormat.QR_CODE, 800,800);
                    BarcodeEncoder encoder = new BarcodeEncoder();
                    Bitmap bitmap = encoder.createBitmap(matrix);
                    img_qr.setImageBitmap(bitmap);

                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
            }
        });
    }
}