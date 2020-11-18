package com.july.myloginscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private lateinit var contactsViewModel: ContactsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        contactsViewModel = run {
            ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        }

        addContact_button.setOnClickListener { addContact() }
        delContact_button.setOnClickListener { delContact() }
        addObserver()
    }

    private fun addObserver() {
        val observer = Observer<List<Contact>> { contacts ->
            if (contacts != null) {
                var text = ""
                for (contact in contacts) {
                    text += contact.lastName + " " + contact.firstName + " - " + contact.phoneNumber + "\n"
                }
                contacts_textView.text = text
            }
        }
        contactsViewModel.contacts.observe(this, observer)
    }

    private fun delContact() {
        val name = fistName_editText.text.toString()

        contactsViewModel.deleteContact(name)
    }

    private fun addContact() {
        val phone = phone_editText.text.toString()
        val name = fistName_editText.text.toString()
        val lastName =
            if (lastName_editText.text.toString() != "") lastName_editText.text.toString()
            else null

        if (name != "" && phone != "") contactsViewModel.saveContact(Contact(phone, name, lastName))
    }
}