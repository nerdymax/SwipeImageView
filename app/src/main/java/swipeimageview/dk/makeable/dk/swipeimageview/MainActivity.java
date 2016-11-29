package swipeimageview.dk.makeable.dk.swipeimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private StickyListHeadersListView mListView;
    private ListAdapter mListAdapter;
    private ArrayList<String> mArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (StickyListHeadersListView) findViewById(R.id.listview);
        generatePictures();
    }

    private void generatePictures() {
        mArrayList = new ArrayList<>();
        mArrayList.add("");
        mArrayList.add("");
        mArrayList.add("");
        mArrayList.add("");
        mArrayList.add("");
        mArrayList.add("");
        mArrayList.add("");

        mListAdapter = new ListAdapter(this, mArrayList);

        mListView.setAdapter(mListAdapter);

        mListView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int totalHeight = getTotalHeightOfListView(mListView);
            }
        });
    }

    private static int getTotalHeightOfListView(StickyListHeadersListView listView) {

        ListAdapter mAdapter = (ListAdapter) listView.getAdapter();

        int totalHeight = 0;

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View mView = mAdapter.getView(i, null, listView);

            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            totalHeight += mView.getMeasuredHeight();
        }

        Log.w("HEIGHT", String.valueOf(totalHeight));

        return totalHeight;

    }
}
