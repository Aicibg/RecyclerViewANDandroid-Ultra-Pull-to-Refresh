package com.example.recyclerviewtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;
import in.srain.cube.views.ptr.util.PtrLocalDisplay;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,Toolbar.OnMenuItemClickListener{
   private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private List<String> strs;
    private Toolbar mtoolbar;
    private PtrFrameLayout mPtrframe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview);
        mPtrframe= (PtrFrameLayout) findViewById(R.id.ptrframelayout);
        mtoolbar= (Toolbar) findViewById(R.id.tlbar);
        initToolbar();
        initPtf();
        strs=new ArrayList<String>();
        strs.add("Android");
        strs.add("JAVA");
        strs.add("PHP");
//        adapter=new RecyclerAdapter(strs);
//        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    //初始化android-Ultra-Pull-To-Refresh刷新控件
    private void initPtf() {
        //下拉刷新头部显示
       final StoreHouseHeader header=new StoreHouseHeader(this);
        //15dp转换为px
        header.setPadding(0, PtrLocalDisplay.designedDP2px(15),0,PtrLocalDisplay.designedDP2px(15));
       // header.initWithStringArray(R.array.storehouse);
        header.initWithString("LOADING");
        header.setTextColor(Color.BLACK);

        mPtrframe.setLoadingMinTime(1000);
        mPtrframe.setDurationToCloseHeader(1500);
        mPtrframe.setHeaderView(header);
        mPtrframe.addPtrUIHandler(header);
        mPtrframe.postDelayed(new Runnable() {
            @Override
            public void run() {
               mPtrframe.autoRefresh(false);
            }
        },100);
        mPtrframe.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                //是否可以刷新
                return PtrDefaultHandler.checkContentCanBePulledDown(frame,content,header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                frame.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new RecyclerAdapter(strs);
                        recyclerView.setAdapter(adapter);
                        mPtrframe.refreshComplete();
                    }
                },100);
            }
        });
    }

    private void initToolbar() {
        setSupportActionBar(mtoolbar);
        mtoolbar.setTitle("");
        mtoolbar.setNavigationIcon(R.mipmap.ic_launcher);

        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击返回",Toast.LENGTH_SHORT).show();
            }
        });
        mtoolbar.setOnMenuItemClickListener(this);
    }

    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.mipmap.ic_launcher:

               break;

       }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
