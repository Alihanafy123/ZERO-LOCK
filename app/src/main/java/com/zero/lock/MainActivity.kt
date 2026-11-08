
package com.zero.lock

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.app.ActivityManager

class MainActivity : Activity() {

    private lateinit var pin: EditText
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(30, 30, 30, 30)
            setBackgroundColor(Color.rgb(8, 10, 8))
        }

        val title = TextView(this).apply {
            text = "ZERO LOCK"
            textSize = 32f
            setTextColor(Color.GREEN)
            gravity = Gravity.CENTER
        }

        pin = EditText(this).apply {
            hint = "ENTER PIN"
            inputType = 2
            setTextColor(Color.WHITE)
            setHintTextColor(Color.GRAY)
            gravity = Gravity.CENTER
        }

        val button = Button(this).apply {
            text = "UNLOCK"
        }

        result = TextView(this).apply {
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
        }

        button.setOnClickListener {
            if (pin.text.toString() == "299261") {
                try {
                    stopLockTask()
                    result.text = "ACCESS GRANTED"
                    result.setTextColor(Color.GREEN)
                } catch (e: Exception) {
                    result.text = "UNLOCK FAILED"
                }
            } else {
                result.text = "INCORRECT PIN"
                pin.text.clear()
            }
        }

        layout.addView(title)
        layout.addView(pin)
        layout.addView(button)
        layout.addView(result)

        setContentView(layout)
    }

    override fun onResume() {
        super.onResume()

        if (android.os.Build.VERSION.SDK_INT >= 23) {
            try {
                startLockTask()
            } catch (e: Exception) {
                result.text = "SCREEN PINNING NOT AVAILABLE"
            }
        }
    }
}
