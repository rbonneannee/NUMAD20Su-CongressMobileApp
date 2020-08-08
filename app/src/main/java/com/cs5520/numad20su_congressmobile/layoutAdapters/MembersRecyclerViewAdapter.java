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
import com.cs5520.numad20su_congressmobile.content.models.Member;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface;
import com.cs5520.numad20su_congressmobile.controllers.FollowInterface.TYPE;
import com.cs5520.numad20su_congressmobile.controllers.MemberDetailsActivity;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Member}. TODO: Replace the implementation
 * with code for your data type.
 */
public class MembersRecyclerViewAdapter extends
    RecyclerView.Adapter<MembersRecyclerViewAdapter.ViewHolder> {

  private final List<Member> mValues;
  private int lastPosition = -1;
  private Context context;
  private FollowInterface followInterface;

  public MembersRecyclerViewAdapter(List<Member> items,
      FollowInterface followInterface) {
    mValues = items;
    this.followInterface = followInterface;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    this.context = parent.getContext();
    View view = LayoutInflater.from(this.context)
        .inflate(R.layout.card_layout, parent, false);
    return new ViewHolder(view, this.context, followInterface);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    Member member = mValues.get(position);
    holder.mItem = member;
    holder.mContentView.setText(String.format("%s %s %s", member.id, member.state, member.party));
    holder.isFollowing = (this.followInterface.following(TYPE.Member).contains(member.id));
    String text = member.short_title + " " + member.first_name + " " + member.last_name;
    holder.mIdView.setText(text);
    holder.followIcon
        .setImageResource((holder.isFollowing) ? R.drawable.heart_closed : R.drawable.heart_open);

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
  public void onViewDetachedFromWindow(@NonNull MembersRecyclerViewAdapter.ViewHolder holder) {
    super.onViewDetachedFromWindow(holder);
    holder.itemView.clearAnimation();
  }

  public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public final TextView mIdView;
    public final TextView mContentView;
    public Member mItem;
    public ImageView followIcon;
    public boolean isFollowing;

    private Context context;
    private FollowInterface followInterface;

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
              new Intent(context, MemberDetailsActivity.class)
                  .putExtra("member", mItem));
          break;
        case R.id.follow_icon:
          if (isFollowing) {
            followInterface.unfollow(TYPE.Member, mItem.id);
            followIcon.setImageResource(R.drawable.heart_open);
          } else {
            followInterface.follow(TYPE.Member, mItem.id);
            followIcon.setImageResource(R.drawable.heart_closed);
          }
          isFollowing = !isFollowing;
          break;
      }
    }
  }
}