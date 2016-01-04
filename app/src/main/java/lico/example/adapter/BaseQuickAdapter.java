package lico.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by zwl on 2015/8/25.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> {
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected final Context mContext;

    protected final int mLayoutResId;

    protected final List<T> data;

    private Map<Integer, onInternalClickListener<T>> canClickItem;

    public BaseQuickAdapter(Context mContext, int mLayoutResId) {
        this(mContext, mLayoutResId, null);
        setHasStableIds(true);
    }

    public BaseQuickAdapter(Context mContext, int mLayoutResId, List<T> data) {
        this.mContext = mContext;
        this.mLayoutResId = mLayoutResId;
        this.data = data;
    }

    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(mLayoutResId, parent, false);
            BaseAdapterHelper vh = new BaseAdapterHelper(view);
            return vh;
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper holder, int position) {
        if (holder != null) {
            addInternalClickListener(holder.itemView, position, data.get(position));
        }
        holder.itemView.setTag(position);
        T item = getItem(position);
        convert((H) holder, item);
    }

    public List<T> getDataList() {
        return data;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public T getItem(int position) {
        if (position >= data.size())
            return null;
        return data.get(position);
    }

    protected abstract void convert(H helper, T item);

    private void addInternalClickListener(final View itemV, final Integer position, final T valuesMap) {
        if (canClickItem != null) {
            for (Integer key : canClickItem.keySet()) {
                View inView = itemV.findViewById(key);
                final onInternalClickListener<T> listener = canClickItem.get(key);
                if (inView != null && listener != null) {
                    inView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            listener.OnClickListener(itemV, view, position, valuesMap);
                        }
                    });
                    inView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View view) {
                            listener.OnLongClickListener(itemV, view, position,
                                    valuesMap);
                            return true;
                        }
                    });
                }
            }
        }
    }

    public void setOnInViewClickListener(Integer key, onInternalClickListener<T> onClickListener) {
        if (canClickItem == null)
            canClickItem = new HashMap<Integer, onInternalClickListener<T>>();
        canClickItem.put(key, onClickListener);

    }

    public interface onInternalClickListener<T> {
        void OnClickListener(View parentV, View v, Integer position, T values);

        void OnLongClickListener(View parentV, View v, Integer position, T values);
    }

    public static class onInternalClickListenerImpl<T> implements onInternalClickListener<T> {
        @Override
        public void OnClickListener(View parentV, View v, Integer position, T values) {

        }

        @Override
        public void OnLongClickListener(View parentV, View v, Integer position, T values) {

        }
    }
}
