package com.example.demomore.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferencesUtil
 * author ： zbc
 * createData： 2017/2/23
 */
public class SharedPreferencesUtil {
    private static SharedPreferencesUtil instance;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    /**
     *
     * @param prefencePath 文件的存储名称
     * @param context
     * @return
     */
    public static SharedPreferencesUtil getInstance(String prefencePath,Context context) {
        if (instance == null && context != null) {
            instance = new SharedPreferencesUtil(prefencePath,context);
        }
        return instance;
    }

    private SharedPreferencesUtil(String prefencePath,Context context) {
        sp = context.getSharedPreferences(prefencePath, Context.MODE_PRIVATE);
        editor = sp.edit();
    }

    public long getLongValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getLong(key, 0);
        }
        return 0;
    }

    public String getStringValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, null);
        }
        return null;
    }

    /**
     * 指定默认值
     * @param key
     * @param defaultUrl
     * @return
     */
    public String getStringValue(String key,String defaultUrl) {
        if (key != null && !key.equals("")) {
            return sp.getString(key, defaultUrl);
        }
        return null;
    }

    public int getIntValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }

    public int getIntValueByDefault(String key)
    {
        if (key != null && !key.equals("")) {
            return sp.getInt(key, 0);
        }
        return 0;
    }
    public boolean getBooleanValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getBoolean(key, false);
        }
        return true;
    }

    public float getFloatValue(String key) {
        if (key != null && !key.equals("")) {
            return sp.getFloat(key, 0);
        }
        return 0;
    }

    public void putStringValue(String key, String value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putString(key, value);
            editor.commit();
        }
    }

    public void putIntValue(String key, int value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putInt(key, value);
            editor.commit();
        }
    }

    public void putBooleanValue(String key, boolean value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putBoolean(key, value);
            editor.commit();
        }
    }

    public void putLongValue(String key, long value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putLong(key, value);
            editor.commit();
        }
    }

    public void putFloatValue(String key, Float value) {
        if (key != null && !key.equals("")) {
            editor = sp.edit();
            editor.putFloat(key, value);
            editor.commit();
        }
    }
}
