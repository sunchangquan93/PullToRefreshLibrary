package com.quanquan.pulltorefreshlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import digiwin.pulltorefreshlibrary.loadrefresh.OnLoadMoreListener;
import digiwin.pulltorefreshlibrary.loadrefresh.OnRefreshListener;
import digiwin.pulltorefreshlibrary.loadrefresh.SwipeToLoadLayout;
import digiwin.pulltorefreshlibrary.recyclerview.DividerItemDecoration;
import digiwin.pulltorefreshlibrary.recyclerviewAdapter.BaseRecyclerAdapter;
import digiwin.pulltorefreshlibrary.recyclerviewAdapter.RecyclerViewHolder;

public class MainActivity extends AppCompatActivity implements OnRefreshListener,OnLoadMoreListener {
    RecyclerView recyclerView;

    SwipeToLoadLayout swipeToLoadLayout;

    BaseRecyclerAdapter<String> adapter;

    List<String> datas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.swipe_target);
        swipeToLoadLayout = (SwipeToLoadLayout)findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(this);
        swipeToLoadLayout.setOnLoadMoreListener(this);
        initData();
    }

    private void initData() {
        datas=new ArrayList<>();
        for(int i=0;i<20;i++){
            datas.add(i+"位");
        }
        adapter=new BaseRecyclerAdapter<String>(this,datas) {
            @Override
            protected int getItemLayout(int viewType) {
                return R.layout.recyclerview_item;
            }
            @Override
            protected void bindData(RecyclerViewHolder holder, int position, String item) {
                holder.setText(R.id.textview,item);
            }
        };
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        swipeToLoadLayout.postDelayed(new TimerTask() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "refresh    ok", Toast.LENGTH_SHORT).show();
                swipeToLoadLayout.setRefreshing(false);
            }
        },1000);

    }

    @Override
    public void onLoadMore() {
        for(int i=0;i<1;i++){
            datas.add(i+"位,222");
        }
        swipeToLoadLayout.postDelayed(new TimerTask() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "onLoadMore    ok", Toast.LENGTH_SHORT).show();
                swipeToLoadLayout.setLoadingMore(false);
            }
        },1000);
    }

}
