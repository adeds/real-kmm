package id.adeds.androidapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.adeds.androidapp.util.toSingleEvent
import id.adeds.shared.domain.interactor.character.GetCharactersUseCase
import id.adeds.shared.domain.interactor.character.UpdateCharactersUseCase
import id.adeds.shared.domain.model.Character
import id.adeds.shared.domain.model.GetData
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val getCharacterUseCase: GetCharactersUseCase,
    private val updateCharacterUseCase: UpdateCharactersUseCase,
) : ViewModel() {
    private val _characters = MutableLiveData<GetData<List<Character>>>()
    val characters: LiveData<GetData<List<Character>>>
        get() = _characters.toSingleEvent()

    fun getCharacter() = viewModelScope.launch {
        getCharacterUseCase()
            .catch {
                _characters.postValue(
                    GetData(
                        errorTitleAndDesc = Pair(
                            "Something went wrong",
                            it.message.orEmpty()
                        )
                    )
                )
            }
            .collect {
                _characters.postValue(GetData(data = it))
            }
    }

    fun setFavorite(character: Character) = viewModelScope.launch {
        updateCharacterUseCase(character)
            .catch {
                _characters.postValue(
                    GetData(
                        errorTitleAndDesc = Pair(
                            "Something went wrong",
                            it.message.orEmpty()
                        )
                    )
                )
            }
            .collect {
                _characters.postValue(GetData(data = it))
            }
    }

}