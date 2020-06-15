package com.kamil184.newmotivate.ui.tasks;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.kamil184.newmotivate.R;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DateGroupViewHolder extends GroupViewHolder {

    @BindView(R.id.date_group_title)
    TextView title;
    @BindView(R.id.date_group_add)
    ImageView add;
    @BindView(R.id.date_group_count)
    Chip count;

    private int size;
    private Context context;

    public DateGroupViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void onBind(ExpandableGroup group) {
        title.setText(group.getTitle());
        size = group.getItemCount();
        if (size == 0) {
            add.setVisibility(View.VISIBLE);
            count.setVisibility(View.GONE);
        } else {
            count.setText(String.valueOf(size));
        }

        add.setOnClickListener(view -> {
            //TODO
        });
    }

    @Override
    public void expand() {
        if (size != 0) {
            Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    add.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    count.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            add.startAnimation(fadeIn);
            count.startAnimation(fadeOut);
        }
    }

    @Override
    public void collapse() {
        if (size == 0) {
            add.setVisibility(View.VISIBLE);
            count.setVisibility(View.GONE);
        } else {
            Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
            Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
            fadeIn.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    count.setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            fadeOut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    add.setVisibility(View.GONE);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
            count.startAnimation(fadeIn);
            add.startAnimation(fadeOut);
        }
    }
}