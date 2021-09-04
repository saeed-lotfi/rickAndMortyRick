package com.saeedlotfi.rickandmortyrick.cv

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.saeedlotfi.rickandmortyrick.R

class BaseLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var progressBar: ProgressBar

    init {
        inflate(context, R.layout.base_layout, this)

        progressBar = findViewById(R.id.progressBar)
    }


    fun setProgressVisibility(isVisible: Boolean) {
        if (isVisible)
            progressBar.visibility = View.VISIBLE
        else
            progressBar.visibility = View.INVISIBLE
    }

}