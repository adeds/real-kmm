package id.adeds.androidapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import id.adeds.androidapp.view.listener.CharacterListClickListener
import id.adeds.shared.domain.model.Character
import id.adeds.shared.domain.model.convertString
import id.id.adeds.androidapp.R
import id.id.adeds.androidapp.databinding.ItemCharacterBinding

class MainAdapter(
    private val list: List<Character>,
    private val listener: CharacterListClickListener,
    ) :
    RecyclerView.Adapter<MainAdapter.MainHolder>() {

    inner class MainHolder(private val itemCharacterBinding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(itemCharacterBinding.root) {
        fun bind() {
            list[adapterPosition].let {
                itemCharacterBinding.apply {
                    nameCharacter.text = it.name
                    statusCharacter.text = it.status.convertString()
                    speciesCharacter.text = it.species
                    genderCharacter.text = it.gender.convertString()
                    originCharacter.text = it.origin
                    locationCharacter.text = it.location
                    imgCharacter.load(it.image)
                    imgFav.load(
                        if (it.isFavorite) R.drawable.ic_baseline_favorite_24
                        else R.drawable.ic_outline_favorite_border_24
                    )
                    imgFav.setOnClickListener { listener.favoriteClick(adapterPosition) }
                }
                itemView.setOnClickListener { listener.itemClick(adapterPosition) }
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