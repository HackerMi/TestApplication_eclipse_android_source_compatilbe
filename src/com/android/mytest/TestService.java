package com.android.mytest;

import android.app.Service;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.os.MiuiServiceManager;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class TestService extends Service {
    private final static String TAG = "@@@@";
    private MyBinder mBinder = new MyBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "test service create");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        MiuiServiceManager.addService("ITestService",mBinder);
        Log.e(TAG, "test service onStartCommand");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "OnBind");
        return null;
    }

    private class MyBinder extends ITestService.Stub {
        @Override
        public String getName(int number) {
            Log.e(TAG, "getName, number: " + number);
            return new String("test getName");
        }

        @Override
        public String getPid(boolean value){
            Log.e(TAG, "getPid, value: " + value);
            return new String("test getPid");
        }
        @Override
        public String testNoPara(){
            Log.e(TAG, "testNoPara ");
            return new String("test NoPara");
        }
        @Override
        public void testNoRet(String para){
            Log.e(TAG, "testNoRet, value: " + para);
        }

        @Override
        public String getAppInfo(){
            JSONObject jsonRet = null;
            JSONArray arrayRet = new JSONArray();
            PackageManager packageManager = getApplicationContext().getPackageManager();

            List<PackageInfo> infos = packageManager.getInstalledPackages(0);

            for (PackageInfo pkgInfo : infos) {
                if ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {
                    try {
                        jsonRet = new JSONObject();
                        jsonRet.put("package_name", pkgInfo.packageName);
                        jsonRet.put("display_name",
                                packageManager.getApplicationLabel(pkgInfo.applicationInfo));
                        jsonRet.put("version_code", pkgInfo.versionCode);
                        jsonRet.put("version_name", pkgInfo.versionName);
                        jsonRet.put("install_time", pkgInfo.firstInstallTime);

                        arrayRet.put(jsonRet);
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //getAppIcon(pkgInfo.packageName);
                }
            }
            String ret = arrayRet.toString();
            Log.e("@@@@", "json str len: " + ret.length());
            return ret;
        }
        @Override
        public String getAppIcon(String pkgName) {
            JSONObject jsonRet = new JSONObject();
            try {
                PackageManager packageManager = getApplicationContext().getPackageManager();
                Drawable appIcon = packageManager.getApplicationIcon(pkgName);
                Bitmap bmp = ((BitmapDrawable) appIcon).getBitmap();
                Bitmap scaledbmp = Bitmap.createScaledBitmap(bmp, 62, 62, true);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                scaledbmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
                String encodeIconString = Base64.encodeToString(baos.toByteArray(),
                        Base64.NO_WRAP);
                jsonRet.put("app_icon", encodeIconString);
            } catch (NameNotFoundException e) {
                e.printStackTrace();
            } catch (JSONException ex) {
                ex.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return jsonRet.toString();
        }
    }
}