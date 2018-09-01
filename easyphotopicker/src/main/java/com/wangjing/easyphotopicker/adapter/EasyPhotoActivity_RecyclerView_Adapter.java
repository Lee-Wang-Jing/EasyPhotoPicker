package com.wangjing.easyphotopicker.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangjing.easyphotopicker.R;

public class EasyPhotoActivity_RecyclerView_Adapter extends RecyclerView.Adapter<EasyPhotoActivity_RecyclerView_Adapter.EasyPhotoViewHolder> {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public EasyPhotoActivity_RecyclerView_Adapter(Context mContext) {
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public EasyPhotoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        EasyPhotoViewHolder easyPhotoViewHolder = new EasyPhotoViewHolder(mLayoutInflater.inflate(R.layout.item_easyphotoactivity_recyclerview_adapter, viewGroup, false));
        return easyPhotoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EasyPhotoViewHolder easyPhotoViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class EasyPhotoViewHolder extends RecyclerView.ViewHolder {

        public EasyPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
