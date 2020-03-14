package tw.org.iii.brad.brad06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class MainActivity extends AppCompatActivity {
    private MyView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myView = findViewById(R.id.myView);
    }

    public void clearMyView(View view) {
        myView.clear();
    }

    public void undo(View view) {
        myView.undo();
    }
    public void redo(View view) {
        myView.redo();
    }
    public void chColor(View view){
        ColorPickerDialogBuilder
                .with(this)
                .setTitle("Choose color")
                .initialColor(myView.getColor())
                .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                .density(12)
                .setOnColorSelectedListener(new OnColorSelectedListener() {
                    @Override
                    public void onColorSelected(int selectedColor) {
                        //toast("onColorSelected: 0x" + Integer.toHexString(selectedColor));
                    }
                })
                .setPositiveButton("ok", new ColorPickerClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                        //changeBackgroundColor(selectedColor);
                        myView.setColor(selectedColor);
                    }
                })
                .setNegativeButton("cancel", null)
                .build()
                .show();
    }
}
