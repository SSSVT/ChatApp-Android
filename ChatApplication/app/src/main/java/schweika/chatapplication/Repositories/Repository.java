package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.Repositories.Services.NetworkConfig;
import schweika.chatapplication.DateDeserializer;

public abstract class Repository
{
    protected Gson gson = new GsonBuilder()
            //.excludeFieldsWithoutExposeAnnotation()
            .registerTypeAdapter(Date.class, new DateDeserializer())
            .create();

    protected Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(NetworkConfig.ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
}
