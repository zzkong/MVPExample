package lico.example.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import lico.example.app.EApplication;
import lico.example.bean.HttpResponseEntity;
import lico.example.listener.JSONParserCompleteListener;

/**
 * Created by zwl on 2015/8/27.
 */
public class VolleyClient {

    /**
     *
     * Get方式访问的StringRequest请求
     */
    public static void stringGet(String url, final JSONParserCompleteListener listener){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {  //响应成功
                Log.e("", "GET 成功返回：" + s);
                HttpResponseEntity response = new HttpResponseEntity(0, "访问成功");
                listener.ParserCompleteListener(response, s);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {  //响应失败
                Log.e("", "POST 失败返回：" + error);
                HttpResponseEntity response = new HttpResponseEntity(101, "出现异常错误");
                listener.ParserCompleteListener(response, null);
            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {  //添加请求头
                HashMap headers = new HashMap();
     //           headers.put("apikey", "95157b16c0d7c6bcbf362f0bdff6134f");
    //            headers.put("platform", Utility.getPhoneModelAndRelease());
    //            headers.put("version", Utility.getVersionCode(MChatApplication._context));
                return headers;
            }
        };
    //    stringRequest.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1, 1.0f)); //设置超时时间
        EApplication.addRequest(stringRequest, "");
    }




    /**
     *
     * POST方式访问的StringRequest请求
     */
    public static void stringPost(String url, final Map<String, String> map, final JSONParserCompleteListener listener){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {   //响应成功
                Log.e("", "POST 成功返回：" + s);
                HttpResponseEntity response = new HttpResponseEntity(0, "访问成功");
                listener.ParserCompleteListener(response, s);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {  //响应失败
                Log.e("", "POST 失败返回：" + error);
                HttpResponseEntity response = new HttpResponseEntity(101, "出现异常错误");
                listener.ParserCompleteListener(response, null);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {   //添加参数
                Log.e("", "打印下参数:" + map.toString());
                return map;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {     //添加头文件
                HashMap headers = new HashMap();
        //        headers.put("AuthenticationKey", LoginInfo.getInstance().AuthenticationKey);
        //        headers.put("platform", Utility.getPhoneModelAndRelease());
        //        headers.put("version", Utility.getVersionCode(MChatApplication._context));
                return headers;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5 * 1000, 1, 1.0f)); //设置超时时间
        EApplication.addRequest(stringRequest, "");
    }




    /**
     * 泛型Post
     */
    public static void jsonPost(Context context, String url, final Map<String, String> map, final Class bean, final JSONParserCompleteListener listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.e("", "" + s.toString());
                try {
                    JSONObject json = new JSONObject(s);
                    int ret = json.getInt("ret");
                    String msg = json.getString("msg");
                    if (ret == 0) {//0
                        Gson gson = new Gson();
                        Object object = gson.fromJson(json.getJSONObject("aParam").toString(), bean);
                        HttpResponseEntity response = new HttpResponseEntity(ret, msg);
                        listener.ParserCompleteListener(response, object);
                    } else {
                        HttpResponseEntity response = new HttpResponseEntity(ret, msg);
                        listener.ParserCompleteListener(response, null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                HttpResponseEntity response = new HttpResponseEntity(101, "出现异常错误");
                listener.ParserCompleteListener(response, null);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };
        EApplication.addRequest(stringRequest, "");
    }

}
