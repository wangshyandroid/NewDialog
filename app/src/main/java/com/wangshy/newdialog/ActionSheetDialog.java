package com.wangshy.newdialog;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 作者：wangshy on 17/10/21 17:42
 */
@SuppressWarnings(value = {"all"})
public class ActionSheetDialog {
    private Context context;
    private Dialog dialog;
    private TextView txt_title;
    private TextView boby_view;
    private TextView txt_cancel;
    private TextView agree_view;
    private OnSheetItemClickListener itemClickListener;
    private Display display;

    private LayoutInflater mInflater;
    private WindowManager wm;
    private DisplayMetrics outMetrics;

    public ActionSheetDialog(Context context) {
        wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        this.context = context;
        mInflater = LayoutInflater.from(context);

//        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        display = windowManager.getDefaultDisplay();
    }

    public ActionSheetDialog builder() {
        View view = mInflater.inflate(R.layout.view_actionsheet, null);
        view.setMinimumWidth(outMetrics.widthPixels);
        txt_title = (TextView) view.findViewById(R.id.txt_title);
        txt_cancel = (TextView) view.findViewById(R.id.drop_out_view);
        agree_view = (TextView) view.findViewById(R.id.agree_view);
        boby_view = (TextView) view.findViewById(R.id.boby_view);
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(SheetItemColor.Lift);
                    dialog.dismiss();
                }
            }
        });
        agree_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null) {
                    itemClickListener.onClick(SheetItemColor.Right);
                    dialog.dismiss();
                }
            }
        });
        NewDialog(view);
        // 定义Dialog布局和参数
        return this;
    }

    private Dialog getDialog() {
        return dialog;
    }

    private void NewDialog(View view) {
        dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.x = 0;
        lp.y = 0;
        dialogWindow.setAttributes(lp);
    }

    public ActionSheetDialog setTitle(String title) {
        txt_title.setText(title);
        return this;
    }

    public ActionSheetDialog setCancelable(boolean cancel) {
        dialog.setCancelable(cancel);
        return this;
    }

    public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
        dialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    public ActionSheetDialog setBody(String body) {
        boby_view.setText(body);
        return this;
    }

    public ActionSheetDialog setOnCheckListener(OnSheetItemClickListener listener) {
        this.itemClickListener = listener;
        return this;
    }

    public void show() {
        dialog.show();
    }

    public void dismiss() {
        dialog.dismiss();
    }

    public interface OnSheetItemClickListener {
        void onClick(SheetItemColor which);
    }

    public enum SheetItemColor {
        Lift, Right;
    }
}
