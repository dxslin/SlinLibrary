package com.slin.indicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slin on 2020/2/13
 */
public class ViewPagerIndicator extends HorizontalScrollView implements
        ViewPager.OnPageChangeListener, View.OnClickListener {

    private RelativeLayout[] tabArr;// tab数组
    private List<String> tabTitles = new ArrayList<>();// tab标题数组
    private int[] tabTextWidthArr;// tab标题宽度数组

    private int currTabPosition;// 当前选中的tab
    private int tabSpacing = 40;// tab之间的间距
    private int cursorPadding;// tab默认和cursor长度一样
    private float mTextSize = 42; //px
    private int tabLength;// tab个数
    private int deviceWidth;// 设备宽度px
    private int baseCursorWidth;// 初始化的游标宽度, 缩放动画每次以此为标准
    private int baseCursorLeftMargin; // 初始化游标的位置，移动时以此为准
    private boolean autoArrange = false;
    private int textColor = getResources().getColor(R.color.gray_666666); //设置选中字体的颜色
    private int selectTextColor = getResources().getColor(R.color.highlight_color); //设置未选中字体的颜色
    private boolean isShowCursor = true;    //是否显示游标
    private int cursorHeight = 4;           //游标高度
    private int cursorColor = getResources().getColor(R.color.highlight_color);    //游标颜色

    private OnTabSelectedListener onTabSelectedListener;    //tab选中事件

    private Context context;
    private ViewPager viewPager;
    private LinearLayout tabLy;// tab的横向线性布局
    private ImageView cursor;// 游标
    private Handler handler = new Handler();

    private int lastPosition = -1;  //viewpager上一次滚动时上一次的位置


    public ViewPagerIndicator(Context context) {
        super(context);
        init();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        parseAttributeSet(context, attrs, 0, 0);
        init();
    }

    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        parseAttributeSet(context, attrs, defStyle, 0);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ViewPagerIndicator(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        parseAttributeSet(context, attrs, defStyleAttr, defStyleAttr);
        init();
    }

    /**
     * 初始化基本布局
     */
    private void init() {
        context = getContext();
        deviceWidth = context.getResources().getDisplayMetrics().widthPixels;
        // 设置属性
        setHorizontalScrollBarEnabled(false);// 去掉滚动条

        // 加载ScrollView的内容布局
        View v = View.inflate(context, R.layout.viewpager_indicator_layout, null);
        tabLy = v.findViewById(R.id.main_tab);// Tab布局
        cursor = v.findViewById(R.id.main_cursor);// 游标
        cursor.setVisibility(isShowCursor ? VISIBLE : GONE);
        this.addView(v);
    }

    /**
     * 解析xml属性
     */
    private void parseAttributeSet(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator, defStyleAttr, defStyleRes);
        final int N = a.length();
        for (int i = 0; i < N; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.ViewPagerIndicator_vpi_textSize) {
                mTextSize = a.getDimensionPixelSize(attr, 15);
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_textColor) {
                textColor = a.getColor(attr, ContextCompat.getColor(context, R.color.gray_666666));
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_selectTextColor) {
                selectTextColor = a.getColor(attr, ContextCompat.getColor(context, R.color.highlight_color));
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_tabSpacing) {
                tabSpacing = a.getDimensionPixelSize(attr, 40);
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_autoArrange) {
                autoArrange = a.getBoolean(attr, true);
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_cursorPadding) {
                cursorPadding = a.getDimensionPixelSize(attr, 0);
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_isShowCursor) {
                isShowCursor = a.getBoolean(attr, true);
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_cursorColor) {
                cursorColor = a.getColor(attr, ContextCompat.getColor(context, R.color.highlight_color));
            } else if (attr == R.styleable.ViewPagerIndicator_vpi_cursorHeight) {
                cursorHeight = a.getDimensionPixelSize(attr, 4);
            }
        }

        a.recycle();

    }


    /**
     * 确定tab间隙长度
     */
    private void initTabSpacing() {
        tabLength = tabTitles.size();
        int tabTextsWidth = 0;// tab文本总长度
        TextView tv = new TextView(context);
        tabTextWidthArr = new int[tabLength];
        for (int i = 0; i < tabLength; i++) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            tabTextWidthArr[i] = (int) tv.getPaint().measureText(tabTitles.get(i));
            tabTextsWidth += tabTextWidthArr[i];
        }
        int scrollViewWidth = tabTextsWidth + tabLength * tabSpacing;
        // 默认, tab(包括间隙)总长度小于屏幕宽度时, 重新计算间距, 让各个tab均匀填满scrollview
        if (autoArrange && scrollViewWidth < deviceWidth) {
            tabSpacing = (deviceWidth - tabTextsWidth) / tabLength;
        }
    }

    /**
     * 初始化Tab
     */
    private void initTabs() {
        tabLy.removeAllViews();
        tabArr = new RelativeLayout[tabLength];
        TextView tabTextView;
        for (int i = 0; i < tabLength; i++) {
            // 初始化tabArr
            LinearLayout.LayoutParams tabParam = new LinearLayout.LayoutParams(
                    tabTextWidthArr[i] + tabSpacing, LayoutParams.MATCH_PARENT);
            tabArr[i] = (RelativeLayout) View.inflate(context, R.layout.viewpager_indicator_tab, null);
            tabArr[i].setLayoutParams(tabParam);
            tabArr[i].setTag(i);
            tabArr[i].setOnClickListener(this);
            // 初始化tabTextViewArr
            tabTextView = tabArr[i].findViewById(R.id.tv_tab);
            tabTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
            tabTextView.setTextColor(textColor);
            tabTextView.setText(tabTitles.get(i));
            // 添加子tab
            tabLy.addView(tabArr[i], i);
        }
    }

    /**
     * 初始化cursor长度
     */
    private void initCursor() {
        if (isShowCursor) {
            cursor.setVisibility(VISIBLE);
            baseCursorWidth = tabTextWidthArr[currTabPosition] + cursorPadding;
            baseCursorLeftMargin = tabSpacing / 2;
            LinearLayout.LayoutParams cursorLp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
            cursorLp.width = baseCursorWidth;
            cursorLp.height = cursorHeight;
            cursorLp.leftMargin = baseCursorLeftMargin;
            cursor.setLayoutParams(cursorLp);
            cursor.setBackgroundColor(cursorColor);
        } else {
            cursor.setVisibility(GONE);
        }
    }

    /**
     * 切换选中的tab标题
     *
     * @param position 位置
     */
    private void selectTitleTab(final int position) {
        if (position < 0 || position >= tabTitles.size()) {
            return;
        }
        currTabPosition = position;
        setTabStyle(position);// 改变tab字体颜色
        handler.post(new Runnable() {
            @Override
            public void run() {
                setTabAnimation(position);
            }
        });
    }

    /**
     * 移动下面的游标
     *
     * @param position      tab位置，这个总是游标左边的那个tab位置，不是当前选中的游标位置
     * @param percentToNext 距离下一个tab的百分比
     */
    private void moveCursor(int position, float percentToNext) {
        LinearLayout.LayoutParams cursorLp = (LinearLayout.LayoutParams) cursor.getLayoutParams();
        if (lastPosition != position) {
            lastPosition = position;
            int margin = tabSpacing / 2;
            for (int i = 0; i < position; i++) {
                margin += tabTextWidthArr[i] + tabSpacing;
            }
            baseCursorWidth = tabTextWidthArr[position] + cursorPadding;
            baseCursorLeftMargin = margin - cursorPadding / 2;
        }

        if (position >= tabLength - 1) {
            //如果是最后一个直接就不在移动了
            cursorLp.width = baseCursorWidth;
            cursorLp.leftMargin = baseCursorLeftMargin;
        } else {
            int offset = (int) ((tabTextWidthArr[position + 1] + cursorPadding - baseCursorWidth) * percentToNext + 0.5);
            cursorLp.width = baseCursorWidth + offset;
            offset = (int) ((tabTextWidthArr[position] + tabSpacing) * percentToNext + 0.5);
            cursorLp.leftMargin = baseCursorLeftMargin + offset;
        }
        cursor.setLayoutParams(cursorLp);
    }

    /**
     * 设置选中项背景和字体颜色
     *
     * @param position position
     */
    private void setTabStyle(int position) {
        // 还原Tab的背景和字体颜色
        for (int i = 0; i < tabLength; i++) {
            ((TextView) tabArr[i].getChildAt(0)).setTextColor(textColor);
        }
        // 改变 选中文本的颜色
        ((TextView) tabArr[position].getChildAt(0)).setTextColor(selectTextColor);
    }

    /**
     * 设置tab的动画
     *
     * @param position position
     */
    private void setTabAnimation(final int position) {
        // 选中的tab中心位置到scrollview最左边位置的距离
        int offset = 0;
        for (int i = 0; i < position; i++) {
            offset += tabTextWidthArr[i] + tabSpacing;
        }
        offset += (tabTextWidthArr[position] + tabSpacing) / 2;
        // tab滚动到居中位置
        int scrollOffset = offset - getWidth() / 2 <= 0 ? 0 : offset - getWidth() / 2;
        smoothScrollTo(scrollOffset, 0);
    }


    @Override
    public void onClick(View v) {
        int i = (Integer) v.getTag();
        setSelectedTab(i);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        moveCursor(position, positionOffset);
    }

    @Override
    public void onPageSelected(final int position) {
        selectTitleTab(position);
    }


    /**
     * 设置tab文字
     *
     * @param tabTitles tab title text
     */
    public void setTabTitles(List<String> tabTitles) {
        if (tabTitles == null || tabTitles.size() < 1) {
            throw new IllegalArgumentException("tabTexts不能为空且长度大于1");
        }
        this.tabTitles.clear();
        this.tabTitles.addAll(tabTitles);

        initTabSpacing();
        initTabs();
        initCursor();
    }

    /**
     * 绑定ViewPager
     *
     * @param viewPager viewpager
     */
    public void bindViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        onPageSelected(currTabPosition);// 初始化tab样式和动画
        if (this.viewPager != null) {
            this.viewPager.addOnPageChangeListener(this);
        }
    }

    /**
     * 设置当前Tab点击时事件
     *
     * @param position position
     */
    public void setSelectedTab(int position) {
        // 如果点击的为当前的Tab，则滚回到顶部， 否则切换
        if (position != currTabPosition) {
            if (viewPager != null) {
                viewPager.setCurrentItem(position);
            } else {
                //选中切换标题
                selectTitleTab(position);
                //切换游标
                moveCursor(position, 0);
            }
            if (onTabSelectedListener != null) {
                onTabSelectedListener.onTabSelected(position);
            }
        }
    }

    /**
     * 设置未选中tab字体的颜色
     */
    public void setTextColor(@ColorInt int UnSelectTextColor) {
        this.textColor = UnSelectTextColor;
    }

    /**
     * 设置选中tab字体的颜色
     */
    public void setSelectTextColor(@ColorInt int selectTextColor) {
        this.selectTextColor = selectTextColor;
    }

    /**
     * 设置tab字体的大小
     *
     * @param textSize 字体大小sp
     */
    public void setTextSize(int textSize) {
        this.mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize, context.getResources().getDisplayMetrics());
    }

    /**
     * tab选中事件
     *
     * @param onTabSelectedListener listener
     */
    public void setOnTabSelectedListener(OnTabSelectedListener onTabSelectedListener) {
        this.onTabSelectedListener = onTabSelectedListener;
    }


    public interface OnTabSelectedListener {
        void onTabSelected(int position);
    }

}