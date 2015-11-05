package nl.tschout.tastekid;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private ArrayList<ResultItem> mResults = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private String nameinvoer;
    private String typeinvoer;

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, final int position) {
        final ResultItem item = mResults.get(position);
        holder.name.setText(item.getName());
        setNameinvoer(item.getName());
        setTypeinvoer(item.getType());

        holder.type.setText(item.getType());
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HIER ONTVANG JE DE KLIK OP ALLEEN DE KNOP
                Log.d("LIKE", "onClick");
                Log.d(item.getName(), item.getName());

            }
        });
    }

    public String getNameinvoer() {
        return nameinvoer;
    }
    public String getTypeinvoer() {
        return typeinvoer;
    }
    public void setNameinvoer(String nameinvoer) {
        this.nameinvoer = nameinvoer;
    }
    public void setTypeinvoer(String typeinvoer) {
        this.typeinvoer = typeinvoer;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name, type;
        public ImageButton like;

        public ViewHolder(View view) {
            super(view);
            name = (TextView)view.findViewById(R.id.activity_list_row_name);
            type = (TextView)view.findViewById(R.id.activity_list_row_type);
            like = (ImageButton)view.findViewById(R.id.activity_list_row_like);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null)
                mOnItemClickListener.onItemClick(mResults.get(getAdapterPosition()));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(ResultItem result);
    }

    @Override
    public int getItemCount() {
        return mResults.size();
    }

    public void setItems(ArrayList<ResultItem> items) {
        if (items != null && items.size() > 0)
            mResults = items;
        notifyDataSetChanged();
    }
}