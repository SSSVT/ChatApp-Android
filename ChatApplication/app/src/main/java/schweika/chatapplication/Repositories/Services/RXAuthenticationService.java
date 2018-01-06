package schweika.chatapplication.Repositories.Services;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.Models.API.Token;
import schweika.chatapplication.Models.UserCredentials;

public interface RXAuthenticationService
{
    @POST("Registration/Register")
    Completable register(@Body User user);

    @GET("Registration/IsUsernameAvailable")
    Single<Boolean> isUsernameAvailable(@Query("id") String username);

    @POST("Token/LoginAsync")
    Single<Token> login(@Body UserCredentials userCredentials);
}
