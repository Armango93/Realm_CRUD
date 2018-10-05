package realmcrud.com.realm_crud;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Book> listOfBooks = new ArrayList<>();
    Button btnAddBook;
    Button btnGetBooks;
    RecyclerView recyclerView;
    RealmController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controller = RealmController.getInstance();

        btnAddBook = (Button) findViewById(R.id.btnAddBook);
        btnGetBooks = (Button) findViewById(R.id.btnGetBookList);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BookActivity.class);
                intent.putExtra("bookName", "");
                startActivity(intent);
            }
        });

        btnGetBooks.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                getBookList();
            }
        });
    }

    public void getBookList() {
        controller.getRealm().beginTransaction();
        listOfBooks = controller.getBooks();

        BookAdapter adapter = new BookAdapter(this, listOfBooks);
        recyclerView.setAdapter(adapter);
        controller.getRealm().commitTransaction();
    }
}
