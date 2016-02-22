package generalassembly.yuliyakaleda.startercode;

import android.animation.LayoutTransition;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  EditText mInput;
  TextView mYourWish;
  LinearLayout mWishList;
  Button mMakeWish;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mInput = (EditText) findViewById(R.id.input);
    mYourWish = (TextView) findViewById(R.id.your_wish);
    mWishList = (LinearLayout) findViewById(R.id.wish_list);
    mMakeWish = (Button) findViewById(R.id.button);

    mMakeWish.setOnClickListener(MainActivity.this);

    LayoutTransition transition = new LayoutTransition();
    transition.enableTransitionType(LayoutTransition.CHANGING);
    mWishList.setLayoutTransition(transition);
  }

  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.button:
        addWish();
        break;
      default:
        if ( ((ViewGroup) v.getParent()).getId() == R.id.wish_list ) {
          removeWish( (TextView) v );
        }
        break;
    }
  }

  private void addWish() {
    String wish = mInput.getText().toString();
    if (wish.isEmpty()) {
      mInput.setError("Enter your wish first!");
      return;
    }

    mYourWish.setText(wish);
    mInput.setText(null);

    Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.animation);
    mYourWish.startAnimation(animation);

    TextView listItem = new TextView(MainActivity.this);
    listItem.setText(wish);
    listItem.setPadding(8, 8, 8, 8);
    listItem.setOnClickListener(MainActivity.this);

    mWishList.addView(listItem, 0);
  }

  private void removeWish(final TextView v) {
    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

    builder.setTitle("Remove Wish");
    builder.setMessage("Remove " + v.getText() + " from your wish list?");

    builder.setNegativeButton("Cancel", null);
    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        mWishList.removeView(v);
        dialog.dismiss();
        Toast.makeText(MainActivity.this, "remove", Toast.LENGTH_SHORT).show();
      }
    });

    builder.show();
  }
}
