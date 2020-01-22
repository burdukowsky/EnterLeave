package tk.burdukowsky.enter_leave

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "actions")
data class Action(

    @DatabaseField(generatedId = true)
    var id: Int? = null,

    @DatabaseField
    var type: ActionType = ActionType.ENTER,

    @DatabaseField
    var time: Long = 0

) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Action

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }

}
