package schweika.chatapplication.Views.Home.Fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.R;
import schweika.chatapplication.RecyclerView.Adapters.GenericRecyclerViewAdapter;
import schweika.chatapplication.RecyclerView.Adapters.RoomRecyclerViewAdapter;
import schweika.chatapplication.ViewModels.HomeViewModel;

public class RoomsFragment extends Fragment
{
    private RecyclerView recyclerView;
    //private RoomRecyclerViewAdapter adapter;
    private GenericRecyclerViewAdapter<Room> adapter;
    private HomeViewModel viewModelWrapper;

    public RoomsFragment()
    {


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModelWrapper = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

        adapter = new GenericRecyclerViewAdapter<>(viewModelWrapper.rooms.getValue(),R.layout.recycler_view_room);

        viewModelWrapper.rooms.observe(this, new Observer<ArrayList<Room>>()
        {
            @Override
            public void onChanged(@Nullable ArrayList<Room> rooms)
            {
                adapter.setList(rooms);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        FloatingActionButton button = view.findViewById(R.id.floatingActionButton_createRoom);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openCreateRoomFragment();
            }
        });

        viewModelWrapper.updateRooms();

        if (recyclerView == null)
            recyclerView = view.findViewById(R.id.recyclerView_rooms);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void openCreateRoomFragment()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fragment_content,new CreateRoomFragment(),"rooms").addToBackStack("rooms").commit();
    }
    @Override
    public void onResume()
    {
        super.onResume();
        viewModelWrapper.updateRooms();
    }
}
