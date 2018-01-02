package schweika.chatapplication.Repositories;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.DateDeserializer;
import schweika.chatapplication.Repositories.Services.NetworkConfig;

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
