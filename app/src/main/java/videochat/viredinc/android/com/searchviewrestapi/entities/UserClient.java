package videochat.viredinc.android.com.searchviewrestapi.entities;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserClient {

    //Retrieve User Data after first-time Login by searching
    @GET("profile/")
    Call<List<Result>> getUserData(@Query("search") String username);

}
