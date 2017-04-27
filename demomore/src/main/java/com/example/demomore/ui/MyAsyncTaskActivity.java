package com.example.demomore.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.demomore.BaseActivity;
import com.example.demomore.R;
import com.example.demomore.utils.ToastUtils;

import butterknife.Bind;

public class MyAsyncTaskActivity extends BaseActivity {

    @Bind(R.id.btn_start)
    Button btnStart;
    @Bind(R.id.btn_add)
    Button btnAdd;
    @Bind(R.id.tv_message1)
    TextView tvMessage1;
    @Bind(R.id.tv_message)
    TextView tvMessage;
    private String num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initListener() {
        btnStart.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getContentView() {
        return R.layout.activity_my_async_task;
    }

    @Override
    protected int getFragmentContentId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                UpdateTextTask updateTextTask = new UpdateTextTask();
                updateTextTask.execute();
                break;
            case R.id.btn_add:

                tvMessage1.setText("当前数字是" + num);
                num += 1;
                break;
        }
    }

    /**
     * AsyncTask是个抽象类，使用时需要继承这个类，然后调用execute()方法。注意继承时需要设定三个泛型Params，Progress和Result的类型，如AsyncTask<Void,Inetger,Void>：
     * <p>
     * Params是指调用execute()方法时传入的参数类型和doInBackgound()的参数类型
     * Progress是指更新进度时传递的参数类型，即publishProgress()和onProgressUpdate()的参数类型
     * Result是指doInBackground()的返回值类型
     */
    class UpdateTextTask extends AsyncTask<Void, Integer, Integer> {


        /**
         * 运行在UI线程中，在调用doInBackground()之前执行
         */
        @Override
        protected void onPreExecute() {
            ToastUtils.showToastShort("开始执行");
        }

        /**
         * 后台运行的方法，可以运行非UI线程，可以执行耗时的方法
         */
        @Override
        protected Integer doInBackground(Void... params) {
            int i = 0;
            while (i < 10) {
                i++;
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
            return null;
        }

        /**
         * 运行在ui线程中，在doInBackground()执行完毕后执行
         */
        @Override
        protected void onPostExecute(Integer integer) {
            ToastUtils.showToastShort("执行完毕");

        }

        /**
         * 在publishProgress()被调用以后执行，publishProgress()用于更新进度
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            tvMessage.setText("" + values[0]);
        }
    }
}
