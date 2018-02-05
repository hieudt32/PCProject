package app.positiveculture.com.agent;

import com.gemvietnam.base.ContainerActivity;
import com.gemvietnam.base.viper.ViewFragment;

import app.positiveculture.com.agent.screen.properties.createotp.CreateOtpPresenter;


public class MainActivity extends ContainerActivity  {

  @Override
  public ViewFragment onCreateFirstFragment() {
    return (ViewFragment) new CreateOtpPresenter(this).getFragment();

  }
}
