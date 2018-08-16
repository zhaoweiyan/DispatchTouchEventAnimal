package com.mygit.dispatchtoucheventanimal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.mygit.dispatchtoucheventanimal.base_recyclerview.BaseRecyclerActivity;
import com.mygit.dispatchtoucheventanimal.refresh_recyclerview.RefreshRecyclerActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.tv_base)
    TextView tv_base;
    @Bind(R.id.tv_update)
    TextView tv_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_base)
    public void gotoBaseRecyclerView() {
        Intent intent = new Intent(this, BaseRecyclerActivity.class);
        startActivity(intent);

    }

    @OnClick(R.id.tv_update)
    public void gotoRecyclerViewRefresh() {
        Intent intent = new Intent(this, RefreshRecyclerActivity.class);
        startActivity(intent);
    }

}
