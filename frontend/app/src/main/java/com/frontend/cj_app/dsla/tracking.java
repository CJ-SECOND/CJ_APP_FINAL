package com.frontend.cj_app.dsla;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.frontend.cj_app.R;
import com.frontend.cj_app.barcode.Barcode;
import com.frontend.cj_app.delivery.delivery_map;
import com.frontend.cj_app.delivery.delivery_map_detail;
import com.frontend.cj_app.delivery.delivery_status;
import com.frontend.cj_app.task.TaskRequestMain;

public class tracking extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tracking);

        FragmentTransaction fm = getSupportFragmentManager().beginTransaction();
        tracking_date_select frag1 = new tracking_date_select();
        fm.replace(R.id.date_select,frag1);
        fm.commit();

        //네비게이션 바
        Button btn_to_map = findViewById(R.id.btn_to_map5);
        btn_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_map_detail.class);
                startActivity(intent);
            }
        });
        Button btn_to_barcode = findViewById(R.id.btn_to_barcode5);
        btn_to_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Barcode.class);
                startActivity(intent);
            }
        });
        Button btn_to_request = findViewById(R.id.btn_to_request5);
        btn_to_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskRequestMain.class);
                startActivity(intent);
            }
        });
        Button btn_to_delivery = findViewById(R.id.btn_to_delivery5);
        btn_to_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_map.class);
                startActivity(intent);
            }
        });

    }
}