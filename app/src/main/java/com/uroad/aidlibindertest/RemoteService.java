package com.uroad.aidlibindertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

/**
 * <p>文件描述：<p>
 * <p>作者：zph<p>
 * <p>创建时间：2018/11/21<p>
 */
public class RemoteService extends Service {
    private TestService mITestService;

    /*第一种方式*/
    public class TestService extends ITestService.Stub {
        public void start() {
            Log.e("LiaBin", "RemoteService start");
            Toast.makeText(getApplicationContext(),"RemoteService start",Toast.LENGTH_SHORT).show();
        }
    }

    /*第二种方式*/
    public  ITestService.Stub iTestService = new ITestService.Stub() {
        @Override
        public void start() throws RemoteException {

        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        //实例化Binder
        mITestService = new TestService();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mITestService;
    }
}

