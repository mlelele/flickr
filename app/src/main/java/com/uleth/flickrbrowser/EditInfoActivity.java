package com.uleth.flickrbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;


public class EditInfoActivity extends BaseActivity {

    EditText Input_tag, Input_author, Input_title;
    //EditText input1, input2, input3;

    Button Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        activateToolbar(true);

        Input_tag = (EditText) findViewById(R.id.Input_tag);
        Input_tag.setText("");
        Input_author = (EditText) findViewById(R.id.Input_author);
        Input_author.setText("");
        Input_title = (EditText) findViewById(R.id.Input_title);
        Input_title.setText("");


        String input1 = Input_tag.getText().toString();
        Input_tag.setText(input1);

        String input2 = Input_author.getText().toString();
        Input_author.setText(input2);

        String input3 = Input_title.getText().toString();
        Input_title.setText(input3);

     /*  ImageView imageView = (ImageView) findViewById(R.id.imgpic);
      //  ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.pic);
        setContentView(imageView);*/
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
        if (id == R.id.action_upload) {
            Intent intent = new Intent(this, UploadActivity.class);
            startActivity(intent);
            //true means you dealt with the action, so this a test
            return true;
        }

        if (id == R.id.action_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void sendEditInfo(View v){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(EditInfoActivity.this, "Image has been uploaded", Toast.LENGTH_SHORT).show();

    }
}