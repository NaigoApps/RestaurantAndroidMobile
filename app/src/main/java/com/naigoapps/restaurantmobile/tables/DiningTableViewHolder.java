package com.naigoapps.restaurantmobile.tables;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.DiningTableDTO;

import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Locale;

public class DiningTableViewHolder extends IdentifiableViewHolder {
    private MaterialCardView cardView;
    private TextView coverChargesView;
    private TextView waiterView;
    private TextView tableView;
    private TextView dateView;

    private DiningTableDTO tableDTO;

    public DiningTableViewHolder(DiningTablesAdapter adapter, View v) {
        super(adapter, v);
        cardView = v.findViewById(R.id.diningTableContainer);
        coverChargesView = v.findViewById(R.id.coverChargesText);
        waiterView = v.findViewById(R.id.waiterText);
        tableView = v.findViewById(R.id.tableText);
        dateView = v.findViewById(R.id.dateText);
    }

    public void setDiningTable(DiningTableDTO dto) {
        this.tableDTO = dto;
        if (dto.getCoverCharges() == 1) {
            coverChargesView.setText(String.format(Locale.getDefault(), "%d coperto", dto.getCoverCharges()));
        } else {
            coverChargesView.setText(String.format(Locale.getDefault(), "%d coperti", dto.getCoverCharges()));
        }
        waiterView.setText(String.format("Cameriere: %s", dto.getWaiter().toString()));
        tableView.setText(String.format("Tavolo: %s", dto.getTable().toString()));
        dateView.setText(DateTimeFormat.forPattern("'Ore' HH:mm").print(dto.getOpeningTime()));
        updateBackground();
    }

    public void setSelected(boolean selected) {
        cardView.setActivated(selected);
    }

    private void updateBackground() {
        if (tableDTO == null) {
            return;
        }
        if (cardView.isActivated()) {
            cardView.setCardElevation(4);
            cardView.setCardBackgroundColor(cardView.getContext().getColor(R.color.colorAccent));
            return;
        }
        cardView.setCardElevation(1);
        switch (tableDTO.getStatus()) {
            case OPEN:
                cardView.setCardBackgroundColor(cardView.getContext().getColor(R.color.colorDanger));
                break;
            case CLOSING:
                cardView.setCardBackgroundColor(cardView.getContext().getColor(R.color.colorWarning));
                break;
            case CLOSED:
                cardView.setCardBackgroundColor(cardView.getContext().getColor(R.color.colorTransparent));
                break;
        }
    }
}