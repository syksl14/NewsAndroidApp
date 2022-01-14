package WebApiServis;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 04.10.2017.
 */

public class ServiceGen {
    final public static String token = "44E86333-0478-4B38-A603-83D696F37B32";
    public static String baseUrl = "http://api.scloudos.com/api/";
    public static Retrofit retrofit;
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create());
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    public static  <S> S createService(Class<S> serviceClass)
    {
        builder.client(httpClient.build());
        retrofit = builder.build();
        return retrofit.create(serviceClass);
    }
}
