package com.lnpdit.policealarm.page.adapter;

import java.util.List;

import com.lnpdit.policealarm.R;
import com.lnpdit.policealarm.entity.DataInfo;
import com.lnpdit.policealarm.page.activity.AlarmUnInformationActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AlarmListAdapter extends BaseAdapter {
    private class buttonViewHolder {
        TextView type_tv;
        TextView time_tv;
    }

    private List<DataInfo> mAppList;
    private LayoutInflater mInflater;
    private Context mContext;
    private buttonViewHolder holder;

    public AlarmListAdapter(Context context, List<DataInfo> appList) {
        mAppList = appList;
        mContext = context;
        mInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mAppList.size();
    }

    @Override
    public Object getItem(int position) {
        return mAppList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void removeItem(int positon) {
        mAppList.remove(positon);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView != null) {
            holder = (buttonViewHolder) convertView.getTag();
        } else {
            convertView = mInflater.inflate(R.layout.list_in_alarmlist, null);
            holder = new buttonViewHolder();
            holder.time_tv = (TextView) convertView.findViewById(R.id.time_tv);
            holder.type_tv = (TextView) convertView.findViewById(R.id.type_tv);
            convertView.setTag(holder);
        }
        DataInfo appInfo = mAppList.get(position);
        if (appInfo != null) {
            String type_str = appInfo.getTagName();
            String time = appInfo.getCTime();
            try {
                holder.type_tv.setText("事件类型：" + type_str);
                holder.time_tv.setText("时间：" + time);
                 convertView.setOnClickListener(new AdapterListener(position,
                 appInfo));
            } catch (Exception e) {

            }
        }
        return convertView;
    }

    class AdapterListener implements OnClickListener {
        private int position;
        private DataInfo transfordate;

        public AdapterListener(int pos, DataInfo td) {
            // TODO Auto-generated constructor stub
            position = pos;
            transfordate = td;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String Id = transfordate.getId();
            String TagName = transfordate.getTagName();
            String CTime = transfordate.getCTime();

            Intent intent = new Intent();
            intent.putExtra("Id", Id);
            intent.putExtra("TagName", TagName);
            intent.putExtra("CTime", CTime);
            intent.putExtra("type", "1");

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
            
            intent.setClass(mContext, AlarmUnInformationActivity.class);
            mContext.startActivity(intent);

        }
    }
}