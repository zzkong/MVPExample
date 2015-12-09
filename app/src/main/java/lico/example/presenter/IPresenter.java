package lico.example.presenter;

/**
 * Created by zzk on 15/11/27.
 */
public interface IPresenter<T> {
    Class<T> getViewClass();
}
