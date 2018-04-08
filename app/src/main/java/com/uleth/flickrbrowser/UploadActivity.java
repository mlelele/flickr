
package com.uleth.flickrbrowser;

        import android.content.Intent;
        import android.content.res.Resources;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.os.Bundle;
        import android.os.Environment;
        import android.support.design.widget.FloatingActionButton;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.squareup.picasso.Picasso;
        import java.io.File;
        import java.io.FileNotFoundException;
        import java.io.InputStream;

public class UploadActivity extends BaseActivity {


    private static final int IMAGE_GALLERY_REQUEST = 88;
    //private ImageView imgPicture;
    ImageView imgPicture;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);
        activateToolbar(true);

        imgPicture = (ImageView) findViewById(R.id.imgPic);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                Uri imageUri = data.getData();

                InputStream inputStream;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);
                    Bitmap image = BitmapFactory.decodeStream(inputStream);
                    imgPicture.setImageBitmap(image);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to displayimage", Toast.LENGTH_LONG).show();

                }
            }

        }
    }
}