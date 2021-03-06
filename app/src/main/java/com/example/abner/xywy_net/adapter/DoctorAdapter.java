package com.example.abner.xywy_net.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.abner.xywy_net.R;
import com.example.abner.xywy_net.base.CommonAdapter;
import com.example.abner.xywy_net.base.ViewHolder;
import com.example.abner.xywy_net.controller.activity.YuyueZhuanjiaActivity;
import com.example.abner.xywy_net.entity.DcBean;

import java.util.List;

/**
 * Created by think on 2017/6/10.
 */

public class DoctorAdapter extends CommonAdapter<DcBean.DataBean> {

    private List<DcBean.DataBean> datas;
    private Context context;
    public DoctorAdapter(Context context, List<DcBean.DataBean> datas) {
        super(context, datas, R.layout.listview_item_doctor);
        this.datas = datas;
        this.context = context;
    }

    @Override
    public void display(ViewHolder holder, DcBean.DataBean dataBean) {
        View convertView = holder.getConvertView();
        View viewById = convertView.findViewById(R.id.free_addnumber);

                viewById.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, YuyueZhuanjiaActivity.class);
                        context.startActivity(intent);
                    }
                });

        String app_image = dataBean.getApp_image();
        holder.setImage(R.id.image_dc,app_image);
        String name = dataBean.getName();
        holder.setText(R.id.name_dc,name);
        String hospital = dataBean.getHospital();
        holder.setText(R.id.address_dc,hospital);
        String title = dataBean.getTitle();
        holder.setText(R.id.work_dc,title);
        String depart = dataBean.getDepart();
        holder.setText(R.id.jineng_dc,depart);
        String teach = dataBean.getTeach();
        holder.setText(R.id.zhiwei_dc,teach);
        String goodat = dataBean.getGoodat();
        holder.setText(R.id.text_dc,goodat);

        SharedPreferences sp = context.getSharedPreferences("dc_data",Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("app_image",app_image);
        edit.putString("name",name);
        edit.putString("depart",depart);
        edit.putString("title",title);
        edit.putString("teach",teach);
        edit.putString("hospital",hospital);
        edit.putString("goodat",goodat);
        edit.commit();



    }
}
