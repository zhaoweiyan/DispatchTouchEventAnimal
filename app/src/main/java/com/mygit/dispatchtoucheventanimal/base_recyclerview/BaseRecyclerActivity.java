package com.mygit.dispatchtoucheventanimal.base_recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.mygit.dispatchtoucheventanimal.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/7/25.
 */

public class BaseRecyclerActivity extends Activity {

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    List<String> mStrList = new ArrayList<>();
    private AdapterBaseRecyclerView mAdapter;

    /**
     * @param savedInstanceState 这些注解@Nullable和@NotNull是用来标注方法是否能传入null值，如果可以传入NUll值，则标记为nullbale，
     *                           如果不可以则标注为Nonnull. 在我们做了一些不安全严谨的编码操作的时候,这些注释会给我们一些警告。
     *                           比如说      getMyAddress(null)------
     *                           这里传参
     *                           public void getAddress(@NotNull String str){
     *                           <p>
     *                           }
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base_recycler);
        ButterKnife.bind(this);
        initRecyclerView();
    }

    private void initRecyclerView() {
        //设置RecyclerView管理器
        /*  LinearLayoutManager参数  参数
            Context context ：上下文，初始化时，构造方法内部加载资源用
            int orientation ：方向，垂直和水平，默认为垂直
            boolean reverseLayout：是否倒序，设置为True，
            从最后一个item开始，倒序加载。
            此时，RecyclerView第一个item是添加进Adapter中的最后一个，
            最后一个item是第一个加进Adapter的数据,RecyclerView会自动滑到末尾
        * */

        for (int i = 0; i < 10; i++) {
            mStrList.add("这是第" + i + "行");
        }
        //垂直布局
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        //横向布局
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //网格布局
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
//        mRecyclerView.addItemDecoration(new DividerGridViewItemDecoration(this));
        //瀑布流布局---瀑布流效果需要在适配器的onBindViewHolder方法中为我们的item设置个随机的高度
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL));

        mAdapter = new AdapterBaseRecyclerView(mStrList);
        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //设置适配器
        mRecyclerView.setAdapter(mAdapter);

        //自定义点击条目回调
        mAdapter.setOnItemClickListener(new AdapterBaseRecyclerView.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, String data) {
                Toast.makeText(BaseRecyclerActivity.this, "您点击了：  " + data, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.add_item)
    public void addItem() {
        mAdapter.addItem(2, "添加的数据" + 2);
    }

    @OnClick(R.id.delete_item)
    public void deleteItem() {
        mAdapter.deleteItem(2);
    }
}
