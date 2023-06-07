package com.naigoapps.restaurantmobile.common;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.Identifiable;

public abstract class RemoteCrudListFragment<I extends Identifiable> extends RemoteDataFragment<I[]> {

    private ActionMode actionMode;

    private SwipeRefreshLayout listContainer;
    private RecyclerView list;
    private FloatingActionButton addButton;
    private RecyclerViewAdapter<?, I> listAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.crud_list_view, container, false);

        listContainer = result.findViewById(R.id.listContainer);
        list = result.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        addButton = result.findViewById(R.id.btnAdd);

        return result;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listContainer.setOnRefreshListener(this::refresh);

        listAdapter = createAdapter(list);
        list.setAdapter(listAdapter);

        listAdapter.observeSelection(this, selection -> {
            if (listAdapter.hasSelection() && actionMode == null) {
                startActionMode();
            } else if (!listAdapter.hasSelection() && actionMode != null) {
                stopActionMode();
            }
//            listAdapter.refreshViewHolders();
        });

        addButton.setOnClickListener((evt) -> this.onAdd());
    }

    @Override
    protected void onLoad(I[] data) {
        listAdapter.updateData(data);
        if (listContainer != null) {
            listContainer.setRefreshing(false);
        }
    }

    protected void startActionMode() {
        ActionMode.Callback callback = new RemoteCrudListFragmentCallback();
        if (getActivity() != null && actionMode == null) {
            actionMode = getActivity().startActionMode(callback);
        }
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

    public abstract void onAdd();

    public abstract Integer getContextualMenuId();

    public abstract boolean onContextualMenuClick(int menuItemId);

    public class RemoteCrudListFragmentCallback implements ActionMode.Callback {

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
            boolean result = onContextualMenuClick(item.getItemId());
            if(result){
                clearSelection();
            }
            return result;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            clearSelection();
        }
    }
}
