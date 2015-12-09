package lico.example.app;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import lico.example.http.VolleyUtil;

/**
 * Created by zzk on 15/12/7.
 */
public class EApplication extends Application{
    private static EApplication instance = null;
    private RequestQueue mRequestQueue;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        VolleyUtil.initialize(instance);
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

    public RequestQueue getVolleyRequestQueue(){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(this);
        }
        return mRequestQueue;
    }

    private static void addRequest(Request<?> request){
        getInstance().getVolleyRequestQueue().add(request);
    }

    public static void addRequest(Request<?> request, String tag){
        request.setTag(tag);
        addRequest(request);
    }

    public static void cancelAllRequests(String tag){
        if(getInstance().getVolleyRequestQueue() != null){
            getInstance().getVolleyRequestQueue().cancelAll(tag);
        }
    }
}
