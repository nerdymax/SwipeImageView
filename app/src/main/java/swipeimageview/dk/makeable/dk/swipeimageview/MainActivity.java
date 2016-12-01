package swipeimageview.dk.makeable.dk.swipeimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;
import org.w3c.dom.Text;

import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class MainActivity extends AppCompatActivity {

    private StickyListHeadersListView mListView;
    private ListAdapter mListAdapter;
    private ArrayList<String> mArrayList;

    private int[] mCellHeights;
    final int HEADER_HEIGHT_IN_DP = 50; // Assuming all cell has the same height (50dp)
    final int[] HEADER_POSITIONS = {0, 3}; // Assuming we know the positions of the header

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

        final int totalHeight = populateCellHeights();

        final TextView indicatorView = (TextView) findViewById(R.id.indicator_view);

        mListView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                final int listViewHeight = mListView.getHeight();
                final int indicatorViewHeight = indicatorView.getHeight();
                final int scrollableHeight = totalHeight - listViewHeight;

                int index = mListView.getFirstVisiblePosition();
                View itemView = mListView.getListChildAt(0);

                int positionY = -itemView.getTop() + mCellHeights[index];

                float currentPercent = (float)positionY / (float)scrollableHeight;
                int indicatorY = (int)((listViewHeight - indicatorViewHeight) * currentPercent);

                final int PADDING_TOP = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
                indicatorView.setY(indicatorY + PADDING_TOP); // Just for testing, added the top padding

                indicatorView.setText("Current item: " + index);
            }
        });
    }

    private int populateCellHeights() {

        mCellHeights = new int[mListView.getCount()];

        int dividerHeight = mListView.getDividerHeight();
        int headerHeight = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, HEADER_HEIGHT_IN_DP, getResources().getDisplayMetrics());

        int totalHeight = 0;

        for (int i = 0; i < mListAdapter.getCount(); i++) {
            mCellHeights[i] = totalHeight;


            View mView = mListAdapter.getView(i, null, mListView);
            mView.measure(
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            int cellHeight = 0;
            if (ArrayUtils.contains(HEADER_POSITIONS, i)) { // If the current position has the header
                cellHeight += headerHeight;
            } else {
                cellHeight += dividerHeight;
            }
            cellHeight += mView.getMeasuredHeight();

            totalHeight += cellHeight;
        }
        return totalHeight;
    }
}
