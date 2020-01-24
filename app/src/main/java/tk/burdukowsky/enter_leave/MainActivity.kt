package tk.burdukowsky.enter_leave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ActionDeleter {

    private lateinit var lvActions: ListView
    private lateinit var actionListAdapter: ActionListAdapter
    private lateinit var actions: MutableList<Action>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        actions = ActionDao.queryForAll()
        lvActions = findViewById(R.id.lvActions)
        actionListAdapter = ActionListAdapter(this, actions)
        lvActions.adapter = actionListAdapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.actionDeleteAllButLast -> {
                ConfirmationDialogFragment(
                    positiveOnClickListener = Runnable { deleteAllActionsButLast() }
                ).show(supportFragmentManager, "deleteAllButLastConfirmationDialog")
                true
            }
            R.id.actionDeleteAll -> {
                ConfirmationDialogFragment(
                    positiveOnClickListener = Runnable { deleteAllActions() }
                ).show(supportFragmentManager, "deleteAllConfirmationDialog")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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

    private fun deleteAllActions() {
        ActionDao.deleteAll()
        actions.clear()
        actionListAdapter.notifyDataSetChanged()
    }

    private fun deleteAllActionsButLast() {
        if (actions.size > 1) {
            val actionsForDelete = actions.subList(0, actions.size - 1)
            ActionDao.deleteIds(actionsForDelete.map { action -> action.id!! })
            actionsForDelete.clear()
            actionListAdapter.notifyDataSetChanged()
        }
    }

    override fun deleteAction(action: Action) {
        ActionDao.delete(action)
        actions.remove(action)
        actionListAdapter.notifyDataSetChanged()
    }

    private fun smoothScrollToBottom() {
        lvActions.post { run { lvActions.smoothScrollToPosition(actionListAdapter.count) } }
    }

}
