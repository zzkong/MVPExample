package lico.example.http;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import lico.example.bean.HttpResponseEntity;
import lico.example.bean.ResponseImagesListEntity;
import lico.example.listener.JSONParserCompleteListener;

/**
 * Created by zwl on 2015/9/16.
 */
public class DataParser {

    public static void parserImageData(String string, final JSONParserCompleteListener listener,
                                       HttpResponseEntity responseEntity){
        try{
            Gson gson = new GsonBuilder().serializeNulls().create();
            ResponseImagesListEntity info = gson.fromJson(string, ResponseImagesListEntity.class);
            HttpResponseEntity response = new HttpResponseEntity(0, "访问成功");
            listener.ParserCompleteListener(response, info);
        }catch (Exception e){
            e.printStackTrace();
            HttpResponseEntity response = new HttpResponseEntity(101, "数据解析异常");
            listener.ParserCompleteListener(response, null);
        }
    }

}
