package TrangChu.Advertist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.appxemphim.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdverAdapter extends RecyclerView.Adapter<MovieAdverAdapter.ViewHoder> {
    private List<MovieAdver> movieAdverList;
    private ViewPager2 viewPager2;
    public MovieAdverAdapter(List<MovieAdver> movieAdverList, ViewPager2 viewPager2) {
        this.movieAdverList = movieAdverList;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHoder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_adv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        MovieAdver movieAdver = movieAdverList.get(position);
        Picasso.get()
                .load(movieAdver.getImage())
                .into(holder.roundedImageView);
        if (position == movieAdverList.size() - 2) {
            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return movieAdverList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder {
        RoundedImageView roundedImageView;

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            roundedImageView = itemView.findViewById(R.id.imgPhoto);
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            movieAdverList.addAll(movieAdverList);
            notifyDataSetChanged();
        }
    };
}
