package id.adeds.androidapp.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import id.adeds.androidapp.viewmodel.MainViewModel
import id.adeds.shared.domain.model.Character
import id.id.adeds.androidapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.apply {
            characters.observe(this@MainActivity) {
                if (it.data != null) loadData(it.data.orEmpty())
                else if (it.errorTitleAndDesc != null) showError(it.errorTitleAndDesc)
            }
            getCharacter()
        }
    }

    private fun loadData(data: List<Character>) {
        Log.d("remoteData", data.toString())
    }

    private fun showError(errorTitleAndDesc: Pair<String, String>?) {
        Toast.makeText(
            this,
            errorTitleAndDesc.toMessage(),
            Toast.LENGTH_SHORT
        ).show()
        Log.e("remoteData", errorTitleAndDesc.toMessage())
    }

    private fun Pair<String, String>?.toMessage(): String {
        return "${this?.first.orEmpty()}\n${this?.second.orEmpty()}"
    }
}