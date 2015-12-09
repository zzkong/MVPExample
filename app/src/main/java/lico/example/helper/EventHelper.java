package lico.example.helper;

import android.support.design.widget.NavigationView;
import android.view.View;

import lico.example.presenter.IPresenter;

/**
 * Created by zzk on 15/11/27.
 */
public class EventHelper {

    public static void click(IPresenter presenter, View ...views){
        if(!(presenter instanceof View.OnClickListener)) return;
        if(views == null || views.length == 0) return;
        for (View v : views) v.setOnClickListener((View.OnClickListener) presenter);
    }

    public static void setNavigationItemSelected(IPresenter presenter, View ...views){
        if(!(presenter instanceof NavigationView.OnNavigationItemSelectedListener)) return;
        if(views == null  || views.length == 0) return;
        for (View v : views) ((NavigationView)v).setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) presenter);
    }
}
