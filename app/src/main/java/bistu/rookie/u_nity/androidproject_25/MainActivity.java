package bistu.rookie.u_nity.androidproject_25;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ServiceLog";
    MyService myService = null;
    ServiceConnection serviceConnection = null;
    Boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.i(TAG,"Service Connect");
                myService = ((MyService.LocalBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.i(TAG,"Service Disconnect");
            }
        } ;

        Button startButton = (Button)findViewById(R.id.StartButton);
        Button endButton = (Button)findViewById(R.id.EndButton);
        Button useButton = (Button)findViewById(R.id.UseButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MyService.class);
                bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
                isBound = true;
            }
        });

        endButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isBound) {
                    unbindService(serviceConnection);
                    isBound = false;
                }
            }
        });

        useButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int numA = 2;
                int numB = 8;
                if(myService != null){
                    Log.i(TAG, "numA = " + numA + "  numB = " + numB);
                    Log.i(TAG, "Using Service: Add\nResult = " + myService.add(numA, numB));
                }

            }
        });
    }

}
