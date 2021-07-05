package TrangChu.hotSeries;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appxemphim.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVHotSeriesFilmAdapter extends RecyclerView.Adapter<RVHotSeriesFilmAdapter.ViewHoder> {
    List<HotSeriesFilm> contactlist;
    Context mcontext;


    public RVHotSeriesFilmAdapter(List<HotSeriesFilm> contactlist, Context context) {
        this.contactlist = contactlist;
        this.mcontext = context;
    }

    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_phimnoibat,parent,false);
        return new ViewHoder(view);
    }
    @NonNull
    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        HotSeriesFilm hotSeriesFilm =contactlist.get(position);
        Picasso.get()
                .load(hotSeriesFilm.getImageHS())
                .into(holder.imageView);
        holder.txtTitle.setText(hotSeriesFilm.getTitleHS());
        holder.txtCategory.setText(hotSeriesFilm.getCategoryHS());
    }
    @Override
    public int getItemCount() {
        return contactlist.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView txtTitle,txtCategory;
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageHS);
            txtTitle=itemView.findViewById(R.id.text_titleHS);
            txtCategory=itemView.findViewById(R.id.textCategoryHS);
        }
    }
}
