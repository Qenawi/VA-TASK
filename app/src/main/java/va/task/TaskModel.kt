package va.task

import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.android.material.internal.ParcelableSparseIntArray
import kotlinx.android.parcel.Parcelize
import va.task.TaskOperations.Add
import va.task.TaskOperations.Div
import va.task.TaskOperations.Mul
import va.task.TaskOperations.Sub

/**
 * Task Operation (Mul / Add / Sub)
 */
@Parcelize
enum class TaskOperations : Parcelable {
  Add,
  Sub,
  Mul,
  Div
}

/**
 * Task Model
 */
@Parcelize
data class TaskModel(
  var firstVariable: Long = 0,
  var secondVariable: Long = 0,
  var operator: TaskOperations = TaskOperations.Add,
  var result: Long = 0
) : Parcelable

fun TaskModel.calculate() {
  result = when (operator) {
    Add -> firstVariable + secondVariable
    Sub -> firstVariable - secondVariable
    Mul -> firstVariable * secondVariable
    Div -> firstVariable / secondVariable
  }
}

/**
 * Service Model
 */
@Parcelize
data class ServiceModel(
  var task: TaskModel? = null,
  var startTime: Long = 0L,
  var executionTime: Long = 0L,
  var status: ServiceEvents = ServiceEvents.Pending,
  var taskId: Long = 0,
) : Parcelable,BaseParcelable {
  override fun unique(): Any
  {
   return taskId
  }
}