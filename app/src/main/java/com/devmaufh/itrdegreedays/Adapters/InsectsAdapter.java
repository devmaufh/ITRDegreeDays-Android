package com.devmaufh.itrdegreedays.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.devmaufh.itrdegreedays.Classes.Dates;
import com.devmaufh.itrdegreedays.Classes.Insect;
import com.devmaufh.itrdegreedays.Models.HomeCard;
import com.devmaufh.itrdegreedays.R;
import com.devmaufh.itrdegreedays.Utilities.DegreeDaysUtilities;
import com.jjoe64.graphview.GraphView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class InsectsAdapter extends RecyclerView.Adapter<InsectsAdapter.ViewHolder> {
    private ArrayList<HomeCard> insectsList;
    private Context context;
    private int layout;
    private ClickListener listener;

    public InsectsAdapter(ArrayList<HomeCard> insectsList,int layout, ClickListener listener) {
        this.insectsList = insectsList;
        this.layout=layout;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(layout,viewGroup,false);
        ViewHolder vh= new ViewHolder(view);
        context=viewGroup.getContext();
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bindUI(insectsList.get(i),listener);
    }
    @Override
    public int getItemCount() {
        return insectsList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener {
        private GraphView plot;
        private TextView title,subtitle;
        private ImageButton btnOptions;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            plot=(GraphView)itemView.findViewById(R.id.home_card_plot);
            title=(TextView)itemView.findViewById(R.id.home_card_title);
            subtitle=(TextView)itemView.findViewById(R.id.home_card_degreeday);
            btnOptions=(ImageButton)itemView.findViewById(R.id.home_card_menuButton);
        }
        public void bindUI(final HomeCard insect,final ClickListener click){
            double[] vlues=values(insect);
            title.setText(insect.getInsect().getName());
            plot.addSeries(DegreeDaysUtilities.Series(insect.getDates(),vlues));
            subtitle.setText( context.getResources().getText(R.string.unidades_acomuladas)+" "+DegreeDaysUtilities.getAcomulated(vlues));

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.clickListener(insect,getAdapterPosition());
                }
            });
        }
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle(R.string.que_desea_hacer);
            menu.add(0,R.id.menu_eliminar,0,R.string.eliminar);
        }
        private double[] values(HomeCard insect){
            double val[]=new double[insect.getDates().size()];
            for (int i = 0; i < val.length; i++) {
                val[i]=DegreeDaysUtilities.calculateDegreeDay(insect.getInsect(),insect.getDates().get(i));
            }
            return val;
        }
    }
    public interface  ClickListener{
        void clickListener(HomeCard insect,int position);
    }
}
