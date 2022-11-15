package space.nurik.note_spy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class DiscripTV1 extends AppCompatActivity {

    private EditText edit_name;
    private EditText edit_message;
    private TextView text_message;
    private Button save;
    private Button button_back;
    private Button shifer;
    private Button re_shifer;
    private String FILE_text_message;
    private String FILE_edit_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tv_1_discrip);

        edit_name = findViewById(R.id.edit_name);
        save = findViewById(R.id.save);
        shifer = findViewById(R.id.shifer);
        re_shifer = findViewById(R.id.re_shifer);
        text_message = findViewById(R.id.text_message);
        edit_message = findViewById(R.id.edit_message);
        //button = findViewById(R.id.button);\

        Bundle arguments = getIntent().getExtras();

        String fileName = arguments.get("fileName").toString();
        edit_name.setText(fileName.substring(0, fileName.lastIndexOf('.')));


        try {
            openText(text_message, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        openEdit(edit_message);
        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveText_message(text_message);
                //saveEdit_message(edit_message);
            }
        });
        Button shifer = (Button) findViewById(R.id.shifer);
        shifer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base();
                showResult();
                blockKlava();
                shifer.setTextColor(Color.WHITE);
                re_shifer.setTextColor(Color.BLACK);

            }
        });
        Button re_shifer = (Button) findViewById(R.id.re_shifer);
        re_shifer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                base64();
                dimesResult();
                blockKlava();
                shifer.setTextColor(Color.BLACK);
                re_shifer.setTextColor(Color.WHITE);

            }
        });
        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DiscripTV1.this, MainActivity.class);
                startActivity(intent);finish();
            }
        });

//        Timer myTimer;
//        myTimer = new Timer();
//        myTimer.schedule(new TimerTask() {
//            public void run() {
//                timerTick();
//            }
//        }, 0, 2000); // каждые 2 секунд

//        String editTextValue = "Нурик хеллоу";
//
//        byte[] encrpt= new byte[0];
//        try {
//            encrpt = editTextValue.getBytes("UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String base64 = Base64.encodeToString(encrpt, Base64.DEFAULT);
/////////////////////////////////
//        byte[] decrypt= Base64.decode(base64, Base64.DEFAULT);
//        String text;
//        try {
//            text = new String(decrypt, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

    }
    private void showResult(){
        text_message.setVisibility(View.VISIBLE);
        edit_message.setVisibility(View.GONE);

    }
    private void dimesResult(){
        text_message.setVisibility(View.GONE);
        edit_message.setVisibility(View.VISIBLE);

    }
    private void blockKlava(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void base64(){
        String name = text_message.getText().toString();
        byte[] decrypt= Base64.decode(name, Base64.DEFAULT);
        String text = null;
        try {
            text = new String(decrypt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        edit_message.setText(text);
    }
    private void base(){
        String name = edit_message.getText().toString();

        byte[] encrpt= new byte[0];
        try {
            encrpt = name.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String base64 = Base64.encodeToString(encrpt, Base64.DEFAULT);
        text_message.setText(base64);
    }



//    private void timerTick() {
//        this.runOnUiThread(doTask);
//    }
//    private Runnable doTask = new Runnable() {
//        public void run() {
//            String name = edit_message.getText().toString();
//
//            byte[] encrpt= new byte[0];
//            try {
//                encrpt = name.getBytes("UTF-8");
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
//            String base64 = Base64.encodeToString(encrpt, Base64.DEFAULT);
//            text_message.setText(base64);
//
//        }
//    };

    // сохранение файла
    public void saveText_message(View view){

        FileOutputStream fos = null;
        try {
            base();
            String text = text_message.getText().toString();
            String text_name = edit_name.getText().toString();

            String path = Environment.getExternalStorageDirectory().toString()+"/Documents/notespy/";

            File directory = new File(path);
            directory.mkdirs();
            File outputFile = new File(directory, text_name + ".txt");

            Bundle arguments = getIntent().getExtras();
            String fileName = arguments.get("fileName").toString();
//            edit_name.setText(fileName.substring(0, fileName.lastIndexOf('.')));
//            FileOutputStream fos = new FileOutputStream(outputFile);
//            fos = new openFileOutput(outputFile, MODE_PRIVATE);

            //Path path = Paths.get(data.getData().getPath()).getParent());
//            String fileName = path.getName().toString();
            File from = new File(path, fileName);
            File to = new File(path,text_name + ".txt");
            from.renameTo(to);


            fos = new FileOutputStream(outputFile);
            fos.write(text.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{
            try{
                if(fos!=null)
                    fos.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
//    public void saveEdit_message(View view){
//
//        FileOutputStream fos = null;
//        try {
//            String text = edit_message.getText().toString();
//            String text_name = edit_name.getText().toString();
//            fos = openFileOutput(text_name, MODE_PRIVATE);
//            fos.write(text.getBytes());
//            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//        }
//        catch(IOException ex) {
//
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        finally{
//            try{
//                if(fos!=null)
//                    fos.close();
//            }
//            catch(IOException ex){
//
//                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

    public static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }
    // открытие файла
    public void openText(View view, String fileName) throws Exception {

        FileInputStream fin = null;
        try {
            String path = Environment.getExternalStorageDirectory().toString()+"/Documents/notespy/" + fileName;

            File fl = new File(path);
            fin = new FileInputStream(fl);
            String ret = convertStreamToString(fin);
            //Make sure you close all streams.
            fin.close();
            text_message.setText(ret);
        }
        catch(IOException ex) {

            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
        finally{

            try{
                if(fin!=null)
                    fin.close();
            }
            catch(IOException ex){

                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

//    public void openEdit(View view){
//
//        FileInputStream fin = null;
//        try {
//            fin = openFileInput(FILE_edit_message);
//            byte[] bytes = new byte[fin.available()];
//            fin.read(bytes);
//            String text = new String (bytes);
//            edit_message.setText(text);
//        }
//        catch(IOException ex) {
//
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        finally{
//
//            try{
//                if(fin!=null)
//                    fin.close();
//            }
//            catch(IOException ex){
//
//                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }

//    public void saveText(View view){
//        FileOutputStream fos = null;
//        try {
//            String text1 = text_message.getText().toString();
//            String text2 = edit_message.getText().toString();
//            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
//            fos.write(text1.getBytes());
//            fos.write(text2.getBytes());
//            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
//        }
//        catch(IOException ex) {
//
//            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//        finally{
//            try{
//                if(fos!=null)
//                    fos.close();
//            }
//            catch(IOException ex){
//
//                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }
//    }


}
