package com.hyvikk.retrofitappdemo;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionClass;
import com.hyvikk.retrofitappdemo.TransactionDatabaseModel.TransactionModel;

import java.util.List;
@Dao
public interface TransactionDao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(TransactionModel transactionModel);

    // below method is use to update
    // the data in our database.
//    @Update
//    void update(CourseModal model);

    // below line is use to delete a
    // specific course in our database.
//    @Delete
//    void delete(CourseModal model);

    // on below line we are making query to
    // delete all courses from our database.
//    @Query("DELETE FROM course_table")
//    void deleteAllCourses();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM transaction_table")
    LiveData<List<TransactionModel>> getAllTransactionData();
}
