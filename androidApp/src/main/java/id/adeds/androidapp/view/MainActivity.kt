package id.adeds.androidapp.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import id.adeds.androidapp.viewmodel.MainViewModel
import id.adeds.shared.domain.model.Character
import id.id.adeds.androidapp.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()
    private lateinit var adapter: MainAdapter
    private val characters = mutableListOf<Character>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.flipper.displayedChild = 0
        adapter = MainAdapter(characters)
        binding.rvCharacters.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        viewModel.apply {
            characters.observe(this@MainActivity) {
                if (it.data != null) loadData(it.data.orEmpty())
                else if (it.errorTitleAndDesc != null) showError(it.errorTitleAndDesc)
            }
            getCharacter()
        }
    }

    private fun loadData(data: List<Character>) {
        binding.flipper.displayedChild = 1
        characters.clear()
        characters.addAll(data)
        adapter.notifyDataSetChanged()
    }

    private fun showError(errorTitleAndDesc: Pair<String, String>?) {
        binding.flipper.displayedChild = 2
        binding.tvErrorMessages.text = errorTitleAndDesc.toMessage()
    }

    private fun Pair<String, String>?.toMessage(): String {
        return "${this?.first.orEmpty()}\n${this?.second.orEmpty()}"
    }
}