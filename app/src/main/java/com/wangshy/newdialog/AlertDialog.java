package com.wangshy.newdialog;


import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


@SuppressWarnings(value = {"all"})
public class AlertDialog {
    private Context context;
    private Dialog dialog;

    private TextView txt_cancel;
    private TextView agree_view;
    private TextView boby_view;
    private LayoutInflater mInflater;
    private WindowManager wm;
    private DisplayMetrics outMetrics;
    private OnSheetItemClickListener itemClickListener;

    public AlertDialog(Context context) {
        this.context = context;

        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
    }


    public AlertDialog builder() {
        // 获取Dialog布局
        View view = LayoutInflater.from(context).inflate(R.layout.view_actionsheet1, null);
        view.setMinimumWidth(outMetrics.widthPixels);
        txt_cancel = (TextView) view.findViewById(R.id.drop_out_view);
        agree_view = (TextView) view.findViewById(R.id.agree_view);
        boby_view = (TextView) view.findViewById(R.id.boby_view);
        txt_cancel.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (itemClickListener != null) {
                            itemClickListener.onClick(EnumCheck.Lift);
                        }
                    }
                });
        agree_view.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        if (itemClickListener != null) {
                            itemClickListener.onClick(EnumCheck.Right);
                        }
                    }
                });
        NewDialog(view);
        return this;
    }

    private void NewDialog(View view) {
        dialog = new Dialog(context, R.style.AlertDialogStyle);
        dialog.setContentView(view);
        dialog.setCancelable(false);
    }

    public AlertDialog setMsg(String msg) {
        if (null != boby_view && !TextUtils.isEmpty(msg)) {
            boby_view.setText(msg);
        }
        return this;
    }

    public AlertDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public AlertDialog setOnCheckListener(OnSheetItemClickListener listener) {
        this.itemClickListener = listener;
        return this;
    }

    public void show() {
        dialog.show();
    }
}
