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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewVisitorManagementAdapter extends RecyclerView.Adapter<ViewVisitorManagementAdapter.ViewHolder> {

    Context context;
    List<Visitor> visitorManagement_list;


    public ViewVisitorManagementAdapter(Context context, List<Visitor> visitorManagement_list) {
        this.context = context;
        this.visitorManagement_list = visitorManagement_list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_management,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Visitor visitor = visitorManagement_list.get(position);
        holder.ttVisitorName.setText(visitor.getVisitorName());
        holder.ttPhoneNumber.setText(Integer.toString(visitor.getPhoneNumber()));
        holder.ttCheckInDate.setText(visitor.getCheckInDate());
        holder.ttCheckOutDate.setText(visitor.getCheckOutDate());
        holder.ttParkingNumber.setText(visitor.getParkingNumber());
        holder.ttApproveStatus.setText(visitor.getApproveStatus());



        holder.btnApproval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.btnApproval);
                popupMenu.getMenuInflater().inflate(R.menu.popupmenu_approval,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_popup_Approve:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setTitle("Approve");
                                builder.setMessage("Are You Sure To Approve?");
                                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                });
                                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentApprove = new Intent(context,ApproveVisitorParking.class);
                                        intentApprove.putExtra("visitor",visitor);
                                        intentApprove.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intentApprove);
                                    }
                                });
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                return true;

                            case R.id.action_popup_Reject:
                                AlertDialog.Builder builderManagement = new AlertDialog.Builder(context);
                                builderManagement.setTitle("Reject");
                                builderManagement.setMessage("Are You Sure To Reject?");
                                builderManagement.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int i) {
                                        dialog.dismiss();
                                    }
                                });
                                builderManagement.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intentReject= new Intent(context,Rejected.class);
                                        intentReject.putExtra("visitor",visitor);
                                        intentReject.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(intentReject);
                                    }
                                });
                                AlertDialog dialogManagement = builderManagement.create();
                                dialogManagement.show();
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

    @Override
    public int getItemCount() {

        return visitorManagement_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ttVisitorName, ttPhoneNumber, ttCheckInDate, ttCheckOutDate, ttParkingNumber ,ttApproveStatus;
        ImageButton btnApproval;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            ttVisitorName = itemView.findViewById(R.id.ttVisitorName);
            ttPhoneNumber = itemView.findViewById(R.id.ttPhoneNumber);
            ttCheckInDate = itemView.findViewById(R.id.ttCheckInDate);
            ttCheckOutDate = itemView.findViewById(R.id.ttCheckOutDate);
            ttParkingNumber = itemView.findViewById(R.id.ttParkingNumber);
            ttApproveStatus = itemView.findViewById(R.id.ttApproveStatus);
            btnApproval = itemView.findViewById(R.id.btnApprove);

        }

    }
}


