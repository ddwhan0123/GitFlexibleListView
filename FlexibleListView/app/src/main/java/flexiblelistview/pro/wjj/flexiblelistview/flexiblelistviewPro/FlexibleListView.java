package flexiblelistview.pro.wjj.flexiblelistview.flexiblelistviewPro;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkfuns.logutils.LogUtils;
import com.nineoldandroids.animation.Animator;

import java.util.ArrayList;

import flexiblelistview.pro.wjj.flexiblelistview.Anim.Techniques;
import flexiblelistview.pro.wjj.flexiblelistview.Anim.YoYo;
import flexiblelistview.pro.wjj.flexiblelistview.R;

/**
 * Created by Ezreal on 2015/12/18.
 */
public class FlexibleListView extends RelativeLayout implements AbsListView.OnScrollListener ,AdapterView.OnItemClickListener{
    private Context context;
    private YoYo.YoYoString rope;
    //根布局
    private View rootView;
    private LinearLayout headView, footView;
    private ListView listView;
    private MyAdapter myAdapter;

    public FlexibleListView(Context context) {
        super(context);
    }

    public FlexibleListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs);
    }

    public FlexibleListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = inflater.inflate(R.layout.flexible_listview, this);
        headView = (LinearLayout) rootView.findViewById(R.id.headView);
        footView = (LinearLayout) rootView.findViewById(R.id.footView);
        listView = (ListView) rootView.findViewById(R.id.listView);
        myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
        listView.setOnScrollListener(this);
        listView.setOnItemClickListener(this);
    }

    public ArrayList makeData() {
        ArrayList<String> list = new ArrayList<>();
        for (int k = 0; k < 30; k++) {
            list.add("第" + k + "个数");
        }
        return list;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                LogUtils.d("--->onScrollStateChanged AbsListView.OnScrollListener.SCROLL_STATE_IDLE");
                //滚动停止
                footView.setVisibility(View.VISIBLE);
                headView.setVisibility(View.VISIBLE);
                makeHeadInAnim(headView, 1500);
                makeFootInAnim(footView, 1500);
                myAdapter.notifyDataSetChanged();
                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
                //正在滚动
//                LogUtils.d("--->onScrollStateChanged AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL");
                footView.setVisibility(View.GONE);
                headView.setVisibility(View.GONE);
                myAdapter.notifyDataSetChanged();

                break;
            case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
                LogUtils.d("--->onScrollStateChanged AbsListView.OnScrollListener.SCROLL_STATE_FLING");
                //开始滚动
                break;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(context,"第"+position+"个",Toast.LENGTH_SHORT).show();
    }

    public class MyAdapter extends BaseAdapter {
        ArrayList<String> list = makeData();

        public final class ViewHolder {
            public TextView text;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                holder = new ViewHolder();
                LinearLayout view = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.item_listview, null);
                convertView = view;
                holder.text = (TextView) convertView.findViewById(R.id.itemTextView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.text.setText(list.get(position));
            return convertView;
        }
    }

    //头部进场动画
    private void makeHeadInAnim(View view, int value) {
        Techniques technique = (Techniques) view.getTag();
        rope = YoYo.with(technique.BounceInDown)
                .duration(value)
                .playOn(view);
    }

    //底部进场动画
    private void makeFootInAnim(View view, int value) {
        Techniques technique = (Techniques) view.getTag();
        rope = YoYo.with(technique.BounceInUp)
                .duration(value)
                .playOn(view);
    }

}
