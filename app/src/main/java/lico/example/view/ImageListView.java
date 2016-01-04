package lico.example.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import lico.example.R;
import lico.example.adapter.BaseAdapterHelper;
import lico.example.adapter.BaseQuickAdapter;
import lico.example.adapter.SimpleRecyclerAdapter;
import lico.example.bean.ImagesListEntity;
import lico.example.ui.SpaceImageDetailActivity;
import lico.example.views.PLAImageView;
import lico.example.views.SpacesItemDecoration;

/**
 * Created by zzk on 15/12/7.
 */
public class ImageListView extends ViewImpl {
    @Bind(R.id.xrecyclerView)
    XRecyclerView xrecyclerView;

    private SimpleRecyclerAdapter mAdapter;
    private List<ImagesListEntity> mList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_image_list;
    }

    public void initViews(Context context, XRecyclerView.LoadingListener listener){
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        xrecyclerView.setLayoutManager(layoutManager);
        xrecyclerView.setLoadingListener(listener);
        xrecyclerView.setRefreshProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.setLaodingMoreProgressStyle(ProgressStyle.BallRotate);
        xrecyclerView.addItemDecoration(new SpacesItemDecoration(5));
        //xrecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);
        mAdapter = new SimpleRecyclerAdapter<ImagesListEntity>(context, mList, R.layout.view_image_list_item) {

            @Override
            protected void convert(BaseAdapterHelper helper, ImagesListEntity item) {
                int width = item.thumbnailWidth;
                int height = item.thumbnailHeight;
                PLAImageView plaImageView = (PLAImageView) helper.getImageView(R.id.item_image);
                plaImageView.setImageHeight(height);
                plaImageView.setImageWidth(width);
           //     helper.setImage(R.id.item_image, item.thumbnailUrl);
                helper.setImageByUrl(R.id.item_image, item.thumbnailUrl);
            }
        };

        mAdapter.setOnInViewClickListener(R.id.root_layout, new BaseQuickAdapter.onInternalClickListenerImpl<ImagesListEntity>(){
            @Override
            public void OnClickListener(View parentV, View v, Integer position, ImagesListEntity values) {
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                int width = v.getWidth();
                int height = v.getHeight();
                gotoDetail((Activity) context, position, values, location[0], location[1], width, height);
            }
        });

        xrecyclerView.setAdapter(mAdapter);
    }

    public void refreshListData(List<ImagesListEntity> imageEntity) {
        mAdapter.getDataList().clear();
        mAdapter.getDataList().addAll(imageEntity);
        mAdapter.notifyDataSetChanged();
        xrecyclerView.refreshComplete();
        xrecyclerView.scrollToPosition(0);
    }

    public void addListData(List<ImagesListEntity> imageEntity){
        xrecyclerView.loadMoreComplete();
        mAdapter.getDataList().addAll(imageEntity);
        //用notifyDataSetChanged();会出现花屏问题
        mAdapter.notifyItemRangeChanged(mAdapter.getDataList().size() - 10, 10);
        xrecyclerView.refreshComplete();
    }

    private void gotoDetail(Activity activity, int position, ImagesListEntity imagesListEntity, int x, int y, int width, int height){
        Intent intent = new Intent(activity, SpaceImageDetailActivity.class);
        intent.putExtra("images", imagesListEntity.imageUrl);
        intent.putExtra("locationX", x);
        intent.putExtra("locationY", y);
        intent.putExtra("width", width);
        intent.putExtra("height", height);
        activity.startActivity(intent);
        activity.overridePendingTransition(0, 0);
    }

    @Override
    public void destroy() {

    }
}
