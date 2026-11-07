package com.zero.lock

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.ViewGroup
import android.widget.*

class MainActivity : Activity() {

    private val pin = "299261"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
            setBackgroundColor(Color.rgb(8, 10, 8))
        }

        val title = TextView(this).apply {
            text = "ZERO LOCK"
            textSize = 34f
            setTextColor(Color.rgb(0, 255, 100))
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "SECURE ACCESS"
            textSize = 14f
            setTextColor(Color.LTGRAY)
            gravity = Gravity.CENTER
        }

        val input = EditText(this).apply {
            hint = "ENTER YOUR PIN"
            setHintTextColor(Color.GRAY)
            setTextColor(Color.WHITE)
            textSize = 20f
            gravity = Gravity.CENTER
            inputType = 2
            isSingleLine = true
        }

        val button = Button(this).apply {
            text = "UNLOCK"
            setTextColor(Color.BLACK)
            setBackgroundColor(Color.rgb(0, 255, 100))
        }

        val message = TextView(this).apply {
            gravity = Gravity.CENTER
            textSize = 16f
            setTextColor(Color.WHITE)
        }

        button.setOnClickListener {
            if (input.text.toString() == pin) {
                message.text = "ACCESS GRANTED"
                message.setTextColor(Color.GREEN)
            } else {
                message.text = "INCORRECT PIN"
                message.setTextColor(Color.RED)
                input.text.clear()
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(input)
        layout.addView(button)
        layout.addView(message)

        setContentView(layout)
    }
}
