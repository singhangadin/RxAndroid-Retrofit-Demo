package com.github.angads25.rxjava_retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.angads25.rxjava_retrofitexample.model.GithubData;
import com.github.angads25.rxjava_retrofitexample.model.GithubService;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.repo_list)
    RecyclerView repoList;

    @BindView(R.id.edt_id_name)
    EditText username;

    @BindView(R.id.btn_go)
    Button yoButton;

    @BindView(R.id.prograss_bar)
    ProgressBar progressBar;

    private GithubService githubService;

    private GithubListAdapter githubListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        githubService = retrofit.create(GithubService.class);

        yoButton.setOnClickListener(this);

        repoList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        repoList.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));

        githubListAdapter = new GithubListAdapter(MainActivity.this);
        repoList.setAdapter(githubListAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_go: {
                Call<ArrayList<GithubData>> yoVar = githubService.getRepositories(username.getText().toString());
                progressBar.setVisibility(View.VISIBLE);
                yoVar.enqueue(new Callback<ArrayList<GithubData>>() {
                    @Override
                    public void onResponse(Call<ArrayList<GithubData>> call, Response<ArrayList<GithubData>> response) {
                        ArrayList<GithubData> responseData = response.body();
                        githubListAdapter.addAll(responseData);
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ArrayList<GithubData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
                break;
            }
        }
    }
}
