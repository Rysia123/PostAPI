package org.example;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller implements Callback<List<Post>>{
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public void start() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PostAPI postAPI = retrofit.create(PostAPI.class);
        Call<List<Post>> call = postAPI.getPost();
        call.enqueue(this);
    }
    @Override
    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
        if(response.isSuccessful()){
            List<Post> posts = response.body();
            System.out.println(posts);
        }
        else{
            System.out.println(response.errorBody());
        }

    }

    @Override
    public void onFailure(Call<List<Post>> call, Throwable throwable) {
        throwable.printStackTrace(); //wywala błąd

    }
}
