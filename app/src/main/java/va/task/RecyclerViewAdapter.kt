package va.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter : ListAdapter<ServiceModel, BaseViewHolder>(BaseItemCallback())
{

  val mCurrentList = arrayListOf<ServiceModel?>()

  private var position: Int = 0

  @LayoutRes
  fun layoutRes() = 0
  fun onViewHolderCreated(viewHolder: BaseViewHolder){}
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val binding = DataBindingUtil.inflate<ViewDataBinding>(
      LayoutInflater.from(parent.context),
      layoutRes(),
      parent,
      false
    )
    return BaseViewHolder(binding).apply {
      onViewHolderCreated(this)
    }
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    holder.bind(getItem(position))
  }


  fun updateList(newList: List<ServiceModel>) {
    mCurrentList.addAll(newList)
    submitList(mCurrentList.toMutableList())
  }

  fun setList(newList: List<ServiceModel?>) {
    mCurrentList.clear()
    mCurrentList.addAll(newList)
    submitList(mCurrentList.toMutableList())
  }

  fun addItemToList(item: ServiceModel?, isAdded: (Boolean) -> Unit) {
    item?.let {
      mCurrentList.add(item)
      submitList(mCurrentList.toMutableList())
      isAdded(true)
    }
  }

  fun editItem(item: ServiceModel?) {
    item?.let {
      mCurrentList[position] = item
      submitList(mCurrentList.toMutableList())
    }
  }

  fun clearCurrentList() {
    mCurrentList.clear()
    submitList(mCurrentList.toMutableList())
  }

}

// MARK:- BaseViewHolder

class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
  fun bind(item: ServiceModel) {
    binding.setVariable(BR.item, item)
    binding.executePendingBindings()
  }
}

class BaseItemCallback : DiffUtil.ItemCallback<ServiceModel>() {
  override fun areItemsTheSame(oldItem: ServiceModel, newItem: ServiceModel) =
    oldItem.unique().toString() == newItem.unique().toString()

  override fun areContentsTheSame(oldItem: ServiceModel, newItem: ServiceModel) =
    oldItem.equals(newItem)
}

interface BaseParcelable {
  fun unique(): Any
}


//Binding Adapter
// MARK : Extentions
@BindingAdapter("adapter")
fun setRecyclerAdapter(recyclerView: RecyclerView, adapter: RecyclerView.Adapter<*>?) {
  adapter?.let {
    recyclerView.adapter = it
  }
}
