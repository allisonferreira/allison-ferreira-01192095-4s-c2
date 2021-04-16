package com.example.cachorros

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaLista : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_lista)

        val layoutLista: LinearLayout = findViewById(R.id.layout_lista)
        val apiCachorros =ConexaoApiCachorros.criar()

        apiCachorros.get().enqueue(object : Callback<List<Cachorro>> {
            override fun onResponse(call: Call<List<Cachorro>>, response: Response<List<Cachorro>>) {
                response.body()?.forEach {
                    val tvCachorro = TextView(baseContext)
                    tvCachorro.text = "Id: ${it.id} - Raça: ${it.raca} - Preço médio: ${it.precoMedio} - Indicado para criança? ${it.indicadoCriancas}"
                    //tv.setTextColor(Color.parseColor("#9911AA"))

                    layoutLista.addView(tvCachorro)
                }
            }

            override fun onFailure(call: Call<List<Cachorro>>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })
    }




}