package com.example.marketplace.utils

import android.view.View
import android.widget.FrameLayout
import androidx.annotation.IdRes
import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.marketplace.R

class KeyboardView(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs), View.OnClickListener {

    private val _price = MutableLiveData<Char>()
    val price: LiveData<Char> = _price
    val builder = StringBuilder()

    init{
        inflate(context, R.layout.keyboard, this)
        initViews()
    }

    private fun initViews() {
//        mPasswordField = `$`(R.id.password_field)
        `$`<View>(R.id.done_view).setOnClickListener(this)
        `$`<View>(R.id.t9_key_0).setOnClickListener(this)
        `$`<View>(R.id.t9_key_1).setOnClickListener(this)
        `$`<View>(R.id.t9_key_2).setOnClickListener(this)
        `$`<View>(R.id.t9_key_3).setOnClickListener(this)
        `$`<View>(R.id.t9_key_4).setOnClickListener(this)
        `$`<View>(R.id.t9_key_5).setOnClickListener(this)
        `$`<View>(R.id.t9_key_6).setOnClickListener(this)
        `$`<View>(R.id.t9_key_7).setOnClickListener(this)
        `$`<View>(R.id.t9_key_8).setOnClickListener(this)
        `$`<View>(R.id.t9_key_9).setOnClickListener(this)
        `$`<View>(R.id.t9_key_clear).setOnClickListener(this)
        `$`<View>(R.id.t9_key_backspace).setOnClickListener(this)
    }

    override fun onClick(v: View) {
        if (v.tag != null && "number_button" == v.tag) {
            _price.value = (v as TextView).text[0]
            return
        }
        when (v.id) {
            R.id.t9_key_clear -> {
//                mPasswordField?.setText(null)
            }
            R.id.t9_key_backspace -> {
//                val editable = mPasswordField!!.text
//                val charCount = editable.length
//                if (charCount > 0) {
//                    editable.delete(charCount - 1, charCount)
//                }
            }
            R.id.done_view -> {
                this.visibility = View.GONE
            }
        }
    }

    fun observeValue() = price

    private fun <T : View> `$`(@IdRes id: Int): T {
        return super.findViewById<View>(id) as T
    }
}