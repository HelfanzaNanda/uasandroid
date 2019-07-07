package com.elfan.dong.myuas.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.elfan.dong.myuas.R;
import com.elfan.dong.myuas.adapter.AdapterBerita;
import com.elfan.dong.myuas.network.ApiServices;
import com.elfan.dong.myuas.network.InitRetrofit;
import com.elfan.dong.myuas.response.BeritaItem;
import com.elfan.dong.myuas.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BeritaFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_berita, container, false);
        recyclerView = view.findViewById(R.id.rvListBerita);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        tampilberita();
        return view;
    }

    private void tampilberita() {
        ApiServices api = InitRetrofit.getInstance();
        Call<ResponseBerita> beritaCall = api.request_show_all_berita();
        beritaCall.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                if (response.isSuccessful()){
                    Log.d( "onResponse: ",response.body().toString());
                    List<BeritaItem> data_berita = response.body().getBerita();
                    boolean status = response.body().isStatus();
                    if (status){
                        AdapterBerita adapter = new AdapterBerita(getActivity(), data_berita);
                        recyclerView.setAdapter(adapter);
                    }else {
                        Toast.makeText(getActivity(), "Tidak Ada Berita Untuk Saat Ini", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure : "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
