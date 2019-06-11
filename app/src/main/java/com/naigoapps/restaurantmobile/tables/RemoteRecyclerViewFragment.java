package com.naigoapps.restaurantmobile.tables;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.View;

import com.naigoapps.restaurantmobile.R;
import com.naigoapps.restaurantmobile.dto.Identifiable;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

public abstract class RemoteRecyclerViewFragment<I extends Identifiable> extends RemoteDataFragment<I[]> {

    private ActionMode actionMode;

    private SwipeRefreshLayout listContainer;

    private RecyclerViewAdapter<?, I> listAdapter;

    public RemoteRecyclerViewFragment() {
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        listContainer = view.findViewById(R.id.listContainer);
        listContainer.setOnRefreshListener(this::refresh);

        RecyclerView list = view.findViewById(R.id.list);
        list.setHasFixedSize(true);
        list.setLayoutManager(new LinearLayoutManager(getContext()));

        listAdapter = createAdapter(list);
        list.setAdapter(listAdapter);
        listAdapter.initSelectionTracker(new SelectionTracker.SelectionObserver() {
            @Override
            public void onSelectionChanged() {
                super.onSelectionChanged();
                if (listAdapter.hasSelection() && actionMode == null) {
                    startActionMode();
                } else if (!listAdapter.hasSelection() && actionMode != null) {
                    stopActionMode();
                }
            }

        });
    }

    @Override
    protected void onLoad(I[] data) {
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new IdentifiableCallback(listAdapter.getData(), data));
        result.dispatchUpdatesTo(listAdapter);
        listAdapter.updateData(data);
        listContainer.setRefreshing(false);
    }

    protected boolean startActionMode() {
        ActionMode.Callback callback = createActionMode();
        if (getActivity() != null && callback != null && actionMode == null) {
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

    public void onDestroyActionMode() {
        actionMode = null;
    }

    protected abstract RecyclerViewAdapter<?, I> createAdapter(RecyclerView view);

    protected abstract ActionMode.Callback createActionMode();

    protected void clearSelection(){
        listAdapter.clearSelection();
    }

    public String getSelection() {
        return listAdapter.getSelection();
    }

}
