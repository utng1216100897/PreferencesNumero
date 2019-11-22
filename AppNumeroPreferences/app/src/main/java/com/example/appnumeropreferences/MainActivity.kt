package com.example.appnumeropreferences

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferencias  = getSharedPreferences("agenda", Context.MODE_PRIVATE)
        val datos = preferencias.getString("aciertos","")
        // se ponen los aciertos
        if(datos.length == 0) {
            txtPuntaje.setText("0")
        }else{
            txtPuntaje.setText(datos)
        }

        val random = java.util.Random();

        var numeroAleatorio = random.nextInt(50-1)+1


        btnVerificar.setOnClickListener {
            val datos = preferencias.getString("aciertos","")
            if (datos.length == 0) {
                Toast.makeText(
                    this, "Error numero equivocado",
                    Toast.LENGTH_LONG
                ).show()
            }else{
                val vPuntaje = txtDato.text.toString()
                var iPuntajeReal = vPuntaje.toInt()
                // se suma a los aciertos
                iPuntajeReal = iPuntajeReal + 1;
                val editor = preferencias.edit()
                editor.putInt("aciertos",
                   iPuntajeReal)
                editor.commit()
                Toast.makeText(this,"Correcto!, adivina un nuevo numero", Toast.LENGTH_LONG).show()
                txtDato.setText("")
                txtPuntaje.setText(iPuntajeReal.toString())
                // se calcula el nuevo numero
                numeroAleatorio = random.nextInt(50- 1)+1


            }

        }
    }
}
