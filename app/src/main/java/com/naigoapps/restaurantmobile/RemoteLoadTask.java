package com.naigoapps.restaurantmobile;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.lang.ref.WeakReference;

import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentActivity;
import retrofit2.Response;

public abstract class RemoteLoadTask<R> extends SimpleTask<Void, R> {
    private final Consumer<String> errorConsumer;
    private WeakReference<FragmentActivity> activity;
    protected Consumer<R> consumer;
    private String errorMessage;

    public RemoteLoadTask(FragmentActivity activity, Consumer<R> consumer) {
        this(activity, consumer, null);
    }

    public RemoteLoadTask(FragmentActivity activity, Consumer<R> consumer, Consumer<String> errorConsumer) {
        this.activity = new WeakReference<>(activity);
        this.consumer = consumer;
        this.errorConsumer = errorConsumer;
    }

    @Override
    protected R doInBackground(Void... voids) {
        R result = null;
        if (activity.get() != null) {
            try {
                Response<R> response = makeRequest(activity.get());
                if (response.isSuccessful()) {
                    result = response.body();
                }else if(response.errorBody() != null){
                    errorMessage = response.errorBody().string();
                }
            } catch (IOException e) {
                Log.e("Error", "Error", e);
            }
        }
        return result;
    }

    protected abstract Response<R> makeRequest(FragmentActivity activity) throws IOException;

    @Override
    protected void onPostExecute(R r) {
        this.consumer.accept(r);
        if(r == null && errorMessage != null){
            if(errorConsumer != null) {
                errorConsumer.accept(errorMessage);
            }else{
                showError(errorMessage);
            }
        }
    }

    private void showError(String msg){
        if(activity.get() != null) {
            Toast.makeText(activity.get(), msg, Toast.LENGTH_SHORT).show();
        }
    }
}
