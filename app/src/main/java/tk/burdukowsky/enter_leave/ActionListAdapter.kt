package tk.burdukowsky.enter_leave

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class ActionListAdapter(
    private var context: Context,
    private var entities: List<Action>
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: layoutInflater.inflate(R.layout.action, parent, false)
        val entity = getItem(position)
        val formattedTime = Utils.millisecondsToFormattedTime(entity.time)
        view.findViewById<TextView>(R.id.tvActionDate).text = formattedTime.date
        view.findViewById<TextView>(R.id.tvActionTime).text = formattedTime.time
        view.findViewById<TextView>(R.id.tvActionType).text = entity.type.getResource()
        view.findViewById<ImageView>(R.id.ivActionDelete).setOnClickListener {
            (context as ActionDeleter).deleteAction(entity)
        }
        return view
    }

    override fun getItem(position: Int) = entities[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = entities.size

}
