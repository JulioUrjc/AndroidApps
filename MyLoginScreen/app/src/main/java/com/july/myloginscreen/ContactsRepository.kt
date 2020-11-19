package com.july.myloginscreen

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


/**
 * Esta clase hace uso de la interfaz ContactoDao e interactúa con la base de datos.
 *
 * @author July Maker
 * @version 2020.00
 */

class ContactsRepository(application: Application) {

    enum class ACTION{
        INSERTAR,
        ACTUALIZAR,
        BORRAR
    }

    private val contactDao: ContactDao? = ContactsDatabase.getInstance(application)?.contactDao()

    fun insert(contact: Contact) {
        if (contactDao != null) ActionAsyncTask(contactDao, ACTION.INSERTAR).execute(contact)
    }

    fun update(contact: Contact) {
        if (contactDao != null) ActionAsyncTask(contactDao, ACTION.ACTUALIZAR).execute(contact)
    }

    fun delete(contact: Contact) {
        if (contactDao != null) ActionAsyncTask(contactDao, ACTION.BORRAR).execute(contact)
    }


    fun getContacts(): LiveData<List<Contact>> {
        return contactDao?.getOrderedAgenda() ?: MutableLiveData<List<Contact>>()
    }

    private class ActionAsyncTask(private val contactDao: ContactDao, private val action:ACTION) :
        AsyncTask<Contact, Void, Void>() {
        override fun doInBackground(vararg contacts: Contact?): Void? {
            for (contact in contacts) {
                when(action){
                    ACTION.INSERTAR->{
                        if (contact != null) contactDao.insert(contact)
                    }
                    ACTION.ACTUALIZAR->{
                        if (contact != null) contactDao.update(contact)
                    }
                    ACTION.BORRAR->{
                        if (contact != null) contactDao.delete(contact)
                    }
                }

            }
            return null
        }
    }
}