# activity的转场动画 #

参考链接：

[http://m.blog.csdn.net/article/details?id=51821159](http://m.blog.csdn.net/article/details?id=51821159)


view的动画实现

[http://blog.csdn.net/crazy1235/article/details/50612827](http://blog.csdn.net/crazy1235/article/details/50612827)

Android5.0中强大的过度动画

[http://blog.csdn.net/qibin0506/article/details/48129139/](http://blog.csdn.net/qibin0506/article/details/48129139/)

在介绍activity的切换动画之前我们先来说明一下实现切换activity的两种方式：
调用startActivity方法启动一个新的Activity并跳转其页面
调用finish方法销毁当前的Activity返回上一个Activity界面
当调用startActivity方法的时候启动一个新的activity，这时候就涉及到了旧的Activity的退出动画和新的Activity的显示动画； 
当调用finish方法的时候，销毁当前Acitivity，就涉及到了当前Activity的退出动画和前一个Activity的显示动画；
所以我们的activity跳转动画是分为两个部分的：一个Activity的销毁动画与一个Activity的显示动画，明白了这一点之后我们开始看一下第一种实现Activity跳转动画的方式：通过overridePendingTransition方法实现Activity切换动画。


（一）**使用overridePendingTransition方法实现Activity跳转动画**

overridePendingTransition方法是Activity中提供的Activity跳转动画方法，通过该方法可以实现Activity跳转时的动画效果。下面我们就将通过一个简单的例子看一下如何通过overridePendingTransition方法实现Activity的切换动画。
demo例子中我们实现了Activity a中有一个点击按钮，点击按钮实现跳转Activity b的逻辑，具体代码如下：

 	   /**
        * 在调用了startActivity方法之后立即调用overridePendingTransition方法
        */
        Intent intent = new Intent(Animatio4nActivity.this, ActivityAnimation.class);
        startActivity(intent);
        //重写Activity原有的动画，参数传入0表示使用默认动画，参数1当前Activity进入的动画，参数2： 当前Activity退出时的动画
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);	



可以看到我们在调用了startActivity方法之后又执行了overridePendingTransition方法，而在overridePendingTransition方法中传递了两个动画布局文件，我们首先看一下这里的动画文件具体是怎么实现的：

在slide_in_left.xml文件中：

	<?xml version="1.0" encoding="utf-8"?>
	<set xmlns:android="http://schemas.android.com/apk/res/android">

        <translate
            android:duration="600"
            android:fromXDelta="100%p"
            android:toXDelta="0" />
        <alpha
            android:duration="600"
            android:fromAlpha="0.0"
            android:interpolator="@android:anim/accelerate_interpolator"
            android:toAlpha="1.0" />
        <scale
            xmlns:android="http://schemas.android.com/apk/res/android"
            android:duration="600"
            android:fromXScale="0"
            android:fromYScale="0"
            android:pivotX="50%"
            android:pivotY="50%"
            android:toXScale="1"
            android:toYScale="1" >
        </scale>
	</set>


这里的overridePendingTransition方法传递的是两个动画文件id，第一个参数是需要打开的Activity进入时的动画，第二个参数是需要关闭的Activity离开时的动画。这样我们执行了这段代码之后在跳转Activity的时候就展示了动画效果：

 //重写Activity原有的动画，参数传入0表示使用默认动画，参数1当前Activity进入的动画，参数2： 当前Activity退出时的动画
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_right);


![](http://i.imgur.com/tp3zkCH.gif)


**通过阅读系统的源码，我们能够知道：**

overridePendingTransition方法需要在startAtivity方法或者是finish方法调用之后立即执行
参数enterAnim表示的是从Activity a跳转到Activity b，进入b时的动画效果
参数exitAnim表示的是从Activity a跳转到Activity b，离开a时的动过效果
若进入b或者是离开a时不需要动画效果，则可以传值为0


当然 overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);是可以在子线程中执行的

	/**
	 * 点击按钮实现跳转逻辑
	 */
	button1.setOnClickListener(new View.OnClickListener() {
	            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                /**
                 * 在子线程中执行overridePendingTransition方法
                 */
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                    }
                }).start();

            }
        });



**（三）使用ActivityOptions切换动画实现Activity跳转动画**
上面我们讲解的通过overridePendingTransition方法基本上可以满足我们日常中对Activity跳转动画的需求了，但是MD风格出来之后，overridePendingTransition这种老旧、生硬的方式怎么能适合我们的MD风格的App呢？好在google在新的sdk中给我们提供了另外一种Activity的过度动画——ActivityOptions。并且提供了兼容包——ActivityOptionsCompat。ActivityOptionsCompat是一个静态类，提供了相应的Activity跳转动画效果，通过其可以实现不少炫酷的动画效果。
（1）在跳转的Activity中设置contentFeature

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 设置contentFeature,可使用切换动画
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        Transition explode = TransitionInflater.from(this).inflateTransition(Android.R.transition.explode);
        getWindow().setEnterTransition(explode);

        setContentView(R.layout.activity_three);
    }

这里我们在Activity的setContentView之前调用了：

	getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

方法，看过我的Activity布局加载流程的同学应该知道，这里的setFeature就是为activity的窗口设置特性，不同的特性对应不同的布局方式，比如可以设置无toolbar模式，有toolbar模式等等。而这里设置的是需要过渡动画，并且我们获取了Android中内置的explode动画，并设值给了Activity的window窗口对象，这样当Activity被启动的时候就会执行explode所带便的动画效果了。
（2）在startActivity执行跳转逻辑的时候调用startActivity的重写方法，执行ActivityOptions.makeSceneTransitionAnimation方法

	/**
         * 点击按钮,实现Activity的跳转操作
         * 通过Android5.0及以上代码的方式实现activity的跳转动画
         */
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreeActivity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
            }
        });



这里我们调用了startActivity的重载方法：

	public void startActivity(Intent intent, @Nullable Bundle options)

并且我们传入了ActivityOptions.makeSceneTransitionAnimation，该方法表示将Activity a平滑的切换到Activity b，其还有几个重载方法可以指定相关的View，即以View为焦点平滑的从Activity a切换到Activity b。