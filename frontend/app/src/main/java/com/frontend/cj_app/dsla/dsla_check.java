package com.frontend.cj_app.dsla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;
import com.frontend.cj_app.task.TaskRequestMain;

public class dsla_check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dsla_check);
        CheckBox dsla_check_before1 = findViewById(R.id.dsla_check_before1);
        TextView dsla_check = findViewById(R.id.dsla_check_1);
        dsla_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), dsla_agreement.class);
                startActivity(intent);
            }
        });

        Button agreebutton3 = findViewById(R.id.agreebutton3);
        agreebutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskRequestMain.class);
                if (!dsla_check_before1.isChecked()) {
                    Toast.makeText(dsla_check.this, "약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                } else
                    startActivity(intent);
            }
        });
    }
}