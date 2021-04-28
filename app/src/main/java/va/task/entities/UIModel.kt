package va.task.entities

import androidx.lifecycle.MutableLiveData
import va.task.RecyclerViewAdapter
import va.task.TaskOperations

class UIModel {
  var obsLoading = MutableLiveData<Boolean>(false)
  var obsEnable = MutableLiveData<Boolean>(false)

  // Mark:- operation Selection
  var obsSelection = MutableLiveData<TaskOperations>()
  var adapter = RecyclerViewAdapter()
  var firstVar= MutableLiveData<String>("")
  var secondVar= MutableLiveData<String>("")

}