package com.zhy.sample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.zhy.autolayout.utils.AutoUtils;
import com.zhy.sample.R;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment
{
    private View mView;
    private ListView mlistview;
    private List<String> mList;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        mView = inflater.inflate(R.layout.fragment_list, container, false);
        initView();
        return mView;
    }

    private void initView()
    {
        mContext = getActivity();
        mlistview = (ListView) mView.findViewById(R.id.id_listview);
        mList = new ArrayList<String>();
        for (int i = 0; i < 50; i++)
        {
            mList.add(i + "");
        }
        mlistview.setAdapter(new MyAdapter());
    }

    class MyAdapter extends BaseAdapter
    {

        @Override
        public int getCount()
        {
            return mList.size();
        }

        @Override
        public Object getItem(int arg0)
        {
            return mList.get(arg0);
        }

        @Override
        public long getItemId(int position)
        {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            ViewHolder holder = null;
            if (convertView == null)
            {
                holder = new ViewHolder();
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
                convertView.setTag(holder);
                //对于listview，注意添加这一行，即可在item上使用高度
                AutoUtils.autoSize(convertView);
            } else
            {
                holder = (ViewHolder) convertView.getTag();
            }

            return convertView;
        }

    }

    class ViewHolder
    {

    }

}
