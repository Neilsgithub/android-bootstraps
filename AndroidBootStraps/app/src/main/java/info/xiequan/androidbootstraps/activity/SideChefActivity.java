package info.xiequan.androidbootstraps.activity;


import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import info.xiequan.androidbootstraps.Object.RawData;
import info.xiequan.androidbootstraps.R;
import info.xiequan.androidbootstraps.adpater.SideChefAdapter;
import info.xiequan.androidbootstraps.base.BaseActivity;
import info.xiequan.androidbootstraps.entity.Data;
import info.xiequan.androidbootstraps.module.SideChefModule;
import info.xiequan.androidbootstraps.util.common.GsonUtil;
import info.xiequan.androidbootstraps.util.network.StringHttpListener;

public class SideChefActivity extends BaseActivity {
    public static final String TAG = SideChefActivity.class.getName();
    private PullToRefreshListView pullToRefreshListView;
    private List<Data> dataList = new ArrayList<Data>();
    private SideChefAdapter adapter;
    private ListView listView;
    private int page, size;

    @Override
    protected void beforeInitView() {
        setContentView(R.layout.activity_side_chef);
    }

    @Override
    protected void initView() {
        size = 20;
        page = 1;
        pullToRefreshListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_view);
        listView = pullToRefreshListView.getRefreshableView();
    }

    @Override
    protected void initListener() {
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 下拉
                page = 1;
                getDataFromService(page, size);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                // 上拉
                page++;
                getDataFromService(page, size);


            }
        });

    }

    @Override
    protected void initData() {

        getDataFromService(page, size);
        adapter = new SideChefAdapter(context, dataList);
        listView.setAdapter(adapter);
    }

    private void getDataFromService(int page, int size) {
        SideChefModule.getSideChefList("http://54.65.55.75:8080/InterfaceIPhone/RecipeList", page, size, new StringHttpListener() {
            @Override
            public void onSuccess(String response) {
                handleDataFromService(response);
                onRefreshComplete();
            }

            @Override
            public void onFailure(String error, Throwable e) {
                super.onFailure(error, e);
            }
        });


    }

    private void handleDataFromService(String json) {
        RawData rawData = GsonUtil.fromJson(json, RawData.class);

        if (page <= 1) {
            dataList.clear();
        }
        dataList.addAll(rawData.getData());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onResume() {
        super.onResume();


    }

    private void onRefreshComplete() {
        pullToRefreshListView.onRefreshComplete();
    }

}
