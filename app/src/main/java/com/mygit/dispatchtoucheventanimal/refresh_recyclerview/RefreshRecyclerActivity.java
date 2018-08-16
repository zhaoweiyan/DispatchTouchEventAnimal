package com.mygit.dispatchtoucheventanimal.refresh_recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.mygit.dispatchtoucheventanimal.AnimalUtil;
import com.mygit.dispatchtoucheventanimal.LogUtil;
import com.mygit.dispatchtoucheventanimal.R;
import com.mygit.dispatchtoucheventanimal.refresh_recyclerview.pulltorefresh.baseview.PullToRefreshLayout;
import com.mygit.dispatchtoucheventanimal.refresh_recyclerview.pulltorefresh.pulltorefreshview.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by admin on 2018/7/31.
 */

public class RefreshRecyclerActivity extends Activity implements PullToRefreshLayout.OnRefreshListener {
    private static final int DEFALUT_PAGE = 1;
    @Bind(R.id.refresh_recycler)
    PullToRefreshRecyclerView refresh_recycler;

    @Bind(R.id.lins_top)
    LinearLayout lins_top;
    private ArrayList<String> list;
    private AdapterRefreshRecycler adapterRefreshRecycler;
    private int currentPage = DEFALUT_PAGE;
    private int measuredHeight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_refresh_recycler);
        ButterKnife.bind(this);
        initAdapter();
        initTopLayout();

    }

    private void initTopLayout() {
        lins_top.measure(0, 0);
        measuredHeight = lins_top.getMeasuredHeight();
        lins_top.getLayoutParams().height = measuredHeight;
        lins_top.requestLayout();
    }

    private float tapDownY = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            tapDownY = ev.getY();
        }
        if (ev.getAction() == MotionEvent.ACTION_UP) {
            float tapUPY = ev.getY();
            if (tapDownY - tapUPY > 50) {
//                mTopLayout.setVisibility(View.GONE);
                //设置顶部缓慢消失
                if (lins_top.getLayoutParams().height == measuredHeight) {
                    AnimalUtil.startAnimal(lins_top, measuredHeight, 0);
                }
            } else if (tapUPY - tapDownY > 50) {
//                lins_top.setVisibility(View.VISIBLE);
                if (lins_top.getLayoutParams().height == 0 && isListViewReachTopEdge(refresh_recycler)) {
                    AnimalUtil.startAnimal(lins_top, 0, measuredHeight);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isListViewReachTopEdge(PullToRefreshRecyclerView recyclerView) {
        boolean result = false;
        if (recyclerView.getFirstVisiblePosition() == 0) {
            final View topChildView = recyclerView.getChild(0);
            LogUtil.e("topChildView.getTop()==" + topChildView.getTop());
            result = topChildView.getTop() == 0;
        }
        return result;
    }

    private void initAdapter() {
        refresh_recycler.setOnRefreshListener(this);
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("这是第" + i + "行");
        }
        //垂直布局
        adapterRefreshRecycler = new AdapterRefreshRecycler(list);
        refresh_recycler.setAdapter(adapterRefreshRecycler);
        refresh_recycler.setCanRefresh(true);
        refresh_recycler.setCanRefresh(true);
    }

    @Override
    public void onRefresh(PullToRefreshLayout pullToRefreshLayout) {
        list.clear();
        currentPage = DEFALUT_PAGE;
        for (int i = 0; i < 10; i++) {
            list.add("这是第" + i + "行");
        }
        adapterRefreshRecycler.setData(list);
        refresh_recycler.Finish();
    }

    @Override
    public void onLoadMore(PullToRefreshLayout pullToRefreshLayout) {
        currentPage = currentPage + 10;
        List<String> mList = new ArrayList<>();
        for (int i = currentPage; i < currentPage + 10; i++) {
            mList.add("这是第" + i + "行");
        }
        list.addAll(mList);
        adapterRefreshRecycler.setData(list);
        refresh_recycler.Finish();
    }

}

