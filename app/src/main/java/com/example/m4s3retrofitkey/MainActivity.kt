package com.example.m4s3retrofitkey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.m4s3retrofitkey.databinding.ActivityMainBinding
import com.example.m4s3retrofitkey.retrofit.RestEngine
import com.example.m4s3retrofitkey.retrofit.Tiempo
import com.example.m4s3retrofitkey.retrofit.TiempoAPIService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBuscar.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            traerRespuesta(binding.txtCiudad.text.toString())
        }
    }

    private fun traerRespuesta(city: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val llamada: TiempoAPIService =
                RestEngine.getRestEngine().create(TiempoAPIService::class.java)
            val resultado: Call<Tiempo> = llamada.obtenerTiempo(city)
            val p:Tiempo? = resultado.execute().body()

            if (p != null){
                runOnUiThread {
                    binding.txtTimezone.text = "timezone: " + p.data[0].timezone
                    binding.txtCityName.text = "city_name: " + p.data[0].city_name
                    binding.txtWather.text = "weather/description: " + p.data[0].weather.description
                    binding.txtTemperatura.text = "temp: " + p.data[0].temp.toString()

                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }
}