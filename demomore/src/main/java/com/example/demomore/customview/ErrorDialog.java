package com.example.demomore.customview;

import android.app.Dialog;
import android.content.Context;
import android.media.DrmInitData;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.demomore.R;

import butterknife.Bind;


/**
 * Created by lenovo on 2016/9/8.
 */
public class ErrorDialog extends Dialog implements View.OnClickListener {

    private final String mMessage;

    private OnClickListener_RegisterCancel registerCancel_cancel;

//    private static TextView custom_dialog_message;
//
//    public Custom_Dialog(Context context) {
//        this(context,0);
//    }
//
//    public Custom_Dialog(Context context, int themeResId) {
//        super(context, themeResId);
//    }
//
//    public static class Builder {
//        private Context context;
//        private String message;
//
//        public Builder(Context context) {
//            this.context = context;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//            custom_dialog_message.setText(message);
//        }
//
//        public Custom_Dialog create() {
//            LayoutInflater inflater = (LayoutInflater) context
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//            final Custom_Dialog dialog = new Custom_Dialog(context, R.style.Dialog);
////            Custom_Dialog dialog = new Custom_Dialog(context, 0);
//            View layout = inflater.inflate(R.layout.dialog_confirmation, null);
//            dialog.addContentView(layout, new LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT
//                    , ViewGroup.LayoutParams.MATCH_PARENT));
//            dialog.setContentView(layout);
//            TextView confirmation_text = (TextView) layout.findViewById(R.id.custom_confirmation);
//            TextView custom_cancel_text = (TextView) layout.findViewById(R.id.custom_cancel);
//            custom_dialog_message = (TextView) layout.findViewById(R.id.custom_dialog_message);
//            confirmation_text.setText("confirmation_text");
//            custom_cancel_text.setText("custom_cancel_text");
//
//            return dialog;
//        }

    private ListView mListView;
    private final Context mContext;
    private TextView message_text;
    private ImageView ivErro;

    /**
     * 创建的时候直接的将项目的信息传入
     *
     * @param context
     * @param message
     */
    public ErrorDialog(Context context, String message) {
        super(context, R.style.ErroDialog);
        mContext = context;
        mMessage = message;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.erro_dalog);

        ivErro = (ImageView) findViewById(R.id.iv_erro);
        //dialog所在的屏幕
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();//获取屏幕中的属性
        attributes.gravity = Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL;//设置屏幕中的控件的位置
        window.setAttributes(attributes);//设置新的属性，将原有的属性效果覆盖
        //不能通过window设置去除标题栏和边框操作，根据源码提示需要通过styles.xml设置

//        mListView.setAdapter(new Myadapter());
//
//        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//                //隐藏对话框
//                dismiss();
//
//            }
//        });
        initData();
        initListener();

    }

    private void initListener() {
        ivErro.setOnClickListener(this);
    }

    private void initData() {
    }


    public void SetOnRegisterCancelClickListener(OnClickListener_RegisterCancel listener_registerCancel) {
        registerCancel_cancel = listener_registerCancel;
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
                   case  R.id.iv_erro:
                       dismiss();
                    break;
                }
    }

    /**
     * 确定的回调接口
     */
    public interface OnClickListener_RegisterCancel {
        void onRegisterCancelClick(String text);
    }

    /**
     * 让后面的文字显示颜色给定的颜色，默认色是酒红色
     *
     * @param string
     * @param color
     * @return
     */
    private CharSequence showTextWithColor(String string, int color) {
        SpannableString ss = new SpannableString(string);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(0xFFFD5056);   //红色前景色
        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(color);  //默认色
//		BackgroundColorSpan
        int end = string.indexOf(":") + 1;

        ss.setSpan(colorSpan, 0, end, SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        ss.setSpan(colorSpan2, end, string.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);

        return ss;
    }

}





