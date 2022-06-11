package com.hyvikk.retrofitappdemo;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.hyvikk.retrofitappdemo.TransactionDatabaseModel.TransactionModel;

import java.util.List;

public class TransactionRepository {

    // below line is the create a variable
    // for dao and list for all courses.
    private TransactionDao transactionDao;
    private LiveData<List<TransactionModel>> getAllTransactionData;

    // creating a constructor for our variables
    // and passing the variables to it.
    public TransactionRepository(Application application) {
        TransactionDatabase database = TransactionDatabase.getInstance(application);
        transactionDao = database.transactionDao();
        getAllTransactionData = transactionDao.getAllTransactionData();
    }

    // creating a method to insert the data to our database.
    public void insert(TransactionModel transactionModel) {
        new InsertCourseAsyncTask(transactionDao).execute(transactionModel);
    }

//    // creating a method to update data in database.
//    public void update(CourseModal model) {
//        new UpdateCourseAsyncTask(dao).execute(model);
//    }
//
//    // creating a method to delete the data in our database.
//    public void delete(CourseModal model) {
//        new DeleteCourseAsyncTask(dao).execute(model);
//    }
//
//    // below is the method to delete all the courses.
//    public void deleteAllCourses() {
//        new DeleteAllCoursesAsyncTask(dao).execute();
//    }
//
    // below method is to read all the courses.
    public LiveData<List<TransactionModel>> getAllTransactionData() {
        return getAllTransactionData;
    }

    // we are creating a async task method to insert new course.
    private static class InsertCourseAsyncTask extends AsyncTask<TransactionModel, Void, Void> {
        private TransactionDao transactionDao;

        private InsertCourseAsyncTask(TransactionDao transactionDao) {
            this.transactionDao = transactionDao;
        }

        @Override
        protected Void doInBackground(TransactionModel... transactionModels) {

            // below line is use to insert
//            // our modal in dao.
               transactionDao.insert(transactionModels[0]);
            return null;
        }
    }

//    // we are creating a async task method to update our course.
//    private static class UpdateCourseAsyncTask extends AsyncTask<CourseModal, Void, Void> {
//        private Dao dao;
//
//        private UpdateCourseAsyncTask(Dao dao) {
//            this.dao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(CourseModal... models) {
//            // below line is use to update
//            // our modal in dao.
//            dao.update(models[0]);
//            return null;
//        }
//    }
//
//    // we are creating a async task method to delete course.
//    private static class DeleteCourseAsyncTask extends AsyncTask<CourseModal, Void, Void> {
//        private Dao dao;
//
//        private DeleteCourseAsyncTask(Dao dao) {
//            this.dao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(CourseModal... models) {
//            // below line is use to delete
//            // our course modal in dao.
//            dao.delete(models[0]);
//            return null;
//        }
//    }
//
//    // we are creating a async task method to delete all courses.
//    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void> {
//        private Dao dao;
//        private DeleteAllCoursesAsyncTask(Dao dao) {
//            this.dao = dao;
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            // on below line calling method
//            // to delete all courses.
//            dao.deleteAllCourses();
//            return null;
//        }
//    }
}
