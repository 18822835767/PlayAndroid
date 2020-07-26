package com.example.playandroid.search.search_hint;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.playandroid.R;
import com.example.playandroid.entity.HotWord;
import com.example.playandroid.util.HandlerUtil;
import com.example.playandroid.util.network.LogUtil;
import com.example.playandroid.view.flowlayout.FlowLayout;
import com.example.playandroid.view.flowlayout.TagModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static com.example.playandroid.util.Constants.SearchHintConstant.HOT_WORD_SUCCESS;

/**
 * 展示搜索热词以及搜索历史的碎片.
 */
public class SearchHintFragment extends Fragment implements SearchHintContract.OnView, 
        View.OnClickListener {

    private static final String TAG = "SearchHintFragment";
    private SearchHintContract.Presenter mPresenter;
    private boolean mFirstLoad = true;
    private FlowLayout mFlowLayout;
    private List<HotWord> mHotWords = new ArrayList<>();
    private View mView;
    private OnListener mListener;

    /**
     * 标记是否需要恢复界面的View.
     */
    private boolean mShouldAddViewAgain = false;

    /**
     * 标记当前页面是否正在显示.
     */
    private boolean mShowing = false;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (OnListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_search_hint, container, false);

        initView();
        initData();
        
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mFirstLoad) {
            mFirstLoad = false;
            mPresenter.start();
        }

        //恢复搜索热词的子View
        if (mShouldAddViewAgain) {
            addViewToFlowLayout();
            mShouldAddViewAgain = false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        //当前页面正在显示.
        mShowing = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        //当前页面没有在显示
        mShowing = false;
    }

    public void setShouldAddViewAgain(boolean shouldAddViewAgain) {
        mShouldAddViewAgain = shouldAddViewAgain;
    }

    private void initView() {
        mFlowLayout = mView.findViewById(R.id.flow_layout);
    }

    private void initData() {
        new SearchHintPresenter(this);
    }

    private void addViewToFlowLayout() {
        for (int i = 0; i < mHotWords.size(); i++) {
            HotWord hotWord = mHotWords.get(i);
            //获得流式布局的子view
            View view = FlowLayout.createChildView((int) mFlowLayout.getItemHeight(), hotWord,
                    R.layout.textview);
            view.setBackgroundResource(R.color.deepGreen);
            //设置点击监听
            view.setOnClickListener(this);
            //添加子View
            mFlowLayout.addView(view);
        }
    }

    /**
     * 获得搜索热词页面的显示情况.
     */
    public boolean isShowing() {
        return mShowing;
    }

    @Override
    public void onGetHotWordsSuccess(List<HotWord> hotWords) {
        mHotWords.clear();
        mHotWords.addAll(hotWords);

        HandlerUtil.post(new UIRunnable(this, HOT_WORD_SUCCESS));
    }

    @Override
    public void onGetHotWordsFailure(Exception e) {
        LogUtil.d(TAG, e.getMessage());
    }

    @Override
    public void getHistoriesFromDaoSuccess(List<String> histories) {
        
    }

    @Override
    public void setPresenter(SearchHintContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {
        if(mListener != null){
            mListener.onClickQuery(((HotWord)(((TagModel)(v.getTag())).getT())).getName());
        }
    }

    private static class UIRunnable implements Runnable {

        private WeakReference<SearchHintFragment> mWeak;
        private int mType;

        UIRunnable(SearchHintFragment fragment, int type) {
            mWeak = new WeakReference<>(fragment);
            mType = type;
        }

        @Override
        public void run() {
            switch (mType) {
                case HOT_WORD_SUCCESS:
                    if (mWeak.get() != null) {
                        mWeak.get().addViewToFlowLayout();
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
    public interface OnListener{
        /**
         * 点击搜索热词时，直接回调活动中的方法，进行搜索.
         * */
        void onClickQuery(String query);
    }
}
