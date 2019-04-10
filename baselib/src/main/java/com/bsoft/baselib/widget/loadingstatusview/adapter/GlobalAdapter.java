package com.bsoft.baselib.widget.loadingstatusview.adapter;

import android.view.View;

import com.bsoft.baselib.widget.loadingstatusview.Gloading;
import com.bsoft.baselib.widget.loadingstatusview.GlobalLoadingStatusView;


/**
 * demo to show how to create a {@link Gloading.Adapter}
 * @see GlobalLoadingStatusView
 * @author billy.qi
 * @since 19/3/18 18:37
 */
public class GlobalAdapter implements Gloading.Adapter {
    String HIDE_LOADING_STATUS_MSG = "hide_loading_status_msg";
    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        GlobalLoadingStatusView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView != null && convertView instanceof GlobalLoadingStatusView) {
            loadingStatusView = (GlobalLoadingStatusView) convertView;
        }
        if (loadingStatusView == null) {
            loadingStatusView = new GlobalLoadingStatusView(holder.getContext(), holder.getRetryTask());
        }
        loadingStatusView.setStatus(status);
        Object data = holder.getData();
        //show or not show msg view
        boolean hideMsgView = HIDE_LOADING_STATUS_MSG.equals(data);
        loadingStatusView.setMsgViewVisibility(!hideMsgView);
        return loadingStatusView;
    }

}
