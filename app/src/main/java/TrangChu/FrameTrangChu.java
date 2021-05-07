package TrangChu;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import com.example.appxemphim.R;
import com.example.appxemphim.databinding.FrameTrangchuBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.example.appxemphim.GetJsonAPI;
import TrangChu.Advertist.MovieAdver;
import TrangChu.Advertist.MovieAdverAdapter;
import TrangChu.Advertist.MovieAdverArrays;
import TrangChu.PhimDacSac.DacSacArrayImage;
import TrangChu.PhimDacSac.DacSacImage;
import TrangChu.PhimDacSac.DacSacRVAdapter;
import TrangChu.PhimPhoBien.PhoBienArrays;
import TrangChu.PhimPhoBien.PhoBienImage;
import TrangChu.PhimPhoBien.PhoBienRVAdapter;
import TrangChu.hotSeries.HighlightFilmsArrays;
import TrangChu.hotSeries.HighlightFilmImage;
import TrangChu.hotSeries.HighlightFilmAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FrameTrangChu extends Fragment {
    FrameTrangchuBinding binding;
    Timer mTimer;
    static List<MovieAdver> movieAdverList;
    MovieAdverAdapter movieAdverAdapter;
    static List<HighlightFilmImage> highlightFilmImageList;
    HighlightFilmAdapter highlightFilmAdapter;
    static List<DacSacImage> dacSacImageList;
     DacSacRVAdapter dacSacRVAdapter;
     static List<PhoBienImage> phoBienImageList;
     PhoBienRVAdapter phoBienRVAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.frame_trangchu,container,false);
        View view=binding.getRoot();

        binding.viewAdver.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                final float normalizedposition = Math.abs(Math.abs(position) - 1);
                page.setScaleX(normalizedposition / 2 + 0.5f);
                page.setScaleY(normalizedposition / 2 + 0.5f);
                page.setTranslationX(-position*page.getWidth());

                page.setAlpha(1-Math.abs(position));
            }
        });
        binding.refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainFragment,new FrameTrangChu()).commit();
            }
        });
       viewPagerAdvertisement();
        phoBien();
       hotSeries();
       dacSac();
        return view;
    }
    public void viewPagerAdvertisement()
    {
        GetJsonAPI.getGetJsonAPI.movieAdver().enqueue(new Callback<MovieAdverArrays>() {
            @Override
            public void onResponse(Call<MovieAdverArrays> call, Response<MovieAdverArrays> response) {
                MovieAdverArrays movieAdverArrays =response.body();
                movieAdverList=new ArrayList<>(Arrays.asList(movieAdverArrays.getAdver()));
                movieAdverAdapter=new MovieAdverAdapter(getActivity(),movieAdverList);
                binding.viewAdver.setAdapter(movieAdverAdapter);
                binding.circleCenter.setViewPager(binding.viewAdver);
                movieAdverAdapter.registerDataSetObserver(binding.circleCenter.getDataSetObserver());
                autoSlideIMage();
            }
            @Override
            public void onFailure(Call<MovieAdverArrays> call, Throwable t) {
                Toast.makeText(getActivity(),"Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void hotSeries()
    {
        GetJsonAPI.getGetJsonAPI.hotSeries().enqueue(new Callback<HighlightFilmsArrays>() {
            @Override
            public void onResponse(Call<HighlightFilmsArrays> call, Response<HighlightFilmsArrays> response) {
                HighlightFilmsArrays highlightFilmsArrays =response.body();
                highlightFilmImageList =new ArrayList<>(Arrays.asList(highlightFilmsArrays.getHotSeries()));
                highlightFilmAdapter =new HighlightFilmAdapter(highlightFilmImageList,getActivity());
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                binding.rcvHotSeries.setLayoutManager(layoutManager);
                binding.rcvHotSeries.setAdapter(highlightFilmAdapter);

            }

            @Override
            public void onFailure(Call<HighlightFilmsArrays> call, Throwable t) {
                Toast.makeText(getActivity(),"Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void dacSac()
    {
        GetJsonAPI.getGetJsonAPI.dacSac().enqueue(new Callback<DacSacArrayImage>() {
            @Override
            public void onResponse(Call<DacSacArrayImage> call, Response<DacSacArrayImage> response) {
                DacSacArrayImage dacSacArrayImage =response.body();
                dacSacImageList=new ArrayList<>(Arrays.asList(dacSacArrayImage.getDacSac()));
                dacSacRVAdapter =new DacSacRVAdapter(dacSacImageList,getActivity());
                LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                binding.rcvDacSac.setLayoutManager(layoutManager);
                binding.rcvDacSac.setAdapter(dacSacRVAdapter);
            }
            @Override
            public void onFailure(Call<DacSacArrayImage> call, Throwable t) {
                Toast.makeText(getActivity(),"Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!",Toast.LENGTH_LONG).show();
            }
        });
    }
    public void phoBien()
    {
        GetJsonAPI.getGetJsonAPI.phoBien().enqueue(new Callback<PhoBienArrays>() {
            @Override
            public void onResponse(Call<PhoBienArrays> call, Response<PhoBienArrays> response) {
                PhoBienArrays phoBienArrays=response.body();
                phoBienImageList=new ArrayList<>(Arrays.asList(phoBienArrays.getPhoBien()));
                phoBienRVAdapter=new PhoBienRVAdapter(phoBienImageList,getActivity());
                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                binding.rcvPhoBien.setLayoutManager(linearLayoutManager);
                binding.rcvPhoBien.setAdapter(phoBienRVAdapter);

            }

            @Override
            public void onFailure(Call<PhoBienArrays> call, Throwable t) {
                Toast.makeText(getActivity(),"Lỗi Kết Nối Mạng , Vui Lòng Thử Lại !!!",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void autoSlideIMage() {
        if (mTimer == null) {
            mTimer = new Timer();
        }
        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            int currentItem = binding.viewAdver.getCurrentItem();
                            int totalItem = movieAdverList.size() - 1;
                            if (currentItem < totalItem) {
                                currentItem++;
                                binding.viewAdver.setCurrentItem(currentItem);
                            } else
                                binding.viewAdver.setCurrentItem(0);
                        }
                    });
                }
            }
        }, 3000, 8000);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTimer != null) {
            mTimer.cancel();
        }
    }


}
