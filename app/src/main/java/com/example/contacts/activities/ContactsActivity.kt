package com.example.contacts.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.adapters.ContactsAdapter
import com.example.contacts.databinding.ActivityContactsBinding
import com.example.contacts.model.Contact
import com.example.contacts.util.Constants


class ContactsActivity : AppCompatActivity() {
    lateinit var binding: ActivityContactsBinding
    private lateinit var adapter: ContactsAdapter
    val contacts = mutableListOf<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        onSaveButtonClick()
        adapter.onContactClickListener =
            ContactsAdapter.OnContactClickListener { contact ->
                val intent = Intent(this, ContactDetailsActivity::class.java)
                intent.putExtra(Constants.CONTACT, contact)
                startActivity(intent)
            }

    }

    private fun initRecyclerView() {
        adapter = ContactsAdapter(contacts)
        binding.rvContacts.adapter = adapter
    }

    private fun onSaveButtonClick() {
        binding.saveBtn.setOnClickListener {
            if (!validateTextFields()) {
                showToast()
                hideKeyboard()
                clearTextFieldsFocus()
                return@setOnClickListener

            }

            val contact = Contact(
                binding.nameEdt.text?.trim().toString(),
                binding.phoneEdt.text?.trim().toString(),
                binding.descriptionEdt.text?.trim().toString()
            )
            contacts.add(contact)
            adapter.notifyItemInserted(contacts.size - 1)
            clearTextFields()
            hideKeyboard()
            clearTextFieldsFocus()
        }
    }


    private fun showToast() {
        Toast.makeText(
            this,
            "Please Check The Required Fields,\n and Try Again!",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun clearTextFieldsFocus() {
        binding.nameEdt.clearFocus()
        binding.phoneEdt.clearFocus()
        binding.descriptionEdt.clearFocus()
    }

    private fun clearTextFields() {
        binding.nameEdt.text?.clear()
        binding.phoneEdt.text?.clear()
        binding.descriptionEdt.text?.clear()
    }

    private fun validateTextFields(): Boolean {
        val name = binding.nameEdt.text.toString().trim()
        val phone = binding.phoneEdt.text.toString()
        binding.nameTil.helperText = validateName(name)
        binding.phoneTil.helperText = validatePhone(phone)
        return (validateName(name) == null) && (validatePhone(phone) == null)
    }

    private fun validateName(name: String): String? {

        if (name.trim().isEmpty())
            return "Required"

        if (name.trim().length < 3)
            return "Name can't be less than 3 characters"

        if (!Regex("[^A-Za-z]").containsMatchIn(name.trim()))
            return "Name can only contain letters"

        return null
    }

    private fun validatePhone(phone: String): String? {

        if (phone.trim().isEmpty())
            return "Required"

        if (phone.trim().length < 11)
            return "Phone number can't be less than 11 digits"

        return null
    }

    private fun hideKeyboard() {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusView = currentFocus

        if (currentFocusView != null) {
            inputMethodManager.hideSoftInputFromWindow(currentFocusView.windowToken, 0)
        }
    }


}