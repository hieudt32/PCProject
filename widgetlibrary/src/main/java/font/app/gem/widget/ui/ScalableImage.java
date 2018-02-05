package font.app.gem.widget.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Scalable Image view
 * Created by neo on 3/25/2016.
 */
public class ScalableImage extends ImageView implements View.OnTouchListener {
    // The aspect ratio to be respected by the measurer
    private double mViewAspectRatio= 1.0;

    public ScalableImage(Context context) {
        super(context);
        init();
    }

    public ScalableImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ScalableImage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        this.setOnTouchListener(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, (int) (widthSize / mViewAspectRatio));
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public void setViewAspectRatio(double viewAspectRatio) {
        mViewAspectRatio = viewAspectRatio;
    }
}
