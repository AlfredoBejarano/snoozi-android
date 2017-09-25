package com.alfredobejarano.elgordo.home.view;

import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import com.alfredobejarano.elgordo.R;
import com.alfredobejarano.elgordo.adddog.view.AddDogActivity;
import com.alfredobejarano.elgordo.base.view.BaseActivity;
import com.alfredobejarano.elgordo.home.model.DogListResponse;
import com.alfredobejarano.elgordo.home.presenter.HomePresenter;
import com.alfredobejarano.elgordo.home.presenter.adapter.DogListAdapter;

import java.util.List;

import butterknife.BindView;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomeActivity extends BaseActivity<List<DogListResponse>, HomePresenter> implements SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.home_dog_list)
    RecyclerView mDogList;
    @BindView(R.id.home_dog_list_empty)
    TextView mDogListEmpty;
    @BindView(R.id.home_refresh)
    SwipeRefreshLayout mHomeRefresh;

    public static Intent provideIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    protected int getLayout() {
        mMenu = R.menu.home_menu;
        attachPresenter(new HomePresenter(this));
        return R.layout.activity_main;
    }

    @Override
    public void setup(List<DogListResponse> data) {
        mHomeRefresh.setOnRefreshListener(this);
        changeViewVisibility(mHomeRefresh, VISIBLE);
        if (data.isEmpty()) {
            changeViewVisibility(mDogListEmpty, VISIBLE);
        } else {
            refreshList(data);
        }
        mHomeRefresh.setRefreshing(false);
        changeLoadingVisibility(GONE);
    }

    private void refreshList(List<DogListResponse> data) {
        mDogList.invalidate();
        mDogList.setLayoutManager(new LinearLayoutManager(this));
        mDogList.setAdapter(new DogListAdapter(data));
        changeViewVisibility(mDogList, VISIBLE);
    }

    @Override
    public void onRefresh() {
        mHomeRefresh.setRefreshing(true);
        changeViewVisibility(mHomeRefresh, GONE);
        changeLoadingVisibility(VISIBLE);
        mPresenter.refreshDogs();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_add_dog) {
            startActivity(AddDogActivity.provideIntent(this));
        }
        return super.onOptionsItemSelected(item);
    }
}
