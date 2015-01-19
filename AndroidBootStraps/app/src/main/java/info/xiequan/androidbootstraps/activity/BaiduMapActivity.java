package info.xiequan.androidbootstraps.activity;

import info.xiequan.androidbootstraps.R;
import info.xiequan.androidbootstraps.base.BaseActivity;

public class BaiduMapActivity extends BaseActivity {
    // 定位相关
//    LocationClient mLocClient;
//    public MyLocationListenner myListener = new MyLocationListenner();
//    private MyLocationConfiguration.LocationMode mCurrentMode;
//    BitmapDescriptor mCurrentMarker;
//
//    MapView mMapView;
//    BaiduMap mBaiduMap;

    // UI相关
//    OnCheckedChangeListener radioButtonListener;
//    Button requestLocButton;
//    boolean isFirstLoc = true;
    // 是否首次定位


    @Override
    protected void beforeInitView() {
        setContentView(R.layout.activity_baidu_map);
    }

    @Override
    protected void initView() {
//        requestLocButton = (Button) findViewById(R.id.button1);
//        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//        requestLocButton.setText("普通");
//        OnClickListener btnClickListener = new OnClickListener() {
//            public void onClick(View v) {
//                switch (mCurrentMode) {
//                    case NORMAL:
//                        requestLocButton.setText("跟随");
//                        mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
//                        mBaiduMap
//                                .setMyLocationConfigeration(new MyLocationConfiguration(
//                                        mCurrentMode, true, mCurrentMarker));
//                        break;
//                    case COMPASS:
//                        requestLocButton.setText("普通");
//                        mCurrentMode = MyLocationConfiguration.LocationMode.NORMAL;
//                        mBaiduMap
//                                .setMyLocationConfigeration(new MyLocationConfiguration(
//                                        mCurrentMode, true, mCurrentMarker));
//                        break;
//                    case FOLLOWING:
//                        requestLocButton.setText("罗盘");
//                        mCurrentMode = MyLocationConfiguration.LocationMode.COMPASS;
//                        mBaiduMap
//                                .setMyLocationConfigeration(new MyLocationConfiguration(
//                                        mCurrentMode, true, mCurrentMarker));
//                        break;
//                }
//            }
//        };
//        requestLocButton.setOnClickListener(btnClickListener);
//
//        RadioGroup group = (RadioGroup) this.findViewById(R.id.radioGroup);
//        radioButtonListener = new OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                if (checkedId == R.id.defaulticon) {
//                    // 传入null则，恢复默认图标
//                    mCurrentMarker = null;
//                    mBaiduMap
//                            .setMyLocationConfigeration(new MyLocationConfiguration(
//                                    mCurrentMode, true, null));
//                }
//                if (checkedId == R.id.customicon) {
//                    // 修改为自定义marker
//                    mCurrentMarker = BitmapDescriptorFactory
//                            .fromResource(R.drawable.icon_geo);
//                    mBaiduMap
//                            .setMyLocationConfigeration(new MyLocationConfiguration(
//                                    mCurrentMode, true, mCurrentMarker));
//                }
//            }
//        };
//        group.setOnCheckedChangeListener(radioButtonListener);
//
//        // 地图初始化
//        mMapView = (MapView) findViewById(R.id.bmapView);
//        mBaiduMap = mMapView.getMap();
//        // 开启定位图层
//        mBaiduMap.setMyLocationEnabled(true);
//        // 定位初始化
//        mLocClient = new LocationClient(this);
//        mLocClient.registerLocationListener(myListener);
//        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);// 打开gps
//        option.setCoorType("bd09ll"); // 设置坐标类型
//        option.setScanSpan(1000);
//        mLocClient.setLocOption(option);
//        mLocClient.start();

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }
//    /**
//     * 定位SDK监听函数
//     */
//    public class MyLocationListenner implements BDLocationListener {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            // map view 销毁后不在处理新接收的位置
//            if (location == null || mMapView == null)
//                return;
//            MyLocationData locData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                            // 此处设置开发者获取到的方向信息，顺时针0-360
//                    .direction(100).latitude(location.getLatitude())
//                    .longitude(location.getLongitude()).build();
//            mBaiduMap.setMyLocationData(locData);
//            if (isFirstLoc) {
//                isFirstLoc = false;
//                LatLng ll = new LatLng(location.getLatitude(),
//                        location.getLongitude());
//                MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
//                mBaiduMap.animateMapStatus(u);
//            }
//        }
//
//        public void onReceivePoi(BDLocation poiLocation) {
//        }
//    }
//
//    @Override
//    protected void onPause() {
//        mMapView.onPause();
//        super.onPause();
//    }
//
//    @Override
//    protected void onResume() {
//        mMapView.onResume();
//        super.onResume();
//    }
//
//    @Override
//    protected void onDestroy() {
//        // 退出时销毁定位
//        mLocClient.stop();
//        // 关闭定位图层
//        mBaiduMap.setMyLocationEnabled(false);
//        mMapView.onDestroy();
//        mMapView = null;
//        super.onDestroy();
//    }


}
