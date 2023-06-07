package com.naigoapps.restaurantmobile.common;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.Identifiable;

public abstract class ListFragment<I extends Identifiable> extends DataFragment<I[]> {

    private ActionMode actionMode;

    private RecyclerView list;
    private RecyclerViewAdapter<?, I> listAdapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(list.getContext()));

        listAdapter = createAdapter(list);
        list.setAdapter(listAdapter);

        listAdapter.observeSelection(this, selection -> {
            if (listAdapter.hasSelection() && actionMode == null) {
                startActionMode();
            } else if (!listAdapter.hasSelection() && actionMode != null) {
                stopActionMode();
            }
        });
    }

    @Override
    protected void onChange(I[] data) {
        listAdapter.updateData(data);
    }

    protected boolean startActionMode() {
        ActionMode.Callback callback = new ListFragment.ListFragmentCallback();
        if (getActivity() != null && actionMode == null) {
            actionMode = getActivity().startActionMode(callback);
            return true;
        }
        return false;
    }

    protected void stopActionMode() {
        if (actionMode != null) {
            actionMode.finish();
        }
    }

    protected abstract RecyclerViewAdapter<?, I> createAdapter(RecyclerView view);

    protected void clearSelection() {
        listAdapter.clearSelection();
    }

    public String getSelection() {
        return listAdapter.getSelection();
    }

    public abstract Integer getContextualMenuId();

    public abstract boolean onContextualMenuClick(int menuItemId);

    public class ListFragmentCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            if (getContextualMenuId() != null) {
                MenuInflater inflater = mode.getMenuInflater();
                inflater.inflate(getContextualMenuId(), menu);
                return true;
            }
            return false;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            return onContextualMenuClick(item.getItemId());
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            clearSelection();
        }
    }

}
