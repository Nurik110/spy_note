package space.nurik.note_spy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DocAdapter extends RecyclerView.Adapter<DocAdapter.ViewHolder>{

    interface OnStateClickListener{
        void onStateClick(DocName state, int position);
    }

    private final LayoutInflater inflater;
    private final List<DocName> states;
    private final OnStateClickListener onClickListener;


    DocAdapter(Context context, List<DocName> states, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.states = states;
        this.inflater = LayoutInflater.from(context);
    }
//    DocAdapter(Context context, List<DocName> states){
//        this.states = states;
//        this.inflater = LayoutInflater.from(context);
//    }

//    public DocAdapter(LayoutInflater inflater, List<DocName> states) {
//        this.inflater = inflater;
//        this.states = states;
//    }

    @Override
    public DocAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.rectangle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DocAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DocName state = states.get(position);
        holder.nameView.setText(state.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(state, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView;
        ViewHolder(View view){
            super(view);
            nameView = view.findViewById(R.id.tv_1);
        }
    }
}
