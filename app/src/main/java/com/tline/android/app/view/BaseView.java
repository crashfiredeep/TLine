package com.tline.android.app.view;

/**
 * @author Naeem <naeemark@gmail.com>
 * @version 1.0.0
 */

public interface BaseView {

    void showLoading();

    void showLoading(String message);

    void hideLoading();

    void showErrorWithMessage(String errorText);

    void showErrorLoading();

    void showToast(String message);

    void showNetworkError();
}
