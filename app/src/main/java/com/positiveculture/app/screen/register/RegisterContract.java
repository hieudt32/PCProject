package com.positiveculture.app.screen.register;

import com.gemvietnam.base.viper.interfaces.IInteractor;
import com.gemvietnam.base.viper.interfaces.IPresenter;
import com.gemvietnam.base.viper.interfaces.PresentView;

import app.positiveculture.com.data.callback.CommonCallback;
import app.positiveculture.com.data.response.dto.CEAProfileDTO;

/**
 * The Register Contract
 */
interface RegisterContract {

	interface Interactor extends IInteractor<Presenter> {
		void checkCeaNumber(String ceaNumber, CommonCallback<CEAProfileDTO> commonCallback);
	}

	interface View extends PresentView<Presenter> {
		void onCheckCeaNumberSuccessful(CEAProfileDTO data);

		void onCheckCeaNumberFailed();
	}

	interface Presenter extends IPresenter<View, Interactor> {
		void gotoConfirmProfile(CEAProfileDTO data);

		void onNextSelected(String trim);

		void goBack();
	}
}



