package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.lenovo.demo_grideview_scoreview.R;
import com.example.lenovo.demo_grideview_scoreview.fragment.AnimeFragment;
import com.example.lenovo.demo_grideview_scoreview.fragment.MovieFragment;
import com.example.lenovo.demo_grideview_scoreview.fragment.TVFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentChangeActivity extends AppCompatActivity implements View.OnClickListener{


    private Button movieBtn, tvBtn, animeBtn, varietyBtn;
    private List<Button> btnList = new ArrayList< Button >();
    private FragmentManager fm;
    private FragmentTransaction ft;
    private MovieFragment movieFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_change);
        findById();
        // 進入系統默認為movie
        fm = getFragmentManager();
        ft = fm.beginTransaction();

        setBackgroundColorById(R.id.movie_btn);
        ft.replace(R.id.fragment_content, new MovieFragment());
        ft.commit();
    }

    private void findById() {
        movieBtn = (Button) this.findViewById(R.id.movie_btn);
        tvBtn = (Button) this.findViewById(R.id.tv_btn);
        animeBtn = (Button) this.findViewById(R.id.anime_btn);
        varietyBtn = (Button) this.findViewById(R.id.variety_btn);
        movieBtn.setOnClickListener(this);
        tvBtn.setOnClickListener(this);
        animeBtn.setOnClickListener(this);
        varietyBtn.setOnClickListener(this);

        btnList.add(movieBtn);
        btnList.add(tvBtn);
        btnList.add(animeBtn);
        btnList.add(varietyBtn);
    }

    private void setBackgroundColorById(int btnId) {
        for (Button btn : btnList) {
            if (btn.getId() == btnId) {
                btn.setBackgroundColor(Color.GREEN);
            } else {
                btn.setBackgroundColor(Color.BLUE);
            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        // Inflate the menu; this addsitems to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clickshere. The action bar will
        // automatically handle clicks onthe Home/Up button, so long
        // as you specify a parentactivity in AndroidManifest.xml.
        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        fm = getFragmentManager();
        ft = fm.beginTransaction();
        switch (v.getId()) {

            case R.id.movie_btn:
                setBackgroundColorById(R.id.movie_btn);

//                ft.replace(R.id.fragment_content, new MovieFragment());
                if(movieFragment==null){
                    movieFragment = new MovieFragment();
                }

                ft.replace(R.id.fragment_content, movieFragment);
                break;

            case R.id.tv_btn:
                setBackgroundColorById(R.id.tv_btn);

                ft.replace(R.id.fragment_content, new TVFragment());
                break;

            case R.id.anime_btn:
                setBackgroundColorById(R.id.anime_btn);

                ft.replace(R.id.fragment_content, new AnimeFragment());
                break;

            case R.id.variety_btn:
                setBackgroundColorById(R.id.variety_btn);
                if(movieFragment!=null){
                    movieFragment.requestData();
                }
                ft.replace(R.id.fragment_content, new AnimeFragment());
               // ft.replace(R.id.fragment_content, new VarietyFragment());
                break;

            default:
                break;
        }
        // 不要忘记提交
        ft.commit();
    }


}
