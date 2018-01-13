package com.itm.mobile.petlover;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.itm.mobile.dados.ormlite.controller.AnimalRepo;
import com.itm.mobile.dados.ormlite.model.Animal;
import com.itm.mobile.util.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class FragMainPetFeira extends Fragment {

    private View frag_main_petfeira;

    private UserSessionManager userSession;

    HashMap<String, String> user;

    public GridView gv_main_petfeira_venda;
    public GridView gv_main_petfeira_doacao;

    public VendasAdapter vendasAdapter;
    public DoacaoAdapter doacaoAdapter;

    ArrayList<Animal> arrayvendas;
    ArrayList<Animal> arraydoacao;

    public class VendasAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Animal> values;

        public VendasAdapter(Context context, ArrayList<Animal> values) {
            this.context = context;
            this.values = values;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;

            if (convertView == null) {

                gridView = new View(context);

                // get layout from mobile.xml
                gridView = inflater.inflate(R.layout.lv_item_petfeira_venda, null);

                // set value into textview
                TextView item_petfeira_venda_grid_descricao = (TextView) gridView
                        .findViewById(R.id.item_petfeira_venda_grid_descricao);
                item_petfeira_venda_grid_descricao.setText(values.get(position).getDescricao());

                // set value into textview
                TextView item_petfeira_venda_grid_valor = (TextView) gridView
                        .findViewById(R.id.item_petfeira_venda_grid_valor);
                item_petfeira_venda_grid_valor.setText(String.valueOf(values.get(position).getPreco()));
                // set image based on selected text
                ImageView imageView = (ImageView) gridView
                        .findViewById(R.id.item_petfeira_venda_grid_image);
                if (values.get(position).getPet().getFoto() != "") {
                    String strFile = "file:///android_asset/img/" + values.get(position).getPet().getFoto();
                    Picasso.with(getContext()).setLoggingEnabled(true);
                    Picasso.with(getContext())
                            .load(strFile)
                            .fit()
                            .tag(this)
                            .into(imageView);
                } else {
                    imageView.setImageResource(R.drawable.ic_pet_sem_foto_200_150);
                }
                //imageView.setImageResource(values.get(position).getPet().getFoto());


            } else {
                gridView = (View) convertView;
            }

            return gridView;
        }

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }
    public class DoacaoAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Animal> values;

        public DoacaoAdapter(Context context, ArrayList<Animal> values) {
            this.context = context;
            this.values = values;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View gridView;

            if (convertView == null) {

                gridView = new View(context);

                // get layout from mobile.xml
                gridView = inflater.inflate(R.layout.lv_item_petfeira_doacao, null);

                // set value into textview
                TextView item_petfeira_doacao_grid_descricao = (TextView) gridView
                        .findViewById(R.id.item_petfeira_doacao_grid_descricao);
                item_petfeira_doacao_grid_descricao.setText(values.get(position).getDescricao());

                // set image based on selected text
                ImageView imageView = (ImageView) gridView
                        .findViewById(R.id.item_petfeira_doacao_grid_image);

                if (values.get(position).getPet().getFoto() != "") {
                    String strFile = "file:///android_asset/img/" + values.get(position).getPet().getFoto();
                    Picasso.with(getContext()).setLoggingEnabled(true);
                    Picasso.with(getContext())
                            .load(strFile)
                            .fit()
                            .tag(this)
                            .into(imageView);
                } else {
                    imageView.setImageResource(R.drawable.ic_pet_sem_foto_200_150);
                }

                //imageView.setImageResource(values.get(position).getPet().getFoto());

            } else {
                gridView = (View) convertView;
            }

            return gridView;
        }

        @Override
        public int getCount() {
            return values.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

    }

    public FragMainPetFeira() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frag_main_petfeira = inflater.inflate(R.layout.frag_main_petfeira, container, false);

        // get user data from userSession
        // Session class instance
        userSession = new UserSessionManager(getContext());
        user = userSession.getUserDetails();

        // Populating Achados GridView
        AnimalRepo animalRepo = new AnimalRepo(getContext());
        arrayvendas = (ArrayList<Animal>) animalRepo.listVendas();

        if (!arrayvendas.isEmpty()) {
            gv_main_petfeira_venda = (GridView) frag_main_petfeira.findViewById(R.id.gv_main_petfeira_venda);

            gv_main_petfeira_venda.setAdapter(new VendasAdapter(getContext(), arrayvendas));

            gv_main_petfeira_venda.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Animal item = arrayvendas.get(position);
                    dialogFeiraDetalhes(item, getString(R.string.dialog_tit_petfeira_venda));
                }
            });

        }

        arraydoacao = (ArrayList<Animal>) animalRepo.listDoacoes();

        if (!arraydoacao.isEmpty()) {
            gv_main_petfeira_doacao = (GridView) frag_main_petfeira.findViewById(R.id.gv_main_petfeira_doacao);

            gv_main_petfeira_doacao.setAdapter(new DoacaoAdapter(getContext(), arraydoacao));

            gv_main_petfeira_doacao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Animal item = arraydoacao.get(position);
                    dialogFeiraDetalhes(item, getString(R.string.dialog_tit_petfeira_adocao));
                }
            });

        }

        return frag_main_petfeira;
    }


    TextView item_petfeira_venda_detalhe_descricao, item_petfeira_venda_detalhe_dt_reg, ed_item_petfeira_venda_detalhe_detalhes,
            ed_item_petfeira_venda_detalhe_condicoes, ed_item_petfeira_venda_detalhe_preco, ed_item_petfeira_venda_detalhe_pet_especie,
            ed_item_petfeira_venda_detalhe_pet_raca, ed_item_petfeira_venda_detalhe_pet_porte, ed_item_petfeira_venda_detalhe_pet_cor,
            ed_item_petfeira_venda_detalhe_pet_dt_nasc, ed_item_petfeira_venda_detalhe_pet_sexo, ed_item_petfeira_venda_detalhe_pet_caracteristicas,
            ed_item_petfeira_venda_detalhe_pet_responsavel, ed_item_petfeira_venda_detalhe_pet_contato;

    private void dialogFeiraDetalhes(Animal animal, String titulo) {

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_content_petfeira_venda_detalhe);
        dialog.setTitle(titulo);

        item_petfeira_venda_detalhe_descricao = (TextView) dialog.findViewById(R.id.item_petfeira_venda_detalhe_descricao);
        item_petfeira_venda_detalhe_dt_reg = (TextView) dialog.findViewById(R.id.item_petfeira_venda_detalhe_dt_reg);

        ed_item_petfeira_venda_detalhe_detalhes = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_detalhes);
        ed_item_petfeira_venda_detalhe_condicoes = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_condicoes);
        ed_item_petfeira_venda_detalhe_preco = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_preco);
        ed_item_petfeira_venda_detalhe_pet_especie = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_especie);

        ed_item_petfeira_venda_detalhe_pet_raca = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_raca);
        ed_item_petfeira_venda_detalhe_pet_porte = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_porte);
        ed_item_petfeira_venda_detalhe_pet_cor = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_cor);
        ed_item_petfeira_venda_detalhe_pet_dt_nasc = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_dt_nasc);

        ed_item_petfeira_venda_detalhe_pet_sexo = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_sexo);
        ed_item_petfeira_venda_detalhe_pet_caracteristicas = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_caracteristicas);
        ed_item_petfeira_venda_detalhe_pet_responsavel = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_responsavel);
        ed_item_petfeira_venda_detalhe_pet_contato = (TextView) dialog.findViewById(R.id.ed_item_petfeira_venda_detalhe_pet_contato);

        LinearLayout ll_item_petfeira_venda_detalhe_preco = (LinearLayout) dialog.findViewById(R.id.ll_item_petfeira_venda_detalhe_preco);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // set image based on selected text
        ImageView imageView = (ImageView) dialog.findViewById(R.id.item_petfeira_venda_detalhe_image);
        if (!animal.getPet().getFoto().isEmpty()) {
            String strFile = "file:///android_asset/img/" + animal.getPet().getFoto();
            Picasso.with(getContext()).setLoggingEnabled(true);
            Picasso.with(getContext())
                    .load(strFile)
                    .fit()
                    .tag(this)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_pet_sem_foto_200_150);
        }

        item_petfeira_venda_detalhe_descricao.setText(animal.getDescricao());
        item_petfeira_venda_detalhe_dt_reg.setText(dateFormatter.format(animal.getDt_reg()));
        ed_item_petfeira_venda_detalhe_detalhes.setText(animal.getDetalhes());
        ed_item_petfeira_venda_detalhe_condicoes.setText(animal.getCondicoes());
        if (animal.getCategoria() == 0){
            ll_item_petfeira_venda_detalhe_preco.setVisibility(View.VISIBLE);
            ed_item_petfeira_venda_detalhe_preco.setText(animal.getPreco().toString());
        }else {
            ll_item_petfeira_venda_detalhe_preco.setVisibility(View.GONE);
        }
        ed_item_petfeira_venda_detalhe_pet_especie.setText(animal.getPet().getEspecie());
        ed_item_petfeira_venda_detalhe_pet_raca.setText(animal.getPet().getRaca());
        ed_item_petfeira_venda_detalhe_pet_porte.setText(animal.getPet().getPorte());
        ed_item_petfeira_venda_detalhe_pet_cor.setText(animal.getPet().getCor());
        ed_item_petfeira_venda_detalhe_pet_dt_nasc.setText(dateFormatter.format(animal.getPet().getDt_nasc()));
        ed_item_petfeira_venda_detalhe_pet_sexo.setText(animal.getPet().getSexo());
        ed_item_petfeira_venda_detalhe_pet_caracteristicas.setText(animal.getPet().getCaracteristicas());
        ed_item_petfeira_venda_detalhe_pet_responsavel.setText(animal.getPet().getResponsavel());
        ed_item_petfeira_venda_detalhe_pet_contato.setText(animal.getPet().getContato());

        Button btn_dialog_petfeira_voltar = (Button) dialog.findViewById(R.id.btn_dialog_petfeira_voltar);
        Button btn_dialog_petfeira_reservar = (Button) dialog.findViewById(R.id.btn_dialog_petfeira_reservar);

        btn_dialog_petfeira_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_dialog_petfeira_reservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });

        dialog.show();
    }
}


