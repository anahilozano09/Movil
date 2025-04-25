package mx.unam.movil

import mx.unam.movil.databinding.ActivityMainBinding
import mx.unam.movil.http.DogAdapter
import mx.unam.movil.http.ModeloDog
import mx.unam.movil.http.ServiceAPI

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mx.unam.movil.databinding.ActivityServicioBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
class ServicioActivity : AppCompatActivity(), SearchView.OnQueryTextListener {
    private lateinit var binding: ActivityServicioBinding  // Updated binding class
    private lateinit var adapter: DogAdapter
    private val dogImages = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServicioBinding.inflate(layoutInflater)  // Updated binding class
        setContentView(binding.root)
        binding.searchview.setOnQueryTextListener(this)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        adapter = DogAdapter(dogImages)
        binding.recycleView.layoutManager = LinearLayoutManager(this)
        binding.recycleView.adapter = adapter
    }
    private fun getRedrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun search(type: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call=getRedrofit().create(ServiceAPI::class.java).getBreedbyDogs("$type/images")
            val pug: ModeloDog? = call.body()
            runOnUiThread {
                if (call.isSuccessful) {
                    val images: List<String> = pug?.images?: emptyList()
                    dogImages.clear()
                    dogImages.addAll(images)
                    adapter.notifyDataSetChanged()
                } else {
                    msgError()
                }
            }
        }
    }
    private fun msgError() {
        Toast.makeText(this, "Error en Conexion", Toast.LENGTH_SHORT).show()
    }
    override fun onQueryTextSubmit(p0: String?): Boolean {
        if(!p0.isNullOrEmpty()){
            search(p0.lowercase())
        }
        return true
    }
    override fun onQueryTextChange(p0: String?): Boolean {
        return true
    }
}