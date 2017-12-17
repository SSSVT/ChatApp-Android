package schweika.chatapplication.Views.List;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import schweika.chatapplication.R;
import schweika.chatapplication.Repositories.ValuesService;

//import retrofit2.Retrofit;

public class ListActivity extends AppCompatActivity
{
    List<String> listItems = new ArrayList<>();

    ArrayAdapter<String> adapter;

    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);

        mListView = findViewById(R.id.listView);

        setListAdapter(adapter);

        Button btn = (Button) findViewById(R.id.button2);
        btn.setOnClickListener((view) ->
        {
            sendNetworkRequest();
        });
    }

    /*public void buttonClick(View view)
    {
        sendNetworkRequest();
    }*/

    protected void sendNetworkRequest()
    {
        Gson gson = new GsonBuilder().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ValuesService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ValuesService client = retrofit.create(ValuesService.class);

        Call<List<String>> call = client.findAll();

        call.enqueue(new Callback<List<String>>()
        {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response)
            {
                if (response.isSuccessful())
                {
                    Toast.makeText(ListActivity.this,"SUCCESS",Toast.LENGTH_SHORT).show();
                    adapter.clear();
                    adapter.addAll(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t)
            {
                Toast.makeText(ListActivity.this,"FAILED",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void addItems(int count)
    {
        for (int i = 0; i < count; i++)
        {
            adapter.add("hello");
        }
    }

    protected ListView getListView()
    {
        if (mListView == null) {
            mListView = (ListView) findViewById(R.id.listView);
        }
        return mListView;
    }

    protected  void setListAdapter(ListAdapter adapter)
    {
        getListView().setAdapter(adapter);
    }

    /*protected ListAdapter getListAdapter()
    {
        ListAdapter adapter = getListView().getAdapter();

        if (adapter instanceof HeaderViewListAdapter)
        {
            return ((HeaderViewListAdapter)adapter).getWrappedAdapter();
        }
        else
        {
            return adapter;
        }
    }*/
}
