package lico.example.http;

import lico.example.bean.HttpResponseEntity;
import lico.example.listener.JSONParserCompleteListener;
import lico.example.utils.UriHelper;

/**
 * Created by zwl on 15/7/27.
 */
public class HttpManager {

    public static final int HTTP_SUCCESS = 0;

    public static void getImages(String keywords, int pageIndex, final JSONParserCompleteListener listener){
        VolleyClient.stringGet(UriHelper.getInstance().getImagesListUrl(keywords, pageIndex), new JSONParserCompleteListener() {
            @Override
            public void ParserCompleteListener(HttpResponseEntity response, Object object) {
                if(response.responseCode == HTTP_SUCCESS){
                    DataParser.parserImageData(object.toString(), listener, response);
                }else{
                    listener.ParserCompleteListener(response, null);
                }
            }
        });
    }

}
