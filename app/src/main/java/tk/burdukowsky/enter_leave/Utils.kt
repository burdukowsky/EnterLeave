package tk.burdukowsky.enter_leave

import java.text.DateFormat
import java.util.*
import android.os.Build
import java.text.SimpleDateFormat

val ruLocale = Locale("ru", "RU")

val currentLocale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    App.instance.resources.configuration.locales.get(0)
} else {
    App.instance.resources.configuration.locale
}

val df: DateFormat = if (currentLocale == ruLocale) {
    SimpleDateFormat("dd.MM.yy HH:mm", ruLocale)
} else {
    DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, currentLocale)
}

fun getCurrentMilliseconds(): Long {
    return System.currentTimeMillis()
}

fun formatMilliseconds(milliseconds: Long): String {
    return df.format(Date(milliseconds))
}
