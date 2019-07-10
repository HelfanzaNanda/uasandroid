package com.elfan.dong.myuas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elfan.dong.myuas.AddPenegaduanActivity;
import com.elfan.dong.myuas.R;
import com.elfan.dong.myuas.adapter.AdapterPengaduan;
import com.elfan.dong.myuas.helper.RealmHelper;
import com.elfan.dong.myuas.model.PengaduanModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PengaduanFragment extends Fragment {

    List<PengaduanModel> list = new ArrayList<>();
    RecyclerView recycler;
    RealmHelper realm;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengaduan, container, false);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddPenegaduanActivity.class);
                startActivity(intent);
            }
        });
        recycler = view.findViewById(R.id.rv_pengaduan);
        realm = new RealmHelper(getActivity());
        showData();
        return view;
    }

    private void showData(){
        list.clear();
        list = realm.showdata();
        recycler.setAdapter(new AdapterPengaduan(getActivity(), list));
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        //recycler.setHasFixedSize(true);
        recycler.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
    }

    @Override
    public void onResume() {
        super.onResume();
        list = realm.showdata();
        recycler.setAdapter(new AdapterPengaduan(getActivity(), list));
    }
}
