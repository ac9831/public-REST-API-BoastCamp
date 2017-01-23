package com.gunjun.android.alarm_app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gunjun.android.alarm_app.models.Repo;
import com.gunjun.android.alarm_app.network.GitHubService;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Realm realm;
    private List<Repo> realmRepos;
    private RealmResults<Repo> list;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.push_alarm)
    Button pushButton;

    @BindView(R.id.release_alarm)
    Button releaseButton;

    private adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();


        network();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void network()  {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("kinwha");


        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response != null &&  response.body() != null)
                {
                    List<Repo> type = response.body();
                    RealmQuery<Repo> query = realm.where(Repo.class);

                    realm.beginTransaction();
                    realmRepos = realm.copyToRealmOrUpdate(type);
                    realm.commitTransaction();
                    list = query.findAll();
                    for(int i=0;i<list.size();i++) {
                        Log.i("MainActivity", list.get(i).getId() + ", " + list.get(i).getName());
                        Log.i("MainActivity", list.get(i).getOwner().getHtml_url() + ", " + list.get(i).getOwner().getLogin() + ", " + list.get(i).getOwner().getUrl());
                    }

                    layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    mAdapter = new adapter(list,mContext);
                    recyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

    }

    @OnClick({R.id.push_alarm, R.id.release_alarm})
    public void onClick(View v) {
        if(v.getId() == R.id.push_alarm) {
            setAlarm(this, 5000);
        } else if(v.getId() == R.id.release_alarm) {
            releaseAlarm(this);
        }
    }

    private void setAlarm(Context context, long second) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlaramActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        Toast.makeText(context, "알람", Toast.LENGTH_SHORT).show();
        alarmManager.set(AlarmManager.RTC, System.currentTimeMillis() + second,pendingIntent);
    }

    private void releaseAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent Intent = new Intent(this, AlaramActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, Intent, 0);
        alarmManager.cancel(pIntent);
    }
}
