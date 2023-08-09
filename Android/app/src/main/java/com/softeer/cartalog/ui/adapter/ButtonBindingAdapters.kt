package com.softeer.cartalog.ui.adapter

import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.fragment.app.FragmentContainerView
import com.softeer.cartalog.ui.activity.MainActivity

@BindingAdapter("activity", "index")
fun setOnClickTabBtn(
    button: AppCompatButton,
    activity: MainActivity,
    idx: Int
) {
    button.setOnClickListener {
        activity.changeTab(idx)
    }
}

@BindingAdapter("layout", "fragmentContainer")
fun setOnClickToggle(
    button: ImageButton,
    layout: ConstraintLayout,
    fragmentContainer: FragmentContainerView
) {

    button.setOnClickListener {
        if (layout.visibility == View.VISIBLE) {
            layout.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    layout.visibility = View.GONE
                    layout.translationY = 0f
                    fragmentContainer.setPadding(0,0,0,0)
                }
            button.animate().rotation(0f).start()
        } else {
            layout.visibility = View.VISIBLE
            layout.alpha = 0f
            layout.animate()
                .alpha(1f).duration = 300
            button.animate().rotation(180f).start()
            fragmentContainer.setPadding(0,150,0,0)
        }
    }
}