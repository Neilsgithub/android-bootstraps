package info.xiequan.androidbootstraps.view.image;


import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;

/**
 * Created by Wilson on 14-4-17.
 * enables hardware accelerated rounded corners
 * https://gist.github.com/aprock/6213395
 * http://stackoverflow.com/questions/17225374/rounding-only-one-image-corner-not-all-four
 */

public class RoundedTransformation implements com.squareup.picasso.Transformation {
    private final int radius;
    private final boolean isRoundTopLeft;
    private final boolean isRoundTopRight;
    private final boolean isRoundBottomRight;
    private final boolean isRoundBottomLeft;


    // radius is corner radii in dp
    // margin is the board in dp


    public RoundedTransformation(int radius, boolean isRoundTopLeft, boolean isRoundTopRight, boolean isRoundBottomRight, boolean isRoundBottomLeft) {
        this.radius = radius;
        this.isRoundTopLeft = isRoundTopLeft;
        this.isRoundTopRight = isRoundTopRight;
        this.isRoundBottomRight = isRoundBottomRight;
        this.isRoundBottomLeft = isRoundBottomLeft;

    }

    @Override
    public Bitmap transform(final Bitmap source) {
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));

        Bitmap output = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        Rect rect = new Rect(0, 0, source.getWidth(), source.getHeight());
        RectF rectF = new RectF(0, 0, source.getWidth(), source.getHeight());

        final Rect topLeftRect = new Rect(0, 0, source.getWidth() / 2, source.getHeight() / 2);
        final Rect topRightRect = new Rect(source.getWidth() / 2, 0, source.getWidth(), source.getHeight() / 2);
        final Rect bottomLeftRect = new Rect(0, source.getHeight() / 2, source.getWidth() / 2, source.getHeight());
        final Rect bottomRightRect = new Rect(source.getWidth() / 2, source.getHeight() / 2, source.getWidth(), source.getHeight());

        //Draw 4 corners
        canvas.drawRoundRect(rectF, radius, radius, paint);

        // Fill in corner with rect
        if (!isRoundTopLeft) {
            canvas.drawRect(topLeftRect, paint);
        }
        if (!isRoundTopRight) {
            canvas.drawRect(topRightRect, paint);
        }
        if (!isRoundBottomRight) {
            canvas.drawRect(bottomRightRect, paint);
        }
        if (!isRoundBottomLeft) {
            canvas.drawRect(bottomLeftRect, paint);
        }


        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(source, rect, rect, paint);

        if (source != output) {
            source.recycle();
        }

        return output;
    }

    @Override
    public String key() {
        return "rounded";
    }
}