package com.github.angads25.rxjava_retrofitexample.model;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * <p>
 * Created by Angad Singh on 16/9/17.
 * </p>
 */

public interface GithubService {

    @GET("users/{username}/repos")
    Call<ArrayList<GithubData>> getRepositories(@Path("username") String username);


}
