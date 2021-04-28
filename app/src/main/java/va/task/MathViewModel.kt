package va.task

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import va.task.entities.UIModel

/**
 * Math Activity View Model
 */

class MathViewModel(app: Application) : AndroidViewModel(app), MathServiceProtocol {
  var uiModel = UIModel()
  //MARK:- On incoming update from service
  override fun onReceive(d: ServiceModel?) {
    println(d?.task?.result)
  }

  //Mark: a/b
  fun divOp() {}

  //Mark: a-b
  fun subOp() {}

  //Mark: a+b
  fun addOp() {}

  //Mark: a*b
  fun mulOp() {}

  //Mark:Calculate
  fun calculate() {}
}
