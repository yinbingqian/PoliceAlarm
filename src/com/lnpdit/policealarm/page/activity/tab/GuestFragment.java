package com.lnpdit.policealarm.page.activity.tab;

import java.util.List;

import com.lnpdit.policealarm.R;
import com.lnpdit.policealarm.base.application.DemoApplication;
import com.lnpdit.policealarm.entity.DataInfoUn;
import com.lnpdit.policealarm.page.activity.AlarmUnInformationActivity;
import com.lnpdit.policealarm.page.adapter.AlarmUnListAdapter;
import com.lnpdit.policealarm.pulltorefresh.library.PullToRefreshBase;
import com.lnpdit.policealarm.pulltorefresh.library.PullToRefreshBase.OnLastItemVisibleListener;
import com.lnpdit.policealarm.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.lnpdit.policealarm.pulltorefresh.library.PullToRefreshListView;
import com.lnpdit.policealarm.utils.EventCache;
import com.lnpdit.policealarm.utils.SOAP_UTILS;
import com.lnpdit.policealarm.webservice.ISoapService;
import com.lnpdit.policealarm.webservice.SoapRes;
import com.lnpdit.policealarm.webservice.SoapService;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class GuestFragment extends Fragment {
    Context context;
    View expertView;
    /** soapService **/
    public ISoapService soapService = new SoapService();

    private PullToRefreshListView listView_expertlist;
    private ListView expertListView;
    private int pageIndex = 1;

    private List<DataInfoUn> alarmunList;
    private AlarmUnListAdapter alarmunlistAdapter;
    public DemoApplication myApplication;
    private static final String TAG = "SU-JPush";

    public GuestFragment() {
        super();
    }

    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initData();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        System.out.println("onAttach");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myApplication = DemoApplication.getInstance();
        EventCache.commandActivity.unregister(this);
        EventCache.commandActivity.register(this);
        EventCache.errorHttp.unregister(this);
        EventCache.errorHttp.register(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        System.out.println("onCreateView");

        context = this.getActivity().getApplicationContext();

        expertView = inflater.inflate(R.layout.fragment_expert, container,
                false);
        initView();
        setListeners();
        initData();
        return expertView;
    }

    private void initData() {
        pageIndex = 1;
        String[] property_va = new String[] { "10", pageIndex + "" };
        soapService.GetDateListUn(property_va, false);
    }

    private void initView() {
        listView_expertlist = (PullToRefreshListView) expertView
                .findViewById(R.id.listView_expertlist);
        expertListView = listView_expertlist.getRefreshableView();
    }

    private void setListeners() {
        listView_expertlist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {

                Intent intent = new Intent();
                intent.setClass(context, AlarmUnInformationActivity.class);
                intent.putExtra("Id", alarmunList.get(position - 1).getId());
                intent.putExtra("tagName",alarmunList.get(position - 1).getTagName());
                intent.putExtra("CTime",alarmunList.get(position - 1).getCTime());
                intent.putExtra("type", "1");
                startActivity(intent);
            }
        });
        listView_expertlist
                .setOnRefreshListener(new OnRefreshListener<ListView>() {

                    @Override
                    public void onRefresh(
                            PullToRefreshBase<ListView> refreshView) {
                        pageIndex = 1;
                        String[] property_va = new String[] { "10",
                                pageIndex + "" };
                        soapService.GetDateListUn(property_va, false);

                    }
                });

        // end of list
        listView_expertlist
                .setOnLastItemVisibleListener(new OnLastItemVisibleListener() {

                    @Override
                    public void onLastItemVisible() {
                        String[] property_va = new String[] { "10",
                                ++pageIndex + "" };
                        soapService.GetDateListUn(property_va, true);

                    }
                });
    }

    public void onEvent(SoapRes obj) {
        if (obj.getCode().equals(SOAP_UTILS.METHOD.GETDATELISTUN)) {
            if (obj.isPage()) {
                for (DataInfoUn bean : (List<DataInfoUn>) obj.getObj()) {
                    alarmunList.add(bean);
                }
                alarmunlistAdapter.notifyDataSetChanged();
            } else {
                alarmunList = (List<DataInfoUn>) obj.getObj();
                if (alarmunList != null) {
                    if (alarmunList.size() != 0) {
                        alarmunlistAdapter = new AlarmUnListAdapter(context,alarmunList);
                        expertListView.setAdapter(alarmunlistAdapter);
                    }
                }
            }
            listView_expertlist.onRefreshComplete();
        }
    }
}