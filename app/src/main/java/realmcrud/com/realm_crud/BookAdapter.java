package realmcrud.com.realm_crud;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    LayoutInflater inflater;
    List<Book> listOfBooks = new ArrayList<>();

    public BookAdapter(Context context, List<Book> list){
        inflater = LayoutInflater.from(context);
        listOfBooks = list;
        System.out.println(listOfBooks);
    }


    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder viewHolder, int i) {
        Book bindBook = listOfBooks.get(i);
        System.out.println(bindBook);
//        viewHolder.id.setText(bindBook.getId());
        viewHolder.title.setText(bindBook.getTitle());
        viewHolder.author.setText(bindBook.getAuthor());
        viewHolder.desc.setText(bindBook.getDescription());
    }

    @Override
    public int getItemCount() {
        return listOfBooks.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView id, title, author, desc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewId);
            title = itemView.findViewById(R.id.textViewTitle);
            author = itemView.findViewById(R.id.textViewAuthor);
            desc = itemView.findViewById(R.id.textViewDesc);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), BookActivity.class);
            intent.putExtra("id", listOfBooks.get(getAdapterPosition()).getId());
            intent.putExtra("title", listOfBooks.get(getAdapterPosition()).getTitle());
            intent.putExtra("author", listOfBooks.get(getAdapterPosition()).getAuthor());
            intent.putExtra("description", listOfBooks.get(getAdapterPosition()).getDescription());

            v.getContext().startActivity(intent);
        }
    }
}
