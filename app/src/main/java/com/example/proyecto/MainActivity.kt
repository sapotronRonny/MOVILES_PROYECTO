package com.example.proyecto

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val linkRegistro = findViewById<TextView>(R.id.linkRegistro)

        val texto = "No tienes cuenta, registrate aquí"
        val spannable = SpannableString(texto)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(this@MainActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: android.text.TextPaint) {
                // Quita subrayado y aplica color mostaza aquí mismo
                ds.isUnderlineText = false
                ds.color = Color.parseColor("#D4AF37")  // Mostaza dorado
            }
        }

        spannable.setSpan(clickableSpan, 18, texto.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        linkRegistro.text = spannable
        linkRegistro.movementMethod = LinkMovementMethod.getInstance()
        linkRegistro.highlightColor = Color.TRANSPARENT
    }
}