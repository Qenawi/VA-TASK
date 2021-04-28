package va.task

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.Calendar

/***
 * @Docs
 */
@RequiresApi(VERSION_CODES.LOLLIPOP)
class MathService : JobService() {
  val jop = HashMap<Long, CoroutineScope>()
  override fun onStartJob(params: JobParameters?): Boolean {
    this.clear()
    //solveEquation() // :-todo
    //MARK:- returning false means the work has been done, return true if the job is being run asynchronously
    return true
  }

  override fun onStopJob(params: JobParameters?): Boolean {
    this.clear()
    //MARK:- return true to restart the job
    return true
  }

  // MARK:- Create New Thread for task
  private fun solveEquation(task: ServiceModel) {
    val taskScope = CoroutineScope(Dispatchers.IO)
    jop[task.taskId] = taskScope
    jop[task.taskId]?.launch { calculate(task) }
  }

  // MARK:- work on task
  private fun calculate(task: ServiceModel) {
    try {
      val timeStart: Long = Calendar.getInstance().timeInMillis
      task.task?.calculate()
      val timeEndElementListener: Long = Calendar.getInstance().timeInMillis
      task.executionTime = timeEndElementListener - timeStart
      sendBroadCast(task = task, status = true)
    } catch (e: Exception) {
      sendBroadCast(task = task, status = false)
    }
  }
  // MARK:- send broad cast

  private fun sendBroadCast(
    task: ServiceModel,
    status: Boolean
  ) {
    // bla bla handle Status
  }

  // MARK:- clear Scope
  private fun clear() {
    jop.keys.forEach { key -> jop[key]?.cancel() }
    jop.clear()
  }
}