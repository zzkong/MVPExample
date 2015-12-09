package lico.example.listener;

import lico.example.bean.ResponseImagesListEntity;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;
import rx.Observable;


/**
 * Created by zzk on 15/12/7.
 */
public interface HttpApi {

    @GET("/data/imgs")
    Observable<ResponseImagesListEntity> getImagess(@Query("col") String col,@Query("tag")String tag, @Query("pn") int pn, @Query("rn") int rn, @Query("from") int from);
}
