# 1：简介

在泰国举行的谷歌开发者论坛上，谷歌为我们介绍了一个名叫 Glide 的图片加载库，作者是**bumptech**。这个库被广泛的运用在google的开源项目中，包括2014年google I/O大会上发布的官方app。

## 1.1：Glide特点

1. 使用简单。
2. 可配置度高，自适应程度高。
3. 支持常见图片格式 Jpg png gif webp。
4. 支持多种数据源，网络、本地、资源、Assets 等。
5. 高效缓存策略，支持Memory和Disk图片缓存，默认Bitmap格式采用RGB_565内存使用至少减少一半。
6. 生命周期集成，根据Activity/Fragment生命周期自动管理请求。
7. 高效处理Bitmap，使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力。


# 2：下载地址

Glide的下载地址是 https://github.com/bumptech/glide，在Github上已经获得了12439个星星了，可见是多么的受欢迎。

Glide最新版本是3.7.0。


# 3：功能

## 3.1：简单使用


```
Glide
    .with(this)
    .load("http://p0.so.qhmsg.com/bdr/_240_/t01db2dc500ce1ce74a.jpg")
    .into(imageView);
```

## 3.2：Glide.with()使用

1. with(Context context)，使用Application上下文，Glide请求将不受Activity/Fragment生命周期控制。
2. with(Activity activity)，使用Activity作为上下文，Glide的请求会受到Activity生命周期控制。
3. with(FragmentActivity activity)，Glide的请求会受到FragmentActivity生命周期控制。
4. with(android.app.Fragment fragment)，Glide的请求会受到Fragment 生命周期控制。
5. with(android.support.v4.app.Fragment fragment)，Glide的请求会受到Fragment生命周期控制。

## 3.3：load()使用

Glide基本可以load任何可以拿到的媒体资源：

1. SD卡资源：
```
load("file://"+ Environment.getExternalStorageDirectory().getPath()+"/test.jpg")
```

2. assets资源：
```
load("file:///android_asset/f003.gif")
3. raw资源：load("Android.resource://com.frank.glide/raw/raw_1")或load("android.resource://com.frank.glide/raw/"+R.raw.raw_1)
```

4. drawable资源：
```
load("android.resource://com.frank.glide/drawable/news")或load("android.resource://com.frank.glide/drawable/"+R.drawable.news)
```

5. ContentProvider资源：
```
load("content://media/external/images/media/139469")
```

6. http资源：
```
load("http://img.my.csdn.NET/uploads/201508/05/1438760757_3588.jpg")
```

7. https资源：
```
load("https://img.alicdn.com/tps/TB1uyhoMpXXXXcLXVXXXXXXXXXX-476-538.jpg_240x5000q50.jpg_.webp")
```



> 注意：

load不限于String类型，还可以是下表中的类型。


参数 | 说明
---|---
.load(String string) | string可以为一个文件路径、uri或者url
.load(Uri uri) | uri类型
.load(File file) | 文件
.load(Integer resourceId) | 资源Id,R.drawable.xxx或者R.mipmap.xxx
.load(byte[] model) | byte[]类型
.load(T model) | 自定义类型
loadFromMediaStore(Uri uri)|uri类型

## 3.4：重要功能

1. 禁止内存缓存
```
.skipMemoryCache(true)
```
2. 清除内存缓存

```
 // 必须在UI线程中调用
    Glide.get(context).clearMemory();
```

3. 禁止磁盘缓存
```
 .diskCacheStrategy(DiskCacheStrategy.NONE)
```

4. 清除磁盘缓存
```
 // 必须在后台线程中调用，建议同时clearMemory()
   Glide.get(applicationContext).clearDiskCache();
```

5. 获取缓存大小
```
new GetDiskCacheSizeTask(textView).execute(new File(getCacheDir(), DiskCache.Factory.DEFAULT_DISK_CACHE_DIR));
```


