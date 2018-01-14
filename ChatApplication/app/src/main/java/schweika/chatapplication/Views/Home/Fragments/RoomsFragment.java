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
import java.util.Timer;
import java.util.TimerTask;

import schweika.chatapplication.Models.API.Room;
import schweika.chatapplication.R;
import schweika.chatapplication.GenericRecyclerViewAdapter;
import schweika.chatapplication.ViewModels.HomeViewModel;
import schweika.chatapplication.ViewModels.Interfaces.RoomViewModelListener;
import schweika.chatapplication.ViewModels.RoomViewModel;

public class RoomsFragment extends Fragment implements RoomViewModelListener
{
    private RecyclerView recyclerView;
    private GenericRecyclerViewAdapter<RoomViewModel> adapter;
    private HomeViewModel viewModelWrapper;
    private Timer timer;
    private TimerTask timerTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_rooms, container, false);

        initialize(view);

        return view;
    }

    private void initialize(View view)
    {
        FloatingActionButton button = view.findViewById(R.id.floatingActionButton_createRoom);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openCreateRoomFragment();
            }
        });

        if (viewModelWrapper == null)
        {
            viewModelWrapper = ViewModelProviders.of(getActivity()).get(HomeViewModel.class);

            viewModelWrapper.rooms.observe(this, new Observer<ArrayList<Room>>()
            {
                @Override
                public void onChanged(@Nullable ArrayList<Room> rooms)
                {
                    adapter.setItems(getRoomViewModels(rooms));
                }
            });
        }

        if (adapter == null)
            adapter = new GenericRecyclerViewAdapter<>(getRoomViewModels(viewModelWrapper.rooms.getValue()),R.layout.recycler_view_room);

        recyclerView = view.findViewById(R.id.recyclerView_rooms);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        this.timer = new Timer();

        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                viewModelWrapper.updateRooms();
            }
        };

        timer.schedule(timerTask,0,5000);
    }

    private void openCreateRoomFragment()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        manager.beginTransaction().replace(R.id.fragment_content,new CreateRoomFragment(),"rooms").addToBackStack("rooms").commit();
    }

    private ArrayList<RoomViewModel> getRoomViewModels(ArrayList<Room> rooms)
    {
        ArrayList<RoomViewModel> roomViewModels = new ArrayList<>();

        for (Room room : rooms)
        {
            RoomViewModel roomViewModel = new RoomViewModel(room,this);
            roomViewModels.add(roomViewModel);
        }

        return roomViewModels;
    }

    @Override
    public void onEnterRoom(RoomViewModel roomViewModel)
    {
        openRoomChatFragment(roomViewModel.room.id);
    }

    @Override
    public void onRemoveRoom(RoomViewModel roomViewModel)
    {
        this.adapter.removeItem(roomViewModel);
        this.viewModelWrapper.rooms.getValue().remove(roomViewModel.room);
    }

    @Override
    public void onEditRoom(RoomViewModel roomViewModel)
    {

    }

    @Override
    public void onPause()
    {
        timer.cancel();
        timer.purge();

        super.onPause();
    }

    private void openRoomChatFragment(long roomID)
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        RoomChatFragment fragment = new RoomChatFragment();

        Bundle bundle = new Bundle();
        bundle.putLong("id",roomID);
        fragment.setArguments(bundle);

        manager.beginTransaction().replace(R.id.fragment_content,fragment,"rooms").addToBackStack("rooms").commit();
    }
}
