package com.dasamarpreet.myaudioplayer;

import static com.dasamarpreet.myaudioplayer.MusicService.mediaPlayerid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
//
//This is the main file which is very important for this app. It has a lot of functions that the application performs. Like,
//        it is used to ask for the permission to Read the Storage, used to set the pages layout for all fragments like all songs,
//        albums, favourite and about us. Moreover, it is also used to set and display  the menu options on
//        each interface. At last but not the least, it has some other functions also which is needed to run the app smoothly.

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final int REQUEST_CODE = 1;
    static ArrayList<MusicFiles> musicFiles;
    static ArrayList<MusicFiles> albums = new ArrayList<>();
    static boolean shuffleBoolean = false, repeatBoolean = false;
    private String My_SORT_PREF = "SortOrder";
    public static final String MUSIC_LAST_PLAYED = "LAST_PLAYED";
    public static final String MUSIC_FILE = "STORED_MUSIC";
    public static boolean SHOW_MINI_PLAYER = false;
    public static String PATH_TO_FRAG = null;
    public static String SONG_NAME_TO_FRAG = null;
    public static String ARTIST_TO_FRAG = null;
    public static final String ARTIST_NAME = "ARTIST NAME";
    public static final String SONG_NAME = "SONG NAME";
    private long backPressedTime;
    private StyleableToast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAnimation();
        setContentView(R.layout.activity_main);
        permission();
    }

    private void permission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);
        } else {
            musicFiles = getAllAudio(this);
            initViewPager();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // DO whatever you want Permission related
                musicFiles = getAllAudio(this);
                initViewPager();
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_CODE);
            }
        }
    }

    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragments(new SongsFragment(), "Songs");
        viewPagerAdapter.addFragments(new AlbumFragment(), "Albums");
        viewPagerAdapter.addFragments(new FavoriteFragment(), "Fav");
        viewPagerAdapter.addFragments(new AboutFragment(), "About Us");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        void addFragments(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }

    public ArrayList<MusicFiles> getAllAudio(Context context) {
        SharedPreferences preferences = getSharedPreferences(My_SORT_PREF, MODE_PRIVATE);
        String sortOrder = preferences.getString("sorting", "sortByName");
        ArrayList<String> duplicate = new ArrayList<>();
        albums.clear();
        ArrayList<MusicFiles> tempAudioList = new ArrayList<>();
        String order = null;
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        switch (sortOrder) {
            case "sortByName":
                order = MediaStore.MediaColumns.DISPLAY_NAME + " ASC";
                break;
            case "sortByDate":
                order = MediaStore.MediaColumns.DATE_ADDED + " ASC";
                break;
            case "sortBySize":
                order = MediaStore.MediaColumns.SIZE + " DESC";
                break;
        }
        String[] projection = {
                MediaStore.Audio.Media.ALBUM,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.DATA, // for path
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media._ID
        };
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, order);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String album = cursor.getString(0);
                String title = cursor.getString(1);
                String duration = cursor.getString(2);
                String path = cursor.getString(3);
                String artist = cursor.getString(4);
                String id = cursor.getString(5);

                MusicFiles musicFiles = new MusicFiles(path, title, album, artist, duration, id);
                // take log.e for check
                Log.e("path: " + path, "Album: " + album);
                tempAudioList.add(musicFiles);
                if (!duplicate.contains(album)) {
                    albums.add(musicFiles);
                    duplicate.add(album);
                }
            }
            cursor.close();
        }
        return tempAudioList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);
        MenuItem menuItem = menu.findItem(R.id.search_menu);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        ArrayList<MusicFiles> myFiles = new ArrayList<>();
        for (MusicFiles song : musicFiles) {
            if (song.getTitle().toLowerCase().contains(userInput)) {
                myFiles.add(song);
            }
        }
        SongsFragment.musicAdapter.updateList(myFiles);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        SharedPreferences.Editor editor = getSharedPreferences(My_SORT_PREF, MODE_PRIVATE).edit();
        switch (item.getItemId()) {
            case R.id.by_name:
                editor.putString("sorting", "sortByName");
                editor.apply();
                this.recreate();
                break;

            case R.id.by_date:
                editor.putString("sorting", "sortByDate");
                editor.apply();
                this.recreate();
                break;

            case R.id.by_size:
                editor.putString("sorting", "sortBySize");
                editor.apply();
                this.recreate();
                break;

            case R.id.dark_theme:
                StyleableToast.makeText(getApplicationContext(), "Dark Theme Selected", Toast.LENGTH_SHORT, R.style.customToast).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;

            case R.id.light_theme:
                StyleableToast.makeText(getApplicationContext(), "Light Theme Selected", Toast.LENGTH_SHORT, R.style.customToast).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

            case R.id.system_theme:
                StyleableToast.makeText(getApplicationContext(), "System Default", Toast.LENGTH_SHORT, R.style.customToast).show();
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;

            case R.id.SourceCodeGitHub:
                String urlTextGit = "https://github.com/dasamarpreet/Rhyemful_Music_App_V1.0.0";
                StyleableToast.makeText(getApplicationContext(), "Redirecting to Github", Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpageGit = Uri.parse(urlTextGit);
                Intent intentGit = new Intent(Intent.ACTION_VIEW, webpageGit);
                startActivity(intentGit);
                break;

            case R.id.SourceCodeOneDrive:
                String urlTextOne = "https://cumailin-my.sharepoint.com/:f:/g/personal/19bca1124_" +
                        "cuchd_in/EqhI-0WN3VVBgI1sSymzGAsBfhNzC6nU4kWoOvyfDycPxw?e=oACfRt";
                StyleableToast.makeText(getApplicationContext(), "Redirecting to OneDrive", Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpageOne = Uri.parse(urlTextOne);
                Intent intentOne = new Intent(Intent.ACTION_VIEW, webpageOne);
                startActivity(intentOne);
                break;

            case R.id.SourceCodeGoogleDrive:
                String urlTextGoogle = "https://drive.google.com/drive/folders/" +
                        "1Bf4_M0j53hs5f0E53MUhS4MtOkmRio7S?usp=sharing";
                StyleableToast.makeText(getApplicationContext(), "Redirecting to G-Drive", Toast.LENGTH_SHORT, R.style.customToast).show();
                // Implicit Intent to open a web page
                Uri webpageGoogle = Uri.parse(urlTextGoogle);
                Intent intentGoogle = new Intent(Intent.ACTION_VIEW, webpageGoogle);
                startActivity(intentGoogle);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(MUSIC_LAST_PLAYED, MODE_PRIVATE);
        String path = preferences.getString(MUSIC_FILE, null);
        String artist = preferences.getString(ARTIST_NAME, null);
        String song_name = preferences.getString(SONG_NAME, null);
        if (path != null) {
            SHOW_MINI_PLAYER = true;
            PATH_TO_FRAG = path;
            ARTIST_TO_FRAG = artist;
            SONG_NAME_TO_FRAG = song_name;
        } else {
            SHOW_MINI_PLAYER = false;
            PATH_TO_FRAG = null;
            ARTIST_TO_FRAG = null;
            SONG_NAME_TO_FRAG = null;
        }
    }

    public void setAnimation() {
        if (Build.VERSION.SDK_INT > 20) {
            Slide slide = new Slide();
            slide.setSlideEdge(Gravity.TOP);
            slide.setDuration(300);
            slide.setInterpolator(new DecelerateInterpolator());
            getWindow().setExitTransition(slide);
            getWindow().setEnterTransition(slide);
        }
    }

    @Override
    public void onBackPressed() {
//        try {
            if (backPressedTime + 2000 > System.currentTimeMillis()){
                super.onBackPressed();
                backToast.cancel();
                mediaPlayerid.stop();
//                mediaPlayerid.release();
            }
            else{
                backToast = StyleableToast.makeText(getApplicationContext(), "Press again to close the App!", Toast.LENGTH_SHORT, R.style.customToast);
                backToast.show();
            }
            backPressedTime = System.currentTimeMillis();
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
    }
}
