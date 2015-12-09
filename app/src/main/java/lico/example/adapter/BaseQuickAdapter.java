package lico.example.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zwl on 2015/8/25.
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper>{
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    protected int mDuration = 300;   //动画时间

    private Interpolator mInterpolator = new LinearInterpolator();  //动画插值器

    private int mLastPosition = -1;

    private boolean isFirstOnly = true;

    protected final Context mContext;

    protected final int mLayoutResId;

    protected final List<T> data;

    private boolean isShowScaleAnimate = true;

    private int upDownFactor = 1;

    protected boolean displayIndeterminateProgress = false;

    private Map<Integer, onInternalClickListener<T>> canClickItem;

    private OnItemClickListener mOnItemClickListerner = null;

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public BaseQuickAdapter(Context mContext, int mLayoutResId) {
        this(mContext, mLayoutResId, null);
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
        if(holder != null){
            addInternalClickListener(holder.itemView, position, data.get(position));
        }
        holder.itemView.setTag(position);
        T item = getItem(position);
        convert((H) holder, item);
    }

    public List<T> getDataList(){
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

    private void addInternalClickListener(final View itemV, final Integer position, final T valuesMap){
        if(canClickItem != null){
            for(Integer key : canClickItem.keySet()){
                View inView = itemV.findViewById(key);
                final onInternalClickListener<T> listener = canClickItem.get(key);
                if(inView != null && listener != null){
                    inView.setOnClickListener(new View.OnClickListener(){
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

    public void setOnInViewClickListener(Integer key, onInternalClickListener<T> onClickListener){
        if(canClickItem == null)
            canClickItem = new HashMap<Integer, onInternalClickListener<T>>();
        canClickItem.put(key, onClickListener);

    }

    public interface onInternalClickListener<T>{
        void OnClickListener(View parentV, View v, Integer position, T values);

        void OnLongClickListener(View parentV, View v, Integer position, T values);
    }

    public static class onInternalClickListenerImpl<T> implements onInternalClickListener<T>{
        @Override
        public void OnClickListener(View parentV, View v, Integer position, T values) {

        }

        @Override
        public void OnLongClickListener(View parentV, View v, Integer position, T values) {

        }
    }

    protected Animator[] getAnimators(View view) {
        if (view.getMeasuredHeight() <=0 || isShowScaleAnimate){
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1f);
            return new ObjectAnimator[]{scaleX, scaleY};
        }
        return new Animator[]{
                ObjectAnimator.ofFloat(view, "scaleX", 1.1f, 1f),
                ObjectAnimator.ofFloat(view, "scaleY", 1.1f, 1f),
                ObjectAnimator.ofFloat(view, "translationY", upDownFactor * 1.5f * view.getMeasuredHeight(), 0)
        };
    }
}
