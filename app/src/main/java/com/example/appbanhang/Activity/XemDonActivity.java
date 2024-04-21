package com.example.appbanhang.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.appbanhang.Adapter.DonHangAdapter;
import com.example.appbanhang.InterfaceUsing.ItemDeleteClickListener;
import com.example.appbanhang.R;
import com.example.appbanhang.Retrofit.ApiBanHang;
import com.example.appbanhang.Retrofit.RetrofitClient;
import com.example.appbanhang.Utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class XemDonActivity extends AppCompatActivity {
    ApiBanHang apiBanHang;
    RecyclerView recyclerView_xemdon;
    Toolbar toolbar;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_don);
        initView();
        initToolbar();
        getOrder();
    }

    private void getOrder() {
        compositeDisposable.add(apiBanHang.xemDonHang(Utils.currentUser.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        donHangModel -> {
                            DonHangAdapter donHangAdapter = new DonHangAdapter(getApplicationContext(), donHangModel.getResult(), new ItemDeleteClickListener() {
                                @Override
                                public void onClickDelete(int iddonhang) {
                                    showDeleteOrder(iddonhang);
                                }
                            });
                            recyclerView_xemdon.setAdapter(donHangAdapter);
                        },
                        throwable -> {

                        }));
    }

    private void showDeleteOrder(int iddonhang) {
        PopupMenu popupMenu = new PopupMenu(this,recyclerView_xemdon.findViewById(R.id.tv_trangthai));
        popupMenu.inflate(R.menu.delete);
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.delete){
                    deleteOrder(iddonhang);
                }
                return false;
            }
        });
        popupMenu.show();
    }

    private void deleteOrder(int iddonhang){
            compositeDisposable.add(apiBanHang.deleteorder(iddonhang)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            messagerModel -> {
                                if (messagerModel.isSuccess()){
                                    getOrder();
                                }
                            },
                            throwable -> {
                                Log.d("log",throwable.getMessage());
                            }
                    ));
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);
        recyclerView_xemdon = findViewById(R.id.recycleView_xemdon);
        toolbar = findViewById(R.id.toolbar_xemdon);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_xemdon.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}