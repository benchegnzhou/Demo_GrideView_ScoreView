package com.example.lenovo.demo_grideview_scoreview.UiActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lenovo.demo_grideview_scoreview.R;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        getSupportActionBar().hide();

    }
}
