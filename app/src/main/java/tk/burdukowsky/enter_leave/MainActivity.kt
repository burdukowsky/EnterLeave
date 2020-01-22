package tk.burdukowsky.enter_leave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView

class MainActivity : AppCompatActivity(), ActionDeleter {

    private lateinit var lvActions: ListView
    private lateinit var actionListAdapter: ActionListAdapter
    private lateinit var actions: MutableList<Action>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actions = ActionDao.queryForAll()
        lvActions = findViewById(R.id.lvActions)
        actionListAdapter = ActionListAdapter(this, actions)
        lvActions.adapter = actionListAdapter
    }

    @Suppress("UNUSED_PARAMETER")
    fun leaveButtonClick(view: View) {
        addAction(ActionType.LEAVE)
    }

    @Suppress("UNUSED_PARAMETER")
    fun enterButtonClick(view: View) {
        addAction(ActionType.ENTER)
    }

    private fun addAction(type: ActionType) {
        val action = Action(null, type, Utils.getCurrentMilliseconds())
        ActionDao.add(action)
        actions.add(action)
        actionListAdapter.notifyDataSetChanged()
        smoothScrollToBottom()
    }

    private fun smoothScrollToBottom() {
        lvActions.post { run { lvActions.smoothScrollToPosition(actionListAdapter.count) } }
    }

    override fun deleteAction(action: Action) {
        ActionDao.delete(action)
        actions.remove(action)
        actionListAdapter.notifyDataSetChanged()
    }

}
