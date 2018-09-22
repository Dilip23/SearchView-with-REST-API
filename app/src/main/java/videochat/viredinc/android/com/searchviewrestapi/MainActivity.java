package videochat.viredinc.android.com.searchviewrestapi;

import android.app.SearchManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import videochat.viredinc.android.com.searchviewrestapi.entities.ApiClient;
import videochat.viredinc.android.com.searchviewrestapi.entities.Result;
import videochat.viredinc.android.com.searchviewrestapi.entities.UserClient;

public class MainActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        final SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        SearchManager searchManager = (SearchManager) getSystemService(SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                UserClient userClient  = ApiClient.getApiClient().create(UserClient.class);
                Call call = userClient.getUserData(s);
                call.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        List<Result> resultList = (List<Result>) response.body();
                        Log.i("onResponse()-res-->",response.body().toString());
                        Log.i("onResponse()->rList-->",resultList.toString());
                        UserAdapter userAdapter = new UserAdapter(getApplicationContext(), resultList);
                        listView.setAdapter(userAdapter);
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG).show();
                        Log.i("onFailure()",t.getMessage());
                    }
                });
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
}