package pt.hctec.theswitcher_hugo.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pt.hctec.theswitcher_hugo.R;
import pt.hctec.theswitcher_hugo.models.Division;

public class DivisionRecycleAdapter extends RecyclerView.Adapter <DivisionRecycleAdapter.DivisionHolder> {

    private List<Division> divisions = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public DivisionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.division_item, parent, false);
        return new DivisionHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull DivisionHolder holder, int position) {
        Division currentDivision = divisions.get(position);
        holder.textViewTitle.setText(currentDivision.getTitle());
        // Toogle Button
        holder.toggleStatus.setChecked(currentDivision.getState() == 1 ? true : false );
    }

    @Override
    public int getItemCount() {
        return divisions.size();
    }

    public void setDivisions(List<Division> divisions) {
        this.divisions = divisions;   // TODO
       // notifyDataSetChanged();  // Ou notifyItem inserted or removed
    }

    public Division getDivisionAt(int adapterPosition) {
            return divisions.get(adapterPosition);
    }


    class DivisionHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;

        private Switch toggleStatus;

        public DivisionHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            toggleStatus = itemView.findViewById(R.id.toggleStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(divisions.get(position));
                    }
                }
            });

            // TODO toggleStatus.setOnCheckedChangeListener();
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Division division);
    }
    // TODO on Switch toggle


    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}