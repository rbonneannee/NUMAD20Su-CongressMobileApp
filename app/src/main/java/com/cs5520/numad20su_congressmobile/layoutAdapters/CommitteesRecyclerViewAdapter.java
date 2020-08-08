package com.cs5520.numad20su_congressmobile.layoutAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.cs5520.numad20su_congressmobile.R;
import com.cs5520.numad20su_congressmobile.content.models.Committee;
import com.cs5520.numad20su_congressmobile.controllers.CommitteeDetailsActivity;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface.TYPE;
import java.util.List;


public class CommitteesRecyclerViewAdapter extends
    RecyclerView.Adapter<CommitteesRecyclerViewAdapter.ViewHolder> {

    private final List<Committee> mValues;
    private final FollowInterface followInterface;
    private int lastPosition = -1;
    private Context context;

    public CommitteesRecyclerViewAdapter(List<Committee> items,
        FollowInterface followInterface) {
        mValues = items;
        this.followInterface = followInterface;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Committee committee = new Committee(mValues.get(position));
        holder.mItem = committee;
        holder.mIdView.setText(committee.id);
        holder.mContentView.setText(committee.name);
        holder.isFollowing = (
            this.followInterface.following(TYPE.Committee).contains(committee.id));
        holder.followIcon
            .setImageResource(
                (holder.isFollowing) ? R.drawable.heart_closed : R.drawable.heart_open);
        Animation animation = AnimationUtils.loadAnimation(this.context,
            (position > lastPosition) ? R.anim.slide_right_anim : R.anim.load_up_anim);
        holder.itemView.startAnimation(animation);
        lastPosition = position;
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull CommitteesRecyclerViewAdapter.ViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(this.context)
            .inflate(R.layout.card_layout, parent, false);
        return new ViewHolder(view, context, followInterface);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mIdView;
        public final TextView mContentView;
        public final ImageView followIcon;
        private final FollowInterface followInterface;
        private final Context context;
        public Committee mItem;
        public boolean isFollowing;

        public ViewHolder(View view,
            Context context,
            FollowInterface followInterface) {
            super(view);
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
            followIcon = view.findViewById(R.id.follow_icon);

            this.context = context;
            this.followInterface = followInterface;

            followIcon.setOnClickListener(this);
            super.itemView.setOnClickListener(this);
        }

        @NonNull
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.card_view:
                    context.startActivity(
                        new Intent(
                            context, CommitteeDetailsActivity.class)
                            .putExtra("committee", mItem));
                    break;
                case R.id.follow_icon:
                    if (isFollowing) {
                        followInterface.unfollow(TYPE.Committee, mItem.id);
                        followIcon.setImageResource(R.drawable.heart_open);
                    } else {
                        followInterface.follow(TYPE.Committee, mItem.id);
                        followIcon.setImageResource(R.drawable.heart_closed);
                    }
                    isFollowing = !isFollowing;
                    break;
            }
        }
    }
}