package schweika.chatapplication.Repositories.Services;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.Token;
import schweika.chatapplication.Models.UserCredentials;

public interface RXTokenService
{
    @POST("Registration/Register")
    Observable<Void> register(@Body User user);

    @GET("Registration/IsUsernameAvailable")
    Observable<Boolean> isUsernameAvailable(@Query("id") String username);

    @POST("Token/LoginAsync")
    Observable<Token> login(@Body UserCredentials userCredentials);
}
