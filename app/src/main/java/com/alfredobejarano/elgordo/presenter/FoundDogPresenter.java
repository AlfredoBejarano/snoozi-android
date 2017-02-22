package com.alfredobejarano.elgordo.presenter;

import android.util.Log;

import com.alfredobejarano.elgordo.model.Dog;
import com.alfredobejarano.elgordo.model.DogUploadPOJO;
import com.alfredobejarano.elgordo.network.ObjectMapper;
import com.alfredobejarano.elgordo.presenter.base.Presenter;
import com.alfredobejarano.elgordo.view.base.View;

import org.json.JSONObject;

import java.util.List;

/**
 * Created by Alfredo on 22/02/2017.
 */

public class FoundDogPresenter implements Presenter {
    private View view;

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public void onResponse(Object data) {
        Log.d("BAMBOOZLED", String.valueOf(data));
    }

    @Override
    public void onError(Object error) {

    }

    @Override
    public void attachView(List<Object> params) {
        setView((View) params.get(0));
        List<Object> dog = (List<Object>) params.get(1);



        ObjectMapper mapper = new ObjectMapper(this, new DogUploadPOJO(buildDog((List<Object>) params.get(1))));
    }

    private Dog buildDog(List<Object> params) {
        Dog dog =  new Dog (
            String.valueOf(params.get(0)),
            String.valueOf(params.get(1)),
            Boolean.valueOf(String.valueOf(params.get(2))),
            String.valueOf(params.get(6)),
            String.valueOf(params.get(5)),
            String.valueOf(params.get(3)),
            Long.valueOf(String.valueOf(params.get(4))),
            String.valueOf(params.get(7))
        );

        return dog;
    }
}
