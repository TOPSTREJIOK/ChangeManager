package com.topstrejiok.changemanager.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.topstrejiok.changemanager.R;
import com.topstrejiok.changemanager.activity.SessionActivity;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {
    private Context context;

    public NameAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_name, viewGroup, false);
        return new NameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder nameViewHolder,
                                 @SuppressLint("RecyclerView") final int position) {
        nameViewHolder.donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Enter donation");
                final View edt = LayoutInflater.from(context).inflate(R.layout.alert_item_donation, null);
                if (SessionActivity.sessionController.getNameItems()
                        .get(position).getDonate() != 0.0){
                    ((TextView) edt.findViewById(R.id.AlertName)).setText(SessionActivity.sessionController.getNameItems()
                            .get(position).getDonate().toString());
                }
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SessionActivity.sessionController.getNameItems()
                                .get(position).setDonate(Double.parseDouble(((EditText)edt.findViewById(R.id.AlertName))
                                .getText().toString()));
                        ((EditText)view).setText(((EditText)edt.findViewById(R.id.AlertName))
                                .getText().toString());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setView(edt);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        nameViewHolder.name.setText(SessionActivity.sessionController.getNameItems()
                .get(position).getName());
        nameViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Are You sure?");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SessionActivity.sessionController.removeName(SessionActivity.sessionController.getNameItems()
                                .get(position).getName(),SessionActivity.sessionController.getNameItems()
                                .get(position).getId());
                        dialogInterface.dismiss();
                        notifyDataSetChanged();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        nameViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Change Name");
                final View edt = LayoutInflater.from(context).inflate(R.layout.alert_item_session, null);
                ((TextView) edt.findViewById(R.id.AlertName)).setText(SessionActivity.sessionController.getNameItems()
                        .get(position).getName());
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SessionActivity.sessionController.getNameItems()
                                .get(position).setName(((TextView) edt.findViewById(R.id.AlertName))
                                .getText().toString());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.setView(edt);
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return SessionActivity.sessionController.getNameItems().size();
    }

    static class NameViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView delete;
        ImageView edit;
        EditText donate;

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.itemName);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);
            donate = itemView.findViewById(R.id.donatedfield);
        }
    }
}
