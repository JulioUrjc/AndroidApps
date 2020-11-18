package com.july.myloginscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel


class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContactsRepository(application)
    val contacts = repository.getContacts()

    fun saveContact(contact: Contact) {
        repository.insert(contact)
    }

    fun updateContact(contact: Contact) {
        repository.update(contact)
    }

    fun deleteContact(contact: Contact) {
        repository.delete(contact)
    }

    fun deleteContact(name: String) {

        contacts?.let{
            for(contacto in contacts.value!!) {
                if(contacto?.firstName == name){
                    repository.delete(contacto)
                    break
                }
            }
        }

    }
}