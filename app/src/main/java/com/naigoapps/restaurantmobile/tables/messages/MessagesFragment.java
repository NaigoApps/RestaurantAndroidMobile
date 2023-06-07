package com.naigoapps.restaurantmobile.tables.messages;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.LocationDTO;
import com.naigoapps.restaurantmobile.dto.MessageRequestDTO;
import com.naigoapps.restaurantmobile.tasks.LocationsLoadTask;
import com.naigoapps.restaurantmobile.tasks.diningTables.DiningTableSkeletonLoadTask;

import java.util.List;

public class MessagesFragment extends Fragment {

    private TextView titleView;
    private TextInputEditText messageField;
    private RecyclerView locationsList;
    private Button sendButton;

    private String tableUuid;
    private String tableName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_messages, container, false);
        titleView = result.findViewById(R.id.titleView);
        messageField = result.findViewById(R.id.messageField);
        locationsList = result.findViewById(R.id.locationsList);
        sendButton = result.findViewById(R.id.sendButton);
        return result;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tableUuid = MessagesFragmentArgs.fromBundle(getArguments()).getTableUuid();
        if (tableUuid != null) {
            DiningTableSkeletonLoadTask task = new DiningTableSkeletonLoadTask(tableDTO -> {
                tableName = tableDTO.format();
                titleView.setText(getString(R.string.send_message_for, tableName));
            }, tableUuid);
            task.execute();
        }

        CheckableLocationsAdapter adapter = new CheckableLocationsAdapter(this, locationsList);
        locationsList.setLayoutManager(new LinearLayoutManager(getContext()));
        locationsList.setAdapter(adapter);
        LocationsLoadTask task = new LocationsLoadTask(adapter::updateData);
        task.execute();

        sendButton.setOnClickListener(evt -> {
            MessageRequestDTO dto = new MessageRequestDTO();
            String message = messageField.getText().toString();
            List<LocationDTO> locations = adapter.getSelectedDatas();
            String[] locationUuids = new String[locations.size()];
            for(int i = 0;i < locations.size();i++){
                locationUuids[i] = locations.get(i).getUuid();
            }

            dto.setMessage(message);
            dto.setLocationUuids(locationUuids);
            dto.setTableUuid(tableUuid);

            MessagePrintTask printTask = new MessagePrintTask(dto, res -> NavHostFragment.findNavController(this).navigateUp());
            printTask.execute();
        });
    }
}
