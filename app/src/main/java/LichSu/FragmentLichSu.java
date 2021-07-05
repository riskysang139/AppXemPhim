package LichSu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appxemphim.R;
import com.example.appxemphim.databinding.FragmentLichSuBinding;


public class FragmentLichSu extends Fragment {
    FragmentLichSuBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_lich_su,container,false);
        View view=binding.getRoot();
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new FragmentLichSu()).commit();
            }
        });
        return view;
    }
}