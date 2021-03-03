package pl.training.runkeeper.commons.views

import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.Button
import android.widget.PopupWindow
import pl.training.runkeeper.R

class DialogBox {

    fun show(context: Context, parent: View) {
        val inflater = context.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.dialog_box, null)
        val popup = PopupWindow(view, 600, MATCH_PARENT, true)
        view.findViewById<Button>(R.id.dialog_box_ok).setOnClickListener { popup.dismiss() }
        popup.showAtLocation(parent, Gravity.CENTER, 0, 0)
    }

}