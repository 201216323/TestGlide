package com.bruce.chang.testglide;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

import java.io.File;

public class GlideBaseActivity extends AppCompatActivity {

    TextView tvGlide1;
    ImageView ivGlide1;
    TextView tvGlide2;
    ImageView ivGlide2;
    TextView tvGlide3;
    ImageView ivGlide3;
    TextView tvGlide4;
    ImageView ivGlide4;
    TextView tvGlide5;
    ImageView ivGlide5;
    TextView tvGlide6;
    ImageView ivGlide6;
    TextView tvGlide7;
    ImageView ivGlide7;
    TextView tvGlide8;
    ImageView ivGlide8;
    TextView tvGlide9;
    ImageView ivGlide9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_base);
        initView();
        initData();
    }

    private void initData() {
        //（1）加载网络图片
        tvGlide1.setText("1加载网络图片");
        Glide.with(this).load("http://img.blog.csdn.net/20161220174646569").into(ivGlide1);
        //（2）加载资源图片
        tvGlide2.setText("2加载资源图片");
        Glide.with(this).load(R.mipmap.fbb).into(ivGlide2);
        //（3）加载本地图片
        tvGlide3.setText("3加载本地图片");
        String path = Environment.getExternalStorageDirectory() + "/swj.jpg";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide.with(this).load(uri).into(ivGlide3);
        // （4）加载网络gif
        tvGlide4.setText("4加载网络gif");
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide.with(this).load(gifUrl).placeholder(R.mipmap.ic_launcher).into(ivGlide4);
        // （5）加载资源gif
        tvGlide5.setText("5加载资源gif");
        Glide.with(this).load(R.mipmap.a).asGif().placeholder(R.mipmap.ic_launcher).into(ivGlide5);
        //（6）加载本地gif
        tvGlide6.setText("6加载本地gif");
        String gifPath = Environment.getExternalStorageDirectory() + "/aobana.gif";
        File gifFile = new File(gifPath);
        Glide.with(this).load(gifFile).placeholder(R.mipmap.ic_launcher).into(ivGlide6);
        //（7）加载本地小视频和快照
        tvGlide7.setText("7加载本地小视频和快照");
        String videoPath = Environment.getExternalStorageDirectory() + "/1.mp4";
        File videoFile = new File(videoPath);
        Glide.with(this).load(Uri.fromFile(videoFile)).placeholder(R.mipmap.ic_launcher).into(ivGlide7);
        //（8）设置缩略图比例,然后，先加载缩略图，再加载原图
        tvGlide8.setText("8设置缩略图比例,然后，先加载缩略图，再加载原图");
        String urlPath = "/storage/emulated/0/swj.jpg";
        Glide.with(this).load(new File(urlPath)).thumbnail(0.1f).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide8);
        //（9）先建立一个缩略图对象，然后，先加载缩略图，再加载原图
        tvGlide9.setText("9先建立一个缩略图对象，然后，先加载缩略图，再加载原图");
        DrawableRequestBuilder thumbnailRequest = Glide.with(this).load(new File(urlPath));
        Glide.with(this).load(Uri.fromFile(videoFile)).thumbnail(thumbnailRequest).centerCrop().placeholder(R.mipmap.ic_launcher).into(ivGlide9);

    }

    private void initView() {
        tvGlide1 = (TextView) findViewById(R.id.tv_glide_1);
        ivGlide1 = (ImageView) findViewById(R.id.iv_glide_1);
        tvGlide2 = (TextView) findViewById(R.id.tv_glide_2);
        ivGlide2 = (ImageView) findViewById(R.id.iv_glide_2);

        tvGlide3 = (TextView) findViewById(R.id.tv_glide_3);
        ivGlide3 = (ImageView) findViewById(R.id.iv_glide_3);

        tvGlide4 = (TextView) findViewById(R.id.tv_glide_4);
        ivGlide4 = (ImageView) findViewById(R.id.iv_glide_4);

        tvGlide5 = (TextView) findViewById(R.id.tv_glide_5);
        ivGlide5 = (ImageView) findViewById(R.id.iv_glide_5);

        tvGlide6 = (TextView) findViewById(R.id.tv_glide_6);
        ivGlide6 = (ImageView) findViewById(R.id.iv_glide_6);

        tvGlide7 = (TextView) findViewById(R.id.tv_glide_7);
        ivGlide7 = (ImageView) findViewById(R.id.iv_glide_7);

        tvGlide8 = (TextView) findViewById(R.id.tv_glide_8);
        ivGlide8 = (ImageView) findViewById(R.id.iv_glide_8);

        tvGlide9 = (TextView) findViewById(R.id.tv_glide_9);
        ivGlide9 = (ImageView) findViewById(R.id.iv_glide_9);


    }
}
