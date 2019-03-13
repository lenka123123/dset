package com.wokun.dset.ucenter.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.shantoo.widget.toast.RxToast;
import com.wokun.dset.R;
import com.wokun.dset.callback.DialogCallback;
import com.wokun.dset.login.LoginMgr;
import com.wokun.dset.model.Constants;
import com.wokun.dset.response.BaseResponse;
import com.wokun.dset.ucenter.bean.BankCardBean;
import com.wokun.dset.utils.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wokun.dset.utils.MD5.ParameterUtils.removeEmptyData;
import static com.wokun.dset.utils.MD5.ParameterUtils.sortMapByKey;


/**
 * Created by xl on 2018/6/4.
 */

public class SwipAdapter extends RecyclerView.Adapter<SwipAdapter.SwipHolder> {

	private Context mContext;
	private List<BankCardBean> mData;

	public SwipAdapter(Context context, List<BankCardBean> data) {
		this.mContext = context;
		this.mData = data;
	}


	public void delposition(int position) {
		//移除数据

		//mData.remove(mData.get(position));
		notifyItemChanged(position);
	}


	@Override
	public SwipHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new SwipHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_my_service, parent, false));
	}

	@Override
	public void onBindViewHolder(SwipHolder holder, final int position) {
		holder.txtName.setText((CharSequence) mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData == null ? 0 : mData.size();
	}

	public class SwipHolder extends RecyclerView.ViewHolder {

		private TextView txtName;

		public SwipHolder(View itemView) {
			super(itemView);
			txtName = itemView.findViewById(R.id.txtName);
		}
	}
}
