package com.bsoft.componentXX;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bsoft.baselib.base.BaseActivity;
import com.bsoft.baselib.widget.IosDialog;
import com.bsoft.baselib.widget.loadingstatusview.Gloading;
import com.bsoft.baselib.widget.loadingstatusview.adapter.SpecialAdapter;
import com.bsoft.testlib.activity.ShapeActivity;

public class MainActivity extends BaseActivity {
    private IosDialog dialog;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());
        Intent intent = new Intent(this, ShapeActivity.class);
        startActivity(intent);
        showDialog();
        showLoading();
        initLoadingStatusViewIfNeed();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //   showLoadFailed();
                // showLoadSuccess();

            }
        }, 3000);    //延时1s执行
        Gloading specialGloading = Gloading.from(new SpecialAdapter());
        mHolder = specialGloading.wrap(this);
        mHolder.showEmpty();
    }


    private void showDialog() {
        IosDialog.DialogBuilder dialogBuilder = new IosDialog.DialogBuilder(this);
        dialogBuilder.setTitle("温馨提示");
        dialogBuilder.setText("嫁给我好吗？");
        dialogBuilder.addListener(new IosDialog.OnButtonClickListener() {
            @Override
            public void onAsureClick() {
                Toast.makeText(MainActivity.this, "太好了", Toast.LENGTH_SHORT).show();
                dialog.dismiss();


            }

            @Override
            public void onCancelClick() {
                Toast.makeText(MainActivity.this, "悲伤的故事", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialogBuilder.create();// .setCancelVisible(true)


    }




    @Override
    protected void onLoadRetry() {
        showLoadSuccess();
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
