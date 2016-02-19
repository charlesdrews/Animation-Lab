package generalassembly.yuliyakaleda.startercode;

import android.animation.LayoutTransition;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

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

    mWishList.addView(listItem);
  }
}
