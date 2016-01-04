package lico.example.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.squareup.leakcanary.LeakCanary;


/**
 * Created by zzk on 15/12/7.
 */
public class EApplication extends Application{
    private static EApplication instance = null;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this) ;
        LeakCanary.install(this);
    }

    public static EApplication getInstance(){
        if(instance == null){
            synchronized (EApplication.class){
                if(instance == null){
                    instance = new EApplication();
                }
            }
        }
        return instance;
    }
}
