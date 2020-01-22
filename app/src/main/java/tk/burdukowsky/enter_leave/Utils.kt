package tk.burdukowsky.enter_leave

import java.text.DateFormat
import java.util.*
import android.os.Build
import java.text.SimpleDateFormat

class Utils {
    companion object {

        private val ruLocale = Locale("ru", "RU")

        private val currentLocale: Locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            App.instance.resources.configuration.locales.get(0)
        } else {
            @Suppress("DEPRECATION")
            App.instance.resources.configuration.locale
        }

        private var dfDate: DateFormat
        private var dfTime: DateFormat

        init {
            if (currentLocale == ruLocale) {
                dfDate = SimpleDateFormat("dd.MM.yy", ruLocale)
                dfTime = SimpleDateFormat("HH:mm:ss", ruLocale)
            } else {
                dfDate = DateFormat.getDateInstance(DateFormat.SHORT, currentLocale)
                dfTime = DateFormat.getTimeInstance(DateFormat.MEDIUM, currentLocale)
            }
        }

        fun getCurrentMilliseconds(): Long {
            return System.currentTimeMillis()
        }

        fun millisecondsToFormattedTime(milliseconds: Long): FormattedTime {
            val date = Date(milliseconds)
            return FormattedTime(dfDate.format(date), dfTime.format(date))
        }

    }
}