```
class GetDiskCacheSizeTask extends AsyncTask<File, Long, Long> {
private final TextView resultView;

public GetDiskCacheSizeTask(TextView resultView) {
    this.resultView = resultView;
}

@Override
protected void onPreExecute() {
    resultView.setText("Calculating...");
}

@Override
protected void onProgressUpdate(Long... values) { /* onPostExecute(values[values.length - 1]); */ }

@Override
protected Long doInBackground(File... dirs) {
    try {
        long totalSize = 0;
        for (File dir : dirs) {
            publishProgress(totalSize);
            totalSize += calculateSize(dir);
        }
        return totalSize;
    } catch (RuntimeException ex) {
        final String message = String.format("Cannot get size of %s: %s", Arrays.toString(dirs), ex);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                resultView.setText("error");
                Toast.makeText(resultView.getContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
    return 0L;
}

@Override
protected void onPostExecute(Long size) {
    String sizeText = android.text.format.Formatter.formatFileSize(resultView.getContext(), size);
    resultView.setText(sizeText);
}

private static long calculateSize(File dir) {
    if (dir == null) return 0;
    if (!dir.isDirectory()) return dir.length();
    long result = 0;
    File[] children = dir.listFiles();
    if (children != null)
        for (File child : children)
            result += calculateSize(child);
    return result;
}
}
```

6. 指定资源的优先加载顺序

```
//优先加载
    Glide
        .with(context)
        .load(heroImageUrl)
        .priority(Priority.HIGH)
        .into(imageViewHero);
    //后加载
    Glide
        .with(context)
        .load(itemImageUrl)
        .priority(Priority.LOW)
        .into(imageViewItem);
```

7. 先显示缩略图，再显示原图
```
//用原图的1/10作为缩略图
    Glide
        .with(this)
        .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
        .thumbnail(0.1f)
        .into(iv_0);

    //用其它图片作为缩略图
    DrawableRequestBuilder<Integer> thumbnailRequest = Glide
        .with(this)
        .load(R.drawable.news);

    Glide.with(this)
        .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
        .thumbnail(thumbnailRequest)
        .into(iv_0);
```

8. 对图片进行裁剪、模糊、滤镜等处理
```
	//圆形裁剪
    Glide.with(this)
        .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
        .bitmapTransform(new CropCircleTransformation(this))
        .into(iv_0);

    //圆角处理
    Glide.with(this)
        .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
        .bitmapTransform(new RoundedCornersTransformation(this,30,0, RoundedCornersTransformation.CornerType.ALL))
        .into(iv_0);

    //灰度处理
    Glide.with(this)
        .load("http://inthecheesefactory.com/uploads/source/nestedfragment/fragments.png")
        .bitmapTransform(new GrayscaleTransformation(this))
        .into(iv_0);
    //其它变换...
```

9. 对请求状态进行监听
10. 对资源的下载进度进行监听

## 3.5：API方法说明

1. thumbnail(float sizeMultiplier)

```
请求给定系数的缩略图。如果缩略图比全尺寸图先加载完，就显示缩略图，否则就不显示。系数sizeMultiplier必须在(0,1)之间，可以递归调用该方法。
```
2. sizeMultiplier(float sizeMultiplier)
```
在加载资源之前给Target大小设置系数。
```

3. diskCacheStrategy(DiskCacheStrategy strategy)
```
设置缓存策略。
DiskCacheStrategy.SOURCE：缓存原始数据，
DiskCacheStrategy.RESULT：缓存变换(如缩放、裁剪等)后的资源数据，
DiskCacheStrategy.NONE：什么都不缓存，
DiskCacheStrategy.ALL：缓存SOURC和RESULT。
默认采用DiskCacheStrategy.RESULT策略，对于download only操作要使用DiskCacheStrategy.SOURCE。
```

4. priority(Priority priority)
```
指定加载的优先级，优先级越高越优先加载，但不保证所有图片都按序加载。
枚举
Priority.IMMEDIATE，
Priority.HIGH，
Priority.NORMAL，
Priority.LOW。
默认为Priority.NORMAL。
```

5. dontAnimate()
```
移除所有的动画。
```

6. animate(int animationId)
```
在异步加载资源完成时会执行该动画。
```

7. animate(ViewPropertyAnimation.Animator animator)
```
在异步加载资源完成时会执行该动画。
```

8. placeholder(int resourceId)
```
设置资源加载过程中的占位Drawable。
```

9. placeholder(Drawable drawable)
```
设置资源加载过程中的占位Drawable。
```

10. fallback(int resourceId)
```
设置model为空时要显示的Drawable。
如果没设置fallback，model为空时将显示error的Drawable，
如果error的Drawable也没设置，就显示placeholder的Drawable。
```

11. fallback(Drawable drawable)
```
设置model为空时显示的Drawable。
```

12. error(int resourceId)
```
设置load失败时显示的Drawable。
```

13. error(Drawable drawable)
```
设置load失败时显示的Drawable。
```

