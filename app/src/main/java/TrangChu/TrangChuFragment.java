package TrangChu;


import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.appxemphim.GetJsonAPI;
import com.example.appxemphim.R;
import com.example.appxemphim.databinding.FragmentTrangchuBinding;

import java.util.ArrayList;

import TrangChu.Advertist.MovieAdver;
import TrangChu.Advertist.MovieAdverAdapter;
import TrangChu.PhimDacSac.DacSacFilm;
import TrangChu.PhimDacSac.RVDacSacAdapter;
import TrangChu.PhimSapChieu.SapChieuFilm;
import TrangChu.PhimSapChieu.RVSapChieuAdapter;
import TrangChu.hotSeries.HotSeriesFilm;
import TrangChu.hotSeries.RVHotSeriesFilmAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrangChuFragment extends Fragment {
    FragmentTrangchuBinding bindingTrangChu;
    private RVDacSacAdapter RVDacSacAdapter;
    private RVSapChieuAdapter RVSapChieuAdapter;
    MovieAdverAdapter movieAdverAdapter;
    private ArrayList<HotSeriesFilm> hotSeriesFilmsList;
    private ArrayList<DacSacFilm> dacSacFilmList;
    private ArrayList<SapChieuFilm> sapChieuFilmList;
    private ArrayList<MovieAdver> movieAdverList;
    private RVHotSeriesFilmAdapter rvDacSacAdapter;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        bindingTrangChu = DataBindingUtil.inflate(inflater, R.layout.fragment_trangchu, container, false);
        View view = bindingTrangChu.getRoot();
        viewPagerAdvertisement();
        hotSeries();
        phoBien();
        dacSac();
        onRefresh();
        return view;
    }

    public void viewPagerAdvertisement() {
        GetJsonAPI.getJsonAPI.movieAdver().enqueue(new Callback<ArrayList<MovieAdver>>() {
            @Override
            public void onResponse(Call<ArrayList<MovieAdver>> call, Response<ArrayList<MovieAdver>> response) {
                movieAdverList = response.body();
                bindingTrangChu.viewAdver.setAdapter(new MovieAdverAdapter(movieAdverList, bindingTrangChu.viewAdver));
                onAutoScrollAD();
//
            }

            //
            @Override
            public void onFailure(Call<ArrayList<MovieAdver>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!", Toast.LENGTH_LONG).show();
            }
        });

    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            bindingTrangChu.viewAdver.setCurrentItem(bindingTrangChu.viewAdver.getCurrentItem() + 1);
        }
    };

    public void hotSeries() {
        GetJsonAPI.getJsonAPI.hotSeries().enqueue(new Callback<ArrayList<HotSeriesFilm>>() {
            @Override
            public void onResponse(Call<ArrayList<HotSeriesFilm>> call, Response<ArrayList<HotSeriesFilm>> response) {
                hotSeriesFilmsList = response.body();
                rvDacSacAdapter = new RVHotSeriesFilmAdapter(hotSeriesFilmsList, getContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                bindingTrangChu.rcvHotSeries.setLayoutManager(layoutManager);
                bindingTrangChu.rcvHotSeries.setAdapter(rvDacSacAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<HotSeriesFilm>> call, Throwable t) {

            }
        });
    }

    public void dacSac() {
        GetJsonAPI.getJsonAPI.dacSac().enqueue(new Callback<ArrayList<DacSacFilm>>() {
            @Override
            public void onResponse(Call<ArrayList<DacSacFilm>> call, Response<ArrayList<DacSacFilm>> response) {
                dacSacFilmList = response.body();
                RVDacSacAdapter = new RVDacSacAdapter(dacSacFilmList, getActivity());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                bindingTrangChu.rcvDacSac.setLayoutManager(layoutManager);
                bindingTrangChu.rcvDacSac.setAdapter(RVDacSacAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<DacSacFilm>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void phoBien() {
        GetJsonAPI.getJsonAPI.phoBien().enqueue(new Callback<ArrayList<SapChieuFilm>>() {
            @Override
            public void onResponse(Call<ArrayList<SapChieuFilm>> call, Response<ArrayList<SapChieuFilm>> response) {
                sapChieuFilmList = response.body();
                RVSapChieuAdapter = new RVSapChieuAdapter(sapChieuFilmList, getActivity());
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                bindingTrangChu.rcvPhoBien.setLayoutManager(linearLayoutManager);
                bindingTrangChu.rcvPhoBien.setAdapter(RVSapChieuAdapter);
            }

            @Override
            public void onFailure(Call<ArrayList<SapChieuFilm>> call, Throwable t) {
                Toast.makeText(getActivity(), "Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onRefresh() {

        bindingTrangChu.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment, new TrangChuFragment()).commit();
                        bindingTrangChu.refreshLayout.setRefreshing(false);
                    }
                },1500);
            }
        });

    }

    private void onAutoScrollAD() {
        bindingTrangChu.viewAdver.setClipToPadding(false);
        bindingTrangChu.viewAdver.setClipChildren(false);
        bindingTrangChu.viewAdver.setOffscreenPageLimit(3);
        bindingTrangChu.viewAdver.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(20));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.8f + r * 0.2f);
            }
        });
        bindingTrangChu.viewAdver.setPageTransformer(compositePageTransformer);
        bindingTrangChu.viewAdver.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                handler.removeCallbacks(runnable);
                handler.postDelayed(runnable,3000);
            }
        });
    }

}
