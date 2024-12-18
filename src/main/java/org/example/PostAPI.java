package org.example;

import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface PostAPI {

    @GET("/posts")
    Call<List<Post>> getPost();

}
