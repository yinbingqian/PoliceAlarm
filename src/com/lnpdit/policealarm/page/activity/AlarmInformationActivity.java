package com.lnpdit.policealarm.page.activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.lnpdit.policealarm.R;
import com.lnpdit.policealarm.base.component.BaseActivity;
import com.lnpdit.policealarm.entity.DataDetailInfo;
import com.lnpdit.policealarm.http.RdaResultPack;
import com.lnpdit.policealarm.page.adapter.image.ImagePagerActivity;
import com.lnpdit.policealarm.utils.SOAP_UTILS;
import com.lnpdit.policealarm.utils.image.NoScrollGridAdapter;
import com.lnpdit.policealarm.utils.image.NoScrollGridView;
import com.lnpdit.policealarm.webservice.SoapRes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AlarmInformationActivity extends BaseActivity{
    private TextView time_tv;// 时间信息
    private TextView location_edit;// 位置信息
    private TextView text_et;// 文本信息
    private TextView tag_tv;// 标签信息
    private Button back_bt;
    private ImageView video_vv;// 视频
    private View line_voice;
    private View line_text;
    private View line_pic;
    private View bottom_line;
    private NoScrollGridView gridview;// 视频
    //语音
    private RelativeLayout mDisplayVoiceLayout;
    private View mDisplayVoiceLength;
    private RelativeLayout video_layout;
    private RelativeLayout sound_layout;
    private RelativeLayout pic_layout;
    private RelativeLayout text_layout;
    
    private ImageView mDisplayVoicePlay;
//    private ProgressBar mDisplayVoiceProgressBar;
    private TextView mDisplayVoiceTime;
    private boolean mPlayState; // 播放状态
    private MediaPlayer mMediaPlayer;
    private int mPlayCurrentPosition;// 当前播放的时间
    private int mMinItemWidth; //��С��item���音频长度
    private int mMaxItemWidth; //����item���
    
    private GridView noScrollgridview;
    public static List<String> label_id;
    public static List<String> label_name;

    private DataDetailInfo datadetailinfo;
    
    Context context;

    String cTime;
    String Id = "";
    String MobileNumber = "";
    String adress = "";
    String audioName = "";
    String videoName = "";
    String longitude = "";
    String latitude = "";
    String Pic1 = "";
    String Pic2 = "";
    String Pic3 = "";
    String Pic4 = "";
    String Pic5 = "";
    String Pic6 = "";
    String textContents = "";
    String TagName = "";
    
//    String pic_array_str = "";
    ArrayList<String> imageUrls;

    static int IO_BUFFER_SIZE = 2 * 1024;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarminformation);
        context = this;
        initView();

        Intent intent = this.getIntent();
        Id = intent.getStringExtra("Id");
        TagName = intent.getStringExtra("tagName");
        cTime = intent.getStringExtra("CTime");

        String[] property_va = new String[] {Id};
        soapService.GetDateDetail(property_va);
        
        //音频长度
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mMaxItemWidth = 500;
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);
    }

    private void initView() {
        imageUrls = new ArrayList<String>();
        time_tv = (TextView) findViewById(R.id.time_tv);
        tag_tv = (TextView) findViewById(R.id.tag_tv);
        text_et = (TextView) findViewById(R.id.text_et);
        location_edit = (TextView) findViewById(R.id.location_edit);
        video_vv = (ImageView) findViewById(R.id.video_vv);
        line_voice = (View) findViewById(R.id.line_voice);
        line_text = (View) findViewById(R.id.line_text);
        line_pic = (View) findViewById(R.id.line_pic);
        bottom_line = (View) findViewById(R.id.bottom_line);
        gridview = (NoScrollGridView) findViewById(R.id.gridview);
        text_layout = (RelativeLayout) findViewById(R.id.text_layout);
        sound_layout = (RelativeLayout) findViewById(R.id.sound_layout);
        video_layout = (RelativeLayout) findViewById(R.id.video_layout);
        pic_layout = (RelativeLayout) findViewById(R.id.pic_layout);
        mDisplayVoiceLayout = (RelativeLayout) findViewById(R.id.voice_display_voice_layout);
        mDisplayVoicePlay = (ImageView) findViewById(R.id.voice_display_voice_play);
        mDisplayVoiceLength = (View) findViewById(
                R.id.voice_display_voice_length);

      mDisplayVoicePlay.setImageResource(
              R.drawable.voice3);
//        mDisplayVoiceProgressBar = (ProgressBar) findViewById(R.id.voice_display_voice_progressbar);
        mDisplayVoiceTime = (TextView) findViewById(R.id.voice_display_voice_time);
        
        back_bt = (Button) findViewById(R.id.back_bt);
        back_bt.setOnClickListener(this);
        

    }

    
    public void GetVideo() {
        if(videoName.equals("")||videoName==null){
            video_vv.setVisibility(8);
            video_layout.setVisibility(8);
            line_voice.setVisibility(8);
        }else{
            video_vv.setOnClickListener(new OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    
                    Intent it = new Intent(Intent.ACTION_VIEW);
                    Uri uri = Uri.parse(SOAP_UTILS.PIC_JOURNAL +videoName);
                    it.setDataAndType(uri, "video/mp4");
                    startActivity(it);

//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    Uri uri = Uri.parse(video);
//                    intent.setDataAndType(uri, "video/mp4");
//                    mContext.startActivity(intent);

                }
            });
        }
    }
    public void GetVoice(){
        
        //手机报音频网络播放
//        final String audio_path_str = MessengerService.AUDIO_PATH + _audio;
//        mDisplayVoiceLayout.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                // TODO Auto-generated method stub
//                MediaPlayer mediaPlayer = new MediaPlayer();
//                try {
//                    mediaPlayer.setDataSource(audio_path_str);
//                    mediaPlayer.prepare();
//                    mediaPlayer.start();
//                } catch (IllegalArgumentException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IllegalStateException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
////              Thread thread = new Thread(new mPlayAudioFileThread());
////              thread.start();
//            }
//        });

        
        
        mPlayState = false; 
        if(audioName.startsWith("")||audioName.startsWith("anyType")){
//        if(audioName.startsWith("null")){
            mDisplayVoiceLayout.setVisibility(8);
            sound_layout.setVisibility(8);
//            line_voice.setVisibility(8);
//            line_pic.setVisibility(8);
        }else{
            int audiotime = 5;
//            final String audiopath = audioName.split(",")[0];
//            mDisplayVoiceTime.setText((int)audiotime + "″");

            //音频条长度
            ViewGroup.LayoutParams lp = mDisplayVoiceLength.getLayoutParams();
            lp.width = (int) (162 + (500 / 60f)* audiotime);
//            mDisplayVoicePlay.setOnClickListener(new OnClickListener() {
            mDisplayVoiceLayout.setOnClickListener(new OnClickListener() {
                
                public void onClick(View v) {
                    // 播放录音
                    if (!mPlayState) {
                        mMediaPlayer = new MediaPlayer();
                        try {
                            // 添加录音的路径
                            mMediaPlayer.setDataSource(SOAP_UTILS.AUDIO_PATH + audioName);
                            // 准备
                            mMediaPlayer.prepare();
                            // 播放
                            mMediaPlayer.start();
                            // 根据时间修改界面
                            new Thread(new Runnable() {
                                
                                public void run() {
//                                    mDisplayVoiceProgressBar.setMax((int)audiotime);
                                    mPlayCurrentPosition = 0;
                                    while (mMediaPlayer.isPlaying()) {
                                        mPlayCurrentPosition = mMediaPlayer .getCurrentPosition() / 1000;
//                                        mDisplayVoiceProgressBar.setProgress(mPlayCurrentPosition);
                                    }
                                }
                            }).start();
                            // 修改播放状态
                            mPlayState = true;
                            // 修改播放图标
                            mDisplayVoicePlay.setImageResource(
                                    R.anim.sound_anim);
                            AnimationDrawable animationDrawable = (AnimationDrawable) mDisplayVoicePlay.getDrawable();  
                            animationDrawable.start();
                            mMediaPlayer.setOnCompletionListener(
                                    new OnCompletionListener() {
                                        // 播放结束后调用
                                        public void onCompletion(MediaPlayer mp) {
                                            // 停止播放
                                            mMediaPlayer.stop();
                                            // 修改播放状态
                                            mPlayState = false;
                                            // 修改播放图标
                                            mDisplayVoicePlay.setImageResource(
                                                    R.drawable.voice3);
                                            // 初始化播放数据
                                            mPlayCurrentPosition = 0;
//                                            mDisplayVoiceProgressBar.setProgress(mPlayCurrentPosition);
                                        }
                                    });
                            
                        } catch (IllegalArgumentException e) {
                            e.printStackTrace();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (mMediaPlayer != null) {
                            // 根据播放状态修改显示内容
                            if (mMediaPlayer.isPlaying()) {
                                mPlayState = false;
                                mMediaPlayer.stop();
                                mDisplayVoicePlay.setImageResource(
                                        R.anim.sound_anim);
                                AnimationDrawable animationDrawable = (AnimationDrawable) mDisplayVoicePlay.getDrawable();  
                                animationDrawable.start();
                                mPlayCurrentPosition = 0;
//                                mDisplayVoiceProgressBar
//                                .setProgress(mPlayCurrentPosition);
                            } else {
                                mPlayState = false;
                                mDisplayVoicePlay.setImageResource(
                                        R.drawable.voice3);
                                mPlayCurrentPosition = 0;
//                                mDisplayVoiceProgressBar
//                                .setProgress(mPlayCurrentPosition);
                            }
                        }
                    }
                }
            });
        }
    }
   
    public void GetLocalOrNetBitmap() {
        imageUrls = new ArrayList<String>();
        if (!Pic1.startsWith("anyType")) {
            imageUrls.add(SOAP_UTILS.PIC_FILE + Pic1);
        }
        if (!Pic2.startsWith("anyType")) {
            imageUrls.add(SOAP_UTILS.PIC_FILE + Pic2);
        }
        if (!Pic3.startsWith("anyType")) {
            imageUrls.add(SOAP_UTILS.PIC_FILE + Pic3);
        }
        if (!Pic4.startsWith("anyType")) {
            imageUrls.add(SOAP_UTILS.PIC_FILE + Pic4);
        }
        if (!Pic5.startsWith("anyType")) {
            imageUrls.add(SOAP_UTILS.PIC_FILE + Pic5);
        }
        if (!Pic6.startsWith("anyType")) {
            imageUrls.add(SOAP_UTILS.PIC_FILE + Pic6);
        }
        if (imageUrls.size()==0) { // 没有图片资源就隐藏GridView
//            if (imageUrls == null || imageUrls.size() == 0) { // 没有图片资源就隐藏GridView
            gridview.setVisibility(View.GONE);
            pic_layout.setVisibility(View.GONE);
//            line_text.setVisibility(View.GONE);
//            line_pic.setVisibility(View.GONE);
        } else {
            gridview.setAdapter(new NoScrollGridAdapter(context, imageUrls));
        }
        gridview.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // TODO Auto-generated method stub
                imageBrower(position, imageUrls);
            }
        });
        
    }
    
    @Override
    protected void onEventMainThread(RdaResultPack http) {
        // TODO Auto-generated method stub

    }
    
    public void setUI(DataDetailInfo datadetailinfo) {
        datadetailinfo.getId();
        longitude = datadetailinfo.getCoordinateX();
        latitude = datadetailinfo.getCoordinateY();
        cTime = datadetailinfo.getCTime();
        Pic1 = datadetailinfo.getPic1();
        Pic2 = datadetailinfo.getPic2();
        Pic3 = datadetailinfo.getPic3();
        Pic4 = datadetailinfo.getPic4();
        Pic5 = datadetailinfo.getPic5();
        Pic6 = datadetailinfo.getPic6();
        adress = datadetailinfo.getAdress();
        audioName = datadetailinfo.getAudioName();
        videoName = datadetailinfo.getVideoName();
        TagName = datadetailinfo.getTagName();
        textContents = datadetailinfo.getTextContents();
        

        audioName = audioName.startsWith("anyType")?"":audioName;
        videoName = videoName.startsWith("anyType")?"":videoName;
        textContents = textContents.startsWith("anyType")?"":textContents;
        
        time_tv.setText(cTime);
        time_tv.setTextColor(this.getResources().getColor(R.color.setting_text_color));
        tag_tv.setText(TagName);
        tag_tv.setTextColor(this.getResources().getColor(R.color.setting_text_color));
        location_edit.setText(adress);
        location_edit.setTextColor(this.getResources().getColor(R.color.setting_text_color));
        if(textContents.equals("")||textContents==null){
            text_layout.setVisibility(8);
            line_text.setVisibility(8);
        }else{
            if(text_et.equals("")||text_et==null || textContents.startsWith("anyType")){
                text_layout.setVisibility(8);
            }else{
                
                text_et.setText(textContents);
                text_et.setTextColor(this.getResources().getColor(R.color.setting_text_color));
                
            }
            
        }
//        if(textContents.equals("")&&audioName.startsWith("null") && pic_array_str.startsWith("[]")){
//        if(textContents.equals("")&&audioName.startsWith("") && pic_array_str.startsWith("[]")){

            if(textContents.equals("")&&audioName.startsWith("") && imageUrls.size()==0){            
            
            line_text.setVisibility(8);
            line_pic.setVisibility(8);
            line_voice.setVisibility(8);
        }
      

        GetLocalOrNetBitmap();
//   
//   //视频
   GetVideo();
//   
//   //音频
   GetVoice();
      
    }
    
    @Override
    public void onEvent(Object obj) {
        // TODO Auto-generated method stub
        super.onEvent(obj);
        SoapRes res = (SoapRes) obj;
        if (res.getCode().equals(SOAP_UTILS.METHOD.GETDATEDETAIL)) {
            datadetailinfo = (DataDetailInfo) res.getObj();
            if(datadetailinfo != null){
                setUI(datadetailinfo);
            }
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
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

    private static void copy(InputStream in, OutputStream out)
            throws IOException {
        byte[] b = new byte[IO_BUFFER_SIZE];
        int read;
        while ((read = in.read(b)) != -1) {
            out.write(b, 0, read);
        }
    }

    protected void imageBrower(int position, ArrayList<String> urls2) {
        Intent intent = new Intent(context, ImagePagerActivity.class);
        // 图片url,为了演示这里使用常量，一般从数据库中或网络中获取
        intent.putExtra("image_urls", urls2);
        intent.putExtra("image_index", position);
        startActivity(intent);
    }

}
