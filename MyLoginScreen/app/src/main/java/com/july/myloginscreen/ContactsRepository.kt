package com.july.myloginscreen

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * Esta clase hace uso de la interfaz NotaDao e interactúa con la base de datos.
 *
 * @author Julio Maker
 * @version 2020.00
 */

class ContactsRepository(application: Application) {

    private val contactDao: ContactDao? = ContactsDatabase.getInstance(application)?.contactDao()

    fun insert(contact: Contact) {
        if (contactDao != null) InsertAsyncTask(contactDao).execute(contact)
    }

    fun update(contact: Contact) {
        if (contactDao != null) contactDao.update(contact)
    }

    fun delete(contact: Contact) {
        if (contactDao != null) DeleteAsyncTask(contactDao).execute(contact)
    }


    fun getContacts(): LiveData<List<Contact>> {
        return contactDao?.getOrderedAgenda() ?: MutableLiveData<List<Contact>>()
    }

    private class InsertAsyncTask(private val contactDao: ContactDao) :
        AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contacts: Contact?): Void? {
            for (contact in contacts) {
                if (contact != null) contactDao.insert(contact)
            }
            return null
        }
    }

    private class DeleteAsyncTask(private val contactDao: ContactDao) :
        AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contacts: Contact?): Void? {
            for (contact in contacts) {
                if (contact != null) contactDao.delete(contact)
            }
            return null
        }
    }
}