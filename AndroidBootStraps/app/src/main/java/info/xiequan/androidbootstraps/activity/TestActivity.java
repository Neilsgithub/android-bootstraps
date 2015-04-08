package info.xiequan.androidbootstraps.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import info.xiequan.androidbootstraps.R;

public class TestActivity extends ActionBarActivity {

    private TextView hello;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        hello = (TextView) findViewById(R.id.hello);
    }

}
