package ravid7.github.loco

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ravid7.github.loco.Utils.DownloadHelper

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Example of a call to a native method
        val img : ImageView = findViewById(R.id.sample_img)
        DownloadHelper(this,  img).execute("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png");
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */

}
