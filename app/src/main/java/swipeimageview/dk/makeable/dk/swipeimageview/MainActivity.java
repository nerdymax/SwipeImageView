package swipeimageview.dk.makeable.dk.swipeimageview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.daimajia.swipe.SwipeLayout;

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
    }
}
