package ravid7.github.loco.Utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import android.widget.Toast
import java.io.InputStream
import java.lang.Exception

class DownloadHelper(val context: Context, val imgView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): Bitmap? {
        val url: String? = params[0]
        var bmp: Bitmap? = null
        try{
            val inputStream: InputStream = java.net.URL(url).openStream()
            bmp = BitmapFactory.decodeStream(inputStream)
        }
        catch (e: Exception){
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
        }
        return bmp;
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        imgView.setImageBitmap(result)
    }
}