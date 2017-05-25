package bwie.com.recle;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements   SwipeRefreshLayout.OnRefreshListener {

    private LinearLayoutManager linearLayoutManager;
    private MyAdapter adapter;
    private RecyAdapter recyAdapter;
    private RecyclerView rlv;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:

//                    rlv.setRefreshing(false);

                    break;
                case 2:

                    loadMoreFooterView.setStatus(LoadMoreFooterView.Status.GONE);

                    break;
            }
        }
    };
    private LoadMoreFooterView loadMoreFooterView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rlv = (RecyclerView) findViewById(R.id.rlv);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.layout);
        swipeRefreshLayout.setOnRefreshListener(this);
//        loadMoreFooterView = (LoadMoreFooterView)rlv.getLoadMoreFooterView();

        /*ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("" + i);
        }
        adapter = new MyAdapter(getApplicationContext(), list);*/
        request();
//loadAndRefrech();

    }

  /*  private void loadAndRefrech() {
        rlv.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                recyclerView.setRefreshing(true);


                handler.sendEmptyMessageDelayed(1,2000);

            }
        });


        rlv.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                loadMoreFooterView.setStatus(LoadMoreFooterView.Status.LOADING);
                handler.sendEmptyMessageDelayed(2,2000);

            }
        });
    }*/

    private void request() {
        RequestParams parms = new RequestParams("http://qhb.2dyt.com/Bwei/news?postkey=1503d&page=1&type=1");
        x.http().get(parms, new Callback.CommonCallback<String>() {

            private List<Bean.ListBean> list;

            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Bean bean = gson.fromJson(result, Bean.class);
                list = bean.getList();
                recyAdapter = new RecyAdapter(getApplicationContext(), list);

                rlv.setAdapter(recyAdapter);
//        adapter.setOnItemClickListener(this);
                linearLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                rlv.setLayoutManager(linearLayoutManager);

                rlv.addItemDecoration(
                        new HorizontalDividerItemDecoration.Builder(getApplicationContext())
                                .color(Color.BLACK)
//                        .sizeResId(R.dimen.divider)
//                        .marginResId(R.dimen.leftmargin, R.dimen.rightmargin)
                                .build());








                // 为 item add remove 时增加动画
                rlv.setItemAnimator(new DefaultItemAnimator());
                rlv.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int currentPage) {
                    }
                });

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });

    }

    @Override
    public void onRefresh() {

       /* Observable
                .timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .map(new Func1<Long, Object>() {
                    @Override
                    public Object call(Long aLong) {
                        fetchingNewData();
                        swipeRefreshLayout.setRefreshing(false);
                        adapter.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Refresh Finished!", Toast.LENGTH_SHORT).show();
                        return null;
                    }
                }).subscribe();*/
    }
/*
    @Override
    public void onItemClickListener(int position, View view) {
        adapter.add(position);
    }

    @Override
    public void onItemLongClickListener(int position, View view) {
        adapter.remove(position);
    }*/



}
