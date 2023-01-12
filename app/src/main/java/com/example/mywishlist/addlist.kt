package com.example.mywishlist

import android.Manifest
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.view.View
import android.webkit.PermissionRequest
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.IOException
import java.util.*

class addlist : AppCompatActivity() {
    companion object {
        private const val CAMERA_PERMISSION_CODE = 1
        private const val CAMERA_REQUIRED_CODE = 2
        private const val GALLERY = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addlist)
        supportActionBar?.hide()
    }

    fun back(view: View) {
        val intent= Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun save(view: View) {
        Snackbar.make(view,"Saving",Snackbar.LENGTH_SHORT).show()
    }


    fun location(view: View) {
        Snackbar.make(view,"Location",Snackbar.LENGTH_SHORT).show()

    }

    fun date(view: View) {
        var dates=findViewById<TextView>(R.id.dates)
        val myCalandar = Calendar.getInstance()
        val year=myCalandar.get(Calendar.YEAR)
        val month=myCalandar.get(Calendar.MONTH)
        val day = myCalandar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                view, Syear, Smonth, Sdayofmonth ->
            val selected="$Sdayofmonth/${Smonth+1}/${Syear}"
            dates.setText(selected)
        }
            ,year
            ,month
            ,day).show()
    }



    fun addimage(view: View) {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Gallery", "Camera")
        pictureDialog.setItems(pictureDialogItems)
        { dialogs, which ->
            when (which) {
                0 -> choosePhotofromGalary()
                1 -> Opencamera()
            }
        }
        pictureDialog.show()
    }


    private fun Opencamera() {
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_REQUIRED_CODE)
        } else {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.CAMERA),
                CAMERA_PERMISSION_CODE
            )
        }

    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== GALLERY)
        {
            if(data!=null)
            {
                val contentURI=data.data
                try {
                    val selectedimageBitmape=
                        MediaStore.Images.Media.getBitmap(this.contentResolver,contentURI)
                    //di.setImageBitmap(selectedimageBitmape)
                }catch (e: IOException)
                {
                    e.printStackTrace()
                    Toast.makeText(this@addlist,"Sorry,failed to load", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun choosePhotofromGalary() {
        Dexter.withActivity(this).withPermissions(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ).withListener(object : MultiplePermissionsListener {
            override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                if (report?.areAllPermissionsGranted() == true) {
                    val galleryIntent =
                        Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                    startActivityForResult(galleryIntent, GALLERY)
                }

            }

            override fun onPermissionRationaleShouldBeShown(
                p0: MutableList<com.karumi.dexter.listener.PermissionRequest>?,
                token: PermissionToken?
            ) {
                showRDforpermissions()
            }
        }).onSameThread().check()

    }

    private fun showRDforpermissions() {
        AlertDialog.Builder(this)
            .setMessage("its look like you have turned off permission required fpr this feature.It can be enabled under the Application Settings")
            .setPositiveButton("Allow from setting")
            { _, _ ->
                try {
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("Package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                }
            }.setNegativeButton("Cancel")
            { dialog, _ ->
                dialog.dismiss()
            }.show()
    }
}