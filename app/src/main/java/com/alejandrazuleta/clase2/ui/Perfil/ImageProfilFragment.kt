package com.alejandrazuleta.clase2.ui.Perfil

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentResolver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.webkit.MimeTypeMap
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment

import com.alejandrazuleta.clase2.R
import kotlinx.android.synthetic.main.fragment_image_profil.view.*

class ImageProfilFragment : DialogFragment() {

    // Use this instance of the interface to deliver action events
    internal lateinit var listener: NoticeDialogListener
    internal lateinit var imageUri : Uri

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor", "WrongConstant")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;
            val view = inflater.inflate(R.layout.fragment_image_profil, null)

            view.imageView5.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar1)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView6.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar2)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView7.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar3)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView8.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar4)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView9.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar5)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView10.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar6)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView11.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                imageUri = Uri.parse("android.resource://com.alejandrazuleta.clase2/" + R.drawable.avatar7)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView12.setBackgroundColor(Color.TRANSPARENT)
            }
            view.imageView12.setOnClickListener {
                it.setBackgroundColor(R.color.colorAccent)
                view.imageView5.setBackgroundColor(Color.TRANSPARENT)
                view.imageView7.setBackgroundColor(Color.TRANSPARENT)
                view.imageView8.setBackgroundColor(Color.TRANSPARENT)
                view.imageView9.setBackgroundColor(Color.TRANSPARENT)
                view.imageView10.setBackgroundColor(Color.TRANSPARENT)
                view.imageView11.setBackgroundColor(Color.TRANSPARENT)
                view.imageView6.setBackgroundColor(Color.TRANSPARENT)
                //check runtime permission
                if((activity!!.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) != PackageManager.PERMISSION_GRANTED){
                    this.requestPermissions(
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        1001
                    )
                    return@setOnClickListener
                }else{
                    fileChooser()
                }
            }

            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view)
                .setTitle(R.string.seleccionimagen)
                .setPositiveButton(R.string.guardar,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Send the positive button event back to the host activity
                        listener.imageUriSelected(imageUri)
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(R.string.cancelar,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Send the negative button event back to the host activity
                        listener.onDialogNegativeClick(this)
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            1001 -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    fileChooser()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(activity!!.applicationContext, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun fileChooser() {
        val intent : Intent =Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(intent, 1000)
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == 1000
            && data != null && data.data != null){
            imageUri = data.data!!
        }
    }

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
        fun imageUriSelected(imUriSelected: Uri)
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = targetFragment as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface, throw exception
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }


}

