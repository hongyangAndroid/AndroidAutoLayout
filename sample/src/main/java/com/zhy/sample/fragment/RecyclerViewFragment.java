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
        mRecyclerView.setAdapter(new MyAdapter());

        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>
    {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            View convertView = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item, parent, false);
            return new ViewHolder(convertView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position)
        {

        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public int getItemCount()
        {
            return mList.size();
        }


    }

    static class ViewHolder extends RecyclerView.ViewHolder
    {

        public ViewHolder(View itemView)
        {
            super(itemView);
            //对于listview，注意添加这一行，即可在item上使用高度
            AutoUtils.autoSize(itemView);
        }
    }

}
