package com.frontend.cj_app.delivery;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.frontend.cj_app.R;
import com.frontend.cj_app.barcode.Barcode;
import com.frontend.cj_app.common.adapter.MapListAdapter;
import com.frontend.cj_app.common.api.RetrofitAPI;
import com.frontend.cj_app.common.model.map.ResultPath;
import com.frontend.cj_app.common.payload.CouryToAddress_Request;
import com.frontend.cj_app.common.payload.CouryToAddress_Response;
import com.frontend.cj_app.common.payload.Coury_map;
import com.frontend.cj_app.common.session.Session;
import com.frontend.cj_app.dsla.tracking;
import com.frontend.cj_app.task.TaskRequest;
import com.frontend.cj_app.task.TaskRequestMain;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.OverlayImage;
import com.naver.maps.map.overlay.PathOverlay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class delivery_map extends AppCompatActivity implements OnMapReadyCallback {

    private Session session = new Session(getApplicationContext());

    String APIKEY_ID = "hwdocwymq1";
    String APIKEY = "JtRWnSdOgAMQJKHsPpNsEzGleRQdfuWyNJosdQqa";

    int userSeq = session.getUserSeq();
    String packageName;

    //마포구 cj물류센터 좌표(고정)
    Double start_longitude =126.84496853255905;
    Double start_latitude = 37.568215489066034;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://naveropenapi.apigw.ntruss.com/map-direction/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Retrofit retrofit2 = new Retrofit.Builder()
            .baseUrl("http://[IP 주소]:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delivery_map);

        Intent data = getIntent();
        packageName = data.getStringExtra("packageName");

        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment)fm.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        CouryToAddress_Request request = new CouryToAddress_Request(userSeq, packageName);
        RetrofitAPI AddressApi = retrofit2.create(RetrofitAPI.class);
        AddressApi.getCouryToAddress(request).enqueue(new Callback<CouryToAddress_Response>() {
            @Override
            public void onResponse(Call<CouryToAddress_Response> call, Response<CouryToAddress_Response> response) {
                CouryToAddress_Response resultgeo = response.body();

                // 전체 배송지 목록
                List<Coury_map> recv_to_addr = resultgeo.getRecvAddrList();

                // 업무 목록 리스트뷰 생성
                ListView un_clear_listview = (ListView)findViewById(R.id.listview1);
                List<Coury_map> un_clear_list = new ArrayList<Coury_map>();

                MapListAdapter mapListAdapter1 = new MapListAdapter(getApplicationContext(), (ArrayList<Coury_map>) recv_to_addr);
                un_clear_listview.setAdapter(mapListAdapter1);

                // 전체 배송 목적지중 첫번째 주소 길찾기
                for(Coury_map coury : recv_to_addr) {
                    un_clear_list.add(coury);

                    un_clear_listview.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                        @Override
                        public void onItemClick(AdapterView parent, View v, int position, long id) {
                            String recvAddress = mapListAdapter1.getItem(position).getCouryToAddress();

                            Geocoder geocoder = new Geocoder(getApplicationContext());
                            List<Address> list = new ArrayList<Address>();
                            try {
                                list = geocoder.getFromLocationName(recvAddress, 10);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            //도착지 위도경도 구하기
                            Double goal_latitude = list.get(0).getLatitude();
                            Double goal_longitude = list.get(0).getLongitude();

                            //driving 데이터 받아오기
                            RetrofitAPI api = retrofit.create(RetrofitAPI.class);

                            api.navigation(APIKEY_ID, APIKEY, Double.toString(start_longitude)+","+Double.toString(start_latitude), Double.toString(goal_longitude)+","+Double.toString(goal_latitude))
                                .enqueue(new Callback<ResultPath>() {
                                    @Override
                                    public void onResponse(Call<ResultPath> call, Response<ResultPath> response) {
                                        ResultPath resultpath = response.body();
                                        List<List<Double>> path_list = resultpath.getRoute().getOption().get(0).getPath();

                                        PathOverlay pathOverlay = new PathOverlay();
                                        List<LatLng> cords = new ArrayList<LatLng>();

                                        for(List<Double> path : path_list) {
                                            double longitude = path.get(0);
                                            double latitude = path.get(1);
                                            cords.add(new LatLng(latitude, longitude));
                                        }

                                        pathOverlay.setColor(0xff029aff);
                                        pathOverlay.setCoords(cords);
                                        pathOverlay.setMap(naverMap);

                                        //카메라 시작위치 설정 출발,도착지의 위도경도 %2 가 가운데에 뜨게함
                                        LatLng mlatlng = new LatLng((start_latitude+goal_latitude) / 2, (start_longitude + goal_longitude) / 2);
                                        CameraPosition cameraPosition = new CameraPosition(mlatlng,11);
                                        naverMap.setCameraPosition(cameraPosition);

                                        //출발지 마커
                                        Marker marker_start = new Marker();
                                        marker_start.setPosition(new LatLng(start_latitude,start_longitude));
                                        marker_start.setIcon(OverlayImage.fromResource(R.drawable.smallmap_start_marker));
                                        marker_start.setWidth(100);
                                        marker_start.setHeight(100);
                                        marker_start.setMap(naverMap);

                                        //도착지 마커
                                        Marker marker_goal = new Marker();
                                        marker_goal.setPosition(new LatLng(goal_latitude,goal_longitude));
                                        marker_goal.setIcon(OverlayImage.fromResource(R.drawable.smallmap_end_marker));
                                        marker_goal.setWidth(100);
                                        marker_goal.setHeight(100);
                                        marker_goal.setMap(naverMap);
                                    }
                                    @Override
                                    public void onFailure(Call<ResultPath>call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<CouryToAddress_Response> call, Throwable t) {
            }
        });

        //네비게이션 바
        Button btn_to_map = findViewById(R.id.btn_to_map4);
        btn_to_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), delivery_map_detail.class);
                startActivity(intent);
            }
        });
        Button btn_to_barcode = findViewById(R.id.btn_to_barcode4);
        btn_to_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Barcode.class);
                startActivity(intent);
            }
        });
        Button btn_to_request = findViewById(R.id.btn_to_request4);
        btn_to_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), TaskRequestMain.class);
                startActivity(intent);
            }
        });

        Button btn_to_more = findViewById(R.id.btn_to_more4);
        btn_to_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), tracking.class);
                startActivity(intent);
            }
        });
    }

}
