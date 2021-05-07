package TrangChu.hotSeries;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.appxemphim.R;

import java.util.List;

import TrangChu.ItemClickListener;

public class HighlightFilmAdapter extends RecyclerView.Adapter<HighlightFilmAdapter.ViewHoder> {
    List<HighlightFilmImage>  contactList;
    Context context;

    public HighlightFilmAdapter(List<HighlightFilmImage> contactList, Context context) {
        this.contactList = contactList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rcvphimnoibat,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
            HighlightFilmImage highlightFilmImage =contactList.get(position);
        Glide.with(context)
                .load(highlightFilmImage.getImageHS())
                .transform(new CenterCrop(),new RoundedCorners(16))
                .into(holder.hotSeriesImage1);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
               switch (position)
               {
                   case 0:
                        break;
               }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView hotSeriesImage1;
        ItemClickListener itemClickListener;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            hotSeriesImage1=itemView.findViewById(R.id.highlightFilmImage);

        }

        @Override
        public void onClick(View v) {
                itemClickListener.OnClick(v,getAdapterPosition());
        }


    }
}
