package info.xiequan.androidbootstraps.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by spark on 22/8/14.
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseFragment extends Fragment {

    protected Activity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = getActivity();
        initView();
        initListener();
        initData();
    }

    /**
     * 加载layout xml
     */
    protected abstract int getLayoutResourceId();

    /**
     * 加载UI
     */
    protected abstract void initView();

    /**
     * 监听控件
     */
    protected abstract void initListener();

    /**
     * 加载网络数据
     */
    protected abstract void initData();

}
