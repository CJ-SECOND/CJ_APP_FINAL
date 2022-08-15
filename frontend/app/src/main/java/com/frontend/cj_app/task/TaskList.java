package com.frontend.cj_app.task;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;
import com.frontend.cj_app.barcode.Barcode;
import com.frontend.cj_app.delivery.delivery_map;
import com.frontend.cj_app.delivery.delivery_map_detail;
import com.frontend.cj_app.delivery.delivery_status;
import com.frontend.cj_app.dsla.tracking;

public class TaskList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_list);

        Intent data = getIntent();

        String area = data.getStringExtra("area");
        String worktime = data.getStringExtra("workTime");
        String datetime = data.getStringExtra("datetime");
        String count = data.getStringExtra("count");
        String packageName = data.getStringExtra("packageName");

        TextView confirmArea = findViewById(R.id.confirmArea);
        confirmArea.setText(area);
        TextView confirmWorktime = findViewById(R.id.confirmWorkTime);
        confirmWorktime.setText(worktime);
        TextView confirmDatetime = findViewById(R.id.confirmDatetime);
        confirmDatetime.setText(datetime);
        TextView confirmPackageName = findViewById(R.id.confirmPackageName);
        confirmPackageName.setText(packageName);
        
        // 선택한 확정 업무 내역 보기
        LinearLayout confirm_layout = findViewById(R.id.confirm_layout1);
        confirm_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Intent request = new Intent(getApplicationContext(), delivery_map.class);
                request.putExtra("packageName", packageName);
                startActivity(request);
                return false;
            }
        });

        //네비게이션 바
        Button btn_to_map = findViewById(R.id.button_map1);
        btn_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_map_detail.class);
                startActivity(intent);
            }
        });
        Button btn_to_barcode = findViewById(R.id.button_register);
        btn_to_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Barcode.class);
                startActivity(intent);
            }
        });
        Button btn_to_delivery = findViewById(R.id.button_delivery);
        btn_to_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_status.class);
                startActivity(intent);
            }
        });
        Button btn_to_more = findViewById(R.id.button_more);
        btn_to_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tracking.class);
                startActivity(intent);
            }
        });
    }
}