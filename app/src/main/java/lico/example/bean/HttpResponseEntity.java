package lico.example.bean;

import java.io.Serializable;

/**
 * Created by zwl on 15/7/27.
 */
public class HttpResponseEntity implements Serializable {
    public int responseCode;
    public String errorMsg;

    public HttpResponseEntity(int responseCode, String errorMsg) {
        this.responseCode = responseCode;
        this.errorMsg = errorMsg;
    }
}
