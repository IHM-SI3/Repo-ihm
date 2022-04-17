package edu.polytech.repo_ihm.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import edu.polytech.repo_ihm.R;
import edu.polytech.repo_ihm.datas.Inventory;
import edu.polytech.repo_ihm.datas.InventoryList;
import edu.polytech.repo_ihm.datas.Product;

public class ProductListFragment extends Fragment implements AdapterView.OnItemClickListener {

    private int inventoryId;
    private EditText pName;
    private EditText pQty;
    private EditText pDate;
    private Button bSubmit;


    public ProductListFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            inventoryId = getArguments().getInt("IV_ID");
            pName =  getActivity().findViewById(R.id.et_name_product);
            pQty =   getActivity().findViewById(R.id.et_product_qty);
            pDate =  getActivity().findViewById(R.id.et_product_date);
            bSubmit = getActivity().findViewById(R.id.b_submit);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_product_list, container, false);
        ListView lv = v.findViewById(R.id.lv_product_list);
        ProductListAdapter pAdapter = new ProductListAdapter(getActivity(), getIv(inventoryId).getProducts());
        lv.setAdapter(pAdapter);
        lv.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Product p = getIv(inventoryId).getProducts().get(i);
        if(getIv(inventoryId).containProduct(p.getName())){
            pName.setText(String.format("%s", p.getName()));
            pQty.setText(String.format("%s", p.getQuantity()));
            pDate.setText(String.format("%s", p.getDateP()));
            bSubmit.setText(String.format("MODIFIER PRODUIT %s", p.getName().toUpperCase(Locale.ROOT)));
        }


    }


    //Impossible d'utiliser un attribut de Type Inventaire (fait crasher l'app) donc il faut utiliser le singleton à chaque fois...
    private Inventory getIv(int id){
        return InventoryList.getInstance().get(id);
    }
}




    /////////////////// Adapter ///////////////////


    class ProductListAdapter extends BaseAdapter{
        private List<Product> productList;
        private LayoutInflater layoutInflater;

        ProductListAdapter(Context context, List<Product> products) {
            this.productList = products;
            layoutInflater = LayoutInflater.from(context);

        }

         @Override
        public int getCount() {
            return productList.size();
        }

        @Override
        public Object getItem(int i) {
            return productList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View convertView,  ViewGroup parent) {
            ConstraintLayout layout;
            if (convertView == null)
                layout = (ConstraintLayout) layoutInflater.inflate(R.layout.custom_product_list_layout, parent, false);
            else
                layout = (ConstraintLayout) convertView;

            ImageView img = layout.findViewById(R.id.img_product);
            TextView pName = layout.findViewById(R.id.tv_product_name);
            TextView pQty = layout.findViewById(R.id.tv_product_qty);
            Product p = productList.get(i);
            img.setImageResource(p.getImg());
            pName.setText(p.getName());
            pQty.setText(MessageFormat.format("qté: {0}", p.getQuantity()));

            return layout;
        }
}


