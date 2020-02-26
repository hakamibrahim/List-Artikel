package com.hakam.artikelretrofit.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.hakam.artikelretrofit.adapter.PostAdapter;
import com.hakam.artikelretrofit.model.PostModel;
import com.hakam.artikelretrofit.R;
import com.hakam.artikelretrofit.network.RetrofitClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient = new RetrofitClient();

    @BindView(R.id.rcArtikel)
    RecyclerView recyclerView;

    private PostAdapter postAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        retrofitClient.apiInterface.getPosts().enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                getData(response.body());
                Toast.makeText(MainActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                //result.setText(t.getMessage());
                Toast.makeText(MainActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getData(List<PostModel> postModelList){
        postAdapter = new PostAdapter(this, postModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(postAdapter);
        recyclerView.setHasFixedSize(true);
    }
}
