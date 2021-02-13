package id.adeds.androidapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.adeds.shared.domain.model.Character
import id.id.adeds.androidapp.databinding.ItemCharacterBinding

class MainAdapter(private val list: List<Character>) :
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    inner class MainHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {
        fun bind() {
            list[adapterPosition].let {
                itemCharacterBinding.apply {
                    nameCharacter.text = it.name
                    statusCharacter.text = it.status.toString()
                    speciesCharacter.text = it.species
                    genderCharacter.text = it.gender.toString()
                    originCharacter.text = it.origin
                    locationCharacter.text = it.location
                    imgCharacter.load(it.image)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder =
        MainHolder(ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = list.size
}