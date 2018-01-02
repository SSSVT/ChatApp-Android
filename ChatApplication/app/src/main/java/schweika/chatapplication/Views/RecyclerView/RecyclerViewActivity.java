package schweika.chatapplication.Views.RecyclerView;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.User;
import schweika.chatapplication.R;
import schweika.chatapplication.RecyclerView.Adapters.UserRecyclerViewAdapter;
import schweika.chatapplication.RecyclerView.ViewModels.UserViewModel;

public class RecyclerViewActivity extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recyclerView_test);

        //recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(new UserRecyclerViewAdapter(this,getUserViewModels()));
    }

    private ArrayList<UserViewModel> getUserViewModels()
    {
        ArrayList<UserViewModel> data = new ArrayList<>();

        User user = new User();
        user.username = "test";
        user.email = "test@test.cz";
        data.add(new UserViewModel(user));


        return data;
    }
}
