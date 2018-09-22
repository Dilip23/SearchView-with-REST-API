package videochat.viredinc.android.com.searchviewrestapi;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import videochat.viredinc.android.com.searchviewrestapi.entities.Result;

public class UserAdapter extends ArrayAdapter<Result> {

    private Context context;
    private List<Result> results;

    public UserAdapter(Context context,List<Result> results){
        super(context,R.layout.row,results);
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        convertView = layoutInflater.inflate(R.layout.row,parent,false);
        Result result = results.get(position);
        TextView textView = convertView.findViewById(R.id.username);
        textView.setText(result.getUsername());

        return convertView;

    }
}
