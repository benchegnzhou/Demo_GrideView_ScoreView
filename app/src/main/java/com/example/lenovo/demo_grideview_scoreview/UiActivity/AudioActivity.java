package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.demo_grideview_scoreview.R;

public class AudioActivity extends AppCompatActivity {


        private SoundPool pool;


        @Override
        protected void onCreate( Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            setContentView(R.layout.audio_activity);
            //指定声音池的最大音频流数目为10，声音品质为5
            pool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
            //载入音频流，返回在池中的id
            final int sourceid = pool.load(this, R.raw.zxc, 0);
            Button button = (Button) this.findViewById(R.id.bt_audio);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {         //播放音频，第二个参数为左声道音量;第三个参数为右声道音量;第四个参数为优先级；第五个参数为循环次数，0不循环，-1循环;第六个参数为速率，速率    最低0.5最高为2，1代表正常速度
                    pool.play(sourceid, 1, 1, 0, 0, 1);
                }
            });

    }
}
