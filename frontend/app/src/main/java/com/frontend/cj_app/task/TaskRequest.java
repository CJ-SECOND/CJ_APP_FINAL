package com.frontend.cj_app.task;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.frontend.cj_app.R;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.model.AssignmentList;
import com.frontend.cj_app.common.payload.Assignment_Request;
import com.frontend.cj_app.common.payload.Assignment_Response;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TaskRequest extends AppCompatActivity {

    String area;
    String dateTime;
    String timeState;
    final int[] count = {0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.task_requset);

        Intent intent = getIntent();
        area = intent.getStringExtra("area");
        dateTime = intent.getStringExtra("dateTime");
        timeState = intent.getStringExtra("timeState");

        TextView requestTitle = findViewById(R.id.requestTitle);
        requestTitle.setText(dateTime);
        TextView requestArea = findViewById(R.id.requestArea);
        requestArea.setText(area);
        TextView requestDateTime = findViewById(R.id.requestDateTime);
        requestDateTime.setText(dateTime);
        TextView requestWorkTime = findViewById(R.id.requestWorkTime);
        requestWorkTime.setText(timeState);

        Button cntBtn1 = findViewById(R.id.cntBtn1);
        Button cntBtn2 = findViewById(R.id.cntBtn2);
        Button cntBtn3 = findViewById(R.id.cntBtn3);
        Button cntBtn4 = findViewById(R.id.cntBtn4);
        Button btn_next8 = findViewById(R.id.btn_next8);

        cntBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cntBtn1.setBackground(getDrawable(R.drawable.border_button3));
                cntBtn1.setTextColor(Color.parseColor("#029aff"));
                count[0] = 30;
            }
        });
        cntBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cntBtn2.setBackground(getDrawable(R.drawable.border_button3));
                cntBtn2.setTextColor(Color.parseColor("#029aff"));
                count[0] = 50;
            }
        });
        cntBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cntBtn3.setBackground(getDrawable(R.drawable.border_button3));
                cntBtn3.setTextColor(Color.parseColor("#029aff"));
                count[0] = 70;
            }
        });
        cntBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cntBtn4.setBackground(getDrawable(R.drawable.border_button3));
                cntBtn4.setTextColor(Color.parseColor("#029aff"));
                count[0] = 100;
            }
        });

        Button stateBtn1 = findViewById(R.id.stateBtn1);
        Button stateBtn2 = findViewById(R.id.stateBtn2);
        Button stateBtn3 = findViewById(R.id.stateBtn3);

        final String[] priority = {""};
        stateBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateBtn1.setBackground(getDrawable(R.drawable.border_button3));
                stateBtn1.setTextColor(Color.parseColor("#029aff"));
                priority[0] = "WEIGHT";
            }
        });

        stateBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateBtn2.setBackground(getDrawable(R.drawable.border_button3));
                stateBtn2.setTextColor(Color.parseColor("#029aff"));
                priority[0] = "SIZE";
            }
        });
        stateBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stateBtn3.setBackground(getDrawable(R.drawable.border_button3));
                stateBtn3.setTextColor(Color.parseColor("#029aff"));
                priority[0] = "TRAFFIC";
            }
        });
        btn_next8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                autoAssignmentCoury(new Assignment_Request(area, priority[0], count[0], timeState));
            }
        });
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://[IP 주소]:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private void autoAssignmentCoury(Assignment_Request data) {
        RetrofitAPI serviceApi = retrofit.create(RetrofitAPI.class);
        serviceApi.autoAssignmentCoury(data).enqueue(new Callback<Assignment_Response>() {
            @Override
            public void onResponse(Call<Assignment_Response> call, Response<Assignment_Response> response) {
                Assignment_Response result = response.body();

                AssignmentList first = result.getFirst();
                AssignmentList mid = result.getMid();
                AssignmentList end = result.getEnd();

                Intent intent = new Intent(getApplicationContext(), TaskConfirm.class);
                intent.putExtra("first", first);
                intent.putExtra("mid", mid);
                intent.putExtra("end", end);
                intent.putExtra("area", area);
                intent.putExtra("dateTime", dateTime);
                intent.putExtra("timeState", timeState);
                intent.putExtra("count", count[0]);
                createNotification();
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<Assignment_Response> call, Throwable t) {

            }
        });
    }

    private void createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "default");

        builder.setSmallIcon(R.mipmap.cjmate_ic2);
        builder.setContentTitle("업무를 확정해주세요.");
        builder.setContentText("마포 5월 3일 업무배치되었습니다.\n" +
                "앱에서 자세히 보고 확정해주시길 바랍니다.");

        builder.setAutoCancel(true); //탭 클릭하면 자동 제거
        //알림 표시
        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        //각 알림의 고유한 int값
        notificationManager.notify(1, builder.build());
    }
}