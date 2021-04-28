package va.task

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * BroadCast Receiver
 **/
interface MathServiceProtocol {
  fun onReceive(d: ServiceModel?)
}

class MathServiceBroadCast : BroadcastReceiver() {
  companion object {
    val fieldName = ""
  }

  var viewmodel: MathServiceProtocol? = null
  override fun onReceive(
    context: Context?,
    intent: Intent?
  ) {
    val result = intent?.getParcelableExtra<ServiceModel>(fieldName)
    viewmodel?.onReceive(result)
  }
}