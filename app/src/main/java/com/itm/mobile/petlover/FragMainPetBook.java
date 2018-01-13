package com.itm.mobile.petlover;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.itm.mobile.dados.ormlite.controller.PetBookRepo;
import com.itm.mobile.dados.ormlite.model.PetBook;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class FragMainPetBook extends Fragment {

    private View frag_main_petbook;

    public ListView lv_main_petbook_list;

    public FragMainPetBookAdapter fragMainPetBookAdapter;

    public FragMainPetBook() {
    }

    ArrayList<PetBook> petBookArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        frag_main_petbook = inflater.inflate(R.layout.frag_main_petbook, container, false);

        // Populating Chegada ListView
        PetBookRepo petBookRepo = new PetBookRepo(getContext());

        petBookArrayList = (ArrayList<PetBook>) petBookRepo.findAll();

        fragMainPetBookAdapter = new FragMainPetBookAdapter(getContext(), petBookArrayList);

        lv_main_petbook_list = (ListView) frag_main_petbook.findViewById(R.id.lv_main_petbook_list);
        lv_main_petbook_list.setAdapter(fragMainPetBookAdapter);

        lv_main_petbook_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dialogPetBookDetalhes(petBookArrayList.get(position));
            }
        });

        return frag_main_petbook;
    }

    TextView tv_item_petbook_msg_dt_reg, tv_item_petbook_msg_titulo, tv_item_petbook_msg_texto,
            tv_item_petbook_detalhe_likes, tv_item_petbook_detalhe_views;

    private void dialogPetBookDetalhes(PetBook petBook) {

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_content_petbook_detalhe);
        dialog.setTitle(petBook.getTitulo());

        tv_item_petbook_msg_titulo = (TextView) dialog.findViewById(R.id.tv_item_petbook_msg_titulo);
        tv_item_petbook_msg_texto = (TextView) dialog.findViewById(R.id.tv_item_petbook_msg_texto);
        tv_item_petbook_msg_dt_reg = (TextView) dialog.findViewById(R.id.tv_item_petbook_msg_dt_reg);
        tv_item_petbook_detalhe_likes = (TextView) dialog.findViewById(R.id.tv_item_petbook_detalhe_likes);
        tv_item_petbook_detalhe_views = (TextView) dialog.findViewById(R.id.tv_item_petbook_detalhe_views);

        // set image based on selected text
        ImageView imageView = (ImageView) dialog.findViewById(R.id.iv_item_petbook_msg_imagem);
        if (!petBook.getImagem().isEmpty()) {
            String strFile = "file:///android_asset/img/" + petBook.getImagem();
            Picasso.with(getContext()).setLoggingEnabled(true);
            Picasso.with(getContext())
                    .load(strFile)
                    .fit()
                    .tag(this)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_pet_sem_foto_200_150);
        }

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        tv_item_petbook_msg_titulo.setText(petBook.getTitulo());
        tv_item_petbook_msg_texto.setText(petBook.getTexto());
        tv_item_petbook_msg_dt_reg.setText(dateFormatter.format(petBook.getDt_reg()));
        tv_item_petbook_detalhe_likes.setText(String.valueOf(petBook.getLikes()));
        tv_item_petbook_detalhe_views.setText(String.valueOf(petBook.getViews()));

        Button btn_dialog_petbook_voltar = (Button) dialog.findViewById(R.id.btn_dialog_petbook_voltar);

        btn_dialog_petbook_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

    private class FragMainPetBookAdapter extends ArrayAdapter<PetBook> {
        public FragMainPetBookAdapter(Context context, ArrayList<PetBook> list) {
            super(context, 0, list);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position
            PetBook item = getItem(position);
            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.lv_item_petbook, parent, false);
            }

            // Lookup view for data population
            TextView tv_item_petbook_msg_titulo = (TextView) convertView.findViewById(R.id.tv_item_petbook_msg_titulo);
            TextView tv_item_petbook_msg_texto = (TextView) convertView.findViewById(R.id.tv_item_petbook_msg_texto);
            TextView tv_item_petbook_nome = (TextView) convertView.findViewById(R.id.tv_item_petbook_nome);
            TextView tv_item_petbook_date = (TextView) convertView.findViewById(R.id.tv_item_petbook_date);
            ImageView iv_item_petbook_avatar = (ImageView) convertView.findViewById(R.id.iv_item_petbook_avatar);
            ImageView iv_item_petbook_msg_imagem = (ImageView) convertView.findViewById(R.id.iv_item_petbook_msg_imagem);

            SimpleDateFormat dfh = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

            tv_item_petbook_msg_titulo.setText(item.getTitulo());
            tv_item_petbook_msg_texto.setText(item.getTexto());
            tv_item_petbook_nome.setText(item.getUsuario().getName());
            tv_item_petbook_date.setText(dfh.format(item.getDt_reg()));
            // set image based on selected text

            if (!item.getUsuario().getAvatar().isEmpty()) {
                String str_iv_item_petbook_avatar = "file:///android_asset/img/" + item.getUsuario().getAvatar();
                Picasso.with(getContext()).setLoggingEnabled(true);
                Picasso.with(getContext())
                        .load(str_iv_item_petbook_avatar)
                        .resize(32,32)
                        .tag(this)
                        .into(iv_item_petbook_avatar);
            } else {
                iv_item_petbook_avatar.setImageResource(R.drawable.ic_avater_sem_foto128dp);
            }
            // set image based on selected text

            if (!item.getImagem().isEmpty()) {
                String str_iv_item_petbook_msg_imagem = "file:///android_asset/img/" + item.getImagem();
                Picasso.with(getContext()).setLoggingEnabled(true);
                Picasso.with(getContext())
                        .load(str_iv_item_petbook_msg_imagem)
                        .resize(300, 200)
                        .centerInside()
                        .tag(this)
                        .into(iv_item_petbook_msg_imagem);
            }
            //iv_item_petbook_avatar.setImageResource(item.pet.getFoto());
            //iv_item_petbook_msg_imagem.setImageResource(item.getImagem());

            // Return the completed view to render on screen
            return convertView;
        }
    }

}


