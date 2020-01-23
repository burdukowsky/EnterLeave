package tk.burdukowsky.enter_leave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
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
                Toast.makeText(this, "DeleteAllButLast", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.actionDeleteAll -> {
                Toast.makeText(this, "DeleteAll", Toast.LENGTH_SHORT).show()
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

    private fun smoothScrollToBottom() {
        lvActions.post { run { lvActions.smoothScrollToPosition(actionListAdapter.count) } }
    }

    override fun deleteAction(action: Action) {
        ActionDao.delete(action)
        actions.remove(action)
        actionListAdapter.notifyDataSetChanged()
    }

}
