package com.naigoapps.restaurantmobile.tables;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.common.RecyclerViewAdapter;
import com.naigoapps.restaurantmobile.common.SelectionType;
import com.naigoapps.restaurantmobile.common.Utils;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;

import org.joda.time.format.DateTimeFormat;

import java.util.Locale;

public class DiningTablesAdapter extends RecyclerViewAdapter<DiningTablesAdapter.DiningTableViewHolder, DiningTableDTO> {

    public DiningTablesAdapter(Fragment fragment, RecyclerView view) {
        super(fragment, view, SelectionType.ONE);
    }

    @NonNull
    @Override
    public DiningTableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DiningTableViewHolder(this, LayoutInflater.from(parent.getContext()).inflate(R.layout.dining_table_item, parent, false));
    }

    public class DiningTableViewHolder extends RecyclerViewAdapter<DiningTableViewHolder, DiningTableDTO>.IdentifiableViewHolder {
        private MaterialCardView cardView;
        private TextView coverChargesView;
        private TextView waiterView;
        private TextView tableView;
        private TextView dateView;

        public DiningTableViewHolder(DiningTablesAdapter adapter, View v) {
            super(v);
            cardView = v.findViewById(R.id.diningTableContainer);
            coverChargesView = v.findViewById(R.id.coverChargesText);
            waiterView = v.findViewById(R.id.waiterText);
            tableView = v.findViewById(R.id.tableText);
            dateView = v.findViewById(R.id.dateText);
        }

        @Override
        public View.OnClickListener getClickListener(DiningTableDTO dto) {
            return Navigation.createNavigateOnClickListener(R.id.selectTable, DiningTablesFragmentDirections
                    .selectTable(dto.getUuid(), dto.format())
                    .setTableUuid(dto.getUuid())
                    .setTableName(dto.format())
                    .getArguments());
        }

        @Override
        public void updateView(DiningTableDTO dto, boolean selected) {
            if (dto.getCoverCharges() == 1) {
                coverChargesView.setText(String.format(Locale.getDefault(), "%d coperto", dto.getCoverCharges()));
            } else {
                coverChargesView.setText(String.format(Locale.getDefault(), "%d coperti", dto.getCoverCharges()));
            }
            waiterView.setText(String.format("Cameriere: %s", Utils.string(dto.getWaiter())));
            tableView.setText(String.format("Tavolo: %s", Utils.string(dto.getTable())));
            dateView.setText(DateTimeFormat.forPattern("'Ore' HH:mm").print(dto.getOpeningTime()));
            updateBackground(dto, selected);
        }

        private void updateBackground(DiningTableDTO dto, boolean selected) {
            if (dto == null) {
                return;
            }
            if (selected) {
                cardView.setCardElevation(4);
                cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorAccent));
                return;
            }
            cardView.setCardElevation(1);
            switch (dto.getStatus()) {
                case OPEN:
                    cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorDanger));
                    break;
                case CLOSING:
                    cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorWarning));
                    break;
                case CLOSED:
                    cardView.setCardBackgroundColor(Utils.color(cardView.getContext(), R.color.colorTransparent));
                    break;
            }
        }

        @Override
        public View getMainView() {
            return cardView;
        }
    }
}
