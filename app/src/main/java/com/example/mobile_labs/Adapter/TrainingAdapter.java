package com.example.mobile_labs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobile_labs.R;
import com.example.mobile_labs.Entity.Training;
import com.example.mobile_labs.my.Сonversion;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.geometry.Polyline;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.mapview.MapView;

import java.util.ArrayList;

public class TrainingAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater LInflater;
    public ArrayList<Training> objects;

    public TrainingAdapter(Context context, ArrayList<Training> trainings) {
        this.context = context;
        objects = trainings;
        LInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int pos) {
        return objects.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return getTraining(pos).id;
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup parent) {
        Training training = objects.get(pos);
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.my_post, parent, false);
        }
        MapView mapView = (MapView) view.findViewById(R.id.mapview);
        MapObjectCollection mapObjects = mapView.getMap().getMapObjects();
        Map map = mapView.getMap();

        TextView distance = (TextView) view.findViewById(R.id.Distance);
        TextView temp = (TextView) view.findViewById(R.id.Temp);
        TextView time = (TextView) view.findViewById(R.id.Time);
        TextView name = (TextView) view.findViewById(R.id.Name);
        TextView date = (TextView) view.findViewById(R.id.Date);

        distance.setText(Сonversion.getDistance(training.distance));
        temp.setText(Сonversion.getTemp(training.temp));
        time.setText(Сonversion.getTime(training.time));
        name.setText(training.user.surname + " " + training.user.name);
        date.setText(Сonversion.getDate(training.startTime));

        mapObjects.addPolyline(new Polyline(training.getTrack()));
        map.setRotateGesturesEnabled(false);
        map.setScrollGesturesEnabled(false);
        map.move( //55.751574, 37.573856
                new CameraPosition(new Point(training.points.get(0).latitude, training.points.get(0).longitude), 14.0f, 0.0f, 0.0f),
                new Animation(Animation.Type.SMOOTH, 0),
                null);

        return view;
    }


    Training getTraining(int pos) {
        return (Training) getItem(pos);
    }

    ArrayList<Training> getBox() {
        //Поменять
        return objects;
    }
}
