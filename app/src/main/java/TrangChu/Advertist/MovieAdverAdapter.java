package TrangChu.Advertist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.appxemphim.R;

import java.util.List;

public class MovieAdverAdapter extends PagerAdapter {
    private Context mContext;
    private List<MovieAdver> mlistMovieAdver;

    public MovieAdverAdapter(Context mContext, List<MovieAdver> mlistMovieAdver) {
        this.mContext = mContext;
        this.mlistMovieAdver = mlistMovieAdver;
    }

    @Override
    public int getCount() {
        if(mlistMovieAdver != null)
        {
            return  mlistMovieAdver.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view= LayoutInflater.from(container.getContext()).inflate(R.layout.view_pager_item,container,false);
        ImageView imageView=view.findViewById(R.id.imgPhoto);
        MovieAdver movieAdver=mlistMovieAdver.get(position);
        Glide.with(mContext)
                    .load(movieAdver.getImage())
                    .into(imageView);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

}
