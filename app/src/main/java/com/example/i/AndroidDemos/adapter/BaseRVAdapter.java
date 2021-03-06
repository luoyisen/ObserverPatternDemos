package com.example.i.AndroidDemos.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.i.AndroidDemos.R;

import java.util.ArrayList;

/***
 * Created by I on 2017/8/23.
 */

public class BaseRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    /**
     * 根据activityname为不同的activity设置不同的
     */
    OnItemClickListener onItemClickListener;
    ArrayList<String> mData;
    private String activityname;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public BaseRVAdapter(ArrayList<String> mData, String activityname) {
        this.activityname = activityname;
        this.mData = mData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /**
         *  Returns the ordinal of this enumeration constant (its position
         *  in its enum declaration, where the initial constant is assigned
         *  an ordinal of zero).
         */
        ArrayList<String> activityList;
        activityList = new ArrayList<>();
        activityList.add("ActivityComponentIntereact");
        activityList.add("ActivityNet");
        if (activityname.equals(activityList.get(0))) {
            if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
                return new viewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_type1, parent, false));
            } else {
                return new viewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_type2, parent, false));
            }
        } else if (activityname.equals(activityList.get(1))) {
            if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
                return new viewHolder2(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_type2, parent, false));
            } else {
                return new viewHolder1(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base_type1, parent, false));
            }
        } else {
            return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_base, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof viewHolder1) {
            ((viewHolder1) holder).textView.setText(mData.get(position));
            /**
             * 这里的item里面如果有button，如果要为整个itemView设置点击事件，就要：
             * 3dpager1.将button设置为不可点击，否则事件不会传递到itemview，或者：
             * 2.自定义button，重写ontouchevent()方法，使得其返回值为false，表示button不处理事件，由button的上级viewgroup来处理，或者：
             * 3.自定义relativelayout，重写dispatchtouchevent()方法，时期返回值为false，表示不再分发事件
             * 如果要同时为button和item设置点击事件，就不用
             */
        } else if (holder instanceof viewHolder2) {
            // TODO: 2017/8/23 当textview要显示的文字太多，而在xml里面指定的textsize因为过大而显示不完，怎样动态调节字体大小以适应item，既不感觉字体小，也不会由文字显示不完全的现象
            ((viewHolder2) holder).textview.setText(mData.get(position));

        } else {
            ((viewHolder) holder).textview.setText(mData.get(position));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder.itemView, pos);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder.itemView, pos);
                }
                return true;//返回true表示事件被消费了
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        return position % 2 == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        TextView textview;

        public viewHolder(View itemView) {
            super(itemView);
            textview = (TextView) itemView.findViewById(R.id.text_info);
        }
    }

    public static class viewHolder1 extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView show_keytech;

        public viewHolder1(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_show);
            show_keytech = (ImageView) itemView.findViewById(R.id.faceimage);
        }
    }

    public static class viewHolder2 extends RecyclerView.ViewHolder {
        ImageView imageview, show_keytech;
        TextView textview;

        public viewHolder2(View itemView) {
            super(itemView);
            imageview = (ImageView) itemView.findViewById(R.id.faceimage);
            show_keytech = (ImageView) itemView.findViewById(R.id.show_keytech);
            textview = (TextView) itemView.findViewById(R.id.phonenumber);
        }
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int pisition);
    }
}
