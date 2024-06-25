package com.example.applicationnews.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.applicationnews.enity.NewsE;
import com.example.applicationnews.R;

import java.util.ArrayList;
import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyHolder> {
    private Context mcontext;
    private List<NewsE.ResultDTO.DataDTO> mDataDTOList = new ArrayList<>();
    public void setListData(List<NewsE.ResultDTO.DataDTO> listData){
        this.mDataDTOList = listData;
        notifyDataSetChanged();
    }

    public NewsListAdapter(Context context){
        this.mcontext = context;

    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //加载布局文件
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        //绑定数据
        NewsE.ResultDTO.DataDTO dataDTO = mDataDTOList.get(position);

        holder.author_name.setText("来源: " + dataDTO.getAuthor_name());
        holder.date.setText(dataDTO.getDate());
        holder.title.setText(dataDTO.getTitle());

        //加载图片
        Glide.with(mcontext).load(dataDTO.getThumbnail_pic_s()).error(R.mipmap.ic_launcher_daufalt_round).into(holder.thumbnail_pic_s);

        //点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null != monItemClickListener) {
                    monItemClickListener.onItemClick(dataDTO, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataDTOList.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView thumbnail_pic_s;
        TextView author_name;
        TextView date;
        TextView title;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail_pic_s = itemView.findViewById(R.id.thumbnail_pic_s);
            author_name = itemView.findViewById(R.id.author_name);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
        }
    }

    private onItemClickListener monItemClickListener;

    public onItemClickListener getMonItemClickListener() {
        return monItemClickListener;
    }

    public void setMonItemClickListener(onItemClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(NewsE.ResultDTO.DataDTO dataDTO,int position);
    }

}
