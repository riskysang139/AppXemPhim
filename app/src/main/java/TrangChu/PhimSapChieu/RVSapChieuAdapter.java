package TrangChu.PhimSapChieu;

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

import org.jetbrains.annotations.NotNull;

import java.util.List;

import TrangChu.ItemClickListener;

public class RVSapChieuAdapter extends RecyclerView.Adapter<RVSapChieuAdapter.ViewHoder> {
    List<SapChieuFilm> contactlist;
    Context context;

    public RVSapChieuAdapter(List<SapChieuFilm> contactlist, Context context) {
        this.contactlist = contactlist;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public RVSapChieuAdapter.ViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_phobien,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RVSapChieuAdapter.ViewHoder holder, int position) {
            SapChieuFilm sapChieuFilm =contactlist.get(position);
            Glide.with(context).load(sapChieuFilm.getPhoBienImage())
                    .transform(new CenterCrop(),new RoundedCorners(25))
                    .into(holder.imageView);
            holder.txtTitle.setText(sapChieuFilm.getTextTitleCS());
            holder.txtCategory.setText(sapChieuFilm.getTxtCategoryCS());
            holder.setItemClickListener(new ItemClickListener() {
                @Override
                public void OnClick(View view, int position) {

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
            imageView=itemView.findViewById(R.id.imageCS);
            txtTitle=itemView.findViewById(R.id.txtTitleCS);
            txtCategory=itemView.findViewById(R.id.textCategoryCS);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v,getAdapterPosition());
        }
    }
}
