package com.frontend.cj_app.barcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.frontend.cj_app.R;
import com.frontend.cj_app.delivery.delivery_map;
import com.frontend.cj_app.delivery.delivery_map_detail;
import com.frontend.cj_app.delivery.delivery_status;
import com.frontend.cj_app.dsla.tracking;
import com.frontend.cj_app.task.TaskRequestMain;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Barcode extends AppCompatActivity{

    Button btnScan, btnPrint;
    TextView tvTest;
    ImageView ivTest;
    static String string;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.barcode);
        tvTest = (TextView)findViewById(R.id.textview_test);
        ivTest = (ImageView)findViewById(R.id.imageview_test);
        btnScan = (Button)findViewById(R.id.button_scan);
        btnScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v){
                new IntentIntegrator(Barcode.this).initiateScan();
            }
        });
        btnPrint = (Button)findViewById(R.id.button_print);
        btnPrint.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View  v) {
                tvTest.setText(string);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = null;
                try{
                    bitmap = barcodeEncoder.encodeBitmap(string, BarcodeFormat.UPC_A,200,20);}
                catch (WriterException e) {
                    e.printStackTrace();
                }
                ivTest.setImageBitmap(bitmap);
            }
        });
        //네비게이션 바
        Button btn_to_map = findViewById(R.id.btn_to_map2);
        btn_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_map_detail.class);
                startActivity(intent);
            }
        });
        Button btn_to_barcode = findViewById(R.id.btn_to_barcode2);
        btn_to_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Barcode.class);
                startActivity(intent);
            }
        });
        Button btn_to_request = findViewById(R.id.btn_to_request2);
        btn_to_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskRequestMain.class);
                startActivity(intent);
            }
        });
        Button btn_to_delivery = findViewById(R.id.btn_to_delivery2);
        btn_to_delivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_map.class);
                startActivity(intent);
            }
        });
        Button btn_to_more = findViewById(R.id.btn_to_more2);
        btn_to_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tracking.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode==RESULT_OK){
            if(requestCode==IntentIntegrator.REQUEST_CODE){

                IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data);
                if (result != null) {
                    if (result.getContents() != null){
                        Toast.makeText(this, "스캔이 완료되었습니다.\n"+result.getContents()+"\nFormat:"+result.getFormatName(),Toast.LENGTH_LONG).show();
                        string = result.getContents();
                    }
                }
            }
        }
        else{
            super.onActivityResult(requestCode,resultCode,data);
        }
    }
}