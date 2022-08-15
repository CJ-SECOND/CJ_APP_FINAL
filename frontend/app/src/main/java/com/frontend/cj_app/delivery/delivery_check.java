package com.frontend.cj_app.delivery;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;
import com.frontend.cj_app.barcode.Barcode;
import com.frontend.cj_app.sms.sms;

public class delivery_check extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_check);

        Intent intent = getIntent();
        String recvName = intent.getStringExtra("recvName");
        String recvAddr = intent.getStringExtra("recvAddr");
        String condition = intent.getStringExtra("condition");

        TextView delivery_check_address = findViewById(R.id.delivery_check_address);
        delivery_check_address.setText(recvAddr);


        final Button btn_lock = (Button) findViewById(R.id.btn_lock);
        btn_lock.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(delivery_check.this);
                dlg.setTitle("비밀번호"); //제목
                dlg.setMessage("#1219 7676#"); //내용
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg.show();
            }
        });

            final Button btn_lost = (Button) findViewById(R.id.btn_lost);
            btn_lost.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(delivery_check.this);
            dlg.setTitle("분실/파손"); //제목
            dlg.setMessage("지원팀에 접수해주세요.(1588-1255)"); //내용
            dlg.setPositiveButton("연결", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dlg.show();
        }

        });
        final Button btn_wrong = (Button) findViewById(R.id.btn_wrong);
        btn_wrong.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(delivery_check.this);
                dlg.setTitle("오배송"); //제목
                dlg.setMessage("지원팀에 접수해주세요.(1588-1255)"); //내용
                dlg.setPositiveButton("연결", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dlg.show();
            }
        });

        Button btn_completion = findViewById(R.id.btn_completion);
        btn_completion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_check_detail.class);
                startActivity(intent);
            }
        });

        Button btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:01088156785"));
                startActivity(intent);
            }
        });

        Button ic_sms = findViewById(R.id.ic_sms);
        ic_sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), sms.class);
                startActivity(intent);
            }
        });

        Button scannerbutton = findViewById(R.id.scannerbutton);
        scannerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Barcode.class);
                startActivity(intent);
            }
        });
    }
}