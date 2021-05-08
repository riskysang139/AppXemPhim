package NguoiDung;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.example.appxemphim.R;
import com.example.appxemphim.databinding.FrameNguoiDungBinding;
import com.facebook.AccessToken;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

public class Frame_NguoiDung extends Fragment
{
    FrameNguoiDungBinding binding;
    FirebaseAuth firebaseAuth;
    UserInfo profile;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.frame__nguoi_dung,container,false);
        View view=binding.getRoot();
        BottomNavigationView bottomNavigationView=getActivity().findViewById(R.id.bottom_navigation);
        bottomNavigationView.setVisibility(View.VISIBLE);
        Glide.with(getContext()).load(R.drawable.logomovieapp).into(binding.imgProfile);
        firebaseAuth=FirebaseAuth.getInstance();
        checkUser();
        binding.btndangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    startActivity(new Intent(getActivity(),SignUpActivity.class));
            }
        });
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new Frame_NguoiDung()).commit();
            }
        });
        return view;
    }

    private void checkUser() {
        FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        String email="";
        Uri photo = null;
        if(firebaseUser==null)
        {
            binding.btnDangXuat.setVisibility(View.INVISIBLE);
            binding.btnHistory.setVisibility(View.INVISIBLE);
            binding.btnnoAdver.setBackgroundResource(R.drawable.shapelinearbtnup);
            binding.btndangNhap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }
            });
        }
        else
        {
            for (UserInfo profile : firebaseUser.getProviderData()) {
                email=profile.getEmail();
                photo=profile.getPhotoUrl();
            }
            binding.btnHistory.setVisibility(View.VISIBLE);
            binding.linearSignInUp.setVisibility(View.INVISIBLE);
            binding.txtUserID.setText(firebaseUser.getUid());
            binding.txtEmail.setText(email);
            Glide.with(getContext()).load(photo).circleCrop().into(binding.imgProfile);
            binding.btnDangXuat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    firebaseAuth.signOut();
                    startActivity(getActivity().getIntent());
                }
            });
        }
    }
}