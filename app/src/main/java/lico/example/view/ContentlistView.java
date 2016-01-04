package lico.example.view;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import lico.example.R;
import lico.example.adapter.BaseAdapterHelper;
import lico.example.adapter.SimpleRecyclerAdapter;
import lico.example.bean.JcodeInfo;
import lico.example.views.PullLoadMoreRecyclerView;

/**
 * Created by zzk on 15/12/22.
 */
public class ContentListView extends ViewImpl {

    @Bind(R.id.pullloadmoreview)
    PullLoadMoreRecyclerView pullloadmoreview;

    private List<JcodeInfo> mList = new ArrayList<>();
    private SimpleRecyclerAdapter mAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_content_list;
    }

    public void initViews(Context context, PullLoadMoreRecyclerView.PullLoadMoreListener listener) {
        pullloadmoreview.setRefreshing(true);
        pullloadmoreview.setLinearLayout();
        pullloadmoreview.setOnPullLoadMoreListener(listener);


        mAdapter = new SimpleRecyclerAdapter<JcodeInfo>(context, mList, R.layout.view_content_list) {

            @Override
            protected void convert(BaseAdapterHelper helper, JcodeInfo item) {
                helper.setText(R.id.content_text, item.title);
                helper.setImageByUrl(R.id.user_avator, item.imageUrl);
            }
        };
        pullloadmoreview.setAdapter(mAdapter);
    }

    public void refreshListData(List<JcodeInfo> list) {
        mAdapter.getDataList().clear();
        mAdapter.getDataList().addAll(list);
        mAdapter.notifyDataSetChanged();
        pullloadmoreview.setPullLoadMoreCompleted();
        Log.e("x", "x getDataList : " + mAdapter.getDataList().size());
    }

    public void addListData(List<JcodeInfo> list) {
        mAdapter.getDataList().addAll(list);
        mAdapter.notifyDataSetChanged();
        pullloadmoreview.setPullLoadMoreCompleted();
    }

    @Override
    public void destroy() {

    }
}
