
package com.uleth.flickrbrowser;

        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.design.widget.FloatingActionButton;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;
        import android.os.Bundle;
        import com.squareup.picasso.Picasso;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.InputStream;

public class UploadActivity extends BaseActivity {



    private static final int IMAGE_GALLERY_REQUEST = 88;
    //private ImageView imgPicture;
    ImageView imgPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        activateToolbar(true);

        imgPicture = (ImageView) findViewById(R.id.imgPic);

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



        return super.onOptionsItemSelected(item);
    }

    public void uploadClicked(View v) {
        //Intent intent = getIntent();
        Intent pickPhoto = new Intent(Intent.ACTION_PICK);
        //this is where to find thh image
        File pictureFromGallery = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureFromGallery.getPath();

        Uri data = Uri.parse(pictureDirectoryPath);

        pickPhoto.setDataAndType(data, "image/*");

        startActivityForResult(pickPhoto, IMAGE_GALLERY_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {

            Intent intent = new Intent(this, EditInfoActivity.class);
            startActivity(intent);

            if (requestCode == IMAGE_GALLERY_REQUEST) {
                Uri imageUri = data.getData();

                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    imgPicture.setImageBitmap(image);
                    Toast.makeText(UploadActivity.this, "Image selected", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to display image", Toast.LENGTH_LONG).show();

                }


            }
        }
    }
}