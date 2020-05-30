package ravid7.github.loco.Utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class DownloadHelper(val context: Context, val imgView: ImageView) : AsyncTask<String, Void, Bitmap?>() {

    var error: String = "x"
    var isThere = true
    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun doInBackground(vararg params: String?): Bitmap? {
        var bmp: Bitmap? = null
        try {
            val connection: HttpURLConnection = URL(params[0]).openConnection() as HttpURLConnection
            connection.connect()
            val inputCode: Int = connection.responseCode
            if(inputCode == HttpURLConnection.HTTP_OK){
                val inputStream: InputStream = connection.inputStream
                bmp = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
            }
        }
        catch (e: Exception){
            isThere = false
            Log.e("Bitch", e.message)
            error.plus(e.message)
        }
        return bmp;
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if(!isThere){
            Toast.makeText(context, error, Toast.LENGTH_LONG).show()
        }
        else{
            imgView.setImageBitmap(result)
            val arr : IntArray = colorFromJNI(result!!)
            if(arr.size > 1){
                val x: String = "width ${arr[0]} height ${arr[1]}"
                Toast.makeText(context, x, Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(context, "failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private external fun colorFromJNI(bitmap: Bitmap) : IntArray

    companion object {
        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
        }
    }
}