package swipeimageview.dk.makeable.dk.swipeimageview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by simonchristensen on 18/11/2016.
 */

public class ListAdapter extends BaseAdapter {

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
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            holder.textView = (TextView) convertView.findViewById(R.id.text_view);
            holder.swipeView = convertView.findViewById(R.id.swipe_view);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.imageView.setBackgroundColor(Color.rgb(i * 100, 100, 100));
        holder.textView.setText(String.format(Locale.US, "Text at Position %d", i));
        holder.swipeView.setBackgroundColor(Color.rgb(i * 100, 0, 100));

        // Set up swipe layout & event listener
        SwipeLayout swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe_layout);
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);
        swipeLayout.addSwipeListener(new SwipeLayout.SwipeListener() {
            @Override
            public void onClose(SwipeLayout layout) {
                //when the SurfaceView totally cover the BottomView.
            }

            @Override
            public void onUpdate(SwipeLayout layout, int leftOffset, int topOffset) {
                //you are swiping.
            }

            @Override
            public void onStartOpen(SwipeLayout layout) {

            }

            @Override
            public void onOpen(SwipeLayout layout) {
                //when the BottomView totally show.
            }

            @Override
            public void onStartClose(SwipeLayout layout) {

            }

            @Override
            public void onHandRelease(SwipeLayout layout, float xvel, float yvel) {
                //when user's hand released.
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private View swipeView;
    }
}
