package app.positiveculture.com.user.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import app.positiveculture.com.user.R;
import butterknife.ButterKnife;

/**
 * Created by HaiLS on 28/09/2017.
 */

public class CanvasView extends View {

  private static final float TOUCH_TOLERANCE = 4;

  public int width;
  public int height;
  private float mX, mY;
  private Bitmap mBitmap;
  private Paint mBitmapPaint;
  private Canvas mCanvas;
  private Paint mPaint;
  private Path mPath;
  private Paint mCirclePaint;
  private Path mCirclePath;
  private OnSignListener mOnSignListener;

  public void setOnSignListener(OnSignListener mOnSignListener) {
    this.mOnSignListener = mOnSignListener;
  }

  public CanvasView(Context context) {
    super(context);
    init(context);
  }

  public CanvasView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    inflate(context, R.layout.drawing_canvas_layout, null);
    ButterKnife.bind(this);

    mPath = new Path();
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setDither(true);
    mPaint.setColor(Color.BLACK);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeJoin(Paint.Join.ROUND);
    mPaint.setStrokeCap(Paint.Cap.ROUND);
    mPaint.setStrokeWidth(4);

    mBitmapPaint = new Paint(Paint.DITHER_FLAG);

    mCirclePath = new Path();
    mCirclePaint = new Paint();
    mCirclePaint.setAntiAlias(true);
    mCirclePaint.setColor(Color.BLACK);
    mCirclePaint.setStyle(Paint.Style.STROKE);
    mCirclePaint.setStrokeJoin(Paint.Join.MITER);
    mCirclePaint.setStrokeWidth(4f);

    setDrawingCacheEnabled(true);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    width = w;
    height = h;

    mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    mCanvas = new Canvas(mBitmap);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);

    canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
    canvas.drawPath(mPath, mPaint);
    canvas.drawPath(mCirclePath, mCirclePaint);
  }

  private void touch_start(float x, float y) {
    mPath.reset();
    mPath.moveTo(x, y);
    mX = x;
    mY = y;
    mPath.quadTo(mX, mY, mX + 0.1f, mY + 0.1f);
  }

  private void touch_move(float x, float y) {
    float dx = Math.abs(x - mX);
    float dy = Math.abs(y - mY);
    if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
      mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
      mX = x;
      mY = y;

      mCirclePath.reset();
      mCirclePath.addCircle(mX, mY, 20, Path.Direction.CW);
    }
  }

  private void touch_up() {
    mPath.lineTo(mX, mY);
    mCirclePath.reset();
    // commit the path to our offscreen
    mCanvas.drawPath(mPath, mPaint);
    // kill this so we don't double draw
    mPath.reset();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();

    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        touch_start(x, y);
        invalidate();
        break;
      case MotionEvent.ACTION_MOVE:
        touch_move(x, y);
        invalidate();
        break;
      case MotionEvent.ACTION_UP:
        touch_up();
        invalidate();
        if (mOnSignListener != null) {
          mOnSignListener.onSigned(checkCanvasEmpty());
        }
        break;
    }
    return true;
  }

  public void clearCanvas() {
    setDrawingCacheEnabled(false);
    onSizeChanged(width, height, width, height);
    invalidate();
    setDrawingCacheEnabled(true);
    if (mOnSignListener != null) {
      mOnSignListener.onSigned(checkCanvasEmpty());
    }
  }

  private boolean checkCanvasEmpty() {
    return getDrawingCache().sameAs(Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888));
  }

  public interface OnSignListener {
    void onSigned(boolean isEmpty);
  }
}
