package com.wangshy.newdialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

/**
 * @author wangshy
 */
public class MainsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void show(View v) {
        new ActionSheetDialog(this)
                .builder()
                .setTitle("头部")
                .setBody("科技化开会就")
                .setOnCheckListener(new OnSheetItemClickListener() {
                    @Override
                    public void onClick(EnumCheck which) {
                        if (which == EnumCheck.Lift) {
                            Log.e("MainsActivity", "---wangshy---->>>> onClick(MainsActivity.java:40)" + which);
                        } else {
                            Log.e("MainsActivity", "---wangshy---->>>> onClick(MainsActivity.java:43)" + which);
                        }
                    }
                }).show();

    }

    public void showDialog(View v) {
        new AlertDialog(this)
                .builder()
                .setMsg("我身体，咿呀咿呀咦~~~")
                .setOnCheckListener(new OnSheetItemClickListener() {
                    @Override
                    public void onClick(EnumCheck which) {
                        if (which == EnumCheck.Lift) {
                            Log.e("MainsActivity", "---wangshy---->>>> onClick(MainsActivity.java:40)" + which);
                        } else {
                            Log.e("MainsActivity", "---wangshy---->>>> onClick(MainsActivity.java:43)" + which);
                        }
                    }
                }).show();
    }
}
