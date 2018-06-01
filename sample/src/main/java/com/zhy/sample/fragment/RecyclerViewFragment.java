package com.zhy.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.base.adapter.recyclerview.CommonAdapter;
import com.zhy.sample.R;
import com.zhy.sample.view.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewFragment extends Fragment
{
    private View mView;
    private RecyclerView mRecyclerView;
    private List<String> mList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        initView();
        return mView;
    }

    private void initView()
    {
        mContext = getActivity();
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.id_recyclerview);
        mList = new ArrayList<String>();
        for (int i = 0; i < 50; i++)
        {
            mList.add(i + "");
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new MyAdapter(getActivity(),mList));

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    class MyAdapter extends CommonAdapter<String>
    {
        public MyAdapter(Context context, List<String> datas)
        {
            super(context, R.layout.recyclerview_item, datas);
        }

        @Override
        public com.zhy.base.adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            com.zhy.base.adapter.ViewHolder viewHolder = super.onCreateViewHolder(parent, viewType);
            AutoUtils.auto(viewHolder.getConvertView());
            return viewHolder;
        }

        @Override
        public void convert(com.zhy.base.adapter.ViewHolder viewHolder, String s)
        {
        }
    }



}
