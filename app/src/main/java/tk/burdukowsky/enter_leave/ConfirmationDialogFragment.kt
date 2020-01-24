package tk.burdukowsky.enter_leave

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class ConfirmationDialogFragment(
    private val message: Int = R.string.confirmation_message,
    private val positiveButtonText: Int = R.string.yes,
    private val negativeButtonText: Int = R.string.no,
    private val positiveOnClickListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ -> },
    private val negativeOnClickListener: DialogInterface.OnClickListener = DialogInterface.OnClickListener { _, _ -> }
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            AlertDialog.Builder(it)
                .setMessage(message)
                .setPositiveButton(positiveButtonText, positiveOnClickListener)
                .setNegativeButton(negativeButtonText, negativeOnClickListener)
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}
