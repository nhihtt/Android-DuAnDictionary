package team1kdictionary.com.onekdictionary.manhinhchinh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import team1kdictionary.com.model.MyCustomDialog;
import team1kdictionary.com.model.Word;
import adapter.WordAdapter;
import team1kdictionary.com.onekdictionary.R;
import team1kdictionary.com.onekdictionary.databinding.ActivityMainBinding;
import team1kdictionary.com.onekdictionary.databinding.CustomDialogBinding;

public class MainActivity extends AppCompatActivity {
    String DATABASE_NAME="TuDienAnhviet.sqlite";
    String DB_PATH_SUFFIX="/databases/";
    SQLiteDatabase database=null;
    ActivityMainBinding binding;
    CustomDialogBinding customDialogBinding;
    GridView gvDic;
    WordAdapter allWordAdapter;
    public static String compareWord = "";

    public static List<String> tuDaTimKiem=new ArrayList<>();
    public static List<Word> itemsWordList = new ArrayList<>();
    public static List<Word> tempList = new ArrayList<>();

    public static Word itemSelected;
    ArrayList<String> text_matched;
    public static MyCustomDialog myDialog;

    public static int RECOGNIZER_RESULT = 1;
    public static String SPEECH_TO_TEXT = "";
    public static Intent speechIntent;
    private static SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        changeActivity();
        processCopy();
        addControls();
        addEvents();
    }

    private void addEvents() {
        try {
            displayWordList();
            voiceRecognization();
            checkPronounce();
        } catch (Exception ex) {
            Toast.makeText(this, "Error " + ex.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void checkPronounce() {

    }

    private void voiceRecognization() {
        speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Đang lắng nghe...");
        speechIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 5);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK && data != null) {
            text_matched = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            SPEECH_TO_TEXT = text_matched.get(0).toString();
            searchView.setFocusable(true);
            searchView.setIconified(false);
            searchView.requestFocusFromTouch();
            searchView.setQuery(SPEECH_TO_TEXT, false);
            TextView txtScore = myDialog.findViewById(R.id.txtScore);
            String text = "";
            for (String result : text_matched) {
                text += result + " ";
            }
//            txtScore.setText(text);
            if (spaceCount(text) == 0 && text.contains(compareWord)) {
                txtScore.setText("100% RẤT TỐT");
            } else if (spaceCount(text) == 1 && text.contains(compareWord)) {
                txtScore.setText("80% TỐT");
            } else if (spaceCount(text) == 2 && text.contains(compareWord)) {
                txtScore.setText("60% TẠM ỔN");
            } else if (spaceCount(text) == 3 && text.contains(compareWord)) {
                txtScore.setText("50% HÃY TẬP LUYỆN THÊM");
            } else if (spaceCount(text) == 4 && text.contains(compareWord)) {
                txtScore.setText("30% HÃY TẬP LUYỆN THÊM");
            } else if (text.contains(compareWord) == false) {
                txtScore.setText("HÃY TẬP XEM LẠI PHÁT ÂM");
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private int spaceCount(String str) {
        int spaceCount = 0;
        for (char c : str.toCharArray()) {
            if (c == ' ') {
                spaceCount++;
            }
        }
        return spaceCount;
    }

    private void displayWordList() {
        itemsWordList.clear();
        database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        Cursor c = database.rawQuery("Select * From data Where _id > 56", null);
        while (c.moveToNext()) {
            String word = c.getString(1);
            String mean = c.getString(2);

            Word vocabulary = new Word(word, null, null, mean, null);
            itemsWordList.add(vocabulary);
//           allWordAdapter.add(vocabulary);

        }
        gvDic = findViewById(R.id.gvDic);
        gvDic.setAdapter(allWordAdapter);
        c.close();
    }

    private void addControls() {
        gvDic = findViewById(R.id.gvDic);
        allWordAdapter = new WordAdapter(MainActivity.this, R.layout.word_item, itemsWordList);
        gvDic.setAdapter(allWordAdapter);
//        setWordForDialog(itemsWordList);
        MyCustomDialog.setWordForDialog(itemsWordList, gvDic, MainActivity.this);

    }


    private void changeActivity() {
        //Set Home Selected
        binding.navBottom.setSelectedItemId(R.id.home);
        //Perform ItemSelectedListener
        binding.navBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case  R.id.home:
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.favorite:
                        startActivity(new Intent(getApplicationContext(), FavoriteActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.history:
                        startActivity(new Intent(getApplicationContext(), HistoryActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                    case  R.id.setting:
                        startActivity(new Intent(getApplicationContext(), SettingActivity.class));
                        finish();
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mnsearch) {
            return true;
        }
        if (id == R.id.mnvoice) {
            startActivityForResult(speechIntent, RECOGNIZER_RESULT);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater= getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);

        final MenuItem mnSearch = menu.findItem(R.id.mnsearch);
        searchView = (SearchView) mnSearch.getActionView();
        searchView.setQueryHint("Nhập từ tìm kiếm ở đây");
        searchView.setQuery(SPEECH_TO_TEXT, false);
        searchView.setIconifiedByDefault(false);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try{
                    //Lưu từ đã search vào history
                    database = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
                    database.execSQL("Update data SET history=? Where word=?", new String[]{"1", query});
                    tuDaTimKiem.add(query);
                    for (int i = 0; i < itemsWordList.size(); i++) {
                        if (itemsWordList.get(i).getEng().startsWith(query)) {
                            tempList.add(itemsWordList.get(i));
//                            Toast.makeText(MainActivity.this, "Lưu thành công", Toast.LENGTH_SHORT).show();
                        }
                    }
//                    setWordForDialog(tempList);
                    MyCustomDialog.setWordForDialog(tempList, gvDic, MainActivity.this);
                }
                catch (Exception ex){
                    Log.e("LOI",ex.toString());
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                allWordAdapter.getFilter().filter(s);
                if (s.compareTo("") == 0) {
                    MyCustomDialog.setWordForDialog(itemsWordList, gvDic, MainActivity.this);
                } else {
                    MyCustomDialog.setWordForDialog(tempList, gvDic, MainActivity.this);
                }
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void processCopy(){
        try {
            File dbFile = getDatabasePath(DATABASE_NAME);
            if (!dbFile.exists()) {
                copyDatabaseFromAsset();
                Toast.makeText(MainActivity.this,"Sao chép cơ sở dữ liệu vào hệ thống điện thoại thành công",
                        Toast.LENGTH_LONG).show();
            }
        }
        catch (Exception ex){
            Toast.makeText(MainActivity.this,ex.toString(),Toast.LENGTH_LONG).show();
            Log.e("LOI",ex.toString());
        }
    }
    private String getDatabasePath(){
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }
    private void copyDatabaseFromAsset() {
        try{
            InputStream myInput=getAssets().open(DATABASE_NAME);
            String outFileName=getDatabasePath();
            File f=new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            if(!f.exists()){
                f.mkdir();
            }
            OutputStream myOutput=new FileOutputStream(outFileName);
            byte []buffer=new byte[1024];
            int length;
            while ((length=myInput.read(buffer))>0){
                myOutput.write(buffer,0,length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex){
            Log.e("LOI",ex.toString());
        }
    }

}
