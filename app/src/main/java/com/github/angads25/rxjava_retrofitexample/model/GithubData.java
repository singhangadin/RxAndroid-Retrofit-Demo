package com.github.angads25.rxjava_retrofitexample.model;

import com.google.gson.annotations.SerializedName;

/**
 * <p>
 * Created by Angad Singh on 16/9/17.
 * </p>
 */

public class GithubData {

    private long id;

    @SerializedName("name")
    private String repositoryName;

    @SerializedName("stargazers_count")
    private String stargazers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getStargazers() {
        return stargazers;
    }

    public void setStargazers(String stargazers) {
        this.stargazers = stargazers;
    }
}
