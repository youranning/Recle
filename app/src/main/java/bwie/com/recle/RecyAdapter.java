package bwie.com.recle;

import android.support.v7.widget.RecyclerView;

/**
 * Created by $USER_NAME on 2017/5/23.
 */


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by muhanxi on 17/5/23.
 */

public class RecyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context mContext;
    private List<Bean.ListBean> list;
    private LayoutInflater inflater;


    public RecyAdapter(Context context, List<Bean.ListBean> list) {

        this.mContext = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == 0) {


            View view = inflater.inflate(R.layout.first, parent, false);

            FirstViewHolder viewHolder = new FirstViewHolder(view);

            return viewHolder;

        } else  {
            View view = inflater.inflate(R.layout.two, parent, false);

            TwoViewHolder viewHolder = new TwoViewHolder(view);

            return viewHolder;
        }

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof FirstViewHolder) {

            FirstViewHolder viewHolder = (FirstViewHolder) holder;

            viewHolder.textView.setText(list.get(position).getTitle());
            x.image().bind(viewHolder.imageView,list.get(position).getPic());
//            viewHolder.imageView
        }


        if (holder instanceof TwoViewHolder) {
            TwoViewHolder viewHolder = (TwoViewHolder) holder;
            viewHolder.textView.setText(list.get(position).getTitle());
            String[] arr = list.get(position).getPic().split("\\|");
            System.out.println(arr[0]);
            x.image().bind(viewHolder.iv1,arr[0]);
            x.image().bind(viewHolder.iv2,arr[1]);

        }


    }


    @Override
    public int getItemViewType(int position) {

        if (list.get(position).getType() == 1) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }


    class FirstViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ImageView imageView;

        public FirstViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.first_tv);
            imageView = (ImageView) itemView.findViewById(R.id.first_iv);
        }
    }

    class TwoViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView iv1;
        ImageView iv2;

        public TwoViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.two_tv);
            iv1 = (ImageView) itemView.findViewById(R.id.two_iv1);
            iv2 = (ImageView) itemView.findViewById(R.id.two_iv2);
        }
    }


    interface OnItemClickListener {

        void onItemClickListener(int position, View view);

        void onItemLongClickListener(int position, View view);
    }


    public OnItemClickListener listener;


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


}
