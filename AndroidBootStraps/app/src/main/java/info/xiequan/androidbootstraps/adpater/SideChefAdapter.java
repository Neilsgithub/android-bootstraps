package info.xiequan.androidbootstraps.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import info.xiequan.androidbootstraps.R;
import info.xiequan.androidbootstraps.entity.Data;

/**
 * Created by spark on 31/1/15.
 * www.blueowls.net
 * i@xiequan.info
 */
public class SideChefAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;
    private List<Data> dataList;

    public SideChefAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = layoutInflater.inflate(R.layout.sidechef_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.refreshData(dataList.get(i));
        return convertView;
    }

    private class ViewHolder {
        TextView recipeame;

        ViewHolder(View parent) {
            initViewHolder(parent);
        }

        public void initViewHolder(View parent) {
            recipeame = (TextView) parent.findViewById(R.id.tv_recipeame);
        }

        public void refreshData(Data data) {
            recipeame.setText(data.getRecipeName());

        }
    }
}