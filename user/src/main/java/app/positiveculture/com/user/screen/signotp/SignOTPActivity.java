package app.positiveculture.com.user.screen.signotp;

import com.gemvietnam.base.ContainerActivity;
import com.gemvietnam.base.viper.ViewFragment;

/**
 * SignOTPActivity
 * Created by BB on 9/19/2017.
 */

public class SignOTPActivity extends ContainerActivity {


  @Override
  public ViewFragment onCreateFirstFragment() {
    return (ViewFragment) new SignOTPPresenter(this)
            .getFragment();
  }
}
