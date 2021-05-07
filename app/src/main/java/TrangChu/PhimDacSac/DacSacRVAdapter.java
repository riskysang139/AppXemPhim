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

public class DacSacRVAdapter extends RecyclerView.Adapter<DacSacRVAdapter.ViewHoder> {
    List<DacSacImage> contactlist;
    Context context;

    public DacSacRVAdapter(List<DacSacImage> contactlist, Context context) {
        this.contactlist = contactlist;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.rcvdacsac,parent,false);
        return new ViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoder holder, int position) {
        DacSacImage dacSacImage=contactlist.get(position);
        Glide.with(context)
                .load(dacSacImage.getDacSacImage())
                .transform(new CenterCrop(),new RoundedCorners(16))
                .into(holder.imageView);
        holder.textView.setText(dacSacImage.getTextDacSac());
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
        TextView textView;
        ItemClickListener itemClickListener;
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        public ViewHoder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageDacSac);
            textView=itemView.findViewById(R.id.textDacSac);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.OnClick(v,getAdapterPosition());
        }
    }
}
