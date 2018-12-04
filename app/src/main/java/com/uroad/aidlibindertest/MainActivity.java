package com.uroad.aidlibindertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.uroad.aidl.RemoteSDK;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    private static final String REMOT_SERVICE_ACTION = "android.intent.action.MyFirstService";

//    private ITestService testService;
//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            testService = ITestService.Stub.asInterface(service);
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        final Intent intent = new Intent(REMOT_SERVICE_ACTION);
//        intent.setPackage(this.getPackageName());
//        bindService(intent, connection, BIND_AUTO_CREATE);


    }

//
//    @OnClick(R.id.but)
//    void onClick() {
//        try {
//            testService.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    @Override
    protected void onResume() {
        super.onResume();
        RemoteSDK.getInstance().bindRemoteService(getApplicationContext(), new RemoteSDK.Call() {
            @Override
            public void status(int state) {
                if (state == success){
                    RemoteSDK.getInstance().getMsg(getApplicationContext(), new RemoteSDK.Callback() {
                        @Override
                        public void Success(String str) {
                            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void Failed(String str) {
                            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(connection);
        RemoteSDK.getInstance().unbindRemoteService(getApplicationContext());
    }
}
