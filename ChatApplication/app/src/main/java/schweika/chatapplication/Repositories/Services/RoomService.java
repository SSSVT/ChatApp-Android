package schweika.chatapplication.Repositories.Services;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import schweika.chatapplication.Models.API.Room;

public interface RoomService
{
    @GET("Rooms/FindByUserID")
    Call<List<Room>> findByUserID(@Header("Authorization") String token,@Query("id") Long id);

    @GET("Rooms/FindAll")
    Call<List<Room>> findAll(@Header("Authorization") String token);

    @POST("Rooms/Create")
    Call<Room> create(@Header("Authorization") String token,@Body Room room);

    @PUT("Rooms/Update/{id}")
    Call<Void> update(@Header("Authorization") String token,@Path("id") long id,@Body Room room);

    @DELETE("Rooms/Delete/{id}")
    Call<Void> delete(@Header("Authorization") String token,@Path("id") long id);
}
