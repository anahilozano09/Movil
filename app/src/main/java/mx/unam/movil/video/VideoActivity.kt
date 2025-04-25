package mx.unam.movil.video
import android.os.Bundle
import android.widget.AdapterView
import android.widget.MediaController
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import mx.unam.movil.R
import mx.unam.movil.databinding.ActivityVideoBinding
class VideoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVideoBinding
    private lateinit var model: ArrayList<Modelo>
    private lateinit var adap: RecipeAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val controller = MediaController(this)
        binding.surface.setMediaController(controller)
        controller.setAnchorView(binding.surface)
        fillList()
        binding.list.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            val data: Modelo = model.get(position)
            var ruta = ""
            when (data.type) {
                1 -> {
                    ruta =
                        "android.resource://" + packageName + "/raw/" + data.nameFile.removeRange(
                            data.nameFile.indexOf('.'),
                            data.nameFile.length
                        )
                }
                2 -> {
                    ruta = data.nameFile
                }
            }
            val rutaUri = ruta.toUri()
            binding.surface.setVideoURI(rutaUri)
            binding.surface.start()
            Toast.makeText(this, data.nameFile, Toast.LENGTH_SHORT).show()
        })
    }
    private fun fillList() {
        model = ArrayList()
        model.add(Modelo("video.3gp", R.drawable.video_uno,1))
        model.add(
            Modelo(
                "https://archive.org/download/ElephantsDream/ed_hd.mp4",
                R.drawable.video_dos,2
            )
        )
        adap = RecipeAdapter(this, model)
        binding.list.adapter = adap
    }
}