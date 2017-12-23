package schweika.chatapplication.Repositories;

import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import schweika.chatapplication.Models.User;

public interface UserService
{
    String ENDPOINT = "http://192.168.1.201:61489/api/v1/Registration/";

    @POST("Register")
    Call<RequestBody> register(@Body User user);

    @GET("IsUsernameAvailable")
    Call<RequestBody> isUsernameAvailable(@Body String username);

    @GET("")
    Call<User> login(String username, String Password);


}
