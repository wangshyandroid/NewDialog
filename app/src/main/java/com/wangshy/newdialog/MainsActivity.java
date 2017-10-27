package com.wangshy.newdialog;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.DefaultLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author wangshy
 */
public class MainsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 1; i <= 50; i++) {
            list.add("测试案例 ----" + i);
        }
        VirtualLayoutManager manager = new VirtualLayoutManager(this);
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(manager);
        final List<LayoutHelper> helpers = new LinkedList<>();
        final GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
        gridLayoutHelper.setItemCount(40);
        helpers.add(gridLayoutHelper);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.set(10, 5, 10, 5);
            }
        });
        final ScrollFixLayoutHelper scrollFixLayoutHelper = new ScrollFixLayoutHelper(FixLayoutHelper.BOTTOM_LEFT, 150, 100);
        helpers.add(DefaultLayoutHelper.newHelper(9));
        scrollFixLayoutHelper.setItemCount(10);
        helpers.add(scrollFixLayoutHelper);
        manager.setLayoutHelpers(helpers);

        recyclerView.setAdapter(new Adapter(manager));
    }
//
//    private class MainViewHolder extends RecyclerView.ViewHolder {
//        TextView textView;
//
//        public MainViewHolder(View itemView) {
//            super(itemView);
//            textView = itemView.findViewById(R.id.textView);
//        }
//    }

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

    private class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MainViewHolder(View inflate) {
            super(inflate);
            textView = itemView.findViewById(R.id.textView);
        }
    }

    private class Adapter extends VirtualLayoutAdapter<MainViewHolder> {

        public Adapter(@NonNull VirtualLayoutManager layoutManager) {
            super(layoutManager);
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MainViewHolder(LayoutInflater.from(MainsActivity.this).inflate(R.layout.rv_itme, parent, false));
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            holder.textView.setText(list.get(position));
        }

        @Override
        public int getItemCount() {
            List<LayoutHelper> helpers = getLayoutHelpers();
            if (helpers == null) {
                return 0;
            }
            int count = 0;
            for (int i = 0, size = helpers.size(); i < size; i++) {
                count += helpers.get(i).getItemCount();
            }
            return count;
        }
    }
}
