package com.example.saisandeep.externalstorageex;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    EditText editText;
    Button intCache,extCache,extPrivate,extPublic,nextB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText= (EditText) findViewById(R.id.editText);
        intCache= (Button) findViewById(R.id.button1);
        extCache= (Button) findViewById(R.id.button2);
        extPrivate= (Button) findViewById(R.id.button3);
        extPublic= (Button) findViewById(R.id.button4);
        nextB= (Button) findViewById(R.id.button5);
    }

    public void internalCache(View v){

        String data=editText.getText().toString();
        File myFolder=getCacheDir();
        File myFile=new File(myFolder,"mydata1.txt");
        writeData(myFile,data);

        //Toast.makeText(this, data+ " was written successfully "+myFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();


    }

    public void externalCache(View v){

        String data=editText.getText().toString();
        File myFolder=getExternalCacheDir();
        File myFile=new File(myFolder,"mydata2.txt");
        writeData(myFile,data);
    }

    public void externalPrivate(View v){

        String data=editText.getText().toString();
        File myFolder=getExternalFilesDir("Sandeep");
        File myFile=new File(myFolder,"mydata3.txt");
        writeData(myFile,data);
    }

    public void externalPublic(View v){

        String data=editText.getText().toString();
        File myFolder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile=new File(myFolder,"mydata4.txt");
        writeData(myFile,data);
    }

    public void next(View v){

        Intent i=new Intent(this,ActivityB.class);
        startActivity(i);
    }

    private void writeData(File myFile,String data){
        FileOutputStream fos=null;
        try {
            fos=new FileOutputStream(myFile);
            fos.write(data.getBytes());
            Toast.makeText(this, data+ " was written successfully "+myFile.getAbsolutePath(),Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
