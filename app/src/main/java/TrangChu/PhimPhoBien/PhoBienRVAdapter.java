package TrangChu.PhimPhoBien;

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
import TrangChu.PhimDacSac.DacSacRVAdapter;

public class PhoBienRVAdapter extends RecyclerView.Adapter<PhoBienRVAdapter.ViewHoder> {
    List<PhoBienImage> contactlist;
    Context context;

    public PhoBienRVAdapter(List<PhoBienImage> contactlist, Context context) {
        this.contactlist = contactlist;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public PhoBienRVAdapter.ViewHoder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rcvphobien,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull PhoBienRVAdapter.ViewHoder holder, int position) {
            PhoBienImage phoBienImage=contactlist.get(position);
            Glide.with(context).load(phoBienImage.getPhoBienImage())
                    .transform(new CenterCrop(),new RoundedCorners(15))
                    .into(holder.imageView);
            holder.txtTitle.setText(phoBienImage.getTextTitlePB());
            holder.txtPublish.setText(phoBienImage.getTxtPublished());
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
        TextView txtPublish;
        ItemClickListener itemClickListener;
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imagePhoBien);
            txtTitle=itemView.findViewById(R.id.txtTitle);
            txtPublish=itemView.findViewById(R.id.txtPublish);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v,getAdapterPosition());
        }
    }
}
