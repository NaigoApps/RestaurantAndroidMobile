package com.naigoapps.restaurantmobile;

import android.util.Log;
import android.widget.Toast;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;

import java.io.IOException;

import retrofit2.Response;

public abstract class RemoteLoadTask<R> extends SimpleTask<Void, R> {
    private final Consumer<String> errorConsumer;
    protected Consumer<R> consumer;
    private String errorMessage;

    public RemoteLoadTask(Consumer<R> consumer) {
        this(consumer, null);
    }

    public RemoteLoadTask(Consumer<R> consumer, Consumer<String> errorConsumer) {
        this.consumer = consumer;
        this.errorConsumer = errorConsumer;
    }

    @Override
    protected R doInBackground(Void... voids) {
        R result = null;
        FragmentActivity activity = Application.getActivity();
        if (activity != null) {
            try {
                Response<R> response = makeRequest(activity);
                if (response.isSuccessful()) {
                    result = response.body();
                } else if (response.errorBody() != null) {
                    errorMessage = response.errorBody().string();
                }
            } catch (IOException e) {
                errorMessage = "Errore di connessione";
                Log.e("Error", "Error", e);
            }
        }
        return result;
    }

    protected abstract Response<R> makeRequest(FragmentActivity activity) throws IOException;

    @Override
    protected void onPostExecute(R r) {
        this.consumer.accept(r);
        if (r == null && errorMessage != null) {
            if (errorConsumer != null) {
                errorConsumer.accept(errorMessage);
            } else {
                showError(errorMessage);
            }
        }
    }

    private void showError(String msg) {
        Toast.makeText(Application.getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
