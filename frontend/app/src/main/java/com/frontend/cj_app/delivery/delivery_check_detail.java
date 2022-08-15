package com.frontend.cj_app.delivery;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;
import com.frontend.cj_app.camera.camera;
import com.frontend.cj_app.sms.sms;

public class delivery_check_detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_check_detail);

        Button send_sms = findViewById(R.id.send_sms);
        send_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sms.class);
                startActivity(intent);
            }
        });


        Button camerabutton = findViewById(R.id.camerabutton);
        camerabutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), camera.class);
                startActivity(intent);
            }
        });
    }
}