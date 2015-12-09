package lico.example.listener;


import lico.example.bean.HttpResponseEntity;

/**
 * Created by zwl on 15/7/27.
 */
public interface JSONParserCompleteListener {
    public void ParserCompleteListener(HttpResponseEntity response, Object object);
}
