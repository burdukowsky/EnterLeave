package tk.burdukowsky.enter_leave

import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus

object ActionDao {

    private val dao: Dao<Action, Int> = DatabaseHelper.getDao(Action::class.java)

    fun add(action: Action): CreateOrUpdateStatus = dao.createOrUpdate(action)

    fun update(action: Action): Int = dao.update(action)

    fun delete(action: Action): Int = dao.delete(action)

    fun queryForAll(): MutableList<Action> = dao.queryForAll()

    fun removeAll() {
        for (entity in queryForAll()) {
            dao.delete(entity)
        }
    }

}
