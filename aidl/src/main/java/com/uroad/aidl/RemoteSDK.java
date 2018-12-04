package com.uroad.aidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;

import com.uroad.aidlibindertest.ITestService;

/**
 * <p>文件描述：<p>
 * <p>作者：zph<p>
 * <p>创建时间：2018/12/4<p>
 */
public class RemoteSDK {

    private ITestService testService;
    private ServiceConnection connection;
    private static final RemoteSDK sdk = new RemoteSDK();

    public static RemoteSDK getInstance(){
        return sdk;
    }

    /*通过IBinder开放出接口*/
    public void bindRemoteService(Context context, final Call call){
        Intent intent = new Intent(context,RemoteService.class);
        intent.setAction(RemoteService.SERVICE);
        intent.setPackage("com.uroad.aidlibindertest");
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                testService = ITestService.Stub.asInterface(service);
                call.status(Call.success);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
        context.bindService(intent, connection,context.BIND_AUTO_CREATE);

    }


    /*释放资源*/
    public void unbindRemoteService(Context context){
        if(connection != null){
            context.unbindService(connection);
        }
    }

    /*提供被调用的接口*/
    public void getMsg(Context context, Callback callback){
        if (testService != null){
            try {
                testService.start();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            callback.Success("成功");
        }else {
            callback.Failed("失败");
        }
    }

    public interface Callback{
        void Success(String str);
        void Failed(String str);

    }

    public interface Call{
        int success  = 0x001;
        int failed  = 0x002;
        void status(int state);

    }
}
