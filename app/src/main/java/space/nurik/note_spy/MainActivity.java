package space.nurik.note_spy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {


//    String[] countries = { "Бразилия", "Аргентина", "Колумбия", "Чили", "Уругвай"};
    ArrayList<DocName> states = new ArrayList<DocName>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView plus = (ImageView) findViewById(R.id.plus);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Discrip.class);
                startActivity(intent);finish();
            }
        });


        setInitialData();
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        // определяем слушателя нажатия элемента в списке
        DocAdapter.OnStateClickListener stateClickListener = new DocAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(DocName state, int position) {
//                    Toast.makeText(getApplicationContext(), "Был выбран пункт " + state.getName(),
//                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, DiscripTV1.class);
                    intent.putExtra("fileName", state.getName() + ".txt");
                    startActivity(intent);finish();
            }
        };
        // создаем адаптер
        DocAdapter adapter = new DocAdapter(this, states, stateClickListener);
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);


        // получаем элемент ListView
//        ListView countriesList = findViewById(R.id.countriesList);
//
//        // создаем адаптер
//        ArrayAdapter<String> adapter = new ArrayAdapter(this,
//                android.R.layout.simple_list_item_1, countries);
//
//        // устанавливаем для списка адаптер
//        countriesList.setAdapter(adapter);



    }
    private void setInitialData(){

        String path = Environment.getExternalStorageDirectory().toString()+"/Documents/notespy";
        File directory = new File(path);
        directory.mkdirs();

        File[] files = directory.listFiles();

        for (int i = 0; i < files.length; i++)
        {
            String fileName = files[i].getName();
//            TextView tv_1 = (TextView) findViewById(R.id.tv_1);
            states.add(new DocName (fileName.substring(0, fileName.lastIndexOf('.'))));
//            tv_1.setText(fileName.substring(0, fileName.lastIndexOf('.')));

//            tv_1.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, DiscripTV1.class);
//                    intent.putExtra("fileName", fileName);
//                    startActivity(intent);finish();
//
//                }
//            });
        }


//        states.add(new DocName ("Бразилия3"));


    }

}