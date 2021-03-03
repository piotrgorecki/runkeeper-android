package pl.training.runkeeper.commons.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import pl.training.runkeeper.R

class ImageBox(context: Context, attributes: AttributeSet) : LinearLayout(context, attributes) {

    private val image: ImageView
    private val text: TextView

    init {
        inflate(context, R.layout.view_image_box, this)
        val settings = context.obtainStyledAttributes(attributes, R.styleable.ImageBox)
        image = findViewById(R.id.box_image)
        text = findViewById(R.id.box_title)
        image.setImageDrawable(settings.getDrawable(R.styleable.ImageBox_image))
        text.text = settings.getString(R.styleable.ImageBox_text)
        settings.recycle()
    }

    fun setOnClickListener(handler: () -> Unit) {
        image.setOnClickListener {
            handler()
        }
    }

}