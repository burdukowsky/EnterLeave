package tk.burdukowsky.enter_leave

enum class ActionType(private val resourceId: Int) {
    ENTER(R.string.enter),
    LEAVE(R.string.leave);

    fun getResource() = App.instance.applicationContext.getString(resourceId)
}
