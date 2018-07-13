package akshay.filemanager

import android.Manifest
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        var status = ContextCompat.checkSelfPermission(this@MainActivity,android.Manifest.permission.READ_EXTERNAL_STORAGE)
        if (status==PackageManager.PERMISSION_GRANTED)
        {
            readFile()
        }
        else{
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),123)
        }

    }

    fun readFile(){
        var path = "/storage/emulated/0/"
        var file = File(path)

        if(!file.exists()){
           path = "/storage/sdcard0/"
           file =File(path)
        }
            var values = file.list()
            var mya = ArrayAdapter<String>(this@MainActivity,android.R.layout.simple_list_item_single_choice,values)
            lv.adapter = mya

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
            readFile()
        }
        else{
            Toast.makeText(this@MainActivity,"Give Permission Before Use",Toast.LENGTH_LONG).show()
        }
    }



}
