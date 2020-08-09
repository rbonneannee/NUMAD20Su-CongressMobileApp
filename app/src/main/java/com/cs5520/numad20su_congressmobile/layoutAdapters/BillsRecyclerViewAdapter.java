package com.cs5520.numad20su_congressmobile.layoutAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Bill;
import com.cs5520.numad20su_congressmobile.controllers.BillDetailsActivity;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface.TYPE;
import java.util.List;

// TODO Get a list of followed bills so that the follow_icon for each can be drawn appropriately
public class BillsRecyclerViewAdapter
    extends RecyclerView.Adapter<BillsRecyclerViewAdapter.ViewHolder>
    implements Filterable {

    private final List<Bill> items;

    private final FollowInterface followInterface;
    private int lastPosition = -1;
    private Context context;

    public BillsRecyclerViewAdapter(List<Bill> items,
        FollowInterface followInterface) {
        this.items = items;
        this.followInterface = followInterface;
    }

    public void add(Bill bill) {
        this.items.add(bill);
        this.notifyItemInserted(this.items.size() - 1);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(this.context)
            .inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view, this.context, this.followInterface);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Bill bill = new Bill(items.get(position));
        holder.bill = bill;
        holder.idView.setText(bill.bill_id);
        holder.contentView.setText(bill.title);
        holder.isFollowing = (this.followInterface.following(TYPE.Bill).contains(bill.bill_id));
        holder.followIcon
            .setImageResource(
                (holder.isFollowing) ? R.drawable.heart_closed : R.drawable.heart_open);

        Animation animation = AnimationUtils.loadAnimation(this.context,
            (position > lastPosition) ? R.anim.slide_right_anim : R.anim.load_up_anim);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView idView;
        public final TextView contentView;
        public final ImageView followIcon;
        private final Context context;
        private final FollowInterface followInterface;
        public Bill bill;
        public Boolean isFollowing;

        public ViewHolder(View view,
            Context context,
            FollowInterface followInterface) {

            super(view);
            idView = view.findViewById(R.id.item_number);
            contentView = view.findViewById(R.id.content);
            followIcon = view.findViewById(R.id.follow_icon);

            this.context = context;
            this.followInterface = followInterface;

            followIcon.setOnClickListener(this);
            super.itemView.setOnClickListener(this);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }


        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.card_view:
                    context.startActivity(
                        new Intent(
                            context, BillDetailsActivity.class)
                            .putExtra("bill", bill));
                    break;
                case R.id.follow_icon:
                    if (isFollowing) {
                        followInterface.unfollow(FollowInterface.TYPE.Bill, bill.bill_id);
                        followIcon.setImageResource(R.drawable.heart_open);
                    } else {
                        followInterface.follow(FollowInterface.TYPE.Bill, bill.bill_id);
                        followIcon.setImageResource(R.drawable.heart_closed);
                    }
                    isFollowing = !isFollowing;
                    break;
            }
        }
    }
}
