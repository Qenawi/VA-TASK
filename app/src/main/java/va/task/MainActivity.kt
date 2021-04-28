package va.task

import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import va.task.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
  val viewModel: MathViewModel by viewModels()
  var broadCastReciver: MathServiceBroadCast? = null
  lateinit var binding: ActivityMainBinding
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    binding.lifecycleOwner = this
    //MARK:- add view model to broad cast
    broadCastReciver = MathServiceBroadCast()
    broadCastReciver?.viewmodel = viewModel
    binding.viewmodel = viewModel
  }

  override fun onResume() {
    val intentFilter = IntentFilter()
    intentFilter.addAction("UpdateFromService")
    registerReceiver(broadCastReciver, intentFilter)
    super.onResume()
  }

  override fun onPause() {
    unregisterReceiver(broadCastReciver)
    super.onPause()
  }
}