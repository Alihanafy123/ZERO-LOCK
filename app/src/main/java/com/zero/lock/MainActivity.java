
package com.zero.lock;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.res.ColorStateList;

public class MainActivity extends Activity {
    private static final String PIN = "299261";
    private EditText pinInput;
    private TextView status;
    private int green = Color.rgb(57, 255, 20);
    private int black = Color.rgb(8, 10, 9);
    private boolean unlockRequested = false;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        Window w = getWindow();
        w.setStatusBarColor(black);
        w.setNavigationBarColor(black);

        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setGravity(Gravity.CENTER);
        root.setPadding(32, 40, 32, 40);
        root.setBackgroundColor(black);

        TextView title = new TextView(this);
        title.setText("ZERO LOCK");
        title.setTextColor(green);
        title.setTextSize(34);
        title.setTypeface(Typeface.DEFAULT, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);
        root.addView(title, matchWrap());

        TextView subtitle = new TextView(this);
        subtitle.setText("PERSONAL DEVICE SECURITY");
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setTextSize(12);
        subtitle.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams sp = matchWrap();
        sp.topMargin = 10;
        root.addView(subtitle, sp);

        pinInput = new EditText(this);
        pinInput.setSingleLine(true);
        pinInput.setInputType(2);
        pinInput.setHint("أدخل الرقم السري");
        pinInput.setHintTextColor(Color.GRAY);
        pinInput.setTextColor(Color.WHITE);
        pinInput.setGravity(Gravity.CENTER);
        pinInput.setTextSize(20);
        pinInput.setBackgroundTintList(ColorStateList.valueOf(green));

        LinearLayout.LayoutParams ip =
            new LinearLayout.LayoutParams(-1, -2);
        ip.topMargin = 42;
        root.addView(pinInput, ip);

        Button unlock = makeButton("فتح ZERO LOCK");
        LinearLayout.LayoutParams bp =
            new LinearLayout.LayoutParams(-1, -2);
        bp.topMargin = 20;
        root.addView(unlock, bp);
        unlock.setOnClickListener(v -> checkPin());

        status = new TextView(this);
        status.setText("جاري تفعيل وضع تثبيت الشاشة...");
        status.setTextColor(Color.LTGRAY);
        status.setGravity(Gravity.CENTER);
        status.setTextSize(13);

        LinearLayout.LayoutParams tp = matchWrap();
        tp.topMargin = 26;
        root.addView(status, tp);

        // Developer Credit
        TextView developer = new TextView(this);
        developer.setText("Created & Developed by Ali Hanafy");
        developer.setTextColor(green);
        developer.setTextSize(12);
        developer.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams dp = matchWrap();
        dp.topMargin = 35;
        root.addView(developer, dp);

        setContentView(root);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (!unlockRequested && pinInput != null) {
            try {
                startLockTask();
                status.setText("تم طلب تفعيل تثبيت الشاشة.");
            } catch (Exception e) {
                status.setText("تعذر تفعيل تثبيت الشاشة.");
            }
        }
    }

    private LinearLayout.LayoutParams matchWrap() {
        return new LinearLayout.LayoutParams(-1, -2);
    }

    private Button makeButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextColor(black);
        b.setAllCaps(false);
        b.setTextSize(15);
        b.setBackgroundTintList(ColorStateList.valueOf(green));
        return b;
    }

    private void checkPin() {
        if (PIN.equals(pinInput.getText().toString())) {
            unlockRequested = true;

            try {
                stopLockTask();
                status.setText("تم فتح ZERO LOCK.");
                Toast.makeText(
                    this,
                    "تم فتح ZERO LOCK",
                    Toast.LENGTH_SHORT
                ).show();
                pinInput.setText("");
            } catch (Exception e) {
                unlockRequested = false;
                status.setText("تعذر إلغاء تثبيت الشاشة.");
            }
        } else {
            status.setText("الرقم السري غير صحيح.");
            Toast.makeText(
                this,
                "رقم سري غير صحيح",
                Toast.LENGTH_SHORT
            ).show();
            pinInput.setText("");
        }
    }
            }
