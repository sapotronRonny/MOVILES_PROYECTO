package com.example.proyecto

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.proyecto.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var etNombre: EditText
    private lateinit var etCorreo: EditText
    private lateinit var etContraseña: EditText
    private lateinit var rgGenero: RadioGroup
    private lateinit var checkPolitica: CheckBox
    private lateinit var checkDeportes: CheckBox
    private lateinit var checkCultura: CheckBox
    private lateinit var checkEducacion: CheckBox
    private lateinit var checkSalud: CheckBox
    private lateinit var spnProvincias: Spinner
    private lateinit var btnRegistro: Button
    private lateinit var cardResultado: CardView
    private lateinit var tvResultado: TextView
    private lateinit var btnLimpiar: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etNombre = findViewById(R.id.etNombre)
        etCorreo = findViewById(R.id.etCorreo)
        etContraseña = findViewById(R.id.etContraseña)
        rgGenero = findViewById(R.id.rgGenero)
        checkPolitica = findViewById(R.id.chkPolitica)
        checkDeportes = findViewById(R.id.chkDeportes)
        checkCultura = findViewById(R.id.chkCultura)
        checkEducacion = findViewById(R.id.chkEducacion)
        checkSalud = findViewById(R.id.chkSalud)
        spnProvincias = findViewById(R.id.spnProvincias)
        btnRegistro = findViewById(R.id.btnRegistro)
        cardResultado = findViewById(R.id.cardResultado)
        tvResultado = findViewById(R.id.tvResultado)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        val provincias = arrayOf(
            "Azuay", "Bolívar", "Cañar", "Carchi", "Chimborazo", "Cotopaxi",
            "El Oro", "Esmeraldas", "Galápagos", "Guayas", "Imbabura", "Loja",
            "Los Ríos", "Manabí", "Morona Santiago", "Napo", "Orellana",
            "Pastaza", "Pichincha", "Santa Elena", "Santo Domingo de los Tsáchilas",
            "Sucumbíos", "Tungurahua", "Zamora Chinchipe"
        )
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, provincias)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spnProvincias.adapter = adapter

        btnRegistro.setOnClickListener {
            mostrardatos()
        }

        btnLimpiar.setOnClickListener {
            limpiardatos()
        }
    }

    private fun mostrardatos() {
        val nombre = etNombre.text.toString()
        val correo = etCorreo.text.toString()
        val contraseña = etContraseña.text.toString()

        if (nombre.isEmpty()) {
            etNombre.error = "Debe ingresar datos válidos"
            return
        } else if (nombre.length < 8) {
            etNombre.error = "El nombre debe tener al menos 8 caracteres"
            return
        } else {
            etNombre.error = null
        }

        if (correo.isEmpty()) {
            etCorreo.error = "Debe ingresar datos válidos"
            return
        } else if (!correo.contains("@") || !correo.endsWith("gmail.com")) {
            etCorreo.error = "Dominio no válido. Solo se permite Gmail"
            return
        } else {
            etCorreo.error = null
        }

        if (contraseña.length < 8 || !contraseña.any { it.isDigit() } || !contraseña.any { it.isUpperCase() }) {
            etContraseña.error = "La contraseña debe tener al menos 8 caracteres, una mayúscula y un número"
            return
        } else {
            etContraseña.error = null
        }

        val idGeneroSeleccionado = rgGenero.checkedRadioButtonId
        if (idGeneroSeleccionado == -1) {
            Toast.makeText(this, "Debe seleccionar un género", Toast.LENGTH_SHORT).show()
            return
        }
        val genero = findViewById<RadioButton>(idGeneroSeleccionado).text.toString()

        val categorias = mutableListOf<String>()
        if (checkPolitica.isChecked) categorias.add("Política")
        if (checkDeportes.isChecked) categorias.add("Deporte")
        if (checkCultura.isChecked) categorias.add("Cultura")
        if (checkEducacion.isChecked) categorias.add("Educación")
        if (checkSalud.isChecked) categorias.add("Salud")

        val provincia = spnProvincias.selectedItem.toString()

        val resumen = """
            Nombre: $nombre
            Contraseña: $contraseña
            Correo: $correo
            Género: $genero
            Categorías: ${categorias.joinToString(", ")}
            Provincia: $provincia
        """.trimIndent()

        tvResultado.text = resumen
        cardResultado.visibility = View.VISIBLE
    }

    private fun limpiardatos() {
        etNombre.text.clear()
        etCorreo.text.clear()
        etContraseña.text.clear()
        rgGenero.clearCheck()
        checkPolitica.isChecked = false
        checkDeportes.isChecked = false
        checkCultura.isChecked = false
        checkEducacion.isChecked = false
        checkSalud.isChecked = false
        spnProvincias.setSelection(0)
        cardResultado.visibility = View.GONE
    }
}
