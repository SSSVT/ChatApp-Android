package schweika.chatapplication.Repositories.Services;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.UserCredentials;

public interface TokenService
{
    @POST("Registration/Register")
    Call<Void> register(@Body User user);

    @GET("Registration/IsUsernameAvailable")
    Call<Boolean> isUsernameAvailable(@Query("id") String username);

    @POST("Token/LoginAsync")
    Call<Token> login(@Body UserCredentials userCredentials);
}
