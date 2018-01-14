package schweika.chatapplication;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.RelativeLayout;

public class BindingAdapters
{
    @BindingAdapter("android:layout_alignParentEnd")
    public static void setAlignParentEnd(View view, boolean alignParentLeft) {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                view.getLayoutParams()
        );

        if (alignParentLeft) {
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_END);
        }

        view.setLayoutParams(layoutParams);
    }
}
