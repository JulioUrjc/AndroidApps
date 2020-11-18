package com.july.myloginscreen

import androidx.lifecycle.LiveData
import androidx.room.*


/**
 * Esta interfaz define los métodos para interactuar con la base de datos.
 *
 * @author July Maker
 * @version 2020.00
 */

@Dao
interface ContactDao {
    @Insert
    fun insert(contact: Contact)

    @Update
    fun update(vararg contact: Contact)

    @Delete
    fun delete(vararg contact: Contact)

    //@Delete
    //fun delete(vararg name: String)

    @Query("SELECT * FROM " + Contact.TABLE_NAME + " ORDER BY last_name, first_name")
    fun getOrderedAgenda(): LiveData<List<Contact>>
}
