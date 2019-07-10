package com.elfan.dong.myuas.helper;

import android.content.Context;

import com.elfan.dong.myuas.model.PengaduanModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmHelper {

    private Context context;
    private Realm realm;

    public RealmHelper(Context context) {
        this.context = context;
        Realm.init(context);
        realm = Realm.getDefaultInstance();
    }

    public void insertdata(PengaduanModel pengaduanModel){
        realm.beginTransaction();
        realm.copyToRealm(pengaduanModel);
        realm.commitTransaction();
        realm.close();
    }

    public PengaduanModel showonedata(Integer id){
        PengaduanModel pengaduanModel = realm.where(PengaduanModel.class).equalTo("id", id).findFirst();
        return pengaduanModel;
    }

    public void updatedata(PengaduanModel pengaduanModel){
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(pengaduanModel);
        realm.commitTransaction();
        realm.close();
    }

    public void deletedata(Integer id){
        realm.beginTransaction();
        PengaduanModel pengaduanModel = realm.where(PengaduanModel.class).equalTo("id",id).findFirst();
        pengaduanModel.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    public List<PengaduanModel> showdata(){
        if (realm.isClosed()){
            realm = Realm.getDefaultInstance();
        }
        RealmResults<PengaduanModel> datahasil = realm.where(PengaduanModel.class).findAll();
        List<PengaduanModel> datalist = new ArrayList<>();
        datalist.addAll(realm.copyFromRealm(datahasil));
        return datalist;
    }

    public long getnextid(){
        if (realm.where(PengaduanModel.class).count() != 0){
            long id = realm.where(PengaduanModel.class).max("id").longValue();
            return id+1;
        }else {
            return 1;
        }
    }
}
