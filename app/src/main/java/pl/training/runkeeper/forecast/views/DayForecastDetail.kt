package pl.training.runkeeper.forecast.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import pl.training.runkeeper.R

class DayForecastDetail(context: Context, attributes: AttributeSet) : ConstraintLayout(context, attributes) {

    private val image: ImageView
    private val text: TextView

    init {
        inflate(context, R.layout.view_day_forecast_detail, this)
        val settings = context.obtainStyledAttributes(attributes, R.styleable.DayForecastDetail)
        image = findViewById(R.id.day_details_image)
        text = findViewById(R.id.day_details_text)
        image.setImageDrawable(settings.getDrawable(R.styleable.DayForecastDetail_image))
        text.text = settings.getString(R.styleable.DayForecastDetail_text)
        settings.recycle()
    }

    fun setText(newText: String) {
        text.text = newText
    }

    fun setImage() {

    }

}