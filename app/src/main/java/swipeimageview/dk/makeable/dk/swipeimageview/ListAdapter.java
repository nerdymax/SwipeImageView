package swipeimageview.dk.makeable.dk.swipeimageview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by simonchristensen on 18/11/2016.
 */

public class ListAdapter extends BaseAdapter{

    private Context mContext;
    private ArrayList<String> mImageViews;

    public ListAdapter(Context context, ArrayList<String> arrayList) {
        mContext = context;
        mImageViews = arrayList;
    }

    @Override
    public int getCount() {
        return mImageViews.size();
    }

    @Override
    public Object getItem(int i) {
        return mImageViews.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    private static class ViewHolder{
        private ImageView mImageView;
    }
}
