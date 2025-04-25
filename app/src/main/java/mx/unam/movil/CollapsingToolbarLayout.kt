package mx.unam.movil

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import mx.unam.movil.databinding.ActivityCollapsingToolbarLayoutBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CollapsingToolbarLayout : AppCompatActivity() {
    private lateinit var binding: ActivityCollapsingToolbarLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityCollapsingToolbarLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        //vieja notación
//val boton: FloatingActionButton =findViewById(R.id.floating_compra)
//nueva notación
        val boton: FloatingActionButton =binding.floatingCompra
        boton.setOnClickListener{
            Toast.makeText(this, "Evento", Toast.LENGTH_SHORT).show()
        }
        val formato= SimpleDateFormat("d MMM yyyy", Locale("mx","es"))
        val fecha= formato.format(Date())
//vieja notación
//val textoFecha: TextView =findViewById(R.id.fecha)
        val textoFecha: TextView =binding.fecha
        textoFecha.text=fecha
        }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}