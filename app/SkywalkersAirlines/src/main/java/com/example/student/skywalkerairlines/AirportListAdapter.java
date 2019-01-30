package com.example.student.skywalkerairlines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AirportListAdapter extends ArrayAdapter<Airport> {

    private ArrayList<Airport> airportListData;
    private Context mContext;
    private int lastposition = -1;


    //Setting the view holder to set values inside of card
    private static class ViewHolder{
        TextView airportName;
        TextView airportCode;
        TextView coordinates;

    }

    //Constructor for adapter
    public AirportListAdapter(ArrayList<Airport> airport,
                               Context context){
        super(context, R.layout.listview_airport, airport);
        this.airportListData = airport;
        this.mContext = context;

    }



    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {

        //Get the data item for this position
        Airport each_airport = getItem(position);

        //reusing the view
        ViewHolder viewHolder;

        final View result;

        if(convertView == null){
            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.listview_airport,parent, false);

            viewHolder.airportName = (TextView) convertView.findViewById(R.id.tv_airport_name);
            viewHolder.airportCode = (TextView) convertView.findViewById(R.id.tv_airport_code);
            viewHolder.coordinates = (TextView) convertView.findViewById(R.id.tv_coordinates);

            result = convertView;

            convertView.setTag(viewHolder);

        }else{
            viewHolder = (ViewHolder) convertView.getTag();

            result = convertView;

        }

        lastposition = position;

        viewHolder.airportName.setText(each_airport.getLocationName());
        viewHolder.airportCode.setText(each_airport.getAirportCode());
        viewHolder.coordinates.setText(each_airport.getCoordinates());

        return convertView;


    }
}
