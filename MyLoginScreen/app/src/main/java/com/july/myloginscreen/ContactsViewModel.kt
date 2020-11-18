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
        var contact: Contact? = null

        //contacts?.let{
        //    val size: Int = it.value?.size ?: 0
        //    var contact:Contact? = null
        //    for(i in 0..size) {
        //        contact = it.value?.get(i)
        //        if(contact?.firstName == name){
        //            repository.delete(contact)
        //            break
        //        }
        //    }
        //}
    }
}