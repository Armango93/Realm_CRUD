package realmcrud.com.realm_crud;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {
    private static RealmController instance;
    private final Realm realm;
    Book result;

    private RealmController() {
        realm = Realm.getDefaultInstance();
        Log.i("Realm", realm.getPath());
    }

    public static RealmController getInstance() {
        if (instance == null) {
            instance = new RealmController();
        }
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    // Clear all object from Book.class
    public void clearAllBooks() {
        realm.beginTransaction();
        realm.delete(Book.class);
        realm.commitTransaction();
    }

    // Find all objects in the Book.class
    public RealmResults<Book> getBooks() {
        List<Book> listBook = realm.where(Book.class).findAll();

        return realm.where(Book.class).findAll();
    }

    // Query a single item with the given id
    public Book getBook(Integer id) {
        return realm.where(Book.class).equalTo("id", id).findFirst();
    }

    // check if Book.class is empty
    public boolean hasBooks() {
        return !realm.isEmpty();
    }

    public void addBook(Book book){
        realm.beginTransaction();
        Book bookObject = realm.createObject(Book.class);
        bookObject.setId(book.getId());
        bookObject.setTitle(book.getTitle());
        bookObject.setAuthor(book.getAuthor());
        bookObject.setDescription(book.getDescription());
        realm.commitTransaction();
    }

//    public void updateBook(int id, final Book book){
//        realm.beginTransaction();
//        result = realm.where(Book.class).equalTo("id", id).findFirst();
////        realm.executeTransaction(new Realm.Transaction() {
////            @Override
////            public void execute(Realm realm) {
////                result.setId(book.getId());
//                result.setTitle(book.getTitle());
//                result.setAuthor(book.getAuthor());
//                result.setDescription(book.getDescription());
////            }
////        });
////        realm.copyToRealmOrUpdate(result);
//        realm.commitTransaction();
//
//    }

    public void updateBook(int id, final Book book, Context context){
        final Book bookUpd = new Book();
        bookUpd.setId(id);
        bookUpd.setTitle(book.getTitle());
        bookUpd.setAuthor(book.getAuthor());
        bookUpd.setDescription(book.getDescription());

        realm.beginTransaction();

                realm.copyToRealmOrUpdate(bookUpd);

        realm.commitTransaction();

        System.out.println("Update was successfull! Transaction commited.");
        Toast.makeText(context, "Update was successfull!", Toast.LENGTH_LONG);
    }

    public void deleteBook(Integer id){
        realm.beginTransaction();
        final Book result = realm.where(Book.class).equalTo("id", id).findFirst();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm();
            }
        });
        realm.commitTransaction();
    }

}
