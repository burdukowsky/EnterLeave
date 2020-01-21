package tk.burdukowsky.enter_leave

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class ActionListAdapter(
    private var context: Context,
    private var entities: List<Action>
) : BaseAdapter() {

    private var layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: layoutInflater.inflate(R.layout.action, parent, false)
        val entity = getItem(position)
        view.findViewById<TextView>(R.id.tvActionTime).text = formatMilliseconds(entity.time)
        view.findViewById<TextView>(R.id.tvActionType).text = entity.type.getResource()
        view.findViewById<ImageView>(R.id.ivActionDelete).setOnClickListener {
            Toast
                .makeText(
                    context,
                    "Callback",
                    Toast.LENGTH_SHORT
                )
                .show()
        }
        return view
    }

    override fun getItem(position: Int) = entities[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = entities.size

}