14. listener(RequestListener<? super ModelType, TranscodeType> requestListener)
```
监听资源加载的请求状态，可以使用两个回调：
onResourceReady(R resource, T model, Target<R> target, boolean isFromMemoryCache, boolean isFirstResource)
和
onException(Exception e, T model, Target&lt;R&gt; target, boolean isFirstResource)，
但不要每次请求都使用新的监听器，要避免不必要的内存申请，可以使用单例进行统一的异常监听和处理。
```

15. skipMemoryCache(boolean skip)
```
设置是否跳过内存缓存，但不保证一定不被缓存
（比如请求已经在加载资源且没设置跳过内存缓存，这个资源就会被缓存在内存中）。
```

16. override(int width, int height)
```
重新设置Target的宽高值（单位为pixel）。
```

17. into(Y target)
```
设置资源将被加载到的Target。
```

18. into(ImageView view)
```
设置资源将被加载到的ImageView。取消该ImageView之前所有的加载并释放资源。
```

19. into(int width, int height)
```
后台线程加载时要加载资源的宽高值（单位为pixel）。
```

20. preload(int width, int height)
```
预加载resource到缓存中（单位为pixel）。
```

21. asBitmap()
```
无论资源是不是gif动画，都作为Bitmap对待。如果是gif动画会停在第一帧。
```

22. asGif()
```
把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()。
```


# 4：使用步骤

## 4.1：在build.gradle中添加依赖

```
compile 'com.github.bumptech.glide:glide:3.7.0'
```
## 4.2：如果你的项目没有support-v4库，还需要添加support-v4依赖

```
 compile 'com.android.support:support-v4:23.3.0'
```

## 4.3：如果使用变换，可以添加一个自定义的变换库

> github网址：


```
https://github.com/wasabeef/glide-transformations
```
> 添加依赖

```
compile 'jp.wasabeef:glide-transformations:2.0.1'
```


```
// If you want to use the GPU Filters
compile 'jp.co.cyberagent.android.gpuimage:gpuimage-library:1.3.0'
```

# 5：例子

## 5.1：基本使用

### 5.1.1：布局

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">

    <ScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent">


        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical">

            <TextView
                android:id="@+id/tv_glide_1"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:layout_margin="5dp" />

            <ImageView
                android:id="@+id/iv_glide_1"

                android:layout_width="200dp"
                android:layout_height="200dp" />

            //2 3 4 5 6 7 8 9.....
        </LinearLayout>


    </ScrollView>
</LinearLayout>
```

### 5.1.2：加载网络图片

```
Glide
    .with(this)
    .load("http://img.blog.csdn.net/20161220174646569")
    .into(ivGlide1);
```
### 5.1.3：加载资源图片

```
Glide
    .with(this
    .load(R.mipmap.fbb)
    .into(ivGlide2);
```

### 5.1.4：加载本地图片
```
 String path = Environment.getExternalStorageDirectory() + "/swj.jpg";
        File file = new File(path);
        Uri uri = Uri.fromFile(file);
        Glide
            .with(this)
            .load(uri)
            .into(ivGlide3);
```
### 5.1.5：加载网络gif
```
        String gifUrl = "http://b.hiphotos.baidu.com/zhidao/pic/item/faedab64034f78f066abccc57b310a55b3191c67.jpg";
        Glide
            .with(this)
            .load(gifUrl)
            .placeholder(R.mipmap.ic_launcher)
            .into(ivGlide4);
```

### 5.1.6：加载资源gif
```
 Glide
     .with(this)
     .load(R.mipmap.a)
     .asGif()
     .placeholder(R.mipmap.ic_launcher)
     .into(ivGlide5);
```

### 5.1.7：加载本地gif
```
String gifPath = Environment.getExternalStorageDirectory() + "/aobana.gif";
        File gifFile = new File(gifPath);
        Glide
            .with(this)
            .load(gifFile)
            .placeholder(R.mipmap.ic_launcher)
            .into(ivGlide6);
```


### 5.1.8：加载本地小视频和快照
```
 String videoPath = Environment.getExternalStorageDirectory() + "/1.mp4";
        File videoFile = new File(videoPath);
        Glide
            .with(this)
            .load(Uri.fromFile(videoFile))
            .placeholder(R.mipmap.ic_launcher)
            .into(ivGlide7);
```


### 5.1.9：设置缩略图比例,然后，先加载缩略图，再加载原图
```
String urlPath = "/storage/emulated/0/swj.jpg";
        Glide
            .with(this)
            .load(new File(urlPath))
            .thumbnail(0.1f)
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher)
            .into(ivGlide8);
