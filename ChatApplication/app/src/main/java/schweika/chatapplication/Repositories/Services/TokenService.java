package schweika.chatapplication.Repositories.Services;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.User;
import schweika.chatapplication.Models.UserCredentials;

public interface TokenService
{
    String ENDPOINT = "http://192.168.1.201:51212/api/v1/";

    @POST("Registration/Register")
    Call<Void> register(@Body User user);

    @GET("Registration/IsUsernameAvailable")
    Call<Boolean> isUsernameAvailable(@Query("id") String username);

    @POST("Token/LoginAsync")
    Call<Token> login(@Body UserCredentials userCredentials);
}
