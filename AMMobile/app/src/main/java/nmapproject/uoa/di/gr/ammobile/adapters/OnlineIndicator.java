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

/**
 * This is an implementation of a an adapter with two elements (one on the left and one on the right)
 * and its purpose is to show a list of items and their condition (true/ false) with use of a green
 * or a red indicator.
 * @param <T> The type of the list tha will be on the left. It usually should be string.
 */
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

    /**
     * It just ccreates and initializes the adapter.
     * @param context
     * @param resource
     * @param textViewResourceId The id of the TextView to be populated on the left
     * @param imageViewResourceId The id of the ImageView to be populated on the right
     * @param objects The list of objects that will populate the TextView list on the left
     * @param onInd The list of the boolean values that will populate the ImageView on the right
     */
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

    /**
     * It just ccreates and initializes the adapter.
     * @param context
     * @param resource
     * @param textViewResourceId The id of the TextView to be populated on the left
     * @param imageViewResourceId The id of the ImageView to be populated on the right
     */
    public OnlineIndicator(Context context, @LayoutRes int resource, @IdRes int textViewResourceId, @IdRes int imageViewResourceId) {
        this(context, resource, textViewResourceId, imageViewResourceId, new ArrayList<T>(), new ArrayList<Boolean>());
    }

    /**
     * Adds an element to the adapter.
     * @param object The value for the TextView
     * @param isOnline The value for the ImageView (boolean)
     */
    public void add(T object, Boolean isOnline) {
//        if (mOriginalValues != null) {
//            mOriginalValues.add(object);
//        } else {
            mObjects.add(object);
            mOnline.add(isOnline);
//        }
        notifyDataSetChanged();
    }

    /**
     * Clears the adapter of every item it had.
     */
    public void removeAll() {
        mObjects = new ArrayList<T>();
        mOnline = new ArrayList<Boolean>();
    }

    /**
     * Gets the adapters size of items.
     * @return Number of items.
     */
    public int getCount() {
        return mObjects.size();
    }

    /**
     * Gets the item at the specified position
     * @param position The position from which to take the item
     * @return The item at the specified position
     */
    public T getItem(int position) {
        return mObjects.get(position);
    }

    /**
     * Gets the boolean value of the item at the specified position
     * @param position The position of the item
     * @return The boolean value
     */
    public Boolean getOnline(int position) {
        return mOnline.get(position);
    }

    /**
     * Returns the position - itemiD
     * @param position The position of the item
     * @return The position of the item.
     */
    public long getItemId(int position) {
        return position;
    }

    /**
     * lalala
     */
    public static class ViewHolder{
        public TextView textList;
    }

    /**
     * Gets - creates the adapter's view.
     * @param position the position
     * @param convertView the view
     * @param parent The view's parent
     * @return the new view
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(mInflater, position, convertView, parent, mResource);
    }

    /**
     * Creates and populates the view, given some rescources.
     * @param inflater inflater
     * @param position position
     * @param convertView convertView
     * @param parent parent
     * @param resource resource
     * @return view
     */
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

