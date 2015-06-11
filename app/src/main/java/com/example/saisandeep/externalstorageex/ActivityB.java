package com.example.saisandeep.externalstorageex;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by saisandeep on 3/17/2015.
 */
public class ActivityB extends Activity {

    EditText editText;
    Button intCacheLoad,extCacheLoad,extPrivateLoad,extPublicLoad,prevA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityb);
        editText= (EditText) findViewById(R.id.editText1);
        intCacheLoad= (Button) findViewById(R.id.button6);
        extCacheLoad= (Button) findViewById(R.id.button7);
        extPrivateLoad= (Button) findViewById(R.id.button8);
        extPublicLoad= (Button) findViewById(R.id.button9);
        prevA= (Button) findViewById(R.id.button10);
    }

    public void internalCacheLoad(View v){

        File myFolder=getCacheDir();
        File myFile=new File(myFolder,"mydata1.txt");
        String data=readData(myFile);

        if(data != null)
        {
            editText.setText(data);
        }
        else
        {

            editText.setText("No data was returned");
        }

    }

    public void externalCacheLoad(View v){

        File myFolder=getExternalCacheDir();
        File myFile=new File(myFolder,"mydata2.txt");
        String data=readData(myFile);

        if(data != null)
        {
            editText.setText(data);
        }
        else
        {

            editText.setText("No data was returned");
        }
    }

    public void externalPrivateLoad(View v){

        File myFolder=getExternalFilesDir("Sandeep");
        File myFile=new File(myFolder,"mydata3.txt");
        String data=readData(myFile);

        if(data != null)
        {
            editText.setText(data);
        }
        else
        {

            editText.setText("No data was returned");
        }
    }

    public void externalPublicLoad(View v){

        File myFolder= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myFile=new File(myFolder,"mydata4.txt");
        String data=readData(myFile);

        if(data != null)
        {
            editText.setText(data);
        }
        else
        {

            editText.setText("No data was returned");
        }
    }

    public void previousLoad(View v){

        Intent in=new Intent(this,MainActivity.class);
        startActivity(in);
    }

    private String readData(File myFile)
    {

        FileInputStream fis=null;
        try {
            fis=new FileInputStream(myFile);

            int read=-1;
            StringBuffer sb=new StringBuffer();
            while((read= fis.read())!= -1)
            {

                sb.append((char)read);
            }

            return sb.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally
        {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
