package com.bruce.chang.testglide.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PointF;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bruce.chang.testglide.R;
import com.bruce.chang.testglide.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;

/**
 * Created by Administrator on 2016/10/28.
 */

public class GlideTranformationsAdapter extends RecyclerView.Adapter<GlideTranformationsAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    public GlideTranformationsAdapter(Context context) {
        mContext = context;
        for (int i = 1; i <= 21; i++) {
            mData.add(i + "");
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.item_glide_tranformations, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // 设置名称
        holder.name.setText("item" + (position + 1));

        int integer = Integer.parseInt(mData.get(position));

        switch (integer) {
            case 1: {
                int width = Utils.dip2px(mContext, 200f);
                int height = Utils.dip2px(mContext, 200f);
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_starfish))
                        .into(holder.image);

                break;
            }
            case 2: {
                int width = Utils.dip2px(mContext, 150.0f);
                int height = Utils.dip2px(mContext, 100.0f);
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .override(width, height)
                        .bitmapTransform(new CenterCrop(mContext),
                                new MaskTransformation(mContext, R.drawable.mask_chat_right))
                        .into(holder.image);
                break;
            }
            case 3:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP))//顶部裁剪
                        .into(holder.image);
                break;
            case 4:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new CropTransformation(mContext, 300, 100))//默认居中裁剪
                        .into(holder.image);
                break;
            case 5:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(
                                new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM))//底部裁剪
                        .into(holder.image);

                break;
            case 6:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new CropSquareTransformation(mContext))//裁剪正方形
                        .into(holder.image);
                break;
            case 7:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new CropCircleTransformation(mContext))//裁剪圆形
                        .into(holder.image);
                break;
            case 8:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0)))//增加颜色滤镜效果
                        .into(holder.image);
                break;
            case 9:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new GrayscaleTransformation(mContext))//灰度
                        .into(holder.image);
                break;
            case 10:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new RoundedCornersTransformation(mContext, 30, 20,
                                RoundedCornersTransformation.CornerType.ALL))//圆角处理
                        .into(holder.image);
                break;
            case 11:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new BlurTransformation(mContext, 25))//模糊处理
                        .into(holder.image);
                break;
            case 12:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new ToonFilterTransformation(mContext))//有点像素描处理
                        .into(holder.image);
                break;
            case 13:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new SepiaFilterTransformation(mContext))//
                        .into(holder.image);
                break;
            case 14:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new ContrastFilterTransformation(mContext, 2.0f))
                        .into(holder.image);
                break;
            case 15:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new InvertFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 16:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new PixelationFilterTransformation(mContext, 20))
                        .into(holder.image);
                break;
            case 17:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new SketchFilterTransformation(mContext))
                        .into(holder.image);
                break;
            case 18:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(
                                new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f)))
                        .into(holder.image);
                break;
            case 19:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new BrightnessFilterTransformation(mContext, 0.5f))
                        .into(holder.image);
                break;
            case 20:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new KuwaharaFilterTransformation(mContext, 25))
                        .into(holder.image);
                break;
            case 21:
                Glide.with(mContext)
                        .load(R.mipmap.fbb)
                        .bitmapTransform(new VignetteFilterTransformation(mContext, new PointF(0.5f, 0.5f),
                                new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f))
                        .into(holder.image);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView)itemView.findViewById(R.id.iv_glide_tranfromations);
            name = (TextView)itemView.findViewById(R.id.tv_glide_name);
        }
    }
}
