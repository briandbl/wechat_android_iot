package pigkeytest.ubtech.com.wechatiot;

import android.app.Application;
import android.util.Log;

/**
 * @作者：brian.li
 * @日期: 2019/1/2 16:42
 * @描述:
 */


public class wechatApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("wechatApplication","application running ");
    }

    static {
        System.loadLibrary("stlport_shared");
        System.loadLibrary("wxcloud");
    }
}
