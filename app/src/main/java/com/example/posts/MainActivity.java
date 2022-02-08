package com.example.posts;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;

import io.reactivex.ObservableSource;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    private Button insertBtn , getbtn ;
    private EditText titleET , bodyET ;

    private RecyclerView postsRecyclerView;
    private static final String TAG = "main";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postsRecyclerView = findViewById(R.id.posts_recyclerview);

        insertBtn = findViewById(R.id.InsertButton) ;
        getbtn = findViewById(R.id.getButton);
        titleET = findViewById(R.id.editTextTitle);
        bodyET = findViewById( R.id.editTextTextBody) ;

        RecyclerView.LayoutManager recyce = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        postsRecyclerView.setLayoutManager(recyce);

        final PostListAdaptor adaptor =new PostListAdaptor();
         postsRecyclerView.setAdapter(adaptor);


        final PostsDB postsDB = PostsDB.getInstance(this);

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsDB.postsDao().insertPost(new Post(new User(1 ,"zatona") , titleET.getText().toString() , bodyET.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                });

            }
        });
        getbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postsDB.postsDao().getPost()
                        .subscribeOn(Schedulers.computation())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<List<Post>>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }
                            @Override
                            public void onSuccess( List<Post> posts) {
                                adaptor.setArrPost(posts);
                                adaptor.notifyDataSetChanged();
                            }
                            @Override
                            public void onError(@NonNull Throwable e) {

                            }
                        });

            }

        });






    }


}