```


### 5.1.10：先建立一个缩略图对象，然后，先加载缩略图，再加载原图

```
    DrawableRequestBuilder thumbnailRequest = Glide.with(this).load(new File(urlPath));
    Glide
    .with(this)
    .load(Uri.fromFile(videoFile))
    .thumbnail(thumbnailRequest)
    .centerCrop()
    .placeholder(R.mipmap.ic_launcher)
    .into(ivGlide9);
```
> 运行结果

![Glide基本加载](http://ww2.sinaimg.cn/mw690/b0d9a523jw1fb23wdra59g208h0gme82.gif)

## 5.2：RecyclerView中加载图片

### 5.2.1：布局

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
>

    <include layout="@layout/titlebar"></include>

    <android.support.v7.widget.RecyclerView
        android:id="@+id/rv_glide"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

    </android.support.v7.widget.RecyclerView>
</LinearLayout>

```


### 5.2.2：初始化RecyclerView
```
        GlideAdapter glideAdapter = new GlideAdapter(this);
        rvGlide.setAdapter(glideAdapter);
        rvGlide.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
```

### 5.2.3：编写适配器(核心代码)

> 准备数据

```
String[] mDatas = new String[]{
   "http://i.imgur.com1111/Z3QjilA.jpg"
   //。。。。。。。
    };
```
> 加载图片


```
 // 显示数据
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(mDatas[position])
                .placeholder(R.mipmap.ic_launcher) //占位图
                .error(R.mipmap.ic_launcher)  //出错的占位图
                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
                .centerCrop()
                .animate(R.anim.glide_anim)
                .fitCenter()
                .into(holder.image);
```
> 加载动画 R.anim.glide_anim

```
<?xml version="1.0" encoding="utf-8"?>
<set xmlns:android="http://schemas.android.com/apk/res/android">

    <!--渐变-->
    <alpha
        android:duration="3000"
        android:fromAlpha="0"
        android:toAlpha="1" />

    <!--缩放-->
    <scale
        android:duration="3000"
        android:fromXScale="0"
        android:fromYScale="0"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toXScale="100%"
        android:toYScale="100%" />

    <!--旋转-->
    <rotate
        android:duration="3000"
        android:fromDegrees="30"
        android:pivotX="50%"
        android:pivotY="50%"
        android:toDegrees="360" />

</set>
```
> 运行结果

![image](http://ww3.sinaimg.cn/mw690/b0d9a523jw1fb27085fqqg208h0gmhdt.gif)

## 5.3：变换

### 5.3.1：布局

布局同5.2.1一样只有一个RecyclerView，这里省略。

### 5.3.2：初始化RecyclerView

```
GlideTranformationsAdapter glideTranformationsAdapter = new GlideTranformationsAdapter(this);
        rv_glide_transformations.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_glide_transformations.setAdapter(glideTranformationsAdapter);
```

### 5.3.3：编写适配器


> a)准备数据

这里对mipmap下的一张图片进行处理，

![image](http://ww2.sinaimg.cn/mw690/b0d9a523jw1fb272glrqkj20go0d2jrz.jpg)

> b)变换加载图片


```
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
```


> 运行结果

![image](http://ww2.sinaimg.cn/mw690/b0d9a523jw1fb273thxgdg208h0gm7wh.gif)

# 6：Glide总结

Glide加载图片功能太强大了，配合着transformations库文件，能实现那么多的效果，这一点不可思议，unbelievable，在我的这个项目中，只有这么简单的功能，APP就已经3.08M了，这一点感觉有点大，不过在这强大的功能面前还是值得的。

在实际开发中，如果使用Glide，都会对这进行一定的封装，这里就不介绍了，有需要的都可以取Github上搜搜看看。

> 源码下载地址：



> 欢迎访问[201216323.tech](http://www.201216323.tech)来查看我的CSDN博客。

> 欢迎关注我的个人技术公众号,快速查看我的最新文章。

![我的公众号图片](http://img.blog.csdn.net/20161220174646569?watermark/2/text/aHR0cDovL2Jsb2cuY3Nkbi5uZXQvY2NnXzIwMTIxNjMyMw==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70/gravity/SouthEast "bruce常")