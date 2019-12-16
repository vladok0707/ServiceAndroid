package bistu.rookie.u_nity.androidproject_25;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private LocalBinder myBinder = new LocalBinder();

    public class LocalBinder extends Binder {

        MyService getService() {
            return MyService.this;
        }

    }

    public int add(int x,int y){
        return x+y;
    }

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }

}
