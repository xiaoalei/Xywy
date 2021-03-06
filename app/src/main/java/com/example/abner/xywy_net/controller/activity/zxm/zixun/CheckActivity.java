package com.example.abner.xywy_net.controller.activity.zxm.zixun;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.abner.xywy_net.R;
import com.example.abner.xywy_net.base.BaseActivity;
import com.example.abner.xywy_net.bean.CommonBean;
import com.example.abner.xywy_net.bean.CommonNetBean;
import com.example.abner.xywy_net.utils.netutils.MyCallBack;
import com.example.abner.xywy_net.utils.netutils.OkHttpUtils;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 张萌 on 2017/6/11.
 */

public class CheckActivity   extends BaseActivity{
    String  url="http://api.wws.xywy.com/index.php?act=zixun&fun=getHealthPlazeList&version=version2&tag=zj&sign=2e0d0887581be1c35794ee4c13b00cae&typeid=18032&dir=zhuanti_nk";
    private OkHttpUtils okHttpUtils;
    List<CommonBean> mlist;
    ListAdapter adapter;
    ListView listView;
    @Override
    protected int layoutId() {
        return R.layout.activity_check;
    }

    @Override
    protected void initView() {
        listView= (ListView) findViewById(R.id.common_listview555);
    }

    @Override
    protected void initData() {
        mlist=new ArrayList<>();
        okHttpUtils=new OkHttpUtils();
    }

    @Override
    protected void loadData() {
        okHttpUtils.getInstance().get(url, null, new MyCallBack() {
            @Override
            public void OnSuccess(String result) {
                Log.i("--commons",result);
                Gson gson=new Gson();
                CommonNetBean commonNetBean = gson.fromJson(result, CommonNetBean.class);
                List<CommonNetBean.DataBean> data = commonNetBean.getData();
                for(int i=0;i<data.size();i++){
                    String title = data.get(i).getTitle();
                    String description = data.get(i).getDescription();
                    long pubdate = Long.parseLong(data.get(i).getPubdate());
                    String date = getFormatedDateTime(pubdate*1000);
                    String id = data.get(i).getId();
                    CommonBean  commonBean=new CommonBean(title,date,description,id);
                    mlist.add(commonBean);
                    adapter=new ListAdapter(mlist,CheckActivity.this);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void OnError(String errorMsg) {

            }
        });
    }
    public static String getFormatedDateTime(long dateTime) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return sDateFormat.format(new Date(dateTime + 0));
    }
    @Override
    protected void initListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CheckActivity.this,DetailCheck.class);
                intent.putExtra("userid",mlist.get(position).getUserid());
                intent.putExtra("title",mlist.get(position).getTitle());
                intent.putExtra("date",mlist.get(position).getDate());
                intent.putExtra("body",mlist.get(position).getBody());
                startActivity(intent);
            }
        });
    }



    class   ListAdapter  extends BaseAdapter {
        List<CommonBean> list;
        Context context;

        public ListAdapter(List<CommonBean> list, Context context) {
            this.list = list;
            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                holder = new Holder();
                convertView = LayoutInflater.from(context).inflate(R.layout.common_item, null);
                holder.title = (TextView) convertView.findViewById(R.id.common_title);
                holder.body = (TextView) convertView.findViewById(R.id.common_body);
                holder.date = (TextView) convertView.findViewById(R.id.common_date);
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }
            CommonBean commonBean = list.get(position);
            holder.title.setText(commonBean.getTitle());
            holder.date.setText(commonBean.getDate());
            holder.body.setText(commonBean.getBody());

            return convertView;
        }

        class Holder {
            TextView title, date, body;
        }



    }




}
