package com.uleth.flickrbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void sendEditInfo(View v){
        Intent intent= new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast.makeText(EditInfoActivity.this, "Image has been uploaded", Toast.LENGTH_SHORT).show();

    }
}