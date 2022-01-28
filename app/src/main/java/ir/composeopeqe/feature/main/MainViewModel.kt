package ir.composeopeqe.feature.mainimport androidx.lifecycle.*import dagger.hilt.android.lifecycle.HiltViewModelimport ir.composeopeqe.data.Repositoryimport ir.composeopeqe.data.Resultimport kotlinx.coroutines.Dispatchersimport kotlinx.coroutines.flow.Flowimport kotlinx.coroutines.flow.MutableStateFlowimport kotlinx.coroutines.flow.collectimport kotlinx.coroutines.flow.flowimport kotlinx.coroutines.launchimport java.lang.Exceptionimport javax.inject.Inject@HiltViewModelclass MainViewModel @Inject constructor(private val repository : Repository) : ViewModel() {    val showError = MutableStateFlow(false)    val showLoading = MutableStateFlow(false)    val listUsers : Flow<List<Result>> =  flow {        try {            showLoading.emit(true)            showError.collect{                if (!it) {                    repository.getListUserFromApi()                    repository.getAllUsers().collect {                        emit(it)                        showLoading.emit(false)                    }                }            }        }        catch (e : Exception){            showError.emit(true)        }    }}