package id.adeds.androidapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.adeds.androidapp.util.toSingleEvent
import id.adeds.shared.domain.interactor.character.GetCharactersUseCase
import id.adeds.shared.domain.model.Character
import id.adeds.shared.domain.model.GetData
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: GetCharactersUseCase
) : ViewModel() {
    private val _characters = MutableLiveData<GetData<List<Character>>>()
    val characters: LiveData<GetData<List<Character>>>
        get() = _characters.toSingleEvent()

    fun getCharacter() = viewModelScope.launch {
        try {
            val result = useCase().toList()
            _characters.postValue(GetData(data = result))
        } catch (e: Exception) {
            _characters.postValue(
                GetData(
                    errorTitleAndDesc = Pair(
                        "Something went wrong",
                        e.message.orEmpty()
                    )
                )
            )
        }
    }

}