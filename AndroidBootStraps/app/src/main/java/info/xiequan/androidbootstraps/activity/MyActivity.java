package info.xiequan.androidbootstraps.activity;

import android.content.Intent;
import android.location.LocationManager;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.GeofenceClient;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.squareup.picasso.Picasso;

import info.xiequan.androidbootstraps.R;
import info.xiequan.androidbootstraps.base.BaseActivity;
import info.xiequan.androidbootstraps.util.common.AndroidUtils;


public class MyActivity extends BaseActivity implements View.OnClickListener {
    private AdView adView;
    private LocationManager lm;
    private static final String AD_UNIT_ID = "ca-app-pub-4706145830093249/3114135639";
    private static final String TAG = MyActivity.class.getName();
    private Button btn_bdmap, btn_side_chef;
    //百度定位
    private LocationClientOption.LocationMode tempMode = LocationClientOption.LocationMode.Battery_Saving;
    private String tempcoor = "gcj02";
    private BDLocation location;
    private LocationClient mLocationClient = null;
    private GeofenceClient geofenceClient;
    private BDLocationListener myListener = new MyLocationListener();
    private String longtitude, latitude;
//    private SimpleDraweeView draweeView;
    private Picasso picasso;

    @Override
    protected void beforeInitView() {
        setContentView(R.layout.activity_my);
    }

    @Override
    protected void initView() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
        layout.addView(adView);

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        adView.loadAd(adRequest);
        btn_bdmap = (Button) findViewById(R.id.btn_bdmap);
        btn_side_chef = (Button) findViewById(R.id.btn_side_chef);
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(myListener); // 注册监听函数
        geofenceClient = new GeofenceClient(getApplicationContext());
        setLocationOption();
        mLocationClient.start();


    }

    @Override
    protected void initListener() {
        btn_bdmap.setOnClickListener(this);
        btn_side_chef.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * 设置相关参数
     */
    private void setLocationOption() {
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);
        option.setIsNeedAddress(true);// 返回的定位结果包含地址信息
        option.setCoorType("bd09ll");// 返回的定位结果是百度经纬度,默认值gcj02
        option.setScanSpan(5000);// 设置发起定位请求的间隔时间为5000ms
        mLocationClient.setLocOption(option);
    }

    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            /**
             * 获取经纬度
             */
            latitude = String.valueOf(location.getLatitude());
            longtitude = String.valueOf(location.getLongitude());
//            AndroidUtils.toast(context, "纬度" + latitude + "经度" + latitude);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /**
     * Called before the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_bdmap:
                intent.setClass(context, BaiduMapActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_side_chef:
                intent.setClass(context, SideChefActivity.class);
                startActivity(intent);
                break;

        }
    }
}
