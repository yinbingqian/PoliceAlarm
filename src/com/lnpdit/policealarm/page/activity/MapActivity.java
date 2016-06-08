package com.lnpdit.policealarm.page.activity;

import java.util.ArrayList;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationClientOption.AMapLocationMode;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.LocationSource.OnLocationChangedListener;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.lnpdit.policealarm.R;
import com.lnpdit.policealarm.base.component.BaseActivity;
import com.lnpdit.policealarm.entity.image.Bimp;
import com.lnpdit.policealarm.http.RdaResultPack;
import com.lnpdit.policealarm.utils.SOAP_UTILS;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MapActivity extends BaseActivity implements LocationSource,AMapLocationListener{


    Context context;

    private Button back_bt;
    
    String Id = "";
    String longitude = "";
    String latitude = "";
    
  //地图
    private OnLocationChangedListener mListener;
    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;
    private AMap aMap;
    private MapView mapView;
//    private PublicData pd;
//    private LocationManagerProxy mAMapLocationManager;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        
        context = this;
        initView();

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");
        longitude = intent.getStringExtra("coordinateX");
        latitude = intent.getStringExtra("coordinateY");
//        pd = PublicData.getInstance();
        mapInit();

        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
        mHandler.sendEmptyMessage(SOAP_UTILS.LOCATION.MSG_LOCATION_START);
        
       
    }
    
    private void initView() {
        locationClient = new AMapLocationClient(this.getApplicationContext());
        locationOption = new AMapLocationClientOption();
        // 设置定位模式为高精度模式
        locationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
        // 设置定位监听
        locationClient.setLocationListener(this);
        
//        location_marker
        initOption();
//        location_text.setText("开始定位");
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
        mHandler.sendEmptyMessage(SOAP_UTILS.LOCATION.MSG_LOCATION_START);
        

        back_bt = (Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(this);
    }

    /**
     * 初始化AMap对象
     */
    private void mapInit() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    Marker MEIYIDUO;
    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.drawable.location_marker));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLACK);// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        // myLocationStyle.anchor(int,int)//设置小蓝点的锚点
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位监听
        aMap.getUiSettings().setMyLocationButtonEnabled(false);// 设置默认定位按钮是否显示
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // aMap.setMyLocationType()
        
//        LatLng ll_meiyijia = new LatLng(41.7320190000,123.4744300000);
        LatLng ll_meiyijia = new LatLng(Double.valueOf(latitude),Double.valueOf(longitude));
        MEIYIDUO = aMap.addMarker(new MarkerOptions()
                .position(ll_meiyijia)
                .title("报案地点")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.cart_alt))
//                .perspective(true)
                .draggable(true));// 设置远小近大效果,2.1.0版本新增
        MEIYIDUO.showInfoWindow();
    }

 // 根据控件的选择，重新设置定位参数
    private void initOption() {
        // 设置是否需要显示地址信息
        locationOption.setNeedAddress(true);
        /**
         * 设置是否优先返回GPS定位结果，如果30秒内GPS没有返回定位结果则进行网络定位 注意：只有在高精度模式下的单次定位有效，其他方式无效
         */
        locationOption.setGpsFirst(false);
        locationOption.setOnceLocation(true);
    }
    
    Handler mHandler = new Handler() {
        public void dispatchMessage(android.os.Message msg) {
            switch (msg.what) {
            // 开始定位
            case SOAP_UTILS.LOCATION.MSG_LOCATION_START:
//                location_text.setText("正在定位...");
                break;
            // 定位完成
            case SOAP_UTILS.LOCATION.MSG_LOCATION_FINISH:
                AMapLocation loc = (AMapLocation) msg.obj;
                String result = SOAP_UTILS.getLocationStr(loc);
                // location_edit.setText(result);
//                location_edit.setText(loc.getAddress());
                mapView.setVisibility(1);
//                location_text.setText("点击定位当前位置");
                longitude = String.valueOf(loc.getLongitude());
                latitude = String.valueOf(loc.getLatitude());
                break;
            // 停止定位
            case SOAP_UTILS.LOCATION.MSG_LOCATION_STOP:
//                location_text.setText("点击定位当前位置");
                break;
            default:
                break;
            }
        };
    };
    
    /**
     * 定位成功后回调函数
     */
    @Override
    public void onLocationChanged(AMapLocation loc) {
        // TODO Auto-generated method stub
        if (mListener != null && loc != null) {
            if (loc != null && loc.getErrorCode() == 0) {
                mListener.onLocationChanged(loc);// 显示系统小蓝点

                Message msg = mHandler.obtainMessage();
                msg.obj = loc;
                msg.what = SOAP_UTILS.LOCATION.MSG_LOCATION_FINISH;
                mHandler.sendMessage(msg);
            } else {
                String errText = "定位失败," + loc.getErrorCode() + ": "
                        + loc.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }
  
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
        mapView.onDestroy();

      Bimp.max = 0;
      Bimp.act_bool = true;
      Bimp.bmp = new ArrayList<Bitmap>(); 
      Bimp.drr = new ArrayList<String>();
        
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

//    @Override
//    protected void onStop() {
//        // TODO Auto-generated method stub
//        super.onStop();
//
//        Bimp.max = 0;
//        Bimp.act_bool = true;
//        Bimp.bmp = new ArrayList<Bitmap>(); 
//        Bimp.drr = new ArrayList<String>();
//    }

    @Override
    public void activate(OnLocationChangedListener listener) {
        // TODO Auto-generated method stub
        mListener = listener;
        if (locationClient == null) {
            locationClient = new AMapLocationClient(this);
            locationOption = new AMapLocationClientOption();
            // 设置定位监听
            locationClient.setLocationListener(this);
            // 设置为高精度定位模式
            locationOption.setLocationMode(AMapLocationMode.Hight_Accuracy);
            // 设置定位参数
            locationClient.setLocationOption(locationOption);
            // 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
            // 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
            // 在定位结束后，在合适的生命周期调用onDestroy()方法
            // 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
            locationClient.startLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        // TODO Auto-generated method stub
        mListener = null;
        if (locationClient != null) {
            locationClient.stopLocation();
            locationClient.onDestroy();
        }
        locationClient = null;
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
        case R.id.back_bt:
            finish();
            break;
        default:
            break;
        }
    }

    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub
        
    }

    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    public View getInfoContents(Marker marker) {
        return null;
    }

    public boolean onMarkerClick(Marker arg0) {
        // TODO Auto-generated method stub
        return false;
    }

}
