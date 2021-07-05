package TrangChu.PhimDacSac;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.appxemphim.R;

import java.util.List;

import TrangChu.ItemClickListener;

public class RVDacSacAdapter extends RecyclerView.Adapter<RVDacSacAdapter.ViewHoder> {
    List<DacSacFilm> contactlist;
    Context context;

    public RVDacSacAdapter(List<DacSacFilm> contactlist, Context context) {
        this.contactlist = contactlist;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_dacsac,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        DacSacFilm dacSacFilm =contactlist.get(position);
        Glide.with(context)
                .load(dacSacFilm.getDacSacImage())
                .transform(new CenterCrop(),new RoundedCorners(25))
                .into(holder.imageView);
        holder.txtTitle.setText(dacSacFilm.getTextDacSac());
        holder.txtCategory.setText(dacSacFilm.getTextCategory());
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void OnClick(View view, int position) {
                switch (position)
                {
                    case 1:
                    {
                        break;
                    }
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return contactlist.size();
    }
    public class ViewHoder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView txtTitle;
        TextView txtCategory;
        ItemClickListener itemClickListener;
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageDacSac);
            txtTitle=itemView.findViewById(R.id.textDacSac);
            txtCategory=itemView.findViewById(R.id.textCategory);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v,getAdapterPosition());
        }
    }
}
