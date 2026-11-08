package com.zero.lock

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText
import android.widget.Button

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.gravity = Gravity.CENTER
            layout.setPadding(30, 30, 30, 30)
            layout.setBackgroundColor(Color.rgb(8, 10, 8))

            val title = TextView(this)
            title.text = "ZERO LOCK"
            title.textSize = 32f
            title.setTextColor(Color.GREEN)
            title.gravity = Gravity.CENTER

            val pin = EditText(this)
            pin.hint = "ENTER PIN"
            pin.inputType = 2
            pin.setTextColor(Color.WHITE)
            pin.setHintTextColor(Color.GRAY)
            pin.gravity = Gravity.CENTER

            val button = Button(this)
            button.text = "UNLOCK"

            val result = TextView(this)
            result.gravity = Gravity.CENTER
            result.setTextColor(Color.WHITE)

            button.setOnClickListener {
                result.text =
                    if (pin.text.toString() == "299261")
                        "ACCESS GRANTED"
                    else
                        "INCORRECT PIN"
            }

            layout.addView(title)
            layout.addView(pin)
            layout.addView(button)
            layout.addView(result)

            setContentView(layout)

        } catch (e: Exception) {
            val error = TextView(this)
            error.text = "ZERO LOCK ERROR: ${e.message}"
            error.setTextColor(Color.RED)
            setContentView(error)
        }
    }
}
