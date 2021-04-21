package com.example.cachorros

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TelaCadastro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_cadastro)
    }

    fun criarCachorro(view: View) {
        val apiCachorros =ConexaoApiCachorros.criar()

        val tvCachorro: TextView = findViewById(R.id.tv_cachorro)
        val etRaca: EditText = findViewById(R.id.et_raca)
        val etPreco: EditText = findViewById(R.id.et_preco)
        val ivCachorro: ImageView = findViewById(R.id.iv_cachorro)
        val swIndicado: Switch = findViewById(R.id.sw_indicado)
        val raca = etRaca.text.toString()
        val preco = etPreco.text.toString().toDouble()
        val indicado = swIndicado.isChecked.toString().toBoolean()

        val cachorro = Cachorro(0, raca, preco, indicado)

        apiCachorros.post(cachorro).enqueue(object : Callback<Cachorro> {

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if(response.code() == 201){
                    val cachorroCriado = response.body()
                    tvCachorro.text = "Cachorro cadastrado com sucesso!!!"
                    ivCachorro.visibility = View.VISIBLE
                }else{
                    tvCachorro.text = "Falha ao criar o cachorro"
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, "Erro na chamada: ${t.message!!}", Toast.LENGTH_SHORT).show()
            }
        })
    }

}