package realmcrud.com.realm_crud;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class BookActivity extends AppCompatActivity {
    RealmController controller;

    EditText editFormID;
    EditText editFormTitle;
    EditText editFormAuthor;
    EditText editFormDesc;

    Button btnSave;
    Button btnDelete;
    Integer bookID;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        controller = RealmController.getInstance();

        btnSave = (Button) findViewById(R.id.buttonSave);
        btnDelete = (Button) findViewById(R.id.buttonDelete);

        editFormID = (EditText) findViewById(R.id.editTextID);
        editFormTitle = (EditText) findViewById(R.id.editTextTitle);
        editFormAuthor = (EditText) findViewById(R.id.editTextAuthor);
        editFormDesc = (EditText) findViewById(R.id.editTextDesc);

        final Bundle extras = getIntent().getExtras();
//            Book bookFromIntent = (Book) extras.get("book");
        bookID = extras.getInt("id");
//            final Integer bookId = bookFromIntent.getId();
        String title = extras.getString("title");
//            String title = (String) bookFromIntent.getTitle();
        String author = extras.getString("author");
//            String author = (String) bookFromIntent.getAuthor();
        String description = extras.getString("description");
//            String description = bookFromIntent.getDescription();

            editFormID.setText(bookID.toString());
            editFormTitle.setText(title);
            editFormAuthor.setText(author);
            editFormDesc.setText(description);


            if(bookID!=null && bookID.toString().trim().length()>0){
                editFormID.setFocusable(false);
            } else{
                btnDelete.setVisibility(View.INVISIBLE);
                editFormID.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Book book = new Book();
                book.setId(editFormID.getId());
                book.setTitle(editFormTitle.getText().toString());
                book.setAuthor(editFormAuthor.getText().toString());
                book.setDescription(editFormDesc.getText().toString());

                if(editFormTitle.getText()!=null && editFormTitle.getText().toString().trim().length()>0){
                    updateBook(bookID, book, getContext());
                } else{
                    addBook(book);
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final String userId = String.valueOf(extras.getInt("id"));
                deleteBook(Integer.parseInt(userId));
            }
        });
    }

    public void addBook(Book book){
        controller.addBook(book);
    }

    public void updateBook(int id, final Book book, Context context){
        controller.updateBook(id, book, context);
    }
    public void deleteBook(final int id){
        controller.deleteBook(id);
    }

    public Context getContext(){
        return this;
    }


}
