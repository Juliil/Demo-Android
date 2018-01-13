package com.itm.mobile.petlover;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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
import android.widget.TextView;

import com.itm.mobile.dados.ormlite.controller.AchadosRepo;
import com.itm.mobile.dados.ormlite.controller.PerdidosRepo;
import com.itm.mobile.dados.ormlite.model.Achados;
import com.itm.mobile.dados.ormlite.model.Animal;
import com.itm.mobile.dados.ormlite.model.Perdidos;
import com.itm.mobile.util.UserSessionManager;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class FragMainPetProcurase extends Fragment {

    private View frag_main_petprocurase;

    private UserSessionManager userSession;

    HashMap<String, String> user;

    public GridView gv_main_petprocurase_achados;
    public GridView gv_main_petprocurase_perdidos;

    ArrayList<Achados> arrayachados;
    ArrayList<Perdidos> arrayperdidos;

    public class AchadosAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Achados> values;

        public AchadosAdapter(Context context, ArrayList<Achados> values) {
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
                gridView = inflater.inflate(R.layout.lv_item_petprocurase_achados, null);

                // set value into textview
                TextView item_petprocurase_achados_grid_local = (TextView) gridView
                        .findViewById(R.id.item_petprocurase_achados_grid_local);
                item_petprocurase_achados_grid_local.setText(values.get(position).getLocal());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                // set value into textview
                TextView item_petprocurase_achados_grid_dta_achado = (TextView) gridView
                        .findViewById(R.id.item_petprocurase_achados_grid_dta_achado);
                item_petprocurase_achados_grid_dta_achado.setText(df.format(values.get(position).getDt_fato()));

                // set image based on selected text
                ImageView imageView = (ImageView) gridView
                        .findViewById(R.id.item_petprocurase_achados_grid_image);
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
    public class PerdidosAdapter extends BaseAdapter {
        private Context context;
        private ArrayList<Perdidos> values;

        public PerdidosAdapter(Context context, ArrayList<Perdidos> values) {
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
                gridView = inflater.inflate(R.layout.lv_item_petprocurase_perdidos, null);

                // set value into textview
                TextView item_petprocurase_perdidos_grid_nome = (TextView) gridView
                        .findViewById(R.id.item_petprocurase_perdidos_grid_nome);
                item_petprocurase_perdidos_grid_nome.setText(values.get(position).getPet().getNome());

                // set value into textview
                TextView item_petprocurase_perdidos_grid_local = (TextView) gridView
                        .findViewById(R.id.item_petprocurase_perdidos_grid_local);
                item_petprocurase_perdidos_grid_local.setText(values.get(position).getLocal());

                SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
                // set value into textview
                TextView item_petprocurase_perdidos_grid_dta_perda = (TextView) gridView
                        .findViewById(R.id.item_petprocurase_perdidos_grid_dta_perda);
                item_petprocurase_perdidos_grid_dta_perda.setText(df.format(values.get(position).getDt_fato()));

                // set image based on selected text
                ImageView imageView = (ImageView) gridView
                        .findViewById(R.id.item_petprocurase_perdidos_grid_image);

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

    AchadosRepo achadosRepo;
    PerdidosRepo perdidosRepo;

    public FragMainPetProcurase() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        frag_main_petprocurase = inflater.inflate(R.layout.frag_main_petprocurase, container, false);

        // get user data from userSession
        // Session class instance
        userSession = new UserSessionManager(getContext());
        user = userSession.getUserDetails();

        // Populating Perdidos GridView
        achadosRepo = new AchadosRepo(getContext());
        arrayachados = (ArrayList<Achados>) achadosRepo.findAll();

        if (!arrayachados.isEmpty()) {
            gv_main_petprocurase_achados = (GridView) frag_main_petprocurase.findViewById(R.id.gv_main_petprocurase_achados);

            gv_main_petprocurase_achados.setAdapter(new AchadosAdapter(getContext(), arrayachados));

            gv_main_petprocurase_achados.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Achados item = arrayachados.get(position);
                    dialogAchadosDetalhes(item);
                }
            });

        }

        // Populating Achados GridView
        perdidosRepo = new PerdidosRepo(getContext());
        arrayperdidos = (ArrayList<Perdidos>) perdidosRepo.findAll();

        if (!arrayperdidos.isEmpty()) {
            gv_main_petprocurase_perdidos = (GridView) frag_main_petprocurase.findViewById(R.id.gv_main_petprocurase_perdidos);

            gv_main_petprocurase_perdidos.setAdapter(new PerdidosAdapter(getContext(), arrayperdidos));

            gv_main_petprocurase_perdidos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Perdidos item = arrayperdidos.get(position);
                    dialogPerdidosDetalhes(item);
                }
            });

        }

        FloatingActionButton fab_petprocurase_achados = (FloatingActionButton) frag_main_petprocurase.findViewById(R.id.fab_petprocurase_achados);
        fab_petprocurase_achados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuscaAchados();
            }
        });

        FloatingActionButton fab_petprocurase_perdidos = (FloatingActionButton) frag_main_petprocurase.findViewById(R.id.fab_petprocurase_perdidos);
        fab_petprocurase_perdidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogBuscaPerdidos();
            }
        });

        return frag_main_petprocurase;
    }

    // CONSTANTS TO DATEPICKER DIALOG

    private DatePickerDialog toDatePickerDialog_dt_fato;

    private int year;
    private int month;
    private int day;

    static final int DATE_DIALOG_ID = 999;

    private SimpleDateFormat dateFormatter;

    EditText ed_dialog_petprocurase_dt_fato, ed_dialog_petprocurase_local, ed_dialog_petprocurase_raca,
            ed_dialog_petprocurase_porte, ed_dialog_petprocurase_especie, ed_dialog_petprocurase_sexo;

    private void dialogBuscaAchados() {
        // custom dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_content_petprocurase_pesquisa);
        dialog.setTitle(R.string.lbl_tit_dialog_content_pesquisa_achados);
        ed_dialog_petprocurase_dt_fato = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_dt_fato);
        ed_dialog_petprocurase_local = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_local);
        ed_dialog_petprocurase_raca = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_raca);
        ed_dialog_petprocurase_porte = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_porte);
        ed_dialog_petprocurase_especie = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_especie);
        ed_dialog_petprocurase_sexo = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_sexo);
        //LinearLayout ll_dialog_petprocurase_dt_fato = (LinearLayout) dialog.findViewById(R.id.ll_dialog_petprocurase_dt_fato);
        Button btn_dialog_petprocurase_cancelar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_cancelar);
        Button btn_dialog_petprocurase_pesquisar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_pesquisar);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Calendar newCalendar = Calendar.getInstance();
        toDatePickerDialog_dt_fato = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ed_dialog_petprocurase_dt_fato.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        ed_dialog_petprocurase_dt_fato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog_dt_fato.show();
            }
        });

        btn_dialog_petprocurase_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_dialog_petprocurase_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* arrayachados = (ArrayList<Achados>) achadosRepo.findAchados(
                        ed_dialog_petprocurase_dt_fato.getText().toString(),
                        ed_dialog_petprocurase_local.getText().toString(),
                        ed_dialog_petprocurase_raca.getText().toString(),
                        ed_dialog_petprocurase_porte.getText().toString(),
                        ed_dialog_petprocurase_especie.getText().toString(),
                        ed_dialog_petprocurase_sexo.getText().toString()
                );
                gv_main_petprocurase_achados.deferNotifyDataSetChanged();

                gv_main_petprocurase_achados.setAdapter(new AchadosAdapter(getContext(), arrayachados));*/

                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void dialogBuscaPerdidos() {
        // custom dialog
        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_content_petprocurase_pesquisa);
        dialog.setTitle(R.string.lbl_tit_dialog_content_pesquisa_perdidos);
        ed_dialog_petprocurase_dt_fato = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_dt_fato);
        ed_dialog_petprocurase_local = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_local);
        ed_dialog_petprocurase_raca = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_raca);
        ed_dialog_petprocurase_porte = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_porte);
        ed_dialog_petprocurase_especie = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_especie);
        ed_dialog_petprocurase_sexo = (EditText) dialog.findViewById(R.id.ed_dialog_petprocurase_sexo);
        //LinearLayout ll_dialog_petprocurase_dt_fato = (LinearLayout) dialog.findViewById(R.id.ll_dialog_petprocurase_dt_fato);
        Button btn_dialog_petprocurase_cancelar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_cancelar);
        Button btn_dialog_petprocurase_pesquisar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_pesquisar);

        dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        Calendar newCalendar = Calendar.getInstance();
        toDatePickerDialog_dt_fato = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                ed_dialog_petprocurase_dt_fato.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        ed_dialog_petprocurase_dt_fato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDatePickerDialog_dt_fato.show();
            }
        });

        btn_dialog_petprocurase_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_dialog_petprocurase_pesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* arrayachados = (ArrayList<Achados>) achadosRepo.findAchados(
                        ed_dialog_petprocurase_dt_fato.getText().toString(),
                        ed_dialog_petprocurase_local.getText().toString(),
                        ed_dialog_petprocurase_raca.getText().toString(),
                        ed_dialog_petprocurase_porte.getText().toString(),
                        ed_dialog_petprocurase_especie.getText().toString(),
                        ed_dialog_petprocurase_sexo.getText().toString()
                );
                gv_main_petprocurase_achados.deferNotifyDataSetChanged();

                gv_main_petprocurase_achados.setAdapter(new AchadosAdapter(getContext(), arrayachados));*/

                dialog.cancel();
            }
        });

        dialog.show();
    }

    TextView item_petprocurase_achados_detalhe_dt_fato, ed_item_petprocurase_achados_detalhe_pet_especie, ed_item_petprocurase_achados_detalhe_pet_raca,
            ed_item_petprocurase_achados_detalhe_pet_porte, ed_item_petprocurase_achados_detalhe_pet_cor, ed_item_petprocurase_achados_detalhe_pet_dt_nasc,
            ed_item_petprocurase_achados_detalhe_pet_sexo, ed_item_petprocurase_achados_detalhe_pet_caracteristicas, ed_item_petprocurase_achados_detalhe_pet_responsavel,
            ed_item_petprocurase_achados_detalhe_pet_contato, ed_item_petprocurase_achados_detalhe_local, ed_item_petprocurase_achados_detalhe_situacao,
            ed_item_petprocurase_achados_detalhe_dt_reg, item_petprocurase_achados_detalhe_texto;

    private void dialogAchadosDetalhes(Achados achados) {

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_content_petprocurase_achados_detalhe);
        dialog.setTitle(R.string.dialog_tit_petprocurase_achados);

        item_petprocurase_achados_detalhe_dt_fato = (TextView) dialog.findViewById(R.id.item_petprocurase_achados_detalhe_dt_fato);
        ed_item_petprocurase_achados_detalhe_pet_especie = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_especie);

        item_petprocurase_achados_detalhe_texto = (TextView) dialog.findViewById(R.id.item_petprocurase_achados_detalhe_texto);
        ed_item_petprocurase_achados_detalhe_pet_raca = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_raca);
        ed_item_petprocurase_achados_detalhe_pet_porte = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_porte);
        ed_item_petprocurase_achados_detalhe_pet_cor = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_cor);
        ed_item_petprocurase_achados_detalhe_pet_dt_nasc = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_dt_nasc);

        ed_item_petprocurase_achados_detalhe_pet_sexo = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_sexo);
        ed_item_petprocurase_achados_detalhe_pet_caracteristicas = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_caracteristicas);
        ed_item_petprocurase_achados_detalhe_pet_responsavel = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_responsavel);
        ed_item_petprocurase_achados_detalhe_pet_contato = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_contato);

        ed_item_petprocurase_achados_detalhe_dt_reg = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_dt_reg);
        ed_item_petprocurase_achados_detalhe_local = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_local);
        ed_item_petprocurase_achados_detalhe_situacao = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_situacao);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // set image based on selected text
        ImageView imageView = (ImageView) dialog.findViewById(R.id.item_petprocurase_achados_detalhe_image);
        if (!achados.getPet().getFoto().isEmpty()) {
            String strFile = "file:///android_asset/img/" + achados.getPet().getFoto();
            Picasso.with(getContext()).setLoggingEnabled(true);
            Picasso.with(getContext())
                    .load(strFile)
                    .fit()
                    .tag(this)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_pet_sem_foto_200_150);
        }

        item_petprocurase_achados_detalhe_texto.setText(R.string.item_petprocurase_achados_detalhe_texto);
        item_petprocurase_achados_detalhe_dt_fato.setText(dateFormatter.format(achados.getDt_fato()));
        ed_item_petprocurase_achados_detalhe_pet_especie.setText(achados.getPet().getEspecie());
        ed_item_petprocurase_achados_detalhe_pet_raca.setText(achados.getPet().getRaca());
        ed_item_petprocurase_achados_detalhe_pet_porte.setText(achados.getPet().getPorte());
        ed_item_petprocurase_achados_detalhe_pet_cor.setText(achados.getPet().getCor());
        ed_item_petprocurase_achados_detalhe_pet_dt_nasc.setText(dateFormatter.format(achados.getPet().getDt_nasc()));
        ed_item_petprocurase_achados_detalhe_pet_sexo.setText(achados.getPet().getSexo());
        ed_item_petprocurase_achados_detalhe_pet_caracteristicas.setText(achados.getPet().getCaracteristicas());
        ed_item_petprocurase_achados_detalhe_pet_responsavel.setText(achados.getPet().getResponsavel());
        ed_item_petprocurase_achados_detalhe_pet_contato.setText(achados.getPet().getContato());
        ed_item_petprocurase_achados_detalhe_dt_reg.setText(dateFormatter.format(achados.getDt_reg()));
        ed_item_petprocurase_achados_detalhe_local.setText(achados.getLocal());
        ed_item_petprocurase_achados_detalhe_situacao.setText(getString(achados.getSituacaoDescr()));

        Button btn_dialog_petprocurase_voltar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_voltar);
        Button btn_dialog_petprocurase_solucionar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_solucionar);

        btn_dialog_petprocurase_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_dialog_petprocurase_solucionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });

        dialog.show();
    }

    private void dialogPerdidosDetalhes(Perdidos item) {

        final Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_content_petprocurase_achados_detalhe);
        dialog.setTitle(R.string.dialog_tit_petprocurase_perdidos);

        item_petprocurase_achados_detalhe_dt_fato = (TextView) dialog.findViewById(R.id.item_petprocurase_achados_detalhe_dt_fato);
        ed_item_petprocurase_achados_detalhe_pet_especie = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_especie);
        item_petprocurase_achados_detalhe_texto = (TextView) dialog.findViewById(R.id.item_petprocurase_achados_detalhe_texto);
        ed_item_petprocurase_achados_detalhe_pet_raca = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_raca);
        ed_item_petprocurase_achados_detalhe_pet_porte = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_porte);
        ed_item_petprocurase_achados_detalhe_pet_cor = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_cor);
        ed_item_petprocurase_achados_detalhe_pet_dt_nasc = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_dt_nasc);

        ed_item_petprocurase_achados_detalhe_pet_sexo = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_sexo);
        ed_item_petprocurase_achados_detalhe_pet_caracteristicas = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_caracteristicas);
        ed_item_petprocurase_achados_detalhe_pet_responsavel = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_responsavel);
        ed_item_petprocurase_achados_detalhe_pet_contato = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_pet_contato);

        ed_item_petprocurase_achados_detalhe_dt_reg = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_dt_reg);
        ed_item_petprocurase_achados_detalhe_local = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_local);
        ed_item_petprocurase_achados_detalhe_situacao = (TextView) dialog.findViewById(R.id.ed_item_petprocurase_achados_detalhe_situacao);

        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // set image based on selected text
        ImageView imageView = (ImageView) dialog.findViewById(R.id.item_petprocurase_achados_detalhe_image);
        if (!item.getPet().getFoto().isEmpty()) {
            String strFile = "file:///android_asset/img/" + item.getPet().getFoto();
            Picasso.with(getContext()).setLoggingEnabled(true);
            Picasso.with(getContext())
                    .load(strFile)
                    .fit()
                    .tag(this)
                    .into(imageView);
        } else {
            imageView.setImageResource(R.drawable.ic_pet_sem_foto_200_150);
        }
        item_petprocurase_achados_detalhe_texto.setText(R.string.item_petprocurase_perdido_detalhe_texto);
        item_petprocurase_achados_detalhe_dt_fato.setText(dateFormatter.format(item.getDt_fato()));
        ed_item_petprocurase_achados_detalhe_pet_especie.setText(item.getPet().getEspecie());
        ed_item_petprocurase_achados_detalhe_pet_raca.setText(item.getPet().getRaca());
        ed_item_petprocurase_achados_detalhe_pet_porte.setText(item.getPet().getPorte());
        ed_item_petprocurase_achados_detalhe_pet_cor.setText(item.getPet().getCor());
        ed_item_petprocurase_achados_detalhe_pet_dt_nasc.setText(dateFormatter.format(item.getPet().getDt_nasc()));
        ed_item_petprocurase_achados_detalhe_pet_sexo.setText(item.getPet().getSexo());
        ed_item_petprocurase_achados_detalhe_pet_caracteristicas.setText(item.getPet().getCaracteristicas());
        ed_item_petprocurase_achados_detalhe_pet_responsavel.setText(item.getPet().getResponsavel());
        ed_item_petprocurase_achados_detalhe_pet_contato.setText(item.getPet().getContato());
        ed_item_petprocurase_achados_detalhe_dt_reg.setText(dateFormatter.format(item.getDt_reg()));
        ed_item_petprocurase_achados_detalhe_local.setText(item.getLocal());
        ed_item_petprocurase_achados_detalhe_situacao.setText(getString(item.getSituacaoDescr()));

        Button btn_dialog_petprocurase_voltar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_voltar);
        Button btn_dialog_petprocurase_solucionar = (Button) dialog.findViewById(R.id.btn_dialog_petprocurase_solucionar);

        btn_dialog_petprocurase_voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        btn_dialog_petprocurase_solucionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.cancel();
            }
        });

        dialog.show();
    }

}


