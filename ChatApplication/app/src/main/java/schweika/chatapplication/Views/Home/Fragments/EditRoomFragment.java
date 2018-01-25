package schweika.chatapplication.Views.Home.Fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schweika.chatapplication.R;

public class EditRoomFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Long id = getArguments().getLong("id");

        return inflater.inflate(R.layout.fragment_edit_room, container, false);
    }
}
