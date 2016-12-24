package com.bruce.chang.testglide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_glide_base, bt_glide_recycler, bt_glide_transformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewAndListener();

    }

    private void initViewAndListener() {
        bt_glide_base = (Button) findViewById(R.id.bt_glide_base);
        bt_glide_recycler = (Button) findViewById(R.id.bt_glide_recycler);
        bt_glide_transformations = (Button) findViewById(R.id.bt_glide_transformations);
        bt_glide_base.setOnClickListener(this);
        bt_glide_recycler.setOnClickListener(this);
        bt_glide_transformations.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_glide_base:
                startActivity(new Intent(MainActivity.this,GlideBaseActivity.class));
                break;
            case R.id.bt_glide_recycler:
                startActivity(new Intent(MainActivity.this,GlideReclclerViewActivity.class));
                break;
            case R.id.bt_glide_transformations:
                startActivity(new Intent(MainActivity.this,GlideTransformationActivity.class));
                break;
            default:
                break;
        }
    }
}
