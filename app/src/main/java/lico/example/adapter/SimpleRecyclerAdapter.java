package lico.example.adapter;

import android.content.Context;

import java.util.List;

/**
 * Created by Administrator on 2015/8/25.
 */
public abstract class SimpleRecyclerAdapter<T> extends BaseQuickAdapter<T, BaseAdapterHelper> {


    public SimpleRecyclerAdapter(Context mContext, List<T> data, int mLayoutResId) {
        super(mContext, mLayoutResId, data);
    }


}
