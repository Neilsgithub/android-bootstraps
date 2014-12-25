package info.xiequan.androidbootstraps.activity;

import android.content.Intent;

import info.xiequan.androidbootstraps.R;
import info.xiequan.androidbootstraps.base.BaseActivity;

/**
 * Created by spark on 25/12/14.
 * www.blueowls.net
 * i@xiequan.info
 */
public class WelcomeActivity extends BaseActivity {
    @Override
    protected void beforeInitView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                startActivity(intent.setClass(context, MyActivity.class));
            }
        }).start();
    }
}
