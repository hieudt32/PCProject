package com.positiveculture.app.screen.forgotpassword;

import com.gemvietnam.base.viper.Interactor;
import com.google.gson.JsonElement;

import app.positiveculture.com.data.ServiceBuilder;
import app.positiveculture.com.data.callback.CommonCallback;

/**
 * The ForgotPassword interactor
 */
class ForgotPasswordInteractor extends Interactor<ForgotPasswordContract.Presenter>
        implements ForgotPasswordContract.Interactor {

    ForgotPasswordInteractor(ForgotPasswordContract.Presenter presenter) {
        super(presenter);
    }

    @Override
    public void forgotPassword(String email, CommonCallback<JsonElement> commonCallback) {
        ServiceBuilder.getInstance().getService().forgotPassword(email).enqueue(commonCallback);
    }

}
