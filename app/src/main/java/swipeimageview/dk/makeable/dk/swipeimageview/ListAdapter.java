package swipeimageview.dk.makeable.dk.swipeimageview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.ArrayList;

/**
 * Created by simonchristensen on 18/11/2016.
 */

public class ListAdapter extends BaseSwipeAdapter {

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
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipe_layout;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
    }

    @Override
    public void fillValues(int position, View convertView) {

        ImageView imageView = (ImageView) convertView.findViewById(R.id.image_view);
        imageView.setBackgroundColor(Color.rgb(position * 100 % 256, 255, 255));

        TextView textView = (TextView) convertView.findViewById(R.id.text_view);
        textView.setText(String.format("This is the content at Position %d.", position));

        View swipeView = convertView.findViewById(R.id.swipe_view);
        swipeView.setBackgroundColor(Color.rgb(position * 100 % 256, 0, 0));

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
    }
}
