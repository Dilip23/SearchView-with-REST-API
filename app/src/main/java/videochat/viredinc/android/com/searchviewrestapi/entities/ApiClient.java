package videochat.viredinc.android.com.searchviewrestapi.entities;


import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    //10.0.2.2
    //192.168.0.104
    public static final String BASE_URL = "http://10.0.2.2:8000/";

    public static Retrofit retrofit = null;
    public static OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder()
            .addNetworkInterceptor(new HttpLoggingInterceptor())
            .readTimeout(1000,TimeUnit.SECONDS);


    public static Retrofit getApiClient(){
        if(retrofit == null){


            /*
                Logging Interceptor for Retrofit Client
            */


            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(loggingInterceptor);

            okhttpBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = chain.request();
                    Request.Builder newRequest = request.newBuilder().header("Authorization","Token "+"2d3bca37396f1c4bb9ddf3ad457d642147aaa334");
                    return chain.proceed(newRequest.build());

                }
            });


            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okhttpBuilder.build())
                    .addConverterFactory(GsonConverterFactory.create(new
                            GsonBuilder().serializeNulls().create()))
                    .build();


        }
        return retrofit;
    }

}