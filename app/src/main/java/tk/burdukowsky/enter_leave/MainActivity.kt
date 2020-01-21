package tk.burdukowsky.enter_leave

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private val actionDao = ActionDao()

    private lateinit var lvActions: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvActions = findViewById(R.id.lvActions)
        lvActions.adapter = ActionListAdapter(this, this.actionDao.queryForAll())
    }

}
