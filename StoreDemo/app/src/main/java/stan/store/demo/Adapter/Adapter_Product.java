package stan.store.demo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashMap;

import stan.store.demo.Model.Product;
import stan.store.demo.R;

public class Adapter_Product extends RecyclerView.Adapter<Adapter_Product.ViewHolder> {
    private Context mContext;
    private Product mProduct;

    public Adapter_Product(Context context, Product Source_Struct) {
        this.mContext = context;
        this.mProduct = Source_Struct;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //宣告View底家
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.mProduct_Img = (ImageView)view.findViewById(R.id.Product_Img);
        holder.mTextView_ProductName = (TextView) view.findViewById(R.id.textView_ProductName);
        holder.mTextView_ProductPrice = (TextView) view.findViewById(R.id.textView_ProductPrice);
        holder.mtextView_ProductExplain = (TextView)view.findViewById(R.id.textView_ProductExplain);
        holder.mButton_AddCart = (ImageView) view.findViewById(R.id.button_AddCart);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //設定資料底家
        holder.mButton_AddCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
        switch (position){
            case 0:
                holder.mProduct_Img.setImageResource(R.drawable.product);
                break;
            case 1:
                holder.mProduct_Img.setImageResource(R.drawable.products1);
                break;
            case 2:
                holder.mProduct_Img.setImageResource(R.drawable.products2);
                break;
            case 3:
                holder.mProduct_Img.setImageResource(R.drawable.products3);
                break;
            case 4:
                holder.mProduct_Img.setImageResource(R.drawable.products4);
                break;
            case 5:
                holder.mProduct_Img.setImageResource(R.drawable.products5);
                break;
            case 6:
                holder.mProduct_Img.setImageResource(R.drawable.products6);
                break;
            case 7:
                holder.mProduct_Img.setImageResource(R.drawable.products7);
                break;
            default:
                holder.mProduct_Img.setImageResource(R.drawable.photo);
                break;
        }
        holder.mTextView_ProductName.setText(mProduct.getName(position));
        holder.mTextView_ProductPrice.setText("$" + mProduct.getPrice(position));
        holder.mtextView_ProductExplain.setText(mProduct.getExplain(position));
    }

    @Override
    public int getItemCount() {
        return mProduct.mArrayList_ProductData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mProduct_Img;
        public TextView mTextView_ProductName;
        public TextView mTextView_ProductPrice;
        public TextView mtextView_ProductExplain;
        public ImageView mButton_AddCart;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}