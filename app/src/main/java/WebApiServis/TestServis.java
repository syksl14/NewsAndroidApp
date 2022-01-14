package WebApiServis;

import java.util.List;

import Models.Haberler;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Selahattin on 4.10.2017.
 */

public interface TestServis {

    @GET("haberler/" + ServiceGen.token)
    Call<List<Haberler>> getHaberler();

    @GET("haberler/haber/{id}/" +  ServiceGen.token)
    Call<Haberler> getHaber(@Path("id") int id);
}
