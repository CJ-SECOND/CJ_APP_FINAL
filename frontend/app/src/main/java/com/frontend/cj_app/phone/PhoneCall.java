package com.frontend.cj_app.phone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.frontend.cj_app.R;

public class PhoneCall extends AppCompatActivity {

    EditText edit_tel;
    Button btn_tel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonecall);

        btn_tel = findViewById(R.id.btn_tel);
        edit_tel = findViewById(R.id.edit_tel);

        edit_tel.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

        btn_tel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel_number = "tel:"+edit_tel.getText().toString();

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(tel_number));
                startActivity(intent);
            }
        });
    }
}