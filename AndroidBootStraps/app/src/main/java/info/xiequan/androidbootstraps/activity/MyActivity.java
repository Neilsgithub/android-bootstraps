package info.xiequan.androidbootstraps.activity;

import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import info.xiequan.androidbootstraps.R;
import info.xiequan.androidbootstraps.base.BaseActivity;


public class MyActivity extends BaseActivity {
    private AdView adView;
    private static final String AD_UNIT_ID = "ca-app-pub-4706145830093249/3114135639";

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

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

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

    /** Called before the activity is destroyed. */
    @Override
    public void onDestroy() {
        // Destroy the AdView.
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }


}
