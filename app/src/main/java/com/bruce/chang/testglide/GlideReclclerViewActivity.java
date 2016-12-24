package com.bruce.chang.testglide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bruce.chang.testglide.adapter.GlideRecyclerviewAdapter;

public class GlideReclclerViewActivity extends AppCompatActivity {

    RecyclerView rv_glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_reclcler_view);
        rv_glide = (RecyclerView) findViewById(R.id.rv_glide);
        GlideRecyclerviewAdapter glideTranformationsAdapter = new GlideRecyclerviewAdapter(this);
        rv_glide.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_glide.setAdapter(glideTranformationsAdapter);

    }
}
