package com.naigoapps.restaurantmobile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.naigoapps.restaurantmobile.dto.DiningTableDTO;
import com.naigoapps.restaurantmobile.dto.EveningDTO;
import com.naigoapps.restaurantmobile.rs.DiningTablesRS;
import com.naigoapps.restaurantmobile.rs.EveningRS;
import com.naigoapps.restaurantmobile.rs.RSFactory;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private SwipeRefreshLayout refreshLayout;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        refreshLayout = view.findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(this::loadEvening);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadEvening();
    }

    private void loadEvening(){
        EveningLoadTask eveningLoadTask = new EveningLoadTask();
        eveningLoadTask.execute();
    }

    private class EveningLoadTask extends SimpleTask<Void, EveningDTO> {

        @Override
        protected void onPreExecute() {
            refreshLayout.setRefreshing(true);
        }

        @Override
        protected EveningDTO doInBackground(Void... voids) {
            EveningRS rs = RSFactory.createService(getContext(), EveningRS.class);
            EveningDTO result = null;
            try{
                Response<EveningDTO> response = rs.selected().execute();
                if(response.isSuccessful()){
                    result = response.body();
                }
            }catch (IOException ex){
                Log.e("Network error", ex.getMessage(), ex);
            }
            return result;
        }

        @Override
        protected void onPostExecute(EveningDTO eveningDTO) {
            refreshLayout.setRefreshing(false);
            if(eveningDTO != null){
                Navigation.findNavController(getView()).navigate(R.id.actionLoadTables);
            }else{
                Toast.makeText(getContext(), "Not loaded", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
