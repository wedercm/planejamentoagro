package br.com.planejamentoagro.adpter;

import java.util.ArrayList;
import java.util.List;

import br.com.planejamentoagro.R;
import br.com.planejamentoagro.controller.ListaClientesFragment;
import br.com.planejamentoagro.controller.ListaTalhaoComAplicacaoFragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.ViewGroup;


public class TabPagerAdapter extends FragmentPagerAdapter {
	private static List<Fragment> fragments = new ArrayList<Fragment>();
	private final int [] imageResId = {
			R.drawable.ic_action_person,
			R.drawable.ic_action_talhoes
	};
	private Context context;
	public TabPagerAdapter(FragmentManager fm, Context context) {
		super(fm);
		this.context = context;
		fragments.add(new ListaClientesFragment());
		fragments.add(new ListaTalhaoComAplicacaoFragment());
		
	}
	@Override
	public CharSequence getPageTitle(int position) {
//		return TITLE[position];
		Drawable image = context.getResources().getDrawable(imageResId[position]);
	    image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
	    SpannableString sb = new SpannableString(" ");
	    ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
	    sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	    return sb;
	}
	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 1:
			return fragments.get(position);
		case 2:
			return fragments.get(position);
		default:
			return fragments.get(position);
		}
	}
	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public void setPrimaryItem(ViewGroup container, int position, Object object) {
		super.setPrimaryItem(container, 1, object);
	}
}