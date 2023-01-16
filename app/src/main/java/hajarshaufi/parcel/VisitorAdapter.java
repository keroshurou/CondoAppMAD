package hajarshaufi.parcel;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VisitorAdapter extends RecyclerView.Adapter<VisitorAdapter.ViewHolder> {

    Context context;
    List<Visitor> visitor_list;

    String url = "http://10.131.77.18/condoapp/deleteVisitor.php";


    public VisitorAdapter(Context context, List<Visitor> visitor_list) {
        this.context = context;
        this.visitor_list = visitor_list;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Visitor visitor = visitor_list.get(position);
        holder.txtVisitorName.setText(visitor.getVisitorName());
        holder.txtPhoneNumber.setText(Integer.toString(visitor.getPhoneNumber()));
        holder.txtCheckInDate.setText(visitor.getCheckInDate());
        holder.txtCheckOutDate.setText(visitor.getCheckOutDate());
        holder.txtParkingNumber.setText(visitor.getParkingNumber());
        holder.txtApproveStatus.setText(visitor.getApproveStatus());



        holder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.btnMore);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_popup_edit:
                                Intent intentEdit = new Intent(context,EditVisitor.class);
                                intentEdit.putExtra("visitor",visitor);
                                intentEdit.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intentEdit);
                                return true;
                            case R.id.action_popup_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Delete Visitor Details");
                                builder.setMessage("Are You Sure To Delete?");
                                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deleteData(visitor_list.get(holder.getBindingAdapterPosition()).getVisitorName());
                                        Delete(holder.getBindingAdapterPosition());
                                        Intent intentDelete = new Intent(context,SuccessfullyDeleted.class);
                                        intentDelete.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intentDelete);
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                return true;
                            default:
                                return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });


    }

    private void deleteData(final String VisitorName){
        StringRequest request = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        if(response.equalsIgnoreCase("Data Deleted")){
                            Toast.makeText(context, "Data Deleted Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(context, "Data Not Deleted",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new HashMap<String, String>();

                params.put("visitorName", VisitorName);


                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(request);
    }


    @Override
    public int getItemCount() {
        return visitor_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtVisitorName, txtPhoneNumber, txtCheckInDate, txtCheckOutDate, txtParkingNumber ,txtApproveStatus;
        ImageButton btnMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtVisitorName = itemView.findViewById(R.id.txtVisitorName);
            txtPhoneNumber = itemView.findViewById(R.id.txtPhoneNumber);
            txtCheckInDate = itemView.findViewById(R.id.txtCheckInDate);
            txtCheckOutDate = itemView.findViewById(R.id.txtCheckOutDate);
            txtParkingNumber = itemView.findViewById(R.id.txtParkingNumber);
            txtApproveStatus = itemView.findViewById(R.id.txtApproveStatus);
            btnMore = itemView.findViewById(R.id.btnMore);

        }
    }
    public void Delete(int item){
        visitor_list.remove(item);
        notifyItemRemoved(item);
    }
}
