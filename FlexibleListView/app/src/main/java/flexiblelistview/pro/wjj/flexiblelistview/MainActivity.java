package flexiblelistview.pro.wjj.flexiblelistview;

import android.app.Activity;
import android.os.Bundle;
import com.apkfuns.logutils.LogUtils;

import flexiblelistview.pro.wjj.flexiblelistview.Anim.YoYo;

public class MainActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtils.d("--->MainActivity onCreate");
        init();
        click();
        logic();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("--->MainActivity onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("--->MainActivity onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("--->MainActivity onDestroy");
    }

    private void init() {

    }

    private void click() {

    }

    private void logic() {

    }


}
