package com.example.student.skywalkerairlines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AircraftListAdapter extends ArrayAdapter<Aircraft>{

        private ArrayList<Aircraft> aircraftArrayListData;
        private Context mContext;
        private int lastposition = -1;


        //Setting the view holder to set values inside of card
        private static class ViewHolder{
            TextView aircraftName;
            TextView airportName;
            TextView aircraftType;

        }

        //Constructor for adapter
        public AircraftListAdapter(ArrayList<Aircraft> aircrafts,
                                  Context context){
            super(context, R.layout.listview_aircraft, aircrafts);
            this.aircraftArrayListData = aircrafts;
            this.mContext = context;

        }



        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            //Get the data item for this position
            Aircraft each_aircraft = getItem(position);

            //reusing the view
            ViewHolder viewHolder;

            final View result;

            if(convertView == null){
                viewHolder = new ViewHolder();

                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.listview_aircraft,parent, false);

                viewHolder.aircraftName = (TextView) convertView.findViewById(R.id.tv_aircraft_name);
                viewHolder.airportName = (TextView) convertView.findViewById(R.id.tv_aircraft_airport_name);
                viewHolder.aircraftType = (TextView) convertView.findViewById(R.id.tv_aircraft_type);

                result = convertView;
                convertView.setTag(viewHolder);

            }else{
                viewHolder = (ViewHolder) convertView.getTag();

                result = convertView;

            }

            lastposition = position;

            viewHolder.aircraftName.setText(each_aircraft.getAircraftName());
            viewHolder.airportName.setText(each_aircraft.getLocation());
            viewHolder.aircraftType.setText(each_aircraft.getType());

            return convertView;


        }
    }

