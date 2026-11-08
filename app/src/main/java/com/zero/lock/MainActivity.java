package com.zero.lock;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final String PIN = "299261";
    private EditText pinInput;
    private TextView status;
    private int green = Color.rgb(57, 255, 20);
    private int black = Color.rgb(8, 10, 9);

    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        Window w = getWindow();
        w.setStatusBarColor(black);
        w.setNavigationBarColor(black);
        w.getDecorView().setSystemUiVisibility(0);

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
        LinearLayout.LayoutParams sp = matchWrap(); sp.topMargin = 10;
        root.addView(subtitle, sp);

        pinInput = new EditText(this);
        pinInput.setSingleLine(true);
        pinInput.setInputType(2);
        pinInput.setHint("أدخل الرقم السري");
        pinInput.setHintTextColor(Color.GRAY);
        pinInput.setTextColor(Color.WHITE);
        pinInput.setGravity(Gravity.CENTER);
        pinInput.setTextSize(20);
        pinInput.setBackgroundTintList(android.content.res.ColorStateList.valueOf(green));
        LinearLayout.LayoutParams ip = new LinearLayout.LayoutParams(-1, -2);
        ip.topMargin = 42;
        root.addView(pinInput, ip);

        Button unlock = makeButton("فتح ZERO LOCK");
        LinearLayout.LayoutParams bp = new LinearLayout.LayoutParams(-1, -2); bp.topMargin = 20;
        root.addView(unlock, bp);
        unlock.setOnClickListener(v -> checkPin());

        Button pinScreen = makeButton("بدء تثبيت الشاشة من أندرويد");
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(-1, -2); lp.topMargin = 14;
        root.addView(pinScreen, lp);
        pinScreen.setOnClickListener(v -> {
            status.setText("سيطلب أندرويد تأكيد تثبيت الشاشة إذا كانت الميزة متاحة.");
            try { startLockTask(); }
            catch (Exception e) { Toast.makeText(this, "تعذر بدء تثبيت الشاشة", Toast.LENGTH_LONG).show(); }
        });

        status = new TextView(this);
        status.setText("وضع تجريبي: هذا التطبيق يحمي شاشته فقط.");
        status.setTextColor(Color.LTGRAY);
        status.setGravity(Gravity.CENTER);
        status.setTextSize(13);
        LinearLayout.LayoutParams tp = matchWrap(); tp.topMargin = 26;
        root.addView(status, tp);
        setContentView(root);
    }

    private LinearLayout.LayoutParams matchWrap() { return new LinearLayout.LayoutParams(-1, -2); }
    private Button makeButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextColor(black);
        b.setAllCaps(false);
        b.setTextSize(15);
        b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(green));
        return b;
    }
    private void checkPin() {
        if (PIN.equals(pinInput.getText().toString())) {
            status.setText("تم قبول الرقم السري.");
            Toast.makeText(this, "تم فتح ZERO LOCK", Toast.LENGTH_SHORT).show();
            pinInput.setText("");
        } else {
            status.setText("الرقم السري غير صحيح.");
            Toast.makeText(this, "رقم سري غير صحيح", Toast.LENGTH_SHORT).show();
        }
    }
}
