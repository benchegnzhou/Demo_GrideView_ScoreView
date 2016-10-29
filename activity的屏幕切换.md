# Activity的屏幕切换和生命周期 #

> **onConfigurationChanged()回调方法不走的原因;**

> * 在定义Activity的时候可以指定对应的屏幕方向，但是在这种情况下后会不走onConfigurationChanged()方法
 

例如： 

	<activity
            android:name=".wxapi.WXEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:screenOrientation="portrait"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />

* android:screenOrientation="portrait"是直接的将屏幕的方向定死为竖向，在Activity的方法中是不走onConfigurationChanged()方法的
* android:configChanges="keyboardHidden|orientation|screenSize"一次解释为键盘隐藏，方向改变，screenSize在2.3之后加上这个屏幕切换不会重走各个生命周期，默认不进行设置的话，手机的横竖屏切换会使当前的Activity重新构造，onPause ->onStop->onDestory-> onCreate ->onStart ->onResume.



* 明白了这个我们就把 android:screenOrientation="portrait"属性去掉
 
	
		<activity android:name=".UiActivity.ConfigurationChangeActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
             /> 

代码中直接的监听

	 @Override
    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);
        LogUtil.e("横竖屏切换 onConfigurationChanged", "onConfigurationChanged");
        LogUtil.e("横竖屏切换 onConfigurationChanged", ""+newConfig.orientation);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tvActivityNew.setText("当前为横屏");
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            tvActivityNew.setText("当前为竖屏");
        }
    }




如果不想这样的话，有两种方式：

 1. 直接设置屏幕为横屏或者竖屏    
 
            android:screenOrientation="landscape"
            android:screenOrientation="portrait"
这时也可以手动切换

		  @Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	            case R.id.btn_activity_change:
	                /**
	                 * 只有代码设置方向或者已经制定方向以后才能够获取到屏幕的方向，否则是-1，表示没有写死
	                 *  android:screenOrientation="portrait"
	                 */
	                int oritentation = getRequestedOrientation();
	                if (oritentation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
	                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	                } else if (oritentation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
	                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
	                }
	                break;
       	 	}
    	}




 2. 通过设置 android:configChanges="orientation|keyboardHidden“
        但是需要注意的是 ：在Android3.2  API13以及以上系统中，需要再添加一个属性screenSize才行，orientation|keyboardHidden |screenSize,这样才能Activity不重新创建.


