package lico.example.views;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import lico.example.R;

/**
 * Created by zzk on 15/12/30.
 */
public class SuperRecyclerView extends LinearLayout{

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private PullLoadMoreListener mLoadMoreListener;
    private boolean isRefresh = false;
    private Context mContext;

    public SuperRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public SuperRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mContext = context;
        View view = LayoutInflater.from(mContext).inflate(R.layout.view_pull_load_more, null);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refreshlayout);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_blue_dark, android.R.color.holo_orange_dark);
    //    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayoutOnRefresh(this));

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
      //  mRecyclerView.addOnScrollListener(new RecyclerViewOnScroll(this));

        mRecyclerView.setOnTouchListener(
                new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (isRefresh) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        );
        this.addView(view);
    }

    public void refresh(){
        if(mLoadMoreListener != null){
            mLoadMoreListener.onRefresh();
        }
    }

    public void loadMore() {
        if (mLoadMoreListener != null) {
            mLoadMoreListener.onLoadMore();

        }
    }

    public interface PullLoadMoreListener {
        public void onRefresh();

        public void onLoadMore();
    }
}
