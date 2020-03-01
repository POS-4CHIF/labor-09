package at.michaelkoenig.labor_09d;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void makeToast(Context ctx, int txtId) {
        Toast.makeText(ctx, txtId, Toast.LENGTH_SHORT).show();
    }

}
