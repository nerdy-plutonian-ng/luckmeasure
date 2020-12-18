package com.nerdymykl.luckmeasure

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class ReplayDialog(val percent : Int) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder : AlertDialog.Builder = AlertDialog.Builder(requireActivity())
        val inflater : LayoutInflater = requireActivity().layoutInflater
        val view  = inflater.inflate(R.layout.result_layout,null)
        val percentTV : TextView = view.findViewById(R.id.percentTV)
        val progressBar : ProgressBar = view.findViewById(R.id.progressbar)
        percentTV.text = (percent*10).toString()
        progressBar.progress = percent * 10

        builder.setTitle("Try again?")
                .setView(view)
                .setPositiveButton(android.R.string.ok,
                        DialogInterface.OnClickListener { dialog, id ->
                            // FIRE ZE MISSILES!
                            (requireActivity() as MeasureLuckActivity).resetGame()
                        })
                .setNegativeButton(android.R.string.cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            // User cancelled the dialog
                        })
        return builder.create()
    }
}