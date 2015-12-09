package lico.example.ui;

import android.view.MenuItem;

import lico.example.presenter.ActivityPresenter;
import lico.example.view.PersonalView;

/**
 * Created by zzk on 15/11/28.
 */
public class PersonalActivity extends ActivityPresenter<PersonalView>{

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
