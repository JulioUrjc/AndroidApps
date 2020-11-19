package com.july.myloginscreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Esta clase hace uso del repositorio e implementa las funciones
 *
 * @author July Maker
 * @version 2020.00
 */

class ContactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ContactsRepository(application)
    val contacts = repository.getContacts()

    fun saveContact(contact: Contact) {

        contacts?.let{
            for(contacto in contacts.value!!) {
                if(contacto?.firstName == contact?.firstName){
                    contact.contactId=contacto.contactId
                    repository.update(contact)
                    return
                }
            }
        }
        repository.insert(contact)
    }

    //fun updateContact(contact: Contact) {
    //    repository.update(contact)
    //}
    //
    //fun deleteContact(contact: Contact) {
    //    repository.delete(contact)
    //}

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