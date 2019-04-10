package com.bsoft.baselib.widget.loadingstatusview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.bsoft.baselib.widget.loadingstatusview.Gloading;
import com.bsoft.baselib.widget.loadingstatusview.GlobalSpecialLoadingStatusView;


/**
 * demo:
 *      when status == STATUS_LOADING use another UI
 *      otherwise, use the same UI as the global status view
 * @author billy.qi
 * @since 19/3/19 23:20
 */
public class SpecialAdapter implements Gloading.Adapter {
    String HIDE_LOADING_STATUS_MSG = "hide_loading_status_msg";
    @Override
    public View getView(Gloading.Holder holder, View convertView, int status) {
        GlobalSpecialLoadingStatusView loadingStatusView = null;
        //reuse the old view, if possible
        if (convertView != null && convertView instanceof GlobalSpecialLoadingStatusView) {
            loadingStatusView = (GlobalSpecialLoadingStatusView) convertView;
        }
        if (loadingStatusView == null) {
            loadingStatusView = new GlobalSpecialLoadingStatusView(holder.getContext(), holder.getRetryTask());
        }
        loadingStatusView.setStatus(status);
        Object data = holder.getData();
        //show or not show msg view
        boolean hideMsgView = HIDE_LOADING_STATUS_MSG.equals(data);
        loadingStatusView.setMsgViewVisibility(!hideMsgView);
        return loadingStatusView;
    }

}
