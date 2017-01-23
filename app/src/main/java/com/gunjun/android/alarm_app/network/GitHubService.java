package com.gunjun.android.alarm_app.network;

import com.gunjun.android.alarm_app.models.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public interface GitHubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);
}
