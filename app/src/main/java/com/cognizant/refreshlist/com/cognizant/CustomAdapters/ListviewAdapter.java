package com.cognizant.refreshlist.com.cognizant.CustomAdapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.cognizant.refreshlist.R;
import com.cognizant.refreshlist.com.cognizant.utils.AppController;
import com.cognizant.refreshlist.com.cognizant.utils.ListviewDataModel;
import com.cognizant.refreshlist.com.cognizant.utils.ViewHolder;

public class ListviewAdapter extends BaseAdapter {

    private Context listViewAdapterContext;
    LayoutInflater layoutInflater;
    ListviewDataModel listviewDataModel;
    ImageLoader imageLoader;


    public ListviewAdapter(Context context, ListviewDataModel listviewDataModel) {
        this.listViewAdapterContext = context;
        this.listviewDataModel = listviewDataModel;
        imageLoader = AppController.getInstance().getImageLoader();

    }

    @Override
    public int getCount() {
        return listviewDataModel.getTitle().size();
    }

    @Override
    public Object getItem(int position) {
        return listviewDataModel.getTitle().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            viewHolder = new ViewHolder();

            layoutInflater = (LayoutInflater.from(listViewAdapterContext));
            convertView = layoutInflater.inflate(R.layout.custom_refreshlist_view, null);

            //Initialize UI components with ViewHolder
            viewHolder.listTitle = (TextView) convertView.findViewById(R.id.textView_title);
            viewHolder.listDescription = (TextView) convertView.findViewById(R.id.textView_desc);
            viewHolder.listImage = (NetworkImageView) convertView.findViewById(R.id.imageView);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Load UI Components with DataModel
        viewHolder.listTitle.setText(listviewDataModel.getTitle().get(position));
        viewHolder.listDescription.setText(listviewDataModel.getDescription().get(position));

        //Check the null value in Image url and add dafault image url
        String imageUrl=listviewDataModel.getImagtUrl().get(position).equals("null")?"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6b/American_Beaver.jpg/220px-American_Beaver.jpg":listviewDataModel.getImagtUrl().get(position);

        imageLoader.get(imageUrl,ImageLoader.getImageListener(viewHolder.listImage,R.mipmap.ic_launcher,android.R.drawable.ic_dialog_alert));
        viewHolder.listImage.setImageUrl(imageUrl, imageLoader);

        return convertView;
    }
}

