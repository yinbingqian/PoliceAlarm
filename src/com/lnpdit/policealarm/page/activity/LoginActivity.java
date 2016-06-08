package com.lnpdit.policealarm.page.activity;

import com.lnpdit.policealarm.R;
import com.lnpdit.policealarm.base.component.BaseActivity;
import com.lnpdit.policealarm.entity.LoginUser;
import com.lnpdit.policealarm.http.RdaResultPack;
import com.lnpdit.policealarm.md5.MD5Plus;
import com.lnpdit.policealarm.utils.SOAP_UTILS;
import com.lnpdit.policealarm.webservice.SoapRes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
    Context context;
    EditText username_edit;
    EditText password_edit;
    Button login_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        initView();

    }

    private void initView() {
        username_edit = (EditText) findViewById(R.id.username_edit);
        password_edit = (EditText) findViewById(R.id.password_edit);
        
        
        //保存登陆用户名
        SharedPreferences sharedPreferences= getSharedPreferences("userinfo", MODE_PRIVATE); 
                // 使用getString方法获得value，注意第2个参数是value的默认值 
                String Id =sharedPreferences.getString("Id", ""); 
                String serialNum =sharedPreferences.getString("serialNum", ""); 
                String passWd =sharedPreferences.getString("passWd", ""); 
                String sex =sharedPreferences.getString("sex", ""); 
                String zone =sharedPreferences.getString("zone", ""); 
                String crTime =sharedPreferences.getString("crTime", ""); 
                //使用toast信息提示框显示信息 

                username_edit.setText(serialNum);
                password_edit.setText(passWd);
                
        login_bt = (Button) findViewById(R.id.login_bt);
        login_bt.setOnClickListener(this);

        /**
         * 限制只能输入字母和数字，默认弹出英文输入法
         */
        password_edit.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }

            @Override
            protected char[] getAcceptedChars() {
                char[] data = getStringData(R.string.login_only_can_input)
                        .toCharArray();
                return data;
            }
        });
        username_edit.setKeyListener(new DigitsKeyListener() {
            @Override
            public int getInputType() {
                return InputType.TYPE_TEXT_VARIATION_PASSWORD;
            }
            
            @Override
            protected char[] getAcceptedChars() {
                char[] data = getStringData(R.string.login_only_can_input)
                        .toCharArray();
                return data;
            }
        });

    }

    public String getStringData(int id) {
        return getResources().getString(id);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        case R.id.login_bt:
            login_validate(username_edit.getText().toString().trim(), MD5Plus
                    .stringToMD5(password_edit.getText().toString().trim())); // 加密
            break;

        default:
            break;
        }

    }

    /**
     * 用户登录
     * 
     * @param username
     * @param password
     */
    private void login_validate(String username, String password) {
        if (username == null || username.equals("")) {
            Toast.makeText(this, "用户名为空", Toast.LENGTH_SHORT).show();
            return;
        } 
        if (password == null || password.equals("")) {
            Toast.makeText(this, "密码为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (gPSIsOPen(context) !=true) {
            Toast.makeText(this, "请开启GPS才可登录", Toast.LENGTH_SHORT).show();
            return;
        } 
        Object[] property_va = { username_edit.getText().toString(),
                password_edit.getText().toString() };
        soapService.CheckPoUserLogin(property_va);
    }

    /**
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     * @param context
     * @return true 表示开启
     */
    public static final boolean gPSIsOPen(final Context context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }
    
    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onEvent(Object obj) {
        // TODO Auto-generated method stub
        super.onEvent(obj);
        SoapRes res = (SoapRes) obj;
        if (res.getCode().equals(SOAP_UTILS.METHOD.CHECKPOUSERLOGIN)) {
            if (res.getObj() != null) {
                LoginUser loginUser = (LoginUser) res.getObj();
                if (loginUser.getId().equals("0")) {
                    Toast.makeText(this, "您的账号与密码不符", Toast.LENGTH_SHORT).show();
                } else {
                    loginUser.setPassWd(
                            password_edit.getText().toString().trim());
                    SharedPreferences sp = context
                            .getSharedPreferences("userinfo", MODE_PRIVATE);
                    Editor editor = sp.edit();
                    editor.putString("Id", loginUser.getId());
                    editor.putString("serialNum", loginUser.getSerialNum());
                    editor.putString("passWd", loginUser.getPassWd());
                    editor.putString("sex", loginUser.getSex());
                    editor.putString("zone", loginUser.getZone());
                    editor.putString("crTime", loginUser.getCrTime());
                    editor.commit();
                    //
                    
                    SharedPreferences sharedPreferences= getSharedPreferences("userinfo", MODE_PRIVATE); 
                            // 使用getString方法获得value，注意第2个参数是value的默认值 
                            String Id =sharedPreferences.getString("Id", ""); 
                            String serialNum =sharedPreferences.getString("serialNum", ""); 
                            String passWd =sharedPreferences.getString("passWd", ""); 
                            String sex =sharedPreferences.getString("sex", ""); 
                            String zone =sharedPreferences.getString("zone", ""); 
                            String crTime =sharedPreferences.getString("crTime", ""); 
                            //使用toast信息提示框显示信息 

//                            Toast.makeText(this, "读取数据如下："+"\n"+"serialNum：" + serialNum + "\n" + "sex：" + sex, 
//                            Toast.LENGTH_LONG).show(); 
                            username_edit.setText(serialNum);
                            password_edit.setText(passWd);
//
//                    Toast.makeText(context,
//                            username_edit.getText().toString().trim(),
//                            Toast.LENGTH_LONG).show();
                     Intent intent_login = new Intent();
                     intent_login.setClass(context, Smarter_Activity.class);
                     startActivity(intent_login);
                     finish();
                }

            }
        }
    }
}
