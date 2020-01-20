package tk.burdukowsky.enter_leave

import com.j256.ormlite.dao.Dao

class ActionDao {

    companion object {
        lateinit var dao: Dao<Action, Int>
    }

    init {
        dao = DatabaseHelper.getDao(Action::class.java)
    }

    fun add(action: Action) = dao.createOrUpdate(action)

    fun update(action: Action) = dao.update(action)

    fun delete(action: Action) = dao.delete(action)

    fun queryForAll(): MutableList<Action> = dao.queryForAll()

    fun removeAll() {
        for (entity in queryForAll()) {
            dao.delete(entity)
        }
    }

}
