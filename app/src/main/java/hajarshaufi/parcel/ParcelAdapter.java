package hajarshaufi.parcel;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ParcelAdapter extends ArrayAdapter<Parcel> {

    Context context;
    List<Parcel> arrayListParcel;

    public ParcelAdapter(@NonNull Context context, List<Parcel> arrayListParcel) {
        super(context, R.layout.parcel_item, arrayListParcel);

        this.context = context;
        this.arrayListParcel = arrayListParcel;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.parcel_item, null, true);
        TextView tvID = view.findViewById(R.id.txt_id);
        TextView tvTrackingNumber = view.findViewById(R.id.txt_trackingNumber);
        TextView tvName = view.findViewById(R.id.txt_Name);

        tvID.setText(arrayListParcel.get(position).getParcelID());
        tvName.setText(arrayListParcel.get(position).getCollectorName());
        tvTrackingNumber.setText(arrayListParcel.get(position).getTrackingNumber());

        return view;
    }
}
