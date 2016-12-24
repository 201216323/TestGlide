package com.bruce.chang.testglide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bruce.chang.testglide.adapter.GlideTranformationsAdapter;

public class GlideTransformationActivity extends AppCompatActivity {

    RecyclerView rv_glide_transformations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_transformation);

        rv_glide_transformations = (RecyclerView) findViewById(R.id.rv_glide_transformations);
        GlideTranformationsAdapter glideTranformationsAdapter = new GlideTranformationsAdapter(this);
        rv_glide_transformations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_glide_transformations.setAdapter(glideTranformationsAdapter);
    }
}
