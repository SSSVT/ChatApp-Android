package schweika.chatapplication.Repositories;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Path;

public interface ValuesService
{
    String ENDPOINT = "http://192.168.1.201:60141/api/";

    @GET("values")
    Call<List<String>> findAll();
}
