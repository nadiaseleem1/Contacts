package com.example.contacts.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.R
import com.example.contacts.databinding.ActivityContactDetailsBinding
import com.example.contacts.model.Contact
import com.example.contacts.util.Constants

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionBar()
        setActivityContent()

    }

    private fun setActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.back)
        supportActionBar?.title = "Contact details"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setActivityContent() {
        val contact = intent.parcelable<Contact>(Constants.CONTACT)
        binding.nameValue.text = contact?.name
        binding.phoneValue.text = contact?.phone
        binding.descriptionValue.text = contact?.description
        binding.nameTv.text = contact?.name
        binding.descriptionValue.movementMethod = ScrollingMovementMethod()

    }

}

inline fun <reified T : Parcelable> Intent.parcelable(key: String): T? = when {
    Build.VERSION.SDK_INT >= 33 -> getParcelableExtra(key, T::class.java)
    else -> @Suppress("DEPRECATION") getParcelableExtra(key) as? T
}
