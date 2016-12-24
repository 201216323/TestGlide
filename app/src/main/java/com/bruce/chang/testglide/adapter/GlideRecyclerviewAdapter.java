package com.bruce.chang.testglide.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bruce.chang.testglide.R;
import com.bumptech.glide.Glide;


/**
 * Created by Administrator on 2016/10/28.
 */

public class GlideRecyclerviewAdapter extends RecyclerView.Adapter<GlideRecyclerviewAdapter.ViewHolder> {
    private Context mContext;

    String[] mDatas = new String[]{
            "http://p2.so.qhmsg.com/bdr/_240_/t01ffcdaedde9bcb74c.jpg",
            "http://http://pic1.desk.chinaz.com/file/201201/4/jxipbz8_p.jpg",
            "http://img.article.pchome.net/00/56/35/09/pic_lib/wm/fengjing1.jpg",
            "http://p1.so.qhmsg.com/bdr/_240_/t0189a38410d9b1a6db.jpg",
            "http://p3.so.qhmsg.com/bdr/_240_/t0118233347354d0838.jpg",
            "http://www.1tong.com/uploads/allimg/131217/1-13121G14R70-L.jpg",
            "http://ww1.sinaimg.cn/mw690/b0d9a523jw1fasfnzuyj1j20760760t2.jpg",
            "http://p1.so.qhmsg.com/bdr/_240_/t01ffd622bffeabb5e1.jpg",
            "http://p4.so.qhmsg.com/bdr/_240_/t01956d7d9ed4a92064.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/f778b46711474fe9b312ea9386ab2d2b.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/cb7a48787b71450f8ea941aeef95a5f9.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/6690ce1ec57e47cd98d08c2eb6d1c382.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/60c9a63daf7e4a2ab2f765857fa639a1.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/488bbab516b84af89b60170ef299b4f1.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/bc9bc6cbc59a483397ca05f09cbe26b6.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/972c13f79ea74aa09d7742af3c81d00c.jpg",
            "http://ws-ugc.fithub.cc/app/2199695/dynamic/7432/e192b9612a5d4f508255c149771f4e10.jpg"
    };

    public GlideRecyclerviewAdapter(Context context) {
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(mContext, R.layout.item_glide_recyclerview, null);
        return new ViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // 获取当前item数据

        // 显示数据
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(mDatas[position])
                .placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_glide_recyclerview);
        }
    }
}
