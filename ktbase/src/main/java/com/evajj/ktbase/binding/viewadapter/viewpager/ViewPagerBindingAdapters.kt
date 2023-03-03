package com.evajj.ktbase.binding.viewadapter.viewpager

import androidx.databinding.BindingAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.evajj.ktbase.binding.command.BindingCommand

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:下午5:41
 * Description:
 **/
@BindingAdapter(
    value = ["onPageScrolledCommand", "onPageSelectedCommand", "onPageScrollStateChangedCommand"],
    requireAll = false
)
fun onScrollChangeCommand(
    viewPager: ViewPager,
    onPageScrolledCommand: BindingCommand<ViewPagerDataWrapper?>?,
    onPageSelectedCommand: BindingCommand<Int?>?,
    onPageScrollStateChangedCommand: BindingCommand<Int?>?
) {
    viewPager.addOnPageChangeListener(object : OnPageChangeListener {
        private var state = 0
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            if (onPageScrolledCommand != null) {
                onPageScrolledCommand.execute(
                  ViewPagerDataWrapper(
                        position.toFloat(),
                        positionOffset,
                        positionOffsetPixels,
                        state
                    )
                )
            }
        }

        override fun onPageSelected(position: Int) {
            if (onPageSelectedCommand != null) {
                onPageSelectedCommand.execute(position)
            }
        }

        override fun onPageScrollStateChanged(state: Int) {
            this.state = state
            if (onPageScrollStateChangedCommand != null) {
                onPageScrollStateChangedCommand.execute(state)
            }
        }
    })
}
class ViewPagerDataWrapper(
    var position: Float,
    var positionOffset: Float,
    var positionOffsetPixels: Int,
    var state: Int
)