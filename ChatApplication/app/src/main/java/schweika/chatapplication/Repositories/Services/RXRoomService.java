package schweika.chatapplication.Repositories.Services;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.Room;

public interface RXRoomService
{
    @GET("Rooms/FindByUserID")
    Observable<List<Room>> findByUserID(@Header("Authorization") String token, @Query("id") Long id);

    @GET("Rooms/FindAll")
    Observable<List<Room>> findAll(@Header("Authorization") String token);

    @POST("Rooms/Create")
    Observable<Room> create(@Header("Authorization") String token,@Body Room room);

    @PUT("Rooms/Update/{id}")
    Completable update(@Header("Authorization") String token, @Path("id") long id, @Body Room room);

    @DELETE("Rooms/Delete/{id}")
    Completable delete(@Header("Authorization") String token,@Path("id") long id);
}
