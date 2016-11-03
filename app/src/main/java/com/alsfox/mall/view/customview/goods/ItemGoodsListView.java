package com.alsfox.mall.view.customview.goods;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alsfox.mall.R;

/**
 * Created by 浩 on 2016/10/28.
 * list型商品item
 */

public class ItemGoodsListView {
    public ImageView searth_list_goods_img;//商品图片
    public TextView searth_list_goods_name;//商品名字
    public TextView searth_list_goods_price;//商品价格
    public RatingBar searth_list_goods_score;//商品评价
    public TextView searth_list_goods_num;//商品购买数量

    public ItemGoodsListView(View itemView) {
        searth_list_goods_img = (ImageView) itemView.findViewById(R.id.searth_list_goods_img);//商品图片
        searth_list_goods_name = (TextView) itemView.findViewById(R.id.searth_list_goods_name);//商品名字
        searth_list_goods_price = (TextView) itemView.findViewById(R.id.searth_list_goods_price);//商品价格
        searth_list_goods_score = (RatingBar) itemView.findViewById(R.id.searth_list_goods_score);//商品评价
        searth_list_goods_num = (TextView) itemView.findViewById(R.id.searth_list_goods_num);//商品购买数量
    }


}
