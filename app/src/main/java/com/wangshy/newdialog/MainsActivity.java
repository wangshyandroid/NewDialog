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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

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
                .setOnCheckListener(new ActionSheetDialog.OnSheetItemClickListener() {
                    @Override
                    public void onClick(ActionSheetDialog.SheetItemColor which) {
                        if (which == ActionSheetDialog.SheetItemColor.Lift) {
                            Log.e("MainsActivity", "---wangshy---->>>> onClick(MainsActivity.java:25)" + which);
                        } else if (which == ActionSheetDialog.SheetItemColor.Right) {
                            Log.e("MainsActivity", "---wangshy---->>>> onClick(MainsActivity.java:27)" + which);
                        }
                    }
                }).show();
    }
}
