package schweika.chatapplication.Views.Home;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import schweika.chatapplication.R;
import schweika.chatapplication.ViewModels.HomeViewModel;
import schweika.chatapplication.Views.Home.Fragments.FriendRequestsFragment;
import schweika.chatapplication.Views.Home.Fragments.FriendsFragment;
import schweika.chatapplication.Views.Home.Fragments.RoomsFragment;
import schweika.chatapplication.Views.Login.LoginActivity;

public class HomeActivity extends AppCompatActivity
{
    //private RoomsFragment roomsFragment = new RoomsFragment();
    //private FriendsFragment friendsFragment = new FriendsFragment();

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_rooms:
                    switchToRoomsFragment();
                    return true;
                case R.id.navigation_friends:
                    switchToFriendsFragment();
                    return true;
                /*case R.id.navigation_friendRequests:
                    switchToRequestsFragment();
                    return true;*/
            }
            return false;
        }
    };

    private void switchToRoomsFragment()
    {


        FragmentManager manager = getSupportFragmentManager();

        //CreateRoomFragment fragment = (CreateRoomFragment) manager.findFragmentByTag("createRoom");

        Fragment fragment = manager.findFragmentByTag("rooms");

        if (fragment == null)
            fragment = new RoomsFragment();

        manager.beginTransaction().replace(R.id.fragment_content, fragment, "rooms").commit();

        /*if (fragment == null)
        {
            manager.beginTransaction().replace(R.id.fragment_content, roomsFragment,"rooms").commit();
        }
        else
        {
            manager.beginTransaction().replace(R.id.fragment_content, fragment, "rooms").commit();
        }*/
    }

    private void switchToFriendsFragment()
    {
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentByTag("friends");

        if (fragment == null)
            fragment = new FriendsFragment();

        manager.beginTransaction().replace(R.id.fragment_content, fragment, "friends").commit();
    }

    /*private void switchToRequestsFragment()
    {
        FragmentManager manager = getSupportFragmentManager();

        Fragment fragment = manager.findFragmentByTag("friendRequests");

        if (fragment == null)
            fragment = new FriendRequestsFragment();

        manager.beginTransaction().replace(R.id.fragment_content, fragment, "friendRequests").commit();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        HomeViewModel viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        switchToRoomsFragment();
    }

    public void logOut(View view)
    {
        SharedPreferences sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove("JWT");
        editor.apply();

        Intent intent = new Intent(this, LoginActivity.class);

        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager manager = getSupportFragmentManager();

        if (manager.getBackStackEntryCount() > 0)
        {
            manager.popBackStack();
        }
        else
        {
            finishAffinity();
        }
    }
}
