package com.frontend.cj_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.task.TaskRequestMain;
import com.frontend.cj_app.user.join.Join_main;
import com.frontend.cj_app.delivery.delivery_map;
import com.frontend.cj_app.delivery.delivery_map_detail;
import com.frontend.cj_app.dsla.tracking;
import com.frontend.cj_app.user.login.Login_main;

public class AppStart extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_start);

        Button btn_ToJoin_1 = findViewById(R.id.btn_ToJoin_1);
        btn_ToJoin_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Join_main.class);
                startActivity(intent);
            }
        });

        Button btn_ToLogin_1 = findViewById(R.id.btn_ToLogin_1);
        btn_ToLogin_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Login_main.class);
                startActivity(intent);
            }
        });
    }
}