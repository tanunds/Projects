package com.example.a8puzzle

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SettingDialogFragment(var size:Int):DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)
            .setTitle("Define size")
            .setSingleChoiceItems(R.array.size_options, size-2){
                dialog,which ->
                size = which + 2
            }
            .setPositiveButton("Change"){
                dialog,id->
                (getActivity() as MainActivity).changeSize(size)
            }
            .setNegativeButton("Cancel"){
                dialog,_-> dialog.cancel()
            }
        val settingDialog = builder.create()
        settingDialog.show()

        return settingDialog
    }
}