package com.example.alvin.listview;

/**
 * Created by Alvin on 04/09/2018.
 */
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<SiswaModel> siswaModels;
    public static final String EXTRA_SISWA = "EXTRA_MEMBER";


    public MainAdapter(List<SiswaModel> siswaModels) {
        this.siswaModels = siswaModels;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_siswa, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainViewHolder holder, final int position) {
        final SiswaModel siswaModel = siswaModels.get(position);

        Glide.with(holder.itemView.getContext())
                .load(siswaModel.getPathFoto())
                .into(holder.inputFoto);
        holder.inputNama.setText(siswaModel.getNama());
        holder.inputAlamat.setText(siswaModel.getAlamat());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.itemView.getContext(), ActionActivity.class);
                i.putExtra(EXTRA_SISWA, siswaModels.get(position));
                holder.itemView.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return siswaModels.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_profile)
        ImageView inputFoto;
        @BindView(R.id.txt_name)
        TextView inputNama;
        @BindView(R.id.txt_address)
        TextView inputAlamat;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

