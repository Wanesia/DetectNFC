package com.example.detectnfc

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NdefMessage
import android.nfc.NfcAdapter
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Detect : AppCompatActivity() {

    var nfcAdapter: NfcAdapter? = null
    var pendingIntent: PendingIntent? = null
    private var intentFiltersArray: Array<IntentFilter>? = null
    private var techListsArray: Array<Array<String>>? = null
    private var messageTextView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wear)
        messageTextView = findViewById(R.id.messageTextView)

        Log.d("create","created");

        nfcAdapter = NfcAdapter.getDefaultAdapter(this)

        if (nfcAdapter == null) {
            // NFC is not available for device
            Log.d("detect", "nfc not available")
            Toast.makeText(
                this, "NO NFC",
                Toast.LENGTH_SHORT
            ).show()
            finish()

        } else if (!nfcAdapter!!.isEnabled()) {
            // NFC is available for device but not enabled
            Log.d("detect", "nfc not on")
            Toast.makeText(
                this, "Turn on NFC",
                Toast.LENGTH_SHORT
            ).show()
            finish()

        } else {
            // NFC is enabled
            Log.d("detect", "nfc detected")
            Toast.makeText(
                this, "NFC detected",
                Toast.LENGTH_SHORT
            ).show()

        }
        // If no NfcAdapter, display that the device has no NFC
        if (nfcAdapter == null) {
            Log.d("detect", "nfc not detected")

            Toast.makeText(
                this, "NO NFC",
                Toast.LENGTH_SHORT
            ).show()
            finish()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
                // Process the messages array.
                Log.d("detect", "detected")
                // Show a Toast or perform other actions here.
            }
        }
    }


    override fun onResume() {
        super.onResume()
        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
            Toast.makeText(this, "NFC!", Toast.LENGTH_LONG).show()
            // Here you could start your Fragment or perform other actions.
        }
    }



//    override fun onNewIntent(intent: Intent) {
//        super.onNewIntent(intent)
//        Log.d("message1","start");
//        Toast.makeText(
//            this, "NFC DETECTED",
//            Toast.LENGTH_LONG
//        ).show()
//        finish()
//
//        if (NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
//            intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES)?.also { rawMessages ->
//                val messages: List<NdefMessage> = rawMessages.map { it as NdefMessage }
//                // Process the messages array.
//                Log.d("detect","detected");
//            }
//        }
//    }



//    override fun onResume() {
//        super.onResume()
//        val intent = intent
//        if (intent != null && NfcAdapter.ACTION_NDEF_DISCOVERED == intent.action) {
//            Toast.makeText(this,"NFC!", Toast.LENGTH_LONG).show()
//            // Here you could start your Fragment
//        }
//    }

}