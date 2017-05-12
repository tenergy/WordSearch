package googleappliedcs.week6.wordsearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by kentaro on 5/11/17.
 */

public class TileAdapter extends BaseAdapter {

    private ArrayList<Tile> tileArray;
    private Context context;

    public TileAdapter(Context context, ArrayList<Tile> tileArray){
        this.context = context;
        this.tileArray = tileArray;
    }

    @Override
    public int getCount() {
        return tileArray.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView textView = new TextView(context);
        textView.setText(tileArray.get(position).toString());
        return textView;
    }
}
