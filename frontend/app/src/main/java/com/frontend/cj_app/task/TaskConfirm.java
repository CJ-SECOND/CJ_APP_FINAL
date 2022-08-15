package com.frontend.cj_app.task;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.model.AssignmentList;
import com.frontend.cj_app.common.payload.Assignment_Request;
import com.frontend.cj_app.common.payload.Confirm_Request;
import com.frontend.cj_app.common.payload.Confirm_Response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskConfirm extends AppCompatActivity {

    String packageName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_confirm);

        Intent intent = getIntent();

        AssignmentList first = (AssignmentList) intent.getSerializableExtra("first");
        AssignmentList mid = (AssignmentList) intent.getSerializableExtra("mid");
        AssignmentList end = (AssignmentList) intent.getSerializableExtra("end");

        String area = intent.getStringExtra("area");
        String dateTime = intent.getStringExtra("dateTime");
        String timeState = intent.getStringExtra("timeState");
        String count = intent.getStringExtra("count");

        LinearLayout first_layout = (LinearLayout) findViewById(R.id.first);
        LinearLayout mid_layout = (LinearLayout) findViewById(R.id.mid);
        LinearLayout end_layout = (LinearLayout) findViewById(R.id.end);

        first_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                first_layout.setBackground(getDrawable(R.drawable.border_button));
                mid_layout.setBackground(getDrawable(R.drawable.border_linear2));
                end_layout.setBackground(getDrawable(R.drawable.border_linear2));
                return false;
            }
        });

        mid_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                first_layout.setBackground(getDrawable(R.drawable.border_linear2));
                mid_layout.setBackground(getDrawable(R.drawable.border_button));
                end_layout.setBackground(getDrawable(R.drawable.border_linear2));
                return false;
            }
        });

        end_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                first_layout.setBackground(getDrawable(R.drawable.border_linear2));
                mid_layout.setBackground(getDrawable(R.drawable.border_linear2));
                end_layout.setBackground(getDrawable(R.drawable.border_button));
                return false;
            }
        });

        TextView area1 = findViewById(R.id.area1);
        area1.setText(area);
        TextView area2 = findViewById(R.id.area2);
        area2.setText(area);
        TextView area3 = findViewById(R.id.area3);
        area3.setText(area);

        TextView count1 = findViewById(R.id.count1);
        count1.setText(count);
        TextView count2 = findViewById(R.id.count2);
        count2.setText(count);
        TextView count3 = findViewById(R.id.count3);
        count3.setText(count);

        TextView datetime1 = findViewById(R.id.datetime1);
        datetime1.setText(dateTime);
        TextView datetime2 = findViewById(R.id.datetime2);
        datetime2.setText(dateTime);
        TextView datetime3 = findViewById(R.id.datetime3);
        datetime3.setText(dateTime);

        TextView timeState1 = findViewById(R.id.timeState1);
        timeState1.setText(timeState);
        TextView timeState2 = findViewById(R.id.timeState2);
        timeState2.setText(timeState);
        TextView timeState3 = findViewById(R.id.timeState3);
        timeState3.setText(timeState);

        TextView classSize1 = findViewById(R.id.classSize1);
        classSize1.setText(first.getClassSize());
        TextView classSize2 = findViewById(R.id.classSize2);
        classSize2.setText(mid.getClassSize());
        TextView classSize3 = findViewById(R.id.classSize3);
        classSize3.setText(end.getClassSize());

        TextView classWeight1 = findViewById(R.id.classWeight1);
        classWeight1.setText(first.getClassWeight());
        TextView classWeight2 = findViewById(R.id.classWeight2);
        classWeight2.setText(mid.getClassWeight());
        TextView classWeight3 = findViewById(R.id.classWeight3);
        classWeight3.setText(end.getClassWeight());

        TextView classTraffic1 = findViewById(R.id.classTrffic1);
        classTraffic1.setText(first.getClassTraffic());
        TextView classTraffic2 = findViewById(R.id.classTrffic2);
        classTraffic2.setText(mid.getClassTraffic());
        TextView classTraffic3 = findViewById(R.id.classTrffic3);
        classTraffic3.setText(end.getClassTraffic());

        TextView classTotal1 = findViewById(R.id.classTotal1);
        classTotal1.setText(first.getTotalScore());

        TextView classTotal2 = findViewById(R.id.classTotal2);
        classTotal2.setText(mid.getTotalScore());

        TextView classTotal3 = findViewById(R.id.classTotal3);
        classTotal3.setText(end.getTotalScore());

        TextView fee1 = findViewById(R.id.fee1);
        fee1.setText(first.getTotalFee());
        TextView fee2 = findViewById(R.id.fee2);
        fee2.setText(mid.getTotalFee());
        TextView fee3 = findViewById(R.id.fee3);
        fee3.setText(end.getTotalFee());

        LinearLayout firstLaylout = findViewById(R.id.first);
        firstLaylout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageName = first.getPackageName();
            }
        });

        LinearLayout midLaylout = findViewById(R.id.mid);
        midLaylout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageName = mid.getPackageName();
            }
        });

        LinearLayout endLayout = findViewById(R.id.end);
        endLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                packageName = end.getPackageName();
            }
        });

        Button btn_confirm = findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // userSeq 는 임시로 1로 지정
                confirmCouryList(new Confirm_Request(packageName, 1));
                Intent intent = new Intent(getApplicationContext(), TaskList.class);
                intent.putExtra("area", area);
                intent.putExtra("datetime", dateTime);
                intent.putExtra("workTime", timeState);
                intent.putExtra("packageName", packageName);
                intent.putExtra("count", count);
                startActivity(intent);
            }
        });

        Button btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://[IP 주소]:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private void confirmCouryList(Confirm_Request request) {
        RetrofitAPI serviceApi = retrofit.create(RetrofitAPI.class);
        serviceApi.confirmCouryList(request).enqueue(new Callback<Confirm_Response>() {
            @Override
            public void onResponse(Call<Confirm_Response> call, Response<Confirm_Response> response) {
                Confirm_Response result = response.body();
                Log.i("result : ", String.valueOf(result));
            }
            @Override
            public void onFailure(Call<Confirm_Response> call, Throwable t) {
                Log.e("get user up Error", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}