package schweika.chatapplication.Views.Home.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import schweika.chatapplication.R;

public class FriendsFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_friends, container, false);

        FloatingActionButton button = view.findViewById(R.id.floatingActionButton_addFriend);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                openAddFriendFragment();
            }
        });

        return view;
    }

    public void openAddFriendFragment()
    {
        FragmentManager manager = getActivity().getSupportFragmentManager();

        //FragmentManager manager = getChildFragmentManager();

        manager.beginTransaction().replace(R.id.fragment_content, new AddFriendFragment(),"friends").addToBackStack(null).commit();
    }
}
