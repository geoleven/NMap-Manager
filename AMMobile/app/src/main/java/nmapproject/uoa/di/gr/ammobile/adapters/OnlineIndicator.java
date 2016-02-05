package nmapproject.uoa.di.gr.ammobile.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nmapproject.uoa.di.gr.ammobile.R;

public class OnlineIndicator<T> extends BaseAdapter {

    private Activity activity;
    private List<T> mObjects;
    private List<Boolean> mOnline;
    private final LayoutInflater mInflater;
    private int mResource;
    private Context mContext;
    private int mDropDownResource;
    private int mTextId = 0;
    @IdRes private int mImageId = 1;
//    private ArrayList<T> mOriginalValues;

    public OnlineIndicator(Context context, @LayoutRes int resource, @IdRes int textViewResourceId,
                        @IdRes int imageViewResourceId, @NonNull List<T> objects, @NonNull List<Boolean> onInd) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mResource = mDropDownResource = resource;
        mObjects = objects;
        mOnline = onInd;
        mTextId = textViewResourceId;
        mImageId = imageViewResourceId;
    }

    public OnlineIndicator(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @IdRes int imageViewResourceId) {
        this(context, resource, textViewResourceId, imageViewResourceId, new ArrayList<T>(), new ArrayList<Boolean>());
    }

    public void add(T object, Boolean isOnline) {
//        if (mOriginalValues != null) {
//            mOriginalValues.add(object);
//        } else {
            mObjects.add(object);
            mOnline.add(isOnline);
//        }
        notifyDataSetChanged();
    }

    public void removeAll() {
        mObjects = new ArrayList<T>();
        mOnline = new ArrayList<Boolean>();
    }

    public int getCount() {
        return mObjects.size();
    }

    public T getItem(int position) {
        return mObjects.get(position);
    }

    public Boolean getOnline(int position) {
        return mOnline.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView textList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(mInflater, position, convertView, parent, mResource);
    }

    private View createViewFromResource(LayoutInflater inflater, int position, View convertView,
                                        ViewGroup parent, int resource) {
        View view;
        TextView text;
        ImageView image;

        if (convertView == null) {
            view = inflater.inflate(resource, parent, false);
        } else {
            view = convertView;
        }

        try {
                text = (TextView) view.findViewById(mTextId);
                image = (ImageView) view.findViewById(mImageId);
        } catch (ClassCastException e) {
            Log.e("ArrayAdapter", "You must supply a resource ID for a TextView");
            throw new IllegalStateException(
                    "ArrayAdapter requires the resource ID to be a TextView", e);
        }

        T item = getItem(position);
        boolean isOn = getOnline(position);
        if (item instanceof CharSequence) {
            text.setText((CharSequence) item);
            if(isOn)
                image.setImageResource(R.drawable.green);
            else
                image.setImageResource(R.drawable.red);
        } else {
            text.setText(item.toString());
            if(isOn)
                image.setImageResource(R.drawable.green);
            else
                image.setImageResource(R.drawable.red);
        }

        return view;
    }


}

