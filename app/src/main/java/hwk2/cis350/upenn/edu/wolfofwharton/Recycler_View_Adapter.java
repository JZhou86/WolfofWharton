package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import java.util.Collections;
import java.util.List;


public class Recycler_View_Adapter extends RecyclerView.Adapter<View_Holder> {
    List<Data> list = Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List<Data> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        holder.name.setText(list.get(position).stockName);
        holder.name.setTextColor(Color.parseColor("#ecf0f1"));
        holder.numShares.setText(list.get(position).numShares + " SHARES");
        holder.numShares.setTextColor(Color.parseColor("#ecf0f1"));
        holder.priceChange.setText(list.get(position).priceChange + "%");
        holder.priceChange.setTextColor(Color.parseColor("#ecf0f1"));

        animate(holder);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // Insert a new item to the RecyclerView
    public void insert(int position, Data data) {
        list.add(position, data);
        notifyItemInserted(position);
    }
    // Remove a RecyclerView item containing the Data object
    public void remove(Data data) {
        int position = list.indexOf(data);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }
}