package schweika.chatapplication.Views.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import schweika.chatapplication.R;
import schweika.chatapplication.Views.Home.Fragments.FriendsFragment;
import schweika.chatapplication.Views.Home.Fragments.RoomsFragment;
import schweika.chatapplication.Views.LoggedOff.LoggedOffActivity;

public class HomeActivity extends AppCompatActivity
{

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
                case R.id.navigation_friendRequests:
                    return true;
            }
            return false;
        }
    };

    private void switchToRoomsFragment()
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_content, new RoomsFragment()).commit();
    }

    private void switchToFriendsFragment()
    {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.fragment_content, new FriendsFragment()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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

        Intent intent = new Intent(this, LoggedOffActivity.class);
        startActivity(intent);

        finish();
    }

    @Override
    public void onBackPressed()
    {
        finishAffinity();
    }

}
